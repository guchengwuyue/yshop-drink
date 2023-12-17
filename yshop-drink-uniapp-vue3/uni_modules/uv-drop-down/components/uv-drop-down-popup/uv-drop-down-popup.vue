<template>
	<view class="uv-drop-down-popup">
		<uv-transition :show="show" mode="fade" :duration="300" :custom-style="overlayStyle" @click="clickOverlay">
			<view class="uv-dp__container" ref="uvDPContainer" :style="{height: `${height}px`}" @click.stop="blockClick">
				<view class="uv-dp__container__list" ref="uvDPList">
					<slot>
						<view class="uv-dp__container__list--item" v-for="(item,index) in list" :key="index" @click="clickHandler(item,index)" :style="[itemCustomStyle(index)]">
							<uv-text :text="item[keyName]" :size="getTextSize(index)" :color="getTextColor(index)"></uv-text>
						</view>
					</slot>
				</view>
			</view>
		</uv-transition>
	</view>
</template>
<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js';
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js';
	// #ifdef APP-NVUE
	const animation = uni.requireNativePlugin('animation');
	const dom = uni.requireNativePlugin('dom');
	// #endif
	/**
	 * DropDownPopup 下拉框
	 * @description 下拉筛选框
	 * @tutorial https://ext.dcloud.net.cn/plugin?name=uv-drop-down
	 * @property {String | Number} name 字段标识
	 * @property {String | Number} zIndex 弹出层的层级
	 * @property {String | Number} opacity 遮罩层的透明度
	 * @property {Boolean} clickOverlayOnClose 是否允许点击遮罩层关闭弹窗
	 * @property {Object} currentDropItem 当前下拉筛选菜单对象
	 * @property {String} keyName 指定从当前下拉筛选菜单对象元素中读取哪个属性作为文本展示
	 */
	export default {
		name: 'uv-drop-down-popup',
		mixins: [mpMixin, mixin],
		props: {
			sign: {
				type: [String, Number],
				default: 'UVDROPDOWN'
			},
			zIndex: {
				type: [Number, String],
				default: 999
			},
			opacity: {
				type: [Number, String],
				default: 0.5
			},
			clickOverlayOnClose: {
				type: Boolean,
				default: true
			},
			// 当前下拉选项对象
			currentDropItem: {
				type: Object,
				default () {
					return {
						activeIndex: 0,
						child: []
					}
				}
			},
			keyName: {
				type: String,
				default: 'label'
			}
		},
		data() {
			return {
				show: false,
				rect: {},
				height: 0
			}
		},
		computed: {
			overlayStyle() {
				let { height = 0, top = 0 } = this.rect;
				// #ifdef H5
				top += this.$uv.sys().windowTop;
				// #endif
				const style = {
					position: 'fixed',
					top: `${top+height}px`,
					left: 0,
					right: 0,
					zIndex: this.zIndex,
					bottom: 0,
					'background-color': `rgba(0, 0, 0, ${this.opacity})`
				}
				return this.$uv.deepMerge(style, this.$uv.addStyle(this.customStyle))
			},
			list() {
				try {
					return Array.isArray(this.currentDropItem.child) ? this.currentDropItem.child : [];
				} catch (e) {
					return [];
				}
			},
			getTextColor(index) {
				return index => {
					const active = this.currentDropItem.activeIndex == index;
					const color = this.currentDropItem.color;
					const activeColor = this.currentDropItem.activeColor;
					if (active) {
						return activeColor ? activeColor : '#3c9cff';
					}
					return color ? color : '#333';
				}
			},
			getTextSize(index) {
				return index => {
					const active = this.currentDropItem.activeIndex == index;
					const size = this.currentDropItem.size;
					const activeSize = this.currentDropItem.activeSize;
					if (active) {
						return activeSize ? activeSize : '30rpx';
					}
					return size ? size : '30rpx';
				}
			},
			itemCustomStyle() {
				return index => {
					const active = this.currentDropItem.activeIndex == index;
					const style = {};
					if (active && this.currentDropItem.itemActiveCustomStyle) {
						return this.$uv.deepMerge(style, this.$uv.addStyle(this.currentDropItem.itemActiveCustomStyle));
					}
					if (this.currentDropItem.itemCustomStyle) {
						return this.$uv.deepMerge(style, this.$uv.addStyle(this.currentDropItem.itemCustomStyle))
					}
					return style;
				}
			}
		},
		created() {
			this.init();
		},
		methods: {
			blockClick() {},
			clickHandler(item, index) {
				this.currentDropItem.activeIndex = index;
				this.$emit('clickItem', item);
				this.close();
			},
			init() {
				uni.$off(`${this.sign}_GETRECT`);
				uni.$on(`${this.sign}_GETRECT`, rect => {
					this.rect = rect;
				})
				uni.$off(`${this.sign}_CLICKMENU`);
				uni.$on(`${this.sign}_CLICKMENU`, async res => {
					if (res.show) {
						this.open();
					} else {
						this.close();
					}
				})
			},
			open() {
				this.show = true;
				this.$nextTick(async () => {
					// #ifndef H5 || MP-WEIXIN
					await this.$uv.sleep(60);
					// #endif
					const res = await this.queryRect();
					// #ifndef APP-NVUE
					this.height = res.height;
					// #endif
					// #ifdef APP-NVUE
					this.animation(res.height);
					// #endif
					this.$emit('popupChange', { show: true });
				})
			},
			close() {
				if(!this.show) return;
				this.height = 0;
				// #ifndef APP-NVUE
				this.height = 0;
				// #endif
				// #ifdef APP-NVUE
				this.animation(0);
				// #endif
				this.show = false;
				uni.$emit(`${this.sign}_CLOSEPOPUP`);
				this.$emit('popupChange', { show: false });
			},
			clickOverlay() {
				if (this.clickOverlayOnClose) {
					this.close();
				}
			},
			// 查询内容高度
			queryRect() {
				// #ifndef APP-NVUE
				// 组件内部一般用this.$uvGetRect，对外的为getRect，二者功能一致，名称不同
				return new Promise(resolve => {
					this.$uvGetRect(`.uv-dp__container__list`).then(size => {
						resolve(size)
					})
				})
				// #endif
				// #ifdef APP-NVUE
				// nvue下，使用dom模块查询元素高度
				// 返回一个promise，让调用此方法的主体能使用then回调
				return new Promise(resolve => {
					dom.getComponentRect(this.$refs.uvDPList, res => {
						resolve(res.size)
					})
				})
				// #endif
			},
			// nvue下设置高度
			animation(height, duration = 200) {
				// #ifdef APP-NVUE
				const ref = this.$refs['uvDPContainer'];
				animation.transition(ref, {
					styles: {
						height: `${height}px`
					},
					duration
				})
				// #endif
			}
		}
	}
</script>
<style scoped lang="scss">
	.uv-dp__container {
		/* #ifndef APP-NVUE */
		overflow: hidden;
		transition: all .15s;
		/* #endif */
		background-color: #fff;
	}
	.uv-dp__container__list {
		&--item {
			padding: 20rpx 60rpx;
		}
	}
</style>
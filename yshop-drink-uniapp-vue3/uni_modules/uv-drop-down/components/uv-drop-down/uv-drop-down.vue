<template>
	<uv-sticky :disabled="!isSticky">
		<view ref="dropDownRef" class="uv-drop-down" :style="[$uv.addStyle(customStyle)]">
			<slot></slot>
		</view>
	</uv-sticky>
</template>
<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js';
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js';
	// #ifdef APP-NVUE
	const dom = uni.requireNativePlugin('dom');
	// #endif
	/**
	 * DropDown 下拉框
	 * @description 下拉筛选
	 * @tutorial https://ext.dcloud.net.cn/plugin?name=uv-drop-down
	 * @property {String | Number} sign 组件唯一标识，需要手动传
	 * @property {Boolean} is-sticky = [true|false] 是否吸顶
	 * @property {Array} default-value 默认值，表示参数value属于这里面的值，就说明是未选中即是默认展示的值。eg：上面示例中的{label: '全部',value: 'all'} 即是默认值。后续处理逻辑也可以根据是否是其中值进行过滤。
	 * @property {String} textSize 每项字体大小
	 * @property {String} textColor 每项文本颜色
	 * @property {String} textActiveSize 每项选中状态字体大小
	 * @property {String} textActiveColor 每项选中状态文本颜色
	 * @property {Object} extraIcon 每项右侧图标
	 * @property {Object} extraActiveIcon 每项选中后右侧图标
	 */
	export default {
		name: 'uv-drop-down',
		mixins: [mpMixin, mixin],
		emits: ['click'],
		props: {
			isSticky: {
				type: Boolean,
				default: true
			},
			sign: {
				type: [String, Number],
				default: 'UVDROPDOWN'
			},
			defaultValue: {
				type: Array,
				default: () => [0, '0', 'all']
			},
			textSize: {
				type: String,
				default: '30rpx'
			},
			textColor: {
				type: String,
				default: '#333'
			},
			textActiveSize: {
				type: String,
				default: '30rpx'
			},
			textActiveColor: {
				type: String,
				default: '#3c9cff'
			},
			extraIcon: {
				type: Object,
				default () {
					return {
						name: 'arrow-down',
						size: '30rpx',
						color: '#333'
					}
				}
			},
			extraActiveIcon: {
				type: Object,
				default () {
					return {
						name: 'arrow-up',
						size: '30rpx',
						color: '#3c9cff'
					}
				}
			}
		},
		computed: {
			parentData() {
				return [this.defaultValue, this.textSize, this.textColor, this.textActiveColor, this.textActiveSize, this.extraIcon, this.extraActiveIcon, this.sign, this.clickHandler];
			}
		},
		mounted() {
			this.init();
		},
		methods: {
			init() {
				uni.$emit(`${this.sign}_CLICKMENU`, {
					show: false
				});
				this.$nextTick(async () => {
					const rect = await this.queryRect();
					uni.$emit(`${this.sign}_GETRECT`, rect);
				})
			},
			// 查询内容高度
			queryRect() {
				// #ifndef APP-NVUE
				// 组件内部一般用this.$uvGetRect，对外的为getRect，二者功能一致，名称不同
				return new Promise(resolve => {
					this.$uvGetRect(`.uv-drop-down`).then(size => {
						resolve(size)
					})
				})
				// #endif
				// #ifdef APP-NVUE
				// nvue下，使用dom模块查询元素高度
				// 返回一个promise，让调用此方法的主体能使用then回调
				return new Promise(resolve => {
					dom.getComponentRect(this.$refs.dropDownRef, res => {
						res.size.top = res.size.top <= 0 ? 0 : res.size.top;
						resolve(res.size)
					})
				})
				// #endif
			},
			clickHandler(data) {
				this.$emit('click', data);
			}
		}
	}
</script>
<style scoped lang="scss">
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
	.uv-drop-down {
		@include flex;
		justify-content: space-between;
		background-color: #fff;
		border-bottom: 1px solid #dadbde;
	}
</style>
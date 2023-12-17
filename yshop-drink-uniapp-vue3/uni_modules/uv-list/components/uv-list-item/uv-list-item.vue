<template>
	<!-- #ifdef APP-NVUE -->
	<cell :keep-scroll-position="keepScrollPosition">
		<!-- #endif -->
		<view 
			:class="{ 'uv-list-item--disabled': disabled }" 
			:style="[$uv.addStyle(customStyle),{'background-color':customStyle.backgroundColor?customStyle.backgroundColor:'#fff'}]"
			:hover-class="(!clickable && !link) || disabled || showSwitch ? '' : 'uv-list-item--hover'"
			class="uv-list-item" @click="onClick">
			<view v-if="!isFirstChild" class="border--left" :class="{ 'uv-list--border': border }"></view>
			<view class="uv-list-item__wrapper">
				<slot>
					<view class="uv-list-item__container"
						:class="{ 'container--right': showArrow || link, 'flex--direction': directionData === 'column'}"
						:style="{paddingTop:padding.top,paddingLeft:padding.left,paddingRight:padding.right,paddingBottom:padding.bottom}">
						<slot name="header">
							<view class="uv-list-item__header">
								<view v-if="thumb" class="uv-list-item__icon">
									<image :src="thumb" class="uv-list-item__icon-img" :class="['uv-list--' + thumbSize]" />
								</view>
								<view v-else-if="showExtraIcon" class="uv-list-item__icon">
									<uv-icon :name="extraIcon.icon" :customPrefix="extraIcon.customPrefix" :color="extraIcon.color" :size="extraIcon.size" />
								</view>
							</view>
						</slot>
						<slot name="body">
							<view class="uv-list-item__content"
								:class="{ 'uv-list-item__content--center': thumb || showExtraIcon || showBadge || showSwitch }">
								<text v-if="title" class="uv-list-item__content-title"
									:class="[ellipsis !== 0 && ellipsis <= 2 ? 'uv-ellipsis-' + ellipsis : '']">{{ title }}</text>
								<text v-if="note" class="uv-list-item__content-note">{{ note }}</text>
							</view>
						</slot>
						<slot name="footer">
							<view v-if="rightText || showBadge || showSwitch" class="uv-list-item__extra"
								:class="{ 'flex--justify': directionData === 'column' }">
								<text v-if="rightText" class="uv-list-item__extra-text">{{ rightText }}</text>
								<uv-badge
									v-if="showBadge"
									:show="!!(badge.show || badge.isDot || badge.value)"
									:isDot="badge.isDot"
									:value="badge.value"
									:max="badge.max"
									:type="badge.type"
									:showZero="badge.showZero"
									:bgColor="badge.bgColor"
									:color="badge.color"
									:shape="badge.shape"
									:numberType="badge.numberType"
									:inverted="badge.inverted"
									customStyle="margin-left: 4px;"
								></uv-badge>
								<uv-switch v-if="showSwitch" :value="switchChecked" :disabled="disabled" @change="onSwitchChange"></uv-switch>
							</view>
						</slot>
					</view>
				</slot>
			</view>
			<uv-icon v-if="showArrow || link" size="34rpx" class="uv-icon-wrapper" color="#bbb" name="arrow-right" />
		</view>
		<!-- #ifdef APP-NVUE -->
	</cell>
	<!-- #endif -->
</template>

<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	/**
	 * ListItem 列表子组件
	 * @description 列表子组件
	 * @tutorial https://ext.dcloud.net.cn/plugin?id=24
	 * @property {String} 	title 							标题
	 * @property {String} 	note 							描述
	 * @property {String} 	thumb 							左侧缩略图，若thumb有值，则不会显示扩展图标
	 * @property {String}  	thumbSize = [lg|base|sm]		略缩图大小
	 * 	@value 	 lg			大图
	 * 	@value 	 base		一般
	 * 	@value 	 sm			小图
	 * @property {String} 	rightText 						右侧文字内容
	 * @property {Boolean} 	disabled = [true|false]			是否禁用
	 * @property {Boolean} 	clickable = [true|false] 		是否开启点击反馈
	 * @property {String} 	link = [navigateTo|redirectTo|reLaunch|switchTab] 是否展示右侧箭头并开启点击反馈
	 *  @value 	navigateTo 	同 uni.navigateTo()
	 * 	@value redirectTo 	同 uni.redirectTo()
	 * 	@value reLaunch   	同 uni.reLaunch()
	 * 	@value switchTab  	同 uni.switchTab()
	 * @property {String | PageURIString} 	to  			跳转目标页面
	 * @property {Boolean} 	showBadge = [true|false] 		是否显示数字角标
	 * @property {Object} 	badge  扩展数字角标的参数，格式为 :badge="{value: 122}"
	 * @property {Boolean} 	showSwitch = [true|false] 		是否显示Switch
	 * @property {Boolean} 	switchChecked = [true|false] 	Switch是否被选中
	 * @property {Boolean} 	showExtraIcon = [true|false] 	左侧是否显示扩展图标
	 * @property {Object} 	extraIcon 						扩展图标参数，格式为 :extraIcon="{icon: 'photo',size: '30px'}"
	 * @property {String} 	direction = [row|column]		排版方向
	 * @value row 			水平排列
	 * @value column 		垂直排列
	 * @event {Function} 	click 							点击 uniListItem 触发事件
	 * @event {Function} 	switchChange 					点击切换 Switch 时触发
	 */
	export default {
		name: 'uv-list-item',
		mixins: [mpMixin, mixin],
		emits: ['click', 'switchChange'],
		props: {
			direction: {
				type: String,
				default: 'row'
			},
			title: {
				type: String,
				default: ''
			},
			note: {
				type: String,
				default: ''
			},
			ellipsis: {
				type: [Number, String],
				default: 0
			},
			disabled: {
				type: [Boolean, String],
				default: false
			},
			clickable: {
				type: Boolean,
				default: false
			},
			showArrow: {
				type: [Boolean, String],
				default: false
			},
			link: {
				type: [Boolean, String],
				default: false
			},
			to: {
				type: String,
				default: ''
			},
			showSwitch: {
				type: [Boolean, String],
				default: false
			},
			switchChecked: {
				type: [Boolean, String],
				default: false
			},
			showBadge: {
				type: [Boolean, String],
				default: false
			},
			badge: {
				type: Object,
				default () {
					return {}
				}
			},
			rightText: {
				type: String,
				default: ''
			},
			thumb: {
				type: String,
				default: ''
			},
			thumbSize: {
				type: String,
				default: 'base'
			},
			showExtraIcon: {
				type: [Boolean, String],
				default: false
			},
			extraIcon: {
				type: Object,
				default () {
					return {
						name: '',
						color: '#000000',
						size: 20,
						customPrefix: ''
					};
				}
			},
			border: {
				type: Boolean,
				default: false
			},
			customStyle: {
				type: Object,
				default () {
					return {
						padding: '',
						backgroundColor: '#FFFFFF'
					}
				}
			},
			keepScrollPosition: {
				type: Boolean,
				default: false
			}
		},
		computed: {
			directionData(){
				return this.direction ? this.direction : (this.parentData.direction ? this.parentData.direction : 'row');
			}
		},
		watch: {
			'customStyle.padding': {
				handler(padding) {
					if(padding) this.setPadding(padding);
				},
				immediate: true
			}
		},
		data() {
			return {
				isFirstChild: false,
				padding: {
					top: "",
					right: "",
					bottom: "",
					left: ""
				},
				parentData: {
					direction: 'row',
					padding: 0
				}
			};
		},
		created() {
			this.updateParentData();
		},
		mounted() {
			this.init();
			this.list = this.getForm()
			// 判断是否存在 uv-list 组件
			if (this.list) {
				if (!this.list.firstChildAppend) {
					this.list.firstChildAppend = true;
					this.isFirstChild = true;
				}
			}
		},
		methods: {
			init(){
				if (!this.parent) {
					this.$uv.error('uv-list-item必须搭配uv-list组件使用');
				}
				this.$nextTick(()=>{
					if(!(this.padding.top || this.padding.right|| this.padding.bottom|| this.padding.left)){
						this.setPadding(this.parentData.padding);
					}
				})
			},
			updateParentData() {
				this.getParentData('uv-list');
			},
			setPadding(padding){
				if(typeof padding == 'number'){
					padding += ''
				}
				let paddingArr = padding.split(' ')
				if (paddingArr.length === 1) {
					const allPadding = paddingArr[0]
					this.padding = {
						"top": allPadding,
						"right": allPadding,
						"bottom": allPadding,
						"left": allPadding
					}
				} else if (paddingArr.length === 2) {
					const [verticalPadding, horizontalPadding] = paddingArr;
					this.padding = {
						"top": verticalPadding,
						"right": horizontalPadding,
						"bottom": verticalPadding,
						"left": horizontalPadding
					}
				} else if (paddingArr.length === 4) {
						const [topPadding, rightPadding, bottomPadding, leftPadding] = paddingArr;
						this.padding = {
							"top": topPadding,
							"right": rightPadding,
							"bottom": bottomPadding,
							"left": leftPadding
						}
				}
			},
			/**
			 * 获取父元素实例
			 */
			getForm(name = 'uniList') {
				let parent = this.$parent;
				let parentName = parent.$options.name;
				while (parentName !== name) {
					parent = parent.$parent;
					if (!parent) return false
					parentName = parent.$options.name;
				}
				return parent;
			},
			onClick() {
				if (this.to !== '') {
					this.openPage();
					return;
				}
				if (this.clickable || this.link) {
					this.$emit('click', {
						data: {}
					});
				}
			},
			onSwitchChange(e) {
				this.$emit('switchChange', e);
			},
			openPage() {
				if (['navigateTo', 'redirectTo', 'reLaunch', 'switchTab'].indexOf(this.link) !== -1) {
					this.pageApi(this.link);
				} else {
					this.pageApi('navigateTo');
				}
			},
			pageApi(api) {
				let callback = {
					url: this.to,
					success: res => {
						this.$emit('click', {
							data: res
						});
					},
					fail: err => {
						this.$emit('click', {
							data: err
						});
					}
				}
				switch (api) {
					case 'navigateTo':
						uni.navigateTo(callback)
						break
					case 'redirectTo':
						uni.redirectTo(callback)
						break
					case 'reLaunch':
						uni.reLaunch(callback)
						break
					case 'switchTab':
						uni.switchTab(callback)
						break
					default:
						uni.navigateTo(callback)
				}
			}
		}
	};
</script>

<style lang="scss" scoped>
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
	$uv-font-size-sm:12px;
	$uv-font-size-base:14px;
	$uv-font-size-lg:16px;
	$uv-spacing-col-lg: 12px;
	$uv-spacing-row-lg: 15px;
	$uv-img-size-sm:20px;
	$uv-img-size-base:26px;
	$uv-img-size-lg:40px;
	$uv-border-color:#e5e5e5;
	$uv-bg-color-hover:#f1f1f1;
	$uv-text-color-grey:#999;
	$list-item-pd: $uv-spacing-col-lg $uv-spacing-row-lg;

	.uv-list-item {
		@include flex(row);
		font-size: $uv-font-size-lg;
		position: relative;
		justify-content: space-between;
		align-items: center;
		background-color: #fff;
		/* #ifdef H5 */
		cursor: pointer;
		/* #endif */
	}

	.uv-list-item--disabled {
		opacity: 0.3;
	}

	.uv-list-item--hover {
		background-color: $uv-bg-color-hover !important;
	}
	
	.uv-list-item__wrapper {
		@include flex(column);
		flex: 1;
	}

	.uv-list-item__container {
		position: relative;
		@include flex(row);
		padding: $list-item-pd;
		padding-left: $uv-spacing-row-lg;
		flex: 1;
		overflow: hidden;
	}

	.container--right {
		padding-right: 0;
	}

	.uv-list--border {
		position: absolute;
		top: 0;
		right: 0;
		left: 0;
		/* #ifdef APP-NVUE */
		border-top-color: $uv-border-color;
		border-top-style: solid;
		border-top-width: 0.5px;
		/* #endif */
	}

	/* #ifndef APP-NVUE */
	.uv-list--border:after {
		position: absolute;
		top: 0;
		right: 0;
		left: 0;
		height: 1px;
		content: '';
		-webkit-transform: scaleY(0.5);
		transform: scaleY(0.5);
		background-color: $uv-border-color;
	}

	/* #endif */
	.uv-list-item__content {
		@include flex(column);
		padding-right: 8px;
		flex: 1;
		color: #3b4144;
		justify-content: space-between;
		overflow: hidden;
	}

	.uv-list-item__content--center {
		justify-content: center;
	}

	.uv-list-item__content-title {
		font-size: $uv-font-size-base;
		color: #3b4144;
		overflow: hidden;
	}

	.uv-list-item__content-note {
		margin-top: 6rpx;
		color: $uv-text-color-grey;
		font-size: $uv-font-size-sm;
		overflow: hidden;
	}

	.uv-list-item__extra {
		@include flex(row);
		justify-content: flex-end;
		align-items: center;
	}

	.uv-list-item__header {
		@include flex(row);
		align-items: center;
	}

	.uv-list-item__icon {
		margin-right: 18rpx;
		flex-direction: row;
		justify-content: center;
		align-items: center;
	}

	.uv-list-item__icon-img {
		/* #ifndef APP-NVUE */
		display: block;
		/* #endif */
		height: $uv-img-size-base;
		width: $uv-img-size-base;
		margin-right: 10px;
	}

	.uv-icon-wrapper {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		align-items: center;
		padding: 0 10px;
	}

	.flex--direction {
		flex-direction: column;
		/* #ifndef APP-NVUE */
		align-items: initial;
		/* #endif */
	}

	.flex--justify {
		/* #ifndef APP-NVUE */
		justify-content: initial;
		/* #endif */
	}

	.uv-list--lg {
		height: $uv-img-size-lg;
		width: $uv-img-size-lg;
	}

	.uv-list--base {
		height: $uv-img-size-base;
		width: $uv-img-size-base;
	}

	.uv-list--sm {
		height: $uv-img-size-sm;
		width: $uv-img-size-sm;
	}

	.uv-list-item__extra-text {
		color: $uv-text-color-grey;
		font-size: $uv-font-size-sm;
	}

	.uv-ellipsis-1 {
		/* #ifndef APP-NVUE */
		overflow: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
		/* #endif */
		/* #ifdef APP-NVUE */
		lines: 1;
		text-overflow: ellipsis;
		/* #endif */
	}

	.uv-ellipsis-2 {
		/* #ifndef APP-NVUE */
		overflow: hidden;
		text-overflow: ellipsis;
		display: -webkit-box;
		-webkit-line-clamp: 2;
		-webkit-box-orient: vertical;
		/* #endif */
		/* #ifdef APP-NVUE */
		lines: 2;
		text-overflow: ellipsis;
		/* #endif */
	}
</style>
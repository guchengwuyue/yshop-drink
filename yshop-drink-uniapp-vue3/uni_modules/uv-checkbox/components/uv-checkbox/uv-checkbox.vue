<template>
	<view
	  class="uv-checkbox"
	  :style="[checkboxStyle]"
	  @tap.stop="wrapperClickHandler"
	  :class="[`uv-checkbox-label--${parentData.iconPlacement}`, parentData.borderBottom && parentData.placement === 'column' && 'uv-border-bottom']"
	>
		<view
		  class="uv-checkbox__icon-wrap"
		  @tap.stop="iconClickHandler"
		  :class="iconClasses"
		  :style="[iconWrapStyle]"
		>
			<slot name="icon">
				<uv-icon
				  class="uv-checkbox__icon-wrap__icon"
				  name="checkbox-mark"
				  :size="elIconSize"
				  :color="elIconColor"
				/>
			</slot>
		</view>
		<view 
			class="uv-checkbox__label-wrap" 
			@tap.stop="labelClickHandler">
			<slot>
				<text
				  :style="{
						color: elDisabled ? elInactiveColor : elLabelColor,
						fontSize: elLabelSize,
						lineHeight: elLabelSize
					}"
				>{{label}}</text>
			</slot>
		</view>
		
	</view>
</template>

<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	import props from './props.js';
	/**
	 * checkbox  复选框
	 * @description 复选框组件一般用于需要多个选择的场景，该组件功能完整，使用方便
	 * @tutorial https://www.uvui.cn/components/checkbox.html
	 * @property {String | Number | Boolean}	name			checkbox组件的标示符
	 * @property {String}						shape			形状，square为方形，circle为圆型
	 * @property {String | Number}				size			整体的大小
	 * @property {Boolean}						checked			是否默认选中
	 * @property {String | Boolean}				disabled		是否禁用
	 * @property {String}						activeColor		选中状态下的颜色，如设置此值，将会覆盖parent的activeColor值
	 * @property {String}						inactiveColor	未选中的颜色
	 * @property {String | Number}				iconSize		图标的大小，单位px
	 * @property {String}						iconColor		图标颜色
	 * @property {String | Number}				label			label提示文字，因为nvue下，直接slot进来的文字，由于特殊的结构，无法修改样式
	 * @property {String}						labelColor 		label的颜色
	 * @property {String | Number}				labelSize		label的字体大小，px单位
	 * @property {String | Boolean}				labelDisabled	是否禁止点击提示语选中复选框
	 * @property {Object}						customStyle		定义需要用到的外部样式
	 * 
	 * @event {Function}	change	任一个checkbox状态发生变化时触发，回调为一个对象
	 * @example <uv-checkbox v-model="checked" :disabled="false">天涯</uv-checkbox>
	 */
	export default {
		name: "uv-checkbox",
		mixins: [mpMixin, mixin, props],
		data() {
			return {
				isChecked: false,
				// 父组件的默认值，因为头条小程序不支持在computed中使用this.parent.shape的形式
				// 故只能使用如此方法
				parentData: {
					iconSize: 12,
					labelDisabled: null,
					disabled: null,
					shape: 'square',
					activeColor: null,
					inactiveColor: null,
					size: 18,
					value: null,
					modelValue: null,
					iconColor: null,
					placement: 'row',
					borderBottom: false,
					iconPlacement: 'left',
					labelSize: 14,
					labelColor: '#303133'
				}
			}
		},
		computed: {
			// 是否禁用，如果父组件uv-raios-group禁用的话，将会忽略子组件的配置
			elDisabled() {
				return this.disabled !== '' ? this.disabled : this.parentData.disabled !== null ? this.parentData.disabled : false;
			},
			// 是否禁用label点击
			elLabelDisabled() {
				return this.labelDisabled !== '' ? this.labelDisabled : this.parentData.labelDisabled !== null ? this.parentData.labelDisabled :
					false;
			},
			// 组件尺寸，对应size的值，默认值为21px
			elSize() {
				return this.size ? this.size : (this.parentData.size ? this.parentData.size : 21);
			},
			// 组件的勾选图标的尺寸，默认12px
			elIconSize() {
				return this.iconSize ? this.iconSize : (this.parentData.iconSize ? this.parentData.iconSize : 12);
			},
			// 组件选中激活时的颜色
			elActiveColor() {
				return this.activeColor ? this.activeColor : (this.parentData.activeColor ? this.parentData.activeColor : '#2979ff');
			},
			// 组件选未中激活时的颜色
			elInactiveColor() {
				return this.inactiveColor ? this.inactiveColor : (this.parentData.inactiveColor ? this.parentData.inactiveColor :
					'#c8c9cc');
			},
			// label的颜色
			elLabelColor() {
				return this.labelColor ? this.labelColor : (this.parentData.labelColor ? this.parentData.labelColor : '#606266')
			},
			// 组件的形状
			elShape() {
				return this.shape ? this.shape : (this.parentData.shape ? this.parentData.shape : 'circle');
			},
			// label大小
			elLabelSize() {
				return this.$uv.addUnit(this.labelSize ? this.labelSize : (this.parentData.labelSize ? this.parentData.labelSize :
					'15'))
			},
			elIconColor() {
				const iconColor = this.iconColor ? this.iconColor : (this.parentData.iconColor ? this.parentData.iconColor :
					'#ffffff');
				// 图标的颜色
				if (this.elDisabled) {
					// disabled状态下，已勾选的checkbox图标改为elInactiveColor
					return this.isChecked ? this.elInactiveColor : 'transparent'
				} else {
					return this.isChecked ? iconColor : 'transparent'
				}
			},
			iconClasses() {
				let classes = []
				// 组件的形状
				classes.push('uv-checkbox__icon-wrap--' + this.elShape)
				if (this.elDisabled) {
					classes.push('uv-checkbox__icon-wrap--disabled')
				}
				if (this.isChecked && this.elDisabled) {
					classes.push('uv-checkbox__icon-wrap--disabled--checked')
				}
				// 支付宝，头条小程序无法动态绑定一个数组类名，否则解析出来的结果会带有","，而导致失效
				// #ifdef MP-ALIPAY || MP-TOUTIAO
				classes = classes.join(' ')
				// #endif
				return classes
			},
			iconWrapStyle() {
				// checkbox的整体样式
				const style = {}
				style.backgroundColor = this.isChecked && !this.elDisabled ? this.elActiveColor : '#ffffff'
				style.borderColor = this.isChecked && !this.elDisabled ? this.elActiveColor : this.elInactiveColor
				style.width = this.$uv.addUnit(this.elSize)
				style.height = this.$uv.addUnit(this.elSize)
				// 如果是图标在右边的话，移除它的右边距
				if (this.parentData.iconPlacement === 'right') {
					style.marginRight = 0
				}
				return style
			},
			checkboxStyle() {
				const style = {}
				if (this.parentData.borderBottom && this.parentData.placement === 'row') {
					this.$uv.error('检测到您将borderBottom设置为true，需要同时将uv-checkbox-group的placement设置为column才有效')
				}
				// 当父组件设置了显示下边框并且排列形式为纵向时，给内容和边框之间加上一定间隔
				if (this.parentData.borderBottom && this.parentData.placement === 'column') {
					style.paddingBottom = '8px'
				}
				return this.$uv.deepMerge(style, this.$uv.addStyle(this.customStyle))
			}
		},
		created() {
			this.init()
		},
		methods: {
			init() {
				// 支付宝小程序不支持provide/inject，所以使用这个方法获取整个父组件，在created定义，避免循环引用
				this.updateParentData()
				if (!this.parent) {
					this.$uv.error('uv-checkbox必须搭配uv-checkbox-group组件使用')
				}
				this.$nextTick(()=>{
					let parentValue = [];
					if(this.parentData.value.length) {
						parentValue = this.parentData.value;
					} else if (this.parentData.modelValue.length){
						parentValue = this.parentData.modelValue;
					}
					// 设置初始化时，是否默认选中的状态，父组件uv-checkbox-group的value可能是array，所以额外判断
					if (this.checked) {
						this.isChecked = true
					} else if (this.$uv.test.array(parentValue)) {
						// 查找数组是是否存在this.name元素值
						this.isChecked = parentValue.some(item => {
							return item === this.name
						})
					}
				})
			},
			updateParentData() {
				this.getParentData('uv-checkbox-group')
			},
			// 横向两端排列时，点击组件即可触发选中事件
			wrapperClickHandler(e) {
				this.parentData.iconPlacement === 'right' && this.iconClickHandler(e)
			},
			// 点击图标
			iconClickHandler(e) {
				this.preventEvent(e)
				// 如果整体被禁用，不允许被点击
				if (!this.elDisabled) {
					this.setRadioCheckedStatus()
				}
			},
			// 点击label
			labelClickHandler(e) {
				this.preventEvent(e)
				// 如果按钮整体被禁用或者label被禁用，则不允许点击文字修改状态
				if (!this.elLabelDisabled && !this.elDisabled) {
					this.setRadioCheckedStatus()
				}
			},
			emitEvent() {
				this.$emit('change', this.isChecked)
				// 尝试调用uv-form的验证方法，进行一定延迟，否则微信小程序更新可能会不及时
				this.$nextTick(() => {
					this.$uv.formValidate(this, 'change')
				})
			},
			// 改变组件选中状态
			// 这里的改变的依据是，更改本组件的checked值为true，同时通过父组件遍历所有uv-checkbox实例
			// 将本组件外的其他uv-checkbox的checked都设置为false(都被取消选中状态)，因而只剩下一个为选中状态
			setRadioCheckedStatus() {
				// 将本组件标记为与原来相反的状态
				this.isChecked = !this.isChecked
				this.emitEvent()
				typeof this.parent.unCheckedOther === 'function' && this.parent.unCheckedOther(this)
			}
		},
		watch:{
			checked(){
				this.isChecked = this.checked
			}
		}
	}
</script>

<style lang="scss" scoped>
	$show-border: 1;
	$show-border-bottom: 1;
	@import '@/uni_modules/uv-ui-tools/libs/css/variable.scss';
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
	@import '@/uni_modules/uv-ui-tools/libs/css/color.scss';
	$uv-checkbox-label-wrap-padding-right:6px !default;
	$uv-checkbox-icon-wrap-font-size:6px !default;
	$uv-checkbox-icon-wrap-border-width:1px !default;
	$uv-checkbox-icon-wrap-border-color:#c8c9cc !default;
	$uv-checkbox-icon-wrap-icon-line-height:0 !default;
	$uv-checkbox-icon-wrap-circle-border-radius:100% !default;
	$uv-checkbox-icon-wrap-square-border-radius:3px !default;
	$uv-checkbox-icon-wrap-checked-color:#fff !default;
	$uv-checkbox-icon-wrap-checked-background-color:red !default;
	$uv-checkbox-icon-wrap-checked-border-color:#2979ff !default;
	$uv-checkbox-icon-wrap-disabled-background-color:#ebedf0 !default;
	$uv-checkbox-icon-wrap-disabled-checked-color:#c8c9cc !default;
	$uv-checkbox-label-margin-left:5px !default;
	$uv-checkbox-label-margin-right:12px !default;
	$uv-checkbox-label-color:$uv-content-color !default;
	$uv-checkbox-label-font-size:15px !default;
	$uv-checkbox-label-disabled-color:#c8c9cc !default;

	.uv-checkbox {
		/* #ifndef APP-NVUE */
		@include flex(row);
		/* #endif */
		overflow: hidden;
		flex-direction: row;
		align-items: center;

		&-label--left {
			flex-direction: row
		}

		&-label--right {
			flex-direction: row-reverse;
			justify-content: space-between
		}

		&__icon-wrap {
			/* #ifndef APP-NVUE */
			box-sizing: border-box;
			// nvue下，border-color过渡有问题
			transition-property: border-color, background-color, color;
			transition-duration: 0.2s;
			/* #endif */
			color: $uv-content-color;
			@include flex;
			align-items: center;
			justify-content: center;
			color: transparent;
			text-align: center;
			font-size: $uv-checkbox-icon-wrap-font-size;
			border-width: $uv-checkbox-icon-wrap-border-width;
			border-color: $uv-checkbox-icon-wrap-border-color;
			border-style: solid;

			/* #ifdef MP-TOUTIAO */
			// 头条小程序兼容性问题，需要设置行高为0，否则图标偏下
			&__icon {
				line-height: $uv-checkbox-icon-wrap-icon-line-height;
			}

			/* #endif */

			&--circle {
				border-radius: $uv-checkbox-icon-wrap-circle-border-radius;
			}

			&--square {
				border-radius: $uv-checkbox-icon-wrap-square-border-radius;
			}

			&--checked {
				color: $uv-checkbox-icon-wrap-checked-color;
				background-color: $uv-checkbox-icon-wrap-checked-background-color;
				border-color: $uv-checkbox-icon-wrap-checked-border-color;
			}

			&--disabled {
				background-color: $uv-checkbox-icon-wrap-disabled-background-color !important;
			}

			&--disabled--checked {
				color: $uv-checkbox-icon-wrap-disabled-checked-color !important;
			}
		}

		&__label {
			/* #ifndef APP-NVUE */
			word-wrap: break-word;
			/* #endif */
			margin-left: $uv-checkbox-label-margin-left;
			margin-right: $uv-checkbox-label-margin-right;
			color: $uv-checkbox-label-color;
			font-size: $uv-checkbox-label-font-size;

			&--disabled {
				color: $uv-checkbox-label-disabled-color;
			}
		}
		
		&__label-wrap {
			padding-left: $uv-checkbox-label-wrap-padding-right;
		}
	}
</style>

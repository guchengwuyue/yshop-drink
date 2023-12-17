<template>
	<view
	  class="uv-radio"
		@tap.stop="wrapperClickHandler"
	  :style="[radioStyle]"
	  :class="[`uv-radio-label--${parentData.iconPlacement}`, parentData.borderBottom && parentData.placement === 'column' && 'uv-border-bottom']"
	>
		<view
		  class="uv-radio__icon-wrap"
		  @tap.stop="iconClickHandler"
		  :class="iconClasses"
		  :style="[iconWrapStyle]"
		>
			<slot name="icon">
				<uv-icon
				  class="uv-radio__icon-wrap__icon"
				  name="checkbox-mark"
				  :size="elIconSize"
				  :color="elIconColor"
				/>
			</slot>
		</view>
		<view
			class="uv-radio__label-wrap" 
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
	 * radio 单选框
	 * @description 单选框用于有一个选择，用户只能选择其中一个的场景。搭配uv-radio-group使用
	 * @tutorial https://www.uvui.cn/components/radio.html
	 * @property {String | Number}	name			radio的名称
	 * @property {String}			shape			形状，square为方形，circle为圆型
	 * @property {Boolean}			disabled		是否禁用
	 * @property {String | Boolean}	labelDisabled	是否禁止点击提示语选中单选框
	 * @property {String}			activeColor		选中时的颜色，如设置parent的active-color将失效
	 * @property {String}			inactiveColor	未选中的颜色
	 * @property {String | Number}	iconSize		图标大小，单位px
	 * @property {String | Number}	labelSize		label字体大小，单位px
	 * @property {String | Number}	label			label提示文字，因为nvue下，直接slot进来的文字，由于特殊的结构，无法修改样式
	 * @property {String | Number}	size			整体的大小
	 * @property {String}			iconColor		图标颜色
	 * @property {String}			labelColor		label的颜色
	 * @property {Object}			customStyle		组件的样式，对象形式
	 * 
	 * @event {Function} change 某个radio状态发生变化时触发(选中状态)
	 * @example <uv-radio :labelDisabled="false">门掩黄昏，无计留春住</uv-radio>
	 */
	export default {
		name: "uv-radio",

		mixins: [mpMixin, mixin, props],
		data() {
			return {
				checked: false,
				// 当你看到这段代码的时候，
				// 父组件的默认值，因为头条小程序不支持在computed中使用this.parent.shape的形式
				// 故只能使用如此方法
				parentData: {
					iconSize: 12,
					labelSize: 14,
					labelDisabled: null,
					disabled: null,
					shape: null,
					activeColor: null,
					inactiveColor: null,
					size: 18,
					value: null,
					modelValue: null,
					iconColor: null,
					placement: 'row',
					borderBottom: false,
					iconPlacement: 'left'
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
					// disabled状态下，已勾选的radio图标改为elInactiveColor
					return this.checked ? this.elInactiveColor : 'transparent'
				} else {
					return this.checked ? iconColor : 'transparent'
				}
			},
			iconClasses() {
				let classes = []
				// 组件的形状
				classes.push('uv-radio__icon-wrap--' + this.elShape)
				if (this.elDisabled) {
					classes.push('uv-radio__icon-wrap--disabled')
				}
				if (this.checked && this.elDisabled) {
					classes.push('uv-radio__icon-wrap--disabled--checked')
				}
				// 支付宝，头条小程序无法动态绑定一个数组类名，否则解析出来的结果会带有","，而导致失效
				// #ifdef MP-ALIPAY || MP-TOUTIAO
				classes = classes.join(' ')
				// #endif
				return classes
			},
			iconWrapStyle() {
				// radio的整体样式
				const style = {}
				style.backgroundColor = this.checked && !this.elDisabled ? this.elActiveColor : '#ffffff'
				style.borderColor = this.checked && !this.elDisabled ? this.elActiveColor : this.elInactiveColor
				style.width = this.$uv.addUnit(this.elSize)
				style.height = this.$uv.addUnit(this.elSize)
				// 如果是图标在右边的话，移除它的右边距
				if (this.parentData.iconPlacement === 'right') {
					style.marginRight = 0
				}
				return style
			},
			radioStyle() {
				const style = {}
				if (this.parentData.borderBottom && this.parentData.placement === 'row') {
					this.$uv.error('检测到您将borderBottom设置为true，需要同时将uv-radio-group的placement设置为column才有效')
				}
				// 当父组件设置了显示下边框并且排列形式为纵向时，给内容和边框之间加上一定间隔
				if (this.parentData.borderBottom && this.parentData.placement === 'column') {
					// ios像素密度高，需要多一点的距离
					style.paddingBottom = this.$uv.os() === 'ios' ? '12px' : '8px'
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
					this.$uv.error('uv-radio必须搭配uv-radio-group组件使用')
				}
				// 设置初始化时，是否默认选中的状态
				this.$nextTick(()=>{
					let parentValue = null;
					// #ifdef VUE2
					parentValue = this.parentData.value;
					// #endif
					// #ifdef VUE3
					parentValue = this.parentData.modelValue;
					// #endif
					this.checked = this.name === parentValue
				})
			},
			updateParentData() {
				this.getParentData('uv-radio-group')
			},
			// 点击图标
			iconClickHandler(e) {
				this.preventEvent(e)
				// 如果整体被禁用，不允许被点击
				if (!this.elDisabled) {
					this.setRadioCheckedStatus()
				}
			},
			// 横向两端排列时，点击组件即可触发选中事件
			wrapperClickHandler(e) {
				this.parentData.iconPlacement === 'right' && this.iconClickHandler(e)
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
				// uv-radio的checked不为true时(意味着未选中)，才发出事件，避免多次点击触发事件
				if (!this.checked) {
					this.$emit('change', this.name)
					// 尝试调用uv-form的验证方法，进行一定延迟，否则微信小程序更新可能会不及时
					this.$nextTick(() => {
						this.$uv.formValidate(this, 'change')
					})
				}
			},
			// 改变组件选中状态
			// 这里的改变的依据是，更改本组件的checked值为true，同时通过父组件遍历所有uv-radio实例
			// 将本组件外的其他uv-radio的checked都设置为false(都被取消选中状态)，因而只剩下一个为选中状态
			setRadioCheckedStatus() {
				this.emitEvent()
				// 将本组件标记为选中状态
				this.checked = true
				typeof this.parent.unCheckedOther === 'function' && this.parent.unCheckedOther(this)
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
	$uv-radio-label-wrap-padding-right: 6px !default;
	$uv-radio-wrap-font-size: 20px !default;
	$uv-radio-wrap-border-width: 1px !default;
	$uv-radio-wrap-border-color: #c8c9cc !default;
	$uv-radio-line-height: 0 !default;
	$uv-radio-circle-border-radius: 100% !default;
	$uv-radio-square-border-radius: 3px !default;
	$uv-radio-checked-color: #fff !default;
	$uv-radio-checked-background-color: red !default;
	$uv-radio-checked-border-color: #2979ff !default;
	$uv-radio-disabled-background-color: #ebedf0 !default;
	$uv-radio-disabled--checked-color: #c8c9cc !default;
	$uv-radio-label-margin-left: 5px !default;
	$uv-radio-label-margin-right: 12px !default;
	$uv-radio-label-color: $uv-content-color !default;
	$uv-radio-label-font-size: 15px !default;
	$uv-radio-label-disabled-color: #c8c9cc !default;
	.uv-radio {
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
			font-size: $uv-radio-wrap-font-size;
			border-width: $uv-radio-wrap-border-width;
			border-color: $uv-radio-wrap-border-color;
			border-style: solid;
			/* #ifdef MP-TOUTIAO */
			// 头条小程序兼容性问题，需要设置行高为0，否则图标偏下
			&__icon {
				line-height: $uv-radio-line-height;
			}
			/* #endif */
			&--circle {
				border-radius: $uv-radio-circle-border-radius;
			}
			&--square {
				border-radius: $uv-radio-square-border-radius;
			}
			&--checked {
				color: $uv-radio-checked-color;
				background-color: $uv-radio-checked-background-color;
				border-color: $uv-radio-checked-border-color;
			}
			&--disabled {
				background-color: $uv-radio-disabled-background-color !important;
			}
			&--disabled--checked {
				color: $uv-radio-disabled--checked-color !important;
			}
		}
		&__label {
			/* #ifndef APP-NVUE */
			word-wrap: break-word;
			/* #endif */
			margin-left: $uv-radio-label-margin-left;
			margin-right: $uv-radio-label-margin-right;
			color: $uv-radio-label-color;
			font-size: $uv-radio-label-font-size;
			&--disabled {
				color: $uv-radio-label-disabled-color;
			}
		}
		&__label-wrap {
			padding-left: $uv-radio-label-wrap-padding-right;
		}
	}
</style>
<template>
	<view class="uv-form-item">
		<view
			class="uv-form-item__body"
			@tap="clickHandler"
			:style="[$uv.addStyle(customStyle), {
				flexDirection: (labelPosition || parentData.labelPosition) === 'left' ? 'row' : 'column'
			}]"
		>
			<!-- 微信小程序中，将一个参数设置空字符串，结果会变成字符串"true" -->
			<slot name="label">
				<view
					class="uv-form-item__body__left"
					v-if="required || leftIcon || label"
					:style="{
						width: $uv.addUnit(labelWidth || parentData.labelWidth),
						marginBottom: parentData.labelPosition === 'left' ? 0 : '5px',
					}"
				>
					<!-- 为了块对齐 -->
					<view class="uv-form-item__body__left__content">
						<!-- nvue不支持伪元素before -->
						<text
							v-if="required"
							class="uv-form-item__body__left__content__required"
						>*</text>
						<view
							class="uv-form-item__body__left__content__icon"
							v-if="leftIcon"
						>
							<uv-icon
								:name="leftIcon"
								:custom-style="leftIconStyle"
							></uv-icon>
						</view>
						<text
							class="uv-form-item__body__left__content__label"
							:style="[parentData.labelStyle, {
								justifyContent: parentData.labelAlign === 'left' ? 'flex-start' : parentData.labelAlign === 'center' ? 'center' : 'flex-end'
							}]"
						>{{ label }}</text>
					</view>
				</view>
			</slot>
			<view class="uv-form-item__body__right">
				<view class="uv-form-item__body__right__content">
					<view class="uv-form-item__body__right__content__slot">
						<slot />
					</view>
					<view class="item__body__right__content__icon">
						<slot name="right" />
					</view>
				</view>
			</view>
		</view>
		<slot name="error">
			<uv-transition 
			:show="true" 
			:duration="100" 
			mode="fade" 
			v-if="!!message && parentData.errorType === 'message'"
		>
				<text
					class="uv-form-item__body__right__message"
					:style="{
						marginLeft:  $uv.addUnit(parentData.labelPosition === 'top' ? 0 : (labelWidth || parentData.labelWidth))
					}"
				>{{ message }}</text>
			</uv-transition>
		</slot>
		<uv-line
			v-if="borderBottom"
			:color="message && parentData.errorType === 'border-bottom' ? '#f56c6c' : '#d6d7d9'"
		></uv-line>
	</view>
</template>
<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	import props from './props.js';
	/**
	 * Form 表单
	 * @description 此组件一般用于表单场景，可以配置Input输入框，Select弹出框，进行表单验证等。
	 * @tutorial https://www.uvui.cn/components/form.html
	 * @property {String}			label			input的label提示语
	 * @property {String}			prop			绑定的值
	 * @property {String | Boolean}	borderBottom	是否显示表单域的下划线边框
	 * @property {String | Number}	labelWidth		label的宽度，单位px
	 * @property {String}			rightIcon		右侧图标
	 * @property {String}			leftIcon		左侧图标
	 * @property {String | Object} leftIconStyle 左侧图标的样式
	 * @property {Boolean}			required		是否显示左边的必填星号，只作显示用，具体校验必填的逻辑，请在rules中配置 (默认 false )
	 *
	 * @example <uv-form-item label="姓名" prop="userInfo.name" borderBottom ref="item1"></uv-form-item>
	 */
	export default {
		name: 'uv-form-item',
		emits: ['click'],
		mixins: [mpMixin, mixin, props],
		data() {
			return {
				// 错误提示语
				message: '',
				parentData: {
					// 提示文本的位置
					labelPosition: 'left',
					// 提示文本对齐方式
					labelAlign: 'left',
					// 提示文本的样式
					labelStyle: {},
					// 提示文本的宽度
					labelWidth: 45,
					// 错误提示方式
					errorType: 'message'
				}
			}
		},
		created() {
			this.init()
		},
		methods: {
			init() {
				// 父组件的实例
				this.updateParentData()
				if (!this.parent) {
					this.$uv.error('uv-form-item需要结合uv-form组件使用')
				}
			},
			// 获取父组件的参数
			updateParentData() {
				// 此方法写在mixin中
				this.getParentData('uv-form');
			},
			// 移除uv-form-item的校验结果
			clearValidate() {
				this.message = null
			},
			// 清空当前的组件的校验结果，并重置为初始值
			resetField() {
				// 找到原始值
				const value = this.$uv.getProperty(this.parent.originalModel, this.prop)
				// 将uv-form的model的prop属性链还原原始值
				this.$uv.setProperty(this.parent.model, this.prop, value)
				// 移除校验结果
				this.message = null
			},
			// 点击组件
			clickHandler() {
				this.$emit('click')
			}
		}
	}
</script>

<style lang="scss" scoped>
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
	@import '@/uni_modules/uv-ui-tools/libs/css/color.scss';
	.uv-form-item {
		@include flex(column);
		font-size: 14px;
		color: $uv-main-color;
		&__body {
			@include flex;
			padding: 10px 0;
			&__left {
				@include flex;
				align-items: center;
				&__content {
					position: relative;
					@include flex;
					align-items: center;
					padding-right: 10rpx;
					flex: 1;
					&__icon {
						margin-right: 8rpx;
					}
					&__required {
						position: absolute;
						left: -9px;
						color: $uv-error;
						line-height: 20px;
						font-size: 20px;
						top: 3px;
					}
					&__label {
						@include flex;
						align-items: center;
						flex: 1;
						color: $uv-main-color;
						font-size: 15px;
					}
				}
			}
			&__right {
				flex: 1;
				&__content {
					@include flex;
					align-items: center;
					flex: 1;
					&__slot {
						flex: 1;
						/* #ifndef MP */
						@include flex;
						align-items: center;
						/* #endif */
					}
					&__icon {
						margin-left: 10rpx;
						color: $uv-light-color;
						font-size: 30rpx;
					}
				}
				&__message__box {
					height: 16px;
					line-height: 16px;
				}
				&__message {
					margin-top: -6px;
					line-height: 24px;
					font-size: 12px;
					color: $uv-error;
				}
			}
		}
	}
</style>
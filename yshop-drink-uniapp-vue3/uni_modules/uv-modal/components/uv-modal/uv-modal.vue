<template>
	<uv-popup
		ref="modalPopup"
		mode="center"
		:zoom="zoom"
    :zIndex="zIndex"
		:customStyle="{
			borderRadius: '6px', 
			overflow: 'hidden',
			marginTop: `-${$uv.addUnit(negativeTop)}`
		}"
		:closeOnClickOverlay="closeOnClickOverlay"
		:safeAreaInsetBottom="false"
		:duration="400"
		@change="popupChange"
	>
		<view
			class="uv-modal"
			:style="{
				width: $uv.addUnit(width),
			}"
		>
			<text
				class="uv-modal__title"
				v-if="title"
			>{{ title }}</text>
			<view
				class="uv-modal__content"
				:style="{
					paddingTop: `${title ? 12 : 25}px`
				}"
			>
				<slot>
					<text 
						class="uv-modal__content__text"
						:style="[
							{
								textAlign: align
							},
							nvueStyle,
							$uv.addStyle(textStyle)
						]"
					>{{ content }}</text>
				</slot>
			</view>
			<slot name="confirmButton">
				<uv-line></uv-line>
				<view
					class="uv-modal__button-group"
					:style="{
						flexDirection: buttonReverse ? 'row-reverse' : 'row'
					}"
				>
					<view
						class="uv-modal__button-group__wrapper uv-modal__button-group__wrapper--cancel"
						:hover-stay-time="150"
						hover-class="uv-modal__button-group__wrapper--hover"
						:class="[showCancelButton && !showConfirmButton && 'uv-modal__button-group__wrapper--only-cancel']"
						v-if="showCancelButton"
						@tap="cancelHandler"
					>
						<text
							class="uv-modal__button-group__wrapper__text"
							:style="{
								color: cancelColor
							}"
						>{{ cancelText }}</text>
					</view>
					<uv-line
						direction="column"
						v-if="showConfirmButton && showCancelButton"
					></uv-line>
					<view
						class="uv-modal__button-group__wrapper uv-modal__button-group__wrapper--confirm"
						:hover-stay-time="150"
						hover-class="uv-modal__button-group__wrapper--hover"
						:class="[!showCancelButton && showConfirmButton && 'uv-modal__button-group__wrapper--only-confirm']"
						v-if="showConfirmButton"
						@tap="confirmHandler"
					>
						<uv-loading-icon v-if="loading"></uv-loading-icon>
						<text
							v-else
							class="uv-modal__button-group__wrapper__text"
							:style="{
								color: confirmColor
							}"
						>{{ confirmText }}</text>
					</view>
				</view>
			</slot>
		</view>
	</uv-popup>
</template>

<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	import props from './props.js';
	/**
	 * Modal 模态框
	 * @description 弹出模态框，常用于消息提示、消息确认、在当前页面内完成特定的交互操作。
	 * @tutorial https://www.uvui.cn/components/modul.html
	 * @property {String}			title				标题内容
	 * @property {String}			content				模态框内容，如传入slot内容，则此参数无效
	 * @property {String}			confirmText			确认按钮的文字 （默认 '确认' ）
	 * @property {String}			cancelText			取消按钮的文字 （默认 '取消' ）
	 * @property {Boolean}			showConfirmButton	是否显示确认按钮 （默认 true ）
	 * @property {Boolean}			showCancelButton	是否显示取消按钮 （默认 false ）
	 * @property {String}			confirmColor		确认按钮的颜色 （默认 '#2979ff' ）
	 * @property {String}			cancelColor			取消按钮的颜色 （默认 '#606266' ）
	 * @property {Boolean}			buttonReverse		对调确认和取消的位置 （默认 false ）
	 * @property {Boolean}			zoom				是否开启缩放模式 （默认 true ）
	 * @property {Boolean}			asyncClose			是否异步关闭，只对确定按钮有效，见上方说明 （默认 false ）
	 * @property {Boolean}			closeOnClickOverlay	是否允许点击遮罩关闭该组件 （默认 true ）
	 * @property {String | Number}	negativeTop			往上偏移的值，给一个负的margin-top，往上偏移，避免和键盘重合的情况，单位任意，数值则默认为px单位 （默认 0 ）
	 * @property {String | Number}	width				modal宽度，不支持百分比，可以数值，px，rpx单位 （默认 '650rpx' ）
	 * @property {String} align 文本对齐方式 （默认'left'） 
	 * @property {String | Object} textStyle 文本扩展样式
	 * @event {Function} confirm	点击确认按钮时触发
	 * @event {Function} cancel		点击取消按钮时触发
	 * @event {Function} close		点击遮罩关闭出发，closeOnClickOverlay为true有效
	 * @example <uv-modal ref="modalPopup" title="title" content="content"></uv-modal>
	 */
	export default {
		name: 'uv-modal',
		mixins: [mpMixin, mixin, props],
		data() {
			return {
				loading: false
			}
		},
		computed: {
			nvueStyle() {
				const style = {};
				// 避免nvue中不能换行的问题
				// #ifdef APP-NVUE
				style.width = this.$uv.addUnit(this.$uv.getPx(this.width) - 50,'px');
				// #endif
				return style;
			}
		},
		methods: {
			open() {
				this.$refs.modalPopup.open();
				if (this.loading) this.loading = false;
			},
			close() {
				this.$refs.modalPopup.close();
			},
			popupChange(e) {
				if(!e.show) this.$emit('close');
			},
			// 点击确定按钮
			confirmHandler() {
				if(!this.loading) {
					this.$emit('confirm');
				}
				// 如果配置了异步关闭，将按钮值为loading状态
				if (this.asyncClose) {
					this.loading = true;
				} else {
					this.close();
				}
			},
			// 点击取消按钮
			cancelHandler() {
				this.$emit('cancel');
				this.close();
			},
			closeLoading() {
				this.loading = false;
			}
		}
	}
</script>

<style lang="scss" scoped>
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
	@import '@/uni_modules/uv-ui-tools/libs/css/color.scss';
	$uv-modal-border-radius: 6px;

	.uv-modal {
		width: 650rpx;
		border-radius: $uv-modal-border-radius;
		overflow: hidden;

		&__title {
			font-size: 16px;
			font-weight: bold;
			color: $uv-content-color;
			text-align: center;
			padding-top: 25px;
		}

		&__content {
			padding: 12px 25px 25px 25px;
			@include flex;
			justify-content: center;

			&__text {
				line-height: 48rpx;
				font-size: 15px;
				color: $uv-content-color;
				flex: 1;
			}
		}

		&__button-group {
			@include flex;
			height: 48px;

			&__wrapper {
				flex: 1;
				@include flex;
				justify-content: center;
				align-items: center;
				height: 48px;
				
				&--confirm,
				&--only-cancel {
					border-bottom-right-radius: $uv-modal-border-radius;
				}
				
				&--cancel,
				&--only-confirm {
					border-bottom-left-radius: $uv-modal-border-radius;
				}

				&--hover {
					background-color: $uv-bg-color;
				}

				&__text {
					color: $uv-content-color;
					font-size: 16px;
					text-align: center;
				}
			}
		}
	}
</style>

<template>
	<uv-popup
		ref="keyboardPopup"
		mode="bottom"
	  :overlay="overlay"
	  :closeOnClickOverlay="closeOnClickOverlay"
	  :safeAreaInsetBottom="safeAreaInsetBottom"
	  :zIndex="zIndex"
	  :customStyle="{ backgroundColor: 'rgb(214, 218, 220)' }"
		@change="popupChange"
	>
		<view class="uv-keyboard">
			<slot />
			<view
			  class="uv-keyboard__tooltip"
			  v-if="tooltip"
			>
				<view
				  hover-class="uv-hover-class"
				  :hover-stay-time="100"
				>
					<text
					  class="uv-keyboard__tooltip__item uv-keyboard__tooltip__cancel"
					  v-if="showCancel"
					  @tap="onCancel"
					>{{showCancel && cancelText}}</text>
				</view>
				<view>
					<text
					  v-if="showTips"
					  class="uv-keyboard__tooltip__item uv-keyboard__tooltip__tips"
					>{{tips ? tips : mode == 'number' ? '数字键盘' : mode == 'card' ? '身份证键盘' : '车牌号键盘'}}</text>
				</view>
				<view
				  hover-class="uv-hover-class"
				  :hover-stay-time="100"
				>
					<text
					  v-if="showConfirm"
					  @tap="onConfirm"
					  class="uv-keyboard__tooltip__item uv-keyboard__tooltip__submit"
					  hover-class="uv-hover-class"
					>{{showConfirm && confirmText}}</text>
				</view>
			</view>
			<template v-if="mode == 'number' || mode == 'card'">
				<uv-keyboard-number
				  :random="random"
				  @backspace="backspace"
				  @change="change"
				  :mode="mode"
				  :dotDisabled="dotDisabled"
				></uv-keyboard-number>
			</template>
			<template v-else>
				<uv-keyboard-car
					ref="uvKeyboardCarRef"
				  :random="random"
					:autoChange="autoChange"
					:disKeys="disKeys"
					:customabc="customabc"
				  @backspace="backspace"
				  @change="change"
					@changeCarInputMode="changeCarInputMode"
				>
					<slot name="abc"></slot>
				</uv-keyboard-car>
			</template>
		</view>
	</uv-popup>
</template>

<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	import props from './props.js';
	/**
	 * keyboard 键盘
	 * @description 此为uViw自定义的键盘面板，内含了数字键盘，车牌号键，身份证号键盘3中模式，都有可以打乱按键顺序的选项。
	 * @tutorial https://www.uvui.cn/components/keyboard.html
	 * @property {String}			mode				键盘类型，见官网基本使用的说明 （默认 'number' ）
	 * @property {Boolean}			dotDisabled			是否显示"."按键，只在mode=number时有效 （默认 false ）
	 * @property {Boolean}			tooltip				是否显示键盘顶部工具条 （默认 true ）
	 * @property {Boolean}			showTips			是否显示工具条中间的提示 （默认 true ）
	 * @property {String}			tips				工具条中间的提示文字，见上方基本使用的说明，如不需要，请传""空字符
	 * @property {Boolean}			showCancel			是否显示工具条左边的"取消"按钮 （默认 true ）
	 * @property {Boolean}			showConfirm			是否显示工具条右边的"完成"按钮（ 默认 true ）
	 * @property {Boolean}			random				是否打乱键盘按键的顺序 （默认 false ）
	 * @property {Boolean}			safeAreaInsetBottom	是否开启底部安全区适配 （默认 true ）
	 * @property {Boolean}			closeOnClickOverlay	是否允许点击遮罩收起键盘 （默认 true ）
	 * @property {Boolean}			show				控制键盘的弹出与收起（默认 false ）
	 * @property {Boolean}			overlay				是否显示遮罩 （默认 true ）
	 * @property {String | Number}	zIndex				弹出键盘的z-index值 （默认 1075 ）
	 * @property {String}			cancelText			取消按钮的文字 （默认 '取消' ）
	 * @property {String}			confirmText			确认按钮的文字 （默认 '确认' ）
	 * @property {Object}			customStyle			自定义样式，对象形式
	 * @event {Function} change 按键被点击(不包含退格键被点击)
	 * @event {Function} cancel 键盘顶部工具条左边的"取消"按钮被点击
	 * @event {Function} confirm 键盘顶部工具条右边的"完成"按钮被点击
	 * @event {Function} backspace 键盘退格键被点击
	 * @example <uv-keyboard mode="number" v-model="show"></uv-keyboard>
	 */
	export default {
		name: "uv-keyboard",
		mixins: [mpMixin, mixin, props],
		emits: ['close','change','confirm','cancel','backspace','changeCarInputMode'],
		methods: {
			open() {
				this.$refs.keyboardPopup.open();
			},
			close() {
				this.$refs.keyboardPopup.close();
			},
			popupChange(e) {
				if(!e.show) this.$emit('close');
			},
			change(e) {
				this.$emit('change', e);
			},
			// 输入完成
			onConfirm() {
				this.$emit('confirm');
				if(this.closeOnClickConfirm) this.close();
			},
			// 取消输入
			onCancel() {
				this.$emit('cancel');
				this.close();
			},
			// 退格键
			backspace() {
				this.$emit('backspace');
			},
			// car模式切换中文|英文方法
			changeCarInputMode(e) {
				this.$emit('changeCarInputMode',e);
			},
			changeCarMode() {
				this.$refs.uvKeyboardCarRef && this.$refs.uvKeyboardCarRef.changeCarInputMode();
			}
		}
	}
</script>

<style lang="scss" scoped>
	$show-hover: 1;
	@import '@/uni_modules/uv-ui-tools/libs/css/variable.scss';
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
	@import '@/uni_modules/uv-ui-tools/libs/css/color.scss';
	.uv-keyboard {

		&__tooltip {
			@include flex;
			justify-content: space-between;
			background-color: #FFFFFF;
			padding: 14px 12px;

			&__item {
				color: #333333;
				flex: 1;
				text-align: center;
				font-size: 15px;
			}

			&__submit {
				text-align: right;
				color: $uv-primary;
			}

			&__cancel {
				text-align: left;
				color: #888888;
			}

			&__tips {
				color: $uv-tips-color;
			}
		}
	}
</style>

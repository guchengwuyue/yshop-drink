<template>
	<view
		:class="['uv-toolbar',{'uv-border-bottom':showBorder}]"
		@touchmove.stop.prevent="noop"
		v-if="show"
	>
		<view
			class="uv-toolbar__cancel__wrapper"
			hover-class="uv-hover-class"
		>
			<text
				class="uv-toolbar__wrapper__cancel"
				@tap="cancel"
				:style="{
					color: cancelColor
				}"
			>{{ cancelText }}</text>
		</view>
		<text
			class="uv-toolbar__title uv-line-1"
			v-if="title"
		>{{ title }}</text>
		<view
			class="uv-toolbar__confirm__wrapper"
			hover-class="uv-hover-class"
		>
			<text
				class="uv-toolbar__wrapper__confirm"
				@tap="confirm"
				:style="{
				color: confirmColor
			}"
			>{{ confirmText }}</text>
		</view>
	</view>
</template>
<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	import props from './props.js';
	/**
	 * Toolbar 工具条
	 * @description 
	 * @tutorial https://www.uvui.cn/components/toolbar.html
	 * @property {Boolean}	show			是否展示工具条（默认 true ）
	 * @property {Boolean}	showBorder			是否展示工具条下方边框（默认 false ）
	 * @property {String}	cancelText		取消按钮的文字（默认 '取消' ）
	 * @property {String}	confirmText		确认按钮的文字（默认 '确认' ）
	 * @property {String}	cancelColor		取消按钮的颜色（默认 '#909193' ）
	 * @property {String}	confirmColor	确认按钮的颜色（默认 '#3c9cff' ）
	 * @property {String}	title	标题文字
	 * @event {Function} 
	 * @example 
	 */
	export default {
		name: 'uv-toolbar',
		emits: ['confirm', 'cancel'],
		mixins: [mpMixin, mixin, props],
		methods: {
			// 点击取消按钮
			cancel() {
				this.$emit('cancel')
			},
			// 点击确定按钮
			confirm() {
				this.$emit('confirm')
			}
		}
	}
</script>

<style lang="scss" scoped>
	$show-lines: 1;
	$show-hover: 1;
	$show-border: 1;
	$show-border-bottom: 1;
	@import '@/uni_modules/uv-ui-tools/libs/css/variable.scss';
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
	$uv-tips-color: #909193 !default;
	$uv-main-color: #303133 !default;
	$uv-primary: #3c9cff !default;
	.uv-toolbar {
		height: 42px;
		@include flex;
		justify-content: space-between;
		align-items: center;
		&__wrapper {
			&__cancel {
				color: $uv-tips-color;
				font-size: 15px;
				padding: 0 15px;
			}
		}
		&__title {
			color: $uv-main-color;
			padding: 0 60rpx;
			font-size: 16px;
			flex: 1;
			text-align: center;
		}
		&__wrapper {
			&__confirm {
				color: $uv-primary;
				font-size: 15px;
				padding: 0 15px;
			}
		}
	}
</style>
<template>
	<view 
		class="uv-divider"
		:style="[$uv.addStyle(customStyle)]"
		@tap="click"
		>
		<uv-line 
			:color="lineColor"
			:customStyle="leftLineStyle"
			:hairline="hairline"
			:dashed="dashed"></uv-line>
		<text 
			v-if="dot"
			class="uv-divider__dot"
		>●</text>
		<text 
			v-else-if="text"
			class="uv-divider__text"
			:style="[textStyle]"
		>{{text}}</text>
		<uv-line 
			:color="lineColor"
			:customStyle="rightLineStyle"
			:hairline="hairline"
			:dashed="dashed"
		></uv-line>
	</view>
</template>
<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	import props from './props.js';
	/**
	 * divider 分割线
	 * @description 区隔内容的分割线，一般用于页面底部"没有更多"的提示。
	 * @tutorial https://www.uvui.cn/components/divider.html
	 * @property {Boolean}			dashed			是否虚线 （默认 false ）
	 * @property {Boolean}			hairline		是否细线 （默认  true ）
	 * @property {Boolean}			dot				是否以点替代文字，优先于text字段起作用 （默认 false ）
	 * @property {String}			textPosition	内容文本的位置，left-左边，center-中间，right-右边 （默认 'center' ）
	 * @property {String | Number}	text			文本内容
	 * @property {String | Number}	textSize		文本大小 （默认 14）
	 * @property {String}			textColor		文本颜色 （默认 '#909399' ）
	 * @property {String}			lineColor		线条颜色 （默认 '#dcdfe6' ）
	 * @property {Object}			customStyle		定义需要用到的外部样式
	 *
	 * @event {Function}	click	divider组件被点击时触发
	 * @example <uv-divider :color="color">锦瑟无端五十弦</uv-divider>
	 */
	export default {
		name: 'uv-divider',
		mixins: [mpMixin, mixin, props],
		emits: ['click'],
		computed: {
			textStyle() {
				const style = {}
				style.fontSize = this.$uv.addUnit(this.textSize)
				style.color = this.textColor
				return style
			},
			// 左边线条的的样式
			leftLineStyle() {
				const style = {}
				// 如果是在左边，设置左边的宽度为固定值
				if (this.textPosition === 'left') {
					style.width = '80rpx'
				} else {
					style.flex = 1
				}
				return style
			},
			// 右边线条的的样式
			rightLineStyle() {
				const style = {}
				// 如果是在右边，设置右边的宽度为固定值
				if (this.textPosition === 'right') {
					style.width = '80rpx'
				} else {
					style.flex = 1
				}
				return style
			}
		},
		methods: {
			// divider组件被点击时触发
			click() {
				this.$emit('click');
			}
		}
	}
</script>
<style lang="scss" scoped>
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
	$uv-divider-margin: 15px 0 !default;
	$uv-divider-text-margin: 0 15px !default;
	$uv-divider-dot-font-size: 12px !default;
	$uv-divider-dot-margin: 0 12px !default;
	$uv-divider-dot-color: #c0c4cc !default;
	.uv-divider {
		@include flex;
		flex-direction: row;
		align-items: center;
		margin: $uv-divider-margin;
		&__text {
			margin: $uv-divider-text-margin;
		}
		&__dot {
			font-size: $uv-divider-dot-font-size;
			margin: $uv-divider-dot-margin;
			color: $uv-divider-dot-color;
		}
	}
</style>
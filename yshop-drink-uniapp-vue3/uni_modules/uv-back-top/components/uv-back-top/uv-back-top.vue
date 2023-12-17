<template>
	<uv-transition mode="fade" :customStyle="backTopStyle" :show="show">
		<slot>
			<view class="uv-back-top" :style="[contentStyle]" @click="backToTop">
				<uv-icon :name="icon" :custom-style="iconStyle"></uv-icon>
				<text v-if="text" class="uv-back-top__text">{{text}}</text>
			</view>
		</slot>
	</uv-transition>
</template>

<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	import props from './props.js';
	// #ifdef APP-NVUE
	const dom = weex.requireModule('dom')
	// #endif
	/**
	 * backTop 返回顶部
	 * @description 本组件一个用于长页面，滑动一定距离后，出现返回顶部按钮，方便快速返回顶部的场景。
	 * @tutorial https://www.uvui.cn/components/backTop.html
	 * @property {String}			mode  		返回顶部的形状，circle-圆形，square-方形 （默认 'circle' ）
	 * @property {String} 			icon 		自定义图标 （默认 'arrow-upward' ） 见官方文档示例
	 * @property {String} 			text 		提示文字 
	 * @property {String | Number}  duration	返回顶部滚动时间 （默认 100）
	 * @property {String | Number}  scrollTop	滚动距离 （默认 0 ）
	 * @property {String | Number}  top  		距离顶部多少距离显示，单位px （默认 400 ）
	 * @property {String | Number}  bottom  	返回顶部按钮到底部的距离，单位px （默认 100 ）
	 * @property {String | Number}  right  		返回顶部按钮到右边的距离，单位px （默认 20 ）
	 * @property {String | Number}  zIndex 		层级   （默认 9 ）
	 * @property {Object<Object>}  	iconStyle 	图标的样式，对象形式   （默认 {color: '#909399',fontSize: '19px'}）
	 * @property {Object}			customStyle	定义需要用到的外部样式
	 * 
	 * @example <uv-back-top :scrollTop="scrollTop"></uv-back-top>
	 */
	export default {
		name: 'uv-back-top',
		emits: ['click'],
		mixins: [mpMixin, mixin, props],
		computed: {
			backTopStyle() {
				// 动画组件样式
				const style = {
					bottom: this.$uv.addUnit(this.bottom),
					right: this.$uv.addUnit(this.right),
					width: '40px',
					height: '40px',
					position: 'fixed',
					zIndex: 10,
				}
				return style
			},
			show() {
				return this.$uv.getPx(this.scrollTop) > this.$uv.getPx(this.top)
			},
			contentStyle() {
				const style = {}
				let radius = 0
				// 是否圆形
				if (this.mode === 'circle') {
					radius = '100px'
				} else {
					radius = '4px'
				}
				// 为了兼容安卓nvue，只能这么分开写
				style.borderTopLeftRadius = radius
				style.borderTopRightRadius = radius
				style.borderBottomLeftRadius = radius
				style.borderBottomRightRadius = radius
				return this.$uv.deepMerge(style, this.$uv.addStyle(this.customStyle))
			}
		},
		methods: {
			backToTop() {
				// #ifdef APP-NVUE
				if (!this.$parent.$refs['uv-back-top']) {
					this.$uv.error(`nvue页面需要给页面最外层元素设置"ref='uv-back-top'`)
				}
				dom.scrollToElement(this.$parent.$refs['uv-back-top'], {
					offset: 0
				})
				// #endif

				// #ifndef APP-NVUE
				uni.pageScrollTo({
					scrollTop: 0,
					duration: this.duration
				});
				// #endif
				this.$emit('click')
			}
		}
	}
</script>

<style lang="scss" scoped>
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
	$uv-back-top-flex: 1 !default;
	$uv-back-top-height: 100% !default;
	$uv-back-top-background-color: #E1E1E1 !default;
	$uv-back-top-tips-font-size: 12px !default;
	.uv-back-top {
		@include flex;
		flex-direction: column;
		align-items: center;
		flex: $uv-back-top-flex;
		height: $uv-back-top-height;
		justify-content: center;
		background-color: $uv-back-top-background-color;
		&__tips {
			font-size: $uv-back-top-tips-font-size;
			transform: scale(0.8);
		}
	}
</style>
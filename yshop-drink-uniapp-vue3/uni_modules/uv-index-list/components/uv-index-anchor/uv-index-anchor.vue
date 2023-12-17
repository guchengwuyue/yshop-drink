<template>
	<!-- #ifdef APP-NVUE -->
	<header>
	<!-- #endif -->
	<view
	    class="uv-index-anchor"
		:ref="`uv-index-anchor-${text}`"
	    :style="{
			height: $uv.addUnit(height),
			backgroundColor: bgColor
		}"
	>
		<text
		    class="uv-index-anchor__text"
		    :style="{
				fontSize: $uv.addUnit(size),
				color: color
			}"
		>{{ text }}</text>
	</view>
	<!-- #ifdef APP-NVUE -->
	</header>
	<!-- #endif -->
</template>

<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	import props from './props.js';
	// #ifdef APP-NVUE
	const dom = uni.requireNativePlugin('dom')
	// #endif
	/**
	 * IndexAnchor 列表锚点
	 * @description 
	 * @tutorial https://www.uvui.cn/components/indexList.html
	 * @property {String | Number}	text	列表锚点文本内容
	 * @property {String}			color	列表锚点文字颜色 ( 默认 '#606266' )
	 * @property {String | Number}	size	列表锚点文字大小，单位默认px ( 默认 14 )
	 * @property {String}			bgColor	列表锚点背景颜色 ( 默认 '#dedede' )
	 * @property {String | Number}	height	列表锚点高度，单位默认px ( 默认 32 )
	 * @example <uv-index-anchor :text="indexList[index]"></uv-index-anchor>
	 */
	export default {
		name: 'uv-index-anchor',
		mixins: [mpMixin, mixin, props],
		data() {
			return {
			}
		},
		mounted() {
			this.init()
		},
		methods: {
			init() {
				// 此处会活动父组件实例，并赋值给实例的parent属性
				const indexList = this.$uv.$parent.call(this, 'uv-index-list')
				if (!indexList) { 
					return this.$uv.error('uv-index-anchor必须要搭配uv-index-list组件使用')
				}
				// 将当前实例放入到uv-index-list中
				indexList.anchors.push(this)
				const indexListItem = this.$uv.$parent.call(this, 'uv-index-item')
				// #ifndef APP-NVUE
				// 只有在非nvue下，uv-index-anchor才是嵌套在uv-index-item中的
				if (!indexListItem) {
					return this.$uv.error('uv-index-anchor必须要搭配uv-index-item组件使用')
				}
				// 设置uv-index-item的id为anchor的text标识符，因为非nvue下滚动列表需要依赖scroll-view滚动到元素的特性
				indexListItem.id = this.text.charCodeAt(0)
				// #endif
			}
		},
	}
</script>

<style lang="scss" scoped>
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';

	.uv-index-anchor {
		position: sticky;
		top: 0;
		@include flex;
		align-items: center;
		padding-left: 15px;
		z-index: 1;

		&__text {
			@include flex;
			align-items: center;
		}
	}
</style>

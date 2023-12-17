<template>
	<!-- #ifndef APP-NVUE -->
	<view 
		class="uv-list"
		:style="[$uv.addStyle(customStyle)]"
	>
		<view 
			v-if="border" 
			class="uv-list--border-top"
			:style="[{ 'background-color': borderColor }]"
		></view>
		<slot />
		<view 
			v-if="border" 
			class="uv-list--border-bottom"
			:style="[{ 'background-color': borderColor }]"
		></view>
	</view>
	<!-- #endif -->
	<!-- #ifdef APP-NVUE -->
	<list 
		:bounce="true" 
		:scrollable="true" 
		show-scrollbar 
		:render-reverse="false" 
		class="uv-list" 
		:class="{ 'uv-list--border': border }"
		:style="[
			{ 'border-top-color': borderColor, 'border-bottom-color':borderColor  },
			$uv.addStyle(customStyle)
		]"
		:enableBackToTop="false"
		:loadmoreoffset="15"
		@scroll="scroll"
		@loadmore="loadMore">
		<slot />
	</list>
	<!-- #endif -->
</template>

<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	/**
	 * List 列表
	 * @description 列表组件
	 * @tutorial https://www.uvui.cn/components/list.html
	 * @property {Boolean} border = [true|false] 是否显示边框
	 * @property {String} borderColor 边框颜色
	 * @property {String} direction 排版方向，默认row，列表里面使用其他组件  最好设置成column
	 */
	export default {
		name: 'uv-list',
		mixins: [mpMixin, mixin],
		'mp-weixin': {
			options: {
				multipleSlots: false
			}
		},
		props: {
			border: {
				type: Boolean,
				default: false
			},
			borderColor: {
				type: String,
				default: '#dadbde'
			},
			// 排版方向，默认row，列表里面使用其他组件  最好设置成column
			direction: {
				type: String,
				default: 'row'
			},
			// 内边距
			padding: {
				type: [String,Number],
				default: '20rpx 30rpx'
			}
		},
		created() {
			this.firstChildAppend = false;
		},
		computed: {
			parentData() {
				return [this.direction,this.padding];
			}
		},
		methods: {
			loadMore(e) {
				this.$emit('scrolltolower');
			},
			scroll(e) {
				this.$emit('scroll', e);
			}
		}
	};
</script>
<style lang="scss" scoped>
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
	@import '@/uni_modules/uv-ui-tools/libs/css/color.scss';
	
	.uv-list {
		position: relative;
		@include flex(column);
		background-color: #fff;
	}

	.uv-list--border {
		position: relative;
		/* #ifdef APP-NVUE */
		border-top-color: $uv-border-color;
		border-top-style: solid;
		border-top-width: 0.5px;
		border-bottom-color: $uv-border-color;
		border-bottom-style: solid;
		border-bottom-width: 0.5px;
		/* #endif */
		z-index: -1;
	}

	/* #ifndef APP-NVUE */

	.uv-list--border-top {
		position: absolute;
		top: 0;
		right: 0;
		left: 0;
		height: 1px;
		-webkit-transform: scaleY(0.5);
		transform: scaleY(0.5);
		background-color: $uv-border-color;
		z-index: 1;
	}

	.uv-list--border-bottom {
		position: absolute;
		bottom: 0;
		right: 0;
		left: 0;
		height: 1px;
		-webkit-transform: scaleY(0.5);
		transform: scaleY(0.5);
		background-color: $uv-border-color;
	}

	/* #endif */
</style>

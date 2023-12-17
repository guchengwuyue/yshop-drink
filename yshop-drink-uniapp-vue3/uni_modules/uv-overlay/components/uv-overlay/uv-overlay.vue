<template>
	<uv-transition
	  :show="show"
		mode="fade"
	  custom-class="uv-overlay"
	  :duration="duration"
	  :custom-style="overlayStyle"
	  @click="clickHandler"
		@touchmove.stop.prevent="clear"
	>
		<slot />
	</uv-transition>
</template>

<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	import props from './props.js';

	/**
	 * overlay 遮罩
	 * @description 创建一个遮罩层，用于强调特定的页面元素，并阻止用户对遮罩下层的内容进行操作，一般用于弹窗场景
	 * @tutorial https://www.uvui.cn/components/overlay.html
	 * @property {Boolean}			show		是否显示遮罩（默认 false ）
	 * @property {String | Number}	zIndex		zIndex 层级（默认 10070 ）
	 * @property {String | Number}	duration	动画时长，单位毫秒（默认 300 ）
	 * @property {String | Number}	opacity		不透明度值，当做rgba的第四个参数 （默认 0.5 ）
	 * @property {Object}			customStyle	定义需要用到的外部样式
	 * @event {Function} click 点击遮罩发送事件
	 * @example <uv-overlay :show="show" @click="show = false"></uv-overlay>
	 */
	export default {
		name: "uv-overlay",
		emits: ['click'],
		mixins: [mpMixin, mixin, props],
		watch: {
			show(newVal){
				// #ifdef H5
				if(newVal){
					document.querySelector('body').style.overflow = 'hidden';
				}else{
					document.querySelector('body').style.overflow = '';
				}
				// #endif
			}
		},
		computed: {
			overlayStyle() {
				const style = {
					position: 'fixed',
					top: 0,
					left: 0,
					right: 0,
					zIndex: this.zIndex,
					bottom: 0,
					'background-color': `rgba(0, 0, 0, ${this.opacity})`
				}
				return this.$uv.deepMerge(style, this.$uv.addStyle(this.customStyle))
			}
		},
		methods: {
			clickHandler() {
				this.$emit('click')
			},
			clear() {}
		}
	}
</script>
<style lang="scss" scoped>
/* #ifndef APP-NVUE */
$uv-overlay-top:0 !default;
$uv-overlay-left:0 !default;
$uv-overlay-width:100% !default;
$uv-overlay-height:100% !default;
$uv-overlay-background-color:rgba(0, 0, 0, .7) !default;
.uv-overlay {
	position: fixed;
	top:$uv-overlay-top;
	left:$uv-overlay-left;
	width: $uv-overlay-width;
	height:$uv-overlay-height;
	background-color:$uv-overlay-background-color;
}
/* #endif */
</style>

<template>
	<uv-transition
		v-if="show"
		:show="show"
		mode="fade"
		:duration="fade ? duration : 0"
		:cell-child="cellChild"
		:custom-style="wrapStyle"
	>
		<view
			class="uv-image"
			:class="[`uv-image--${elIndex}`]"
			@tap="onClick"
			:style="[wrapStyle, backgroundStyle]"
		>
			<image
				v-if="!isError && observeShow"
				:src="src"
				:mode="mode"
				@error="onErrorHandler"
				@load="onLoadHandler"
				:show-menuv-by-longpress="showMenuByLongpress"
				:lazy-load="lazyLoad"
				class="uv-image__image"
				:style="[imageStyle]"
			></image>
			<view
				v-if="showLoading && loading"
				class="uv-image__loading"
				:style="{
					borderRadius: shape == 'circle' ? '50%' : $uv.addUnit(radius),
					backgroundColor: bgColor,
					width: $uv.addUnit(width),
					height: $uv.addUnit(height)
				}"
			>
				<slot name="loading">
					<uv-icon
						:name="loadingIcon"
						:width="width"
						:height="height"
					></uv-icon>
				</slot>
			</view>
			<view
				v-if="showError && isError && !loading"
				class="uv-image__error"
				:style="{
					borderRadius: shape == 'circle' ? '50%' : $uv.addUnit(radius),
					width: $uv.addUnit(width),
					height: $uv.addUnit(height)
				}"
			>
				<slot name="error">
					<uv-icon
						:name="errorIcon"
						:width="width"
						:height="height"
					></uv-icon>
				</slot>
			</view>
		</view>
	</uv-transition>
</template>

<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	import props from './props.js';
	/**
	 * Image 图片
	 * @description 此组件为uni-app的image组件的加强版，在继承了原有功能外，还支持淡入动画、加载中、加载失败提示、圆角值和形状等。
	 * @tutorial https://www.uvui.cn/components/image.html
	 * @property {String}			src 				图片地址
	 * @property {String}			mode 				裁剪模式，见官网说明 （默认 'aspectFill' ）
	 * @property {String | Number}	width 				宽度，单位任意，如果为数值，则为px单位 （默认 '300' ）
	 * @property {String | Number}	height 				高度，单位任意，如果为数值，则为px单位 （默认 '225' ）
	 * @property {String}			shape 				图片形状，circle-圆形，square-方形 （默认 'square' ）
	 * @property {String | Number}	radius		 		圆角值，单位任意，如果为数值，则为px单位 （默认 0 ）
	 * @property {Boolean}			lazyLoad			是否懒加载，仅微信小程序、App、百度小程序、字节跳动小程序有效 （默认 true ）
	 * @property {Boolean}			showMenuByLongpress	是否开启长按图片显示识别小程序码菜单，仅微信小程序有效 （默认 true ）
	 * @property {String}			loadingIcon 		加载中的图标，或者小图片 （默认 'photo' ）
	 * @property {String}			errorIcon 			加载失败的图标，或者小图片 （默认 'error-circle' ）
	 * @property {Boolean}			showLoading 		是否显示加载中的图标或者自定义的slot （默认 true ）
	 * @property {Boolean}			showError 			是否显示加载错误的图标或者自定义的slot （默认 true ）
	 * @property {Boolean}			fade 				是否需要淡入效果 （默认 true ）
	 * @property {Boolean}			webp 				只支持网络资源，只对微信小程序有效 （默认 false ）
	 * @property {String | Number}	duration 			搭配fade参数的过渡时间，单位ms （默认 500 ）
	 * @property {String}			bgColor 			背景颜色，用于深色页面加载图片时，为了和背景色融合  (默认 '#f3f4f6' )
	 * @property {Object}			customStyle  		定义需要用到的外部样式
	 * @event {Function}	click	点击图片时触发
	 * @event {Function}	error	图片加载失败时触发
	 * @event {Function} load 图片加载成功时触发
	 * @example <uv-image width="100%" height="300px" :src="src"></uv-image>
	 */
	export default {
		name: 'uv-image',
		emits: ['click','load','error'],
		mixins: [mpMixin, mixin, props],
		data() {
			return {
				// 图片是否加载错误，如果是，则显示错误占位图
				isError: false,
				// 初始化组件时，默认为加载中状态
				loading: true,
				// 图片加载完成时，去掉背景颜色，因为如果是png图片，就会显示灰色的背景
				backgroundStyle: {},
				// 用于fade模式的控制组件显示与否
				show: false,
				// 是否开启图片出现在可视范围进行加载（另一种懒加载）
				observeShow: !this.observeLazyLoad,
				elIndex: '',
				// 因为props的值无法修改，故需要一个中间值
				imgWidth: this.width,
				// 因为props的值无法修改，故需要一个中间值
				imgHeight: this.height,
				thresholdValue: 50
			};
		},
		watch: {
			src: {
				immediate: true,
				handler(n) {
					if (!n) {
						// 如果传入null或者''，或者false，或者undefined，标记为错误状态
						this.isError = true
					} else {
						this.isError = false;
						this.loading = true;
					}
				}
			},
			width(newVal){
				// 这样做的目的是避免在更新时候，某些平台动画会恢复关闭状态
				this.show = false;
				this.$uv.sleep(2).then(res=>{
					this.show = true;
				});
				this.imgWidth = newVal;
			},
			height(newVal){
				// 这样做的目的是避免在更新时候，某些平台动画会恢复关闭状态
				this.show = false;
				this.$uv.sleep(2).then(res=>{
					this.show = true;
				});
				this.imgHeight = newVal;
			}
		},
		computed: {
			wrapStyle() {
				let style = {};
				// 通过调用addUnit()方法，如果有单位，如百分比，px单位等，直接返回，如果是纯粹的数值，则加上rpx单位
				if(this.mode !== 'heightFix') {
					style.width = this.$uv.addUnit(this.imgWidth);
				}
				if(this.mode !== 'widthFix') {
					style.height = this.$uv.addUnit(this.imgHeight);
				}
				// 如果是显示圆形，设置一个很多的半径值即可
				style.borderRadius = this.shape == 'circle' ? '10000px' : this.$uv.addUnit(this.radius)
				// 如果设置圆角，必须要有hidden，否则可能圆角无效
				style.overflow = this.radius > 0 ? 'hidden' : 'visible'
				return this.$uv.deepMerge(style, this.$uv.addStyle(this.customStyle));
			},
			imageStyle() {
				let style = {};
				style.borderRadius = this.shape == 'circle' ? '10000px' : this.$uv.addUnit(this.radius);
				// #ifdef APP-NVUE
				style.width = this.$uv.addUnit(this.imgWidth);
				style.height = this.$uv.addUnit(this.imgHeight);
				// #endif
				return style;
			}
		},
		created() {
			this.elIndex = this.$uv.guid();
			this.observer = {}
			this.observerName = 'lazyLoadContentObserver'
		},
		mounted() {
			this.show = true;
			this.$nextTick(()=>{
				if(this.observeLazyLoad) this.observerFn();
			})
		},
		methods: {
			// 点击图片
			onClick() {
				this.$emit('click')
			},
			// 图片加载失败
			onErrorHandler(err) {
				this.loading = false
				this.isError = true
				this.$emit('error', err)
			},
			// 图片加载完成，标记loading结束
			onLoadHandler(event) {
				if(this.mode == 'widthFix') this.imgHeight = 'auto'
				if(this.mode == 'heightFix') this.imgWidth = 'auto'
				this.loading = false
				this.isError = false
				this.$emit('load', event)
				this.removeBgColor()
			},
			// 移除图片的背景色
			removeBgColor() {
				// 淡入动画过渡完成后，将背景设置为透明色，否则png图片会看到灰色的背景
				this.backgroundStyle = {
					backgroundColor: 'transparent'
				};
			},
			// 观察图片是否在可见视口
			observerFn(){
				// 在需要用到懒加载的页面，在触发底部的时候触发tOnLazyLoadReachBottom事件，保证所有图片进行加载
				this.$nextTick(() => {
					uni.$once('onLazyLoadReachBottom', () => {
						if (!this.observeShow) this.observeShow = true
					})
				})
				setTimeout(() => {
					// #ifndef APP-NVUE
					this.disconnectObserver(this.observerName)
					const contentObserver = uni.createIntersectionObserver(this)
					contentObserver.relativeToViewport({
						bottom: this.thresholdValue
					}).observe(`.uv-image--${this.elIndex}`, (res) => {
						if (res.intersectionRatio > 0) {
							// 懒加载状态改变
							this.observeShow = true
							// 如果图片已经加载，去掉监听，减少性能消耗
							this.disconnectObserver(this.observerName)
						}
					})
					this[this.observerName] = contentObserver
					// #endif
					// #ifdef APP-NVUE
					this.observeShow = true;
					// #endif
				}, 50)
			},
			disconnectObserver(observerName) {
				const observer = this[observerName]
				observer && observer.disconnect()
			}
		}
	};
</script>

<style lang="scss" scoped>
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
	@import '@/uni_modules/uv-ui-tools/libs/css/color.scss';
	$uv-image-error-top:0px !default;
	$uv-image-error-left:0px !default;
	$uv-image-error-width:100% !default;
	$uv-image-error-hight:100% !default;
	$uv-image-error-background-color:$uv-bg-color !default;
	$uv-image-error-color:$uv-tips-color !default;
	$uv-image-error-font-size: 46rpx !default;

	.uv-image {
		position: relative;
		transition: opacity 0.5s ease-in-out;

		&__image {
			width: 100%;
			height: 100%;
		}

		&__loading,
		&__error {
			position: absolute;
			top: $uv-image-error-top;
			left: $uv-image-error-left;
			width: $uv-image-error-width;
			height: $uv-image-error-hight;
			@include flex;
			align-items: center;
			justify-content: center;
			background-color: $uv-image-error-background-color;
			color: $uv-image-error-color;
			font-size: $uv-image-error-font-size;
		}
	}
</style>

<template>
	<view class="uqrcode"
		:class="{ 'uqrcode-hide': hide }"
		:style="{ width: `${templateOptions.width}px`, height: `${templateOptions.height}px` }">
		<view class="uqrcode-canvas-wrapper">
			<!-- 画布 -->
			<!-- #ifndef APP-NVUE -->
			<canvas class="uqrcode-canvas"
				:id="canvasId"
				:canvas-id="canvasId"
				:type="canvasType"
				:style="{
          width: `${templateOptions.canvasWidth}px`,
          height: `${templateOptions.canvasHeight}px`,
          transform: templateOptions.canvasTransform
        }"
				v-if="templateOptions.canvasDisplay"
				@click="onClick"></canvas>
			<!-- #endif -->

			<!-- nvue用gcanvas -->
			<!-- #ifdef APP-NVUE -->
			<gcanvas class="uqrcode-canvas"
				ref="gcanvas"
				:style="{
          width: `${templateOptions.canvasWidth}px`,
          height: `${templateOptions.canvasHeight}px`
        }"
				v-if="templateOptions.canvasDisplay"
				@click="onClick"></gcanvas>
			<!-- #endif -->
		</view>

		<!-- 加载效果 -->
		<view class="uqrcode-makeing"
			v-if="loading === undefined || !loading ? makeing : loading">
			<slot name="loading">
				<image class="uqrcode-makeing-image"
					:style="{ width: `${templateOptions.size / 4}px`, height: `${templateOptions.size / 4}px` }"
					src="data:image/gif;base64,R0lGODlhAAEAAfIEAOHh4SSsWuDg4N3d3f///wAAAAAAAAAAACH/C05FVFNDQVBFMi4wAwEAAAAh/wtYTVAgRGF0YVhNUDw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDYuMC1jMDAyIDc5LjE2NDQ4OCwgMjAyMC8wNy8xMC0yMjowNjo1MyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIDIyLjAgKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjAyODhGMzM4RDEwMTExRUM4MDhCRkVBQkE2QUZDQzkwIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjAyODhGMzM5RDEwMTExRUM4MDhCRkVBQkE2QUZDQzkwIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6MDI4OEYzMzZEMTAxMTFFQzgwOEJGRUFCQTZBRkNDOTAiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6MDI4OEYzMzdEMTAxMTFFQzgwOEJGRUFCQTZBRkNDOTAiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz4B//79/Pv6+fj39vX08/Lx8O/u7ezr6uno5+bl5OPi4eDf3t3c29rZ2NfW1dTT0tHQ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">
				</image>
			</slot>
		</view>

		<!-- 错误处理 -->
		<view class="uqrcode-error"
			v-if="isError"
			@click="onClick">
			<slot name="error"
				:error="error">
				<text class="uqrcode-error-message">{{ error.errMsg }}</text>
			</slot>
		</view>

		<!-- H5保存提示 -->
		<!-- #ifdef H5 -->
		<view class="uqrcode-h5-save"
			v-if="isH5Save">
			<slot name="h5save"
				:tempFilePath="tempFilePath">
				<image class="uqrcode-h5-save-image"
					:src="tempFilePath"></image>
				<text class="uqrcode-h5-save-text">{{ h5SaveIsDownload ? '若保存失败，' : '' }}请长按二维码进行保存</text>
			</slot>
			<view class="uqrcode-h5-save-close"
				@click.stop="isH5Save = false">
				<view class="uqrcode-h5-save-close-before"></view>
				<view class="uqrcode-h5-save-close-after"></view>
			</view>
		</view>
		<!-- #endif -->
	</view>
</template>

<script>
	import props from './props.js';
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	// #ifdef VUE3
	import { toRaw } from 'vue';
	// #endif
	/* 引入uvQRCode核心js */
	import UQRCode from './qrcode';
	/* 引入nvue所需模块 */
	// #ifdef APP-NVUE
	import { enable, WeexBridge } from './gcanvas';
	const modal = weex.requireModule('modal');
	// #endif
	/* 引入队列 */
	import { queueDraw, queueLoadImage } from './queue';
	/* 引入缓存图片 */
	import { cacheImageList } from './cache';
	let instance = null;
	/**
	 * qrcode 二维码
	 * @description 二维码生成插件，可扩展性高，它支持自定义渲染二维码，可通过uQRCode API得到二维码绘制关键信息后，使用canvas、svg或js操作dom的方式绘制二维码图案。还可自定义二维码样式，如随机颜色、圆点、方块、块与块之间的间距等。
	 * @tutorial https://www.uvui.cn/components/qrcode.html
	 * @property {String}	 value	 二维码内容 (start为true时必填 )
	 * @property {Object}	 options  二维码配置选项 (data|size|margin...)
	 * @property {String}	 fileType  导出的文件类型  (jpg | png)  
	 * @property {String}	 start  是否初始化组件后就开始生成 (默认 true)
	 * @property {String}	 auto  是否数据发生改变自动重绘 (默认 false)
	 * @property {String}	 hide  隐藏组件。如果只需要导出二维码作为图片使用，可设置为true，不能在组件或组件父级元素设置v-if="false"、v-show="false"、style="display:none;"等实现隐藏效果，这样会导致导出二维码空白  (默认 false)
	 * @property {String}	 type  canvas组件类型。微信小程序默认2d (默认 undefined)
	 * @property {String}	 queue  队列绘制 (默认 false)
	 * @property {String}	 isQueueLoadImage  是否队列加载图片，选择true将通过队列缓存所需要加载的图片。优点是加载重复资源可减少资源请求次数，节省网络资源，缺点是会转化为同步请求，资源不重复且多的情况下，等待时间会更久。总之，请求重复资源较多则选择true，请求不重复资源较多则选择false (默认 false)
	 * @property {String}	 loading  loading态 (默认 false)
	 * @property {String}	 h5SaveIsDownload  H5保存即自动下载（在支持的环境下），默认false为仅弹层提示用户需要长按图片保存，不会自动下载 (默认 false)
	 * @property {String}	 h5DownloadName  H5下载名称 
	 * @property {String}	 h5SaveTip  H5保存二维码时候是否显示提示
	 * @example <uv-qrcode ref="uvqrcode" size="400rpx" canvas-id="qrcode" value="https://www.uvui.cn"></uv-qrcode>
	 */
	export default {
		name: 'uv-qrcode',
		mixins: [mpMixin,mixin,props],
		emits: ['click','change','complete'],
		data() {
			return {
				canvasId: "",
				canvas: undefined,
				canvasType: undefined,
				canvasContext: undefined,
				makeDelegate: undefined,
				drawDelegate: undefined,
				toTempFilePathDelegate: undefined,
				makeExecuted: false,
				makeing: false,
				drawing: false,
				isError: false,
				error: undefined,
				isH5Save: false,
				tempFilePath: '',
				templateOptions: {
					size: 0,
					width: 0, // 组件宽度
					height: 0,
					canvasWidth: 0, // canvas宽度
					canvasHeight: 0,
					canvasTransform: '',
					canvasDisplay: false
				},
				uqrcodeOptions: {
					data: ''
				},
				plugins: [],
				makeingPattern: [
					[
						[true, true, true, false, false, false, false, true, true, true],
						[true, true, true, false, false, false, false, true, true, true],
						[true, true, true, false, false, false, false, true, true, true],
						[true, true, true, false, false, false, false, true, true, true],
						[true, true, true, false, false, false, false, true, true, true],
						[true, true, true, false, false, false, false, true, true, true],
						[true, true, true, false, false, false, false, true, true, true],
						[true, true, true, true, true, true, true, true, true, true],
						[true, true, true, true, true, true, true, true, true, true],
						[true, true, true, true, true, true, true, true, true, true]
					],
					[
						[true, true, true, true, true, true, true, true, true, true],
						[true, true, true, true, true, true, true, true, true, true],
						[true, true, true, true, true, true, true, true, true, true],
						[true, true, true, false, false, false, false, true, true, true],
						[true, true, true, false, false, false, false, true, true, true],
						[true, true, true, false, false, false, false, true, true, true],
						[true, true, true, false, false, false, false, false, false, false],
						[true, true, true, true, true, true, false, true, true, true],
						[true, true, true, true, true, true, false, true, true, true],
						[true, true, true, true, true, true, false, true, true, true]
					],
					[
						[true, true, true, true, true, true, true, true, true, true],
						[true, true, true, true, true, true, true, true, true, true],
						[true, true, true, true, true, true, true, true, true, true],
						[true, true, true, false, false, false, false, true, true, true],
						[true, true, true, false, false, false, false, true, true, true],
						[true, true, true, true, true, true, true, false, false, false],
						[true, true, true, true, true, true, true, false, false, false],
						[true, true, true, true, true, true, true, false, false, false],
						[true, true, true, false, false, false, false, true, true, true],
						[true, true, true, false, false, false, false, true, true, true]
					],
					[
						[true, true, true, true, true, true, true, true, true, true],
						[true, true, true, true, true, true, true, true, true, true],
						[true, true, true, true, true, true, true, true, true, true],
						[true, true, true, false, false, false, false, false, false, false],
						[true, true, true, false, false, false, false, false, false, false],
						[true, true, true, false, false, false, false, false, false, false],
						[true, true, true, false, false, false, false, false, false, false],
						[true, true, true, true, true, true, true, true, true, true],
						[true, true, true, true, true, true, true, true, true, true],
						[true, true, true, true, true, true, true, true, true, true]
					]
				]
			};
		},
		watch: {
			type: {
				handler(val) {
					const types = ['2d'];
					if (types.includes(val)) {
						this.canvasType = val;
					} else {
						this.canvasType = undefined;
					}
				},
				immediate: true
			},
			value: {
				handler() {
					if (this.auto) {
						this.remake();
					}
				}
			},
			size: {
				handler() {
					if (this.auto) {
						this.remake();
					}
				}
			},
			options: {
				handler() {
					if (this.auto) {
						this.remake();
					}
				},
				deep: true
			},
			makeing: {
				handler(val) {
					if (!val) {
						if (typeof this.toTempFilePathDelegate === 'function') {
							this.toTempFilePathDelegate();
						}
					}
				}
			}
		},
		created() {
			this.canvasId = this.$uv.guid();
		},
		mounted() {
			this.templateOptions.size = this.$uv.getPx(this.size);
			this.templateOptions.width = this.templateOptions.size;
			this.templateOptions.height = this.templateOptions.size;
			this.templateOptions.canvasWidth = this.templateOptions.size;
			this.templateOptions.canvasHeight = this.templateOptions.size;
			if (this.canvasType == '2d') {
				// #ifndef MP-WEIXIN
				this.templateOptions.canvasTransform = `scale(${this.templateOptions.size / this.templateOptions.canvasWidth}, ${this.templateOptions.size /
        this.templateOptions.canvasHeight})`;
				// #endif
			} else {
				this.templateOptions.canvasTransform = `scale(${this.templateOptions.size / this.templateOptions.canvasWidth}, ${this.templateOptions.size /
        this.templateOptions.canvasHeight})`;
			}
			if (this.start) {
				this.make();
			}
		},
		methods: {
			/**
			 * 获取模板选项
			 */
			getTemplateOptions() {
				var size = this.$uv.getPx(this.size);
				return deepReplace(this.templateOptions, {
					size,
					width: size,
					height: size
				});
			},
			/**
			 * 获取插件选项
			 */
			getUqrcodeOptions() {
				return deepReplace(this.options, {
					data: String(this.value),
					size: Number(this.templateOptions.size)
				});
			},
			/**
			 * 重置画布
			 */
			resetCanvas(callback) {
				this.templateOptions.canvasDisplay = false;
				this.$nextTick(() => {
					this.templateOptions.canvasDisplay = true;
					this.$nextTick(() => {
						callback && callback();
					});
				});
			},
			/**
			 * 绘制二维码
			 */
			async draw(callback = {}, isDrawDelegate = false) {
				if (typeof callback.success != 'function') {
					callback.success = () => {};
				}
				if (typeof callback.fail != 'function') {
					callback.fail = () => {};
				}
				if (typeof callback.complete != 'function') {
					callback.complete = () => {};
				}

				if (this.drawing) {
					if (!isDrawDelegate) {
						this.drawDelegate = () => {
							this.draw(callback, true);
						};
						return;
					}
				} else {
					this.drawing = true;
				}

				if (!this.canvasId) {
					console.error('[uQRCode]: canvasId must be set!');
					this.isError = true;
					this.drawing = false;
					callback.fail({
						errMsg: '[uQRCode]: canvasId must be set!'
					});
					callback.complete({
						errMsg: '[uQRCode]: canvasId must be set!'
					});
					return;
				}
				if (!this.value) {
					console.error('[uQRCode]: value must be set!');
					this.isError = true;
					this.drawing = false;
					callback.fail({
						errMsg: '[uQRCode]: value must be set!'
					});
					callback.complete({
						errMsg: '[uQRCode]: value must be set!'
					});
					return;
				}

				/* 组件数据 */
				this.templateOptions = this.getTemplateOptions();
				/* uQRCode选项 */
				this.uqrcodeOptions = this.getUqrcodeOptions();
				/* 纠错等级兼容字母写法 */
				if (typeof this.uqrcodeOptions.errorCorrectLevel === 'string') {
					this.uqrcodeOptions.errorCorrectLevel = UQRCode.errorCorrectLevel[this.uqrcodeOptions.errorCorrectLevel];
				}
				/* nvue不支持动态修改gcanvas尺寸，除nvue外，默认使用useDynamicSize */
				// #ifndef APP-NVUE
				if (typeof this.options.useDynamicSize === 'undefined') {
					this.uqrcodeOptions.useDynamicSize = true;
				}
				// #endif
				// #ifdef APP-NVUE
				if (typeof this.options.useDynamicSize === 'undefined') {
					this.uqrcodeOptions.useDynamicSize = false;
				}
				// if (typeof this.options.drawReserve === 'undefined') {
				//   this.uqrcodeOptions.drawReserve = true;
				// }
				// #endif

				/* 获取uQRCode实例 */
				const qr = instance = new UQRCode();
				/* 注册扩展 */
				this.plugins.forEach(p => qr.register(p.plugin));
				/* 设置uQRCode选项 */
				qr.setOptions(this.uqrcodeOptions);
				/* 调用制作二维码方法 */
				qr.make();

				/* 获取canvas上下文 */
				let canvasContext = null;
				// #ifndef APP-NVUE
				if (this.canvasType === '2d') {
					// #ifdef MP-WEIXIN
					/* 微信小程序获取canvas2d上下文方式 */
					const canvas = (this.canvas = await new Promise(resolve => {
						uni
							.createSelectorQuery()
							.in(this) // 在组件内使用需要
							.select(`#${this.canvasId}`)
							.fields({
								node: true,
								size: true
							})
							.exec(res => {
								resolve(res[0].node);
							});
					}));
					canvasContext = this.canvasContext = canvas.getContext('2d');
					/* 2d的组件设置宽高与实际canvas绘制宽高不是一个，打个比方，组件size=200，canvas.width设置为100，那么绘制出来就是100=200，组件size=400，canvas.width设置为800，绘制大小还是800=400，所以无需理会下方返回的dynamicSize是多少，按dpr重新赋值给canvas即可 */
					this.templateOptions.canvasWidth = qr.size;
					this.templateOptions.canvasHeight = qr.size;
					this.templateOptions.canvasTransform = '';
					/* 使用dynamicSize+scale，可以解决小块间出现白线问题，dpr可以解决模糊问题 */
					const dpr = uni.getSystemInfoSync().pixelRatio;
					canvas.width = qr.dynamicSize * dpr;
					canvas.height = qr.dynamicSize * dpr;
					canvasContext.scale(dpr, dpr);
					/* 微信小程序获取图像方式 */
					qr.loadImage = this.getLoadImage(function(src) {
						/* 小程序下获取网络图片信息需先配置download域名白名单才能生效 */
						return new Promise((resolve, reject) => {
							const img = canvas.createImage();
							img.src = src;
							img.onload = () => {
								resolve(img);
							};
							img.onerror = err => {
								reject(err);
							};
						});
					});
					// #endif
					// #ifndef MP-WEIXIN
					/* 非微信小程序不支持2d，切换回uniapp获取canvas上下文方式 */
					canvasContext = this.canvasContext = uni.createCanvasContext(this.canvasId, this);
					/* 使用dynamicSize，可以解决小块间出现白线问题，再通过scale缩放至size，使其达到所设尺寸 */
					this.templateOptions.canvasWidth = qr.dynamicSize;
					this.templateOptions.canvasHeight = qr.dynamicSize;
					this.templateOptions.canvasTransform = `scale(${this.templateOptions.size / this.templateOptions.canvasWidth}, ${this.templateOptions.size /
          this.templateOptions.canvasHeight})`;
					/* uniapp获取图像方式 */
					qr.loadImage = this.getLoadImage(function(src) {
						return new Promise((resolve, reject) => {
							if (src.startsWith('http')) {
								uni.getImageInfo({
									src,
									success: res => {
										resolve(res.path);
									},
									fail: err => {
										reject(err);
									}
								});
							} else {
								if (src.startsWith('.')) {
									console.error('[uQRCode]: 本地图片路径仅支持绝对路径！');
									throw new Error('[uQRCode]: local image path only supports absolute path!');
								} else {
									resolve(src);
								}
							}
						});
					});
					// #endif
				} else {
					/* uniapp获取canvas上下文方式 */
					canvasContext = this.canvasContext = uni.createCanvasContext(this.canvasId, this);
					/* 使用dynamicSize，可以解决小块间出现白线问题，再通过scale缩放至size，使其达到所设尺寸 */
					this.templateOptions.canvasWidth = qr.dynamicSize;
					this.templateOptions.canvasHeight = qr.dynamicSize;
					this.templateOptions.canvasTransform = `scale(${this.templateOptions.size / this.templateOptions.canvasWidth}, ${this.templateOptions.size /
          this.templateOptions.canvasHeight})`;
					/* uniapp获取图像方式 */
					qr.loadImage = this.getLoadImage(function(src) {
						return new Promise((resolve, reject) => {
							/* getImageInfo在微信小程序的bug：本地路径返回路径会把开头的/或../移除，导致路径错误，解决方法：限制只能使用绝对路径 */
							if (src.startsWith('http')) {
								uni.getImageInfo({
									src,
									success: res => {
										resolve(res.path);
									},
									fail: err => {
										reject(err);
									}
								});
							} else {
								if (src.startsWith('.')) {
									console.error('[uQRCode]: 本地图片路径仅支持绝对路径！');
									throw new Error('[uQRCode]: local image path only supports absolute path!');
								} else {
									resolve(src);
								}
							}
						});
					});
				}
				// #endif
				// #ifdef APP-NVUE
				/* NVue获取canvas上下文方式 */
				const gcanvas = this.$refs['gcanvas'];
				const canvas = enable(gcanvas, {
					bridge: WeexBridge
				});
				canvasContext = this.canvasContext = canvas.getContext('2d');
				/* NVue获取图像方式 */
				qr.loadImage = this.getLoadImage(function(src) {
					return new Promise((resolve, reject) => {
						/* getImageInfo在nvue的bug：获取同一个路径的图片信息，同一时间第一次获取成功，后续失败，猜测是写入本地时产生文件写入冲突，所以没有返回，特别是对于网络资源 --- 已实现队列绘制，已解决此问题 */
						if (src.startsWith('.')) {
							console.error('[uQRCode]: 本地图片路径仅支持绝对路径！');
							throw new Error('[uQRCode]: local image path only supports absolute path!');
						} else {
							uni.getImageInfo({
								src,
								success: res => {
									resolve(res.path);
								},
								fail: err => {
									reject(err);
								}
							});
						}
					});
				});
				// #endif

				/* 设置uQRCode实例的canvas上下文 */
				qr.canvasContext = canvasContext;
				/* 延时等待页面重新绘制完毕 */
				setTimeout(() => {
					/* 从插件获取具体要调用哪一个扩展函数 */
					var plugin = this.plugins.find(p => p.name == qr.style);
					var drawCanvasName = plugin ? plugin.drawCanvas : 'drawCanvas';
					/* 虽然qr[drawCanvasName]是直接返回Promise的，但由于js内部this指向问题，故不能直接exec(qr[drawCanvasName])此方式执行，需要改成exec(() => qr[drawCanvasName]())才能正确获取this */
					var drawCanvas;
					if (this.queue) {
						drawCanvas = () => queueDraw.exec(() => qr[drawCanvasName]());
						// drawCanvas = () => queueDraw.exec(() => new Promise((resolve, reject) => {
						//   setTimeout(() => {
						//     qr[drawCanvasName]().then(resolve).catch(reject);
						//   }, 1000);
						// }));
					} else {
						drawCanvas = () => qr[drawCanvasName]();
					}
					/* 调用绘制方法将二维码图案绘制到canvas上 */
					drawCanvas()
						.then(() => {
							if (this.drawDelegate) {
								/* 高频重绘纠正 */
								let delegate = this.drawDelegate;
								this.drawDelegate = undefined;
								delegate();
							} else {
								this.drawing = false;
								callback.success();
							}
						})
						.catch(err => {
							console.log(err);
							if (this.drawDelegate) {
								/* 高频重绘纠正 */
								let delegate = this.drawDelegate;
								this.drawDelegate = undefined;
								delegate();
							} else {
								this.drawing = false;
								this.isError = true;
								callback.fail(err);
							}
						})
						.finally(() => {
							callback.complete();
						});
				}, 300);
			},
			/**
			 * 生成二维码
			 */
			make(callback = {}) {
				this.makeExecuted = true;
				this.makeing = true;
				this.isError = false;

				if (typeof callback.success != 'function') {
					callback.success = () => {};
				}
				if (typeof callback.fail != 'function') {
					callback.fail = () => {};
				}
				if (typeof callback.complete != 'function') {
					callback.complete = () => {};
				}

				this.resetCanvas(() => {
					clearTimeout(this.makeDelegate);
					this.makeDelegate = setTimeout(() => {
						this.draw({
							success: () => {
								setTimeout(() => {
									callback.success();
									this.complete(true);
								}, 300);
							},
							fail: err => {
								callback.fail(err);
								this.error = err;
								this.complete(false, err.errMsg);
							},
							complete: () => {
								callback.complete();
								this.makeing = false;
							}
						});
					}, 300);
				});
			},
			/**
			 * 重新生成
			 */
			remake(callback) {
				this.$emit('change');
				this.make(callback);
			},
			/**
			 * 生成完成
			 */
			complete(success = true, errMsg = '') {
				if (success) {
					this.$emit('complete', {
						success
					});
				} else {
					this.$emit('complete', {
						success,
						errMsg
					});
				}
			},
			/**
			 * 导出临时路径
			 */
			toTempFilePath(callback = {}) {
				if (typeof callback.success != 'function') {
					callback.success = () => {};
				}
				if (typeof callback.fail != 'function') {
					callback.fail = () => {};
				}
				if (typeof callback.complete != 'function') {
					callback.complete = () => {};
				}

				if (!this.makeExecuted) {
					console.error('[uQRCode]: make() 方法从未调用！请先成功调用 make() 后再进行操作。');
					var err = {
						errMsg: '[uQRCode]: make() method has never been executed! please execute make() successfully before operating.'
					};
					callback.fail(err);
					callback.complete(err);
					return;
				}

				if (this.isError) {
					callback.fail(this.error);
					callback.complete(this.error);
					return;
				}

				if (this.makeing) {
					/* 如果还在生成状态，那当前操作将托管到委托，监听生成完成后再通过委托复调当前方法 */
					this.toTempFilePathDelegate = () => {
						this.toTempFilePath(callback);
					};
					return;
				} else {
					this.toTempFilePathDelegate = null;
				}

				// #ifndef APP-NVUE
				if (this.canvasType === '2d') {
					// #ifdef MP-WEIXIN
					try {
						let dataURL = null;
						// #ifdef VUE3
						dataURL = toRaw(this.canvas)
							.toDataURL();
						// #endif
						// #ifndef VUE3
						dataURL = this.canvas.toDataURL();
						// #endif
						callback.success({
							tempFilePath: dataURL
						});
						callback.complete({
							tempFilePath: dataURL
						});
					} catch (e) {
						callback.fail(e);
						callback.complete(e);
					}
					// #endif
				} else {
					uni.canvasToTempFilePath({
							canvasId: this.canvasId,
							fileType: this.fileType,
							width: Number(this.templateOptions.canvasWidth),
							height: Number(this.templateOptions.canvasHeight),
							destWidth: Number(this.templateOptions.size),
							destHeight: Number(this.templateOptions.size),
							success: res => {
								callback.success(res);
							},
							fail: err => {
								callback.fail(err);
							},
							complete: () => {
								callback.complete();
							}
						},
						this
					);
				}
				// #endif
				// #ifdef APP-NVUE
				const dpr = uni.getSystemInfoSync().pixelRatio;
				this.canvasContext.toTempFilePath(
					0,
					0,
					this.templateOptions.canvasWidth * dpr,
					this.templateOptions.canvasHeight * dpr,
					this.templateOptions.size * dpr,
					this.templateOptions.size * dpr,
					'',
					1,
					res => {
						callback.success(res);
						callback.complete(res);
					}
				);
				// #endif
			},
			/**
			 * 保存
			 */
			save(callback = {}) {
				if (typeof callback.success != 'function') {
					callback.success = () => {};
				}
				if (typeof callback.fail != 'function') {
					callback.fail = () => {};
				}
				if (typeof callback.complete != 'function') {
					callback.complete = () => {};
				}

				this.toTempFilePath({
					success: res => {
						// #ifndef H5
						if (this.canvasType === '2d') {
							// #ifdef MP-WEIXIN
							/* 需要将 data:image/png;base64, 这段去除 writeFile 才能正常打开文件，否则是损坏文件，无法打开 */
							const reg = new RegExp('^data:image/png;base64,', 'g');
							const dataURL = res.tempFilePath.replace(reg, '');
							const fs = wx.getFileSystemManager();
							const tempFilePath = `${wx.env.USER_DATA_PATH}/${new Date().getTime()}${
                Math.random()
                  .toString()
                  .split('.')[1]
              }.png`;
							fs.writeFile({
								filePath: tempFilePath, // 要写入的文件路径 (本地路径)
								data: dataURL, // base64图片
								encoding: 'base64',
								success: res1 => {
									uni.saveImageToPhotosAlbum({
										filePath: tempFilePath,
										success: res2 => {
											callback.success(res2);
										},
										fail: err2 => {
											callback.fail(err2);
										},
										complete: () => {
											callback.complete();
										}
									});
								},
								fail: err => {
									callback.fail(err);
								},
								complete: () => {
									callback.complete();
								}
							});
							// #endif
						} else {
							uni.saveImageToPhotosAlbum({
								filePath: res.tempFilePath,
								success: res1 => {
									callback.success(res1);
								},
								fail: err1 => {
									callback.fail(err1);
								},
								complete: () => {
									callback.complete();
								}
							});
						}
						// #endif

						// #ifdef H5
						/* 可以在电脑浏览器下载，移动端iOS不行，安卓微信浏览器不行，安卓外部浏览器可以 */
						this.isH5Save = this.h5SaveTip;
						this.tempFilePath = res.tempFilePath;
						if (this.h5SaveIsDownload) {
							const aEle = document.createElement('a');
							aEle.download = this.h5DownloadName; // 设置下载的文件名，默认是'下载'
							aEle.href = res.tempFilePath;
							document.body.appendChild(aEle);
							aEle.click();
							aEle.remove(); // 下载之后把创建的元素删除
						}
						callback.success({
							errMsg: 'ok'
						});
						callback.complete({
							errMsg: 'ok'
						});
						// #endif
					},
					fail: err => {
						callback.fail(err);
						callback.complete(err);
					}
				});
			},
			/**
			 * 注册click事件
			 */
			onClick(e) {
				this.$emit('click', e);
			},
			/**
			 * 获取实例
			 */
			getInstance() {
				return instance;
			},
			/**
			 * 注册扩展，组件仅支持注册type为style的drawCanvas扩展
			 * @param {Object} plugin
			 */
			registerStyle(plugin) {
				if (plugin.Type != 'style') {
					console.warn('[uQRCode]: registerStyle 仅支持注册 style 类型的扩展！');
					return {
						errMsg: 'registerStyle 仅支持注册 style 类型的扩展！'
					};
				}
				if (typeof plugin === 'function') {
					this.plugins.push({
						plugin,
						name: plugin.Name,
						drawCanvas: plugin.DrawCanvas
					});
				}
			},
			getLoadImage(loadImage) {
				var that = this;
				if (typeof loadImage == 'function') {
					return function(src) {
						/* 判断是否是队列加载图片的 */
						if (that.isQueueLoadImage) {
							/* 解决iOS APP||NVUE同时绘制多个二维码导致图片丢失需使用队列 */
							return queueLoadImage.exec(() => {
								return new Promise((resolve, reject) => {
									setTimeout(() => {
										const cache = cacheImageList.find(x => x.src == src);
										if (cache) {
											resolve(cache.img);
										} else {
											loadImage(src)
												.then(img => {
													cacheImageList.push({
														src,
														img
													});
													resolve(img);
												})
												.catch(err => {
													reject(err);
												});
										}
									}, 10);
								});
							});
						} else {
							return loadImage(src);
						}
					};
				} else {
					return function(src) {
						return Promise.resolve(src);
					};
				}
			}
		}
	};

	/**
	 * 对象属性深度替换
	 * @param {Object} o 原始对象/默认对象/被替换的对象
	 * @param {Object} r 从这个对象里取值替换到o对象里
	 * @return {Object} 替换后的新对象
	 */
	function deepReplace(o = {}, r = {}, c = false) {
		let obj;
		if (c) {
			// 从源替换
			obj = o;
		} else {
			// 不替换源，copy一份备份来替换
			obj = {
				...o
			};
		}
		for (let k in r) {
			var vr = r[k];
			if (vr != undefined) {
				if (vr.constructor == Object) {
					obj[k] = this.deepReplace(obj[k], vr);
				} else if (vr.constructor == String && !vr) {
					obj[k] = obj[k];
				} else {
					obj[k] = vr;
				}
			}
		}
		return obj;
	}
</script>

<style scoped>
	.uqrcode {
		position: relative;
	}
	.uqrcode-hide {
		position: fixed;
		left: 7500rpx;
	}
	.uqrcode-canvas {
		transform-origin: top left;
	}
	.uqrcode-makeing {
		position: absolute;
		top: 0;
		right: 0;
		bottom: 0;
		left: 0;
		z-index: 10;
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		justify-content: center;
		align-items: center;
	}
	.uqrcode-makeing-image {
		/* #ifndef APP-NVUE */
		display: block;
		max-width: 120px;
		max-height: 120px;
		/* #endif */
	}
	.uqrcode-error {
		position: absolute;
		top: 0;
		right: 0;
		bottom: 0;
		left: 0;
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		justify-content: center;
		align-items: center;
	}
	.uqrcode-error-message {
		font-size: 12px;
		color: #939291;
	}
	/* #ifdef H5 */
	.uqrcode-h5-save {
		position: fixed;
		top: 0;
		right: 0;
		bottom: 0;
		left: 0;
		z-index: 100;
		background-color: rgba(0, 0, 0, 0.68);
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
	.uqrcode-h5-save-image {
		width: 512rpx;
		height: 512rpx;
		padding: 32rpx;
	}
	.uqrcode-h5-save-text {
		margin-top: 20rpx;
		font-size: 32rpx;
		font-weight: 700;
		color: #ffffff;
	}
	.uqrcode-h5-save-close {
		position: relative;
		margin-top: 72rpx;
		width: 60rpx;
		height: 60rpx;
		border: 2rpx solid #ffffff;
		border-radius: 60rpx;
		padding: 10rpx;
	}
	.uqrcode-h5-save-close-before {
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%) rotate(45deg);
		width: 40rpx;
		height: 4rpx;
		background: #ffffff;
	}
	.uqrcode-h5-save-close-after {
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%) rotate(-45deg);
		width: 40rpx;
		height: 4rpx;
		background: #ffffff;
	}
	/* #endif */
</style>
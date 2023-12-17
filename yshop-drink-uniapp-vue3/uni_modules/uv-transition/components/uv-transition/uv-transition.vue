<template>
  <!-- #ifndef APP-NVUE -->
  <view 
		v-if="isShow" 
		ref="ani" 
		:animation="animationData" 
		:class="customClass" 
		:style="transformStyles" 
		@click="onClick">
		<slot></slot>
	</view>
  <!-- #endif -->
  <!-- #ifdef APP-NVUE -->
  <view 
		v-if="isShow" 
		ref="ani" 
		:animation="animationData" 
		:class="customClass" 
		:style="transformStyles" 
		@click="onClick">
		<slot></slot>
	</view>
  <!-- #endif -->
</template>
<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	import { createAnimation } from './createAnimation'
	/**
	* transition  动画组件
	* @description
	* @tutorial
	* @property {Boolean}	show	控制组件显示或关闭 （默认 false ）
	* @property {Array | String	}	mode	内置过渡动画类型 （默认 'fade' ）
	* @value fade 渐隐渐出过渡
	* @value slide-top 由上至下过渡
	* @value slide-bottom 由下至上过渡
	* @value slide-left 由左至右过渡
	* @value slide-right 由右至左过渡
	* @value zoom-in 由小到大过渡
	* @value zoom-out 由大到小过渡
	* @property {String | Number}	duration	动画的执行时间，单位ms （默认 300 ）
	* @property {String} timingFunction	使用的动画过渡函数 （默认 'ease-out' ）
	* @property {Object} customStyle	自定义样式
	* @property {String} customClass	自定义类名
	* @event {Function} click 点击组件触发	
	* @event {Function} change	过渡动画结束时触发	
	* @example 
	*/
	export default {
		name: 'uv-transition',
		mixins: [mpMixin,mixin],
		emits:['click','change'],
		props: {
			// 是否展示组件
			show: {
				type: Boolean,
				default: false
			},
			// 使用的动画模式
			mode: {
				type: [Array, String, null],
				default() {
					return 'fade'
				}
			},
			// 动画的执行时间，单位ms
			duration: {
				type: [String, Number],
				default: 300
			},
			// 使用的动画过渡函数
			timingFunction: {
				type: String,
				default: 'ease-out'
			},
			customClass: {
				type: String,
				default: ''
			},
			// nvue模式下 是否直接显示，在uv-list等cell下面使用就需要设置
			cellChild: {
				type: Boolean,
				default: false
			}
		},
		data(){
			return {
				isShow: false,
				transform: '',
				opacity: 1,
				animationData: {},
				durationTime: 300,
				config: {}
			}
		},
		watch: {
			show: {
				handler(newVal) {
					if (newVal) {
						this.open();
					} else {
						// 避免上来就执行 close,导致动画错乱
						if (this.isShow) {
							this.close();
						}
					}
				},
				immediate: true
			}
		},
		computed: {
			// 初始化动画条件
			transformStyles() {
				const style = {
					transform: this.transform,
					opacity: this.opacity,
					...this.$uv.addStyle(this.customStyle),
					'transition-duration': `${this.duration / 1000}s`
				};
				return this.$uv.addStyle(style,'string');
			}
		},
		created() {
			// 动画默认配置
			this.config = {
				duration: this.duration,
				timingFunction: this.timingFunction,
				transformOrigin: '50% 50%',
				delay: 0
			};
			this.durationTime = this.duration;
		},
		methods: {
			/**
			 *  ref 触发 初始化动画
			 */
			init(obj = {}) {
				if (obj.duration) {
					this.durationTime = obj.duration;
				}
				this.animation = createAnimation(Object.assign(this.config, obj),this);
			},
			/**
			 * 点击组件触发回调
			 */
			onClick() {
				this.$emit('click', {
					detail: this.isShow
				})
			},
			/**
			 * ref 触发 动画分组
			 * @param {Object} obj
			 */
			step(obj, config = {}) {
				if (!this.animation) return;
				for (let i in obj) {
					try {
						if(typeof obj[i] === 'object'){
							this.animation[i](...obj[i]);
						}else{
							this.animation[i](obj[i]);
						}
					} catch (e) {
						console.error(`方法 ${i} 不存在`);
					}
				}
				this.animation.step(config);
				return this;
			},
			/**
			 *  ref 触发 执行动画
			 */
			run(fn) {
				if (!this.animation) return;
				this.animation.run(fn);
			},
			// 开始过度动画
			open() {
				clearTimeout(this.timer);
				this.transform = '';
				this.isShow = true;
				let { opacity, transform } = this.styleInit(false);
				if (typeof opacity !== 'undefined') {
					this.opacity = opacity;
				}
				this.transform = transform;
				// 确保动态样式已经生效后，执行动画，如果不加 nextTick ，会导致 wx 动画执行异常
				this.$nextTick(() => {
					// TODO 定时器保证动画完全执行，目前有些问题，后面会取消定时器
					this.timer = setTimeout(() => {
						this.animation = createAnimation(this.config, this);
						this.tranfromInit(false).step();
						// #ifdef APP-NVUE
						if(this.cellChild) {
							this.opacity = 1;
						} else{
							this.animation.run();
						}
						// #endif
						// #ifndef APP-NVUE
						this.animation.run();
						// #endif
						// #ifdef VUE3
						// #ifdef H5
						this.opacity = 1;
						// #endif
						// #endif
						this.$emit('change', {
							detail: this.isShow
						})
						// #ifdef H5
						// #ifdef VUE3
						this.transform = '';
						// #endif
						// #endif
					}, 20);
				})
			},
			// 关闭过渡动画
			close(type) {
				if (!this.animation) return;
				this.tranfromInit(true)
					.step()
					.run(() => {
						this.isShow = false;
						this.animationData = null;
						this.animation = null;
						let { opacity, transform } = this.styleInit(false);
						this.opacity = opacity || 1;
						this.transform = transform;
						this.$emit('change', {
							detail: this.isShow
						});
					})
			},
			// 处理动画开始前的默认样式
			styleInit(type) {
				let styles = {
					transform: ''
				};
				let buildStyle = (type, mode) => {
					if (mode === 'fade') {
						styles.opacity = this.animationType(type)[mode];
					} else {
						styles.transform += this.animationType(type)[mode] + ' ';
					}
				}
				if (typeof this.mode === 'string') {
					buildStyle(type, this.mode);
				} else {
					this.mode.forEach(mode => {
						buildStyle(type, mode)
					})
				}
				return styles
			},
			// 处理内置组合动画
			tranfromInit(type) {
				let buildTranfrom = (type, mode) => {
					let aniNum = null;
					if (mode === 'fade') {
						aniNum = type ? 0 : 1;
					} else {
						aniNum = type ? '-100%' : '0';
						if (mode === 'zoom-in') {
							aniNum = type ? 0.8 : 1
						}
						if (mode === 'zoom-out') {
							aniNum = type ? 1.2 : 1
						}
						if (mode === 'slide-right') {
							aniNum = type ? '100%' : '0'
						}
						if (mode === 'slide-bottom') {
							aniNum = type ? '100%' : '0'
						}
					}
					this.animation[this.animationMode()[mode]](aniNum)
				}
				if (typeof this.mode === 'string') {
					buildTranfrom(type, this.mode)
				} else {
					this.mode.forEach(mode => {
						buildTranfrom(type, mode)
					})
				}
				return this.animation;
			},
			animationType(type) {
				return {
					fade: type ? 1 : 0,
					'slide-top': `translateY(${type ? '0' : '-100%'})`,
					'slide-right': `translateX(${type ? '0' : '100%'})`,
					'slide-bottom': `translateY(${type ? '0' : '100%'})`,
					'slide-left': `translateX(${type ? '0' : '-100%'})`,
					'zoom-in': `scaleX(${type ? 1 : 0.8}) scaleY(${type ? 1 : 0.8})`,
					'zoom-out': `scaleX(${type ? 1 : 1.2}) scaleY(${type ? 1 : 1.2})`
				}
			},
			// 内置动画类型与实际动画对应字典
			animationMode() {
				return {
					fade: 'opacity',
					'slide-top': 'translateY',
					'slide-right': 'translateX',
					'slide-bottom': 'translateY',
					'slide-left': 'translateX',
					'zoom-in': 'scale',
					'zoom-out': 'scale'
				}
			},
			// 驼峰转中横线
			toLine(name) {
				return name.replace(/([A-Z])/g, '-$1').toLowerCase()
			}
		}
	}
</script>

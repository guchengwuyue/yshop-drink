<template>
	<view
	  class="uv-icon"
	  @tap="clickHandler"
	  :class="['uv-icon--' + labelPos]"
	>
		<image
		  class="uv-icon__img"
		  v-if="isImg"
		  :src="name"
		  :mode="imgMode"
		  :style="[imgStyle, $uv.addStyle(customStyle)]"
		></image>
		<text
		  v-else
		  class="uv-icon__icon"
		  :class="uClasses"
		  :style="[iconStyle, $uv.addStyle(customStyle)]"
		  :hover-class="hoverClass"
		>{{icon}}</text>
		<!-- 这里进行空字符串判断，如果仅仅是v-if="label"，可能会出现传递0的时候，结果也无法显示 -->
		<text
		  v-if="label !== ''" 
		  class="uv-icon__label"
		  :style="{
			color: labelColor,
			fontSize: $uv.addUnit(labelSize),
			marginLeft: labelPos == 'right' ? $uv.addUnit(space) : 0,
			marginTop: labelPos == 'bottom' ? $uv.addUnit(space) : 0,
			marginRight: labelPos == 'left' ? $uv.addUnit(space) : 0,
			marginBottom: labelPos == 'top' ? $uv.addUnit(space) : 0
		}"
		>{{ label }}</text>
	</view>
</template>

<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	// #ifdef APP-NVUE
	// nvue通过weex的dom模块引入字体，相关文档地址如下：
	// https://weex.apache.org/zh/docs/modules/dom.html#addrule
	import iconUrl from './uvicons.ttf';
	const domModule = weex.requireModule('dom')
	domModule.addRule('fontFace', {
		'fontFamily': "uvicon-iconfont",
		'src': "url('" + iconUrl + "')"
	})
	// #endif
	// 引入图标名称，已经对应的unicode
	import icons from './icons';
	import props from './props.js';
	/**
	 * icon 图标
	 * @description 基于字体的图标集，包含了大多数常见场景的图标。
	 * @tutorial https://www.uvui.cn/components/icon.html
	 * @property {String}			name			图标名称，见示例图标集
	 * @property {String}			color			图标颜色,可接受主题色 （默认 color['uv-content-color'] ）
	 * @property {String | Number}	size			图标字体大小，单位px （默认 '16px' ）
	 * @property {Boolean}			bold			是否显示粗体 （默认 false ）
	 * @property {String | Number}	index			点击图标的时候传递事件出去的index（用于区分点击了哪一个）
	 * @property {String}			hoverClass		图标按下去的样式类，用法同uni的view组件的hoverClass参数，详情见官网
	 * @property {String}			customPrefix	自定义扩展前缀，方便用户扩展自己的图标库 （默认 'uicon' ）
	 * @property {String | Number}	label			图标右侧的label文字
	 * @property {String}			labelPos		label相对于图标的位置，只能right或bottom （默认 'right' ）
	 * @property {String | Number}	labelSize		label字体大小，单位px （默认 '15px' ）
	 * @property {String}			labelColor		图标右侧的label文字颜色 （ 默认 color['uv-content-color'] ）
	 * @property {String | Number}	space			label与图标的距离，单位px （默认 '3px' ）
	 * @property {String}			imgMode			图片的mode
	 * @property {String | Number}	width			显示图片小图标时的宽度
	 * @property {String | Number}	height			显示图片小图标时的高度
	 * @property {String | Number}	top				图标在垂直方向上的定位 用于解决某些情况下，让图标垂直居中的用途  （默认 0 ）
	 * @property {Boolean}			stop			是否阻止事件传播 （默认 false ）
	 * @property {Object}			customStyle		icon的样式，对象形式
	 * @event {Function} click 点击图标时触发
	 * @event {Function} touchstart 事件触摸时触发
	 * @example <uv-icon name="photo" color="#2979ff" size="28"></uv-icon>
	 */
	export default {
		name: 'uv-icon',
		emits: ['click'],
		mixins: [mpMixin, mixin, props],
		data() {
			return {
				colorType: [
					'primary',
					'success',
					'info',
					'error',
					'warning'
				]
			}
		},
		computed: {
			uClasses() {
				let classes = []
				classes.push(this.customPrefix)
				classes.push(this.customPrefix + '-' + this.name)
				// 主题色，通过类配置
				if (this.color && this.colorType.includes(this.color)) classes.push('uv-icon__icon--' + this.color)
				// 阿里，头条，百度小程序通过数组绑定类名时，无法直接使用[a, b, c]的形式，否则无法识别
				// 故需将其拆成一个字符串的形式，通过空格隔开各个类名
				//#ifdef MP-ALIPAY || MP-TOUTIAO || MP-BAIDU
				classes = classes.join(' ')
				//#endif
				return classes
			},
			iconStyle() {
				let style = {}
				style = {
					fontSize: this.$uv.addUnit(this.size),
					lineHeight: this.$uv.addUnit(this.size),
					fontWeight: this.bold ? 'bold' : 'normal',
					// 某些特殊情况需要设置一个到顶部的距离，才能更好的垂直居中
					top: this.$uv.addUnit(this.top)
				}
				// 非主题色值时，才当作颜色值
				if (this.color && !this.colorType.includes(this.color)) style.color = this.color
				return style
			},
			// 判断传入的name属性，是否图片路径，只要带有"/"均认为是图片形式
			isImg() {
				const isBase64 = this.name.indexOf('data:') > -1 && this.name.indexOf('base64') > -1;
				return this.name.indexOf('/') !== -1 || isBase64;
			},
			imgStyle() {
				let style = {}
				// 如果设置width和height属性，则优先使用，否则使用size属性
				style.width = this.width ? this.$uv.addUnit(this.width) : this.$uv.addUnit(this.size)
				style.height = this.height ? this.$uv.addUnit(this.height) : this.$uv.addUnit(this.size)
				return style
			},
			// 通过图标名，查找对应的图标
			icon() {
				// 如果内置的图标中找不到对应的图标，就直接返回name值，因为用户可能传入的是unicode代码
				const code = icons['uvicon-' + this.name];
				// #ifdef APP-NVUE
				if(!code) {
					return code ? unescape(`%u${code}`) : ['uvicon'].indexOf(this.customPrefix) > -1 ? unescape(`%u${this.name}`) : '';
				}
				// #endif
				return code ? unescape(`%u${code}`) : ['uvicon'].indexOf(this.customPrefix) > -1 ? this.name : '';
			}
		},
		methods: {
			clickHandler(e) {
				this.$emit('click', this.index)
				// 是否阻止事件冒泡
				this.stop && this.preventEvent(e)
			}
		}
	}
</script>

<style lang="scss" scoped>
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
	@import '@/uni_modules/uv-ui-tools/libs/css/color.scss';
	// 变量定义
	$uv-icon-primary: $uv-primary !default;
	$uv-icon-success: $uv-success !default;
	$uv-icon-info: $uv-info !default;
	$uv-icon-warning: $uv-warning !default;
	$uv-icon-error: $uv-error !default;
	$uv-icon-label-line-height: 1 !default;
	/* #ifndef APP-NVUE */
	// 非nvue下加载字体
	@font-face {
		font-family: 'uvicon-iconfont';
		src: url('./uvicons.ttf') format('truetype');
	}
	/* #endif */
	.uv-icon {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		align-items: center;
		&--left {
			flex-direction: row-reverse;
			align-items: center;
		}
		&--right {
			flex-direction: row;
			align-items: center;
		}
		&--top {
			flex-direction: column-reverse;
			justify-content: center;
		}
		&--bottom {
			flex-direction: column;
			justify-content: center;
		}
		&__icon {
			font-family: uvicon-iconfont;
			position: relative;
			@include flex;
			align-items: center;
			&--primary {
				color: $uv-icon-primary;
			}
			&--success {
				color: $uv-icon-success;
			}
			&--error {
				color: $uv-icon-error;
			}
			&--warning {
				color: $uv-icon-warning;
			}
			&--info {
				color: $uv-icon-info;
			}
		}
		&__img {
			/* #ifndef APP-NVUE */
			height: auto;
			will-change: transform;
			/* #endif */
		}
		&__label {
			/* #ifndef APP-NVUE */
			line-height: $uv-icon-label-line-height;
			/* #endif */
		}
	}
</style>
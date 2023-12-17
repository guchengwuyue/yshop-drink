export default {
	props: {
		// 是否显示组件
		show: {
			type: Boolean,
			default: true
		},
		// 颜色
		color: {
			type: String,
			default: '#909193'
		},
		// 提示文字颜色
		textColor: {
			type: String,
			default: '#909193'
		},
		// 文字和图标是否垂直排列
		vertical: {
			type: Boolean,
			default: false
		},
		// 模式选择，circle-圆形，spinner-花朵形，semicircle-半圆形
		mode: {
			type: String,
			default: 'spinner'
		},
		// 图标大小，单位默认px
		size: {
			type: [String, Number],
			default: 24
		},
		// 文字大小
		textSize: {
			type: [String, Number],
			default: 15
		},
		// 文字样式
		textStyle: {
			type: Object,
			default () {
				return {}
			}
		},
		// 文字内容
		text: {
			type: [String, Number],
			default: ''
		},
		// 动画模式 https://www.runoob.com/cssref/css3-pr-animation-timing-function.html
		timingFunction: {
			type: String,
			default: 'linear'
		},
		// 动画执行周期时间
		duration: {
			type: [String, Number],
			default: 1200
		},
		// mode=circle时的暗边颜色
		inactiveColor: {
			type: String,
			default: ''
		},
		...uni.$uv?.props?.loadingIcon
	}
}
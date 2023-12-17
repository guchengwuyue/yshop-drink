export default {
	props: {
		// 组件状态，loadmore-加载前的状态，loading-加载中的状态，nomore-没有更多的状态
		status: {
			type: String,
			default: 'loadmore'
		},
		// 组件背景色
		bgColor: {
			type: String,
			default: 'transparent'
		},
		// 是否显示加载中的图标
		icon: {
			type: Boolean,
			default: true
		},
		// 字体大小
		fontSize: {
			type: [String, Number],
			default: 14
		},
		// 图标大小
		iconSize: {
			type: [String, Number],
			default: 16
		},
		// 字体颜色
		color: {
			type: String,
			default: '#606266'
		},
		// 加载中状态的图标，spinner-花朵状图标，circle-圆圈状，semicircle-半圆
		loadingIcon: {
			type: String,
			default: 'spinner'
		},
		// 加载前的提示语
		loadmoreText: {
			type: String,
			default: '加载更多'
		},
		// 加载中提示语
		loadingText: {
			type: String,
			default: '正在加载...'
		},
		// 没有更多的提示语
		nomoreText: {
			type: String,
			default: '没有更多了'
		},
		// 在“没有更多”状态下，是否显示粗点
		isDot: {
			type: Boolean,
			default: false
		},
		// 加载中图标的颜色
		iconColor: {
			type: String,
			default: '#b7b7b7'
		},
		// 上边距
		marginTop: {
			type: [String, Number],
			default: 10
		},
		// 下边距
		marginBottom: {
			type: [String, Number],
			default: 10
		},
		// 高度，单位px
		height: {
			type: [String, Number],
			default: 'auto'
		},
		// 是否显示左边分割线
		line: {
			type: Boolean,
			default: false
		},
		// 线条颜色
		lineColor: {
			type: String,
			default: '#E6E8EB'
		},
		// 是否虚线，true-虚线，false-实线
		dashed: {
			type: Boolean,
			default: false
		},
		...uni.$uv?.props?.loadmore
	}
}
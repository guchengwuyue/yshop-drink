export default {
	props: {
		// 返回顶部的形状，circle-圆形，square-方形
		mode: {
			type: String,
			default: 'circle'
		},
		// 自定义图标
		icon: {
			type: String,
			default: 'arrow-upward'
		},
		// 提示文字
		text: {
			type: String,
			default: ''
		},
		// 返回顶部滚动时间
		duration: {
			type: [String, Number],
			default: 100
		},
		// 滚动距离
		scrollTop: {
			type: [String, Number],
			default: 0
		},
		// 距离顶部多少距离显示，单位px
		top: {
			type: [String, Number],
			default: 400
		},
		// 返回顶部按钮到底部的距离，单位px
		bottom: {
			type: [String, Number],
			default: 100
		},
		// 返回顶部按钮到右边的距离，单位px
		right: {
			type: [String, Number],
			default: 20
		},
		// 层级
		zIndex: {
			type: [String, Number],
			default: 9
		},
		// 图标的样式，对象形式
		iconStyle: {
			type: Object,
			default: () => ({
				color: '#909399',
				fontSize: '19px'
			})
		},
		...uni.$uv?.props?.backtop
	}
}
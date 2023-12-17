export default {
	props: {
		// 是否展示弹窗
		show: {
			type: Boolean,
			default: false
		},
		// 是否显示遮罩
		overlay: {
			type: Boolean,
			default: true
		},
		// 弹出的方向，可选值为 top bottom right left center
		mode: {
			type: String,
			default: 'bottom'
		},
		// 动画时长，单位ms
		duration: {
			type: [String, Number],
			default: 300
		},
		// 是否显示关闭图标
		closeable: {
			type: Boolean,
			default: false
		},
		// 自定义遮罩的样式
		overlayStyle: {
			type: [Object, String],
			default: ''
		},
		// 点击遮罩是否关闭弹窗
		closeOnClickOverlay: {
			type: Boolean,
			default: true
		},
		// 层级
		zIndex: {
			type: [String, Number],
			default: 10075
		},
		// 是否为iPhoneX留出底部安全距离
		safeAreaInsetBottom: {
			type: Boolean,
			default: true
		},
		// 是否留出顶部安全距离（状态栏高度）
		safeAreaInsetTop: {
			type: Boolean,
			default: false
		},
		// 自定义关闭图标位置，top-left为左上角，top-right为右上角，bottom-left为左下角，bottom-right为右下角
		closeIconPos: {
			type: String,
			default: 'top-right'
		},
		// 圆角值
		round: {
			type: [Boolean, String, Number],
			default: 0
		},
		// mode=center，也即中部弹出时，是否使用缩放模式
		zoom: {
			type: Boolean,
			default: true
		},
		// 弹窗背景色，设置为transparent可去除白色背景
		bgColor: {
			type: String,
			default: ''
		},
		// 遮罩的透明度，0-1之间
		overlayOpacity: {
			type: [Number, String],
			default: 0.5
		},
		...uni.$uv?.props?.popup
	}
}
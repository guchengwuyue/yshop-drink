export default {
	props: {
		// 是否开启顶部安全区适配
		safeAreaInsetTop: {
			type: Boolean,
			default: true
		},
		// 固定在顶部时，是否生成一个等高元素，以防止塌陷
		placeholder: {
			type: Boolean,
			default: false
		},
		// 是否固定在顶部
		fixed: {
			type: Boolean,
			default: true
		},
		// 是否显示下边框
		border: {
			type: Boolean,
			default: false
		},
		// 左边的图标
		leftIcon: {
			type: String,
			default: 'arrow-left'
		},
		// 左边的提示文字
		leftText: {
			type: String,
			default: ''
		},
		// 左右的提示文字
		rightText: {
			type: String,
			default: ''
		},
		// 右边的图标
		rightIcon: {
			type: String,
			default: ''
		},
		// 标题
		title: {
			type: [String, Number],
			default: ''
		},
		// 背景颜色
		bgColor: {
			type: String,
			default: '#ffffff'
		},
		imgMode: {
			type: String,
			default: 'aspectFill'
		},
		// 标题的宽度
		titleWidth: {
			type: [String, Number],
			default: '400rpx'
		},
		// 导航栏高度
		height: {
			type: [String, Number],
			default: '44px'
		},
		// 左侧返回图标的大小
		leftIconSize: {
			type: [String, Number],
			default: 20
		},
		// 左侧返回图标的颜色
		leftIconColor: {
			type: String,
			default: '#303133'
		},
		// 点击左侧区域(返回图标)，是否自动返回上一页
		autoBack: {
			type: Boolean,
			default: false
		},
		// 标题的样式，对象或字符串
		titleStyle: {
			type: [String, Object],
			default: ''
		},
		...uni.$uv?.props?.navbar
	}
}
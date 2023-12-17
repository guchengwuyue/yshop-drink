export default {
	props: {
		// 颜色选择器初始颜色
		color: {
			type: Object,
			default: () => {
				return { r: 0, g: 0, b: 0, a: 0 }
			}
		},
		// 预制颜色
		prefabColor: {
			type: Array,
			default: () => []
		},
		// 是否允许点击遮罩关闭
		closeOnClickOverlay: {
			type: Boolean,
			default: true
		},
		// 顶部标题
		title: {
			type: String,
			default: ''
		},
		// 取消按钮的文字
		cancelText: {
			type: String,
			default: '取消'
		},
		// 确认按钮的文字
		confirmText: {
			type: String,
			default: '确定'
		},
		// 取消按钮的颜色
		cancelColor: {
			type: String,
			default: '#909193'
		},
		// 确认按钮的颜色
		confirmColor: {
			type: String,
			default: '#3c9cff'
		},
		...uni.$uv?.props?.pickColor
	}
}
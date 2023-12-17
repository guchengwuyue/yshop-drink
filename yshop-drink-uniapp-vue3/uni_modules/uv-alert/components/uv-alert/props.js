export default {
	props: {
		// 显示文字
		title: {
			type: String,
			default: ''
		},
		// 主题，success/warning/info/error
		type: {
			type: String,
			default: 'warning'
		},
		// 辅助性文字
		description: {
			type: String,
			default: ''
		},
		// 是否可关闭
		closable: {
			type: Boolean,
			default: false
		},
		// 是否显示图标
		showIcon: {
			type: Boolean,
			default: false
		},
		// 浅或深色调，light-浅色，dark-深色
		effect: {
			type: String,
			default: 'light'
		},
		// 文字是否居中
		center: {
			type: Boolean,
			default: false
		},
		// 字体大小
		fontSize: {
			type: [String, Number],
			default: 14
		},
		...uni.$uv?.props?.alert
	}
}
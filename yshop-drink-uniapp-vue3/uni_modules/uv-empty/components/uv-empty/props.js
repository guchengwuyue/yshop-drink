export default {
	props: {
		// 内置图标名称，或图片路径，建议绝对路径
		icon: {
			type: String,
			default: ''
		},
		// 提示文字
		text: {
			type: String,
			default: ''
		},
		// 文字颜色
		textColor: {
			type: String,
			default: '#c0c4cc'
		},
		// 文字大小
		textSize: {
			type: [String, Number],
			default: 14
		},
		// 图标的颜色
		iconColor: {
			type: String,
			default: '#c0c4cc'
		},
		// 图标的大小
		iconSize: {
			type: [String, Number],
			default: 90
		},
		// 选择预置的图标类型
		mode: {
			type: String,
			default: 'data'
		},
		//  图标宽度，单位px
		width: {
			type: [String, Number],
			default: 160
		},
		// 图标高度，单位px
		height: {
			type: [String, Number],
			default: 160
		},
		// 是否显示组件
		show: {
			type: Boolean,
			default: true
		},
		// 组件距离上一个元素之间的距离，默认px单位
		marginTop: {
			type: [String, Number],
			default: 0
		},
		...uni.$uv?.props?.empty
	}
}
export default {
	props: {
		// 宫格的name
		name: {
			type: [String, Number, null],
			default: null
		},
		// 背景颜色
		bgColor: {
			type: String,
			default: 'transparent'
		},
		...uni.$uv?.props?.gridItem
	}
}
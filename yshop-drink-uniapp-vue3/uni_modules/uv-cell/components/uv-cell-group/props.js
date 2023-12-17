export default {
	props: {
		// 分组标题
		title: {
			type: String,
			default: ''
		},
		// 是否显示外边框
		border: {
			type: Boolean,
			default: true
		},
		...uni.$uv?.props?.cellGroup
	}
}
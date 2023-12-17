export default {
	props: {
		// 标题
		title: {
			type: [String, Number],
			default: ''
		},
		// 描述文本
		desc: {
			type: [String, Number],
			default: ''
		},
		// 图标大小
		iconSize: {
			type: [String, Number],
			default: 17
		},
		// 当前步骤是否处于失败状态
		error: {
			type: Boolean,
			default: false
		},
		...uni.$uv?.props?.stepsItem
	}
}
export default {
	props: {
		// 标题
		title: {
			type: String,
			default: ''
		},
		// 标题右侧内容
		value: {
			type: String,
			default: ''
		},
		// 标题下方的描述信息
		label: {
			type: String,
			default: ''
		},
		// 是否禁用折叠面板
		disabled: {
			type: Boolean,
			default: false
		},
		// 是否展示右侧箭头并开启点击反馈
		isLink: {
			type: Boolean,
			default: true
		},
		// 是否开启点击反馈
		clickable: {
			type: Boolean,
			default: true
		},
		// 是否显示内边框
		border: {
			type: Boolean,
			default: true
		},
		// 标题的对齐方式
		align: {
			type: String,
			default: 'left'
		},
		// 唯一标识符
		name: {
			type: [String, Number],
			default: ''
		},
		// 标题左侧图片，可为绝对路径的图片或内置图标
		icon: {
			type: String,
			default: ''
		},
		// 面板展开收起的过渡时间，单位ms
		duration: {
			type: Number,
			default: 300
		},
		...uni.$uv?.props?.collapseItem
	}
}
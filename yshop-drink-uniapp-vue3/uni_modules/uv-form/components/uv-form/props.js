export default {
	props: {
		// 当前form的需要验证字段的集合
		model: {
			type: Object,
			default: () => ({})
		},
		// 验证规则
		rules: {
			type: [Object, Function, Array],
			default: () => ({})
		},
		// 有错误时的提示方式，message-提示信息，toast-进行toast提示
		// border-bottom-下边框呈现红色，none-无提示
		errorType: {
			type: String,
			default: 'message'
		},
		// 是否显示表单域的下划线边框
		borderBottom: {
			type: Boolean,
			default: true
		},
		// label的位置，left-左边，top-上边
		labelPosition: {
			type: String,
			default: 'left'
		},
		// label的宽度，单位px
		labelWidth: {
			type: [String, Number],
			default: 45
		},
		// lable字体的对齐方式
		labelAlign: {
			type: String,
			default: 'left'
		},
		// lable的样式，对象形式
		labelStyle: {
			type: Object,
			default: () => ({})
		},
		...uni.$uv?.props?.form
	}
}
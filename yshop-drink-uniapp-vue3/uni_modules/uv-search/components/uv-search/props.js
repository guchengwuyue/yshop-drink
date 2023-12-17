export default {
	props: {
		value: {
			type: [String, Number],
			default: ''
		},
		modelValue: {
			type: [String, Number],
			default: ''
		},
		// 搜索框形状，round-圆形，square-方形
		shape: {
			type: String,
			default: 'round'
		},
		// 搜索框背景色，默认值#f2f2f2
		bgColor: {
			type: String,
			default: '#f2f2f2'
		},
		// 占位提示文字
		placeholder: {
			type: String,
			default: '请输入关键字'
		},
		// 是否启用清除控件
		clearabled: {
			type: Boolean,
			default: true
		},
		// 是否自动聚焦
		focus: {
			type: Boolean,
			default: false
		},
		// 是否在搜索框右侧显示取消按钮
		showAction: {
			type: Boolean,
			default: true
		},
		// 右边控件的样式
		actionStyle: {
			type: Object,
			default: () => ({})
		},
		// 取消按钮文字
		actionText: {
			type: String,
			default: '搜索'
		},
		// 输入框内容对齐方式，可选值为 left|center|right
		inputAlign: {
			type: String,
			default: 'left'
		},
		// input输入框的样式，可以定义文字颜色，大小等，对象形式
		inputStyle: {
			type: Object,
			default: () => ({})
		},
		// 是否禁用输入框
		disabled: {
			type: Boolean,
			default: false
		},
		// 边框颜色
		borderColor: {
			type: String,
			default: 'transparent'
		},
		// 搜索图标的颜色，默认同输入框字体颜色
		searchIconColor: {
			type: String,
			default: '#909399'
		},
		// 输入框字体颜色
		color: {
			type: String,
			default: '#606266'
		},
		// placeholder的颜色
		placeholderColor: {
			type: String,
			default: '#909399'
		},
		// 左边输入框的图标，可以为uvui图标名称或图片路径
		searchIcon: {
			type: String,
			default: 'search'
		},
		searchIconSize: {
			type: [Number, String],
			default: 22
		},
		// 组件与其他上下左右元素之间的距离，带单位的字符串形式，如"30px"、"30px 20px"等写法
		margin: {
			type: String,
			default: '0'
		},
		// 开启showAction时，是否在input获取焦点时才显示
		animation: {
			type: Boolean,
			default: false
		},
		// 输入框最大能输入的长度，-1为不限制长度(来自uniapp文档)
		maxlength: {
			type: [String, Number],
			default: -1
		},
		// 搜索框高度，单位px
		height: {
			type: [String, Number],
			default: 32
		},
		// 搜索框左侧文本
		label: {
			type: [String, Number, null],
			default: null
		},
		// 搜索框扩展样式
		boxStyle: {
			type: [String, Object],
			default: () => ({})
		},
		...uni.$uv?.props?.search
	}
}
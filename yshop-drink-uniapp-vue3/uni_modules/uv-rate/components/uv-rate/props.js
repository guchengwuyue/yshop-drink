export default {
	props: {
		value: {
			type: [String, Number],
			default: 0
		},
		modelValue: {
			type: [String, Number],
			default: 0
		},
		// 要显示的星星数量
		count: {
			type: [String, Number],
			default: 5
		},
		// 是否不可选中
		disabled: {
			type: Boolean,
			default: false
		},
		// 是否只读
		readonly: {
			type: Boolean,
			default: false
		},
		// 星星的大小，单位px
		size: {
			type: [String, Number],
			default: 18
		},
		// 未选中时的颜色
		inactiveColor: {
			type: String,
			default: '#b2b2b2'
		},
		// 选中的颜色
		activeColor: {
			type: String,
			default: '#FA3534'
		},
		// 星星之间的间距，单位px
		gutter: {
			type: [String, Number],
			default: 4
		},
		// 最少能选择的星星个数
		minCount: {
			type: [String, Number],
			default: 1
		},
		// 是否允许半星
		allowHalf: {
			type: Boolean,
			default: false
		},
		// 选中时的图标(星星)
		activeIcon: {
			type: String,
			default: 'star-fill'
		},
		// 未选中时的图标(星星)
		inactiveIcon: {
			type: String,
			default: 'star'
		},
		// 是否可以通过滑动手势选择评分
		touchable: {
			type: Boolean,
			default: false
		},
		...uni.$uv?.props?.rate
	}
}
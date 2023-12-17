export default {
	props: {
		// 瀑布流数据
		// #ifdef VUE2
		value: {
			type: Array,
			default: () => []
		},
		// #endif
		// #ifdef VUE3
		modelValue: {
			type: Array,
			default: () => []
		},
		// #endif
		// 数据的id值，根据id值对数据执行删除操作
		// 如数据为：{id: 1, name: 'uv-ui'}，那么该值设置为id
		idKey: {
			type: String,
			default: 'id'
		},
		// 每次插入数据的事件间隔，间隔越长能保证两列高度相近，但是用户体验不好，单位ms
		addTime: {
			type: Number,
			default: 200
		},
		// 瀑布流的列数，默认2，最高为5
		columnCount: {
			type: [Number, String],
			default: 2
		},
		// 列与列的间隙，默认20
		columnGap: {
			type: [Number, String],
			default: 20
		},
		// 左边和列表的间隙
		leftGap: {
			type: [Number, String],
			default: 0
		},
		// 右边和列表的间隙
		rightGap: {
			type: [Number, String],
			default: 0
		},
		// 是否显示滚动条，仅nvue生效
		showScrollbar: {
			type: [Boolean],
			default: false
		},
		// 列宽，nvue生效
		columnWidth: {
			type: [Number, String],
			default: 'auto'
		},
		// 瀑布流的宽度，nvue生效
		width: {
			type: [Number, String],
			default: ''
		},
		// 瀑布流的高度，nvue生效
		height: {
			type: [Number, String],
			default: ''
		},
		...uni.$uv?.props?.waterfall
	}
}
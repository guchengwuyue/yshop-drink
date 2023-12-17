export default {
	props: {
		// 列表数据
		list: {
			type: Array,
			default: ()=>[]
		},
		// 从list元素对象中读取的键名，默认name
		keyName: {
			type: String,
			default: 'name'
		},
		// 当前选中项
		current: {
			type: [Number, String],
			default: 0
		},
		// 头部内容的高度
		hdHeight: {
			type: [Number, String],
			default: 0
		},
		// 是否联动，默认开启联动
		chain: {
			type: Boolean,
			default: true
		},
		// 整个列表的高度，默认auto屏幕高度
		height: {
			type: [Number, String],
			default: 'auto'
		},
		// 左边列表的宽度，默认200rpx
		barWidth: {
			type: [Number, String],
			default: '180rpx'
		},
		// 左边列表是否允许滚动
		barScrollable: {
			type: Boolean,
			default: true
		},
		// 背景颜色 默认主题颜色 $bg-color
		barBgColor: {
			type: String,
			default: ''
		},
		// 左边列表的自定义样式
		barStyle: {
			type: Object,
			default: ()=>{}
		},
		// 左边列表项的自定义样式
		barItemStyle: {
			type: Object,
			default: ()=>{}
		},
		// 左边选择项激活时的自定义样式
		barItemActiveStyle: {
			type: Object,
			default: ()=>{}
		},
		// 左边选择项激活时的左边线条自定义样式
		barItemActiveLineStyle: {
			type: Object,
			default: ()=>{}
		},
		// 菜单项中的徽标自定义样式，比如定位位置
		barItemBadgeStyle: {
			type: Object,
			default: ()=>{}
		},
		// 右边区域自定义样式
		contentStyle: {
			type: Object,
			default: ()=>{}
		}
	}
}
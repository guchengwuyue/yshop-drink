export default {
	props: {
		// 控制打开或者关闭
		show: {
			type: Boolean,
			default: false
		},
		// 标识符，如果是v-for，可用index索引值
		name: {
			type: [String, Number],
			default: ''
		},
		// 是否禁用
		disabled: {
			type: Boolean,
			default: false
		},
		// 是否自动关闭其他swipe按钮组
		autoClose: {
			type: Boolean,
			default: true
		},
		// 滑动距离阈值，只有大于此值，才被认为是要打开菜单
		threshold: {
			type: Number,
			default: 20
		},
		// 右侧按钮内容
		options: {
			type: Array,
			default: () => []
		},
		// 动画过渡时间，单位ms
		duration: {
			type: [String, Number],
			default: 300
		},
		...uni.$uv?.props?.swipeActionItem
	}
}
export default {
	props: {
		// 头像图片组
		urls: {
			type: Array,
			default: () => []
		},
		// 最多展示的头像数量
		maxCount: {
			type: [String, Number],
			default: 5
		},
		// 头像形状
		shape: {
			type: String,
			default: 'circle'
		},
		// 图片裁剪模式
		mode: {
			type: String,
			default: 'scaleToFill'
		},
		// 超出maxCount时是否显示查看更多的提示
		showMore: {
			type: Boolean,
			default: true
		},
		// 头像大小
		size: {
			type: [String, Number],
			default: 40
		},
		// 指定从数组的对象元素中读取哪个属性作为图片地址
		keyName: {
			type: String,
			default: ''
		},
		// 头像之间的遮挡比例
		gap: {
			type: [String, Number],
			validator(value) {
				return value >= 0 && value <= 1
			},
			default: 0.5
		},
		// 需额外显示的值
		extraValue: {
			type: [Number, String],
			default: 0
		},
		...uni.$uv?.props?.avatarGroup
	}
}
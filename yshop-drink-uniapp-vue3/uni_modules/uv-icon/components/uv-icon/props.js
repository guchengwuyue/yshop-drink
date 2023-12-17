export default {
	props: {
		// 图标类名
		name: {
			type: String,
			default: ''
		},
		// 图标颜色，可接受主题色
		color: {
			type: String,
			default: '#606266'
		},
		// 字体大小，单位px
		size: {
			type: [String, Number],
			default: '16px'
		},
		// 是否显示粗体
		bold: {
			type: Boolean,
			default: false
		},
		// 点击图标的时候传递事件出去的index（用于区分点击了哪一个）
		index: {
			type: [String, Number],
			default: null
		},
		// 触摸图标时的类名
		hoverClass: {
			type: String,
			default: ''
		},
		// 自定义扩展前缀，方便用户扩展自己的图标库
		customPrefix: {
			type: String,
			default: 'uvicon'
		},
		// 图标右边或者下面的文字
		label: {
			type: [String, Number],
			default: ''
		},
		// label的位置，只能右边或者下边
		labelPos: {
			type: String,
			default: 'right'
		},
		// label的大小
		labelSize: {
			type: [String, Number],
			default: '15px'
		},
		// label的颜色
		labelColor: {
			type: String,
			default: '#606266'
		},
		// label与图标的距离
		space: {
			type: [String, Number],
			default: '3px'
		},
		// 图片的mode
		imgMode: {
			type: String,
			default: ''
		},
		// 用于显示图片小图标时，图片的宽度
		width: {
			type: [String, Number],
			default: ''
		},
		// 用于显示图片小图标时，图片的高度
		height: {
			type: [String, Number],
			default: ''
		},
		// 用于解决某些情况下，让图标垂直居中的用途
		top: {
			type: [String, Number],
			default: 0
		},
		// 是否阻止事件传播
		stop: {
			type: Boolean,
			default: false
		},
		...uni.$uv?.props?.icon
	}
}
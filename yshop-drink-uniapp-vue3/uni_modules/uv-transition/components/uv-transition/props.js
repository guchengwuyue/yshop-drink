export default {
	props: {
		// 是否展示组件
		show: {
			type: Boolean,
			default: false
		},
		// 使用的动画模式
		mode: {
			type: [Array, String, null],
			default() {
				return 'fade'
			}
		},
		// 动画的执行时间，单位ms
		duration: {
			type: [String, Number],
			default: 300
		},
		// 使用的动画过渡函数
		timingFunction: {
			type: String,
			default: 'ease-out'
		},
		customClass: {
			type: String,
			default: ''
		},
		...uni.$uv?.props?.transition
	}
}
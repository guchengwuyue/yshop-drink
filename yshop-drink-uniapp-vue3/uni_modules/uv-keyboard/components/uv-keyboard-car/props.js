export default {
	props: {
		// 是否打乱键盘按键的顺序
		random: {
			type: Boolean,
			default: false
		},
		// 输入一个中文后，是否自动切换到英文
		autoChange: {
			type: Boolean,
			default: false
		},
		// 被禁用的键
		disKeys: {
			type: Array,
			default: ()=>[]
		},
		// 是否自定义abc
		customabc: {
			type: Boolean,
			default: false
		}
	}
}
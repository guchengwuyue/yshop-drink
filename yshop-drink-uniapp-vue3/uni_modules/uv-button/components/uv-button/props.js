export default {
	props: {
		// 是否细边框
		hairline: {
			type: Boolean,
			default: true
		},
		// 按钮的预置样式，info，primary，error，warning，success
		type: {
			type: String,
			default: 'info'
		},
		// 按钮尺寸，large，normal，small，mini
		size: {
			type: String,
			default: 'normal'
		},
		// 按钮形状，circle（两边为半圆），square（带圆角）
		shape: {
			type: String,
			default: 'square'
		},
		// 按钮是否镂空
		plain: {
			type: Boolean,
			default: false
		},
		// 是否禁止状态
		disabled: {
			type: Boolean,
			default: false
		},
		// 是否加载中
		loading: {
			type: Boolean,
			default: false
		},
		// 加载中提示文字
		loadingText: {
			type: [String, Number],
			default: ''
		},
		// 加载状态图标类型
		loadingMode: {
			type: String,
			default: 'spinner'
		},
		// 加载图标大小
		loadingSize: {
			type: [String, Number],
			default: 14
		},
		// 开放能力，具体请看uniapp稳定关于button组件部分说明
		// https://uniapp.dcloud.io/component/button
		openType: {
			type: String,
			default: ''
		},
		// 用于 <form> 组件，点击分别会触发 <form> 组件的 submit/reset 事件
		// 取值为submit（提交表单），reset（重置表单）
		formType: {
			type: String,
			default: ''
		},
		// 打开 APP 时，向 APP 传递的参数，open-type=launchApp时有效
		// 只微信小程序、QQ小程序有效
		appParameter: {
			type: String,
			default: ''
		},
		// 指定是否阻止本节点的祖先节点出现点击态，微信小程序有效
		hoverStopPropagation: {
			type: Boolean,
			default: true
		},
		// 指定返回用户信息的语言，zh_CN 简体中文，zh_TW 繁体中文，en 英文。只微信小程序有效
		lang: {
			type: String,
			default: 'en'
		},
		// 会话来源，open-type="contact"时有效。只微信小程序有效
		sessionFrom: {
			type: String,
			default: ''
		},
		// 会话内消息卡片标题，open-type="contact"时有效
		// 默认当前标题，只微信小程序有效
		sendMessageTitle: {
			type: String,
			default: ''
		},
		// 会话内消息卡片点击跳转小程序路径，open-type="contact"时有效
		// 默认当前分享路径，只微信小程序有效
		sendMessagePath: {
			type: String,
			default: ''
		},
		// 会话内消息卡片图片，open-type="contact"时有效
		// 默认当前页面截图，只微信小程序有效
		sendMessageImg: {
			type: String,
			default: ''
		},
		// 是否显示会话内消息卡片，设置此参数为 true，用户进入客服会话会在右下角显示"可能要发送的小程序"提示，
		// 用户点击后可以快速发送小程序消息，open-type="contact"时有效
		showMessageCard: {
			type: Boolean,
			default: true
		},
		// 额外传参参数，用于小程序的data-xxx属性，通过target.dataset.name获取
		dataName: {
			type: String,
			default: ''
		},
		// 节流，一定时间内只能触发一次
		throttleTime: {
			type: [String, Number],
			default: 0
		},
		// 按住后多久出现点击态，单位毫秒
		hoverStartTime: {
			type: [String, Number],
			default: 0
		},
		// 手指松开后点击态保留时间，单位毫秒
		hoverStayTime: {
			type: [String, Number],
			default: 200
		},
		// 按钮文字，之所以通过props传入，是因为slot传入的话
		// nvue中无法控制文字的样式
		text: {
			type: [String, Number],
			default: ''
		},
		// 按钮图标
		icon: {
			type: String,
			default: ''
		},
		// 按钮图标大小
		iconSize: {
			type: [String, Number],
			default: ''
		},
		// 按钮图标颜色
		iconColor: {
			type: String,
			default: '#000000'
		},
		// 按钮颜色，支持传入linear-gradient渐变色
		color: {
			type: String,
			default: ''
		},
		// 自定义按钮文本样式
		customTextStyle: {
			type: [Object,String],
			default: ()=>{}
		},
		...uni.$uv?.props?.button
	}
}

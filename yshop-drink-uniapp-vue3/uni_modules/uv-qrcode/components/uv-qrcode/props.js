export default {
	props: {
		//二维码内容
		value: {
			type: [String, Number]
		},
		//选项
		options: {
			type: Object,
			default: () => {
				return {};
			}
		},
		//二维码大小
		size: {
			type: [String, Number],
			default: 200
		},
		//导出的文件类型
		fileType: {
			type: String,
			default: 'png'
		},
		//是否初始化组件后就开始生成
		start: {
			type: Boolean,
			default: true
		},
		//是否数据发生改变自动重绘
		auto: {
			type: Boolean,
			default: true
		},
		//隐藏组件
		hide: {
			type: Boolean,
			default: false
		},
		/**
		 * canvas 类型，微信小程序默认使用2d，非2d微信官方已放弃维护，问题比较多
		 * 注意：微信小程序type2d手机上正常，PC上微信内打开小程序toDataURL报错，看后期微信官方团队会不会做兼容，不兼容的话只能在自行判断在PC使用非2d，或者直接提示用户请在手机上操作，微信团队的海报中心小程序就是这么做的
		 */
		type: {
			type: String,
			default: () => {
				// #ifdef MP-WEIXIN
				return '2d';
				// #endif
				// #ifndef MP-WEIXIN
				return 'normal';
				// #endif
			}
		},
		//队列绘制，主要针对NVue端
		queue: {
			type: Boolean,
			default: false
		},
		//是否队列加载图片，可减少canvas发起的网络资源请求，节省服务器资源
		isQueueLoadImage: {
			type: Boolean,
			default: false
		},
		//loading态
		loading: {
			type: Boolean,
			default: undefined
		},
		//H5保存即自动下载（在支持的环境下），默认false为仅弹层提示用户需要长按图片保存，不会自动下载
		h5SaveIsDownload: {
			type: Boolean,
			default: false
		},
		//H5下载名称
		h5DownloadName: {
			type: String,
			default: 'uvQRCode'
		},
		// H5保存二维码时候是否显示提示
		h5SaveTip: {
			type: Boolean,
			default: true
		}
	}
}
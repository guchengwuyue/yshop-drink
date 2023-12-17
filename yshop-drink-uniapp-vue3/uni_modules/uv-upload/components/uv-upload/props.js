export default {
	props: {
		// 接受的文件类型, 可选值为all media image file video
		accept: {
			type: String,
			default: 'image'
		},
		// 	图片或视频拾取模式，当accept为image类型时设置capture可选额外camera可以直接调起摄像头
		capture: {
			type: [String, Array],
			default: () => ['album', 'camera']
		},
		// 当accept为video时生效，是否压缩视频，默认为true
		compressed: {
			type: Boolean,
			default: true
		},
		// 当accept为video时生效，可选值为back或front
		camera: {
			type: String,
			default: 'back'
		},
		// 当accept为video时生效，拍摄视频最长拍摄时间，单位秒
		maxDuration: {
			type: Number,
			default: 60
		},
		// 上传区域的图标，只能内置图标
		uploadIcon: {
			type: String,
			default: 'camera-fill'
		},
		// 上传区域的图标的颜色，默认
		uploadIconColor: {
			type: String,
			default: '#D3D4D6'
		},
		// 是否开启文件读取前事件
		useBeforeRead: {
			type: Boolean,
			default: false
		},
		// 读取后的处理函数
		afterRead: {
			type: Function,
			default: null
		},
		// 读取前的处理函数
		beforeRead: {
			type: Function,
			default: null
		},
		// 是否开启图片预览功能
		previewFullImage: {
			type: Boolean,
			default: true
		},
		// 是否开启视频预览功能
		previewFullVideo: {
			type: Boolean,
			default: true
		},
		// 最大上传数量
		maxCount: {
			type: [String, Number],
			default: 52
		},
		// 是否禁用
		disabled: {
			type: Boolean,
			default: false
		},
		// 预览上传的图片时的裁剪模式，和image组件mode属性一致
		imageMode: {
			type: String,
			default: 'aspectFill'
		},
		// 标识符，可以在回调函数的第二项参数中获取
		name: {
			type: String,
			default: ''
		},
		// 所选的图片的尺寸, 可选值为original compressed
		sizeType: {
			type: Array,
			default: () => ['original', 'compressed']
		},
		// 是否开启图片多选，部分安卓机型不支持
		multiple: {
			type: Boolean,
			default: false
		},
		// 是否展示删除按钮
		deletable: {
			type: Boolean,
			default: true
		},
		// 文件大小限制，单位为byte
		maxSize: {
			type: [String, Number],
			default: Number.MAX_VALUE
		},
		// 显示已上传的文件列表
		fileList: {
			type: Array,
			default: () => []
		},
		// 上传区域的提示文字
		uploadText: {
			type: String,
			default: ''
		},
		// 内部预览图片区域和选择图片按钮的区域宽度
		width: {
			type: [String, Number],
			default: 80
		},
		// 内部预览图片区域和选择图片按钮的区域高度
		height: {
			type: [String, Number],
			default: 80
		},
		// 是否在上传完成后展示预览图
		previewImage: {
			type: Boolean,
			default: true
		},
		...uni.$uv?.props?.upload
	}
}
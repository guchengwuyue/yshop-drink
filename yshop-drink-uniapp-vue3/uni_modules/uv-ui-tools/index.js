// 全局挂载引入http相关请求拦截插件
import Request from './libs/luch-request'

// 引入全局mixin
import mixin from './libs/mixin/mixin.js'
// 小程序特有的mixin
import mpMixin from './libs/mixin/mpMixin.js'
// #ifdef MP
import mpShare from '@/uni_modules/uv-ui-tools/libs/mixin/mpShare.js'
// #endif

// 路由封装
import route from './libs/util/route.js'
// 公共工具函数
import * as index from './libs/function/index.js'
// 防抖方法
import debounce from './libs/function/debounce.js'
// 节流方法
import throttle from './libs/function/throttle.js'
// 规则检验
import * as test from './libs/function/test.js'

// 颜色渐变相关,colorGradient-颜色渐变,hexToRgb-十六进制颜色转rgb颜色,rgbToHex-rgb转十六进制
import * as colorGradient from './libs/function/colorGradient.js'

// 配置信息
import config from './libs/config/config.js'
// 平台
import platform from './libs/function/platform'

const $uv = {
	route,
	config,
	test,
	date: index.timeFormat, // 另名date
	...index,
	colorGradient: colorGradient.colorGradient,
	hexToRgb: colorGradient.hexToRgb,
	rgbToHex: colorGradient.rgbToHex,
	colorToRgba: colorGradient.colorToRgba,
	http: new Request(),
	debounce,
	throttle,
	platform,
	mixin,
	mpMixin
}
uni.$uv = $uv;
const install = (Vue,options={}) => {
		// #ifndef APP-NVUE
		const cloneMixin = index.deepClone(mixin);
		delete cloneMixin?.props?.customClass;
		delete cloneMixin?.props?.customStyle;
		Vue.mixin(cloneMixin);
		// #ifdef MP
		if(options.mpShare){
			Vue.mixin(mpShare);
		}
		// #endif
		// #endif
		// #ifdef VUE2
		// 时间格式化，同时两个名称，date和timeFormat
		Vue.filter('timeFormat', (timestamp, format) => uni.$uv.timeFormat(timestamp, format));
		Vue.filter('date', (timestamp, format) => uni.$uv.timeFormat(timestamp, format));
		// 将多久以前的方法，注入到全局过滤器
		Vue.filter('timeFrom', (timestamp, format) => uni.$uv.timeFrom(timestamp, format));
		// 同时挂载到uni和Vue.prototype中
		// #ifndef APP-NVUE
		// 只有vue，挂载到Vue.prototype才有意义，因为nvue中全局Vue.prototype和Vue.mixin是无效的
		Vue.prototype.$uv = $uv;
		// #endif
		// #endif
		// #ifdef VUE3
		Vue.config.globalProperties.$uv = $uv;
		// #endif
}
export default {
	install
}
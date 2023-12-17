import * as index from '../function/index.js';
import * as test from '../function/test.js';
export default {
	// 定义每个组件都可能需要用到的外部样式以及类名
	props: {
		// 每个组件都有的父组件传递的样式，可以为字符串或者对象形式
		customStyle: {
			type: [Object, String],
			default: () => ({})
		},
		customClass: {
			type: String,
			default: ''
		},
		// 跳转的页面路径
		url: {
			type: String,
			default: ''
		},
		// 页面跳转的类型
		linkType: {
			type: String,
			default: 'navigateTo'
		}
	},
	data() {
		return {}
	},
	onLoad() {
		// getRect挂载到$uv上，因为这方法需要使用in(this)，所以无法把它独立成一个单独的文件导出
		this.$uv.getRect = this.$uvGetRect
	},
	created() {
		// 组件当中，只有created声明周期，为了能在组件使用，故也在created中将方法挂载到$uv
		this.$uv.getRect = this.$uvGetRect
	},
	computed: {
		$uv() {
			return {
				...index,
				test,
				unit: uni?.$uv?.config?.unit
			}
		},
		/**
		 * 生成bem规则类名
		 * 由于微信小程序，H5，nvue之间绑定class的差异，无法通过:class="[bem()]"的形式进行同用
		 * 故采用如下折中做法，最后返回的是数组（一般平台）或字符串（支付宝和字节跳动平台），类似['a', 'b', 'c']或'a b c'的形式
		 * @param {String} name 组件名称
		 * @param {Array} fixed 一直会存在的类名
		 * @param {Array} change 会根据变量值为true或者false而出现或者隐藏的类名
		 * @returns {Array|string}
		 */
		bem() {
			return function(name, fixed, change) {
				// 类名前缀
				const prefix = `uv-${name}--`
				const classes = {}
				if (fixed) {
					fixed.map((item) => {
						// 这里的类名，会一直存在
						classes[prefix + this[item]] = true
					})
				}
				if (change) {
					change.map((item) => {
						// 这里的类名，会根据this[item]的值为true或者false，而进行添加或者移除某一个类
						this[item] ? (classes[prefix + item] = this[item]) : (delete classes[prefix + item])
					})
				}
				return Object.keys(classes)
					// 支付宝，头条小程序无法动态绑定一个数组类名，否则解析出来的结果会带有","，而导致失效
					// #ifdef MP-ALIPAY || MP-TOUTIAO || MP-LARK || MP-BAIDU
					.join(' ')
				// #endif
			}
		}
	},
	methods: {
		// 跳转某一个页面
		openPage(urlKey = 'url') {
			const url = this[urlKey]
			if (url) {
				// 执行类似uni.navigateTo的方法
				uni[this.linkType]({
					url
				})
			}
		},
		// 查询节点信息
		// 目前此方法在支付宝小程序中无法获取组件跟接点的尺寸，为支付宝的bug(2020-07-21)
		// 解决办法为在组件根部再套一个没有任何作用的view元素
		$uvGetRect(selector, all) {
			return new Promise((resolve) => {
				uni.createSelectorQuery()
					.in(this)[all ? 'selectAll' : 'select'](selector)
					.boundingClientRect((rect) => {
						if (all && Array.isArray(rect) && rect.length) {
							resolve(rect)
						}
						if (!all && rect) {
							resolve(rect)
						}
					})
					.exec()
			})
		},
		getParentData(parentName = '') {
			// 避免在created中去定义parent变量
			if (!this.parent) this.parent = {}
			// 这里的本质原理是，通过获取父组件实例(也即类似uv-radio的父组件uv-radio-group的this)
			// 将父组件this中对应的参数，赋值给本组件(uv-radio的this)的parentData对象中对应的属性
			// 之所以需要这么做，是因为所有端中，头条小程序不支持通过this.parent.xxx去监听父组件参数的变化
			// 此处并不会自动更新子组件的数据，而是依赖父组件uv-radio-group去监听data的变化，手动调用更新子组件的方法去重新获取
			this.parent = this.$uv.$parent.call(this, parentName)
			if (this.parent.children) {
				// 如果父组件的children不存在本组件的实例，才将本实例添加到父组件的children中
				this.parent.children.indexOf(this) === -1 && this.parent.children.push(this)
			}
			if (this.parent && this.parentData) {
				// 历遍parentData中的属性，将parent中的同名属性赋值给parentData
				Object.keys(this.parentData).map((key) => {
					this.parentData[key] = this.parent[key]
				})
			}
		},
		// 阻止事件冒泡
		preventEvent(e) {
			e && typeof(e.stopPropagation) === 'function' && e.stopPropagation()
		},
		// 空操作
		noop(e) {
			this.preventEvent(e)
		}
	},
	onReachBottom() {
		uni.$emit('uvOnReachBottom')
	},
	beforeDestroy() {
		// 判断当前页面是否存在parent和chldren，一般在checkbox和checkbox-group父子联动的场景会有此情况
		// 组件销毁时，移除子组件在父组件children数组中的实例，释放资源，避免数据混乱
		if (this.parent && test.array(this.parent.children)) {
			// 组件销毁时，移除父组件中的children数组中对应的实例
			const childrenList = this.parent.children
			childrenList.map((child, index) => {
				// 如果相等，则移除
				if (child === this) {
					childrenList.splice(index, 1)
				}
			})
		}
	},
	// 兼容vue3
	unmounted() {
		if (this.parent && test.array(this.parent.children)) {
			// 组件销毁时，移除父组件中的children数组中对应的实例
			const childrenList = this.parent.children
			childrenList.map((child, index) => {
				// 如果相等，则移除
				if (child === this) {
					childrenList.splice(index, 1)
				}
			})
		}
	}
}
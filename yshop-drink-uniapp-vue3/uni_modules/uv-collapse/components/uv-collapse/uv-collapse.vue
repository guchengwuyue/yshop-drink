<template>
	<view class="uv-collapse">
		<uv-line v-if="border"></uv-line>
		<slot />
	</view>
</template>

<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	import props from './props.js';
	/**
	 * collapse 折叠面板 
	 * @description 通过折叠面板收纳内容区域
	 * @tutorial https://www.uvui.cn/components/collapse.html
	 * @property {String | Number | Array}	value		当前展开面板的name，非手风琴模式：[<string | number>]，手风琴模式：string | number
	 * @property {Boolean}					accordion	是否手风琴模式（ 默认 false ）
	 * @property {Boolean}					border		是否显示外边框 ( 默认 true ）
	 * @event {Function}	change 		当前激活面板展开时触发(如果是手风琴模式，参数activeNames类型为String，否则为Array)
	 * @example <uv-collapse></uv-collapse>
	 */
	export default {
		name: "uv-collapse",
		mixins: [mpMixin, mixin, props],
		watch: {
			needInit() {
				this.init()
			},
			// 当父组件需要子组件需要共享的参数发生了变化，手动通知子组件
			parentData() {
				if (this.children.length) {
					this.children.map(child => {
						// 判断子组件(uv-checkbox)如果有updateParentData方法的话，就就执行(执行的结果是子组件重新从父组件拉取了最新的值)
						typeof(child.updateParentData) === 'function' && child.updateParentData()
					})
				}
			}
		},
		created() {
			this.children = []
		},
		computed: {
			needInit() {
				// 通过computed，同时监听accordion和value值的变化
				// 再通过watch去执行init()方法，进行再一次的初始化
				return [this.accordion, this.value]
			}
		},
		methods: {
			// 重新初始化一次内部的所有子元素
			init() {
				this.children.map(child => {
					child.init()
				})
			},
			/**
			 * collapse-item被点击时触发，由collapse统一处理各子组件的状态
			 * @param {Object} target 被操作的面板的实例
			 */
			onChange(target) {
				let changeArr = []
				this.children.map((child, index) => {
					// 如果是手风琴模式，将其他的折叠面板收起来
					if (this.accordion) {
						child.expanded = child === target ? !target.expanded : false
						child.setContentAnimate()
					} else {
						if(child === target) {
							child.expanded = !child.expanded
							child.setContentAnimate()
						}
					}
					// 拼接change事件中，数组元素的状态
					changeArr.push({
						// 如果没有定义name属性，则默认返回组件的index索引
						name: child.name || index,
						status: child.expanded ? 'open' : 'close'
					})
				})

				this.$emit('change', changeArr)
				this.$emit(target.expanded ? 'open' : 'close', target.name)
			}
		}
	}
</script>

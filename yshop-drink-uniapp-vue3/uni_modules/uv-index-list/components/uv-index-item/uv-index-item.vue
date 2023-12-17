<template>
	<!-- #ifdef APP-NVUE -->
	<cell ref="uv-index-item">
		<!-- #endif -->
		<view
			class="uv-index-item"
			:id="`uv-index-item-${id}`"
			:class="[`uv-index-item-${id}`]"
		>
			<slot />
		</view>
		<!-- #ifdef APP-NVUE -->
	</cell>
	<!-- #endif -->
</template>

<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	// #ifdef APP-NVUE
	// 由于weex为阿里的KPI业绩考核的产物，所以不支持百分比单位，这里需要通过dom查询组件的宽度
	const dom = uni.requireNativePlugin('dom')
	// #endif
	/**
	 * IndexItem 
	 * @description 
	 * @tutorial https://www.uvui.cn/components/indexList.html
	 * @property {String}
	 * @event {Function}
	 * @example
	 */
	export default {
		name: 'uv-index-item',
		mixins: [mpMixin, mixin],
		data() {
			return {
				// 本组件到滚动条顶部的距离
				top: 0,
				height: 0,
				id: ''
			}
		},
		created() {
			// 子组件uv-index-anchor的实例
			this.anchor = {}
		},
		mounted() {
			this.init()
		},
		methods: {
			init() {
				// 此处会活动父组件实例，并赋值给实例的parent属性
				this.getParentData('uv-index-list')
				if (!this.parent) {
					return this.$uv.error('uv-index-item必须要搭配uv-index-list组件使用')
				}
				this.$uv.sleep().then(() =>{
					this.getIndexItemRect().then(size => {
						// 由于对象的引用特性，此处会同时生效到父组件的children数组的本实例的top属性中，供父组件判断读取
						this.top = Math.ceil(size.top)
						this.height = Math.ceil(size.height)
					})
				})
			},
			getIndexItemRect() {
				return new Promise(resolve => {
					// #ifndef APP-NVUE
					this.$uvGetRect('.uv-index-item').then(size => {
						resolve(size)
					})
					// #endif

					// #ifdef APP-NVUE
					const ref = this.$refs['uv-index-item']
					dom.getComponentRect(ref, res => {
						resolve(res.size)
					})
					// #endif
				}) 
			}
		},
	}
</script>

<style lang="scss" scoped>
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
</style>

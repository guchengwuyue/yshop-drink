<template>
		<view 
			class="uv-vtabs-item"
			:id="`content_${index}`"
			ref="uv-vtabs-item"
		>
			<slot />
		</view>
</template>
<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js';
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js';
	// #ifdef APP-NVUE
	const dom = uni.requireNativePlugin('dom');
	// #endif
	export default {
		name: 'uv-vtabs-item',
		mixins: [mpMixin, mixin],
		props: {
			index: {
				type: [Number,String],
				default: 0
			}
		},
		data(){
			return {
				// 记录item的离顶部的距离
				top: 0,
				// 记录item的高度
				height: 0
				// 是否为联动
			}
		},
		mounted() {
			this.init();
		},
		methods: {
			async init(){
				this.getParentData('uv-vtabs');
				if (!this.parent) {
					return this.$uv.error('uv-vtabs必须要搭配uv-vtabs-item组件使用')
				}
				if(!this.parent.chain) return;
				await this.$uv.sleep();
				this.getItemRect().then(size=>{
					// 由于对象的引用特性，此处会同时生效到父组件的children数组的本实例的top属性中，供父组件判断读取
					this.top = size.top;
					this.height = size.height;
				});
			},
			getItemRect(){
				return new Promise(resolve => {
					// #ifndef APP-NVUE
					this.$uvGetRect('.uv-vtabs-item').then(size => {
						resolve(size)
					})
					// #endif
					// #ifdef APP-NVUE
					const ref = this.$refs['uv-vtabs-item']
					dom.getComponentRect(ref, res => {
						resolve(res.size)
					})
					// #endif
				})
			}
		}
	}
</script>

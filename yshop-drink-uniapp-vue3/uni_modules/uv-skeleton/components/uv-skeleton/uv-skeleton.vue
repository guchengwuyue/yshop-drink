<template>
	<view class="uv-skeleton">
		<view
		    class="uv-skeleton__wrapper"
		    ref="uv-skeleton__wrapper"
		    v-if="loading"
			style="display: flex; flex-direction: row;"
		>
			<view
			    class="uv-skeleton__wrapper__avatar"
			    v-if="avatar"
			    :class="[`uv-skeleton__wrapper__avatar--${avatarShape}`, animate && 'animate']"
			    :style="{
						height: $uv.addUnit(avatarSize),
						width: $uv.addUnit(avatarSize)
					}"
			></view>
			<view
			    class="uv-skeleton__wrapper__content"
			    ref="uv-skeleton__wrapper__content"
				style="flex: 1;"
			>
				<view
				    class="uv-skeleton__wrapper__content__title"
				    v-if="title"
				    :style="{
							width: uTitleWidth,
							height: $uv.addUnit(titleHeight),
						}"
				    :class="[animate && 'animate']"
				></view>
				<view
				    class="uv-skeleton__wrapper__content__rows"
				    :class="[animate && 'animate']"
				    v-for="(item, index) in rowsArray"
				    :key="index"
				    :style="{
							 width: item.width,
							 height: item.height,
							 marginTop: item.marginTop
						}"
				>
		
				</view>
			</view>
		</view>
		<slot v-else />
	</view>
</template>

<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	import props from './props.js';
	// #ifdef APP-NVUE
	// 由于weex为阿里的KPI业绩考核的产物，所以不支持百分比单位，这里需要通过dom查询组件的宽度
	const dom = uni.requireNativePlugin('dom')
	const animation = uni.requireNativePlugin('animation')
	// #endif
	/**
	 * Skeleton 骨架屏
	 * @description 骨架屏一般用于页面在请求远程数据尚未完成时，页面用灰色块预显示本来的页面结构，给用户更好的体验。
	 * @tutorial https://www.uvui.cn/components/skeleton.html
	 * @property {Boolean}					loading		是否显示骨架占位图，设置为false将会展示子组件内容 (默认 true )
	 * @property {Boolean}					animate		是否开启动画效果 (默认 true )
	 * @property {String | Number}			rows		段落占位图行数 (默认 0 )
	 * @property {String | Number | Array}	rowsWidth	段落占位图的宽度，可以为百分比，数值，带单位字符串等，可通过数组传入指定每个段落行的宽度 (默认 '100%' )
	 * @property {String | Number | Array}	rowsHeight	段落的高度 (默认 18 )
	 * @property {Boolean}					title		是否展示标题占位图 (默认 true )
	 * @property {String | Number}			titleWidth	标题的宽度 (默认 '50%' )
	 * @property {String | Number}			titleHeight	标题的高度 (默认 18 )
	 * @property {Boolean}					avatar		是否展示头像占位图 (默认 false )
	 * @property {String | Number}			avatarSize	头像占位图大小 (默认 32 )
	 * @property {String}					avatarShape	头像占位图的形状，circle-圆形，square-方形 (默认 'circle' )
	 * @example <uv-search placeholder="日照香炉生紫烟" v-model="keyword"></uv-search>
	 */
	export default {
		name: 'uv-skeleton',
		mixins: [mpMixin, mixin, props],
		data() {
			return {
				width: 0,
			}
		},
		watch: {
			loading() {
				this.getComponentWidth()
			}
		},
		computed: {
			rowsArray() {
				if (/%$/.test(this.rowsHeight)) {
					this.$uv.error('rowsHeight参数不支持百分比单位')
				}
				const rows = []
				for (let i = 0; i < this.rows; i++) {
					let item = {},
						// 需要预防超出数组边界的情况
						rowWidth = this.$uv.test.array(this.rowsWidth) ? (this.rowsWidth[i] || (i === this.row - 1 ? '70%' : '100%')) : i ===
						this.rows - 1 ? '70%' : this.rowsWidth,
						rowHeight = this.$uv.test.array(this.rowsHeight) ? (this.rowsHeight[i] || '18px') : this.rowsHeight
					// 如果有title占位图，第一个段落占位图的外边距需要大一些，如果没有title占位图，第一个段落占位图则无需外边距
					// 之所以需要这么做，是因为weex的无能，以提升性能为借口不支持css的一些伪类
					item.marginTop = !this.title && i === 0 ? 0 : this.title && i === 0 ? '20px' : '12px'
					// 如果设置的为百分比的宽度，转换为px值，因为nvue不支持百分比单位
					if (/%$/.test(rowWidth)) {
						// 通过parseInt提取出百分比单位中的数值部分，除以100得到百分比的小数值
						item.width = this.$uv.addUnit(this.width * parseInt(rowWidth) / 100)
					} else {
						item.width = this.$uv.addUnit(rowWidth)
					}
					item.height = this.$uv.addUnit(rowHeight)
					rows.push(item)
				}
				// console.log(rows);
				return rows
			},
			uTitleWidth() {
				let tWidth = 0
				if (/%$/.test(this.titleWidth)) {
					// 通过parseInt提取出百分比单位中的数值部分，除以100得到百分比的小数值
					tWidth = this.$uv.addUnit(this.width * parseInt(this.titleWidth) / 100)
				} else {
					tWidth = this.$uv.addUnit(this.titleWidth)
				}
				return this.$uv.addUnit(tWidth)
			},
			
		},
		mounted() {
			this.init()
		},
		methods: {
			init() {
				this.getComponentWidth()
				// #ifdef APP-NVUE
				this.loading && this.animate && this.setNvueAnimation()
				// #endif
			},
			async setNvueAnimation() {
				// #ifdef APP-NVUE
				// 为了让opacity:1的状态保持一定时间，这里做一个延时
				await this.$uv.sleep(500)
				const skeleton = this.$refs['uv-skeleton__wrapper'];
				skeleton && this.loading && this.animate && animation.transition(skeleton, {
					styles: {
						opacity: 0.5
					},
					duration: 600,
				}, () => {
					// 这里无需判断是否loading和开启动画状态，因为最终的状态必须达到opacity: 1，否则可能
					// 会停留在opacity: 0.5的状态中
					animation.transition(skeleton, {
						styles: {
							opacity: 1
						},
						duration: 600,
					}, () => {
						// 只有在loading中时，才执行动画
						this.loading && this.animate && this.setNvueAnimation()
					})
				})
				// #endif
			},
			// 获取组件的宽度
			async getComponentWidth() {
				// 延时一定时间，以获取dom尺寸
				await this.$uv.sleep(20)
				// #ifndef APP-NVUE
				this.$uvGetRect('.uv-skeleton__wrapper__content').then(size => {
					this.width = size.width
				})
				// #endif

				// #ifdef APP-NVUE
				const ref = this.$refs['uv-skeleton__wrapper__content']
				ref && dom.getComponentRect(ref, (res) => {
					this.width = res.size.width
				})
				// #endif
			}
		}
	}
</script>

<style lang="scss" scoped>
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';

	@mixin background {
		/* #ifdef APP-NVUE */
		background-color: #F1F2F4;
		/* #endif */
		/* #ifndef APP-NVUE */
		background: linear-gradient(90deg, #F1F2F4 25%, #e6e6e6 37%, #F1F2F4 50%);
		background-size: 400% 100%;
		/* #endif */
	}

	.uv-skeleton {
		flex: 1;
		
		&__wrapper {
			@include flex(row);
			
			&__avatar {
				@include background;
				margin-right: 15px;
			
				&--circle {
					border-radius: 100px;
				}
			
				&--square {
					border-radius: 4px;
				}
			}
			
			&__content {
				flex: 1;
			
				&__rows,
				&__title {
					@include background;
					border-radius: 3px;
				}
			}
		}
	}

	/* #ifndef APP-NVUE */
	.animate {
		animation: skeleton 1.8s ease infinite
	}

	@keyframes skeleton {
		0% {
			background-position: 100% 50%
		}

		100% {
			background-position: 0 50%
		}
	}

	/* #endif */
</style>

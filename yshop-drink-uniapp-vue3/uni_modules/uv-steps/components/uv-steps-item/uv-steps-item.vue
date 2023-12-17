<template>
	<view 
		class="uv-steps-item" 
		ref="uv-steps-item" 
		:class="[`uv-steps-item--${parentData.direction}`]"
		:style="[$uv.addStyle(customStyle)]"
		>
		<view 
			class="uv-steps-item__line" 
			v-if="index + 1 < childLength"
			:class="[`uv-steps-item__line--${parentData.direction}`]" 
			:style="[lineStyle]">
		</view>
		<view 
			:class="[
				'uv-steps-item__wrapper',
				`uv-steps-item__wrapper--${parentData.direction}`, 
				parentData.dot && `uv-steps-item__wrapper--${parentData.direction}--dot`
			]">
			<slot name="icon">
				<view 
					class="uv-steps-item__wrapper__dot" 
					v-if="parentData.dot" 
					:style="{
						backgroundColor: statusColor
					}">
				</view>
				<view 
					class="uv-steps-item__wrapper__icon" 
					v-else-if="parentData.activeIcon || parentData.inactiveIcon">
					<uv-icon 
						:name="index <= parentData.current ? parentData.activeIcon : parentData.inactiveIcon"
						:size="iconSize"
						:color="index <= parentData.current ? parentData.activeColor : parentData.inactiveColor">
					</uv-icon>
				</view>
				<view 
					v-else 
					:style="{
						backgroundColor: statusClass === 'process' ? parentData.activeColor : 'transparent',
						borderColor: statusColor
					}" 
					class="uv-steps-item__wrapper__circle">
					<text 
						v-if="statusClass === 'process' || statusClass === 'wait'"
						class="uv-steps-item__wrapper__circle__text" 
						:style="{
							color: index == parentData.current ? '#ffffff' : parentData.inactiveColor
						}">{{ index + 1}}</text>
					<uv-icon 
						v-else 
						:color="statusClass === 'error' ? 'error' : parentData.activeColor" 
						size="12"
						:name="statusClass === 'error' ? 'close' : 'checkmark'">
					</uv-icon>
				</view>
			</slot>
		</view>
		<view 
			:class="[
				'uv-steps-item__content',
				`uv-steps-item__content--${parentData.direction}`
			]"
			:style="[contentStyle]"
		>
			<slot name="title">
				<uv-text
					:text="title" 
					:type="parentData.current == index ? 'main' : 'content'" 
					lineHeight="20px"
					:size="parentData.current == index ? 14 : 13"
				></uv-text>
			</slot>
			<slot name="desc">
				<uv-text 
					:text="desc" 
					type="tips" 
					size="12"
				></uv-text>
			</slot>
		</view>
	</view>
</template>

<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	import props from './props.js';
	// #ifdef APP-NVUE
	const dom = uni.requireNativePlugin('dom')
	// #endif
	/**
	 * StepsItem 步骤条的子组件
	 * @description 本组件需要和uv-steps配合使用
	 * @tutorial https://www.uvui.cn/components/steps.html
	 * @property {String}			title			标题文字
	 * @property {String}			current			描述文本
	 * @property {String | Number}	iconSize		图标大小  (默认 17 )
	 * @property {Boolean}			error			当前步骤是否处于失败状态  (默认 false )
	 * @example <uv-steps current="0"><uv-steps-item title="已出库" desc="10:35" ></uv-steps-item></uv-steps>
	 */
	export default {
		name: 'uv-steps-item',
		mixins: [mpMixin, mixin, props],
		data() {
			return {
				index: 0,
				childLength: 0,
				showLine: false,
				size: {
					height: 0,
					width: 0
				},
				parentData: {
					direction: 'row',
					current: 0,
					activeColor: '',
					inactiveColor: '',
					activeIcon: '',
					inactiveIcon: '',
					dot: false
				}
			}
		},
		watch: {
			'parentData'(newValue, oldValue) {}
		},
		created() {
			this.init()
		},
		computed: {
			lineStyle() {
				const style = {}
				if (this.parentData.direction === 'row') {
					style.width = this.size.width + 'px'
					style.left = this.size.width / 2 + 'px'
				} else {
					style.height = this.size.height + 'px'
				}
				style.backgroundColor = this.parent.children?.[this.index + 1]?.error ? '#f56c6c' : this.index < this.parentData.current ? this.parentData.activeColor : this.parentData.inactiveColor
				return style
			},
			statusClass() {
				const {
					index,
					error
				} = this
				const {
					current
				} = this.parentData
				if (current == index) {
					return error === true ? 'error' : 'process'
				} else if (error) {
					return 'error'
				} else if (current > index) {
					return 'finish'
				} else {
					return 'wait'
				}
			},
			statusColor() {
				let color = ''
				switch (this.statusClass) {
					case 'finish':
						color = this.parentData.activeColor
						break
					case 'error':
						color = '#f56c6c'
						break
					case 'process':
						color = this.parentData.dot ? this.parentData.activeColor : 'transparent'
						break
					default:
						color = this.parentData.inactiveColor
						break
				}
				return color
			},
			contentStyle() {
				const style = {}
				if (this.parentData.direction === 'column') {
					style.marginLeft = this.parentData.dot ? '2px' : '6px'
					style.marginTop = this.parentData.dot ? '0px' : '6px'
				} else {
					style.marginTop = this.parentData.dot ? '2px' : '6px'
					style.marginLeft = this.parentData.dot ? '2px' : '6px'
				}
				return style
			}
		},
		mounted() {
			this.parent && this.parent.updateFromChild()
			this.$uv.sleep().then(() => {
				this.getStepsItemRect()
			})
		},
		methods: {
			init() {
				// 初始化数据
				this.updateParentData()
				if (!this.parent) {
					return this.$uv.error('uv-steps-item必须要搭配uv-steps组件使用')
				}
				this.index = this.parent.children.indexOf(this)
				this.childLength = this.parent.children.length
			},
			updateParentData() {
				// 此方法在mixin中
				this.getParentData('uv-steps')
			},
			// 父组件数据发生变化
			updateFromParent() {
				this.init()
			},
			// 获取组件的尺寸，用于设置横线的位置
			getStepsItemRect() {
				// #ifndef APP-NVUE
				this.$uvGetRect('.uv-steps-item').then(size => {
					this.size = size
				})
				// #endif
				// #ifdef APP-NVUE
				dom.getComponentRect(this.$refs['uv-steps-item'], res => {
					const {
						size
					} = res
					this.size = size
				})
				// #endif
			}
		}
	}
</script>

<style lang="scss" scoped>
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
	@import '@/uni_modules/uv-ui-tools/libs/css/color.scss';
	.uv-steps-item {
		flex: 1;
		@include flex;

		&--row {
			flex-direction: column;
			align-items: center;
			position: relative;
		}

		&--column {
			position: relative;
			flex-direction: row;
			justify-content: flex-start;
			padding-bottom: 5px;
		}

		&__wrapper {
			@include flex;
			justify-content: center;
			align-items: center;
			position: relative;
			background-color: #fff;

			&--column {
				width: 20px;
				height: 32px;

				&--dot {
					height: 20px;
					width: 20px;
				}
			}

			&--row {
				width: 32px;
				height: 20px;

				&--dot {
					width: 20px;
					height: 20px;
				}
			}

			&__circle {
				width: 20px;
				height: 20px;
				/* #ifndef APP-NVUE */
				box-sizing: border-box;
				flex-shrink: 0;
				/* #endif */
				border-radius: 100px;
				border-width: 1px;
				border-color: $uv-tips-color;
				border-style: solid;
				@include flex(row);
				align-items: center;
				justify-content: center;
				transition: background-color 0.3s;

				&__text {
					color: $uv-tips-color;
					font-size: 11px;
					@include flex(row);
					align-items: center;
					justify-content: center;
					text-align: center;
					line-height: 11px;
				}
			}

			&__dot {
				width: 10px;
				height: 10px;
				border-radius: 100px;
				background-color: $uv-content-color;
			}
		}

		&__content {
			@include flex;
			flex: 1;

			&--row {
				flex-direction: column;
				align-items: center;
			}

			&--column {
				flex-direction: column;
				margin-left: 6px;
			}
		}

		&__line {
			position: absolute;
			background: $uv-tips-color;

			&--row {
				top: 10px;
				height: 1px;
			}

			&--column {
				width: 1px;
				left: 10px;
			}
		}
	}
</style>

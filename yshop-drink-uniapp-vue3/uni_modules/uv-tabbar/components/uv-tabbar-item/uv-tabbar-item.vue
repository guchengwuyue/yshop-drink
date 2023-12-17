<template>
	<view
	    class="uv-tabbar-item"
	    :style="[$uv.addStyle(customStyle)]"
	    @tap="clickHandler"
	>
		<view class="uv-tabbar-item__icon">
			<uv-icon
			    v-if="icon"
			    :name="icon"
			    :color="isActive? parentData.activeColor : parentData.inactiveColor"
			    :size="iconSize? iconSize: parentData.iconSize"
			></uv-icon>
			<template v-else>
				<slot
				    v-if="isActive"
				    name="active-icon"
				/>
				<slot
				    v-else
				    name="inactive-icon"
				/>
			</template>
			<uv-badge
				absolute
				:offset="[0, dot ? '34rpx' : badge > 9 ? '14rpx' : '20rpx']"
			    :customStyle="badgeStyle"
			    :isDot="dot"
			    :value="badge || (dot ? 1 : null)"
			    :show="dot || badge > 0"
			></uv-badge>
		</view>
		
		<slot name="text">
			<text
			    class="uv-tabbar-item__text"
			    :style="{
					color: isActive? parentData.activeColor : parentData.inactiveColor
				}"
			>{{ text }}</text>
		</slot>
	</view>
</template>

<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	import props from './props.js';
	/**
	 * TabbarItem 底部导航栏子组件
	 * @description 此组件提供了自定义tabbar的能力。
	 * @tutorial https://www.uvui.cn/components/tabbar.html
	 * @property {String | Number}	name		item标签的名称，作为与uv-tabbar的value参数匹配的标识符
	 * @property {String}			icon		uvui内置图标或者绝对路径的图片
	 * @property {String | Number}	badge		右上角的角标提示信息
	 * @property {Boolean}			dot			是否显示圆点，将会覆盖badge参数（默认 false ）
	 * @property {String}			text		描述文本
	 * @property {Object | String}	badgeStyle	控制徽标的位置，对象或者字符串形式，可以设置top和right属性（默认 'top: 6px;right:2px;' ）
	 * @property {Object}			customStyle	定义需要用到的外部样式
	 * 
	 * @example <uv-tabbar :value="value2" :placeholder="false" @change="name => value2 = name" :fixed="false" :safeAreaInsetBottom="false"><uv-tabbar-item text="首页" icon="home" dot ></uv-tabbar-item></uv-tabbar>
	 */
	export default {
		name: 'uv-tabbar-item',
		mixins: [mpMixin, mixin, props],
		emits: ['click','change'],
		data() {
			return {
				isActive: false, // 是否处于激活状态
				parentData: {
					value: null,
					activeColor: '',
					inactiveColor: '',
					iconSize: 20
				}
			}
		},
		created() {
			this.init()
		},
		methods: {
			init() {
				// 支付宝小程序不支持provide/inject，所以使用这个方法获取整个父组件，在created定义，避免循环引用
				this.updateParentData()
				if (!this.parent) {
					this.$uv.error('uv-tabbar-item必须搭配uv-tabbar组件使用')
				}
				// 本子组件在uv-tabbar的children数组中的索引
				const index = this.parent.children.indexOf(this)
				// 判断本组件的name(如果没有定义name，就用index索引)是否等于父组件的value参数
				this.isActive = (this.name || index) === this.parentData.value
			},
			updateParentData() {
				// 此方法在mixin中
				this.getParentData('uv-tabbar')
			},
			// 此方法将会被父组件uv-tabbar调用
			updateFromParent() {
				// 重新初始化
				this.init()
			},
			clickHandler() {
				this.$nextTick(() => {
					const index = this.parent.children.indexOf(this)
					const name = this.name || index
					// 点击的item为非激活的item才发出change事件
					if (name !== this.parent.value) {
						this.parent.$emit('change', name)
					}
					this.$emit('click', name)
				})
			}
		},
	}
</script>

<style lang="scss" scoped>
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
	@import '@/uni_modules/uv-ui-tools/libs/css/color.scss';
	.uv-tabbar-item {
		@include flex(column);
		align-items: center;
		justify-content: center;
		flex: 1;
		
		&__icon {
			@include flex;
			position: relative;
			width: 150rpx;
			justify-content: center;
		}

		&__text {
			margin-top: 2px;
			font-size: 12px;
			color: $uv-content-color;
		}
	}

	/* #ifdef MP */
	// 由于小程序都使用shadow DOM形式实现，需要给影子宿主设置flex: 1才能让其撑开
	:host {
		flex: 1
	}
	/* #endif */
</style>

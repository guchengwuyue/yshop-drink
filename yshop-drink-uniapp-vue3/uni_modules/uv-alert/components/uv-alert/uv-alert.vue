<template>
	<uv-transition
	    mode="fade"
	    :show="show"
	>
		<view
		    class="uv-alert"
		    :class="[`uv-alert--${type}--${effect}`]"
		    @tap.stop="clickHandler"
		    :style="[$uv.addStyle(customStyle)]"
		>
			<view
			    class="uv-alert__icon"
			    v-if="showIcon"
			>
				<uv-icon
				    :name="iconName"
				    size="18"
				    :color="iconColor"
				></uv-icon>
			</view>
			<view
			    class="uv-alert__content"
			    :style="[{
					paddingRight: closable ? '20px' : 0
				}]"
			>
				<text
				    class="uv-alert__content__title"
				    v-if="title"
					:style="[{
						fontSize: $uv.addUnit(fontSize),
						textAlign: center ? 'center' : 'left'
					}]"
				    :class="[effect === 'dark' ? 'uv-alert__text--dark' : `uv-alert__text--${type}--light`]"
				>{{ title }}</text>
				<text
				    class="uv-alert__content__desc"
					v-if="description"
					:style="[{
						fontSize: $uv.addUnit(fontSize),
						textAlign: center ? 'center' : 'left'
					}]"
				    :class="[effect === 'dark' ? 'uv-alert__text--dark' : `uv-alert__text--${type}--light`]"
				>{{ description }}</text>
			</view>
			<view
			    class="uv-alert__close"
			    v-if="closable"
			    @tap.stop="closeHandler"
			>
				<uv-icon
				    name="close"
				    :color="iconColor"
				    size="15"
				></uv-icon>
			</view>
		</view>
	</uv-transition>
</template>

<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	import props from './props.js';
	/**
	 * Alert  警告提示
	 * @description 警告提示，展现需要关注的信息。
	 * @tutorial https://www.uvui.cn/components/alertTips.html
	 * 
	 * @property {String}			title       显示的文字 
	 * @property {String}			type        使用预设的颜色  （默认 'warning' ）
	 * @property {String}			description 辅助性文字，颜色比title浅一点，字号也小一点，可选  
	 * @property {Boolean}			closable    关闭按钮(默认为叉号icon图标)  （默认 false ）
	 * @property {Boolean}			showIcon    是否显示左边的辅助图标   （ 默认 false ）
	 * @property {String}			effect      多图时，图片缩放裁剪的模式  （默认 'light' ）
	 * @property {Boolean}			center		文字是否居中  （默认 false ）
	 * @property {String | Number}	fontSize    字体大小  （默认 14 ）
	 * @property {Object}			customStyle	定义需要用到的外部样式
	 * @event    {Function}        click       点击组件时触发
	 * @example  <uv-alert :title="title"  type = "warning" :closable="closable" :description = "description"></uv-alert>
	 */
	export default {
		name: 'uv-alert',
		mixins: [mpMixin, mixin, props],
		emits: ['click'],
		data() {
			return {
				show: true
			}
		},
		computed: {
			iconColor() {
				return this.effect === 'light' ? this.type : '#fff'
			},
			// 不同主题对应不同的图标
			iconName() {
				switch (this.type) {
					case 'success':
						return 'checkmark-circle-fill';
						break;
					case 'error':
						return 'close-circle-fill';
						break;
					case 'warning':
						return 'error-circle-fill';
						break;
					case 'info':
						return 'info-circle-fill';
						break;
					case 'primary':
						return 'more-circle-fill';
						break;
					default: 
						return 'error-circle-fill';
				}
			}
		},
		methods: {
			// 点击内容
			clickHandler() {
				this.$emit('click')
			},
			// 点击关闭按钮
			closeHandler() {
				this.show = false
			}
		}
	}
</script>

<style lang="scss" scoped>
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
	@import '@/uni_modules/uv-ui-tools/libs/css/color.scss';
	.uv-alert {
		position: relative;
		background-color: $uv-primary;
		padding: 8px 10px;
		@include flex(row);
		align-items: center;
		border-top-left-radius: 4px;
		border-top-right-radius: 4px;
		border-bottom-left-radius: 4px;
		border-bottom-right-radius: 4px;

		&--primary--dark {
			background-color: $uv-primary;
		}

		&--primary--light {
			background-color: #ecf5ff;
		}

		&--error--dark {
			background-color: $uv-error;
		}

		&--error--light {
			background-color: #FEF0F0;
		}

		&--success--dark {
			background-color: $uv-success;
		}

		&--success--light {
			background-color: #f5fff0;
		}

		&--warning--dark {
			background-color: $uv-warning;
		}

		&--warning--light {
			background-color: #FDF6EC;
		}

		&--info--dark {
			background-color: $uv-info;
		}

		&--info--light {
			background-color: #f4f4f5;
		}

		&__icon {
			margin-right: 5px;
		}

		&__content {
			@include flex(column);
			flex: 1;

			&__title {
				color: $uv-main-color;
				font-size: 14px;
				font-weight: bold;
				color: #fff;
				margin-bottom: 2px;
			}

			&__desc {
				color: $uv-main-color;
				font-size: 14px;
				flex-wrap: wrap;
				color: #fff;
			}
		}

		&__title--dark,
		&__desc--dark {
			color: #FFFFFF;
		}

		&__text--primary--light,
		&__text--primary--light {
			color: $uv-primary;
		}

		&__text--success--light,
		&__text--success--light {
			color: $uv-success;
		}

		&__text--warning--light,
		&__text--warning--light {
			color: $uv-warning;
		}

		&__text--error--light,
		&__text--error--light {
			color: $uv-error;
		}

		&__text--info--light,
		&__text--info--light {
			color: $uv-info;
		}

		&__close {
			position: absolute;
			top: 11px;
			right: 10px;
		}
	}
</style>

<template>
	<view class="uv-cell" :class="[customClass]" :style="[$uv.addStyle(customStyle)]"
		:hover-class="(!disabled && (clickable || isLink)) ? 'uv-cell--clickable' : ''" :hover-stay-time="250"
		@click="clickHandler">
		<view class="uv-cell__body" 
			:class="[ center && 'uv-cell--center', size === 'large' && 'uv-cell__body--large']"
			:style="[cellStyle]"
		>
			<view class="uv-cell__body__content">
				<view class="uv-cell__left-icon-wrap">
					<slot name="icon">
						<uv-icon v-if="icon" :name="icon" :custom-style="iconStyle" :size="size === 'large' ? 22 : 18"></uv-icon>
					</slot>
				</view>
				<view class="uv-cell__title">
					<slot name="title">
						<text v-if="title" class="uv-cell__title-text" :style="[titleTextStyle]"
							:class="[disabled && 'uv-cell--disabled', size === 'large' && 'uv-cell__title-text--large']">{{ title }}</text>
					</slot>
					<slot name="label">
						<text class="uv-cell__label" v-if="label"
							:class="[disabled && 'uv-cell--disabled', size === 'large' && 'uv-cell__label--large']">{{ label }}</text>
					</slot>
				</view>
			</view>
			<slot name="value">
				<text class="uv-cell__value"
					:class="[disabled && 'uv-cell--disabled', size === 'large' && 'uv-cell__value--large']"
					v-if="!$uv.test.empty(value)">{{ value }}</text>
			</slot>
			<view class="uv-cell__right-icon-wrap" v-if="$slots['right-icon'] || isLink"
				:class="[`uv-cell__right-icon-wrap--${arrowDirection}`]">
				<slot name="right-icon" v-if="$slots['right-icon']">
				</slot>
				<uv-icon v-else :name="rightIcon" :custom-style="rightIconStyle" :color="disabled ? '#c8c9cc' : 'info'"
					:size="size === 'large' ? 18 : 16"></uv-icon>
			</view>
		</view>
		<uv-line v-if="border"></uv-line>
	</view>
</template>

<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	import props from './props.js';
	/**
	 * cell  单元格
	 * @description cell单元格一般用于一组列表的情况，比如个人中心页，设置页等。
	 * @tutorial https://www.uvui.cn/components/cell.html
	 * @property {String | Number}	title			标题
	 * @property {String | Number}	label			标题下方的描述信息
	 * @property {String | Number}	value			右侧的内容
	 * @property {String}			icon			左侧图标名称，或者图片链接(本地文件建议使用绝对地址)
	 * @property {Boolean}			disabled		是否禁用cell	
	 * @property {Boolean}			border			是否显示下边框 (默认 true )
	 * @property {Boolean}			center			内容是否垂直居中(主要是针对右侧的value部分) (默认 false )
	 * @property {String}			url				点击后跳转的URL地址
	 * @property {String}			linkType		链接跳转的方式，内部使用的是uvui封装的route方法，可能会进行拦截操作 (默认 'navigateTo' )
	 * @property {Boolean}			clickable		是否开启点击反馈(表现为点击时加上灰色背景) （默认 false ） 
	 * @property {Boolean}			isLink			是否展示右侧箭头并开启点击反馈 （默认 false ）
	 * @property {Boolean}			required		是否显示表单状态下的必填星号(此组件可能会内嵌入input组件) （默认 false ）
	 * @property {String}			rightIcon		右侧的图标箭头 （默认 'arrow-right'）
	 * @property {String}			arrowDirection	右侧箭头的方向，可选值为：left，up，down
	 * @property {Object | String}			rightIconStyle	右侧箭头图标的样式
	 * @property {Object | String}			titleStyle		标题的样式
	 * @property {Object | String}			iconStyle		左侧图标样式
	 * @property {String}			size			单位元的大小，可选值为 large，normal，mini 
	 * @property {Boolean}			stop			点击cell是否阻止事件传播 (默认 true )
	 * @property {Object | String}  cellStyle 单元格自定义样式
	 * @property {Object}			customStyle		定义需要用到的外部样式
	 * 
	 * @event {Function}			click			点击cell列表时触发
	 * @example 该组件需要搭配cell-group组件使用，见官方文档示例
	 */
	export default {
		name: 'uv-cell',
		emits: ['click'],
		mixins: [mpMixin, mixin, props],
		computed: {
			titleTextStyle() {
				return this.$uv.addStyle(this.titleStyle)
			}
		},
		methods: {
			// 点击cell
			clickHandler(e) {
				if (this.disabled) return
				this.$emit('click', {
					name: this.name
				})
				// 如果配置了url(此props参数通过mixin引入)参数，跳转页面
				this.openPage()
				// 是否阻止事件传播
				this.stop && this.preventEvent(e)
			},
		}
	}
</script>

<style lang="scss" scoped>
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
	@import '@/uni_modules/uv-ui-tools/libs/css/color.scss';
	$uv-cell-padding: 10px 15px !default;
	$uv-cell-font-size: 15px !default;
	$uv-cell-line-height: 24px !default;
	$uv-cell-color: $uv-main-color !default;
	$uv-cell-icon-size: 16px !default;
	$uv-cell-title-font-size: 15px !default;
	$uv-cell-title-line-height: 22px !default;
	$uv-cell-title-color: $uv-main-color !default;
	$uv-cell-label-font-size: 12px !default;
	$uv-cell-label-color: $uv-tips-color !default;
	$uv-cell-label-line-height: 18px !default;
	$uv-cell-value-font-size: 14px !default;
	$uv-cell-value-color: $uv-content-color !default;
	$uv-cell-clickable-color: $uv-bg-color !default;
	$uv-cell-disabled-color: #c8c9cc !default;
	$uv-cell-padding-top-large: 13px !default;
	$uv-cell-padding-bottom-large: 13px !default;
	$uv-cell-value-font-size-large: 15px !default;
	$uv-cell-label-font-size-large: 14px !default;
	$uv-cell-title-font-size-large: 16px !default;
	$uv-cell-left-icon-wrap-margin-right: 4px !default;
	$uv-cell-right-icon-wrap-margin-left: 4px !default;
	$uv-cell-title-flex: 1 !default;
	$uv-cell-label-margin-top: 5px !default;
	.uv-cell {
		&__body {
			@include flex();
			/* #ifndef APP-NVUE */
			box-sizing: border-box;
			/* #endif */
			padding: $uv-cell-padding;
			font-size: $uv-cell-font-size;
			color: $uv-cell-color;
			&__content {
				@include flex(row);
				align-items: center;
				flex: 1;
			}
			&--large {
				padding-top: $uv-cell-padding-top-large;
				padding-bottom: $uv-cell-padding-bottom-large;
			}
		}
		&__left-icon-wrap,
		&__right-icon-wrap {
			@include flex();
			align-items: center;
			// height: $uv-cell-line-height;
			font-size: $uv-cell-icon-size;
		}
		&__left-icon-wrap {
			margin-right: $uv-cell-left-icon-wrap-margin-right;
		}
		&__right-icon-wrap {
			margin-left: $uv-cell-right-icon-wrap-margin-left;
			transition: transform 0.3s;
			&--up {
				transform: rotate(-90deg);
			}
			&--down {
				transform: rotate(90deg);
			}
		}
		&__title {
			flex: $uv-cell-title-flex;
			&-text {
				font-size: $uv-cell-title-font-size;
				line-height: $uv-cell-title-line-height;
				color: $uv-cell-title-color;
				&--large {
					font-size: $uv-cell-title-font-size-large;
				}
			}
		}
		&__label {
			margin-top: $uv-cell-label-margin-top;
			font-size: $uv-cell-label-font-size;
			color: $uv-cell-label-color;
			line-height: $uv-cell-label-line-height;
			&--large {
				font-size: $uv-cell-label-font-size-large;
			}
		}
		&__value {
			text-align: right;
			font-size: $uv-cell-value-font-size;
			line-height: $uv-cell-line-height;
			color: $uv-cell-value-color;
			&--large {
				font-size: $uv-cell-value-font-size-large;
			}
		}
		&--clickable {
			background-color: $uv-cell-clickable-color;
		}
		&--disabled {
			color: $uv-cell-disabled-color;
			/* #ifndef APP-NVUE */
			cursor: not-allowed;
			/* #endif */
		}
		&--center {
			align-items: center;
		}
	}
</style>
<template>
	<text
		v-if="show && ((Number(value) === 0 ? showZero : true) || isDot)"
		:class="[isDot ? 'uv-badge--dot' : 'uv-badge--not-dot', inverted && 'uv-badge--inverted', shape === 'horn' && 'uv-badge--horn', `uv-badge--${propsType}${inverted ? '--inverted' : ''}`]"
		:style="[$uv.addStyle(customStyle), badgeStyle]"
		class="uv-badge"
	>{{ isDot ? '' :showValue }}</text>
</template>

<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	import props from './props.js';
	/**
	 * badge 徽标数
	 * @description 该组件一般用于图标右上角显示未读的消息数量，提示用户点击，有圆点和圆包含文字两种形式。
	 * @tutorial https://www.uvui.cn/components/badge.html
	 * 
	 * @property {Boolean} 			isDot 		是否显示圆点 （默认 false ）
	 * @property {String | Number} 	value 		显示的内容
	 * @property {Boolean} 			show 		是否显示 （默认 true ）
	 * @property {String | Number} 	max 		最大值，超过最大值会显示 '{max}+'  （默认999）
	 * @property {String} 			type 		主题类型，error|warning|success|primary （默认 'error' ）
	 * @property {Boolean} 			showZero	当数值为 0 时，是否展示 Badge （默认 false ）
	 * @property {String} 			bgColor 	背景颜色，优先级比type高，如设置，type参数会失效
	 * @property {String} 			color 		字体颜色 （默认 '#ffffff' ）
	 * @property {String} 			shape 		徽标形状，circle-四角均为圆角，horn-左下角为直角 （默认 'circle' ）
	 * @property {String} 			numberType	设置数字的显示方式，overflow|ellipsis|limit  （默认 'overflow' ）
	 * @property {Array}} 			offset		设置badge的位置偏移，格式为 [x, y]，也即设置的为top和right的值，absolute为true时有效
	 * @property {Boolean} 			inverted	是否反转背景和字体颜色（默认 false ）
	 * @property {Boolean} 			absolute	是否绝对定位（默认 false ）
	 * @property {Object}			customStyle	定义需要用到的外部样式
	 * @example <uv-badge :type="type" :count="count"></uv-badge>
	 */
	export default {
		name: 'uv-badge',
		mixins: [mpMixin, mixin, props],
		computed: {
			// 是否将badge中心与父组件右上角重合
			boxStyle() {
				let style = {};
				return style;
			},
			// 整个组件的样式
			badgeStyle() {
				const style = {}
				if(this.color) {
					style.color = this.color
				}
				if (this.bgColor && !this.inverted) {
					style.backgroundColor = this.bgColor
				}
				if (this.absolute) {
					style.position = 'absolute'
					// 如果有设置offset参数
					if(this.offset.length) {
						// top和right分为为offset的第一个和第二个值，如果没有第二个值，则right等于top
						const top = this.offset[0]
						const right = this.offset[1] || top
						style.top = this.$uv.addUnit(top)
						style.right = this.$uv.addUnit(right)
					}
				}
				return style
			},
			showValue() {
				switch (this.numberType) {
					case "overflow":
						return Number(this.value) > Number(this.max) ? this.max + "+" : this.value
						break;
					case "ellipsis":
						return Number(this.value) > Number(this.max) ? "..." : this.value
						break;
					case "limit":
						return Number(this.value) > 999 ? Number(this.value) >= 9999 ?
							Math.floor(this.value / 1e4 * 100) / 100 + "w" : Math.floor(this.value /
								1e3 * 100) / 100 + "k" : this.value
						break;
					default:
						return Number(this.value)
				}
			},
			propsType(){
				return this.type || 'error'
			}
		}
	}
</script>

<style lang="scss" scoped>
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
	@import '@/uni_modules/uv-ui-tools/libs/css/color.scss';
	$uv-badge-primary: $uv-primary !default;
	$uv-badge-error: $uv-error !default;
	$uv-badge-success: $uv-success !default;
	$uv-badge-info: $uv-info !default;
	$uv-badge-warning: $uv-warning !default;
	$uv-badge-dot-radius: 100px !default;
	$uv-badge-dot-size: 8px !default;
	$uv-badge-dot-right: 4px !default;
	$uv-badge-dot-top: 0 !default;
	$uv-badge-text-font-size: 11px !default;
	$uv-badge-text-right: 10px !default;
	$uv-badge-text-padding: 2px 5px !default;
	$uv-badge-text-align: center !default;
	$uv-badge-text-color: #FFFFFF !default;

	.uv-badge {
		border-top-right-radius: $uv-badge-dot-radius;
		border-top-left-radius: $uv-badge-dot-radius;
		border-bottom-left-radius: $uv-badge-dot-radius;
		border-bottom-right-radius: $uv-badge-dot-radius;
		@include flex;
		line-height: $uv-badge-text-font-size;
		text-align: $uv-badge-text-align;
		font-size: $uv-badge-text-font-size;
		color: $uv-badge-text-color;

		&--dot {
			height: $uv-badge-dot-size;
			width: $uv-badge-dot-size;
		}
		
		&--inverted {
			font-size: 13px;
		}
		
		&--not-dot {
			padding: $uv-badge-text-padding;
		}

		&--horn {
			border-bottom-left-radius: 0;
		}

		&--primary {
			background-color: $uv-badge-primary;
		}
		
		&--primary--inverted {
			color: $uv-badge-primary;
		}

		&--error {
			background-color: $uv-badge-error;
		}
		
		&--error--inverted {
			color: $uv-badge-error;
		}

		&--success {
			background-color: $uv-badge-success;
		}
		
		&--success--inverted {
			color: $uv-badge-success;
		}

		&--info {
			background-color: $uv-badge-info;
		}
		
		&--info--inverted {
			color: $uv-badge-info;
		}

		&--warning {
			background-color: $uv-badge-warning;
		}
		
		&--warning--inverted {
			color: $uv-badge-warning;
		}
	}
</style>

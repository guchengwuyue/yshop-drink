<template>
	<view class="uv-calendar-item__weeks-box" :class="{
		'uv-calendar-item--disable':weeks.disable || (weeks.extraInfo&&weeks.extraInfo.disable),
		'uv-calendar-item--isDay':calendar.fullDate === weeks.fullDate && weeks.isDay && !multiple,
		'uv-calendar-item--checked':(calendar.fullDate === weeks.fullDate && !weeks.isDay && !multiple) ,
		'uv-calendar-item--before-checked':weeks.beforeRange,
		'uv-calendar-item--range': weeks.range,
		'uv-calendar-item--after-checked':weeks.afterRange,
		'uv-calendar-item--multiple':weeks.multiple
		}" :style="[itemBoxStyle]" @click="choiceDate(weeks)">
		<view class="uv-calendar-item__weeks-box-item">
			<text v-if="selected&&weeks.extraInfo&&weeks.extraInfo.badge" class="uv-calendar-item__weeks-box-circle"></text>
			<text class="uv-calendar-item__weeks-top-text" v-if="weeks.extraInfo&&weeks.extraInfo.topinfo" :style="[infoStyle('top')]">{{weeks.extraInfo&&weeks.extraInfo.topinfo}}</text>
			<text class="uv-calendar-item__weeks-box-text" :class="{
				'uv-calendar-item--isDay-text': weeks.isDay,
				'uv-calendar-item--isDay':calendar.fullDate === weeks.fullDate && weeks.isDay && !multiple,
				'uv-calendar-item--checked':calendar.fullDate === weeks.fullDate && !weeks.isDay && !multiple,
				'uv-calendar-item--before-checked':weeks.beforeRange,
				'uv-calendar-item--range': weeks.range,
				'uv-calendar-item--after-checked':weeks.afterRange,
				'uv-calendar-item--multiple':weeks.multiple,
				'uv-calendar-item--disable':weeks.disable || (weeks.extraInfo&&weeks.extraInfo.disable)
				}" :style="[itemBoxStyle]">{{weeks.date}}</text>
			<text v-if="!lunar&&!weeks.extraInfo && weeks.isDay" class="uv-calendar-item__weeks-lunar-text" :class="{
				'uv-calendar-item--isDay-text':weeks.isDay,
				'uv-calendar-item--isDay':calendar.fullDate === weeks.fullDate && weeks.isDay && !multiple,
				'uv-calendar-item--checked':calendar.fullDate === weeks.fullDate && !weeks.isDay && !multiple,
				'uv-calendar-item--before-checked':weeks.beforeRange,
				'uv-calendar-item--range': weeks.range,
				'uv-calendar-item--after-checked':weeks.afterRange,
				'uv-calendar-item--multiple':weeks.multiple
				}" :style="[itemBoxStyle]">{{todayText}}</text>
			<text v-if="lunar&&!weeks.extraInfo" class="uv-calendar-item__weeks-lunar-text" :class="{
				'uv-calendar-item--isDay-text':weeks.isDay,
				'uv-calendar-item--isDay':calendar.fullDate === weeks.fullDate && weeks.isDay && !multiple,
				'uv-calendar-item--checked':calendar.fullDate === weeks.fullDate && !weeks.isDay && !multiple,
				'uv-calendar-item--before-checked':weeks.beforeRange,
				'uv-calendar-item--range': weeks.range,
				'uv-calendar-item--after-checked':weeks.afterRange,
				'uv-calendar-item--multiple':weeks.multiple,
				'uv-calendar-item--disable':weeks.disable || (weeks.extraInfo&&weeks.extraInfo.disable)
				}" :style="[itemBoxStyle]">{{weeks.isDay ? todayText : (weeks.lunar.IDayCn === '初一'?weeks.lunar.IMonthCn:weeks.lunar.IDayCn)}}</text>
			<text v-if="weeks.extraInfo&&weeks.extraInfo.info" class="uv-calendar-item__weeks-lunar-text" :class="{
						'uv-calendar-item__weeks-lunar-text--equal': weeks.dateEqual
					}" :style="[infoStyle('bottom')]">{{weeks.extraInfo.info}}</text>
		</view>
	</view>
</template>
<script>
	import { colorGradient } from '@/uni_modules/uv-ui-tools/libs/function/colorGradient.js';
	import { initVueI18n } from '@dcloudio/uni-i18n'
	import i18nMessages from './i18n/index.js'
	const { t } = initVueI18n(i18nMessages)
	export default {
		emits: ['change'],
		props: {
			weeks: {
				type: Object,
				default () {
					return {}
				}
			},
			calendar: {
				type: Object,
				default: () => {
					return {}
				}
			},
			selected: {
				type: Array,
				default: () => {
					return []
				}
			},
			lunar: {
				type: Boolean,
				default: false
			},
			color: {
				type: String,
				default: '#3c9cff'
			},
			range: {
				type: Boolean,
				default: false
			},
			multiple: {
				type: Boolean,
				default: false
			}
		},
		computed: {
			todayText() {
				return t("uv-calender.today")
			},
			itemBoxStyle() {
				const style = {};
				if (this.multiple) { // 多选状态
					if (this.weeks.multiple) {
						style.backgroundColor = this.color;
						style.color = '#fff';
					} else if (this.weeks.isDay) {
						style.color = this.color;
					}
				} else if (this.range) { // 范围选择
					if (this.weeks.beforeRange || this.weeks.afterRange) {
						style.backgroundColor = this.color;
					} else if (this.weeks.range) {
						style.backgroundColor = colorGradient(this.color, '#ffffff', 100)[90]
						style.color = this.color;
						style.opacity = 0.8;
						style.borderRadius = 0;
					}
				} else {
					if (this.weeks.isDay) {
						style.color = this.color;
					}
					if (this.calendar.fullDate === this.weeks.fullDate) {
						style.backgroundColor = this.color;
						style.color = '#fff';
					}
				}
				return style;
			},
			infoStyle(val) {
				return val => {
					const style = {};
					if (!this.weeks.multiple) {
						if (val == 'top') {
							style.color = this.weeks.extraInfo.topinfoColor ? this.weeks.extraInfo.topinfoColor : '#606266';
						} else if (val == 'bottom') {
							style.color = this.weeks.extraInfo.infoColor ? this.weeks.extraInfo.infoColor : '#f56c6c';
						}
						if (this.weeks.range) {
							style.color = this.color;
						}
						if (this.calendar.fullDate === this.weeks.fullDate || this.weeks.beforeRange || this.weeks.afterRange) {
							style.color = this.multiple ? style.color : '#fff';
						}
					} else {
						style.color = '#fff';
					}
					return style;
				}
			}
		},
		methods: {
			choiceDate(weeks) {
				if (this.weeks.extraInfo && this.weeks.extraInfo.disable) return;
				this.$emit('change', weeks)
			}
		}
	}
</script>
<style lang="scss" scoped>
	@mixin flex($direction: row) {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: $direction;
	}
	$uv-font-size-base: 14px;
	$uv-text-color: #333;
	$uv-font-size-sm: 24rpx;
	$uv-error: #f56c6c !default;
	$uv-opacity-disabled: 0.3;
	$uv-text-color-disable: #c0c0c0;
	$uv-primary: #3c9cff !default;
	$info-height: 32rpx;
	.uv-calendar-item__weeks-box {
		flex: 1;
		@include flex(column);
		justify-content: center;
		align-items: center;
		border-radius: 4px;
	}
	.uv-calendar-item__weeks-top-text {
		height: $info-height;
		line-height: $info-height;
		font-size: $uv-font-size-sm;
	}
	.uv-calendar-item__weeks-box-text {
		font-size: $uv-font-size-base;
		color: $uv-text-color;
	}
	.uv-calendar-item__weeks-lunar-text {
		height: $info-height;
		line-height: $info-height;
		font-size: $uv-font-size-sm;
		color: $uv-text-color;
	}
	.uv-calendar-item__weeks-lunar-text--equal {
		/* #ifdef H5 */
		white-space: nowrap;
		transform: scale(.8);
		/* #endif */
		/* #ifndef H5 */
		font-size: 20rpx;
		/* #endif */
	}
	.uv-calendar-item__weeks-box-item {
		position: relative;
		@include flex(column);
		justify-content: center;
		align-items: center;
		width: 106rpx;
		height: 56px;
	}
	.uv-calendar-item__weeks-box-circle {
		position: absolute;
		top: 5px;
		right: 5px;
		width: 8px;
		height: 8px;
		border-radius: 8px;
		background-color: $uv-error;
	}
	.uv-calendar-item--disable {
		background-color: rgba(249, 249, 249, $uv-opacity-disabled);
		color: $uv-text-color-disable;
	}
	.uv-calendar-item--isDay-text {
		color: $uv-primary;
	}
	.uv-calendar-item--isDay {
		background-color: $uv-primary;
		color: #fff;
	}
	.uv-calendar-item--checked {
		background-color: $uv-primary;
		color: #fff;
		border-radius: 4px;
	}
	// .uv-calendar-item--range {
	// 	background-color: $uv-primary;
	// 	color: #fff;
	// }
	.uv-calendar-item--before-checked {
		color: #fff;
	}
	.uv-calendar-item--after-checked {
		color: #fff;
	}
	.uv-calendar-item--multiple {
		background-color: $uv-primary;
		color: #fff;
	}
</style>
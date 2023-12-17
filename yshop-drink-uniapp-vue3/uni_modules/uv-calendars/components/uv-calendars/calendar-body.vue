<template>
	<view class="uv-calendar-body">
		<view class="uv-calendar__header">
			<view class="uv-calendar__header-btn-box" @click.stop="pre">
				<view class="uv-calendar__header-btn uv-calendar--left"></view>
			</view>
			<picker mode="date" :value="getDate" fields="month" @change="bindDateChange">
				<text class="uv-calendar__header-text">{{ (nowDate.year||'') +' / '+( nowDate.month||'')}}</text>
			</picker>
			<view class="uv-calendar__header-btn-box" @click.stop="next">
				<view class="uv-calendar__header-btn uv-calendar--right"></view>
			</view>
			<text class="uv-calendar__backtoday" @click="backToday">{{todayText}}</text>
		</view>
		<view class="uv-calendar__box">
			<view v-if="showMonth" class="uv-calendar__box-bg">
				<text class="uv-calendar__box-bg-text">{{nowDate.month}}</text>
			</view>
			<view class="uv-calendar__weeks uv-calendar__weeks-week">
				<view class="uv-calendar__weeks-day">
					<text class="uv-calendar__weeks-day-text">{{SUNText}}</text>
				</view>
				<view class="uv-calendar__weeks-day">
					<text class="uv-calendar__weeks-day-text">{{monText}}</text>
				</view>
				<view class="uv-calendar__weeks-day">
					<text class="uv-calendar__weeks-day-text">{{TUEText}}</text>
				</view>
				<view class="uv-calendar__weeks-day">
					<text class="uv-calendar__weeks-day-text">{{WEDText}}</text>
				</view>
				<view class="uv-calendar__weeks-day">
					<text class="uv-calendar__weeks-day-text">{{THUText}}</text>
				</view>
				<view class="uv-calendar__weeks-day">
					<text class="uv-calendar__weeks-day-text">{{FRIText}}</text>
				</view>
				<view class="uv-calendar__weeks-day">
					<text class="uv-calendar__weeks-day-text">{{SATText}}</text>
				</view>
			</view>
			<view class="uv-calendar__weeks" v-for="(item,weekIndex) in weeks" :key="weekIndex">
				<view class="uv-calendar__weeks-item" v-for="(weeks,weeksIndex) in item" :key="weeksIndex">
					<calendar-item class="uv-calendar-item--hook" :weeks="weeks" :rangeInfoText="rangeInfoText(weeks)" :multiple="multiple" :range="range" :calendar="calendar" :selected="selected" :lunar="lunar" :color="color" @change="choiceDate"></calendar-item>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js';
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js';

	import CalendarItem from './calendar-item.vue';

	import { initVueI18n } from '@dcloudio/uni-i18n';
	import i18nMessages from './i18n/index.js';
	const { t } = initVueI18n(i18nMessages);
	export default {
		mixins: [mpMixin, mixin],
		components: {
			CalendarItem
		},
		props: {
			date: {
				type: [String,Array],
				default: ''
			},
			nowDate: {
				type: [String, Object],
				default: ''
			},
			weeks: {
				type: [Array, Object],
				default () {
					return []
				}
			},
			calendar: {
				type: Object,
				default () {
					return {}
				}
			},
			selected: {
				type: Array,
				default () {
					return []
				}
			},
			lunar: {
				type: Boolean,
				default: false
			},
			showMonth: {
				type: Boolean,
				default: true
			},
			color: {
				type: String,
				default: '#3c9cff'
			},
			startText: {
				type: String,
				default: '开始'
			},
			endText: {
				type: String,
				default: '结束'
			},
			range: {
				type: Boolean,
				default: false
			},
			multiple: {
				type: Boolean,
				default: false
			},
			// 是否允许日期范围的起止时间为同一天，mode = range时有效
			allowSameDay: {
				type: Boolean,
				default: false
			}
		},
		computed: {
			getDate() {
				return Array.isArray(this.date) ? this.date[0] : this.date;
			},
			/**
			 * for i18n
			 */
			todayText() {
				return t("uv-calender.today")
			},
			monText() {
				return t("uv-calender.MON")
			},
			TUEText() {
				return t("uv-calender.TUE")
			},
			WEDText() {
				return t("uv-calender.WED")
			},
			THUText() {
				return t("uv-calender.THU")
			},
			FRIText() {
				return t("uv-calender.FRI")
			},
			SATText() {
				return t("uv-calender.SAT")
			},
			SUNText() {
				return t("uv-calender.SUN")
			},
			rangeInfoText(weeks) {
				return weeks=> {
					if(this.allowSameDay && weeks.beforeRange && weeks.afterRange && weeks.dateEqual) {
						return this.setInfo(weeks,`${this.startText}/${this.endText}`);
					} 
					if(weeks.beforeRange) {
						return this.setInfo(weeks,this.startText);
					}
					if(weeks.afterRange) {
						return this.setInfo(weeks,this.endText);
					}
					if(weeks.extraInfo?.info_old == ' ') {
						weeks.extraInfo.info = null;
					}else if(weeks.extraInfo?.info_old) {
						weeks.extraInfo.info = weeks.extraInfo.info_old;
					} 
				}
			}
		},
		methods: {
			setInfo(weeks,text) {
				this.setInfoOld(weeks);
				if(weeks.extraInfo) {
					weeks.extraInfo.info = text;
				}else {
					weeks.extraInfo = {
						info: text
					}
				}
			},
			setInfoOld(weeks) {
				if(weeks.extraInfo) {
					weeks.extraInfo.info_old = weeks.extraInfo.info ? weeks.extraInfo.info_old || weeks.extraInfo.info : ' ';
				}
			},
			bindDateChange(e) {
				this.$emit('bindDateChange', e);
			},
			backToday() {
				this.$emit('backToday');
			},
			pre() {
				this.$emit('pre');
			},
			next() {
				this.$emit('next');
			},
			choiceDate(e) {
				this.$emit('choiceDate', e);
			}
		}
	}
</script>

<style scoped lang="scss">
	@mixin flex($direction: row) {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: $direction;
	}
	$uv-bg-color-mask: rgba($color: #000000, $alpha: 0.4);
	$uv-border-color: #EDEDED !default;
	$uv-text-color: #333;
	$uv-bg-color-hover: #f1f1f1;
	$uv-font-size-base: 14px;
	$uv-text-color-placeholder: #808080;
	$uv-color-subtitle: #555555;
	$uv-text-color-grey: #999;
	.uv-calendar {
		@include flex(column);
	}
	.uv-calendar__mask {
		position: fixed;
		bottom: 0;
		top: 0;
		left: 0;
		right: 0;
		background-color: $uv-bg-color-mask;
		transition-property: opacity;
		transition-duration: 0.3s;
		opacity: 0;
		/* #ifndef APP-NVUE */
		z-index: 99;
		/* #endif */
	}
	.uv-calendar--mask-show {
		opacity: 1
	}
	.uv-calendar--fixed {
		position: fixed;
		/* #ifdef APP-NVUE */
		bottom: 0;
		/* #endif */
		left: 0;
		right: 0;
		transition-property: transform;
		transition-duration: 0.3s;
		transform: translateY(460px);
		/* #ifndef APP-NVUE */
		bottom: calc(var(--window-bottom));
		z-index: 99;
		/* #endif */
	}
	.uv-calendar--ani-show {
		transform: translateY(0);
	}
	.uv-calendar__content {
		background-color: #fff;
	}
	.uv-calendar__header {
		position: relative;
		@include flex;
		justify-content: center;
		align-items: center;
		height: 50px;
		border-bottom-color: $uv-border-color;
		border-bottom-style: solid;
		border-bottom-width: 1px;
	}
	.uv-calendar--fixed-top {
		@include flex;
		justify-content: space-between;
		border-top-color: $uv-border-color;
		border-top-style: solid;
		border-top-width: 1px;
	}
	.uv-calendar--fixed-width {
		width: 50px;
	}
	.uv-calendar__backtoday {
		position: absolute;
		right: 0;
		top: 25rpx;
		padding: 0 5px;
		padding-left: 10px;
		height: 25px;
		line-height: 25px;
		font-size: 12px;
		border-top-left-radius: 25px;
		border-bottom-left-radius: 25px;
		color: $uv-text-color;
		background-color: $uv-bg-color-hover;
	}
	.uv-calendar__header-text {
		text-align: center;
		width: 100px;
		font-size: $uv-font-size-base;
		color: $uv-text-color;
	}
	.uv-calendar__header-btn-box {
		@include flex;
		align-items: center;
		justify-content: center;
		width: 50px;
		height: 50px;
	}
	.uv-calendar__header-btn {
		width: 10px;
		height: 10px;
		border-left-color: $uv-text-color-placeholder;
		border-left-style: solid;
		border-left-width: 2px;
		border-top-color: $uv-color-subtitle;
		border-top-style: solid;
		border-top-width: 2px;
	}
	.uv-calendar--left {
		transform: rotate(-45deg);
	}
	.uv-calendar--right {
		transform: rotate(135deg);
	}
	.uv-calendar__weeks {
		position: relative;
		@include flex;
	}
	.uv-calendar__weeks-week {
		padding: 0 0 2rpx;
	}
	.uv-calendar__weeks-item {
		flex: 1;
	}
	.uv-calendar__weeks-day {
		flex: 1;
		@include flex(column);
		justify-content: center;
		align-items: center;
		height: 45px;
		border-bottom-color: #F5F5F5;
		border-bottom-style: solid;
		border-bottom-width: 1px;
	}
	.uv-calendar__weeks-day-text {
		font-size: 14px;
	}
	.uv-calendar__box {
		position: relative;
	}
	.uv-calendar__box-bg {
		@include flex(column);
		justify-content: center;
		align-items: center;
		position: absolute;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
	}
	.uv-calendar__box-bg-text {
		font-size: 200px;
		font-weight: bold;
		color: $uv-text-color-grey;
		opacity: 0.1;
		text-align: center;
		/* #ifndef APP-NVUE */
		line-height: 1;
		/* #endif */
	}
</style>
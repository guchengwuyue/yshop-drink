<template>
	<view class="uv-calendar">
		<view class="uv-calendar__content" v-if="insert">
			<calendar-body
				:date="date"
				:nowDate="nowDate"
				:weeks="weeks"
				:calendar="calendar"
				:selected="selected"
				:lunar="lunar"
				:showMonth="showMonth"
				:color="color"
				:startText="startText"
				:endText="endText"
				:range="range"
				:multiple="multiple"
				:allowSameDay="allowSameDay"
				@bindDateChange="bindDateChange"
				@pre="pre"
				@next="next"
				@backToday="backToday"
				@choiceDate="choiceDate"
			></calendar-body>
		</view>
		<uv-popup ref="popup" mode="bottom" v-else :round="round" z-index="998" :close-on-click-overlay="closeOnClickOverlay" @maskClick="maskClick">
			<view style="min-height: 100px;">
				<uv-toolbar
					:show="true"
					:cancelColor="cancelColor"
					:confirmColor="getConfirmColor"
					:cancelText="cancelText"
					:confirmText="confirmText"
					:title="title"
					@cancel="close"
					@confirm="confirm"></uv-toolbar>
				<view class="line"></view>
				<calendar-body
					:nowDate="nowDate"
					:weeks="weeks"
					:calendar="calendar"
					:selected="selected"
					:lunar="lunar"
					:showMonth="showMonth"
					:color="color"
					:startText="startText"
					:endText="endText"
					:range="range"
					:multiple="multiple"
					:allowSameDay="allowSameDay"
					@bindDateChange="bindDateChange"
					@pre="pre"
					@next="next"
					@backToday="backToday"
					@choiceDate="choiceDate"
				></calendar-body>
			</view>
		</uv-popup>
	</view>
</template>
<script>
	/**
	 * Calendar 日历
	 * @description 日历组件可以查看日期，选择任意范围内的日期，打点操作。常用场景如：酒店日期预订、火车机票选择购买日期、上下班打卡等
	 * @tutorial https://ext.dcloud.net.cn/plugin?name=uv-calendar
	 * @property {String} date 自定义当前时间，默认为今天
	 * @property {Boolean} lunar 显示农历
	 * @property {String} startDate 日期选择范围-开始日期
	 * @property {String} endDate 日期选择范围-结束日期
	 * @property {String} mode = [不传 | multiple | range ]  多个日期 | 选择日期范围 默认单日期
	 * @property {Boolean} insert = [true|false] 插入模式,默认为false
	 * 	@value true 弹窗模式
	 * 	@value false 插入模式
	 * @property {Boolean} clearDate = [true|false] 弹窗模式是否清空上次选择内容
	 * @property {Array} selected 打点，期待格式[{date: '2019-06-27', info: '签到', data: { custom: '自定义信息', name: '自定义消息头',xxx:xxx... }}]
	 * @property {String} cancelColor 取消按钮颜色
	 * @property {String} confirmColor  确认按钮颜色，默认#3c9cff
	 * @property {String} title 头部工具条中间的标题文字
	 * @property {String} color 主题色，默认#3c9cff
	 * @property {Number} round :insert="false"时的圆角
	 * @property {Boolean} closeOnClickOverlay 点击遮罩是否关闭
	 * @property {String} startText range为true时，第一个日期底部的提示文字
	 * @property {String} endText range为true时，最后一个日期底部的提示文字
	 * 
	 * @event {Function} change 日期改变，`insert :ture` 时生效
	 * @event {Function} confirm 确认选择`insert :false` 时生效
	 * @event {Function} monthSwitch 切换月份时触发
	 * 
	 * @example <uv-calendar :insert="true":lunar="true" :start-date="'2019-3-2'":end-date="'2019-5-20'"@change="change" />
	 */
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js';
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js';
	import Calendar from './util.js';
	import calendarBody from './calendar-body.vue';
	import { initVueI18n } from '@dcloudio/uni-i18n';
	import i18nMessages from './i18n/index.js';
	const { t } = initVueI18n(i18nMessages);
	export default {
		components: {
			calendarBody
		},
		mixins: [mpMixin, mixin],
		emits: ['close', 'confirm', 'change', 'monthSwitch'],
		props: {
			// 取消按钮颜色
			cancelColor: {
				type: String,
				default: ''
			},
			// 确认按钮颜色，range模式下未选全显示灰色
			confirmColor: {
				type: String,
				default: '#3c9cff'
			},
			// 标题
			title: {
				type: String,
				default: ''
			},
			// 主题色
			color: {
				type: String,
				default: '#3c9cff'
			},
			// 默认显示日期
			date: {
				type: [String,Array],
				default: ''
			},
			// 打点等设置
			selected: {
				type: Array,
				default () {
					return []
				}
			},
			// 是否显示农历
			lunar: {
				type: Boolean,
				default: false
			},
			// 可选择的起始日期
			startDate: {
				type: String,
				default: ''
			},
			// 可选择的结束日期
			endDate: {
				type: String,
				default: ''
			},
			// multiple - 选择多日期  range - 选择日期范围
			mode: {
				type: String,
				default: ''
			},
			// 是否插入模式
			insert: {
				type: Boolean,
				default: false
			},
			// 是否显示月份为背景
			showMonth: {
				type: Boolean,
				default: true
			},
			// 弹窗模式是否清空上次选择内容
			clearDate: {
				type: Boolean,
				default: true
			},
			// 弹窗圆角
			round: {
				type: [Number,String],
				default: 8
			},
			// 点击遮罩是否关闭弹窗
			closeOnClickOverlay: {
				type: Boolean,
				default: true
			},
			// range为true时，第一个日期底部的提示文字
			startText: {
				type: String,
				default: '开始'
			},
			// range为true时，最后一个日期底部的提示文字
			endText: {
				type: String,
				default: '结束'
			},
			// 是否允许日期范围的起止时间为同一天，mode = range时有效
			allowSameDay: {
				type: Boolean,
				default: false
			},
			...uni.$uv?.props?.calendars
		},
		data(){
			return {
				weeks: [],
				calendar: {},
				nowDate: '',
				allowConfirm: false,
				multiple: false,
				range: false
			}
		},
		computed:{
			/**
			 * for i18n
			 */
			confirmText() {
				return t("uv-calender.ok")
			},
			cancelText() {
				return t("uv-calender.cancel")
			},
			getConfirmColor() {
				if(this.range || this.multiple) {
					return this.allowConfirm? this.confirmColor: '#999'
				}else {
					return this.confirmColor;
				}
			}
		},
		watch: {
			date(newVal) {
				this.init(newVal)
			},
			startDate(val) {
				this.cale.resetSatrtDate(val)
				this.cale.setDate(this.nowDate.fullDate)
				this.weeks = this.cale.weeks
			},
			endDate(val) {
				this.cale.resetEndDate(val)
				this.cale.setDate(this.nowDate.fullDate)
				this.weeks = this.cale.weeks
			},
			selected(newVal) {
				this.cale.setSelectInfo(this.nowDate.fullDate, newVal)
				this.weeks = this.cale.weeks
			}
		},
		created() {
			this.setMode();
			this.cale = new Calendar({
				selected: this.selected,
				startDate: this.startDate,
				endDate: this.endDate,
				range: this.range,
				multiple: this.multiple,
				allowSameDay: this.allowSameDay
			})
			this.init(this.date)
		},
		methods: {
			setMode() {
				switch (this.mode){
					case 'range':
						this.range = true;
						break;
					case 'multiple':
						this.multiple = true;
					default:
						break;
				}
			},
			async open() {
				if (this.clearDate && !this.insert) {
					this.cale.cleanRangeStatus()
					this.init(this.date)
				}
				if(!this.insert){
					this.$refs.popup.open();
				}
			},
			close() {
				this.$refs.popup.close();
				this.$emit('close');
			},
			confirm() {
				if(this.range && !this.cale.rangeStatus.after) {
					return;
				} else if(this.multiple && this.cale.multipleStatus.data.length == 0){
					return;
				}
				this.setEmit('confirm');
				this.close()
			},
			maskClick() {
				if(this.closeOnClickOverlay) {
					this.$emit('close');
				}
			},
			bindDateChange(e) {
				const value = e.detail.value + '-1'
				this.setDate(value)
		
				const { year, month } = this.cale.getDate(value)
				this.$emit('monthSwitch', {
					year,
					month
				})
			},
			/**
			 * 初始化日期显示
			 * @param {Object} date
			 */
			init(date) {
				if(this.range) {
					// 重置范围选择状态
					this.cale.cleanRangeStatus();
				}else if(this.multiple){
					// 重置多选状态
					this.cale.cleanMultipleStatus();
				}
				this.cale.setDate(date,'init')
				this.weeks = this.cale.weeks
				this.nowDate = this.calendar = this.cale.getInfo(date)
				this.changeConfirmStatus();
			},
			/**
			 * 变化触发
			 */
			change() {
				this.changeConfirmStatus();
				if (!this.insert) return
				this.setEmit('change')
			},
			changeConfirmStatus() {
				if (this.range) {
					this.allowConfirm = this.cale.rangeStatus.after ? true : false;
				} else if(this.multiple) {
					this.allowConfirm = this.cale.multipleStatus.data.length > 0 ? true : false;
				}
			},
			/**
			 * 选择月份触发
			 */
			monthSwitch() {
				let {
					year,
					month
				} = this.nowDate
				this.$emit('monthSwitch', {
					year,
					month: Number(month)
				})
			},
			/**
			 * 派发事件
			 * @param {Object} name
			 */
			setEmit(name) {
				let {
					year,
					month,
					date,
					fullDate,
					lunar,
					extraInfo
				} = this.calendar
				this.$emit(name, {
					range: this.cale.rangeStatus,
					multiple: this.cale.multipleStatus,
					year,
					month,
					date,
					fulldate: fullDate,
					lunar,
					extraInfo: extraInfo || {}
				})
			},
			/**
			 * 选择天触发
			 * @param {Object} weeks
			 */
			choiceDate(weeks) {
				if (weeks.disable) return
				this.calendar = weeks
				// 设置范围选择
				this.cale.setRange(this.calendar.fullDate)
				// 设置多选
				this.cale.setMultiple(this.calendar.fullDate);
				this.weeks = this.cale.weeks
				this.change()
			},
			/**
			 * 回到今天
			 */
			backToday() {
				const nowYearMonth = `${this.nowDate.year}-${this.nowDate.month}`
				const date = this.cale.getDate(new Date())
				const todayYearMonth = `${date.year}-${date.month}`
				this.init(date.fullDate)
				if (nowYearMonth !== todayYearMonth) {
					this.monthSwitch()
				}
				this.change()
			},
			/**
			 * 上个月
			 */
			pre() {
				const preDate = this.cale.getDate(this.nowDate.fullDate, -1, 'month').fullDate
				this.setDate(preDate)
				this.monthSwitch()
			},
			/**
			 * 下个月
			 */
			next() {
				const nextDate = this.cale.getDate(this.nowDate.fullDate, +1, 'month').fullDate
				this.setDate(nextDate)
				this.monthSwitch()
			},
			/**
			 * 设置日期
			 * @param {Object} date
			 */
			setDate(date) {
				this.cale.setDate(date)
				this.weeks = this.cale.weeks
				this.nowDate = this.cale.getInfo(date)
			}
		}
	}
</script>
<style scoped lang="scss">
	$uv-border-color: #EDEDED !default;
	.uv-calendar__content {
		background-color: #fff;
	}
	.line {
		width: 750rpx;
		height: 1px;
		border-bottom-color: $uv-border-color;
		border-bottom-style: solid;
		border-bottom-width: 1px;
	}
</style>
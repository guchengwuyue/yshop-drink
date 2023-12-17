<template>
	<view class="uv-calendar-header uv-border-bottom">
		<text
			class="uv-calendar-header__title"
			v-if="showTitle"
		>{{ title }}</text>
		<text
			class="uv-calendar-header__subtitle"
			v-if="showSubtitle"
		>{{ subtitle }}</text>
		<view class="uv-calendar-header__weekdays">
			<text class="uv-calendar-header__weekdays__weekday">一</text>
			<text class="uv-calendar-header__weekdays__weekday">二</text>
			<text class="uv-calendar-header__weekdays__weekday">三</text>
			<text class="uv-calendar-header__weekdays__weekday">四</text>
			<text class="uv-calendar-header__weekdays__weekday">五</text>
			<text class="uv-calendar-header__weekdays__weekday">六</text>
			<text class="uv-calendar-header__weekdays__weekday">日</text>
		</view>
	</view>
</template>

<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	export default {
		name: 'uv-calendar-header',
		mixins: [mpMixin, mixin],
		props: {
			// 标题
			title: {
				type: String,
				default: ''
			},
			// 副标题
			subtitle: {
				type: [String,null],
				default: ''
			},
			// 是否显示标题
			showTitle: {
				type: Boolean,
				default: true
			},
			// 是否显示副标题
			showSubtitle: {
				type: Boolean,
				default: true
			},
		},
		data() {
			return {

			}
		},
		methods: {
			name() {

			}
		},
	}
</script>

<style lang="scss" scoped>
	$show-border: 1;
	$show-border-bottom: 1;
	@import '@/uni_modules/uv-ui-tools/libs/css/variable.scss';
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
	@import '@/uni_modules/uv-ui-tools/libs/css/color.scss';
	.uv-calendar-header {
		padding-bottom: 4px;

		&__title {
			font-size: 16px;
			color: $uv-main-color;
			text-align: center;
			height: 42px;
			line-height: 42px;
			font-weight: bold;
		}

		&__subtitle {
			font-size: 14px;
			color: $uv-main-color;
			height: 40px;
			text-align: center;
			line-height: 40px;
			font-weight: bold;
		}

		&__weekdays {
			@include flex;
			justify-content: space-between;

			&__weekday {
				font-size: 13px;
				color: $uv-main-color;
				line-height: 30px;
				flex: 1;
				text-align: center;
			}
		}
	}
</style>

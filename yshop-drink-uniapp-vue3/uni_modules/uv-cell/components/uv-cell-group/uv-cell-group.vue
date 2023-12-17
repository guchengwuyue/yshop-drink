<template>
    <view :style="[$uv.addStyle(customStyle)]" :class="[customClass]" class="uv-cell-group">
        <view v-if="title" class="uv-cell-group__title">
            <slot name="title">
				<text class="uv-cell-group__title__text">{{ title }}</text>
			</slot>
        </view>
        <view class="uv-cell-group__wrapper">
			<uv-line v-if="border"></uv-line>
            <slot />
        </view>
    </view>
</template>

<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	import props from './props.js';
	/**
	 * cellGroup  单元格
	 * @description cell单元格一般用于一组列表的情况，比如个人中心页，设置页等。
	 * @tutorial https://www.uvui.cn/components/cell.html
	 * 
	 * @property {String}	title		分组标题
	 * @property {Boolean}	border		是否显示外边框 (默认 true )
	 * @property {Object}	customStyle	定义需要用到的外部样式
	 * 
	 * @event {Function} click 	点击cell列表时触发
	 * @example <uv-cell-group title="设置喜好">
	 */
	export default {
		name: 'uv-cell-group',
		mixins: [mpMixin, mixin, props]
	}
</script>

<style lang="scss" scoped>
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
	@import '@/uni_modules/uv-ui-tools/libs/css/color.scss';
	$uv-cell-group-title-padding: 16px 16px 8px !default;
	$uv-cell-group-title-font-size: 15px !default;
	$uv-cell-group-title-line-height: 16px !default;
	$uv-cell-group-title-color: $uv-main-color !default;

    .uv-cell-group {
		flex: 1;
		
        &__title {
            padding: $uv-cell-group-title-padding;

            &__text {
                font-size: $uv-cell-group-title-font-size;
                line-height: $uv-cell-group-title-line-height;
                color: $uv-cell-group-title-color;
            }
        }
		
		&__wrapper {
			position: relative;
		}
    }
</style>


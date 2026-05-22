<template>
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<view class="remark-page w-100 h-100 overflow-hidden">
		<view class="remark-page__textarea">
			<textarea
				placeholder-class="text-color-assist font-size-base"
				v-model="remark"
				class="remark-page__input w-100 border-box font-size-base"
				:class="isDanger ? 'remark-page__input--danger' : 'remark-page__input--assist'"
				placeholder="请填写备注信息"
				focus
			/>
			<view
				class="remark-page__counter"
				:class="isDanger ? 'remark-page__counter--danger' : 'remark-page__counter--assist'"
			>
				{{ remarkLength }}/50
			</view>
		</view>
		<view class="remark-page__label font-size-base text-color-assist">
			快捷输入
		</view>
		<view class="remark-page__quick-list">
			<view
				class="remark-page__quick-item"
				v-for="(item, index) in quickInputs"
				:key="index"
				@tap="handleQuickInput(item)"
			>
				{{ item }}
			</view>
		</view>
		<view class="remark-page__actions">
			<button type="primary" class="remark-page__submit-btn font-size-base" @tap="submit">完成</button>
		</view>
		<uv-toast ref="uToast"></uv-toast>
	</view>
</template>

<script setup>
import {
  ref,
  computed
} from 'vue'
import { onLoad} from '@dcloudio/uni-app'

const title = ref('备注')
const remark = ref('')
const quickInputs = ref(['请放门把手上', '请放门口', '请放前台桌上', '如地址封闭管理，请电话与我联系'])
const uToast = ref()

onLoad((opt) => {
	remark.value = opt.remark
})

const remarkLength = computed(() => {
	return remark.value.length
})
const isDanger = computed(() => {
	return remark.value.length > 50
})

const handleQuickInput = (item) => {
	remark.value = remark.value.concat(' ', item)
}
const submit = () => {
	if (remark.value.length > 50) {
		uToast.value.show({
			message: '不能超过50个字符',
			type: 'error'
		})
		return
	}
	uni.navigateTo({
		url: '/pages/components/pages/pay/pay?remark=' + remark.value
	})
}
</script>

<style lang="scss" scoped>
$remark-page-padding-y: 30rpx;
$remark-page-padding-x: 40rpx;
$remark-textarea-radius: 8rpx;
$remark-textarea-padding-y: 30rpx;
$remark-textarea-padding-x: 40rpx;
$remark-textarea-height: 320rpx;
$remark-counter-offset-y: 30rpx;
$remark-counter-offset-x: 40rpx;
$remark-label-margin-y: 40rpx;
$remark-quick-list-padding-right: 20rpx;
$remark-quick-item-padding-y: 16rpx;
$remark-quick-item-padding-x: 26rpx;
$remark-quick-item-gap: 20rpx;
$remark-quick-item-border-width: 2rpx;
$remark-actions-margin-top: 60rpx;
$remark-submit-btn-width: 90%;
$remark-submit-btn-height: 80rpx;
$remark-submit-btn-radius: 40rpx;

.remark-page {
	--remark-page-padding-y: #{$remark-page-padding-y};
	--remark-page-padding-x: #{$remark-page-padding-x};
	--remark-textarea-padding-y: #{$remark-textarea-padding-y};
	--remark-textarea-padding-x: #{$remark-textarea-padding-x};
	--remark-textarea-height: #{$remark-textarea-height};
	--remark-counter-offset-y: #{$remark-counter-offset-y};
	--remark-counter-offset-x: #{$remark-counter-offset-x};
	--remark-submit-btn-width: #{$remark-submit-btn-width};
	--remark-submit-btn-height: #{$remark-submit-btn-height};

	padding: var(--remark-page-padding-y) var(--remark-page-padding-x);

	&__textarea {
		position: relative;
	}

	&__input {
		border-radius: $remark-textarea-radius;
		padding: var(--remark-textarea-padding-y) var(--remark-textarea-padding-x);
		height: var(--remark-textarea-height);
		background-color: $text-color-white;
		color: $text-color-base;

		&--danger {
			color: $color-error;
		}

		&--assist {
			color: $text-color-assist;
		}
	}

	&__counter {
		position: absolute;
		bottom: var(--remark-counter-offset-y);
		right: var(--remark-counter-offset-x);
		font-size: $font-size-base;

		&--danger {
			color: $color-error;
		}

		&--assist {
			color: $text-color-assist;
		}
	}

	&__label {
		margin: $remark-label-margin-y 0;
	}

	&__quick-list {
		display: flex;
		flex-wrap: wrap;
		justify-content: flex-start;
		padding-right: $remark-quick-list-padding-right;
	}

	&__quick-item {
		background-color: $text-color-white;
		border: $remark-quick-item-border-width solid $color-primary;
		color: $color-primary;
		font-size: $font-size-base;
		padding: $remark-quick-item-padding-y $remark-quick-item-padding-x;
		margin-right: $remark-quick-item-gap;
		margin-bottom: $remark-quick-item-gap;
	}

	&__actions {
		display: flex;
		justify-content: center;
		align-items: center;
		margin-top: $remark-actions-margin-top;
	}

	&__submit-btn {
		width: var(--remark-submit-btn-width);
		height: var(--remark-submit-btn-height);
		border-radius: $remark-submit-btn-radius;
		line-height: var(--remark-submit-btn-height);
	}
}
</style>

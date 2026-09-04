<template>
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<view class="container packages-page position-relative w-100 h-100 overflow-hidden">
		<uv-empty v-if="coupons.length == 0" mode="coupon"></uv-empty>

		<scroll-view scroll-y class="packages-list">
			<view class="packages-list__wrapper">
				<view
					class="packages-item"
					v-for="(item, index) in coupons"
					:key="index"
					@tap="openDetailModal(item, index)"
				>
					<view class="packages-ticket">
						<view
							class="packages-ticket__body"
							:class="{ 'packages-ticket__body--selected': item.id == coupon_id }"
						>
							<view class="packages-ticket__left">
								<image
									class="packages-ticket__picture"
									:src="item.image"
									mode="aspectFill"
								></image>
								<view class="packages-ticket__intro">
									<view class="packages-ticket__value">
										￥
										<text class="packages-ticket__amount">{{ item.value }}</text>
										<view>满{{ item.least }}减{{ item.value }}</view>
									</view>
									<view class="packages-ticket__type">{{ item.title }}</view>
									<view class="packages-ticket__date u-line-1">
										{{ formatDateTime(item.startTime, 'yyyy-MM-dd') }}-{{ formatDateTime(item.endTime, 'yyyy-MM-dd') }}
									</view>
								</view>
							</view>
							<view class="packages-ticket__right" @click.stop="" v-if="activeTabIndex == 0">
								<view
									v-if="item.id != coupon_id"
									class="packages-ticket__btn packages-ticket__btn--use immediate-use"
									:round="true"
									@tap="useCouponWith(item)"
								>立即使用</view>
								<view
									v-else
									class="packages-ticket__btn packages-ticket__btn--use immediate-use"
									:round="true"
									@tap="cancelCoupon(item)"
								>取消使用</view>
							</view>
						</view>
					</view>
				</view>
			</view>
		</scroll-view>

		<modal custom :show="detailModalVisible" @cancel="closeDetailModal" width="90%">
			<view class="packages-modal">
				<view class="d-flex font-size-extra-lg text-color-base just-content-center mb-20">{{ coupon.title }}</view>
				<view class="d-flex font-size-sm text-color-base mb-20">有效期：{{formatDateTime(coupon.startTime, 'yyyy-MM-dd')}}至{{formatDateTime(coupon.endTime, 'yyyy-MM-dd')}}</view>
				<view class="d-flex font-size-sm text-color-base mb-20">领取时间：{{ coupon.createtime_text }}</view>
				<view class="d-flex font-size-sm text-color-base mb-20">卷价值：满{{ coupon.least }}减{{ coupon.value }}</view>
				<view class="d-flex font-size-sm text-color-base mb-20">适用范围：{{ typeInfo(coupon.type) }}</view>
				<view class="d-flex font-size-sm text-color-base mb-20">适用店铺：{{ coupon.shopName }}</view>
				<view class="d-flex align-items-center just-content-center" v-if="activeTabIndex == 0">
					<button type="primary" @tap="useCoupon" class="packages-modal__btn">立即使用</button>
				</view>
			</view>
		</modal>

		<!--轻提示-->
		<uv-toast ref="uToast"></uv-toast>
	</view>
</template>

<script setup>
import {
  ref,
  watch,
  toRaw
} from 'vue'
import { useMainStore } from '@/store/store'
import { storeToRefs } from 'pinia'
import { onLoad,onShow ,onPullDownRefresh,onHide} from '@dcloudio/uni-app'
import { formatDateTime,prePage } from '@/utils/util'
import {
  couponMine
} from '@/api/coupon'
const main = useMainStore()
const { isLogin } = storeToRefs(main)
const title = ref('优惠券')
const activeTabIndex = ref(0)
const detailModalVisible = ref(false)
const coupon = ref({})
const couponIndex = ref(0)
const coupons = ref([])
const amount = ref(0)
const buttonLock = ref(false)
const coupon_id = ref(0)
const shop_id = ref(0)
const type = ref(0)
const uToast = ref()

onLoad((options) => {
	if (options.amount) {
		amount.value = options.amount;
	}
	if (options.coupon_id) {
		coupon_id.value = options.coupon_id
	}
	if (options.shop_id) {
		shop_id.value = options.shop_id;
	}
	if (options.type) {
		type.value = options.type;
	}
	activeTabIndex.value = 0;
	getCoupons();
})

onPullDownRefresh(() => {
	getCoupons();
})

// 使用范围
const typeInfo = (type) => {
	if (type == 0) {
		return '外卖和自取';
	}
	if (type == 1) {
		return '自取';
	}
	if (type == 2) {
		return '外卖';
	}
}
const getCoupons = async() => {
	let data = await couponMine({shop_id: shop_id.value, type: type.value, page:1, pagesize:10000});
	uni.stopPullDownRefresh();
	if (data) {
		coupons.value = data;
	}
}
const openDetailModal = (mycoupon, index) => {
	couponIndex.value = index;
	coupon.value = mycoupon;
	detailModalVisible.value = true;
}
// 使用优惠券
const useCouponWith = (mycoupon) => {
	coupon.value = mycoupon;
	useCoupon();
}
// 取消优惠券
const cancelCoupon = () => {
	coupon.value = {}
	coupon_id.value = 0
	prePage().coupon = {}
}
const closeDetailModal = () => {
	detailModalVisible.value = false;
	coupon.value = {};
}
// 使用优惠及

const useCoupon = () => {
	if (buttonLock.value == true) {
		return;
	}
	buttonLock.value = true
	//console.log('coupon:',coupon.value);
	if (parseFloat(coupon.value.least) > parseFloat(amount.value)) {
		//console.log('pages3:');
		uToast.value.show({
			message: '订单金额满'+coupon.value.least+'才能使用',
			type: 'error'
		});
		buttonLock.value = false
	} else {
	    main.SET_COUPON(coupon)
		console.log('main.myconpon:',main.mycoupon)
		//prePage().coupon = coupon.value;
		//prePage().coupons = 1; // 哨兵
		
		uni.navigateBack({
			
		})
		
	}
	
}



</script>

<style lang="scss" scoped>
// 选券页局部 token（与 uni.scss 全局变量配合）
$packages-list-offset-nav: 120rpx;
$packages-list-margin-top: $spacing-row-lg;
$packages-list-padding-x: $spacing-row-base;
$packages-item-gap: $spacing-row-lg;
$packages-item-radius: $border-radius-base;
$packages-notch-size: 30rpx;
$packages-notch-offset: 65rpx;
$packages-ticket-radius: 20rpx;
$packages-ticket-divider: $border-color-light;
$packages-ticket-left-width: 70%;
$packages-ticket-right-width: 30%;
$packages-ticket-padding: $spacing-row-base;
$packages-ticket-right-padding-y: 40rpx;
$packages-picture-size: 190rpx;
$packages-amount-font-size: 60rpx;
$packages-date-font-size: 20rpx;
$packages-intro-gap: 10rpx;
$packages-btn-radius: 40rpx;
$packages-btn-padding-x: 20rpx;
$packages-btn-line-height: 40rpx;
$packages-btn-margin-left: 20rpx;
$packages-selected-border-width: 1rpx;
$packages-modal-btn-width: 95%;

/* #ifdef H5 */
page {
	height: 100%;
}
/* #endif */

.packages-page {
	--packages-picture-size: #{$packages-picture-size};
	--packages-list-offset-nav: #{$packages-list-offset-nav};

	display: flex;
	flex-direction: column;
}

.packages-list {
	margin-top: $packages-list-margin-top;
	height: calc(100vh - var(--packages-list-offset-nav));

	/* #ifdef H5 */
	height: calc(100vh - var(--packages-list-offset-nav) - 44px);
	/* #endif */

	&__wrapper {
		display: flex;
		flex-direction: column;
		padding: 0 $packages-list-padding-x;
	}
}

.packages-item {
	display: flex;
	flex-direction: column;
	position: relative;
	margin-bottom: $packages-item-gap;
	background-color: $text-color-white;
	border-radius: $packages-item-radius;
	box-shadow: $box-shadow;

	&::before,
	&::after {
		content: '';
		position: absolute;
		bottom: $packages-notch-offset;
		width: $packages-notch-size;
		height: $packages-notch-size;
		background-color: $bg-color;
		border-radius: $border-radius-circle;
	}

	&::before {
		left: calc(-1 * #{$packages-notch-size} / 2);
	}

	&::after {
		right: calc(-1 * #{$packages-notch-size} / 2);
	}
}

.packages-ticket {
	background-color: $text-color-white;

	&__body {
		display: flex;

		&--selected {
			border: $packages-selected-border-width solid $color-error;
			border-radius: $packages-ticket-radius;
		}
	}

	&__left {
		display: flex;
		width: $packages-ticket-left-width;
		padding: $packages-ticket-padding;
		background-color: $text-color-white;
		border-radius: $packages-ticket-radius;
		border-right: dashed 2rpx $packages-ticket-divider;
	}

	&__picture {
		flex-shrink: 0;
		width: var(--packages-picture-size);
		height: var(--packages-picture-size);
		border-radius: $packages-ticket-radius;
	}

	&__intro {
		margin-left: $packages-intro-gap;
		min-width: 0;
	}

	&__value {
		font-size: $font-size-base;
		color: $uv-warning;

		.packages-ticket__amount {
			margin-right: $packages-intro-gap;
			font-size: $packages-amount-font-size;
			font-weight: bold;
		}
	}

	&__type {
		font-size: $font-size-base;
		color: $uv-info-dark;
	}

	&__date {
		margin-top: $packages-intro-gap;
		font-size: $packages-date-font-size;
		color: $uv-info-dark;
	}

	&__right {
		display: flex;
		align-items: center;
		width: $packages-ticket-right-width;
		padding: $packages-ticket-right-padding-y $packages-ticket-padding;
		background-color: $text-color-white;
		border-radius: $packages-ticket-radius;
	}

	&__btn {
		height: auto;
		margin-left: $packages-btn-margin-left;
		padding: 0 $packages-btn-padding-x;
		font-size: $font-size-sm;
		line-height: $packages-btn-line-height;
		border-radius: $packages-btn-radius;
		color: $text-color-white !important;

		&--use {
			background-color: $uv-warning !important;
		}
	}
}

.packages-modal__btn {
	width: $packages-modal-btn-width;
	border-radius: 50rem !important;
}
</style>

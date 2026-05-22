<template>
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<view class="container coupons-page position-relative w-100 h-100 overflow-hidden">
		<view class="coupons-exchange">
			<view class="coupons-exchange__input">
				<uv-search placeholder="请输入兑换码" v-model="exchange_code" @click="exchange"></uv-search>
			</view>
		</view>
		<view class="coupons-tabbar">
			<view
				class="coupons-tab"
				:class="{ 'coupons-tab--active': activeTabIndex == index }"
				v-for="(item, index) in tabs"
				:key="index"
				@tap="handleTab(index)"
			>
				<view class="coupons-tab__title">{{ item.title }}</view>
			</view>
		</view>
		<view class="flex-fill">
			<scroll-view scroll-y class="coupons-list" @scrolltolower="getCoupons(activeTabIndex)">
				<view class="coupons-list__wrapper" v-if="0 === activeTabIndex">
					<uv-empty v-if="myCoupons.length == 0" mode="list"></uv-empty>
					<view class="coupons-item" v-for="(item, index) in myCoupons" :key="index" @tap="openDetailModal(item,index)">
						<view class="coupons-ticket">
							<view class="coupons-ticket__body">
								<view class="coupons-ticket__left">
									<image
										class="coupons-ticket__picture"
										:src="item.image"
										mode="aspectFill"
									></image>
									<view class="coupons-ticket__intro">
										<view class="coupons-ticket__value">
											￥
											<text class="coupons-ticket__amount">{{item.value}}</text>
											<view>
												满{{item.least}}减{{item.value}}
											</view>
										</view>
										<view class="coupons-ticket__type">{{ item.title }}</view>
										<view class="coupons-ticket__date u-line-1">{{formatDateTime(item.startTime, 'yyyy-MM-dd')}}-{{formatDateTime(item.endTime, 'yyyy-MM-dd')}}</view>
									</view>
								</view>
								<view class="coupons-ticket__right" @click.stop="" v-if="activeTabIndex == 1">
									<view class="coupons-ticket__btn coupons-ticket__btn--use immediate-use" :round="true" @tap="receive(item, index)">立即领取</view>
								</view>
								<view class="coupons-ticket__right" @click.stop="" v-if="activeTabIndex == 0">
									<view v-if="item.status == 0" class="coupons-ticket__btn coupons-ticket__btn--use immediate-use" :round="true" @tap="useCouponWith(item)">立即使用</view>
									<view v-else class="coupons-ticket__btn coupons-ticket__btn--used">已使用</view>
								</view>
							</view>
						</view>
					</view>
				</view>
				<view class="coupons-list__wrapper" v-if="1 === activeTabIndex">
					<uv-empty v-if="notCoupons.length == 0" mode="list"></uv-empty>
					<view class="coupons-item" v-for="(item, index) in notCoupons" :key="index" @tap="openDetailModal(item,index)">
						<view class="coupons-ticket">
							<view class="coupons-ticket__body">
								<view class="coupons-ticket__left">
									<image
										class="coupons-ticket__picture"
										:src="item.image"
										mode="aspectFill"
									></image>
									<view class="coupons-ticket__intro">
										<view class="coupons-ticket__value">
											￥
											<text class="coupons-ticket__amount">{{item.value}}</text>
											<view>
												满{{item.least}}减{{item.value}}
											</view>
										</view>
										<view class="coupons-ticket__type">{{ item.title }}</view>
										<view class="coupons-ticket__date u-line-1">{{formatDateTime(item.startTime, 'yyyy-MM-dd')}}-{{formatDateTime(item.endTime, 'yyyy-MM-dd')}}</view>
									</view>
								</view>
								<view class="coupons-ticket__right" @click.stop="" v-if="activeTabIndex == 1">
									<view class="coupons-ticket__btn coupons-ticket__btn--use immediate-use" :round="true" v-if="item.isReceive == 0" @tap="receive(item, index)">立即领取</view>
									<view v-else class="coupons-ticket__btn coupons-ticket__btn--used immediate-use">已领取</view>
								</view>
								<view class="coupons-ticket__right" @click.stop="" v-if="activeTabIndex == 0">
									<view v-if="item.status == 0" class="coupons-ticket__btn coupons-ticket__btn--use immediate-use" :round="true" @tap="useCouponWith(item)">立即使用</view>
									<view v-else class="coupons-ticket__btn coupons-ticket__btn--used">已使用</view>
								</view>
							</view>
						</view>
					</view>
				</view>
			</scroll-view>
		</view>
		<modal custom :show="detailModalVisible" @cancel="closeDetailModal" width="90%" title="优惠券详情">
			<view class="modal-content">
				<view class="d-flex font-size-extra-lg text-color-base just-content-center mb-20">{{ coupon.title }}</view>
				<view class="d-flex font-size-sm text-color-base mb-20">
					有效期：{{formatDateTime(coupon.startTime, 'yyyy-MM-dd')}}-{{formatDateTime(coupon.endTime, 'yyyy-MM-dd')}}
				</view>
				<view class="d-flex font-size-sm text-color-base mb-20">
					领取时间：{{formatDateTime(coupon.createTime)}}
				</view>
				<view class="d-flex font-size-sm text-color-base mb-20">
					券价值：满{{ coupon.least }}减{{ coupon.value }}
				</view>
				<view class="d-flex font-size-sm text-color-base mb-20" v-if="activeTabIndex == 1">
					每人限领：{{ coupon.limit }} 张
				</view>
				<view class="d-flex font-size-sm text-color-base mb-20">
					适用范围：{{typeInfo(coupon.type)}}
				</view>
				<view class="d-flex font-size-sm text-color-base mb-20">
					适用店铺：{{coupon.shopName}}
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
  watch
} from 'vue'
import { useMainStore } from '@/store/store'
import { storeToRefs } from 'pinia'
import { onLoad,onShow ,onPullDownRefresh,onHide} from '@dcloudio/uni-app'
import { formatDateTime,kmUnit } from '@/utils/util'
import {
  couponReceive,
  couponMine,
  couponIndexApi
} from '@/api/coupon'
const main = useMainStore()
const { isLogin } = storeToRefs(main)
const title = ref('优惠券')

const tabs = ref([
	{title: '我的优惠券', page:1, pagesize:10,
		coupons: []
	},
	{title: '未领优惠券', page:1, pagesize:10,
		coupons: []
	}
])
const activeTabIndex = ref(0)
const detailModalVisible = ref(false)
const coupon = ref({})
const couponIndex = ref(0)
const exchange_code = ref('')
const uToast = ref()
const myCoupons = ref([])
const notCoupons = ref([])

onShow(() => {
	getCoupons(0)
})
onPullDownRefresh(() => {
	if(activeTabIndex.value == 0) {
		myCoupons.value = []
	}
	if(activeTabIndex.value == 1) {
		notCoupons.value = []
	}
	tabs.value[activeTabIndex.value].page = 1;
	getCoupons(activeTabIndex.value)
})
watch(activeTabIndex, () => {
   getCoupons(activeTabIndex.value)
})

// 兑换
const exchange = async() => {
	let data = await couponReceive({code:exchange_code.value});
	if (data) {
		uToast.value.show({
			message: '兑换成功',
			type: 'success'
		});
		tabs.value[0].coupons = [];
		tabs.value[0].page = 1;
		getCoupons(0)
		tabs.value[1].coupons = [];
		tabs.value[1].page = 1;
		getCoupons(1)
	}
}
// 使用范围
const typeInfo = (type) => {
	if (type == 0) {
		return '通用'
	}
	if (type == 1) {
		return '自取'
	}
	if (type == 2) {
		return '外卖'
	}
}
const handleTab = (index) => {
	console.log('activeTabIndex2:',index)
	activeTabIndex.value = index
}
const getCoupons = async(type) => {
	let page = tabs.value[type].page;
	let pagesize = tabs.value[type].pagesize;
	// 我的优惠券
	let data = [];
	if (type == 0) {
		myCoupons.value = await couponMine({page:page,pagesize:pagesize});
	}
	// 未领优惠券
	if (type == 1) {
		notCoupons.value = await couponIndexApi({page:page,pagesize:pagesize});
	}
	//console.log('data:',data)
	uni.stopPullDownRefresh();
	
	console.log('tabs.value:',tabs.value[type].title)
	//tabs.value[type].page++;
}
const openDetailModal = (couponData,index) => {
	couponIndex.value = index;
	coupon.value = couponData
	detailModalVisible.value = true
}
const useCouponWith = (coupon) => {
	//coupon.value = coupon
	useCoupon();
}
const closeDetailModal = () => {
	detailModalVisible.value = false
	coupon.value = {}
}
const useCoupon = () => {
	uni.switchTab({
		url: '/pages/menu/menu'
	})
}
const showTip1 = () => {
	uni.showToast({
		title: '您暂时还没有赠送中卡券哦~',
		icon: 'none'
	})
}
const showTip2 = () => {
	uni.showToast({
		title: '您暂时还没有券码哦~',
		icon: 'none'
	})
}
// 领取优惠券
const receive = async(coupon,index) => {
	let data = await couponReceive({id:coupon.id});
	if (data) {
		uToast.value.show({
			message: '领取成功',
			type: 'success'
		});
		detailModalVisible.value = false
		getCoupons(1)
	}
}


</script>

<style lang="scss" scoped>
// 优惠券页局部 token（与 uni.scss 全局变量配合）
$coupons-exchange-height: 100rpx;
$coupons-exchange-input-width: 70%;
$coupons-tabbar-height: 80rpx;
$coupons-tab-indicator-height: 5rpx;
$coupons-tab-title-padding-y: 15rpx;
$coupons-list-offset-nav: 120rpx;
$coupons-list-offset-header: 200rpx;
$coupons-list-padding-x: $spacing-row-base;
$coupons-item-gap: $spacing-row-lg;
$coupons-item-radius: $border-radius-base;
$coupons-notch-size: 30rpx;
$coupons-notch-offset: 65rpx;
$coupons-ticket-radius: 20rpx;
$coupons-ticket-divider: $border-color-light;
$coupons-ticket-left-width: 70%;
$coupons-ticket-right-width: 30%;
$coupons-ticket-padding: $spacing-row-base;
$coupons-ticket-right-padding-y: 40rpx;
$coupons-picture-size: 190rpx;
$coupons-amount-font-size: 60rpx;
$coupons-date-font-size: 20rpx;
$coupons-intro-gap: 10rpx;
$coupons-btn-radius: 40rpx;
$coupons-btn-padding-x: 20rpx;
$coupons-btn-line-height: 40rpx;
$coupons-btn-margin-left: 20rpx;

/* #ifdef H5 */
page {
	height: 100%;
}
/* #endif */

.coupons-page {
	--coupons-picture-size: #{$coupons-picture-size};
	--coupons-list-offset-nav: #{$coupons-list-offset-nav};
	--coupons-list-offset-header: #{$coupons-list-offset-header};

	display: flex;
	flex-direction: column;
}

.coupons-exchange {
	flex-shrink: 0;
	display: flex;
	align-items: center;
	justify-content: center;
	height: $coupons-exchange-height;
	background-color: $text-color-white;

	&__input {
		width: $coupons-exchange-input-width;
	}
}

.coupons-tabbar {
	flex-shrink: 0;
	display: flex;
	align-items: center;
	justify-content: center;
	width: 100%;
	height: $coupons-tabbar-height;
}

.coupons-tab {
	flex: 1;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	height: 100%;
	font-size: $font-size-base;
	color: $text-color-base;
	position: relative;

	&__title {
		padding: $coupons-tab-title-padding-y 0;
	}

	&--active {
		color: $color-primary;

		.coupons-tab__title {
			border-bottom: $coupons-tab-indicator-height solid $color-primary;
		}
	}
}

.coupons-list {
	height: calc(100vh - var(--coupons-list-offset-nav) - var(--coupons-list-offset-header));

	/* #ifdef H5 */
	height: calc(100vh - var(--coupons-list-offset-nav) - var(--coupons-list-offset-header) - 44px);
	/* #endif */

	&__wrapper {
		display: flex;
		flex-direction: column;
		padding: 0 $coupons-list-padding-x;
	}
}

.coupons-item {
	display: flex;
	flex-direction: column;
	position: relative;
	margin-bottom: $coupons-item-gap;
	background-color: $text-color-white;
	border-radius: $coupons-item-radius;
	box-shadow: $box-shadow;

	&::before,
	&::after {
		content: '';
		position: absolute;
		bottom: $coupons-notch-offset;
		width: $coupons-notch-size;
		height: $coupons-notch-size;
		background-color: $bg-color;
		border-radius: $border-radius-circle;
	}

	&::before {
		left: calc(-1 * #{$coupons-notch-size} / 2);
	}

	&::after {
		right: calc(-1 * #{$coupons-notch-size} / 2);
	}
}

.coupons-ticket {
	background-color: $text-color-white;

	&__body {
		display: flex;
	}

	&__left {
		display: flex;
		width: $coupons-ticket-left-width;
		padding: $coupons-ticket-padding;
		background-color: $text-color-white;
		border-radius: $coupons-ticket-radius;
		border-right: dashed 2rpx $coupons-ticket-divider;
	}

	&__picture {
		flex-shrink: 0;
		width: var(--coupons-picture-size);
		height: var(--coupons-picture-size);
		border-radius: $coupons-ticket-radius;
	}

	&__intro {
		margin-left: $coupons-intro-gap;
		min-width: 0;
	}

	&__value {
		font-size: $font-size-base;
		color: $uv-warning;

		.coupons-ticket__amount {
			margin-right: $coupons-intro-gap;
			font-size: $coupons-amount-font-size;
			font-weight: bold;
		}
	}

	&__type {
		font-size: $font-size-base;
		color: $uv-info-dark;
	}

	&__date {
		margin-top: $coupons-intro-gap;
		font-size: $coupons-date-font-size;
		color: $uv-info-dark;
	}

	&__right {
		display: flex;
		align-items: center;
		width: $coupons-ticket-right-width;
		padding: $coupons-ticket-right-padding-y $coupons-ticket-padding;
		background-color: $text-color-white;
		border-radius: $coupons-ticket-radius;
	}

	&__btn {
		height: auto;
		margin-left: $coupons-btn-margin-left;
		padding: 0 $coupons-btn-padding-x;
		font-size: $font-size-sm;
		line-height: $coupons-btn-line-height;
		border-radius: $coupons-btn-radius;
		color: $text-color-white !important;

		&--use {
			background-color: $uv-warning !important;
		}

		&--used {
			background-color: $uv-info-dark !important;
		}
	}
}
</style>

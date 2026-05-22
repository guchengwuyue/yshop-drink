<template>
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<view class="container order-detail-page">
		<view class="order-detail-page__body">
			<view class="bg-white">
				<view class="order-detail-section">
					<!-- store info begin -->
					<list-cell :hover="false">
						<view class="w-100 d-flex align-items-center">
							<view class="d-flex flex-column w-60">
								<view class="w-100 font-size-lg text-color-base text-truncate">{{ order.shop.name }}</view>
							</view>
							<view class="d-flex justify-content-end align-items-center w-40">
								<view class="iconfont-yshop icon-mobile order-detail-page__shop-icon" @click="makePhoneCall(order.shop)"></view>
								<view class="iconfont-yshop icon-location order-detail-page__shop-icon order-detail-page__shop-icon--last" @click="openLocation(order.shop)"></view>
							</view>
						</view>
					</list-cell>
					<!-- store info end -->
					<list-cell :hover="false" padding="50rpx 30rpx">
						<view class="w-100 d-flex flex-column">
							<view class="d-flex align-items-center just-content-center">
								<view class="order-detail-page__sort-num">{{ order.paid == 1 ? order.numberId : '--' }}</view>
							</view>
							<!-- steps begin -->
							<view class="d-flex just-content-center">
								<view class="order-detail-steps d-flex flex-column w-80">
									<view class="order-detail-steps__icons">
										<view class="order-detail-steps__icon-item">
											<view class="iconfont-yshop icon-lamp"></view>
										</view>
										<view class="order-detail-steps__icon-item">
											<view class="iconfont-yshop icon-daojishi" v-if="{active: order.paid == 1 && order.status == 0}"></view>
											<view class="iconfont-yshop icon-daojishi order-detail-steps__icon--inactive" v-else></view>
										</view>
										<view class="order-detail-steps__icon-item" v-if="order.orderType == 'takeout'">
											<view class="iconfont-yshop icon-takeout" v-if="order.status == 1"></view>
											<view class="iconfont-yshop icon-takeout order-detail-steps__icon--inactive" v-else></view>
										</view>
										<view class="order-detail-steps__icon-item">
											<view class="iconfont-yshop icon-doorbell" v-if="order.status >= 2"></view>
											<view class="iconfont-yshop icon-doorbell order-detail-steps__icon--inactive" v-else></view>
										</view>
									</view>
									<view class="order-detail-steps__labels">
										<view class="order-detail-steps__label order-detail-steps__label--active">
											<view class="order-detail-steps__line order-detail-steps__line--transparent"></view>
											<view class="order-detail-steps__text">已下单</view>
											<view class="order-detail-steps__line"></view>
										</view>
										<view class="order-detail-steps__label" :class="{'order-detail-steps__label--active': order.paid == 1}">
											<view class="order-detail-steps__line"></view>
											<view class="order-detail-steps__text">制作中</view>
											<view class="order-detail-steps__line"></view>
										</view>
										<view class="order-detail-steps__label" :class="{'order-detail-steps__label--active': order.status == 1}" v-if="order.orderType == 'takeout'">
											<view class="order-detail-steps__line"></view>
											<view class="order-detail-steps__text">配送中</view>
											<view class="order-detail-steps__line order-detail-steps__line--transparent"></view>
										</view>
										<view class="order-detail-steps__label" :class="{'order-detail-steps__label--active': order.status >= 2}">
											<view class="order-detail-steps__line"></view>
											<view class="order-detail-steps__text">
												{{ order.orderType == 'takeout' ? '已送达' : '请取餐' }}
											</view>
											<view class="order-detail-steps__line order-detail-steps__line--transparent"></view>
										</view>
									</view>
								</view>
							</view>
							<!-- steps end -->
							<view v-if="order.status==0 && order.paid > 0" class="d-flex just-content-center align-items-center font-size-base text-color-assist mb-40">
								您前面还有 <text class="text-color-primary mr-10 ml-10">{{order.preNum}}</text> 单待制作
							</view>
							<!-- goods begin -->
							<view class="w-100 d-flex flex-column position-relative mt-30 order-detail-page__goods-preview">
								<view class="w-100 d-flex align-items-center mb-40" v-for="(good, index) in order.products" :key="index">
									<view class="d-flex flex-column w-60 overflow-hidden">
										<view class="font-size-lg text-color-base mb-10 text-truncate">{{ good.title }}</view>
										<view class="font-size-sm text-color-assist text-truncate">{{ good.spec }}</view>
									</view>
									<view class="d-flex w-40 align-items-center justify-content-between pl-30">
										<view class="font-size-base text-color-base">x{{ good.number }}</view>
										<view class="font-size-base text-color-base font-weight-bold">￥{{ good.price }}</view>
									</view>
								</view>
							</view>
							<!-- goods end -->
						</view>
					</list-cell>
				</view>
				<view class="order-detail-section">
					<!-- goods begin -->
					<list-cell :hover="false" padding="30rpx 30rpx">
						<view class="w-100 d-flex flex-column position-relative">
							<view class="w-100 d-flex align-items-center mb-40" v-for="(good, index) in order.cartInfo" :key="index">
								<image :src="good.image" mode="aspectFill" class="order-detail-page__thumb"></image>
								<view class="d-flex flex-column w-60 overflow-hidden">
									<view class="font-size-lg text-color-base mb-10 text-truncate">{{ good.title }}</view>
									<view class="font-size-sm text-color-assist text-truncate">{{ good.spec }}</view>
								</view>
								<view class="d-flex w-40 align-items-center justify-content-between pl-30">
									<view class="font-size-base text-color-base">x{{ good.number }}</view>
									<view class="font-size-base text-color-base font-weight-bold">￥{{ good.price }}</view>
								</view>
							</view>
						</view>
					</list-cell>
					<!-- goods end -->
					<!-- payment and amount begin -->
					<list-cell :hover="false" padding="50rpx 30rpx">
						<view class="w-100 d-flex flex-column">
							<view class="order-detail-pay-cell">
								<view>支付方式</view>
								<view class="font-weight-bold">{{ order.statusDto.payType }}</view>
							</view>
							<view class="order-detail-pay-cell">
								<view>订单金额</view>
								<view class="font-weight-bold">￥{{ order.totalPrice }}</view>
							</view>
							<view class="order-detail-pay-cell" v-if="order.orderType == 'takeout'">
								<view>配送费</view>
								<view class="font-weight-bold">￥{{ order.payPostage }}</view>
							</view>
							<view class="order-detail-pay-cell">
								<view>优惠金额</view>
								<view class="font-weight-bold">￥{{ order.couponPrice }}</view>
							</view>
							<view class="order-detail-pay-cell">
								<view>实付金额</view>
								<view class="font-weight-bold">￥{{ order.payPrice }}</view>
							</view>
						</view>
					</list-cell>
					<!-- payment and amount end -->
				</view>
				<view class="order-detail-section">
					<!-- order info begin -->
					<list-cell :hover="false" padding="50rpx 30rpx">
						<view class="w-100 d-flex flex-column">
							<view class="order-detail-pay-cell">
								<view>下单时间</view>
								<view class="font-weight-bold">{{ formatDateTime(order.createTime )}}</view>
							</view>
							<view class="order-detail-pay-cell">
								<view>下单门店</view>
								<view class="font-weight-bold">{{ order.shop.name }}</view>
							</view>
							<view class="order-detail-pay-cell">
								<view>订单号</view>
								<view class="font-weight-bold">{{ order.id }}</view>
							</view>
						</view>
					</list-cell>
					<!-- order info end -->
				</view>
				<!-- order other info begin -->
				<list-cell :hover="false" padding="50rpx 30rpx 20rpx" last>
					<view class="w-100 d-flex flex-column">
						<view class="order-detail-pay-cell">
							<view>享用方式</view>
							<view class="font-weight-bold">{{order.orderType == 'takein' ? '自取' : '外卖'}}</view>
						</view>
						<view class="order-detail-pay-cell">
							<view>取餐时间</view>
							<view class="font-weight-bold">{{order.getTime ? formatDateTime(order.getTime) : '立即取餐'}}</view>
						</view>
						<view class="order-detail-pay-cell">
							<view>制作完成时间</view>
							<view class="font-weight-bold">{{ order.deliveryTime ? formatDateTime(order.deliveryTime) : '无' }}</view>
						</view>
						<view class="order-detail-pay-cell">
							<view>备注</view>
							<view class="font-weight-bold">{{ order.remark ? order.remark : '无' }}</view>
						</view>
					</view>
				</list-cell>
				<!-- order other info end -->
			</view>
			<view class="fixed-bottom flex justify-end bg-white p-2" v-if="order.paid > 0 && order.refundStatus == 0">
				<view class="mr-1"><uv-button type="success" v-if="order.status < 2" :plain="true" size="small" text="确认收到餐" @click="receive(order)"></uv-button></view>
				<view><uv-button type="warning" :plain="true" size="small" text="申请退款" @click="refund(order)"></uv-button></view>
			</view>
		</view>
	</view>
</template>

<script setup>
import {
  ref
} from 'vue'
import { onLoad} from '@dcloudio/uni-app'
import { formatDateTime } from '@/utils/util'
import {
  orderDetail,
  orderReceive,
} from '@/api/order'
const title = ref('订单详情')
const order = ref({
	shop:{name:''},
	statusDto:{payType:''}
})
const numForMading = ref(5)

onLoad((option) => {
	detail(option.id);
})

const detail =  async(id) => {
	let data = await orderDetail(id);
	if (data) {
		order.value = data;
	}
}
const openLocation = () => {
	let shop = order.value.shop;
	uni.openLocation({
		address: shop.addressMap + shop.address + " " + shop.name,
		latitude: parseFloat(shop.lat),
		longitude: parseFloat(shop.lng),
		fail(res) {
			console.log(res);
		}
	});
}
const makePhoneCall = () =>{
	let shop = order.value.shop;
	uni.makePhoneCall({
		phoneNumber: shop.mobile,
		fail(res) {
			console.log(res)
		}
	})
}

// 确认收到货
const receive  = async(order) => {
	let data = await orderReceive({uni:order.orderId});
	if (data) {
		await getOrders(true)
	}
}
//提交退款
const refund = (order) => {
	uni.navigateTo({
		url: '/pages/components/pages/orders/refund?orderId=' + order.orderId + '&payPrice=' + order.payPrice + '&totalPrice=' + order.totalPrice
	})
}

</script>

<style lang="scss" scoped>
$order-detail-padding: $spacing-row-base;
$order-detail-body-padding-bottom: 100rpx;
$order-detail-thumb-size: 120rpx;
$order-detail-thumb-radius: 8rpx;
$order-detail-pay-cell-gap: 40rpx;
$order-detail-sort-num-size: 64rpx;
$order-detail-section-notch: 30rpx;
$order-detail-section-notch-offset: 15rpx;
$order-detail-steps-icon-size: 60rpx;
$order-detail-steps-img-size: 80rpx;
$order-detail-steps-margin-y: 30rpx;
$order-detail-steps-label-gap: $spacing-col-sm;
$order-detail-shop-icon-size: 45rpx;
$order-detail-shop-icon-gap: 40rpx;
$order-detail-goods-offset: -40rpx;
$order-detail-steps-line-height: 2rpx;

@mixin order-detail-section-notch {
	content: '';
	position: absolute;
	bottom: calc(-1 * #{$order-detail-section-notch-offset});
	z-index: 10;
	width: $order-detail-section-notch;
	height: $order-detail-section-notch;
	background-color: $bg-color;
	border-radius: $border-radius-circle;
}

/* #ifdef H5 */
page {
	min-height: 100%;
	background-color: $bg-color;
}
/* #endif */

.order-detail-page {
	padding: $order-detail-padding;
	--order-detail-thumb-size: #{$order-detail-thumb-size};

	&__body {
		padding-bottom: $order-detail-body-padding-bottom;
	}

	&__thumb {
		flex-shrink: 0;
		width: var(--order-detail-thumb-size);
		height: var(--order-detail-thumb-size);
		margin-right: $spacing-row-lg;
		border-radius: $order-detail-thumb-radius;
	}

	&__sort-num {
		font-size: $order-detail-sort-num-size;
		font-weight: bold;
		color: $text-color-base;
		line-height: 2;
	}

	&__shop-icon {
		font-size: $order-detail-shop-icon-size;
		color: $color-primary;

		&--last {
			margin-right: 0;
		}

		&:not(&--last) {
			margin-right: $order-detail-shop-icon-gap;
		}
	}

	&__goods-preview {
		margin-bottom: $order-detail-goods-offset;
	}

	.iconfont-yshop {
		color: $color-primary;
	}
}

.order-detail-section {
	position: relative;

	&::before {
		@include order-detail-section-notch;
		left: calc(-1 * #{$order-detail-section-notch-offset});
	}

	&::after {
		@include order-detail-section-notch;
		right: calc(-1 * #{$order-detail-section-notch-offset});
	}
}

.order-detail-pay-cell {
	display: flex;
	align-items: center;
	justify-content: space-between;
	width: 100%;
	margin-bottom: $order-detail-pay-cell-gap;
	font-size: $font-size-base;
	color: $text-color-base;

	&:last-child {
		margin-bottom: 0;
	}
}

.order-detail-steps {
	&__icons {
		display: flex;
		margin: $order-detail-steps-margin-y 0;
	}

	&__icon-item {
		flex: 1;
		display: flex;
		align-items: center;
		justify-content: center;

		image {
			width: $order-detail-steps-img-size;
			height: $order-detail-steps-img-size;
		}
	}

	&__icon--inactive {
		color: $text-color-assist;
	}

	&__labels {
		display: flex;
		margin-bottom: $order-detail-pay-cell-gap;
	}

	&__label {
		flex: 1;
		display: flex;
		align-items: center;
		justify-content: center;
		font-size: $font-size-base;
		color: $text-color-assist;

		&--active {
			font-weight: bold;
			color: $text-color-base;

			.order-detail-steps__line {
				background-color: $text-color-base;
			}
		}
	}

	&__line {
		flex: 1;
		height: $order-detail-steps-line-height;
		background-color: $text-color-assist;
		transform: scaleY(0.5);

		&--transparent {
			background-color: transparent;
		}
	}

	&__text {
		margin: 0 $order-detail-steps-label-gap;
	}

	.icon-lamp,
	.icon-daojishi,
	.icon-takeout,
	.icon-doorbell {
		font-size: $order-detail-steps-icon-size;
	}
}
</style>

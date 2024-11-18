<template>
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<view class="container position-relative w-100 h-100 overflow-hidden">
		<uv-empty v-if="coupons.length == 0" mode="coupon"></uv-empty>
		
		<scroll-view scroll-y class="coupon-list">
			<view class="wrapper">
				<view class="coupon" v-for="(item, index) in coupons" :key="index" @tap="openDetailModal(item, index)">
					<view class="taobao">
						<view class="ticket" :style="{border: item.id == coupon_id ? '1rpx solid red':''}">
							<view class="left">
								<image class="picture" :src="item.image" mode="aspectFill"></image>
								<view class="introduce">
									<view class="top">
										￥
										<text class="big">{{ item.value }}</text>
										<view>满{{ item.least }}减{{ item.value }}</view>
									</view>
									<view class="type">{{ item.title }}</view>
									<view class="date u-line-1">{{formatDateTime(item.startTime, 'yyyy-MM-dd')}}-{{formatDateTime(item.endTime, 'yyyy-MM-dd')}}</view>
								</view>
							</view>
							<view class="right" @click.stop="" v-if="activeTabIndex == 0">
								<view v-if="item.id != coupon_id" class="use immediate-use" :round="true" @tap="useCouponWith(item)">立即使用</view>
								<view v-else class="use immediate-use" :round="true" @tap="cancelCoupon(item)">取消使用</view>
							</view>
						</view>
					</view>
				</view>
			</view>
		</scroll-view>
		
		<modal custom :show="detailModalVisible" @cancel="closeDetailModal" width="90%">
			<view class="modal-content">
				<view class="d-flex font-size-extra-lg text-color-base just-content-center mb-20">{{ coupon.title }}</view>
				<view class="d-flex font-size-sm text-color-base mb-20">有效期：{{formatDateTime(coupon.startTime, 'yyyy-MM-dd')}}至{{formatDateTime(coupon.endTime, 'yyyy-MM-dd')}}</view>
				<view class="d-flex font-size-sm text-color-base mb-20">领取时间：{{ coupon.createtime_text }}</view>
				<view class="d-flex font-size-sm text-color-base mb-20">卷价值：满{{ coupon.least }}减{{ coupon.value }}</view>
				<view class="d-flex font-size-sm text-color-base mb-20">适用范围：{{ typeInfo(coupon.type) }}</view>
				<view class="d-flex font-size-sm text-color-base mb-20">适用店铺：{{ coupon.shopName }}</view>
				<view class="d-flex align-items-center just-content-center" v-if="activeTabIndex == 0">
					<button type="primary" @tap="useCoupon" class="use-coupon-btn">立即使用</button>
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
/* #ifdef H5 */
page {
	height: 100%;
}
/* #endif */

.container {
	display: flex;
	flex-direction: column;
}


.coupon-list {
	margin-top: 30rpx;
	height:calc(100vh - 120rpx); 
	// height: calc(100vh - 120rpx - 200rpx);
	/* #ifdef H5 */
	// height: calc(100vh - 120rpx - 200rpx - 44px);
	/* #endif */
}

.wrapper {
	padding: 0 20rpx;
	display: flex;
	flex-direction: column;

	.coupon {
		display: flex;
		flex-direction: column;
		background-color: #ffffff;
		margin-bottom: 30rpx;
		//padding: 0 30rpx;
		border-radius: 6rpx;
		box-shadow: 0 10rpx 10rpx -10rpx rgba(15, 15, 15, 0.1);
		position: relative;

		&::before {
			content: '';
			position: absolute;
			background-color: $bg-color;
			width: 30rpx;
			height: 30rpx;
			bottom: 65rpx;
			left: -15rpx;
			border-radius: 100%;
		}

		&::after {
			content: '';
			position: absolute;
			background-color: $bg-color;
			width: 30rpx;
			height: 30rpx;
			bottom: 65rpx;
			right: -15rpx;
			border-radius: 100%;
		}

		.detail {
			padding: 20rpx 0;
			position: relative;

			&::after {
				content: '';
				position: absolute;
				left: 0;
				right: 0;
				bottom: 0;
				border-bottom: 1rpx dashed #c6c6c6;
				transform: scaleY(0.5);
			}

			.coupon-img {
				width: 150rpx;
				height: 150rpx;
				margin-right: 40rpx;
			}
		}
	}
}

.use-coupon-btn {
	width: 95%;
	border-radius: 50rem !important;
}

.taobao {
	background-color: white;
	.title {
		display: flex;
		align-items: center;
		justify-content: space-between;
		margin-bottom: 20rpx;
		font-size: 30rpx;
		.left {
			display: flex;
			align-items: center;
		}
		.store {
			font-weight: 500;
		}
		.buddha {
			width: 70rpx;
			height: 70rpx;
			border-radius: 10rpx;
			margin-right: 10rpx;
		}
		.entrance {
			color: $uv-info;
			border: solid 2rpx $uv-info;
			line-height: 48rpx;
			padding: 0 30rpx;
			background: none;
			border-radius: 15px;
		}
	}
	.ticket {
		display: flex;
		.left {
			width: 70%;
			padding: 20rpx;
			background-color: white; //rgb(255, 245, 244);
			border-radius: 20rpx;
			border-right: dashed 2rpx rgb(224, 215, 211);
			display: flex;
			.picture {
				//width: 172rpx;
				border-radius: 20rpx;
				width: 190rpx;
				height: 190rpx;
			}
			.introduce {
				margin-left: 10rpx;
				.top {
					color: $uv-warning;
					font-size: 28rpx;
					.big {
						font-size: 60rpx;
						font-weight: bold;
						margin-right: 10rpx;
					}
				}
				.type {
					font-size: 28rpx;
					color: $uv-info-dark;
				}
				.date {
					margin-top: 10rpx;
					font-size: 20rpx;
					color: $uv-info-dark;
				}
			}
		}
		.right {
			width: 30%;
			padding: 40rpx 20rpx;
			background-color: white; //rgb(255, 245, 244);
			border-radius: 20rpx;
			display: flex;
			align-items: center;
			.use {
				height: auto;
				padding: 0 20rpx;
				font-size: 24rpx;
				border-radius: 40rpx;
				color: #ffffff !important;
				background-color: $uv-warning !important;
				line-height: 40rpx;
				color: rgb(117, 142, 165);
				margin-left: 20rpx;
			}
			.used {
				height: auto;
				padding: 0 20rpx;
				font-size: 24rpx;
				border-radius: 40rpx;
				//color: #ffffff!important;
				//background-color: $u-type-warning!important;
				line-height: 40rpx;
				//color: rgb(117, 142, 165);
				margin-left: 20rpx;
			}
		}
		.right_log {
			text-align: center;
			width: 30%;
			padding: 80rpx 0rpx;
			background-color: white; //rgb(255, 245, 244);
			border-radius: 20rpx;

			align-items: center;
		}
	}
}
</style>

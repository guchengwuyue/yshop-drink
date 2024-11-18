<template>
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<view class="container position-relative w-100 h-100 overflow-hidden">
		<view class="exchange-box">
			<view class="input-box">
				<input type="text" v-model="exchange_code" placeholder="请输入兑换码" placeholder-class="text-color-assist font-size-base" />
				<button type="primary" @click="exchange">兑换</button>
			</view>
		</view>
		<view class="tabbar">
			<view class="tab" :class="{active: activeTabIndex == index}" 
				v-for="(item, index) in tabs" :key="index" @tap="handleTab(index)">
				<view class="title">{{ item.title }}</view>
			</view>
		</view>
		<view class="flex-fill">
			<scroll-view scroll-y class="coupon-list" @scrolltolower="getCoupons(activeTabIndex)">
				<view class="wrapper"  v-if="0 === activeTabIndex">
					<uv-empty v-if="myCoupons.length == 0" mode="list"></uv-empty>
					<view class="coupon" v-for="(item, index) in myCoupons" :key="index" @tap="openDetailModal(item,index)">
						<view class="taobao">
							<view class="ticket">
								<view class="left">
									<image
										class="picture"
										:src="item.image"
										mode="aspectFill"
									></image>
									<view class="introduce">
										<view class="top">
											￥
											<text class="big">{{item.value}}</text>
											<view>
												满{{item.least}}减{{item.value}}
											</view>
										</view>
										<view class="type">{{ item.title }}</view>
										<view class="date u-line-1">{{formatDateTime(item.startTime, 'yyyy-MM-dd')}}-{{formatDateTime(item.endTime, 'yyyy-MM-dd')}}</view>
									</view>
								</view>
								<view class="right" @click.stop="" v-if="activeTabIndex == 1">
									<view class="use immediate-use" :round="true" @tap="receive(item, index)" >立即领取</view>
								</view>
								<view class="right" @click.stop="" v-if="activeTabIndex == 0">
									<view v-if="item.status == 0" class="use immediate-use" :round="true" @tap="useCouponWith(item)" >立即使用</view>
									<view v-else class="used">已使用</view>
								</view>
							</view>
						</view>
					</view>
				</view>
				<view class="wrapper" v-if="1 === activeTabIndex">
					<uv-empty v-if="notCoupons.length == 0" mode="list"></uv-empty>
					<view class="coupon" v-for="(item, index) in notCoupons" :key="index" @tap="openDetailModal(item,index)">
						<view class="taobao">
							<view class="ticket">
								<view class="left">
									<image
										class="picture"
										:src="item.image"
										mode="aspectFill"
									></image>
									<view class="introduce">
										<view class="top">
											￥
											<text class="big">{{item.value}}</text>
											<view>
												满{{item.least}}减{{item.value}}
											</view>
										</view>
										<view class="type">{{ item.title }}</view>
										<view class="date u-line-1">{{formatDateTime(item.startTime, 'yyyy-MM-dd')}}-{{formatDateTime(item.endTime, 'yyyy-MM-dd')}}</view>
									</view>
								</view>
								<view class="right" @click.stop="" v-if="activeTabIndex == 1">
									<view class="use immediate-use" :round="true" @tap="receive(item, index)" >立即领取</view>
								</view>
								<view class="right" @click.stop="" v-if="activeTabIndex == 0">
									<view v-if="item.status == 0" class="use immediate-use" :round="true" @tap="useCouponWith(item)" >立即使用</view>
									<view v-else class="used">已使用</view>
								</view>
							</view>
						</view>
					</view>
				</view>
			</scroll-view>
		</view>
		<modal custom :show="detailModalVisible" @cancel="closeDetailModal" width="90%">
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
				<view class="d-flex align-items-center just-content-center" v-if="activeTabIndex == 0">
					<button type="primary" @tap="useCoupon" class="use-coupon-btn">立即使用</button>
				</view>
				<view class="d-flex align-items-center just-content-center" v-if="activeTabIndex == 1">
					<button type="primary" @tap="receive(coupon, couponIndex)" class="use-coupon-btn">立即领取</button>
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
const openDetailModal = (coupon,index) => {
	couponIndex.value = index;
	coupon.value = coupon
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

.exchange-box {
	flex-shrink: 0;
	height: 200rpx;
	background-color: #ffffff;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;

	.input-box {
		display: flex;
		align-items: stretch;
		width: 70%;
		flex-shrink: 0;
		input {
			flex: 1;
			height: 80rpx;
			border: 1rpx solid #eee;
			border-right: 0;
			border-radius: 8rpx 0 0 8rpx;
			// padding: 20rpx;
			font-size: $font-size-base;
			color: $text-color-base;
		}
		button {
			border-radius: 0 8rpx 8rpx 0;
			display: flex;
			align-items: center;
		}
	}
}

.tabbar {
	flex-shrink: 0;
	width: 100%;
	height: 120rpx;
	display: flex;
	align-items: center;
	justify-content: center;
	
	.tab {
		flex: 1;
		height: 100%;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		font-size: $font-size-base;
		color: $text-color-base;
		position: relative;
		
		.title {
			padding: 15rpx 0;
		}
		
		&.active {
			color: $color-primary;
			
			.title {
				border-bottom: 5rpx solid $color-primary;
			}
		}
	}
}


.coupon-list {
	height: calc(100vh - 120rpx - 200rpx);
	/* #ifdef H5 */
	height: calc(100vh - 120rpx - 200rpx - 44px);
	/* #endif */
}

.wrapper {
	padding: 0 20rpx;
	display: flex;
	flex-direction: column;
	
	.coupon {
		display: flex;
		flex-direction: column;
		background-color: #FFFFFF;
		margin-bottom: 30rpx;
		//padding: 0 30rpx;
		border-radius: 6rpx;
		box-shadow: 0 10rpx 10rpx -10rpx rgba(15, 15, 15, 0.1);
		position: relative;
		
		&::before {
			content: "";
			position: absolute;
			background-color: $bg-color;
			width: 30rpx;
			height: 30rpx;
			bottom: 65rpx;
			left: -15rpx;
			border-radius: 100%;
		}
		
		&::after {
			content: "";
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
			background-color: white;//rgb(255, 245, 244);
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
				.top{
					color:$uv-warning;
					font-size: 28rpx;
					.big{
						font-size: 60rpx;
						font-weight: bold;
						margin-right: 10rpx;
					}
				}
				.type{
					font-size: 28rpx;
					color: $uv-info-dark;
				}
				.date{
					margin-top: 10rpx;
					font-size: 20rpx;
					color: $uv-info-dark;
				}
			}
		}
		.right {
			width: 30%;
			padding: 40rpx 20rpx;
			background-color: white;//rgb(255, 245, 244);
			border-radius: 20rpx;
			display: flex;
			align-items: center;
			.use {
				height: auto;
				padding: 0 20rpx;
				font-size: 24rpx;
				border-radius: 40rpx;
				color: #ffffff!important;
				background-color: $uv-warning!important;
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
			background-color: white;//rgb(255, 245, 244);
			border-radius: 20rpx;
			
			align-items: center;
			
		}
	}
}
</style>

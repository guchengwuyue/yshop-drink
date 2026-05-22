<template>
	<!-- #ifdef MP-WEIXIN -->
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<!-- #endif -->
	<view class="container score-product-order-detail">
		<view class="score-product-order-detail__body">
			<view class="bg-white">
				<view class="score-product-order-detail__section">
					
					<list-cell :hover="false" padding="50rpx 30rpx">
						<view class="w-100 d-flex flex-column position-relative score-product-order-detail__goods-preview">
							<view class="w-100 d-flex align-items-center mb-40">
								<view class="d-flex flex-column w-60 overflow-hidden">
									<image class="score-product-order-detail__thumb" :src="order.product.image" mode="aspectFill"></image>
								</view>
								<view class="d-flex flex-column w-60 overflow-hidden">
									<view class="font-size-lg text-color-base mb-10 text-truncate">{{ order.product.title }}</view>
								</view>
								<view class="d-flex w-40 align-items-center justify-content-between pl-30">
									<view class="font-size-base text-color-base">x{{ order.number }}</view>
									<view class="font-size-base text-color-base font-weight-bold">{{ order.score }}</view>
								</view>
							</view>
						</view>
					</list-cell>
				</view>
				<view class="score-product-order-detail__section">
					<list-cell :hover="false" padding="50rpx 30rpx">
						<view class="w-100 d-flex flex-column">
				
							<view class="score-product-order-detail__cell">
								<view>消耗积分</view>
								<view class="font-weight-bold">{{ order.totalScore }}</view>
							</view>
						</view>
					</list-cell>
				</view>
				<view class="score-product-order-detail__section">
					<list-cell :hover="false" padding="50rpx 30rpx">
						<view class="w-100 d-flex flex-column">
							<view class="score-product-order-detail__cell">
								<view>订单状态</view>
								<view class="font-weight-bold">{{ order.statusText }}</view>
							</view>
							<view class="score-product-order-detail__cell">
								<view>下单时间</view>
								<view class="font-weight-bold">{{ formatDateTime(order.createTime) }}</view>
							</view>
							<view class="score-product-order-detail__cell">
								<view>订单号</view>
								<view class="font-weight-bold">{{ order.orderId }}</view>
							</view>
						</view>
					</list-cell>
					
					<list-cell :hover="false">
						<view class="w-100 d-flex align-items-center">
							<view class="score-product-order-detail__cell">
								<view>收货地址</view>
							</view>
							<view class="d-flex flex-column">
								<view class="w-100 d-flex align-items-center overflow-hidden">
									<view class="font-size-sm text-color-assist">{{ order.customerAddress + ' ' + order.customerName + ' ' + order.customerPhone }}</view>
								</view> 
							</view>
						</view> 
					</list-cell>
					
					<list-cell :hover="false" padding="50rpx 30rpx" v-if="order.expressCompany">
						<view class="w-100 d-flex flex-column">
							<view class="score-product-order-detail__cell">
								<view>快递公司</view>
								<view class="font-weight-bold">{{order.expressCompany }}</view>
							</view>
							<view class="score-product-order-detail__cell">
								<view>快递单号</view>
								<view class="font-weight-bold">{{order.expressNumber }}<text class="score-product-order-detail__copy" @click="copy()">复制</text></view>
								<!-- <text class="copy" @click="copy()">复制</text> -->
							</view>
						</view>
					</list-cell>
					

					<uv-steps current="0" direction="column" dot>
						<uv-steps-item :title="activity.acceptStation" :desc="activity.acceptTime" :key="index" v-for="(activity, index) in expresssn">
						</uv-steps-item>
					</uv-steps>
				</view>
			</view>
		</view>
		<uv-toast ref="uToast" />
	</view>
	<view class="fixed-bottom flex justify-end bg-white p-2" v-if="order.havePaid > 0">
		<view class="mr-1" v-if="order.haveDelivered ==0 && order.haveReceived == 0"><uv-button type="success"  :plain="true" size="small" text="确认收货" @click="receive(order)"></uv-button></view>
		<view class="mr-1"><uv-button type="error" :plain="true" size="small" text="查看物流" @click="getExpresssn()"></uv-button></view>
	</view>
</template>

<script setup>
import {
  ref,
  computed,
  getCurrentInstance
} from 'vue'
import { useMainStore } from '@/store/store'
import { storeToRefs } from 'pinia'
import { onLoad,onPullDownRefresh,onReachBottom} from '@dcloudio/uni-app'
import { formatDateTime,kmUnit } from '@/utils/util'
import {
  scoreShopOrderDetail,
  scoreShopReceive,
  getLogistic
} from '@/api/score'
const main = useMainStore()
const { proxy } = getCurrentInstance();
const { isLogin } = storeToRefs(main)
const title = ref('积分订详情')

const order = ref({
	product: {title:'',image:''}
})
const id = ref(0)
const expresssn = ref([])

onLoad((option) => {
	id.value = option.id
	detail(option.id)
})
onPullDownRefresh(() => {
	 detail(id.value)
})

const  detail = async(id) =>{
	let data = await scoreShopOrderDetail({id:id});
	uni.stopPullDownRefresh();
	if (data) {
		order.value = data;
	}
}

const getExpresssn = async() => {
	if(order.value.haveDelivered == 0){
		proxy.$refs.uToast.show({
			message: '还未发货，暂无物流信息',
			type: 'error',
		})
		return	
	}
	
	let data = await getLogistic({shipperCode:order.value.expressSn,logisticCode:order.value.expressNumber});
	if (data.success == "false") {
		uni.showToast({
			title: res.reason,
			icon: 'none'
		})
		return	
	}
	expresssn.value = data.traces
}

const copy = () => {
	uni.setClipboardData({
		data: order.value.expressNumber
	})
}

// 确认收到货
const  receive = async(order) => {
	let data = await scoreShopReceive({
		id: order.id
	});
	if (data) {
		proxy.$refs.uToast.show({
			message: '已签收',
			type: 'success',
		})
		setTimeout(function() {
			detail(order.id)
		}, 1000);
	}
}



</script>

<style lang="scss" scoped>
$score-product-order-detail-padding: $spacing-row-base;
$score-product-order-detail-body-padding-bottom: 100rpx;
$score-product-order-detail-thumb-size: 140rpx;
$score-product-order-detail-cell-gap: 40rpx;
$score-product-order-detail-section-notch: 30rpx;
$score-product-order-detail-section-notch-offset: 15rpx;
$score-product-order-detail-goods-offset: -40rpx;
$score-product-order-detail-copy-color: #1296db;
$score-product-order-detail-copy-gap: $spacing-row-base;

@mixin score-product-order-detail-section-notch {
	content: '';
	position: absolute;
	bottom: calc(-1 * #{$score-product-order-detail-section-notch-offset});
	z-index: 10;
	width: $score-product-order-detail-section-notch;
	height: $score-product-order-detail-section-notch;
	background-color: $bg-color;
	border-radius: $border-radius-circle;
}

/* #ifdef H5 */
page {
	min-height: 100%;
	background-color: $bg-color;
}
/* #endif */

.score-product-order-detail {
	padding: $score-product-order-detail-padding;
	--score-product-order-detail-thumb-size: #{$score-product-order-detail-thumb-size};

	&__body {
		padding-bottom: $score-product-order-detail-body-padding-bottom;
	}

	&__goods-preview {
		margin-bottom: $score-product-order-detail-goods-offset;
	}

	&__thumb {
		flex-shrink: 0;
		width: var(--score-product-order-detail-thumb-size);
		height: var(--score-product-order-detail-thumb-size);
	}

	&__copy {
		margin-left: $score-product-order-detail-copy-gap;
		color: $score-product-order-detail-copy-color;
	}
}

.score-product-order-detail__section {
	position: relative;

	&::before {
		@include score-product-order-detail-section-notch;
		left: calc(-1 * #{$score-product-order-detail-section-notch-offset});
	}

	&::after {
		@include score-product-order-detail-section-notch;
		right: calc(-1 * #{$score-product-order-detail-section-notch-offset});
	}
}

.score-product-order-detail__cell {
	display: flex;
	align-items: center;
	justify-content: space-between;
	width: 100%;
	margin-bottom: $score-product-order-detail-cell-gap;
	font-size: $font-size-base;
	color: $text-color-base;

	&:last-child {
		margin-bottom: 0;
	}
}
</style>

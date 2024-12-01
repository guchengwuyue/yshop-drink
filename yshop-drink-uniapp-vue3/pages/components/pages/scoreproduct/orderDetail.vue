<template>
	<!-- #ifdef MP-WEIXIN -->
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<!-- #endif -->
	<view class="container" style="padding:20rpx;">
		<view style="padding-bottom: 100rpx;">
			<view class="bg-white">
				<view class="section">
					
					<list-cell :hover="false" padding="50rpx 30rpx">
						<view class="w-100 d-flex flex-column position-relative" style="margin-bottom: -40rpx;">
							<view class="w-100 d-flex align-items-center mb-40">
								<view class="d-flex flex-column w-60 overflow-hidden">
									<image class="product-image" :src="order.product.image" mode="aspectFill"></image>
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
				<view class="section">
					<list-cell :hover="false" padding="50rpx 30rpx">
						<view class="w-100 d-flex flex-column">
				
							<view class="pay-cell">
								<view>消耗积分</view>
								<view class="font-weight-bold">{{ order.totalScore }}</view>
							</view>
						</view>
					</list-cell>
				</view>
				<view class="section">
					<list-cell :hover="false" padding="50rpx 30rpx">
						<view class="w-100 d-flex flex-column">
							<view class="pay-cell">
								<view>订单状态</view>
								<view class="font-weight-bold">{{ order.statusText }}</view>
							</view>
							<view class="pay-cell">
								<view>下单时间</view>
								<view class="font-weight-bold">{{ formatDateTime(order.createTime) }}</view>
							</view>
							<view class="pay-cell">
								<view>订单号</view>
								<view class="font-weight-bold">{{ order.orderId }}</view>
							</view>
						</view>
					</list-cell>
					
					<list-cell :hover="false">
						<view class="w-100 d-flex align-items-center">
							<view class="pay-cell">
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
							<view class="pay-cell">
								<view>快递公司</view>
								<view class="font-weight-bold">{{order.expressCompany }}</view>
							</view>
							<view class="pay-cell">
								<view>快递单号</view>
								<view class="font-weight-bold">{{order.expressNumber }}<text class="copy" @click="copy()">复制</text></view>
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
.copy {
	color: #1296db;
	margin-left: 10rpx;
}
@mixin arch {
	content: "";
	position: absolute;
	background-color: $bg-color;
	width: 30rpx;
	height: 30rpx;
	bottom: -15rpx;
	z-index: 10;
	border-radius: 100%;
}

.section {
	position: relative;
	
	&::before {
		@include arch;
		left: -15rpx;
	}
	
	&::after {
		@include arch;
		right: -15rpx;
	}
}

.pay-cell {
	width: 100%;
	display: flex;
	align-items: center;
	justify-content: space-between;
	font-size: $font-size-base;
	color: $text-color-base;
	margin-bottom: 40rpx;

	&:nth-last-child(1) {
		margin-bottom: 0;
	}
}

.invote-box {
	position: absolute;
	width: 100%;
	left: 0;
	top: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	
	image {
		width: 30rpx;
		height: 30rpx;
	}
}

.btn-box {
	background-color: #ffffff;
	position: fixed;
	bottom: 0;
	left: 0;
	right: 0;
	height: 120rpx;
	box-shadow: 0 0 20rpx rgba($color: #000000, $alpha: 0.1);
	display: flex;
	align-items: center;
	justify-content: space-evenly;
	z-index: 11;
	
	.item {
		display: flex;
		align-items: center;
		justify-content: center;
		padding: 20rpx 10rpx;
		flex: 1;
		flex-shrink: 0;
		
		button {
			width: 100%;
			border-radius: 50rem !important;
			height: 80rpx;
			display: flex;
			align-items: center;
			justify-content: center;
			padding: 0;
		}
	}
}
.product-image {
	width: 140rpx;
	height: 140rpx;
}
</style>

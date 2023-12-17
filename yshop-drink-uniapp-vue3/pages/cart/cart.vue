<template>
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<view  class="cart-popup">
		 <view class="top flex justify-between">
		   <text>已点{{ getCartGoodsNumber }}份</text>
		   <text @tap="handleCartClear">清空</text>
		 </view>
		 <scroll-view class="cart-list" scroll-y>
		  <view class="wrapper">
		   <uv-empty mode="car" v-if="cart.length == 0"></uv-empty>
		   <view class="item" v-for="(item, index) in cart" :key="index">
			<view class="left">
			 <view class="name">{{ item.name }}</view>
			 <view class="props">{{ item.valueStr }}</view>
			</view>
			<view class="center">
			 <text>￥{{ item.price }}</text>
			</view>
			<view class="right">
			 <button type="default" plain size="mini" class="btn" hover-class="none"
			  @tap="handleCartItemReduce(index)">
			  <view class="iconfont iconsami-select"></view>
			 </button>
			 <view class="number">{{ item.number }}</view>
			 <button type="primary" class="btn" size="min" hover-class="none"
			  @tap="handleCartItemAdd(index)">
			  <view class="iconfont iconadd-select"></view>
			 </button>
			</view>
		   </view>		
		  </view>
		 </scroll-view>
	 </view>
	 <view class="fixed-bottom flex justify-between align-center bg-white ">
	 	<view class="mx-2 ont-weight-light">应付:<text class="text-danger">￥{{ getCartGoodsPrice }}</text></view>
	 	<view><uv-button type="warning" color="#dd524d" :customStyle="customStyle" size="large" text="去结算" @click="toPay"></uv-button></view>
	 </view>
	 <uv-toast ref="uToast"></uv-toast>
</template>

<script setup>
import {
  ref,
  computed
} from 'vue'
import { useMainStore } from '@/store/store'
import { storeToRefs } from 'pinia'
import { onLoad,onShow} from '@dcloudio/uni-app'
const main = useMainStore()
const { orderType,address, store,location,isLogin } = storeToRefs(main)
const title = ref('购物车')
const cart = ref([])
const uToast = ref()

// onLoad(() => {
// 	cart.value = uni.getStorageSync('cart')
// })
onShow(() => {
	//cart.value = []
	cart.value = uni.getStorageSync('cart')
})
const getCartGoodsNumber = computed(() => { //计算购物车总数
	if(cart.value.length == 0) {
		return 0
	}
	return cart.value.reduce((acc, cur) => acc + cur.number, 0)
})
const getCartGoodsPrice = computed(() =>{ //计算购物车总价
	if(cart.value.length == 0) {
		return 0
	}
	let price = cart.value.reduce((acc, cur) => acc + cur.number * cur.price, 0);
	return parseFloat(price).toFixed(2);
})
const customStyle = computed(() =>{ 
	return {
		  paddingLeft:'60rpx',
		  paddingRight:'60rpx'
		}
})
const handleCartItemAdd = (index) => {
	cart.value[index].number += 1
	uni.setStorageSync('cart', JSON.parse(JSON.stringify(cart.value)))
}
const handleCartItemReduce = (index) => {
	if (cart.value[index].number === 1) {
		cart.value.splice(index, 1)
	} else {
		cart.value[index].number -= 1
	}
	if (!cart.value.length) {
		cartPopupVisible.value = false
	}
	uni.setStorageSync('cart', JSON.parse(JSON.stringify(cart.value)))
}
const handleCartClear = () => { //清空购物车
	uni.showModal({
		title: '提示',
		content: '确定清空购物车么',
		success: ({
			confirm
		}) => {
			if (confirm) {
				cart.value = []
				uni.setStorageSync('cart', JSON.parse(JSON.stringify(cart.value)))
			}
		}
	})
}
const toPay = () => {
	
	if(cart.value.length == 0){
		uToast.value.show({message:'请先去点餐哦',type: 'error'});
		return;
	}

	if (!isLogin.value) {
		uni.navigateTo({
			url: '/pages/components/pages/login/login'
		})
		return
	} else {
		if (store.value.status == 0) {
			uToast.value.show({message:'不在店铺营业时间内',type: 'error'});
			return;
		}
		// 判断当前是否在配送范围内
		if (orderType.value == 'takeout' && store.value.distance < store.value.far) {
			uToast.value.show({message:'选中的地址不在配送范围',type: 'error'});
			return;
		}

		uni.showLoading({
			title: '加载中'
		})
		uni.setStorageSync('cart', JSON.parse(JSON.stringify(cart.value)))

		uni.navigateTo({
			url: '/pages/components/pages/pay/pay'
		})
	}

	uni.hideLoading()
}
</script>

<style lang="scss" scoped>
.cart-popup {
		.top {
			background-color: $bg-color-primary;
			//color: $color-primary;
			color: #5A5B5C;
			padding: 10rpx 30rpx;
			font-size: 24rpx;
			text-align: right;
		}
		.cart-list {
			background-color: #ffffff;
			width: 100%;
			overflow: hidden;
			min-height: 1vh;
			max-height: 60vh;
	
			.wrapper {
				height: 100%;
				display: flex;
				flex-direction: column;
				padding: 0 30rpx;
				margin-bottom: 156rpx;
	
				.item {
					display: flex;
					justify-content: space-between;
					align-items: center;
					padding: 30rpx 0;
					position: relative;
	
					&::after {
						content: ' ';
						position: absolute;
						bottom: 0;
						left: 0;
						width: 100%;
						background-color: $border-color;
						height: 2rpx;
						transform: scaleY(0.6);
					}
	
					.left {
						flex: 1;
						display: flex;
						flex-direction: column;
						overflow: hidden;
						margin-right: 30rpx;
	
						.name {
							font-size: $font-size-sm;
							color: $text-color-base;
						}
						.props {
							color: $text-color-assist;
							font-size: 24rpx;
							overflow: hidden;
							text-overflow: ellipsis;
							white-space: nowrap;
						}
					}
	
					.center {
						margin-right: 120rpx;
						font-size: $font-size-base;
					}
	
					.right {
						display: flex;
						align-items: center;
						justify-content: space-between;
	
						.btn {
							width: 46rpx;
							height: 46rpx;
							border-radius: 100%;
							padding: 0;
							text-align: center;
							line-height: 46rpx;
						}
	
						.number {
							font-size: $font-size-base;
							width: 46rpx;
							height: 46rpx;
							text-align: center;
							line-height: 46rpx;
						}
					}
				}
			}
		}
	}
</style>

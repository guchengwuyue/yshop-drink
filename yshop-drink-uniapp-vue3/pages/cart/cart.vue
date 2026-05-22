<template>
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<view class="cart-page">
		 <view class="cart-page__header flex justify-between">
		   <text>已点{{ getCartGoodsNumber }}份</text>
		   <text @tap="handleCartClear">清空</text>
		 </view>
		 <scroll-view class="cart-page__list" scroll-y>
		  <view class="cart-page__list-inner">
		   <uv-empty mode="car" v-if="cart.length == 0"></uv-empty>
		   <view class="cart-item" v-for="(item, index) in cart" :key="index">
			<view class="cart-item__info">
			 <view class="cart-item__name">{{ item.name }}</view>
			 <view class="cart-item__props">{{ item.valueStr }}</view>
			</view>
			<view class="cart-item__price">
			 <text>￥{{ item.price }}</text>
			</view>
			<view class="cart-item__controls">
			 <button type="default" plain size="mini" class="cart-item__btn" hover-class="none"
			  @tap="handleCartItemReduce(index)">
			  <view class="iconfont iconsami-select"></view>
			 </button>
			 <view class="cart-item__qty">{{ item.number }}</view>
			 <button type="primary" class="cart-item__btn" size="min" hover-class="none"
			  @tap="handleCartItemAdd(index)">
			  <view class="iconfont iconadd-select"></view>
			 </button>
			</view>
		   </view>
		  </view>
		 </scroll-view>
	 </view>
	 <view class="fixed-bottom flex justify-between align-center bg-white">
	 	<view class="mx-2 font-weight-light">应付:<text class="text-danger">￥{{ getCartGoodsPrice }}</text></view>
	 	<view><uv-button type="primary" :customStyle="customStyle" size="large" text="去结算" @click="toPay"></uv-button></view>
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
// 购物车页局部 token（与 uni.scss 全局变量配合）
$cart-list-max-height: 60vh;
$cart-list-min-height: 1vh;
$cart-list-bottom-space: 156rpx;
$cart-price-offset: 120rpx;
$cart-control-size: 46rpx;
$cart-divider-height: 2rpx;
$cart-header-color: #5a5b5c;
$cart-gap-sm: 10rpx;

.cart-page {
	--cart-control-size: #{$cart-control-size};
	--cart-list-max-height: #{$cart-list-max-height};

	&__header {
		padding: $cart-gap-sm $spacing-row-lg;
		font-size: $font-size-sm;
		text-align: right;
		color: $cart-header-color;
		background-color: $bg-color-primary;
	}

	&__list {
		width: 100%;
		overflow: hidden;
		min-height: $cart-list-min-height;
		max-height: var(--cart-list-max-height);
		background-color: $text-color-white;
	}

	&__list-inner {
		display: flex;
		flex-direction: column;
		height: 100%;
		padding: 0 $spacing-row-lg;
		margin-bottom: $cart-list-bottom-space;
	}

}

.cart-item {
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding: $spacing-row-lg 0;
	position: relative;

	&::after {
		content: '';
		position: absolute;
		bottom: 0;
		left: 0;
		width: 100%;
		height: $cart-divider-height;
		background-color: $border-color;
		transform: scaleY(0.6);
	}

	&__info {
		flex: 1;
		display: flex;
		flex-direction: column;
		overflow: hidden;
		margin-right: $spacing-row-lg;
	}

	&__name {
		font-size: $font-size-sm;
		color: $text-color-base;
	}

	&__props {
		font-size: $font-size-sm;
		color: $text-color-assist;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
	}

	&__price {
		margin-right: $cart-price-offset;
		font-size: $font-size-base;
	}

	&__controls {
		display: flex;
		align-items: center;
		justify-content: space-between;
	}

	&__btn,
	&__qty {
		width: var(--cart-control-size);
		height: var(--cart-control-size);
		line-height: var(--cart-control-size);
		text-align: center;
	}

	&__btn {
		padding: 0;
		border-radius: $border-radius-circle;
	}

	&__qty {
		font-size: $font-size-base;
	}
}
</style>

<template>
	<!-- #ifdef MP-WEIXIN -->
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<!-- #endif -->
	<view class="px-2 mt-2">
		<view class="bg-white p-2" >
			<uv-form :model="form" ref="uForm" >
				<uv-form-item>
						<view v-if="address.realName">
							<view>{{ address.address }} {{ address.detail }}</view>
							<text>{{ address.realName }} {{ address.phone }}</text>
						</view>
						<view v-else>请选择收货地址</view>
						<template #right>
							<uv-icon name="arrow-right" @click="chooseAddress"></uv-icon>
						</template>
				</uv-form-item>
			</uv-form>
		</view>
		<view class="bg-white p-2 mt-2">
			<view class="mb-5">积分商城</view>
			<view class="flex justify-between">
				<view class="flex">
					<image :src="product.image" mode="aspectFill" class="image"></image>
					<text class="font-size-medium">{{ product.title }}</text>
				</view>
				<view class="flex flex-column">
					<text class="font-size-medium">{{ product.score }}积分</text>
					<text class="font-size-medium text-color-assist mt-2 text-right">×1</text>
				</view>
			</view>
			<view class="flex justify-between mt-5">
				<text class="font-size-medium">积分抵扣</text>
				<text class="font-size-medium">{{ product.score }}</text>
			</view>
			<view class="text-right font-size-lg mt-5 text-color-assist">合计:<text class="font-size-medium text-color-base ">¥{{ product.score }}</text></view>
		</view>
	</view>
	<view class="fixed-bottom flex justify-between align-center bg-white ">
		<view class="mx-2 ont-weight-light">合计:<text class="text-danger">￥{{ product.score }}积分</text></view>
		<view><uv-button type="warning" color="#ffcc00" :customStyle="customStyle" size="large" text="立即兑换" @click="submit"></uv-button></view>
	</view>
	<uv-toast ref="uToast" />
</template>

<script setup>
import {
  ref,
  computed,
  getCurrentInstance
} from 'vue'
import { onReachBottom,onLoad,onPullDownRefresh} from '@dcloudio/uni-app'
import {
  scoreShopExchange,
  scoreShopDetail
} from '@/api/score'
import cookie from '@/utils/cookie'
import { useMainStore } from '@/store/store'
import { storeToRefs } from 'pinia'
const { proxy } = getCurrentInstance();
const main = useMainStore()
const { address,isLogin} = storeToRefs(main)

const title = ref("确认订单")
const product = ref({})

const customStyle = computed(() =>{
	return {
		  paddingLeft:'60rpx',
		  paddingRight:'60rpx'
		}
})

onLoad(() => {
	console.log('address:',address.value)
	if(!isLogin.value) {
		uni.navigateTo({url: '/pages/components/pages/login/login'})
	}
	product.value = cookie.get('score_product')
	
})

// 选择地址
const chooseAddress = () => {
	uni.navigateTo({
		url: '/pages/components/pages/address/address?is_choose=true&scene=scoreShop'
	})
}

// 提交
const submit = async() => {
	if (!address.value.id) {
		proxy.$refs.uToast.show({
			message: '请选择收货地址',
			type: 'warning',
		})
		return
	}
	let data = await scoreShopExchange({
		productId: product.value.id,
		addressId: address.value.id,
		num: 1
	})

	if (data) {
		cookie.remove('score_product')
		setTimeout(function() {
			uni.navigateTo({
				url: '/pages/components/pages/scoreproduct/order'
			})
		}, 1000)
	}
}

</script>

<style lang="scss" scoped>
	.image {
		width: 160rpx;
		height: 160rpx;
		margin-right: 30rpx;
		border-radius: 8rpx;
	}
</style>
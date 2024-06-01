<template>
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<view class="container">
		<view class="bg-white">
			<uv-tabs :list="tabList" :current="current" @change="change" keyName="name" :scrollable="false"></uv-tabs>
		</view>
		<view class="orders-list d-flex flex-column w-100" style="padding: 20rpx; padding-bottom: 0;">
			<view class="order-item" v-for="(item, index) in orders" :key="index" style="margin-bottom: 30rpx;" @tap="detail(item.orderId)">
				<list-cell :hover="false">
					<view class="w-100 d-flex align-items-center">
						<view class="flex-fill d-flex flex-column">
							<view class="font-size-lg text-color-base" style="margin-bottom: 20rpx;">
								{{ item.shop.name }}
							</view>
							<view class="font-size-sm text-color-assist">取餐号：{{ item.numberId }}</view>
							<view class="font-size-sm text-color-assist">订单编号：{{ item.orderId }}</view>
						</view>
						<view class="font-size-lg text-color-primary">
							{{ item.statusDto.title }}
						</view>
					</view>
				</list-cell>
				<list-cell :hover="false" last>
					<view class="w-100 d-flex flex-column">
						<view class="w-100 text-truncate font-size-lg text-color-base" style="margin-bottom: 20rpx;">
						<!-- 	{{ orderGoodsName(item.cartInfo) }} -->
							<view class="flex mb-2" v-for="(good,index) in item.cartInfo" :key="index">  
								<image :src="good.image" mode="aspectFill" class="image"></image>
								<view class="flex flex-column">
									<view class="font-size-medium mt-1 text-color-base">{{ good.title }}</view>
									<view class="font-size-sm mt-1">{{ good.spec }}</view>
									<view class="font-size-sm mt-2">×{{ good.number }}  ¥{{ good.price }}</view>
								</view>
							</view>
						</view>
						<view class="d-flex justify-content-between align-items-center" style="margin-bottom: 30rpx;">
							<view class="font-size-sm text-color-assist">
								{{formatDateTime(item.createTime) }}
							</view>
							<view class="d-flex font-size-sm text-color-base align-items-center">
								<view style="margin-right: 10rpx;">共{{ goodsNum(item.cartInfo) }}件商品，实付</view>
								<view class="font-size-lg">￥{{ item.payPrice }}</view>
							</view>
						</view>
						<view class="d-flex align-items-center justify-content-end">
							<view>
								<button v-if="item.paid > 0 && item.status < 2 && item.refundStatus == 0" class="left-margin"  plain size="mini" @tap.stop="receive(item)">确认收到餐</button>
								<button class="left-margin"  plain size="mini" @tap="detail(item.orderId)">订单详情</button>
							</view>
						</view>
					</view>
				</list-cell>
			</view>
		</view>
		<uv-empty v-if="orders.length == 0" mode="order"></uv-empty>
	</view>
</template>


<script setup>
import {
  ref,
  computed
} from 'vue'
import { useMainStore } from '@/store/store'
import { storeToRefs } from 'pinia'
import { onLoad,onPullDownRefresh,onReachBottom} from '@dcloudio/uni-app'
import { formatDateTime,kmUnit } from '@/utils/util'
import {
  orderGetOrders,
  orderReceive
} from '@/api/order'
const main = useMainStore()
const { isLogin } = storeToRefs(main)
const title = ref('我的订单')

const page = ref(1)
const pageSize = ref(10)
const orders = ref([])
const tabList = ref([{
			type: -1,
			name: '全部',
		}, {
			type: 0,
			name: '待支付',
		}, {
			type: 1,
			name: '进行中'
		}, {
			type: 4,
			name: '已完成'
		}, {
			type: -3,
			name: '退款单'
		}]
)
const current = ref(0)
const type = ref(-1)

const orderGoodsName = computed(() => { //计算单个饮品添加到购物车的数量
	return (goods) => {
		let arr = []
		goods.forEach(good => arr.push(good.title + '*' + good.number))
		return arr.join('，')
	}
})
const goodsNum = computed(() => { //计算单个饮品添加到购物车的数量
	return (goods) => {
		let num = 0;
		goods.forEach(good => num += parseInt(good.number))
		return num;
	}
})
onLoad(() => {
	if(!isLogin.value) {
		uni.navigateTo({url: '/pages/components/pages/login/login'})
	}
	getOrders(false)
})
onPullDownRefresh(() => {
	 getOrders(false)
})
onReachBottom(() => {
	getOrders(false)
})

// tab栏切换
const change = (e) => {
	type.value = e.type
	getOrders(true)
}

const getOrders = async(isRefresh = false) => {
	uni.showLoading({
		title: '加载中'
	})
	if(isRefresh) {
		orders.value = []
		page.value = 1
	}
	let ordersData = await orderGetOrders({page:page.value, limit:pageSize.value,type:type.value});

	if(ordersData) {
		orders.value = orders.value.concat(ordersData)
		page.value += 1
	}
	uni.stopPullDownRefresh();
	uni.hideLoading()
}
const detail = (id) => {
	uni.navigateTo({
		url: '/pages/components/pages/orders/detail?id=' + id
	})
}
// 确认收到货
const receive  = async(order) => {
	let data = await orderReceive({uni:order.orderId});
	if (data) {
		await getOrders(true)
	}
}
	

</script>

<style lang="scss" scoped>
	.left-margin {
		margin-left: 10rpx;
	}
	.image {
		width: 160rpx;
		height: 160rpx;
		margin-right: 30rpx;
		border-radius: 8rpx;
	}
</style>

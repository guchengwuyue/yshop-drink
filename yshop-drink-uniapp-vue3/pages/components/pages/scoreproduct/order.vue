<template>
	<!-- #ifdef MP-WEIXIN -->
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<!-- #endif -->
	<view class="container score-product-orders">
		<view class="bg-white">
			<uv-tabs :list="tabList" :current="current" @change="change" keyName="name" :scrollable="false"></uv-tabs>
		</view>
		<view class="score-product-orders__list d-flex flex-column w-100">
			<view class="score-product-orders-item" v-for="(item, index) in orders" :key="index">
				<list-cell :hover="false">
					<view class="w-100 d-flex align-items-center">
						<view class="flex-fill d-flex flex-column">
							<view class="font-size-sm text-color-assist">订单编号：{{ item.orderId }}</view>
						</view>
						<view class="font-size-lg text-color-primary">
							{{ item.statusText }}
						</view>
					</view>
				</list-cell>
				<list-cell :hover="false" last>
					<view class="w-100 d-flex flex-column">
						<view class="flex ">
							<image :src="item.product.image" mode="aspectFill" class="score-product-orders-item__thumb"></image>
							<view>
								<view class="w-100 text-truncate font-size-lg text-color-base score-product-orders-item__title">
									{{ item.product.title }}
								</view>
								<view class="font-size-sm mt-2">×{{ item.product.number }}  {{ item.product.score }}积分</view>
							</view>
						</view>
						<view class="d-flex justify-content-between align-items-center score-product-orders-item__meta">
							<view class="font-size-sm text-color-assist">
								{{formatDateTime(item.createTime) }}
							</view>
							<view class="d-flex font-size-sm text-color-base align-items-center">
								<view class="score-product-orders-item__summary">共{{item.number }}件商品，消耗积分</view>
								<view class="font-size-lg"> {{ item.totalScore }}</view>
							</view>
						</view>
						<view class="d-flex align-items-center justify-content-end score-product-orders-item__actions">
							<view>
								<button v-if="item.havePaid > 0 && item.haveDelivered == 1 && item.haveReceived == 0"
									class="score-product-orders-item__btn" type="primary" plain size="mini"
									@tap.stop="receive(item)">确认收到货</button>
								<button class="score-product-orders-item__btn" plain size="mini" @tap="detail(item.id)">订单详情</button>
							</view>
						</view>
					</view>
				</list-cell>
			</view>
		</view>
		<uv-empty v-if="orders.length == 0" mode="order"></uv-empty>
	</view>
	<uv-toast ref="uToast" />
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
  scoreShopOrder,
  scoreShopReceive
} from '@/api/score'
const main = useMainStore()
const { proxy } = getCurrentInstance();
const { isLogin } = storeToRefs(main)
const title = ref('我的积分订单')

const type = ref(-1)
const page = ref(1)
const pageSize = ref(10)
const orders = ref([])
const tabList = ref([{
			type: -1,
			name: '全部',
		}, {
			type: 0,
			name: '待发货',
		}, {
			type: 1,
			name: '待收货'
		}, {
			type: 2,
			name: '已完成'
		}]
)
onLoad(() => {
	if (!isLogin.value) {
		uni.navigateTo({
			url: '/pages/components/pages/login/login'
		})
	}
	getOrders(false)
})

onReachBottom(() => {
	getOrders(false)
})
onPullDownRefresh(() => {
	 getOrders(false)
})

// tab栏切换
const change = (e) => {
	type.value = e.type
	getOrders(true)
}

const  getOrders = async(isRefresh = false) => {
	uni.showLoading({
		title: '加载中'
	})
	if (isRefresh) {
		orders.value = []
		page.value = 1
	}
	let data = await scoreShopOrder({
		page: page.value,
		pagesize: pageSize.value,
		type: type.value
	});
	if (data.length > 0) {
		orders.value = orders.value.concat(data)
		page.value += 1
	}
	uni.stopPullDownRefresh();
	uni.hideLoading()
}
const detail = (id) => {
	uni.navigateTo({
		url: '/pages/components/pages/scoreproduct/orderDetail?id=' + id
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
			getOrders(true)
		}, 1000);
	}
}


</script>

<style lang="scss" scoped>
$score-product-orders-list-padding-x: $spacing-row-base;
$score-product-orders-list-padding-bottom: 0;
$score-product-orders-item-gap: $spacing-row-lg;
$score-product-orders-section-gap: $spacing-row-base;
$score-product-orders-btn-gap: $spacing-row-base;
$score-product-orders-summary-gap: $spacing-row-base;
$score-product-orders-thumb-size: 160rpx;
$score-product-orders-thumb-radius: 8rpx;

.score-product-orders {
	--score-product-orders-thumb-size: #{$score-product-orders-thumb-size};

	&__list {
		padding: $score-product-orders-list-padding-x;
		padding-bottom: $score-product-orders-list-padding-bottom;
	}
}

.score-product-orders-item {
	margin-bottom: $score-product-orders-item-gap;

	&__title {
		margin-bottom: $score-product-orders-section-gap;
	}

	&__thumb {
		flex-shrink: 0;
		width: var(--score-product-orders-thumb-size);
		height: var(--score-product-orders-thumb-size);
		margin-right: $spacing-row-lg;
		border-radius: $score-product-orders-thumb-radius;
	}

	&__meta {
		margin-top: $score-product-orders-section-gap;
	}

	&__summary {
		margin-right: $score-product-orders-summary-gap;
	}

	&__actions {
		margin-top: $score-product-orders-section-gap;
	}

	&__btn + &__btn {
		margin-left: $score-product-orders-btn-gap;
	}
}
</style>

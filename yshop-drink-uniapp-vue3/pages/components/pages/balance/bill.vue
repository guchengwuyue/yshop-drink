<template>
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<view>
		<view class="wrap">
			<view class="bg-white" v-if="cate == 0">
				<uv-tabs activeColor="#f29100" ref="tabs" :list="list" :current="current" @change="change" :scrollable="false" swiperWidth="750"></uv-tabs>
			</view>
			<view class="bg-white" v-else>
				<uv-tabs activeColor="#f29100" ref="tabs" :list="list1" :current="current" @change="change" :scrollable="false" swiperWidth="750"></uv-tabs>
			</view>
			<swiper class="swiper-box" :current="swiperCurrent" @transition="transition" @animationfinish="animationfinish">
				
				<swiper-item class="swiper-item" v-for="(item, index) in orderList" :key="index">
					<scroll-view scroll-y style="height: 100%;width: 100%;" @scrolltolower="reachBottom">
						<view class="page-box">
							<view v-if="item.length == 0">
								<view class="centre" v-if="loadStatus[index] != 'loading'">
									<view class="explain">
										您还没有相关的账单
										<view class="tips">可以去看看有那些想买的</view>
									</view>
									<view class="btn">
										<navigator open-type="switchTab" url="/pages/menu/menu">随便逛逛</navigator>
									</view>
								</view>
								<view v-else>
									<u-loadmore :status="loadStatus[index]" bgColor="#f2f2f2"></u-loadmore>
								</view>
							</view>
							<view v-else>
								<view class="order" v-for="(res, resIndex) in orderList[index]" :key="resIndex">
									<view class="type">
										<view>{{res.title}}</view>
										<view>{{res.mark}}</view>
									</view>
									<view class="total">
										<view>
												{{res.pm == 0 ? '-':'＋'}}￥{{res.number}}元
										</view>
										<view>
											{{formatDateTime(res.createTime)}}
										</view>
									</view>
								</view>
								<u-loadmore :status="loadStatus[index]" bgColor="#f2f2f2"></u-loadmore>
							</view>
						</view>
					</scroll-view>
				</swiper-item>
			</swiper>
		</view>
	</view>
</template>

<script setup>
import {
  ref,
  watch
} from 'vue'
import { useMainStore } from '@/store/store'
import { storeToRefs } from 'pinia'
import { onLoad,onShow} from '@dcloudio/uni-app'
import { formatDateTime } from '@/utils/util'
import {
  balanceGetBillList
} from '@/api/user'
const main = useMainStore()
const { isLogin } = storeToRefs(main)
const title = ref('账单')
const orderList = ref([[], [], [], []])
const list = ref([
				{
					name: '全部'
				},
				{
					name: '消费'
				},
				{
					name: '充值'
				},
				{
					name: '退款'
				}
			]
)
const list1 = ref([
				{
					name: '全部'
				},
				{
					name: '消费'
				}
			]
)
const current = ref(0)
const swiperCurrent = ref(0)
const tabsHeight =ref(0)
const dx = ref(0)
const loadStatus = ref(['loadmore','loadmore','loadmore','loadmore'])
const page = ref(1)
const pageSize = ref(20)
const tabs = ref()
const cate = ref(0)

onLoad((option) => {
	console.log('cate:',option.cate)
	cate.value = option.cate
	getBill();
})

const getBill = async() => {
	if (loadStatus.value[swiperCurrent.value] == 'loading') {
		return;
	}
	loadStatus.value.splice(swiperCurrent.value,1,"loading")
	let data = await balanceGetBillList({cate:cate.value,type:swiperCurrent.value,page:page.value,pagesize:pageSize.value});
	if (page.value == 1 ){
		orderList.value[swiperCurrent.value] = [];
	}
	if (data && data.length > 0) {
		page.value++;
		orderList.value[swiperCurrent.value] = orderList.value[swiperCurrent.value].concat(data);
		
		loadStatus.value.splice(swiperCurrent.value,1,"loadmore")
	} else{
		loadStatus.value.splice(swiperCurrent.value,1,"nomore")
	}
}
const reachBottom = () => {
	if (loadStatus.value[swiperCurrent.value] == 'nomore') {
		return;
	}
	getBill();
}
// tab栏切换
const change = (e) => {
	swiperCurrent.value = e.index;
	page.value = 1;
}
const transition = ({ detail: { dx } }) => {
}
const animationfinish = ({ detail }) => {
	swiperCurrent.value = detail.current;
	current.value = detail.current;
	page.value = 1;
	getBill();
}


</script>

<style>
/* #ifndef H5 */
page {
	height: 100%;
	background-color: #f2f2f2;
}
/* #endif */
</style>

<style lang="scss" scoped>
.order {
	width: 710rpx;
	background-color: #ffffff;
	margin: 20rpx auto;
	border-radius: 20rpx;
	box-sizing: border-box;
	padding: 20rpx;
	font-size: 28rpx;
	
	.total {
		display: inline-block;
		text-align: right;
		
		.total-price {
			font-size: 36rpx;
		}
		float: right;
	}
	.type {
		display: inline-block;
		
	}
	
}
.centre {
	text-align: center;
	margin: 200rpx auto;
	font-size: 32rpx;
	image {
		width: 164rpx;
		height: 164rpx;
		border-radius: 50%;
		margin-bottom: 20rpx;
	}
	.tips {
		font-size: 24rpx;
		color: #999999;
		margin-top: 20rpx;
	}
	.btn {
		margin: 80rpx auto;
		width: 200rpx;
		border-radius: 32rpx;
		line-height: 64rpx;
		color: #ffffff;
		font-size: 26rpx;
		background: linear-gradient(270deg, rgba(249, 116, 90, 1) 0%, rgba(255, 158, 1, 1) 100%);
	}
}
.wrap {
	display: flex;
	flex-direction: column;
	height: calc(100vh - var(--window-top));
	width: 100%;
}
.swiper-box {
	flex: 1;
}
.swiper-item {
	height: 100%;
}
</style>


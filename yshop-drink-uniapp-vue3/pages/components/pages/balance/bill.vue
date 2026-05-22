<template>
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<view class="bill-page">
		<view class="bill-page__body">
			<view class="bg-white" v-if="cate == 0">
				<uv-tabs :activeColor="balanceTabActiveColor" ref="tabs" :list="list" :current="current" @change="change" :scrollable="false" swiperWidth="750"></uv-tabs>
			</view>
			<view class="bg-white" v-else>
				<uv-tabs :activeColor="balanceTabActiveColor" ref="tabs" :list="list1" :current="current" @change="change" :scrollable="false" swiperWidth="750"></uv-tabs>
			</view>
			<swiper class="bill-page__swiper" :current="swiperCurrent" @transition="transition" @animationfinish="animationfinish">
				<swiper-item class="bill-page__swiper-item" v-for="(item, index) in orderList" :key="index">
					<scroll-view scroll-y class="bill-page__scroll" @scrolltolower="reachBottom">
						<view class="bill-page__content">
							<view v-if="item.length == 0">
								<view class="bill-empty" v-if="loadStatus[index] != 'loading'">
									<view class="bill-empty__text">
										您还没有相关的账单
										<view class="bill-empty__tips">可以去看看有那些想买的</view>
									</view>
									<view class="bill-empty__btn">
										<navigator open-type="switchTab" url="/pages/menu/menu">随便逛逛</navigator>
									</view>
								</view>
								<view v-else>
									<u-loadmore :status="loadStatus[index]" :bgColor="balancePageBg"></u-loadmore>
								</view>
							</view>
							<view v-else>
								<view class="bill-item" v-for="(res, resIndex) in orderList[index]" :key="resIndex">
									<view class="bill-item__meta">
										<view>{{res.title}}</view>
										<view>{{res.mark}}</view>
									</view>
									<view class="bill-item__amount">
										<view>{{res.pm == 0 ? '-':'＋'}}￥{{res.number}}元</view>
										<view>{{formatDateTime(res.createTime)}}</view>
									</view>
								</view>
								<u-loadmore :status="loadStatus[index]" :bgColor="balancePageBg"></u-loadmore>
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

// 与下方 SCSS token 保持一致，供模板组件颜色属性使用
const balanceTabActiveColor = '#f29100'
const balancePageBg = '#f2f2f2'

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

<style lang="scss">
$balance-page-bg: #f2f2f2;

/* #ifndef H5 */
page {
	height: 100%;
	background-color: $balance-page-bg;
}
/* #endif */
</style>

<style lang="scss" scoped>
// 账单页局部 token（与 uni.scss 全局变量配合；$balance-page-bg 与上方及 script 常量保持一致）
$balance-page-bg: #f2f2f2;
$balance-tab-active: #f29100;
$balance-card-width: 710rpx;
$balance-card-radius: 20rpx;
$balance-card-padding: $spacing-row-base;
$balance-card-gap: $spacing-row-base;
$balance-empty-margin-top: 200rpx;
$balance-empty-btn-width: 200rpx;
$balance-empty-btn-radius: 32rpx;
$balance-empty-btn-height: 64rpx;
$balance-empty-image-size: 164rpx;
$balance-gradient-start: #f9745a;
$balance-gradient-end: #ff9e01;

.bill-page {
	--balance-page-bg: #{$balance-page-bg};
	--balance-tab-active: #{$balance-tab-active};

	&__body {
		display: flex;
		flex-direction: column;
		width: 100%;
		height: calc(100vh - var(--window-top));
	}

	&__swiper {
		flex: 1;
	}

	&__swiper-item {
		height: 100%;
	}

	&__scroll {
		width: 100%;
		height: 100%;
	}
}

.bill-item {
	display: flex;
	align-items: flex-start;
	justify-content: space-between;
	box-sizing: border-box;
	width: $balance-card-width;
	margin: $balance-card-gap auto;
	padding: $balance-card-padding;
	font-size: $font-size-base;
	background-color: $text-color-white;
	border-radius: $balance-card-radius;

	&__meta,
	&__amount {
		display: flex;
		flex-direction: column;
	}

	&__amount {
		flex-shrink: 0;
		text-align: right;
	}
}

.bill-empty {
	margin: $balance-empty-margin-top auto;
	font-size: $font-size-lg;
	text-align: center;

	image {
		width: $balance-empty-image-size;
		height: $balance-empty-image-size;
		margin-bottom: $balance-card-gap;
		border-radius: $border-radius-circle;
	}

	&__tips {
		margin-top: $balance-card-gap;
		font-size: $font-size-sm;
		color: $text-color-assist;
	}

	&__btn {
		width: $balance-empty-btn-width;
		margin: 80rpx auto;
		font-size: 26rpx;
		line-height: $balance-empty-btn-height;
		color: $text-color-white;
		border-radius: $balance-empty-btn-radius;
		background: linear-gradient(270deg, $balance-gradient-start 0%, $balance-gradient-end 100%);
	}
}
</style>


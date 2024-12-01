<template>
	<!-- #ifdef MP-WEIXIN -->
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<!-- #endif -->
	<view>
		<!--商品区-->
		<uv-waterfall v-model="list" :add-time="0" ref="uWaterfall" @changeList="changeList" :left-gap="10"
			:right-gap="10"
			:column-gap="1">
			<template v-slot:list1>
				<view class="demo-warter" v-for="(item, index) in list1" :key="index" @click="goDetail(item)">
					<image :src="item.image" mode="widthFix" style="width: 300rpx;"></image>
					<view class="demo-title">
						{{item.title}}
					</view>
					<view class="demo-price">
						消耗积分:{{item.score}}
					</view>
				</view>
			</template>
			<template v-slot:list2>
				<view class="demo-warter" v-for="(item, index) in list2" :key="index" @click="goDetail(item)">
					<image :src="item.image" mode="widthFix" style="width: 300rpx;"></image>
					<view class="demo-title">
						{{item.title}}
					</view>
					<view class="demo-price">
						消耗积分:{{item.score}}
					</view>
				</view>
			</template>
		</uv-waterfall>
		<uv-load-more v-if="list.length > 0" :status="status"></uv-load-more>
		<uv-empty v-if="list.length == 0" mode="list"></uv-empty>
	</view>
</template>

<script setup>
import {
  ref,
  computed,
  getCurrentInstance
} from 'vue'
import { onReachBottom,onShow,onPullDownRefresh} from '@dcloudio/uni-app'
import {
  scoreShopIndex
} from '@/api/score'
const { proxy } = getCurrentInstance();

const title = ref('积分商品')
const list = ref([])
const page = ref(1)
const pagesize = ref(10)
const status = ref('loadmore')
const list1 = ref([])
const list2 = ref([])

const uWaterfall = ref()

let currentInstance = getCurrentInstance()

onShow(() => {
	getProduct()
})

onReachBottom(async() => {
	if (status.value == 'loading') {
		return;
	}
	page.value++
	status.value = 'loading'; 
	let data = await scoreShopIndex({
		page: page.value,
		pagesize: pagesize.value
	});
	if (data) {
		list.value = list.value.concat(data);
		if (data.length == 0) {
			page.value--;
			status.value = 'nomore';
		} else if (data.length < pagesize.value) {
			status.value = 'nomore';
		} else {
			status.value = 'loadmore';
		}
	} else {
		status.value = 'loadmore';
	}
})

onPullDownRefresh(() => {
	page.value = 1;
	getProduct()
})

const changeList = (e) => {
	console.log('e:',e)
	if(e.name == 'list1'){
		list1.value.push(e.value)
	}else{
		list2.value.push(e.value)
	}
	
}
const goDetail = (item) => {
	uni.navigateTo({
		url: '/pages/components/pages/scoreproduct/detail?id=' + item.id
	})
}
const  getProduct = async() => {
	status.value = 'loading';
	//proxy.$refs.uWaterfall.clear();

	let data = await scoreShopIndex({
		page: page.value,
		pagesize: pagesize.value
	});
	console.log('data:',data)
	uni.stopPullDownRefresh();
	if (data) {
		list.value = data;
		console.log('data2:',list.value)
		if (data.length < pagesize.value) {
			status.value = 'nomore';
		}
	} else {
		status.value = 'nomore';
	}
}


</script>

<style lang="scss">
	.search {
		margin: 10rpx !important;
	}

	.demo-warter {
		border-radius: 8px;
		margin: 5px;
		background-color: #ffcc00;
		padding: 8px;
		position: relative;
	}

	.demo-title {
		font-size: 30rpx;
		margin-top: 5px;
		color: #ffffff;
	}

	.demo-tag {
		display: flex;
		margin-top: 5px;
	}

	.demo-tag-owner {
		background-color: $uv-error;
		color: #FFFFFF;
		display: flex;
		align-items: center;
		padding: 4rpx 14rpx;
		border-radius: 50rpx;
		font-size: 20rpx;
		line-height: 1;
	}

	.demo-tag-text {
		border: 1px solid $uv-primary;
		color: $uv-primary;
		margin-left: 10px;
		border-radius: 50rpx;
		line-height: 1;
		padding: 4rpx 14rpx;
		display: flex;
		align-items: center;
		border-radius: 50rpx;
		font-size: 20rpx;
	}

	.demo-price {
		font-size: 30rpx;
		color: $bg-color;
		margin-top: 5px;
	}

	.demo-shop {
		font-size: 32rpx;
		color: #cdad73;
		margin-top: 5px;
	}

	.page {
		padding: 10px 0;

		.demo-layout {
			text-align: center;
			background-color: #c6caca;
			border-radius: 20rpx;
			margin: 5px 0;
			padding: 3px;
		}

		.select {
			background-color: #eea13c;
			color: #ffffff;
		}
	}
</style>

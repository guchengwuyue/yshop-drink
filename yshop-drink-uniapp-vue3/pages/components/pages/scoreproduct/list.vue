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
				<view class="score-product-list__card" v-for="(item, index) in list1" :key="index" @click="goDetail(item)">
					<image :src="item.image" mode="widthFix" class="score-product-list__image"></image>
					<view class="score-product-list__title">
						{{item.title}}
					</view>
					<view class="score-product-list__score">
						消耗积分:{{item.score}}
					</view>
				</view>
			</template>
			<template v-slot:list2>
				<view class="score-product-list__card" v-for="(item, index) in list2" :key="index" @click="goDetail(item)">
					<image :src="item.image" mode="widthFix" class="score-product-list__image"></image>
					<view class="score-product-list__title">
						{{item.title}}
					</view>
					<view class="score-product-list__score">
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
$score-product-accent: #ffcc00;
$score-product-list-card-radius: 8px;
$score-product-list-card-gap: 5px;
$score-product-list-card-padding: 8px;
$score-product-list-image-width: 300rpx;
$score-product-list-title-gap: 5px;

.score-product-list__card {
	--score-product-accent: #{$score-product-accent};
	--score-product-list-image-width: #{$score-product-list-image-width};

	position: relative;
	margin: $score-product-list-card-gap;
	padding: $score-product-list-card-padding;
	border-radius: $score-product-list-card-radius;
	background-color: var(--score-product-accent);
}

.score-product-list__image {
	width: var(--score-product-list-image-width);
}

.score-product-list__title {
	margin-top: $score-product-list-title-gap;
	font-size: $font-size-paragraph;
	color: $text-color-white;
}

.score-product-list__score {
	margin-top: $score-product-list-title-gap;
	font-size: $font-size-paragraph;
	color: $bg-color;
}
</style>

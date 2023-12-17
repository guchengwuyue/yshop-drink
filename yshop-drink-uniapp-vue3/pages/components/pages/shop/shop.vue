<template>
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<view>
		<view>
			<uv-search margin="30rpx" v-model="keywork" @custom="search(keywork)"></uv-search>
		</view>
		<view v-for="(item,index) in list" :key="index">
			<uni-card @click="choice(item)" :border="item.id == store.id" :title="item.name" :thumbnail="item.image" :thumb-width="80" :sub-title="item.status_text">
				<view class="body">
					<view class="body-left">
						<view>距离您 {{kmUnit(item.dis)}}</view>
						<view v-if="item.distance > 0">配送距离：{{item.distance + 'km '}} & 配送费：{{item.delivery_price}}</view>
						<view v-else>外卖不配送</view>
						<view>{{item.addressMap + ' ' + item.address}}</view>
						<view>营业时间 {{formatDateTime(item.startTime,'hh:mm')}} - {{formatDateTime(item.endTime,'hh:mm')}}</view>
					</view>
					<view class="body-right">
						<uv-button @click="openLocation(item)">导航</uv-button>
						<uv-button @click="call(item.mobile)">致电</uv-button>
					</view>
				</view>
			</uni-card>
		</view>
	</view>
</template>

<script setup>
import {
  ref
} from 'vue'
import { useMainStore } from '@/store/store'
import { storeToRefs } from 'pinia'
import { onLoad,onShow ,onPullDownRefresh,onHide} from '@dcloudio/uni-app'
import { formatDateTime,kmUnit,prePage } from '@/utils/util'
import {
  shopNearby,
  menuGoods
} from '@/api/goods'
import {
  shopGetList
} from '@/api/market'
const main = useMainStore()
const { store,location } = storeToRefs(main)
const title = ref('店铺')
const list = ref([])
const keywork = ref('')
const page = ref(1)
const pagesize = ref(10)
	
onLoad(() => {
	getShop();
})

const getShop = async(keywork = '') => {
	let data = await shopGetList({
		lat: location.value.latitude ? location.value.latitude : 0,
		lng: location.value.longitude ? location.value.longitude : 0,
		kw: keywork,
		shop_id: 0
	});
	if (data) {
		//console.log(data);
		if (page.value == 1) {
			list.value = data;
		} else {
			for(let i in data) {
				list.value.push(data[i]);
			}
		}
	}
}
//打开定位
const openLocation = (shop) => {
	//console.log(shop);
	uni.openLocation({
		latitude: parseFloat(shop.lat),
		longitude: parseFloat(shop.lng),
		name:shop.name,
		address: shop.addressMap + shop.address,
		fail: (res) => {
			console.log(res);
		}
	})
}
// 打电话
const call = (mobile) => {
	uni.makePhoneCall({
		phoneNumber:mobile
	})
}
// 搜索按钮
const search = (keywork) => {
	page.value = 1;
	getShop(keywork);
}
// 选中店铺
const choice = (shop) => {
	main.SET_STORE(shop);
	uni.$emit('refreshMenu')
	uni.switchTab({ 
		url:'/pages/menu/menu',
		success(res) {
		},
		fail(res) {
			console.log(res);
		}
	});
}

	
</script>

<style lang="scss">
	.body {
		
		.body-left {
			display: inline-block;
			width: 77%;
			padding-left: 6rpx;
		}
		.body-right {
			display: inline-block;
			width: 20%;
		}
	}
	
	.u-border {
		border: 1rpx solid #1296db;
	}
</style>

<template>
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<view class="container">
		<view class="main">
			<uv-empty v-if="addresses.length == 0" mode="address"></uv-empty>
			<template v-else>
				<uv-swipe-action>
					<uv-swipe-action-item class="address-wrapper" :options="swipeOption"
						@click="handleSwipeClick(address.id)" v-for="(address, index) in addresses" :key="index">
						<view class="address" @tap="chooseAddress(address)">
							<view class="left flex-fill overflow-hidden mr-20">
								<view class="font-size-lg font-weight-bold text-truncate"
									style="margin-bottom: 10rpx;white-space:normal;">
									{{ address.address + ' ' + address.detail }}
								</view>
								<view class="font-size-sm text-color-assist">
									{{ address.realName }} {{ address.isDefault ? '默认' : '' }} {{ address.phone }}
								</view>
							</view>
							<image src="/static/images/edit.png" class="edit-icon" @tap.stop="edit(address.id)"></image>
						</view>
					</uv-swipe-action-item>
				</uv-swipe-action>
			</template>
		</view>
		<view class="btn-box">
			<button type="primary" size="default" @tap="add">新增地址</button>
		</view>
	</view>
</template>

<script setup>
import {
  ref,
  computed
} from 'vue'
import { useMainStore } from '@/store/store'
import { storeToRefs } from 'pinia'
import { onLoad,onShow } from '@dcloudio/uni-app'
import { formatDateTime,prePage } from '@/utils/util'
import {
  addressAll,
  addressDelete,
  shopGetDistanceFromLocation
} from '@/api/address'
const main = useMainStore()
const { isLogin,addresses,store } = storeToRefs(main)
const title = ref('我的地址')

const scene = ref('menu')
const is_choose = ref(false)
const swipeOption = ref(
[{
	text: '删除',
	style: {
		backgroundColor: '#D12E32'
	}
}])

onLoad((option) => {
	console.log('option:',option)
	is_choose.value = option.is_choose || false
	scene.value = option.scene || 'menu'
})
onShow(() => {
	init();
})

const init = async() => {
	let data = await addressAll();
	if (data) {
		main.SET_ADDRESSES(data)
	}
}
const add = () => {
	uni.navigateTo({
		url: '/pages/components/pages/address/add'
	})
}
const edit = (id) => {
	uni.navigateTo({
		url: '/pages/components/pages/address/add?id=' + id
	})
}
const handleSwipeClick = async(id) => {
	let [error, res] = await uni.showModal({
		title: '提示',
		content: '确定要删除？'
	});
	if (res && res.confirm) {
		let data = await addressDelete({
			id: id
		});
		if (data) {
			const index = addresses.value.findIndex(item => item.id == id)
			const newaddresses = JSON.parse(JSON.stringify(addresses.value))
			newaddresses.splice(index, 1)
			main.SET_ADDRESSES(newaddresses)
			uni.showToast({
				title: '删除成功！',
				icon: 'success'
			})
		}
	}
}
const chooseAddress = async(address) => {
	if (!is_choose.value) {
		return;
	}
	
	console.log('scene.value:',scene.value)

	if (scene.value == 'menu' || scene.value == 'pay') {
		let data = await shopGetDistanceFromLocation({
			lat: address.latitude,
			lng: address.longitude,
			lat2: main.store.lat,
			lng2: main.store.lng
		});
		if (!data) {
			return;
		}
		
		if (parseFloat(data) > store.value.distance) {
			uni.showToast({
				title: '不在配送范围！',
				icon: 'error'
			})
			return;
		}
		main.SET_ADDRESS(address)
		main.SET_ORDER_TYPE('takeout')
		main.store.far = data
		main.store.far_text = data + 'km'
		main.SET_STORE(main.store)
		main.SET_LOCATION({
			latitude: address.lat,
			longitude: address.lng
		});

		if (scene.value == 'menu') {
			uni.switchTab({
				url: '/pages/menu/menu'
			})
		} else if (scene.value == 'pay') {
			uni.navigateBack();
		}
	} else if (scene.value == 'scoreShop') {
		main.SET_ADDRESS(address)
		uni.navigateBack()
	}
}

</script>

<style lang="scss" scoped>
	.container {
		width: 100%;
		height: 100%;
	}

	.main {
		width: 100%;
		padding: 30rpx;
		display: flex;
		flex-direction: column;
		padding-bottom: 100rpx;

		.address-wrapper {
			margin-bottom: 30rpx;
		}

		.address {
			width: 100%;
			padding: 40rpx 30rpx;
			background-color: #FFFFFF;
			display: flex;
			justify-content: space-between;
			align-items: center;

			.right {
				flex: 1;
				overflow: hidden;
				display: flex;
				flex-direction: column;
			}

			.edit-icon {
				width: 50rpx;
				height: 50rpx;
				flex-shrink: 0;
			}
		}
	}

	.btn-box {
		height: 100rpx;
		background-color: #FFFFFF;
		box-shadow: 0 0 20rpx rgba($color: $text-color-assist, $alpha: 0.1);
		position: fixed;
		bottom: 0;
		left: 0;
		right: 0;
		padding: 10rpx 0;
		display: flex;
		align-items: center;
		justify-content: center;

		button {
			height: 80rpx;
			width: 80%;
			border-radius: 50rem !important;
			display: flex;
			align-items: center;
			justify-content: center;
		}
	}
</style>

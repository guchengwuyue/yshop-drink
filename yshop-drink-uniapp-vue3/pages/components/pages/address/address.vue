<template>
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<view class="container address-page">
		<view class="address-page__main">
			<uv-empty v-if="addresses.length == 0" mode="address"></uv-empty>
			<template v-else>
				<uv-swipe-action>
					<uv-swipe-action-item class="address-page__item" :options="swipeOption"
						@click="handleSwipeClick(address.id)" v-for="(address, index) in addresses" :key="index">
						<view class="address-page__card" @tap="chooseAddress(address)">
							<view class="flex-fill overflow-hidden mr-20">
								<view class="address-page__detail font-size-lg font-weight-bold text-truncate">
									{{ address.address + ' ' + address.detail }}
								</view>
								<view class="font-size-sm text-color-assist">
									{{ address.realName }} {{ address.isDefault ? '默认' : '' }} {{ address.phone }}
								</view>
							</view>
							<image src="/static/images/edit.png" class="address-page__edit-icon" @tap.stop="edit(address.id)"></image>
						</view>
					</uv-swipe-action-item>
				</uv-swipe-action>
			</template>
		</view>
		<view class="address-page__footer">
			<button type="primary" size="default" class="rounded-pill address-page__btn" @tap="add">新增地址</button>
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
const swipeOption = ref([{
	text: '删除',
	style: {
		backgroundColor: '#D12E32' // $color-error
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
	uni.showModal({
		title: '提示',
		content: '确定要删除？',
		success: async function (res) {
			if (res.confirm) {
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
			} else if (res.cancel) {
				console.log('用户点击取消');
			}
		}
	});
	// if (res && res.confirm) {
		
	// }
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
$address-padding: $spacing-row-lg;
$address-item-gap: $spacing-row-lg;
$address-card-padding-y: 40rpx;
$address-main-padding-bottom: 100rpx;
$address-footer-height: 100rpx;
$address-footer-padding-y: 10rpx;
$address-btn-height: 80rpx;
$address-btn-width: 80%;
$address-edit-icon-size: 50rpx;
$address-detail-gap: 10rpx;
$address-footer-shadow: 0 0 20rpx rgba($text-color-assist, 0.1);

.address-page {
	--address-edit-icon-size: #{$address-edit-icon-size};
	--address-btn-height: #{$address-btn-height};
	--address-btn-width: #{$address-btn-width};

	&__main {
		display: flex;
		flex-direction: column;
		width: 100%;
		padding: $address-padding;
		padding-bottom: $address-main-padding-bottom;
	}

	&__item {
		margin-bottom: $address-item-gap;
	}

	&__card {
		display: flex;
		align-items: center;
		justify-content: space-between;
		width: 100%;
		padding: $address-card-padding-y $address-padding;
		background-color: $text-color-white;
	}

	&__detail {
		margin-bottom: $address-detail-gap;
		white-space: normal;
	}

	&__edit-icon {
		flex-shrink: 0;
		width: var(--address-edit-icon-size);
		height: var(--address-edit-icon-size);
	}

	&__footer {
		position: fixed;
		right: 0;
		bottom: 0;
		left: 0;
		display: flex;
		align-items: center;
		justify-content: center;
		height: $address-footer-height;
		padding: $address-footer-padding-y 0;
		background-color: $text-color-white;
		box-shadow: $address-footer-shadow;
	}

	&__btn {
		display: flex;
		align-items: center;
		justify-content: center;
		width: var(--address-btn-width);
		height: var(--address-btn-height);
	}
}
</style>

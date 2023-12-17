<template>
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<view class="container">
		<view class="form-box">
			<view class="form">
				<list-cell :hover="false">
					<view class="form-input">
						<view class="label">收货人</view>
						<input class="input" placeholder="请输入收货人" v-model="form.realName" placeholder-class="text-color-assist" />
					</view>
				</list-cell>
				<list-cell :hover="false">
					<view class="form-input">
						<view class="label">联系方式</view>
						<input class="input" placeholder="请输入收货人联系方式" v-model="form.phone" placeholder-class="text-color-assist" />
					</view>
				</list-cell>
				<list-cell :hover="false">
					<view class="form-input">
						<view class="label">收货地址</view>
						<view class="input" @click="chooseLocation">{{form.address ? form.address : '请选择收货地址'}}</view>
					</view>
				</list-cell>
				<list-cell :hover="false">
					<view class="form-input">
						<view class="label">详细地址</view>
						<input class="input" placeholder="请输入收货人详细地址" v-model="form.detail" placeholder-class="text-color-assist" />
					</view>
				</list-cell>
				<list-cell :hover="false">
					<view class="form-input">
						<view class="label">默认地址</view>
						<view class="radio-group">
							<view class="radio" :class="{'checked': !form.isDefault}" style="margin-right: 10rpx;" @tap="form.isDefault=0">否</view>
							<view class="radio" :class="{'checked': form.isDefault}" @tap="form.isDefault=1">是</view>
						</view>
					</view>
				</list-cell>
			</view>
			<view class="btn-section">
				<button type="primary" size="default" @tap="save">保存</button>
			</view>
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
  getAddressAddAndEdit
} from '@/api/address'
const main = useMainStore()
const { isLogin,addresses } = storeToRefs(main)
const title = ref('编辑地址')

const form = ref({
	realName: '',
	isDefault: 0,
	phone: '',
	address: '',
	detail: '',
	latitude: '',
	longitude: ''
})

onLoad((option) => {
	//为了方便演示，这里用本地缓存
	console.log('option:',option)
	if (option.id) {
		form.value = addresses.value.find(item => item.id == option.id)
	}
})

const save = async() => {
	let data = {}
	if (form.value.hasOwnProperty('id')) {
		data = await getAddressAddAndEdit(form.value);
	} else {
		data = await getAddressAddAndEdit(form.value);
	}
	if (data) {
		setTimeout(function(){
			uni.navigateBack()
		}, 2000);
	}
}

const chooseLocation = async() => {
	uni.chooseLocation({
		success: function (res) {
			form.value.address = res.address + ' ' + res.name;
			form.value.latitude = res.latitude;
			form.value.longitude = res.longitude;
		}
	});
	// let [error, res] = await uni.chooseLocation();
	// if (res) {
	// 	form.value.address = res.address + ' ' + res.name;
	// 	form.value.latitude = res.latitude;
	// 	form.value.longitude = res.longitude;
	// }
}

</script>

<style lang="scss" scoped>
	.form-box {
		width: 100%;
		height: 100%;
		padding: 30rpx;
		display: flex;
		flex-direction: column;
		color: $text-color-base;

		.form-input {
			display: flex;
			align-items: center;
			width: 100%;
		}

		.label {
			width: 200rpx;
			font-size: $font-size-lg;
			color: $text-color-base;
			font-weight: 500;
		}

		.input {
			flex: 1;
			display: flex;
			align-items: center;
		}

		.radio-group {
			display: flex;
			justify-content: flex-start;

			.radio {
				padding: 10rpx 30rpx;
				border-radius: 6rpx;
				border: 2rpx solid $text-color-assist;
				color: $text-color-assist;
				font-size: $font-size-base;

				&.checked {
					background-color: $color-primary;
					color: $text-color-white;
					border: 2rpx solid $color-primary;
				}
			}
		}

		.btn-section {
			flex: 1;
			display: flex;
			align-items: center;
			justify-content: center;

			button {
				font-size: $font-size-base;
				height: 90rpx;
				border-radius: 50rem !important;
				width: 85%;
				display: flex;
				align-items: center;
				justify-content: center;
			}
		}
	}
</style>

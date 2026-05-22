<template>
	<!-- <layout> -->
		<uv-navbar
		  :fixed="false"
		  :title="title"
		  left-arrow
		  @leftClick="$onClickLeft"
		/>
		<view class="logout-page">
			<button type="primary" size="default" class="logout-page__btn" @click="logout">
				确定退出登录
			</button>
			<uv-toast ref="uToast"></uv-toast>
		</view>
	<!-- </layout> -->
</template>

<script setup>
import {
  ref
} from 'vue'
import { useMainStore } from '@/store/store'
import { storeToRefs } from 'pinia'
const main = useMainStore()
const { member,isLogin } = storeToRefs(main)
const uToast = ref()
const title = ref('退出')

const logout = () => {
	uni.removeStorageSync( 'userinfo');
	uni.removeStorageSync( 'accessToken');
	main.SET_MEMBER({});
	main.SET_TOKEN('');
	uToast.value.show({
		message: '已退出',
		type: 'success'
	});
	setTimeout(function() {
		uni.redirectTo({
			url: '/pages/components/pages/login/login',
		})
	}, 1000);
}
	
</script>

<style lang="scss" scoped>
.logout-page {
	display: flex;
	height: 100%;

	&__btn {
		margin: auto;
	}
}
</style>

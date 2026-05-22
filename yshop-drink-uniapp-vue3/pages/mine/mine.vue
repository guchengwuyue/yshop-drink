<template>
	<layout>
		<uv-navbar
		  :fixed="false"
		  :title="title"
		  left-arrow
		  @leftClick="$onClickLeft"
		/>
		<view class="container mine-page">
			<view class="mine-page__user-section">
				<!-- user box begin -->
				<view class="mine-user d-flex flex-column bg-white">

					<view class="mine-user__header d-flex align-items-center">
						<view class="mine-user__avatar rounded-circle">
							<image :src="isLogin ? member.avatar ? member.avatar : '/static/images/mine/default.png' : '/static/images/mine/default.png'"></image>
						</view>
						<view class="mine-user__info d-flex flex-column flex-fill overflow-hidden">
							<view v-if="isLogin"
								class="font-size-lg font-weight-bold d-flex justify-content-start align-items-center"
								@tap="serv({type:'pages',pages:'/pages/components/pages/mine/userinfo'})">
								<view class="text-truncate">{{ member.nickname }}</view>
								<view class="iconfont iconarrow-right line-height-100"></view>
							</view>
							<view v-else class="font-size-lg font-weight-bold" @tap="login">游客</view>
						</view>
					</view>
					<!-- user grid begin -->
					<view class="mine-user__stats w-100 d-flex align-items-center just-content-center">
						<view class="mine-user__stat" @tap="serv({type:'pages',pages:'/pages/components/pages/coupons/coupons'})">
							<view class="value font-size-extra-lg font-weight-bold text-color-base">
								{{ isLogin ? member.couponCount : 0}}
							</view>
							<view class="font-size-sm text-color-assist">优惠券</view>
						</view>
						<view class="mine-user__stat" @tap="serv({type:'pages', pages: '/pages/components/pages/balance/bill?cate=1'})">
							<view class="value font-size-extra-lg font-weight-bold text-color-base">
								{{ isLogin ? member.integral : 0 }}
							</view>
							<view class="font-size-sm text-color-assist">积分</view>
						</view>
						<view class="mine-user__stat">
							<view class="value font-size-extra-lg font-weight-bold text-color-base">
								{{ isLogin ? member.nowMoney : 0 }}
							</view>
							<view class="font-size-sm text-color-assist">余额</view>
						</view>
						<view class="mine-user__stat" @tap="serv({type:'pages', pages: '/pages/components/pages/balance/bill?cate=0'})">
							<view class="value font-size-extra-lg font-weight-bold text-color-base">
								{{ isLogin ? member.sumMoney : 0 }}
							</view>
							<view class="font-size-sm text-color-assist">历史消费</view>
						</view>
					</view>
					<!-- user grid end -->
				</view>
				<!-- user box end -->

			</view>
			<!-- service box begin -->
			<view class="mine-service">
				<view class="mine-service__title font-size-lg text-color-base font-weight-bold">我的服务</view>
				<view class="u-m-t-20">
					<uv-cell-group>
						<block v-for="(item, index) in services" :key='index'>
							<uv-cell :title="item.name" v-if="item.type == 'contact'" :isLink="true">
								<template #icon>
									<image :src="item.image" class="mine-service__icon mr-1"></image>
								</template>
							</uv-cell>
							<uv-cell :isLink="true" :title="item.name" v-else-if="item.type == 'call'" v-on:click="makePhoneCall(item.phone)">
								<template #icon>
									<image :src="item.image" class="mine-service__icon mr-1"></image>
								</template>
							</uv-cell>
							<uv-cell :isLink="true" :title="item.name" v-else @tap="serv(item)">
								<template #icon>
									<image :src="item.image" class="mine-service__icon mr-1"></image>
								</template>
							</uv-cell>
						</block>
					</uv-cell-group>
				</view>
			</view>
		</view>
	</layout>
</template>

<script setup>
import {
  ref,
  computed
} from 'vue'
import { useMainStore } from '@/store/store'
import { storeToRefs } from 'pinia'
import { onLoad,onShow} from '@dcloudio/uni-app'
import { formatDateTime,kmUnit } from '@/utils/util'
import {
  userGetUserInfo,
  mineService
} from '@/api/user'
const main = useMainStore()
const { member,isLogin } = storeToRefs(main)

const title = ref('个人中心')
const services = ref([])

const growthValue = computed(() => { 
	if (!isLogin.value) return 0
	const {
		currentValue,
		needValue
	} = member.value
	return currentValue / (currentValue + needValue) * 100
})

onLoad(() => {
	getServices();
})	
onShow(() => {
	getUserInfo();
})


const getUserInfo = async() => {
	if (isLogin.value) {
		let data = await userGetUserInfo();
		if (data) {
			main.SET_MEMBER(data);
		}
	}
}
const getServices = async() => {
	let data = await mineService();
	if (data) {
		services.value = data;
	}
}
const makePhoneCall = (phoneNumber) => {
	uni.makePhoneCall({
		phoneNumber: phoneNumber,
	})
}
const login = () => {
	uni.navigateTo({
		url: '/pages/components/pages/login/login'
	})
}
const packages = () => {
	if (!isLogin.value) {
		login()
		return
	}
	uni.navigateTo({
		url: '/pages/components/pages/packages/index'
	})
}
const serv = (item) => {
	switch (item.type) {
		case 'pages':
			if (!isLogin.value) {
				login()
				return
			}
			uni.navigateTo({
				url: item.pages
			})
			break;
		case 'miniprogram':
			uni.navigateToMiniProgram({
				appId: item.app_id
			})
			break;
		case 'menu':
			uni.navigateTo({
				url: '/pages/components/pages/mine/service?id=' + item.id + '&name=' + item.name
			})
			break;
		case 'content':
			uni.navigateTo({
				url: '/pages/components/pages/mine/content?id=' + item.id + '&name=' + item.name
			})
			break;
	}
}



</script>

<style lang="scss" scoped>
// 个人中心页局部 token（与 uni.scss 全局变量配合）
$mine-section-padding-x: $spacing-row-lg;
$mine-user-box-radius: $border-radius-lg;
$mine-user-box-margin-top: 70rpx;
$mine-user-box-margin-bottom: $spacing-row-lg;
$mine-avatar-size: 160rpx;
$mine-avatar-image-size: 140rpx;
$mine-avatar-offset-top: -35rpx;
$mine-avatar-margin-x: 35rpx;
$mine-avatar-radius: 20rpx;
$mine-avatar-shadow: 0 0 20rpx rgba(0, 0, 0, 0.2);
$mine-stat-width: 25%;
$mine-stat-padding: $spacing-row-lg;
$mine-stat-value-gap: $spacing-row-base;
$mine-info-margin-top: $spacing-row-base;
$mine-service-padding-y: 32rpx;
$mine-service-padding-bottom: 10rpx;
$mine-service-icon-size: $img-size-sm;
$mine-service-title-gap: $spacing-row-base;

/* #ifdef H5 */
page {
	height: auto;
	min-height: 100%;
}
/* #endif */

.mine-page {
	--mine-avatar-size: #{$mine-avatar-size};
	--mine-avatar-image-size: #{$mine-avatar-image-size};
	--mine-service-icon-size: #{$mine-service-icon-size};

	&__user-section {
		padding: 0 $mine-section-padding-x;
	}
}

.mine-user {
	position: relative;
	margin-top: $mine-user-box-margin-top;
	margin-bottom: $mine-user-box-margin-bottom;
	border-radius: $mine-user-box-radius;
	box-shadow: $box-shadow;

	&__info {
		margin-top: $mine-info-margin-top;
	}

	&__avatar {
		position: relative;
		display: flex;
		align-items: center;
		justify-content: center;
		flex-shrink: 0;
		width: var(--mine-avatar-size);
		height: var(--mine-avatar-size);
		margin-top: $mine-avatar-offset-top;
		margin-left: $mine-avatar-margin-x;
		margin-right: $mine-avatar-margin-x;
		border-radius: $mine-avatar-radius;
		background-color: $text-color-white;
		box-shadow: $mine-avatar-shadow;

		image {
			width: var(--mine-avatar-image-size);
			height: var(--mine-avatar-image-size);
			border-radius: $border-radius-circle;
		}
	}

	&__stat {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		width: $mine-stat-width;
		padding: $mine-stat-padding;

		.value {
			margin-bottom: $mine-stat-value-gap;
		}
	}
}

.mine-service {
	width: 100%;
	padding: $mine-service-padding-y $mine-section-padding-x $mine-service-padding-bottom;
	background-color: $text-color-white;
	box-shadow: $box-shadow;

	&__title {
		margin-bottom: $mine-service-title-gap;
	}

	&__icon {
		width: var(--mine-service-icon-size);
		height: var(--mine-service-icon-size);
		flex-shrink: 0;
	}
}
</style>

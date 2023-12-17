<template>
	<layout>
		<uv-navbar
		  :fixed="false"
		  :title="title"
		  left-arrow
		  @leftClick="$onClickLeft"
		/>
		<view class="container">
			<view style="padding: 0 30rpx;">
				<!-- user box begin -->
				<view class="d-flex flex-column bg-white user-box">

					<view class="d-flex align-items-center">
						<view class="avatar rounded-circle">
							<image :src="isLogin ? member.avatar ? member.avatar : '/static/images/mine/default.png' : '/static/images/mine/default.png'"></image>
						</view>
						<view class="d-flex flex-column flex-fill overflow-hidden" style="margin-top: 20rpx;">
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
					<view class="w-100 d-flex align-items-center just-content-center">
						<view class="user-grid" @tap="serv({type:'pages',pages:'/pages/components/pages/coupons/coupons'})">
							<view class="value font-size-extra-lg font-weight-bold text-color-base">
								{{ isLogin ? member.couponCount : 0}}
							</view>
							<view class="font-size-sm text-color-assist">优惠券</view>
						</view>
						<view class="user-grid"  @tap="serv({type:'pages', pages: '/pages/components/pages/balance/bill?cate=1'})">
							<view class="value font-size-extra-lg font-weight-bold text-color-base">
								{{ isLogin ? member.integral : 0 }}
							</view>
							<view class="font-size-sm text-color-assist">积分</view>
						</view>
						<view class="user-grid">
							<view class="value font-size-extra-lg font-weight-bold text-color-base">
								{{ isLogin ? member.nowMoney : 0 }}
							</view>
							<view class="font-size-sm text-color-assist">余额</view>
						</view>
						<view class="user-grid" @tap="serv({type:'pages', pages: '/pages/components/pages/balance/bill?cate=0'})">
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
			<view class="service-box">
				<view class="font-size-lg text-color-base font-weight-bold" style="margin-bottom: 20rpx;">我的服务</view>
				<view class="u-m-t-20">
					<uv-cell-group>
						<block v-for="(item, index) in services" :key='index'>
							<uv-cell :title="item.name" v-if="item.type == 'contact'" :isLink="true">
								<template #icon>
									<image :src="item.image" style="width: 40rpx;height: 40rpx;" class="mr-1"></image>
								</template>
							</uv-cell>
							<uv-cell :isLink="true" :title="item.name" v-else-if="item.type == 'call'" v-on:click="makePhoneCall(item.phone)">
								<template #icon>
									<image :src="item.image" style="width: 40rpx;height: 40rpx;" class="mr-1"></image>
								</template>
							</uv-cell>
							<uv-cell :isLink="true" :title="item.name" v-else @tap="serv(item)">
								<template #icon>
									<image :src="item.image" style="width: 40rpx;height: 40rpx;" class="mr-1"></image>
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
	page {
		height: auto;
		min-height: 100%;
	}

	.bg {
		width: 100%;
		height: calc(410 / 594 * 750rpx);
	}

	.hym-btn {
		position: absolute;
		top: 40rpx;
		right: 40rpx;
		color: $color-primary;
		display: flex;
		align-items: center;
		justify-content: center;
		border-radius: 50rem;
		font-size: $font-size-sm;
		box-shadow: 0 0 20rpx rgba(66, 66, 66, 0.1);

		&::after {
			border: 0;
		}

		image {
			width: 30rpx;
			height: 30rpx;
			margin-right: 10rpx;
		}
	}

	.user-box {
		position: relative;
		border-radius: 8rpx;
		margin-bottom: 30rpx;
		margin-top: 70rpx;
		box-shadow: $box-shadow;
	}

	.avatar {
		position: relative;
		margin-top: -35rpx;
		margin-left: 35rpx;
		margin-right: 35rpx;
		width: 160rpx;
		height: 160rpx;
		border-radius: 20rpx;
		display: flex;
		align-items: center;
		justify-content: center;
		background-color: #FFFFFF;
		box-shadow: 0 0 20rpx rgba($color: #000000, $alpha: 0.2);

		image {
			width: 140rpx;
			height: 140rpx;
			border-radius: 100%;
		}

		.badge {
			position: absolute;
			right: -10rpx;
			bottom: -10rpx;
			background-color: #FFFFFF;
			border-radius: 50rem;
			display: flex;
			align-items: center;
			justify-content: center;
			color: $color-warning;
			font-size: 24rpx;
			padding: 8rpx 16rpx;
			box-shadow: 0 0 20rpx rgba($color: #000000, $alpha: 0.2);

			image {
				width: 30rpx;
				height: 30rpx;
			}
		}
	}

	.level-benefit {
		margin-left: 35rpx;
		padding: 10rpx 0 10rpx 30rpx;
		border-radius: 50rem 0 0 50rem;
	}

	.user-grid {
		width: 25%;
		padding: 30rpx;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;

		.value {
			margin-bottom: 20rpx;
		}
	}

	.level-benefit-box {
		border-radius: 8rpx;
		margin-bottom: 30rpx;
		box-shadow: 0 10rpx 8rpx rgba($color: #878889, $alpha: 0.1);
		width: 100%;
		display: flex;
		padding: 30rpx;
		flex-direction: column;
		background-color: #FFFFFF;

		.row {
			display: flex;
			padding: 30rpx 0 20rpx;
			justify-content: space-around;
			align-items: center;

			.grid {
				width: 20%;
				display: flex;
				flex-direction: column;
				font-size: $font-size-sm;
				color: $text-color-assist;
				align-items: center;

				image {
					width: 80rpx;
					height: 80rpx;
					margin-bottom: 10rpx;
				}

			}
		}
	}


	.banner {
		width: 100%;
		border-radius: 8rpx;
		margin-bottom: 30rpx;
	}

	.service-box {
		width: 100%;
		background-color: #FFFFFF;
		padding: 32rpx 30rpx 10rpx;
		box-shadow: $box-shadow;

		.row {
			display: flex;
			flex-wrap: wrap;
			color: $text-color-assist;
			font-size: $font-size-sm;
			padding-bottom: -40rpx;

			.grid {
				display: flex;
				flex-direction: column;
				justify-content: center;
				align-items: center;
				margin-bottom: 40rpx;
				width: 25%;
				position: relative;

				image {
					width: 80rpx;
					height: 80rpx;
					margin-bottom: 20rpx;
				}
			}

			.opacity-0 {
				position: absolute;
				width: 100%;
				height: 100%;
				opacity: 0;
				z-index: 10;
			}

		}
	}
</style>

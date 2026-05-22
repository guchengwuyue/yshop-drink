<template>
  <layout>
    <uv-sticky
      bg-color="#F5F5F5"
      offset-top="0"
      customNavHeight="0"
    >
      <uv-navbar
        :fixed="false"
        :safeAreaInsetTop="true"
        height="0"
        bgColor="transparent"
        leftIcon=""
      />
      <!-- #ifndef MP -->
      <blank size="10"></blank>
      <!-- #endif -->

      <blank size="5"></blank>
    </uv-sticky>

    <blank size="10"></blank>
	<view class="container index-page">
			<view class="index-banner">
				<uv-swiper class="index-banner__swiper" height="300" imgMode="aspectFill" keyName="image" :list="listAds" indicatorMode="dot" indicatorStyle="bottom"></uv-swiper>
				<view class="index-banner__intro">
					<view class="index-banner__greet">您好，{{ isLogin ? member.nickname : '游客' }}</view>
					<view class="index-banner__note">java-springboot-意向点餐外卖系统</view>
				</view>
			</view>
			<view class="index-content">
				<view class="flex justify-between index-menu">
					<view class="flex flex-column align-center index-menu__item" @tap="takein">
						<view><image src="/static/images/index002.png" mode="aspectFit" class="index-menu__icon"></image></view>
						<view>自取</view>
						<view class="font-small text-light-black">下单免排队</view>
					</view>
					<view class="flex flex-column align-center index-menu__item" @tap="takeout">
						<view><image src="/static/images/index003.png" mode="aspectFit" class="index-menu__icon"></image></view>
						<view>外卖</view>
						<view class="font-small text-light-black">美食送到家</view>
					</view>
				</view>
				 <view class="index-card">
					<view class="index-card__main">
						<view class="index-card__header">
							<text class="index-card__title">我的卡券</text>
							<text class="index-card__value">{{member.couponCount}}</text>
						</view>
						<view class="index-card__desc">
							可抵扣商品价格哦
						</view>
					</view>
					<view class="index-card__action" @tap="coupons">
						去领个券
					</view>
				</view>
				<view class="index-card">
					<view class="index-card__main" @tap="goScore">
						<view class="index-card__header">
							<text class="index-card__title">积分商城</text>
						</view>
						<view class="index-card__desc">
							进入积分商城兑换奈雪券及周边好礼
						</view>
					</view>
					<view class="index-card__action index-card__action--with-icon" @tap="goScore">
						<image src="/static/images/jifen.png" class="index-card__action-icon"></image>
						<text>逛一逛</text>
					</view>
				</view>
			</view>
	</view>
  </layout>
</template>

<script setup>
import {
  ref
} from 'vue'
import { onLoad } from '@dcloudio/uni-app'
import {
  menuAds
} from '@/api/market'
import { storeToRefs } from 'pinia'
import { useMainStore } from '@/store/store'
//
const main = useMainStore()
const { member,store, isLogin} = storeToRefs(main)
//const store = ref(main.store)
const listAds = ref([])
// const isLogin = ref(main.isLogin)

const handGetListAds = async () => {
	let shop_id = store.id ? store.id : 0;
	let data = await menuAds({
		shop_id: shop_id
	});
	if (data) {
		listAds.value = data.list;
		console.log('listAds:',listAds.value)
		uni.setStorage({
				key: 'isActive',
				data: data.isActive
			});
		if(data.list.length > 0){
			uni.setStorage({
					key: 'shopAd',
					data: data.list[0].image
			 });
			}
		}
}

const takein = () => {
	main.SET_ORDER_TYPE('takein')
	uni.switchTab({
		url: '/pages/menu/menu'
	})
}

const takeout = () => {
	main.SET_ORDER_TYPE('takeout')
	uni.switchTab({
		url: '/pages/menu/menu'
	}) 
}

const coupons = () => { 
	console.log("--> % orderType:\n", main.orderType)
	console.log("--> % isLogin:\n", main.isLogin)
	if(!main.isLogin) {
		uni.navigateTo({url: '/pages/components/pages/login/login'})
		return
	}
	uni.navigateTo({
		url: '/pages/components/pages/coupons/coupons'
	})
}

const goScore = () => { 
	uni.navigateTo({
		url: '/pages/components/pages/scoreproduct/list'
	})
}




onLoad(() => {
 // main.init()
  handGetListAds()
})

</script>

<style lang="scss">
// 首页局部尺寸 token（与 uni.scss 全局变量配合）
$index-banner-height: 300rpx;
$index-menu-item-width: 355rpx;
$index-card-radius: 10rpx;
$index-content-padding-x: 15rpx;
$index-intro-offset-x: 40rpx;
$index-intro-offset-y: 50rpx;
$index-value-font-size: 44rpx;
$index-gap-sm: 10rpx;

/* #ifdef H5 */
page {
	height: auto;
	min-height: 100%;
}
/* #endif */

.index-page {
	--index-banner-height: #{$index-banner-height};
	--index-menu-item-width: #{$index-menu-item-width};

	.index-banner {
		position: relative;
		width: 100%;

		&__swiper {
			width: 100%;
			height: var(--index-banner-height);
		}

		&__intro {
			position: absolute;
			top: calc(#{$index-intro-offset-y} + var(--status-bar-height));
			left: $index-intro-offset-x;
			color: $text-color-white;
			display: flex;
			flex-direction: column;
		}

		&__greet {
			font-size: $font-size-lg;
			margin-bottom: $index-gap-sm;
		}

		&__note {
			font-size: $font-size-sm;
		}
	}

	.index-content {
		padding: 0 $index-content-padding-x;
	}

	.index-menu {
		margin-top: $spacing-row-base;

		&__item {
			width: var(--index-menu-item-width);
			padding-bottom: $spacing-row-lg;
			background-color: $text-color-white;
		}

		&__icon {
			width: 100rpx;
			height: 100rpx;
			margin-top: $spacing-row-lg;
		}
	}

	.index-card {
		position: relative;
		display: flex;
		align-items: center;
		justify-content: center;
		margin: $index-gap-sm 0;
		padding: 25rpx;
		border-radius: $index-card-radius;
		background-color: $text-color-white;
		box-shadow: $box-shadow;

		&__main {
			flex: 1;
			display: flex;
			flex-direction: column;
			justify-content: center;
		}

		&__header {
			display: flex;
			align-items: center;
		}

		&__title {
			margin-right: $index-gap-sm;
			font-size: $font-size-base;
			color: $text-color-base;
		}

		&__value {
			font-size: $index-value-font-size;
			font-weight: bold;
		}

		&__desc {
			display: flex;
			align-items: center;
			font-size: $font-size-sm;
			color: $text-color-assist;
		}

		&__action {
			display: flex;
			align-items: center;
			justify-content: center;
			font-size: $font-size-sm;
			color: $color-primary;

			&--with-icon {
				flex-direction: column;
			}
		}

		&__action-icon {
			width: $img-size-sm;
			height: $img-size-sm;
			margin-bottom: $index-gap-sm;
		}
	}
}
</style>

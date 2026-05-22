<template>
	<!-- #ifdef MP-WEIXIN -->
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<!-- #endif -->
	<view class="container score-product-detail">
		<view class="score-product-detail__carousel">
			<swiper indicator-dots circular=true duration="400">
				<swiper-item class="score-product-detail__swiper-item" v-for="(item,index) in product.images" :key="index">
					<view class="score-product-detail__image-wrapper">
						<image :src="item" class="loaded" @click="previewImage(index)" mode="aspectFill"></image>
					</view>
				</swiper-item>
			</swiper>
		</view>

		<view class="score-product-detail__intro" v-if="product.id">
			<text class="score-product-detail__title">{{product.title}}</text>
			<view class="score-product-detail__meta">
				<text>积分： {{product.score}}</text>
				<text>销量: {{product.sales}}</text>
				<text>库存: {{product.stock}}</text>
			</view>
		</view>

		<view class="score-product-detail__desc" v-if="product.desc">
			<rich-text :nodes="product.desc"></rich-text>
		</view>

		<!-- 底部操作菜单 -->
		<view class="fixed-bottom px-2">
			<button class="score-product-detail__btn" @click="confirm">立即兑换</button>
		</view>

		<uv-toast ref="uToast" />
	</view>
</template>

<script setup>
import {
  ref,
  computed,
  getCurrentInstance
} from 'vue'
import { onReachBottom,onLoad,onPullDownRefresh} from '@dcloudio/uni-app'
import {
  scoreShopExchange,
  scoreShopDetail
} from '@/api/score'
import cookie from '@/utils/cookie'
const { proxy } = getCurrentInstance();

const buttonText = ref("立即兑换")
const specClass = ref('none')
const product = ref({})
const id = ref(false)
const form = ref( 
	{
		address: {
			id: 0,
			door_number: "",
			name: "请选择收货地址",
			address: "",
			mobile: "",
		},
		num: 1 // 兑换数量
	}
)
const lock = ref(false)

onPullDownRefresh(() => {
	getDetail(id.value);
})

onLoad((options) => {
   id.value = options.id;
   getDetail(options.id);
})


// 选择地址
const chooseAddress = () => {
	uni.navigateTo({
		url: '/pages/components/pages/address/address?is_choose=true&scene=scoreShop'
	})
}

const confirm = () => {
	cookie.set('score_product',product.value)
	uni.navigateTo({
		url: '/pages/components/pages/scoreproduct/confirm'
	})
}
// 该表购买数量
const valChange = (e) => {
	form.value.num = e.value
}
// 获取商品详情
const  getDetail = async(id, flash_id) => {
	let data = await scoreShopDetail({
		id: id
	});
	uni.stopPullDownRefresh();
	product.value = data;
}
//规格弹窗开关
const toggleSpec = () => {
	if (specClass.value === 'show') {
		specClass.value = 'hide';
		setTimeout(() => {
			specClass.value = 'none';
		}, 250);
	} else if (specClass.value === 'none') {
		specClass.value = 'show';
	}
}
//stopPrevent() {},
// 查看图片
const previewImage = (index) => {
	uni.previewImage({
		current: product.value.images_text[index],
		urls: product.value.images_text,
		indicator: "number",
		loop: true
	})
}



</script>

<style lang="scss">
$score-product-accent: #ffcc00;
$score-product-detail-carousel-height: 722rpx;
$score-product-detail-swiper-height: 750rpx;
$score-product-detail-intro-padding-y: 20rpx;
$score-product-detail-intro-padding-x: 30rpx;
$score-product-detail-title-height: 50rpx;
$score-product-detail-meta-height: 64rpx;
$score-product-detail-btn-height: 100rpx;
$score-product-detail-btn-font-size: 40rpx;
$score-product-detail-desc-margin-bottom: 200rpx;

.score-product-detail {
	--score-product-accent: #{$score-product-accent};

	&__carousel {
		position: relative;
		height: $score-product-detail-carousel-height;

		swiper {
			height: 100%;
		}
	}

	&__image-wrapper {
		width: 100%;
		height: 100%;
	}

	&__swiper-item {
		display: flex;
		justify-content: center;
		align-content: center;
		height: $score-product-detail-swiper-height;
		overflow: hidden;

		image {
			width: 100%;
			height: 100%;
		}
	}

	&__intro {
		background-color: $text-color-white;
		padding: $score-product-detail-intro-padding-y $score-product-detail-intro-padding-x;
	}

	&__title {
		height: $score-product-detail-title-height;
		line-height: $score-product-detail-title-height;
		font-size: $font-size-lg;
		color: $color-subtitle;
	}

	&__meta {
		display: flex;
		align-items: center;
		height: $score-product-detail-meta-height;
		padding: 10rpx 0;
		font-size: $font-size-paragraph;
		color: $text-color-black;

		text {
			flex: 1;
		}
	}

	&__btn {
		height: $score-product-detail-btn-height;
		line-height: $score-product-detail-btn-height;
		margin: 30rpx auto 20rpx;
		border-radius: $score-product-detail-btn-height;
		background-color: var(--score-product-accent);
		font-size: $score-product-detail-btn-font-size;
		color: $text-color-white;
	}

	&__desc {
		margin-top: $spacing-col-base;
		margin-bottom: $score-product-detail-desc-margin-bottom;
		padding: $spacing-row-base;
		background-color: $text-color-white;
	}
}
</style>

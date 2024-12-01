<template>
	<!-- #ifdef MP-WEIXIN -->
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<!-- #endif -->
	<view class="container">
		<view class="carousel">
			<swiper indicator-dots circular=true duration="400">
				<swiper-item class="swiper-item" v-for="(item,index) in product.images" :key="index">
					<view class="image-wrapper">
						<image :src="item" class="loaded" @click="previewImage(index)" mode="aspectFill"></image>
					</view>
				</swiper-item>
			</swiper>
		</view>

		<view class="introduce-section" v-if="product.id">
			<text class="title">{{product.title}}</text>
			<view class="price-box bot-row">
				<text>积分： {{product.score}}</text>
				<text>销量: {{product.sales}}</text>
				<text>库存: {{product.stock}}</text>
			</view>
		</view>

		<view class="detail-desc" v-if="product.desc">
			<rich-text :nodes="product.desc"></rich-text>
		</view>

		<!-- 底部操作菜单 -->
		<view class="fixed-bottom px-2">
			<button class="btn" @click="confirm">立即兑换</button>
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

<style lang='scss'>
	.icon-you {
		color: #888;
	}

	.carousel {
		height: 722upx;
		position: relative;

		swiper {
			height: 100%;
		}

		.image-wrapper {
			width: 100%;
			height: 100%;
		}

		.swiper-item {
			display: flex;
			justify-content: center;
			align-content: center;
			height: 750upx;
			overflow: hidden;

			image {
				width: 100%;
				height: 100%;
			}
		}

	}

	/* 标题简介 */
	.introduce-section {
		background: #fff;
		padding: 20upx 30upx;

		.title {
			font-size: 32upx;
			color: #555555;
			height: 50upx;
			line-height: 50upx;
		}

		.price-box {
			display: flex;
			align-items: baseline;
			height: 64upx;
			padding: 10upx 0;
			font-size: 30rpx;
			color: #5A5B5C;
		}

		.price {
			font-size: 35rpx;
		}

		.bot-row {
			display: flex;
			align-items: center;

			text {
				flex: 1;
			}
		}
	}
	
	.btn {
		height: 100rpx;
		line-height: 100rpx;
		border-radius: 100rpx;
		background: #ffcc00;
		font-size: 40rpx;
		color: #fff;
		margin: 30upx auto 20upx;
		
	
	}

	/*  详情 */
	.detail-desc {
		background: #fff;
		margin-top: 16upx;
		margin-bottom: 200rpx;
		padding: 20rpx;
	}


	

</style>

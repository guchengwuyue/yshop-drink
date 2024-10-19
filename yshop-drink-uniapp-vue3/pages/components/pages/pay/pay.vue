<template>
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<view class="container position-relative">
		<view style="margin-bottom: 130rpx;">
			<view class="section-1">
				<template v-if="store.distance > 0">
					<list-cell class="location">
						<view class="flex-fill d-flex justify-content-between align-items-center">
							<view class="store-name flex-fill">{{ orderType == 'takeout' ? '外卖配送' : '点餐自取' }}</view>
							<uv-switch activeColor="#09b4f1" v-model="active" @change="takout">
							</uv-switch>
						</view>
					</list-cell>
				</template>

				<template v-if="orderType == 'takeout'">
					<list-cell @click="chooseAddress">
						<view v-if="address.realName" class="w-100 d-flex flex-column">
							<view class="d-flex align-items-center justify-content-between mb-10">
								<view class="font-size-lg text-color-base">
									{{ address.address + ' ' + address.detail }}
								</view>
								<image src="/static/images/navigator-1.png" class="arrow"></image>
							</view>
							<view class="d-flex text-color-assist font-size-sm align-items-center">
								<view class="mr-10">{{ address.realName }}</view>
								<view>{{ address.phone }}</view>
							</view>
						</view>
						<view v-else class="flex-fill d-flex justify-content-between align-items-center">
							<view class="store-name flex-fill">选择收货地址</view>
							<image src="/static/images/navigator-1.png" class="arrow"></image>
						</view>
					</list-cell>
				</template>
			</view>

			<view class="section-1">
				<template>
					<list-cell class="location" @click="goToShop">
						<view class="flex-fill d-flex justify-content-between align-items-center">
							<view class="store-name flex-fill">{{ store.name }}</view>
							<image src="/static/images/navigator-1.png" class="arrow"></image>
						</view>
					</list-cell>
				</template>

				<template>
					<list-cell arrow class="meal-time" v-if="orderType == 'takein'">
						<view class="flex-fill d-flex justify-content-between align-items-center"
							@click="takeinTIme = !takeinTIme">
							<view class="title">取餐时间</view>
							<view class="time">
								{{ takeinRange[defaultSelector[0]].name }}
								<u-picker v-model="takeinTIme" :range="takeinRange" range-key="name" mode="selector"
									@cancel="takeinCancelTime" @confirm="takeinConfirmTime"
									:default-selector="defaultSelector"></u-picker>
							</view>
						</view>
					</list-cell>
					<list-cell class="contact" last :hover="false" v-if="orderType == 'takein'">
						<view class="flex-fill d-flex justify-content-between align-items-center">
							<view class="title flex-fill">联系电话</view>
							<view class="time"><input class="text-right" placeholder="请输入手机号码" :value="member.mobile" />
							</view>
							<button class="contact-tip font-size-sm">自动填写</button>
						</view>
					</list-cell>
				</template>
				<template v-if="orderType == 'takeout'">
					<list-cell>
						<view class="w-100 d-flex flex-column">
							<view class="d-flex align-items-center font-size-base text-color-base">
								<view class="flex-fill">预计送达时间</view>
								<view class="mr-10">
									{{ defaultTime }}
									<u-picker :default-time="defaultTime" v-model="takeoutTIme" :params="paramsTime"
										mode="time" @cancel="cancelTime" @confirm="choiceTime"></u-picker>
								</view>
							</view>
						</view>
					</list-cell>
				</template>
			</view>
			<!-- 购物车列表 begin -->
			<view class="section-2">
				<view class="cart d-flex flex-column">
					<list-cell last v-for="(item, index) in cart" :key="index">
						<view class="w-100 d-flex flex-column">
							<view class="d-flex align-items-center mb-10">
								<view
									class="d-flex flex-fill justify-content-between align-items-center text-color-base font-size-lg">
									<image style="width: 80rpx;height: 80rpx;" mode="aspectFill" :src="item.image">
									</image>
								</view>
								<view class="name-and-props overflow-hidden">
									<view class="text-color-base font-size-lg">{{ item.name }}</view>
								</view>
								<view
									class="d-flex flex-fill justify-content-between align-items-center text-color-base font-size-lg">
									<view>x{{ item.number }}</view>
									<view>￥{{ item.price }}</view>
								</view>
							</view>
							<view class="text-truncate font-size-base text-color-assist">{{ item.valueStr }}</view>
						</view>
					</list-cell>
				</view>
				<list-cell arrow @click="goToPackages">
					<view class="flex-fill d-flex justify-content-between align-items-center">
						<view class="text-color-base">优惠券</view>
						<view v-if="coupons == 0" class="text-color-base">暂无可用</view>
						<view v-else-if="coupon.title" class="text-color-danger">
							{{ coupon.title }}(满{{ coupon.least }}减{{ coupon.value }})
						</view>
						<view v-else class="text-color-primary">可用优惠券{{ coupons }}张</view>
					</view>
				</list-cell>
				<list-cell last>
					<view class="flex-fill d-flex justify-content-end align-items-center">
						<view>
							总计￥{{ total }}
							<text v-if="orderType == 'takeout'">,配送费￥{{ store.deliveryPrice }}</text>
							<text v-if="coupon.value">,￥-{{ coupon.value }}</text>
							,实付
						</view>
						<view class="font-size-extra-lg font-weight-bold">￥{{ amount }}</view>
					</view>
				</list-cell>
			</view>
			<!-- 购物车列表 end -->
			<view class="d-flex align-items-center justify-content-start font-size-sm text-color-warning"
				style="padding: 20rpx 0;">
			</view>
			<!-- 支付方式 begin -->
			<view class="payment">
				<list-cell last :hover="false"><text>支付方式</text></list-cell>
				<list-cell>
					<view class="d-flex align-items-center justify-content-between w-100 disabled"
						@click="setPayType('yue')">
						<view class="iconfont iconbalance line-height-100 payment-icon"></view>
						<view class="flex-fill">余额支付（余额￥{{ member.nowMoney }}）</view>
						<view class="font-size-sm" v-if="member.nowMoney == 0">余额不足</view>
						<view class="iconfont line-height-100 checkbox checked iconradio-button-on" v-if="payType == 'yue'">
						</view>
						<view class="iconfont line-height-100 checkbox iconradio-button-off" v-else></view>
					</view>
				</list-cell>
				<list-cell last>
					<view class="d-flex align-items-center justify-content-between w-100" @click="setPayType('weixin')">
						<view class="iconfont iconwxpay line-height-100 payment-icon" style="color: #7EB73A"></view>
						<view class="flex-fill">微信支付</view>
						<view class="iconfont line-height-100 checkbox checked iconradio-button-on" v-if="payType == 'weixin'">
						</view>
						<view class="iconfont line-height-100 checkbox iconradio-button-off" v-else></view>
					</view>
				</list-cell>
				<!-- #ifdef H5 -->
				<list-cell>
					<view class="d-flex align-items-center justify-content-between w-100" @click="setPayType('alipay')">
						<view class="iconfont-yshop icon-alipay line-height-100 payment-icon" style="color:#07b4fd" ></view>
						<view class="flex-fill">支付宝</view>
						<view class="iconfont line-height-100 checkbox checked iconradio-button-on" v-if="payType == 'alipay'" ></view>
						<view class="iconfont line-height-100 checkbox iconradio-button-off" v-else ></view>     
					</view>
				</list-cell>
				<!-- #endif -->
			</view>
			<!-- 支付方式 end -->
			<!-- 备注 begin -->
			<list-cell  last @click="goToRemark">
				<view class="d-flex flex-fill align-items-center justify-content-between overflow-hidden" style="margin-bottom: 110rpx;">
					<view class="flex-shrink-0 mr-20">备注</view>
					<view class="text-color-primary flex-fill text-truncate text-right">{{ form.remark || '点击填写备注' }}
					</view>
				</view>
			</list-cell>
			<!-- 备注 end -->
		</view>
		<!-- 付款栏 begin -->
		<view style="z-index: 1;"
			class="w-100 pay-box position-fixed fixed-bottom d-flex align-items-center justify-content-between bg-white">
			<view class="font-size-sm" style="margin-left: 20rpx;">合计：</view>
			<view class="font-size-lg flex-fill">￥{{ amount }}</view>
			<view class="bg-primary h-100 d-flex align-items-center just-content-center text-color-white font-size-base"
				style="padding: 0 60rpx;" @tap="debounce(submit, 500)">付款</view>
		</view>
		<!-- 付款栏 end -->
		<modal :show="ensureAddressModalVisible" custom :mask-closable="false" :radius="'0rpx'" width="90%">
			<view class="modal-content">
				<view class="d-flex justify-content-end">
					<image src="/static/images/pay/close.png" style="width: 40rpx; height: 40rpx;"
						@tap="ensureAddressModalVisible = false"></image>
				</view>
				<view class="d-flex just-content-center align-items-center" style="margin-bottom: 40px;">
					<view class="font-size-extra-lg text-color-base">请再次确认下单地址</view>
				</view>
				<view
					class="d-flex font-size-base text-color-base font-weight-bold align-items-center justify-content-between mb-20">
					<view>{{ address.realName }}</view>
					<view>{{ address.phone }}</view>
				</view>
				<view class="d-flex font-size-sm text-color-assist align-items-center justify-content-between mb-40">
					<view style="max-width: 60%;">{{ address.address + address.detail }}</view>
					<button type="primary" size="mini" plain class="change-address-btn" style="white-space: nowrap;"
						@click="chooseAddress">修改地址</button>
				</view>
				<button type="primary" class="pay_btn" @tap="debounce(pay, 500)">确认并付款</button>
			</view>
		</modal>
		<uv-toast ref="uToast"></uv-toast>
	</view>
</template>

<script setup>
import {
  ref,
  computed,
  nextTick
} from 'vue'
import { useMainStore } from '@/store/store'
import { storeToRefs } from 'pinia'
import { onLoad,onShow ,onPullDownRefresh,onHide} from '@dcloudio/uni-app'
import { formatDateTime,isWeixin } from '@/utils/util'
import  debounce  from '@/uni_modules/uv-ui-tools/libs/function/debounce'

import {
  orderSubmit,
  payUnify,
  getWechatConfig
} from '@/api/order'
import {
  couponCount
} from '@/api/coupon'
// #ifdef H5
import * as jweixin from 'weixin-js-sdk'
// #endif
const main = useMainStore()
const { orderType,address, store,location,isLogin,member,mycoupon } = storeToRefs(main)
const active = ref(false)
const title = ref('支付')
const jsStr = ref('')
const cart = ref([])
const form = ref({
	remark: ''
})
const  ensureAddressModalVisible = ref(false)
const  takeoutTIme = ref(false) // 外卖取餐时间picker
const paramsTime = ref({
	year: false,
	month: false,
	day: false,
	hour: true,
	minute: true,
	second: false
})
const defaultTime = ref('00:00')
const takeinTIme = ref(false) // 到店自取时间selector
const takeinRange = ref([{
		name: '立即用餐',
		value: 0
	},
	{
		name: '10分钟后',
		value: 10
	},
	{
		name: '20分钟后',
		value: 20
	},
	{
		name: '30分钟后',
		value: 30
	},
	{
		name: '40分钟后',
		value: 40
	},
	{
		name: '50分钟后',
		value: 50
	}
])
const defaultSelector = ref([0])
const payType = ref('weixin') // 付款方式
const coupons = ref(0) // 可用优惠券数量
const coupon = ref(main.mycoupon) // 选中的
const subscribeMss = ref({
	'takein': '',
	'takeout': '',
	'takein_made': '',
	'takeout_made': ''
})// 微信订阅信息
const uToast = ref()

const total = computed(() =>{
	return cart.value.reduce((acc, cur) => acc + cur.number * cur.price, 0);
})
const amount = computed(() =>{
	let amount = cart.value.reduce((acc, cur) => acc + cur.number * cur.price, 0);
	// 加配送费
	if (store.value.distance > 0 && orderType.value == 'takeout') {
		amount += parseFloat(store.value.deliveryPrice);
	}

	
	// 减去优惠券
	if (main.mycoupon.hasOwnProperty('id')) {
		amount -= parseFloat(main.mycoupon.value);
	}
	return amount.toFixed(2);
})

onShow(() => {
	coupon.value = main.mycoupon
	let date = new Date(new Date().getTime() + 3600000); // 一个小时后
	let hour = date.getHours();
	let minute = date.getMinutes();
	if (hour < 10) {
		hour = '0' + hour;
	}
	if (minute < 10) {
		minute = '0' + minute;
	}
	defaultTime.value = hour + ':' + minute;
	
	console.log('member:',member.value)
	
	if(orderType.value == 'takeout'){
		active.value = true
	}else{
		active.value = false
	}
	
	getCoupons();
	
	let paytype = uni.getStorageSync('paytype');
	payType.value = paytype ? paytype : 'weixin';
	
})
onHide(() => {
	subscribeMss.value = [];
	coupons.value = 0;
})
onLoad((option) => {
	cart.value = uni.getStorageSync('cart')
	if(option.remark) {
		form.value.remark = option.remark
	}
})

const getSubscribeMss = async() => {
	 let data = []
	if (data) {
		subscribeMss.value = data;
	}
}
// 更改支付方式
const setPayType = (paytype) => {
	payType.value = 'weixin';
	payType.value= paytype;
	uni.setStorage({
		key: 'paytype',
		data: paytype
	})
}
const getCoupons = async() => {
	//0=通用,1=自取,2=外卖
	let type = orderType.value == 'takein' ? 1 : 2;
	let data = await couponCount({
		shop_id: store.value.id ? store.value.id : 0,
		type: type
	});
	if (data) {
		coupons.value = data;
	}
}
// 选择时间
const choiceTime = (value) => {
	let hour = value.hour;
	let minute = value.minute;

	let date = new Date(new Date().getTime() + 3600000); // 一个小时后
	let nowhour = date.getHours();
	let nowminute = date.getMinutes();

	if ((hour * 60 * 60 + minute * 60) * 1000 - 3600000 < (nowhour * 60 * 60 + nowminute * 60) * 1000) {
		uToast.value.show({
			message: '请至少选择一个小时之后',
			type: 'error'
		});
		return
	}

	if (hour < 10) {
		hour = '0' + hour;
	}
	if (minute < 10) {
		minute = '0' + minute;
	}
	defaultTime,value = hour + ':' + minute;
	takeoutTIme.value = false;
}
const cancelTime = (value) => {
	takeoutTIme.value = false;
}
// 到店自取-取消选择取餐时间
const takeinCancelTime = (value) => {
	takeinTIme.value = false;
}
// 到店自取-选择取餐时间
const takeinConfirmTime = (value) => {
	defaultSelector.value = value;
}
// 是否外卖开关
const takout = (value) => {
	let type = 'takeout';
	if (value == false) {
		type = 'takein';
	}
	main.SET_ORDER_TYPE(type);

	// 如果存在优惠券看看需不需要清除
	if (coupon.value.hasOwnProperty('type')) {
		//0=通用,1=自取,2=外卖
		if (coupon.value.type != 0) {
			if (coupon.value.type == 1 && orderType.value == 'takeout') {
				coupon.value = {};
			}
			if (coupon.value.type == 2 && orderType.value == 'takeint') {
				coupon.value = {};
			}
		}
	}
	subscribeMss.value = [];
	coupons.value = 0;
	getCoupons();
}
const goToRemark = () => {
	uni.navigateTo({
		url: '/pages/components/pages/remark/remark?remark=' + form.value.remark
	});
}
const chooseAddress = () => {
	uni.navigateTo({
		url: '/pages/components/pages/address/address?is_choose=true&scene=pay'
	});
}
const goToPackages = () => {
	let newamount = amount.value;
	let coupon_id = coupon.value.id ? coupon.value.id : 0;
	let type = orderType.value == 'takein' ? 1 : 2;
	let shop_id = store.value.id;
	uni.navigateTo({
		url: '/pages/components/pages/packages/index?amount=' + newamount + '&coupon_id=' + coupon_id +
			'&shop_id=' + shop_id + '&type=' + type
	});
}
const goToShop = () => {
	uni.navigateTo({
		url: `/pages/components/pages/shop/shop`
	});
}
const submit = () => {
	if (orderType.value == 'takeout') {
		// 外卖类型
		if (typeof address.value.id == 'undefined') {
			uToast.value.show({
				message: '请选择收货地址',
				type: 'error'
			});
			return
		}

		// 起送价钱
		if (store.value.min_price > total.value) {
			uToast.value.show({
				message: '本店外卖起送价为￥' + store.value.min_price,
				type: 'error'
			});
			return
		}

		pay();

	} else {
		pay();
	}
}
const pay = async() => {
	let that = this;
	// #ifdef MP-WEIXIN
	await new Promise(function(revolve) {
		//订阅号信息id
		 let subscribeMss = ['KBtfY9G1IWCzC6q-ZKo-Q-MmdP7aaF79nx0XFcBf3h4'];

		wx.showModal({
			title: '温馨提示',
			content: '为更好的促进您与商家的交流，小程序需要在您成交时向您发送消息',
			confirmText: "同意",
			cancelText: "拒绝",
			success: function(res) {
				if (res.confirm) {
					uni.requestSubscribeMessage({
						tmplIds: subscribeMss,
						complete(res) {
							console.log(res)
							revolve(true)
						}
					});
				} else {
					revolve(true)
				}
			}
		})
	});
	

	// #endif
	uni.showLoading({
		title: '加载中'
	});

	let data = {
		orderType: orderType.value, // 购买类型:takein=自取,takeout=外卖
		addressId:orderType.value == 'takeout' ? address.value.id : 0, // 外卖配送地址
		shopId: store.value.id, // 店铺id
		mobile: member.value.mobile, // 联系电话
		gettime: takeinRange.value[defaultSelector.value[0]].value, // 取餐时间
		payType: payType.value, // 支付类型
		remark: form.value.remark, // 备注
		productId: [],
		spec: [],
		number: [],
		couponId: coupon.value.id ? coupon.value.id : 0 // 优惠券id
	};

	cart.value.forEach((item, index) => {
		data.productId.push(item.id);
		data.spec.push(item.valueStr.replace(/,/g, '|'));
		//data.spec.push(item.valueStr);
		data.number.push(item.number);
	});

	//console.log(data);
	let order = await orderSubmit(data);
	if (!order) {
		uni.hideLoading();
		return;
	}
	
	main.DEL_COUPON()


	if (payType.value == 'weixin') {
		// 微信支付
		weixinPay(order);
	} else if (payType.value == 'yue') {
		// 余额支付
		balancePay(order);
	} else if (payType.value == 'alipay') {
		// 余额支付
		aliPay(order);
	} 
	uni.hideLoading()
	return
}
const balancePay = async(order) => {
	let from = 'routine'
	// #ifdef H5
	from = 'h5'
	// #endif
	let pay = await payUnify({
		uni: order.orderId,
		from: from,
		paytype: 'yue'
	});

	uni.hideLoading();
	if (!pay) {
		return;
	}

	member.value.money -= amount.value
	main.SET_MEMBER(member.value)
	uni.removeStorageSync('cart');
	uni.switchTab({
		url: '/pages/order/order',
		fail(res) {
			console.log(res);
		}
	});
}
const weixinPay = async(order) => {
	let from = 'routine'
	// #ifdef H5
	from = 'h5'
	if(isWeixin()){
		from = 'wechat'
	}
	
	// #endif
	//let that = this;
	let data = await payUnify({
		uni: order.orderId,
		from: from,
		paytype: 'weixin'
	});
	console.log('param2:',data)
	if (!data) {
		uni.hideLoading();
		return;
	}
	if (data.trade_type == 'MWEB') {
		// #ifdef H5
		// 微信外的H5
		console.log('data:',data)
		location.href = data.data;
		// #endif
		console.log('data1:',data)
	} else if (data.trade_type == 'JSAPI') {
		console.log('param:',data)

		// #ifdef MP-WEIXIN
		uni.requestPayment({
			provider: 'wxpay',
			timeStamp: data.data.timeStamp,
			nonceStr: data.data.nonceStr,
			package: data.data.package,
			signType: 'MD5',
			paySign: data.data.paySign,
			success: function(res) {

				uni.removeStorageSync('cart');
				uni.switchTab({
					url: '/pages/order/order'
				});
			},
			fail: function(err) {
				console.log('fail:' + JSON.stringify(err));
			}
		});
		// #endif
	} else if (data.trade_type == 'W-JSAPI'){
		//公众号支付
	
		
	}else if (data.trade_type == 'APP') {

	}
}
const aliPay = async(order) => {

	// #ifdef H5
	//let that = this;
	if(isWeixin()){
		uni.showToast({
			title: '请普通浏览器打开进行支付宝支付~',
			icon: 'none'
		})
		return
	}
	let data = await payUnify({
		uni: order.orderId,
		from: 'h5',
		paytype: 'alipay'
	});

	console.log('data:',data.data)
  // 支付宝支付，这里只要提交表单
	let form = data.data
	const div = document.createElement('formdiv');
	div.innerHTML = form;
	document.body.appendChild(div);      
	//document.forms[0].setAttribute('target', ' self');
	document.forms[0].submit();
	//div.remove();

// #endif


}




</script>

<style lang="scss" scoped>
	.container {
		padding: 30rpx;
	}

	.arrow {
		width: 50rpx;
		height: 50rpx;
		position: relative;
		margin-right: -10rpx;
	}

	.location {
		.store-name {
			font-size: $font-size-lg;
		}

		.iconfont {
			font-size: 50rpx;
			line-height: 100%;
			color: $color-primary;
		}
	}

	.section-1 {
		margin-bottom: 30rpx;

		.contact {
			.contact-tip {
				margin-left: 10rpx;
				border: 2rpx solid $color-primary;
				padding: 6rpx 10rpx;
				color: $color-primary;
			}
		}
	}

	.section-2 {
		.name-and-props {
			width: 65%;
		}
	}

	.payment {
		margin-bottom: 30rpx;

		.disabled {
			color: $text-color-grey;
		}

		.payment-icon {
			font-size: 44rpx;
			margin-right: 10rpx;
		}

		.checkbox {
			font-size: 36rpx;
			margin-left: 10rpx;
		}

		.checked {
			color: $color-primary;
		}
	}

	.pay-box {
		box-shadow: 0 0 20rpx rgba(0, 0, 0, 0.1);
		height: 100rpx;
	}

	.modal-content {
		.change-address-btn {
			line-height: 2;
			padding: 0 1em;
		}

		.pay_btn {
			width: 100%;
			border-radius: 50rem !important;
			line-height: 3;
		}
	}

	.choice {
		background-color: $bg-color-grey;
		border-radius: 38rpx;
		display: flex;
		align-items: center;
		font-size: $font-size-sm;
		padding: 0 38rpx;
		color: $text-color-assist;

		.dinein,
		.takeout {
			width: 50%;
			position: relative;
			display: flex;
			align-items: center;

			&.active {
				padding: 14rpx 38rpx;
				color: #ffffff;
				background-color: $color-primary;
				border-radius: 38rpx;
			}
		}

		.takeout {
			margin-left: 20rpx;
			height: 100%;
			flex: 1;
			padding: 14rpx 0;
		}

		.dinein.active {
			//margin-left: -38rpx;
		}

		.takeout.active {
			//margin-right: -38rpx;
		}
	}
</style>

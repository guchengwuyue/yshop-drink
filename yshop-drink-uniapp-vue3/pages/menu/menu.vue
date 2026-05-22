<template>
	<layout>
		<uv-navbar
		  :fixed="false"
		  :title="title"
		  left-arrow
		  @leftClick="$onClickLeft"
		/>
		
		<view class="container" v-if="!loading">
			<!-- <view>
				<image :src="shopAd" mode="aspectFill" class="w-100 " style="height: 250rpx;"></image>
			</view> -->
			<view class="notice-bar" v-if="store.notice">
					<uv-notice-bar  :text="store.notice"></uv-notice-bar>
			</view>
		<view class="main">
			<view class="nav">
				<view class="header">
					<view class="mr-1"><image :src="store.image" class="store-header-img"></image></view>
					<view class="left" v-if="orderType == 'takein'">
						<view class="store-name" @click="selectShop()">
							<text>{{ store.name }}</text>
							<view class="iconfont iconarrow-right"></view>
						</view>
						<view class="store-location">
							<text>距离您 {{kmUnit(store.dis)}}</text>
						</view>
					</view>
					<view class="left overflow-hidden" v-else>
						<view class="store-name" @click="selectShop()">
							<view>{{ store.name }}
								<text class="small" v-if="store.distance > 0 && orderType == 'takeout'">(配送距离:
									{{store.distance}}km)</text>
								<text class="small" v-else-if="orderType == 'takeout'">(本店不支持外卖)</text>
							</view>
							<view class="iconfont iconarrow-right"></view>
						</view>
					</view>
					<view class="right">
						<view class="dinein" :class="{active: orderType == 'takein'}" @tap="takein">
							<text>自取</text>
						</view>
						<view class="takeout" :class="{active: orderType == 'takeout'}" @tap="takout">
							<text>外卖</text>
						</view>
					</view>
				</view>
			</view>
		
			<!-- #ifdef H5 -->
			<view class="content"
				:style="{height: 'calc(100vh - 300rpx + '+(store.notice ? '0rpx':'60rpx')+')'}">
				<!-- #endif -->
				<!-- #ifndef H5 -->
				<view class="content" :style="{height: 'calc(100vh - 350rpx + '+(store.notice ? '0rpx':'60rpx')+')'}">
					<!-- #endif -->
					<scroll-view class="menus" :scroll-into-view="menuScrollIntoView" scroll-with-animation scroll-y>
						<view class="wrapper">
							<view class="menu" :id="`menu-${item.id}`" :class="{'current': item.id === currentCateId}"
								v-for="(item, index) in goods" :key="index" @tap="handleMenuTap(item.id)">
								<text>{{ item.name }}</text>
								<view class="dot" v-show="menuCartNum(item.id)">{{ menuCartNum(item.id) }}</view>
							</view>
						</view>
					</scroll-view>
					<!-- goods list begin -->
					<scroll-view class="goods" scroll-with-animation scroll-y :scroll-top="cateScrollTop"
						@scroll="handleGoodsScroll">
						<view class="wrapper">
							<view class="list">
								<!-- category begin -->
								<view class="category" v-for="(item, index) in goods" :key="index"
									:id="`cate-${item.id}`">
									<view class="title">
										<text>{{ item.name }}</text>
										<image mode="aspectFill" :src="item.icon" class="icon"></image>
									</view>
									<view class="items">
										<!-- 商品 begin -->
										<view class="good" v-for="(good, key) in item.goodsList" :key="key"
											:class="{'background-grey': good.stock <= 0}">
											<image mode="aspectFill" :src="good.image" class="image"
												@tap="showGoodDetailModal(item, good)"></image>
											<view class="right">
												<text class="name">{{ good.storeName }}</text>
												<text class="tips">{{ good.storeInfo }}</text>
												<view class="price_and_action">
													<text class="price">￥{{ good.price }}</text>
													<view class="btn-group" v-if="good.stock > 0">
														<button type="primary" class="btn property_btn"
															hover-class="none" size="mini"
															@tap="showGoodDetailModal(item, good)">
															选规格
														</button>
														<view class="dot" v-show="goodCartNum(good.id)">
															{{ goodCartNum(good.id) }}</view>
													</view>
											

													<view v-if="good.stock == 0">已售罄</view>
												</view>

											</view>
										</view>
										<!-- 商品 end -->
									</view>
								</view>
								<!-- category end -->
								<view class="bottom-spacer"></view>
							</view>
						</view>
					</scroll-view>
					<!-- goods list end -->
				</view>
				<!-- content end -->
				<!-- 购物车栏 begin -->
				<view class="cart-box" v-if="cart.length > 0 && isCartShow">
					<view class="mark">
						<image src="/static/images/menu/cart.png" class="cart-img" @tap="openCartPopup"></image>
						<view class="tag">{{ getCartGoodsNumber }}</view>
					</view>
					<view class="price" @tap="openCartShow">￥{{ getCartGoodsPrice }}</view>
					<button type="primary" class="pay-btn" @tap="toPay" :disabled="disabledPay">
						{{ disabledPay ? `差${spread}元起送` : '去结算' }}
					</button>
				</view>
				<!-- 购物车栏 end -->
			</view>
			<!-- 商品详情模态框 begin -->
			<modal :show="goodDetailModalVisible" class="good-detail-modal" color="#5A5B5C" width="90%" custom
				padding="0rpx" radius="12rpx">
				<view class="cover">
					<view class="btn-group">
						<image src="/static/images/menu/close.png" @tap="closeGoodDetailModal"></image>
					</view>
				</view>
				<scroll-view class="detail" scroll-y>
					<view v-if="good.image" class="image">
						<image :src="good.image"></image>
					</view>

					<view class="wrapper">
						<view class="basic">
							<view class="name">{{ good.storeName }}</view>
							<view class="tips flex justify-between">{{ good.storeInfo }} <text style="color: red;">可获积分:10</text></view>
						</view>
						<view class="properties">
							<view class="property" v-for="(item, index) in good.productAttr" :key="index">
								<view class="title">
									<text class="name">{{ item.attrName }}</text>
								</view>
								<view class="values">
									<view class="value" v-for="(value, key) in item.attrValueArr" :key="key"
										:class="{'default': value == newValue[index]}"
										@tap="changePropertyDefault(index, key,false)">
										{{ value }}
									</view>
								</view>
							</view>
						</view>
					</view>
				</scroll-view>
				<view class="action">
					<view class="left">
						<view class="price">￥{{ good.price }}</view>
						<view class="props">
							{{ good.valueStr }}
						</view>
					</view>
					<view class="btn-group">
						<text style="margin-right: 20rpx;">库存：{{good.stock}} </text>
						<button type="default" plain class="btn" size="mini" hover-class="none"
							@tap="handlePropertyReduce">
							<view class="iconfont iconsami-select"></view>
						</button>
						<view class="number">{{ good.number }}</view>
						<button type="primary" class="btn" size="min" hover-class="none" @tap="handlePropertyAdd">
							<view class="iconfont iconadd-select"></view>
						</button>
					</view>
				</view>
				<view class="add-to-cart-btn" @tap="handleAddToCartInModal">
					<view>加入购物车</view>
				</view>
			</modal>
			<!-- 商品详情模态框 end -->
			<!-- 购物车详情popup -->
			<uv-popup ref="popup" mode="bottom" class="cart-popup">
				<template #default>
				<view  class="cart-popup">
					 <view class="top">
					  <text @tap="handleCartClear">清空</text>
					 </view>
					 <scroll-view class="cart-list" scroll-y>
					  <view class="wrapper">
					   <view class="item" v-for="(item, index) in cart" :key="index">
						<view class="left">
						 <view class="name">{{ item.name }}</view>
						 <view class="props">{{ item.valueStr }}</view>
						</view>
						<view class="center">
						 <text>￥{{ item.price }}</text>
						</view>
						<view class="right">
						 <button type="default" plain size="mini" class="btn" hover-class="none"
						  @tap="handleCartItemReduce(index)">
						  <view class="iconfont iconsami-select"></view>
						 </button>
						 <view class="number">{{ item.number }}</view>
						 <button type="primary" class="btn" size="min" hover-class="none"
						  @tap="handleCartItemAdd(index)">
						  <view class="iconfont iconadd-select"></view>
						 </button>
						</view>
					   </view>
							
					  </view>
					 </scroll-view>
				 </view>
				</template>
			</uv-popup>
			   <!-- 购物车详情popup -->
			<uv-toast ref="uToast"></uv-toast>
		</view>
		<!--轻提示-->
		<view class="loading" v-else>
			<uv-loading-icon  color="#DA5650" size=40 mode="circle" ></uv-loading-icon>
			<button type="primary" class="locate-store-btn" @click="init"
				v-if="!store.id">定位最近的门店</button>
		<!-- 	<uv-toast ref="uToast"></uv-toast> -->
		</view>
	</layout>
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
import { formatDateTime,kmUnit } from '@/utils/util'
import {
  shopNearby,
  menuGoods
} from '@/api/goods'
import {
  menuAds
} from '@/api/market'
const main = useMainStore()
const { orderType,address, store,location,isLogin } = storeToRefs(main)
const title = ref('点餐')
const text = ref('滚动通知')

const goods = ref([])
const ads = ref([])
const loading = ref(true) 
const currentCateId = ref(0)
const cateScrollTop = ref(0)
const menuScrollIntoView = ref('')
const cart = ref([])
const goodDetailModalVisible = ref(false)
const good= ref({})
const category = ref({})
const cartPopupVisible = ref(false)
const sizeCalcState = ref(false)
const newValue = ref([])
const shopAd = ref('')
const isCartShow = ref(true)
const popup = ref()

// #region agent log
const debugLog = (hypothesisId, location, message, data = {}) => {
	fetch('http://127.0.0.1:7584/ingest/889fdfba-29d4-4509-84ce-3dfa9254da00', {
		method: 'POST',
		headers: { 'Content-Type': 'application/json', 'X-Debug-Session-Id': 'e0348b' },
		body: JSON.stringify({
			sessionId: 'e0348b',
			runId: 'pre-fix',
			hypothesisId,
			location,
			message,
			data,
			timestamp: Date.now()
		})
	}).catch(() => {})
}
const measureMenuLayout = () => {
	const sys = uni.getSystemInfoSync()
	const notice = !!store.value?.notice
	const inlineHeight = `calc(100vh - 500rpx + ${notice ? '0rpx' : '60rpx'})`
	let platform = 'OTHER'
	// #ifdef H5
	platform = 'H5'
	// #endif
	// #ifdef MP-WEIXIN
	platform = 'MP-WEIXIN'
	// #endif
	debugLog('H1', 'menu.vue:measureMenuLayout', 'layout context', {
		platform,
		windowHeight: sys.windowHeight,
		screenHeight: sys.screenHeight,
		statusBarHeight: sys.statusBarHeight,
		hasNotice: notice,
		inlineHeight,
		cssExpectedH5: 'calc(100vh - 212rpx - 188rpx)',
		cssExpectedDefault: 'calc(100vh - 212rpx)'
	})
	const q = uni.createSelectorQuery()
	q.select('.container').boundingClientRect()
	q.select('.main').boundingClientRect()
	q.select('.content').boundingClientRect()
	q.select('.menus').boundingClientRect()
	q.select('.goods').boundingClientRect()
	q.select('.nav').boundingClientRect()
	q.exec((res) => {
		const [container, main, content, menus, goods, nav] = res || []
		debugLog('H3', 'menu.vue:measureMenuLayout', 'parent rects', {
			containerH: container?.height,
			mainH: main?.height,
			navH: nav?.height
		})
		debugLog('H1', 'menu.vue:measureMenuLayout', 'content rect', {
			contentH: content?.height,
			contentTop: content?.top,
			windowHeight: sys.windowHeight,
			heightRatio: content?.height && sys.windowHeight ? (content.height / sys.windowHeight).toFixed(3) : null
		})
		debugLog('H5', 'menu.vue:measureMenuLayout', 'scroll columns', {
			menusH: menus?.height,
			goodsH: goods?.height,
			menusGoodsDelta: menus?.height != null && goods?.height != null ? Math.abs(menus.height - goods.height) : null
		})
	})
}
// #endregion

const newkmUnit = computed(() => (param) =>{
  console.log('param:',param)
  return '10km'
})
const goodCartNum = computed(() => { //计算单个饮品添加到购物车的数量
	return (id) => cart.value.reduce((acc, cur) => {
		if (cur.id === id) {
			return acc += cur.number
		}
		return acc
	}, 0)
})
const menuCartNum = computed(() =>{
	return (id) => cart.value.reduce((acc, cur) => {
		if (cur.cate_id === id) {
			return acc += cur.number
		}
		return acc
	}, 0)
})
const getCartGoodsNumber = computed(() => { //计算购物车总数
	return cart.value.reduce((acc, cur) => acc + cur.number, 0)
})
const getCartGoodsPrice = computed(() =>{ //计算购物车总价
	let price = cart.value.reduce((acc, cur) => acc + cur.number * cur.price, 0);
	return parseFloat(price).toFixed(2);
})
const disabledPay = computed(() => { //是否达到起送价
	return orderType.value == 'takeout' && (getCartGoodsPrice < parseFloat(store.value.min_price)) ? true :
		false
})
const spread = computed(() => { //差多少元起送
	if (orderType.value != 'takeout') return
	return parseFloat((store.value.min_price - getCartGoodsPrice).toFixed(2))
})

// 监听自定义事件
uni.$on('refreshMenu', () => {
	// 在这里执行onLoad逻辑
	console.log('refreshMenu1:',store.value.id)
	init()
})

onPullDownRefresh(() => {
	init()
})
onLoad(() => {
	init();
	refreshCart()
})
onHide(() => {
	// 重新进入要重新计算页面高度，否则有问题
	sizeCalcState.value = false;
})
onShow(() => {
	//init();
	refreshCart()
	shopAd.value = uni.getStorageSync('shopAd')
})

const openCartShow = () =>{
	isCartShow.value = false
}
const in_array = (search, array) => {
	for (var i in array) {
		if (array[i] == search) {
			return true;
		}
	}
	return false;
}
const selectShop = () => {
	uni.navigateTo({
		url: '/pages/components/pages/shop/shop'
	})
}
const uToast = ref()
const  init = async() => { //页面初始化
	loading.value = true;
    
	//return
	let error = {},
		result = location.value
	console.log('result:',result)
	if (!location.value.hasOwnProperty('latitude')) {
		  console.log('result1:',location.value)
		  uni.getLocation(({
			 type: 'wgs84',
			 success: function (res) {
			   console.log('location1:',res)
		
				result = {
					latitude: res.latitude,
					longitude: res.longitude
				};
				getShopList(result)
			 },
			 fail: function (res) {
			  
			   uni.showToast({
			     title: '获取位置失败，请检查是否开启相关权限',
			     duration: 2000,
			     icon: 'error'
			   });
			   // 默认地为你为北京地址
			   result = {
			   	latitude: 39.919990,
			   	longitude: 116.456270
			   };
			   getShopList(result)
			 },
			 complete: function (res) {
			 }
		}));
		 if(!result.hasOwnProperty('latitude')){
			result = {
				latitude: 39.919990,
				longitude: 116.456270
			};
			getShopList(result)
		 }
		return
	}
	
	getShopList(result)
   
	
}
const getShopList = async(res) => {
	 console.log('location9:',res)
	if (res) {
		main.SET_LOCATION(res);
	
		let shop_id = 0;
		if (store.value.id) {
			shop_id = store.value.id;
		}
	
		let shop = await shopNearby({
			lat: res.latitude,
			lng: res.longitude,
			shop_id: shop_id,
			kw: ''
		});
		if (shop) {
			//广告图
			getAds(shop.id);
	
			shop.notice = shop.status == 1 ? shop.notice : '店铺营业时间为:' + formatDateTime(shop.startTime,'hh:mm')+' - '+formatDateTime(shop.endTime,'hh:mm') +
			'，不在营业时间内无法下单';
			// 设置店铺信息
			main.SET_STORE(shop);
			let mygoods = await menuGoods({
				shopId: shop.id
			});
			if (mygoods) {
				goods.value = mygoods;
				refreshCart();
			}
			console.log('goods:',mygoods)
			console.log('goods:',goods.value)
			loading.value = false;
			nextTick(() => measureMenuLayout())
			uni.stopPullDownRefresh();
		}
	}
}
const refreshCart = () =>{
	if (goods.value && goods.value.length > 0) {
		let newGoods = goods.value;
		cart.value = [];
		let newCart = uni.getStorageSync('cart') || [];
		let tmpCart = [];
		if (newCart) {
			for (let i in newCart) {
				for (let ii in newGoods) {
					for (let iii in newGoods[ii].goodsList) {
						if (newCart[i].id == newGoods[ii].goodsList[iii].id) {
							tmpCart.push(newCart[i]);
						}
					}
				}
			}
			cart.value = tmpCart;
			cartPopupVisible.value = false;
		}
	}
}
const  getAds = async(shop_id) =>{
	let data = await menuAds({
		shop_id: shop_id ? shop_id : 0
	});
	if (data) {
		ads.value = data;
	}
}
const takout = (force = false) => {
	if (orderType.value == 'takeout' && force == false) return
	main.SET_ORDER_TYPE('takeout');

	if (!isLogin.value) {
		uni.navigateTo({
			url: '/pages/components/pages/login/login'
		})
		return
	} 

}
const takein = (force = false) => {
	if (orderType.value == 'takein' && force == false) return
	main.SET_ORDER_TYPE('takein');

	if (!isLogin.value) {
		uni.navigateTo({
			url: '/pages/components/pages/login/login'
		})
		return
	} 

}
const handleMenuTap = (id) => { //点击菜单项事件
	if (!sizeCalcState.value) {
		calcSize()
	}

	currentCateId.value = id
	nextTick(() => cateScrollTop.value = goods.value.find(item => item.id == id).top)
}
const handleGoodsScroll = ({ detail }) => { //商品列表滚动事件
	if (!sizeCalcState.value) {
		calcSize()
	}
	console.log('scrollTop:',detail)
	const {
		scrollTop
	} = detail
	let tabs = goods.value.filter(item => item.top <= scrollTop).reverse()
	if (tabs.length > 0) {
		currentCateId.value = tabs[0].id
	}
}
const calcSize = () => {
	let h = 10
	let view = uni.createSelectorQuery().select('#ads')
	if (view) {
		view.fields({
			size: true
		}, data => {
			if (data) {
				h += Math.floor(data.height)
			}
		}).exec()
	}
	goods.value.forEach(item => {
		let view = uni.createSelectorQuery().select(`#cate-${item.id}`)
		view.fields({
			size: true
		}, data => {
			console.log('h3:',h)
			item.top = h
			h += data.height
			item.bottom = h
		}).exec()
	})
	sizeCalcState.value = true
}
const handleAddToCart = (cate, newGood, num) =>{ //添加到购物车
	const index = cart.value.findIndex(item => {
		if (newGood) {
			return (item.id === newGood.id) && (item.valueStr === good.value.valueStr)
		} else {
			return item.id === newGood.id
		}
	})
	if (index > -1) {
		cart.value[index].number += num
	} else {
		cart.value.push({
			id: newGood.id,
			cate_id: cate.id,
			name: newGood.storeName,
			price: newGood.price,
			number: num,
			image: newGood.image,
			valueStr: good.value.valueStr
		})
	}
	uni.setStorageSync('cart', JSON.parse(JSON.stringify(cart.value)))
}
const handleReduceFromCart = (item, good) => {
	const index = cart.value.findIndex(item => item.id === good.id)
	cart.value[index].number -= 1
	if (cart.value[index].number <= 0) {
		cart.value.splice(index, 1)
	}
	uni.setStorageSync('cart', JSON.parse(JSON.stringify(cart.value)))
}
const showGoodDetailModal = (item, newGood) => {
	isCartShow.value = true
	good.value = JSON.parse(JSON.stringify({
		...newGood,
		number: 1
	}))
	category.value = JSON.parse(JSON.stringify(item))
	goodDetailModalVisible.value = true;
	console.log('goodDetailModalVisible:',goodDetailModalVisible.value)
	changePropertyDefault(0, 0,true);
}
const closeGoodDetailModal = () => { //关闭饮品详情模态框
	goodDetailModalVisible.value = false
    category.value = {}
	good.value = {}
}
const changePropertyDefault = (index, key, isDefault) => { //改变默认属性值
	let valueStr = ''
	console.log('good:',good.value)
	if(isDefault){
		newValue.value = []
		for(let i = 0;i < good.value.productAttr.length;i++){
			newValue.value[i] = good.value.productAttr[i].attrValueArr[0]
		}

		//valueStr = newValue.value.join(',')

	}else{
		newValue.value[index] = good.value.productAttr[index].attrValueArr[key]
		//valueStr = newValue.value.join(',')
	}
	
	valueStr = newValue.value.join(',')
	let productValue = good.value.productValue[valueStr]
	if(!productValue) {
		let skukey = JSON.parse(JSON.stringify(newValue.value))
		skukey.sort((a, b) => a.localeCompare(b))
		//console.log('skukey:',skukey)
		valueStr = skukey.join(',')
		productValue = good.value.productValue[valueStr]
	}

	
	//let productValue = good.value.productValue[valueStr]
	good.value.number = 1;
	good.value.price = parseFloat(productValue.price).toFixed(2);
	good.value.stock = productValue.stock;
	good.value.image = productValue.image ? productValue.image : good.value.image;
	good.value.valueStr = valueStr

}
const handlePropertyAdd = () => {
	good.value.number += 1
}
const handlePropertyReduce = () => {
	if (good.value.number === 1) return
	good.value.number -= 1
}
const handleAddToCartInModal = () => {
	if (good.value.stock <= 0) {
		uToast.value.show({message:'商品库存不足',type: 'error'});
		return;
	}
	handleAddToCart(category.value, good.value, good.value.number)
	closeGoodDetailModal()
}
const openCartPopup = () => { //打开/关闭购物车列表popup
	popup.value.open()
}
const handleCartClear = () => { //清空购物车
	uni.showModal({
		title: '提示',
		content: '确定清空购物车么',
		success: ({
			confirm
		}) => {
			if (confirm) {
				popup.value.close()
				cart.value = []
				uni.setStorageSync('cart', JSON.parse(JSON.stringify(cart.value)))
			}
		}
	})
}
const handleCartItemAdd = (index) => {
	cart.value[index].number += 1
	uni.setStorageSync('cart', JSON.parse(JSON.stringify(cart.value)))
}
const handleCartItemReduce = (index) => {
	if (cart.value[index].number === 1) {
		cart.value.splice(index, 1)
	} else {
		cart.value[index].number -= 1
	}
	if (!cart.value.length) {
		cartPopupVisible.value = false
	}
	uni.setStorageSync('cart', JSON.parse(JSON.stringify(cart.value)))
}
const toPay = () => {

	if (!isLogin.value) {
		uni.navigateTo({
			url: '/pages/components/pages/login/login'
		})
		return
	} else {
		if (store.value.status == 0) {
			uToast.value.show({message:'不在店铺营业时间内',type: 'error'});
			return;
		}
		if(orderType.value == 'takeout' && store.value.distance <= 0){
			uToast.value.show({message:'本店不支持外卖',type: 'error'});
			return;
		}
		// 判断当前是否在配送范围内
		if (orderType.value == 'takeout' && store.value.distance < store.value.far) {
			uToast.value.show({message:'选中的地址不在配送范围',type: 'error'});
			return;
		}

		uni.showLoading({
			title: '加载中'
		})
		uni.setStorageSync('cart', JSON.parse(JSON.stringify(cart.value)))

		uni.navigateTo({
			url: '/pages/components/pages/pay/pay'
		})
	}

	uni.hideLoading()
}

</script>

<style lang="scss" scoped>
// 点餐页局部 token（与 uni.scss 全局变量配合）
$menu-nav-height: 140rpx;
$menu-sidebar-width: 200rpx;
$menu-radius-sm: 8rpx;
$menu-radius-pill: 38rpx;
$menu-radius-cart: 48rpx;
$menu-good-image-size: 160rpx;
$menu-control-size-sm: 44rpx;
$menu-control-size: 46rpx;
$menu-list-bottom-space: 200rpx;
$menu-cart-height: 96rpx;
$menu-cart-popup-max-height: 60vh;
$menu-cart-list-bottom: 156rpx;
$menu-cart-price-offset: 120rpx;
$menu-sold-out-bg: #e1e4e4;
$menu-secondary-color: #5a5b5c;
$menu-shadow-cart: 0 0 20rpx rgba(0, 0, 0, 0.2);
$menu-dot-size: 34rpx;
$menu-badge-size: 36rpx;
$menu-tag-size: 40rpx;
$menu-font-menu: 26rpx;
$menu-gap-sm: 10rpx;
$menu-gap-base: 20rpx;
$menu-divider-height: 2rpx;

@mixin text-ellipsis {
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

@mixin quantity-control($size) {
	.btn,
	.number {
		width: $size;
		height: $size;
		line-height: $size;
		text-align: center;
	}

	.btn {
		padding: 0;
		border-radius: $border-radius-circle;
	}
}

@mixin list-divider {
	&::after {
		content: '';
		position: absolute;
		bottom: 0;
		left: 0;
		width: 100%;
		height: $menu-divider-height;
		background-color: $border-color;
		transform: scaleY(0.6);
	}
}

/* #ifdef H5 */
page {
	height: auto;
	min-height: 100%;
}
/* #endif */

.container {
	--menu-control-size-sm: #{$menu-control-size-sm};
	--menu-control-size: #{$menu-control-size};
	--menu-cart-popup-max-height: #{$menu-cart-popup-max-height};

	overflow: hidden;
	position: relative;
}

.loading {
	width: 100%;
	height: 100%;
	display: flex;
	align-items: center;
	justify-content: center;

	image {
		width: 260rpx;
		height: 260rpx;
		position: relative;
		margin-top: -200rpx;
		/* #ifdef h5 */
		margin-top: 0;
		/* #endif */
	}
}

.main {
	width: 100%;
	height: 100%;
	position: relative;
}

.nav {
	width: 100%;
	height: $menu-nav-height;
	display: flex;
	flex-direction: column;

	.header {
		width: 100%;
		height: $menu-nav-height;
		display: flex;
		align-items: center;
		justify-content: space-between;
		padding: $spacing-row-base;
		background-color: $text-color-white;

		.left {
			flex: 1;
			display: flex;
			flex-direction: column;

			.store-name {
				display: flex;
				align-items: center;
				justify-content: flex-start;
				margin-bottom: $menu-gap-sm;
				font-size: $font-size-lg;

				.small {
					font-size: $font-size-sm;
					color: $text-color-assist;
				}

				.iconfont {
					margin-left: $menu-gap-sm;
					line-height: 100%;
				}
			}

			.store-location {
				display: flex;
				align-items: center;
				justify-content: flex-start;
				font-size: $font-size-sm;
				color: $text-color-assist;

				.iconfont {
					display: table-cell;
					vertical-align: middle;
					line-height: 100%;
					color: $color-primary;
				}
			}
		}

		.right {
			display: flex;
			align-items: center;
			padding: 0 $menu-radius-pill;
			font-size: $font-size-sm;
			color: $text-color-assist;
			background-color: $bg-color-grey;
			border-radius: $menu-radius-pill;

			.dinein,
			.takeout {
				position: relative;
				display: flex;
				align-items: center;

				&.active {
					padding: 14rpx $menu-radius-pill;
					color: $text-color-white;
					background-color: $color-primary;
					border-radius: $menu-radius-pill;
				}
			}

			.takeout {
				flex: 1;
				height: 100%;
				margin-left: $spacing-row-base;
				padding: 14rpx 0;
			}

			.dinein.active {
				margin-left: -$menu-radius-pill;
			}

			.takeout.active {
				margin-right: -$menu-radius-pill;
			}
		}
	}
}
	
.content {
	width: 100%;
	height: calc(100vh - 212rpx);
	/* #ifdef H5 */
	height: calc(100vh - 212rpx - 188rpx);
	/* #endif */
	display: flex;

	.menus {
		width: $menu-sidebar-width;
		height: 100%;
		overflow: hidden;
		background-color: $bg-color-grey;

		.wrapper {
			width: 100%;
			height: 100%;

			.menu {
				position: relative;
				display: flex;
				align-items: center;
				justify-content: flex-start;
				padding: $spacing-row-lg $spacing-row-base;
				font-size: $menu-font-menu;
				color: $text-color-assist;

				&.current {
					color: $text-color-base;
					background-color: $text-color-white;
				}

				.dot {
					position: absolute;
					top: 16rpx;
					right: $menu-gap-sm;
					width: $menu-dot-size;
					height: $menu-dot-size;
					font-size: 22rpx;
					line-height: $menu-dot-size;
					text-align: center;
					color: $text-color-white;
					background-color: $color-primary;
					border-radius: $border-radius-circle;
				}

				&:last-child {
					margin-bottom: $menu-list-bottom-space;
				}
			}
		}
	}

	.goods {
		flex: 1;
		height: 100%;
		overflow: hidden;
		background-color: $text-color-white;

		.wrapper {
			width: 100%;
			height: 100%;
			padding: $spacing-row-base;

			.ads {
				height: calc(300 / 550 * 510rpx);

				image {
					width: 100%;
					height: 100%;
					border-radius: $menu-radius-sm;
				}
			}

			.list {
				width: 100%;
				font-size: $font-size-base;

				.category {
					width: 100%;

					.title {
						display: flex;
						align-items: center;
						padding: $spacing-row-lg 0;
						color: $text-color-base;

						.icon {
							width: 38rpx;
							height: 38rpx;
							margin-left: $menu-gap-sm;
						}
					}

					&:last-child {
						margin-bottom: $menu-list-bottom-space;
					}

					.items {
						display: flex;
						flex-direction: column;

						.good {
							display: flex;
							align-items: center;
							padding: 15rpx 0;

							.image {
								width: $menu-good-image-size;
								height: $menu-good-image-size;
								margin-right: $spacing-row-base;
								border-radius: $menu-radius-sm;
							}

							.right {
								flex: 1;
								display: flex;
								flex-direction: column;
								align-items: flex-start;
								justify-content: space-between;
								height: $menu-good-image-size;
								padding-right: 14rpx;
								overflow: hidden;

								.name {
									width: 100%;
									margin-bottom: $menu-gap-sm;
									font-size: $font-size-base;
									@include text-ellipsis;
								}

								.tips {
									width: 100%;
									height: 40rpx;
									margin-bottom: $menu-gap-sm;
									font-size: $font-size-sm;
									line-height: 40rpx;
									color: $text-color-assist;
									@include text-ellipsis;
								}

								.price_and_action {
									display: flex;
									align-items: center;
									justify-content: space-between;
									width: 100%;

									.price {
										font-size: $font-size-base;
										font-weight: 600;
									}

									.btn-group {
										position: relative;
										display: flex;
										align-items: center;
										justify-content: space-between;

										.btn {
											box-sizing: border-box;
											height: var(--menu-control-size-sm);
											padding: 0 $spacing-row-base;
											font-size: $font-size-sm;
											line-height: var(--menu-control-size-sm);

											&.property_btn {
												border-radius: 24rpx;
											}

											&.add_btn,
											&.reduce_btn {
												width: var(--menu-control-size-sm);
												padding: 0;
												border-radius: var(--menu-control-size-sm);
											}
										}

										.dot {
											position: absolute;
											top: -10rpx;
											right: -12rpx;
											width: $menu-badge-size;
											height: $menu-badge-size;
											font-size: $font-size-sm;
											line-height: $menu-badge-size;
											text-align: center;
											color: $color-primary;
											background-color: $text-color-white;
											border: 1px solid $color-primary;
											border-radius: $border-radius-circle;
										}

										@include quantity-control(var(--menu-control-size-sm));
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
	
.good-detail-modal {
	display: flex;
	flex-direction: column;
	width: 100%;
	height: 100%;

	.cover {
		display: flex;
		align-items: center;
		justify-content: center;
		height: 20rpx;

		.btn-group {
			position: absolute;
			top: 0;
			right: $menu-gap-sm;
			z-index: 210;
			display: flex;
			align-items: center;
			justify-content: space-around;

			image {
				width: $img-size-lg;
				height: $img-size-lg;
			}
		}
	}

	.detail {
		position: relative;
		width: 100%;
		min-height: 1vh;
		max-height: calc(90vh - 320rpx - 80rpx - 120rpx);

		.image {
			display: flex;
			align-items: center;
			justify-content: center;

			image {
				width: 260rpx;
				height: 260rpx;
			}
		}

		.wrapper {
			width: 100%;
			height: 100%;
			overflow: hidden;

			.basic {
				display: flex;
				flex-direction: column;
				padding: 0 $spacing-row-base $spacing-row-lg;

				.name {
					margin-bottom: $menu-gap-sm;
					font-size: $font-size-base;
					color: $text-color-base;
				}

				.tips {
					font-size: $font-size-sm;
					color: $text-color-grey;
				}
			}

			.properties {
				display: flex;
				flex-direction: column;
				width: 100%;
				padding: $menu-gap-sm $spacing-row-lg 0;
				border-top: $menu-divider-height solid $bg-color-grey;

				.property {
					display: flex;
					flex-direction: column;
					width: 100%;
					margin-bottom: $spacing-row-lg;

					.title {
						display: flex;
						align-items: center;
						justify-content: flex-start;
						width: 100%;
						margin-bottom: $spacing-row-base;

						.name {
							margin-right: $spacing-row-base;
							font-size: $menu-font-menu;
							color: $text-color-base;
						}

						.desc {
							flex: 1;
							font-size: $font-size-sm;
							color: $color-primary;
							@include text-ellipsis;
						}
					}

					.values {
						display: flex;
						flex-wrap: wrap;
						width: 100%;

						.value {
							margin-right: $spacing-col-base;
							margin-bottom: $spacing-col-base;
							padding: $spacing-col-base $spacing-row-lg;
							font-size: $menu-font-menu;
							color: $text-color-assist;
							background-color: $bg-color-grey;
							border-radius: $menu-radius-sm;

							&.default {
								color: $text-color-white;
								background-color: $color-primary;
							}
						}
					}
				}
			}
		}
	}

	.action {
		display: flex;
		align-items: center;
		justify-content: space-between;
		height: 120rpx;
		padding: 0 26rpx;
		background-color: $bg-color-grey;

		.left {
			flex: 1;
			display: flex;
			flex-direction: column;
			justify-content: center;
			margin-right: $spacing-row-base;
			overflow: hidden;

			.price {
				font-size: $font-size-lg;
				color: $text-color-base;
			}

			.props {
				width: 100%;
				font-size: $font-size-sm;
				color: $text-color-assist;
				@include text-ellipsis;
			}
		}

		.btn-group {
			display: flex;
			align-items: center;
			justify-content: space-around;

			.btn {
				font-size: $font-size-base;
			}

			@include quantity-control(var(--menu-control-size-sm));
		}
	}

	.add-to-cart-btn {
		display: flex;
		align-items: center;
		justify-content: center;
		height: 80rpx;
		font-size: $font-size-base;
		color: $text-color-white;
		background-color: $color-primary;
		border-radius: 0 0 $border-radius-lg $border-radius-lg;
	}
}
	
.cart-box {
	position: fixed;
	bottom: $spacing-row-lg;
	/* #ifdef H5 */
	bottom: var(--window-bottom);
	/* #endif */
	left: $spacing-row-lg;
	right: $spacing-row-lg;
	z-index: 9999;
	display: flex;
	align-items: center;
	justify-content: space-between;
	height: $menu-cart-height;
	background-color: $text-color-white;
	border-radius: $menu-radius-cart;
	box-shadow: $menu-shadow-cart;

	.cart-img {
		position: relative;
		width: $menu-cart-height;
		height: $menu-cart-height;
		margin-top: calc(-1 * #{$menu-cart-height} / 2);
	}

	.pay-btn {
		display: flex;
		align-items: center;
		height: 100%;
		padding: 0 $spacing-row-lg;
		font-size: $font-size-base;
		color: $text-color-white;
		border-radius: 0 50rpx 50rpx 0;
	}

	.mark {
		position: relative;
		margin-right: $spacing-row-lg;
		padding-left: 46rpx;

		.tag {
			position: absolute;
			top: -50rpx;
			right: -10rpx;
			display: flex;
			align-items: center;
			justify-content: center;
			width: $menu-tag-size;
			height: $menu-tag-size;
			padding: 4rpx;
			font-size: $font-size-sm;
			color: $text-color-white;
			background-color: $color-primary;
			border-radius: $border-radius-circle;
			opacity: 0.9;
		}
	}

	.price {
		flex: 1;
		color: $text-color-base;
	}
}

.cart-popup {
	.top {
		padding: $menu-gap-sm $spacing-row-lg;
		font-size: $font-size-sm;
		text-align: right;
		color: $menu-secondary-color;
		background-color: $bg-color-primary;
	}

	.cart-list {
		width: 100%;
		min-height: 1vh;
		max-height: var(--menu-cart-popup-max-height);
		overflow: hidden;
		background-color: $text-color-white;

		.wrapper {
			display: flex;
			flex-direction: column;
			height: 100%;
			padding: 0 $spacing-row-lg;
			margin-bottom: $menu-cart-list-bottom;

			.item {
				position: relative;
				display: flex;
				align-items: center;
				justify-content: space-between;
				padding: $spacing-row-lg 0;
				@include list-divider;

				.left {
					flex: 1;
					display: flex;
					flex-direction: column;
					margin-right: $spacing-row-lg;
					overflow: hidden;

					.name {
						font-size: $font-size-sm;
						color: $text-color-base;
					}

					.props {
						font-size: $font-size-sm;
						color: $text-color-assist;
						@include text-ellipsis;
					}
				}

				.center {
					margin-right: $menu-cart-price-offset;
					font-size: $font-size-base;
				}

				.right {
					display: flex;
					align-items: center;
					justify-content: space-between;
					@include quantity-control(var(--menu-control-size));

					.btn {
						font-size: $font-size-base;
					}
				}
			}
		}
	}
}

// 页面级工具类
.notice-bar {
	height: 60rpx;
	background-color: $text-color-white;
}

.store-header-img {
	width: $img-size-lg;
	height: $img-size-lg;
}

.bottom-spacer {
	height: 110rpx;
}

.locate-store-btn {
	position: absolute;
	top: 650rpx;
	z-index: 3001;
}

.background-grey {
	padding: 15rpx !important;
	background-color: $menu-sold-out-bg;
}
</style>

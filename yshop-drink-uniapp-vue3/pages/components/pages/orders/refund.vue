<template>
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<view class="p-2">
		<uv-list>
			<uv-list-item title="订单金额"  :rightText="'¥'+totalPrice"></uv-list-item>
			<uv-list-item title="退款金额"  :rightText="'¥'+payPrice"></uv-list-item>
			<uv-list-item title="退款类型"  rightText="整个订单"></uv-list-item>
			<uv-list-item title="退款原因"  rightText="协商一致"></uv-list-item>
			<uv-list-item title="退款留言" >
				<template #footer><uv-input placeholder="请输入内容" border="none" v-model="refundReasonWapExplain"></uv-input></template>
			</uv-list-item>
		</uv-list>
		<view class="mt-2">
			<uv-button shape="circle" type="warning" :plain="true" size="small" text="申请退款" @click="refund()"></uv-button>
		</view>
		<uv-toast ref="toast"></uv-toast>
	</view>
</template>

<script setup>
import {
  ref
} from 'vue'
import { onLoad} from '@dcloudio/uni-app'
import {
  orderRefund
} from '@/api/order'
const title = ref('申请退款')
const orderId = ref('')
const payPrice = ref('')
const totalPrice = ref('')
const refundReasonWapExplain = ref('')
const toast = ref()
onLoad((option) => {
	console.log('option:',option)
	orderId.value = option.orderId
	payPrice.value = option.payPrice
	totalPrice.value = option.totalPrice
})

const refund  = async() => {
	let data = await orderRefund({refundReasonWapExplain:refundReasonWapExplain.value,uni:orderId.value,text:'协商一致'});
	if (data) {
		uni.showToast({
			title: '提交成功',
			icon: 'success'
		})
	}
}

</script>

<style>
</style>
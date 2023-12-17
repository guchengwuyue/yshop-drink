<template>
 <el-drawer v-model="drawer" :title="dialogTitle" size="40%">
    <div>
      <el-descriptions title="收货信息1" :column="2" v-if="DetailData.orderType == 'takeout'">
        <el-descriptions-item label="用户昵称">{{ nickname }}</el-descriptions-item>
        <el-descriptions-item label="收货人">{{ DetailData.realName }}</el-descriptions-item>
        <el-descriptions-item label="联系电话">{{ DetailData.userPhone }}</el-descriptions-item>
        <el-descriptions-item label="收货地址">{{ DetailData.userAddress }}</el-descriptions-item>
      </el-descriptions>
      <el-descriptions title="商品明细" :column="3">
        <div v-for="(val, i ) in product" :key="i">
          <el-descriptions-item label="商品图片">
            <el-image style="width: 40px; height: 40px" :src="val.image" :fit="fit" />
          </el-descriptions-item>
          <el-descriptions-item label="商品名称">{{ val.title + ' - ' }}{{val.spec}}</el-descriptions-item>
          <el-descriptions-item label="商品价格">{{ '￥'+ val.price + ' x '+ val.number}}</el-descriptions-item>
       </div>
      </el-descriptions>
      <el-descriptions title="订单信息" :column="2">
        <el-descriptions-item label="门店">{{ DetailData.shopName }}</el-descriptions-item>
        <el-descriptions-item label="取餐号">{{ DetailData.numberId }}</el-descriptions-item>
        <el-descriptions-item label="订单号">{{ DetailData.orderId }}</el-descriptions-item>
        <el-descriptions-item label="订单状态">{{ DetailData.statusStr }}</el-descriptions-item>
        <el-descriptions-item label="商品总数">{{ DetailData.totalNum }}</el-descriptions-item>
        <el-descriptions-item label="商品总价">{{ DetailData.totalPrice }}</el-descriptions-item>
        <el-descriptions-item label="支付邮费">{{ DetailData.payPostage }}</el-descriptions-item>
        <el-descriptions-item label="优惠券金额">{{ DetailData.couponPrice }}</el-descriptions-item>
        <el-descriptions-item label="积分抵扣">{{ DetailData.useIntegral }}</el-descriptions-item>
        <el-descriptions-item label="实际支付">{{ DetailData.payPrice }}</el-descriptions-item>
        <el-descriptions-item label="赠送积分">{{ DetailData.gainIntegral }}</el-descriptions-item>
        <el-descriptions-item label="创建时间">{{ formatDate(DetailData.createTime)}}</el-descriptions-item>
        <el-descriptions-item label="支付时间">{{ formatDate(DetailData.payTime) }}</el-descriptions-item>
        <el-descriptions-item label="支付方式">{{ DetailData.payType }}</el-descriptions-item>
      </el-descriptions>
      <!-- <el-descriptions title="物流信息" :column="2">
        <el-descriptions-item label="快递公司">{{ DetailData.deliveryName }}</el-descriptions-item>
        <el-descriptions-item label="快递单号">{{ DetailData.deliveryId }} <el-button type="primary" @click="getLogistic(DetailData.deliverySn,DetailData.deliveryId)">物流追踪</el-button></el-descriptions-item>
      </el-descriptions> -->
      <el-timeline>
        <el-timeline-item
          v-for="(activity, index) in logisticResult"
          :key="index"
          :timestamp="activity.acceptTime"
        >
          {{ activity.acceptStation }}
        </el-timeline-item>
      </el-timeline>
      <!-- <el-button @click="innerDrawer = true">Click me!</el-button>
      <el-drawer
        v-model="innerDrawer"
        title="I'm inner Drawer"
        :append-to-body="true"
        :before-close="handleClose"
      >
        <p>_(:зゝ∠)_</p>
      </el-drawer> -->
    </div>
  </el-drawer>
</template>
<script setup lang="ts">
import * as StoreOrderApi from '@/api/mall/order/storeOrder'
import { formatDate } from '@/utils/formatTime'
//const message = useMessage() // 消息弹窗

const { t } = useI18n() // 国际化
// const message = useMessage() // 消息弹窗
const dialogTitle = ref('') // 弹窗的标题
const drawer = ref(false)
const DetailData = ref({})
const nickname = ref('')
const logisticResult = ref({})
const product = ref([])
/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  drawer.value = true
  dialogTitle.value = t('action.' + type)
  DetailData.value = await StoreOrderApi.getStoreOrder(id)
  nickname.value = DetailData.value.userRespVO.nickname
  product.value = DetailData.value.storeOrderCartInfoDOList
  console.log('aa:',DetailData.value )
}
defineExpose({ open }) // 提供 open 方法，用于打开弹窗

//const getLogistic = async(deliverySn,deliveryId) => {
//   const res  = await StoreOrderApi.getLogistic(deliverySn, deliveryId)
//   if (res.success == "false") {
//     message.error(res.reason)
//   }
//   logisticResult.value = res.traces
// }
</script>
<style scoped>
</style>
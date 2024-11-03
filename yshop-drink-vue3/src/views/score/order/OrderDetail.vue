<template>
 <el-drawer v-model="drawer" :title="dialogTitle" size="40%">
    <div>
      <el-descriptions title="收货信息" :column="2">
        <el-descriptions-item label="用户昵称">{{ nickname }}</el-descriptions-item>
        <el-descriptions-item label="收货人">{{ DetailData.customerName }}</el-descriptions-item>
        <el-descriptions-item label="联系电话">{{ DetailData.customerPhone }}</el-descriptions-item>
        <el-descriptions-item label="收货地址">{{ DetailData.customerAddress }}</el-descriptions-item>
      </el-descriptions>
      <el-descriptions title="商品明细" :column="2">
        <el-descriptions-item label="商品图片">
          <el-image style="width: 40px; height: 40px" :src="product.image" :fit="fit" />
        </el-descriptions-item>
        <el-descriptions-item label="商品名称">{{ product.title }}</el-descriptions-item>
        <el-descriptions-item label="商品价格">{{ product.score }}积分</el-descriptions-item>
      </el-descriptions>
      <el-descriptions title="订单信息" :column="2">
        <el-descriptions-item label="订单号">{{ DetailData.orderId }}</el-descriptions-item>
        <el-descriptions-item label="数量">{{ DetailData.number }}</el-descriptions-item>
        <el-descriptions-item label="花费积分">{{ DetailData.totalScore }}</el-descriptions-item>
        <el-descriptions-item label="是否支付">
           <span v-if="DetailData.havePaid == 0" style="color:#F56C6C">否</span>
           <span v-if="DetailData.havePaid == 1" style="color:#409EFF">是</span>
        </el-descriptions-item>
        <el-descriptions-item label="是否发货">
           <span v-if="DetailData.haveDelivered == 0" style="color:#F56C6C">否</span>
           <span v-if="DetailData.haveDelivered == 1" style="color:#409EFF">是</span>
        </el-descriptions-item>
        <el-descriptions-item label="是否收货">
           <span v-if="DetailData.haveReceived == 0" style="color:#F56C6C">否</span>
           <span v-if="DetailData.haveReceived == 1" style="color:#409EFF">是</span>
        </el-descriptions-item>
        <el-descriptions-item label="ip">{{ DetailData.ip }}</el-descriptions-item>
        <el-descriptions-item label="创建时间">{{ formatDate(DetailData.createTime)}}</el-descriptions-item>
      </el-descriptions>
      <el-descriptions title="物流信息" :column="2">
        <el-descriptions-item label="快递公司">{{ DetailData.expressCompany }}</el-descriptions-item>
        <el-descriptions-item label="快递单号">
          {{ DetailData.expressNumber }} 
          <el-button type="primary" @click="getLogistic(DetailData.expressSn,DetailData.expressNumber)">物流追踪</el-button>
        </el-descriptions-item>
      </el-descriptions>
      <el-timeline>
        <el-timeline-item
          v-for="(activity, index) in logisticResult"
          :key="index"
          :timestamp="activity.acceptTime"
        >
          {{ activity.acceptStation }}
        </el-timeline-item>
      </el-timeline>
    </div>
  </el-drawer>
</template>
<script setup lang="ts">
import * as OrderApi from '@/api/score/order'
import { formatDate } from '@/utils/formatTime'

const { t } = useI18n() // 国际化
const dialogTitle = ref('') // 弹窗的标题
const drawer = ref(false)
const DetailData = ref({})
const nickname = ref('')
const logisticResult = ref({})
const product = ref({})
/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  drawer.value = true
  dialogTitle.value = t('action.' + type)
  let data  = await OrderApi.getOrder(id)
  DetailData.value = data
  nickname.value = DetailData.value.userRespVO.nickname
  product.value = data.scoreProductRespVO
  console.log('aa:',product.value )
}
defineExpose({ open }) // 提供 open 方法，用于打开弹窗

const getLogistic = async(deliverySn,deliveryId) => {
  const res  = await OrderApi.getLogistic(deliverySn, deliveryId)
  if (res.success == "false") {
    message.error(res.reason)
  }
  logisticResult.value = res.traces
}
</script>
<style scoped>
</style>
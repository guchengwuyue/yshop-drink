<template>
 <el-drawer v-model="drawer" :title="dialogTitle" size="40%">
    <div>
      <el-descriptions title="收货信息1" :column="2" v-if="DetailData.orderType == 'takeout'">
        <el-descriptions-item label="用户昵称">{{ nickname }}</el-descriptions-item>
        <el-descriptions-item label="收货人">{{ DetailData.realName }}</el-descriptions-item>
        <el-descriptions-item label="联系电话">{{ DetailData.userPhone }}</el-descriptions-item>
        <el-descriptions-item label="收货地址">{{ DetailData.userAddress }}</el-descriptions-item>
      </el-descriptions>
      <el-descriptions title="商品明细" :column="1">
          <el-descriptions-item label="菜品">
            <div v-for="(num,k) in addProductMark + 1" :key="k">
              <div v-if="num > 1" style="text-align:center;font-weight:bold;font-size:22px">
                第{{ num - 1 }}次加菜
              </div>
              <div v-else style="text-align:center;font-weight:bold;font-size:22px">
                首次点菜
              </div>
              <table width="100%">
                <tr style="font-weight:bold;height:50px">
                  <td>图片</td>
                  <td>名称</td>
                  <td>价格</td>
                  <td>数量</td>
                  <td>状态</td>
                </tr>
                <tr  v-for="(val, i ) in product" :key="i" >
                    <td><el-image style="width: 40px; height: 40px" :src="val.image" :fit="fit" /></td>
                    <td>{{ val.title}}</td>
                    <td>{{ '￥'+ val.price}}</td>
                    <td>{{ ' x '+ val.number}}</td>
                    <td>
                      <el-tag  type="success" v-if="val.isOrder==1">已出单</el-tag>
                      <el-tag  type="danger" v-else>未出单</el-tag>
                    </td>
                </tr>
              </table>
            </div>
          </el-descriptions-item>
      </el-descriptions>
      <el-descriptions title="订单信息" :column="2">
        <template #title>
          订单信息
          <el-tag  type="danger" v-if="DetailData.orderType=='desk'">堂食</el-tag>
          <el-tag  type="danger" v-if="DetailData.orderType=='takeout'">外卖</el-tag>
          <el-tag  type="danger" v-if="DetailData.orderType=='takein'">自取</el-tag>
        </template>
        <el-descriptions-item label="门店">{{ DetailData.shopName }}</el-descriptions-item>
        <el-descriptions-item label="取餐号">{{ DetailData.numberId }}</el-descriptions-item>
        <el-descriptions-item label="桌位号">{{ DetailData.deskNumber ? DetailData.deskNumber : '无' }}</el-descriptions-item>
        <el-descriptions-item label="就餐人数">{{ DetailData.deskPeople ? DetailData.deskPeople : '无' }}</el-descriptions-item>
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
const addProductMark = ref(0)
/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  drawer.value = true
  dialogTitle.value = t('action.' + type)
  DetailData.value = await StoreOrderApi.getStoreOrder(id)
  nickname.value = DetailData.value.userRespVO.nickname
  product.value = DetailData.value.storeOrderCartInfoDOList
  //获取加餐数量
  //addProductMark.value = DetailData.value.storeOrderCartInfoDOList[DetailData.value.storeOrderCartInfoDOList.length - 1].addProductMark

  console.log('addProductMark.value:',addProductMark.value )
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
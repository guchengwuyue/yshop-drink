<template>
  <el-drawer v-model="drawer" :title="dialogTitle" size="40%">
    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="oid" label="订单id" width="180" />
      <el-table-column prop="changeMessage" label="操作记录" width="180" />
      <el-table-column prop="changeTime" :formatter="dateFormatter" label="操作时间" />
    </el-table>
  </el-drawer>

</template>
<script setup lang="ts">
import * as StoreOrderApi from '@/api/mall/order/storeOrder'
import { dateFormatter } from '@/utils/formatTime'

const { t } = useI18n() // 国际化
const dialogTitle = ref('') // 弹窗的标题
const drawer = ref(false)
const tableData = ref([])
/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  drawer.value = true
  dialogTitle.value = t('action.' + type)
  console.log(id)
  tableData.value = await StoreOrderApi.getStoreOrderRecordList(id)
}
defineExpose({ open }) // 提供 open 方法，用于打开弹窗
</script>
<style scoped>
</style>
<template>
  <ContentWrap>
   <el-tabs v-model="activeName"  @tab-click="handleClick">
     <el-tab-pane label="全部订单" name="first"/>
     <el-tab-pane label="普通订单" name="second"/>
   </el-tabs>
 </ContentWrap>
 <ContentWrap>
   <!-- 搜索工作栏 -->
   <el-form
     class="-mb-15px"
     :model="queryParams"
     ref="queryFormRef"
     :inline="true"
     label-width="68px"
   >
     <el-form-item label="订单号" prop="orderId">
       <el-input
         v-model="queryParams.orderId"
         placeholder="请输入订单号"
         clearable
         @keyup.enter="handleQuery"
         class="!w-240px"
       />
     </el-form-item>
     <el-form-item label="取餐号" prop="numberId">
       <el-input
         v-model="queryParams.numberId"
         placeholder="请输入取餐号"
         clearable
         @keyup.enter="handleQuery"
         class="!w-240px"
       />
     </el-form-item>   
     <el-form-item>
       <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
       <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
       <el-button
         type="success"
         plain
         @click="handleExport"
         :loading="exportLoading"
         v-hasPermi="['order:store-order:export']"
       >
         <Icon icon="ep:download" class="mr-5px" /> 导出
       </el-button>
     </el-form-item>
   </el-form>
   
 </ContentWrap>

 <!-- 列表 -->
 <ContentWrap>

     <el-row :gutter="24">
       <el-col :span="6" v-for="(order,k) in list" :key="k">
         <div >
           <el-card class="box-card">
             <template #header>
               <div class="card-header">
                 <span>{{ order.shopName }}</span>
               </div>
             </template>
             <div>取餐号:{{ order.numberId }}</div>
             <div>下单时间:{{ formatDate(order.createTime) }}</div>
             <div>取餐时间:{{ formatDate(order.getTime) }}</div>
             <div>类型:{{ order.orderType == 'takeout' ? '外卖' : '自取' }}</div>
             <div>  
               <el-button
                 type="primary"
                 @click="openForm('orderDetail', order.id)"
                 v-hasPermi="['order:store-order:update']"
               >详情</el-button>
               <el-button
                 type="primary"
                 @click="openForm('orderSend', order.id)"
                 v-hasPermi="['order:store-order:update']"
               >出单</el-button>
               <el-button
                 type="danger"
                 @click="handleDelete(order.id)"
                 v-hasPermi="['order:store-order:update']"
               >删除</el-button>
             </div>
           </el-card>
        </div>
        </el-col>
        </el-row>
 </ContentWrap>

 <!-- 表单弹窗：添加/修改 -->
 <StoreOrderForm ref="formRef" @success="getList" />
 <OrderSend ref="formRef1" @success="getList" />
 <OrderSendInfo ref="formRef2" @success="getList" />
 <StoreOrderRemark ref="formRef3" @success="getList" />
 <OrderDetail ref="formRef4" />
 <OrderRecord ref="formRef5" />
</template>

<script setup lang="ts" name="StoreOrder">
// import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import * as StoreOrderApi from '@/api/mall/order/storeOrder'
import StoreOrderForm from './StoreOrderForm.vue'
import OrderSend from './OrderSend.vue'
import OrderSendInfo from './OrderSendInfo.vue'
import StoreOrderRemark from './StoreOrderRemark.vue'
import OrderDetail from './OrderDetail.vue'
import OrderRecord from './OrderRecord.vue'
import type { TabsPaneContext } from 'element-plus'
import { formatDate } from '@/utils/formatTime'
const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const total = ref(0) // 列表的总页数
const list = ref([]) // 列表的数据
const queryParams = reactive({
 pageNo: 1,
 pageSize: 10,
 orderId: "",
 realName: "",
 userPhone: "",
 createTime: [],
 orderStatus: 1,
 payStatus: "",
 numberId: undefined

})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

const activeName = ref('first')



const handleClick = (tab: TabsPaneContext, event: Event) => {
 console.log(tab, event)
}


/** 查询列表 */
const getList = async () => {
 loading.value = true
 try {
   const data = await StoreOrderApi.getStoreOrderPage(queryParams)
   list.value = data.list
   //console.log("aa:",list.value)
   total.value = data.total
 } finally {
   loading.value = false
 }
}



/** 搜索按钮操作 */
const handleQuery = () => {
 queryParams.pageNo = 1
 getList()
}

/** 重置按钮操作 */
const resetQuery = () => {
 queryFormRef.value.resetFields()
 handleQuery()
}

/** 添加/修改操作 */
const formRef = ref()
const formRef1 = ref()
const formRef2 = ref()
const formRef3 = ref()
const formRef4 = ref()
const formRef5 = ref()
const openForm = (type: string, id?: number) => {
 if (type == 'updateOrder') {
   formRef.value.open(type, id)
 } else if (type == 'orderSend') {
   formRef1.value.open(type, id)
 }else if (type == 'sendInfo') {
   formRef2.value.open(type, id)
 }else if (type == 'remark') {
   formRef3.value.open(type, id)
 }else if (type == 'orderDetail') {
   formRef4.value.open(type, id)
 }else if (type == 'orderRecord') {
   formRef5.value.open(type, id)
 }

 
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
 try {
   // 删除的二次确认
   await message.delConfirm()
   // 发起删除
   await StoreOrderApi.deleteStoreOrder(id)
   // 刷新列表
   getList()
 } catch {}
}


/** 导出按钮操作 */
const handleExport = async () => {
 try {
   // 导出的二次确认
   await message.exportConfirm()
   // 发起导出
   exportLoading.value = true
   const data = await StoreOrderApi.exportStoreOrder(queryParams)
   download.excel(data, '订单.xls')
 } catch {
 } finally {
   exportLoading.value = false
 }
}

/** 初始化 **/
onMounted(() => {
 getList()
})
</script>

<style scoped  >
img {
       height: 36px;
       display: block;
   }
.tabBox{
   width: 100%;
   height: 100%;
   display: flex;
   align-items: center
   }
.tabBox_img{
   width: 36px;
   height: 36px;
 }
.tabBox_img img{
 width: 100%;
 height: 100%;
}
.tabBox_tit{
   width :60%;
   font-size: 12px !important;
   margin: 0 2px 0 10px;
   letter-spacing: 1px;
   padding: 5px 0;
   box-sizing: border-box;
   text-align: left;
 }
 .tabBox_pice{
   width :30%;
   font-size: 12px !important;
   margin: 0 2px 0 10px;
   letter-spacing: 1px;
   padding: 5px 0;
   box-sizing: border-box;
   text-align: left;
 }

</style>
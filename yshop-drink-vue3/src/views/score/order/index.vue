<template>
  <ContentWrap>
    <el-tabs v-model="activeName">
      <el-tab-pane label="全部订单" name="first"/>
    </el-tabs>
    <el-form-item label="订单状态：" >
      <el-radio-group v-model="orderStatus" size="large"  fill="#DC143C" @change="queryOrderStatus">
        <el-radio-button label="-1">全部</el-radio-button>
        <el-radio-button label="0">待发货</el-radio-button>
        <el-radio-button label="1">待收货</el-radio-button>
        <el-radio-button label="2">已完成</el-radio-button>
      </el-radio-group>
    </el-form-item>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="68px"
    >
      <el-form-item label="用户名" prop="userId">
        <el-input
          v-model="queryParams.customerPhone"
          placeholder="请输入电话"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>

      <el-form-item label="订单号" prop="number">
        <el-input
          v-model="queryParams.orderId"
          placeholder="请输入订单号"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list">
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="订单号" align="center" prop="orderId" width="190" />
      <el-table-column label="用户id|昵称" align="center" prop="uid" width="150">
        <template #default="scope">
          <span>{{ scope.row.uid }}|{{ scope.row.userRespVO.nickname }}</span>
        </template>
      </el-table-column>
      <el-table-column label="商品信息" align="center" prop="productId" width="350">
        <template #default="scope">
          <div class="tabBox">
              <div class="tabBox_img">
                  <img :src="scope.row.scoreProductRespVO.image" />
              </div>
              <span class="tabBox_tit">{{ scope.row.scoreProductRespVO.title }}</span>
              <span class="tabBox_pice">{{ '积分'+ scope.row.scoreProductRespVO.score + ' x '+ scope.row.number}}</span>
          </div>
        </template>
      </el-table-column>
      <!-- <el-table-column label="数量" align="center" prop="number" /> -->
      <el-table-column label="总消耗积分" align="center" prop="totalScore" />
      <el-table-column label="是否支付" align="center" prop="havePaid">
        <template #default="scope">
           <span v-if="scope.row.havePaid == 0" style="color:#F56C6C">否</span>
           <span v-if="scope.row.havePaid == 1" style="color:#409EFF">是</span>
        </template>
      </el-table-column>
      <el-table-column label="是否发货" align="center" prop="haveDelivered">
        <template #default="scope">
           <span v-if="scope.row.haveDelivered == 0" style="color:#F56C6C">否</span>
           <span v-if="scope.row.haveDelivered == 1" style="color:#409EFF">是</span>
        </template>
      </el-table-column>
      <el-table-column label="是否收货" align="center" prop="haveReceived">
        <template #default="scope">
           <span v-if="scope.row.haveReceived == 0" style="color:#F56C6C">否</span>
           <span v-if="scope.row.haveReceived == 1" style="color:#409EFF">是</span>
        </template>
      </el-table-column>
      <el-table-column
        label="添加时间"
        align="center"
        prop="createTime"
        :formatter="dateFormatter"
        width="170"
      />
      <el-table-column label="操作" align="center" fixed="right" width="150">
        <template #default="scope">
          <div class="flex justify-center items-center">
          <el-button
            link
            type="primary"
            @click="openForm('orderSend', scope.row.id)"
            v-if = "scope.row.haveDelivered == 0 && scope.row.havePaid == 1"
            v-hasPermi="['score:order:update']"
          >
            发货
          </el-button>
          <el-dropdown>
            <el-button type="primary" link><Icon icon="ep:d-arrow-right" /> 更多</el-button>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="openForm('orderDetail', scope.row.id)">订单详情</el-dropdown-item>
                <el-dropdown-item @click="handleDelete(scope.row.id)">删除订单</el-dropdown-item>
                <el-dropdown-item v-if = "scope.row.haveDelivered == 1 && scope.row.haveReceived == 0" @click="handleTake(scope.row.id)">后台收货</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <Pagination
      :total="total"
      v-model:page="queryParams.pageNo"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <OrderForm ref="formRef" @success="getList" />
  <OrderDetail ref="formRef4" />
</template>

<script setup lang="ts" name="ScoreOrder">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import * as OrderApi from '@/api/score/order'
import OrderForm from './OrderForm.vue'
import OrderDetail from './OrderDetail.vue'
const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const total = ref(0) // 列表的总页数
const list = ref([]) // 列表的数据
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  orderId: "",
  type: -1,
  customerName: null,
  customerPhone: null,
  customerAddress: null,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中
const activeName = ref('first')
const orderStatus = ref('-1')

const queryOrderStatus = (value) => {
  queryParams.type = value
  getList()
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await OrderApi.getOrderPage(queryParams)
    list.value = data.list
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
const formRef4 = ref()
const openForm = (type: string, id?: number) => {
  if (type == 'orderSend') {
    formRef.value.open(type, id)
  }else if (type == 'orderDetail') {
    formRef4.value.open(type, id)
  }
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await OrderApi.deleteOrder(id)
    message.success(t('common.delSuccess'))
    // 刷新列表
    await getList()
  } catch {}
}

/** 导出按钮操作 */
const handleExport = async () => {
  try {
    // 导出的二次确认
    await message.exportConfirm()
    // 发起导出
    exportLoading.value = true
    const data = await OrderApi.exportOrder(queryParams)
    download.excel(data, '积分商城订单.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 确认收货按钮操作 */
const handleTake = async (id: number) => {
  try {
    // 删除的二次确认
    await message.confirm('修改收货状态')
    // 发起删除
    await OrderApi.takeStoreOrder(id)
    message.success(t('common.updateSuccess'))
    // 刷新列表
    await getList()
  } catch {}
}

/** 初始化 **/
onMounted(() => {
  getList()
})
</script>
<style>
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
    /* width :40%; */
    font-size: 12px !important;
    margin: 0 2px 0 10px;
    letter-spacing: 1px;
    padding: 5px 0;
    box-sizing: border-box;
    text-align: left;
  }
  .tabBox_pice{
    /* width :30%; */
    font-size: 12px !important;
    margin: 0 2px 0 10px;
    letter-spacing: 1px;
    padding: 5px 0;
    box-sizing: border-box;
    text-align: left;
  }
</style>

<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="100px"
    >
      <el-form-item label="店铺名称" prop="shopName">
        <el-input
          v-model="queryParams.shopName"
          placeholder="请输入店铺名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="优惠券名称" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入优惠券名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button type="primary" @click="openForm('create')" v-hasPermi="['coupon::create']">
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['coupon::export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list">
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="可用店铺" align="center" prop="shopName"  width="150" />
      <el-table-column label="优惠券名称" align="center" prop="title"  width="150" />
      <el-table-column label="是否上架" align="center" prop="isSwitch">
        <template #default="scope">
          <span v-if="scope.row.isSwitch == 1">上架</span>
          <span v-else>下架</span>
         </template>
      </el-table-column>
      <el-table-column label="消费多少可用" align="center" prop="least"  width="150" />
      <el-table-column label="优惠券金额" align="center" prop="value"  width="150"/>
      <el-table-column label="可用类型" align="center" prop="type">
        <template #default="scope">
          <span v-if="scope.row.type == 1">自取</span>
          <span v-if="scope.row.type == 0">外卖</span>
          <span v-else>通用</span>
         </template>
      </el-table-column>
      <el-table-column label="兑换码" align="center" prop="exchangeCode" />
      <el-table-column label="已领" align="center" prop="receive" />
      <el-table-column label="发行数量" align="center" prop="distribute" />
      <el-table-column label="限领数量" align="center" prop="limit" />
      <el-table-column
        label="开始时间"
        align="center"
        prop="startTime"
        :formatter="dateFormatter"
        width="170"
      />
      <el-table-column
        label="结束时间"
        align="center"
        prop="endtIme"
        :formatter="dateFormatter"
        width="170"
      />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        :formatter="dateFormatter"
        width="170"
      />

      <el-table-column label="操作" align="center" fixed="right" width="150">
        <template #default="scope">
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['coupon::update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['coupon::delete']"
          >
            删除
          </el-button>
          <el-button
            link
            type="primary"
            @click="openForm('couponRecord', scope.row.id)"
            v-hasPermi="['coupon::delete']"
          >
            领取记录
          </el-button>
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
  <Form ref="formRef" @success="getList" />
  <OrderRecord ref="formRef5" />
</template>

<script setup lang="ts" name="Coupon">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import * as Api from '@/api/mall/coupon/'
import Form from './Form.vue'
import OrderRecord from './user/OrderRecord.vue'
const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const total = ref(0) // 列表的总页数
const list = ref([]) // 列表的数据
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  shopId: null,
  shopName: null,
  title: null,
  switch: null,
  least: null,
  value: null,
  startTime: [],
  endtIme: [],
  createTime: [],
  weigh: null,
  type: null,
  exchangeCode: null,
  receive: null,
  distribute: null,
  score: null,
  instructions: null,
  image: null,
  limit: null
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await Api.getCouponPage(queryParams)
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
const formRef5 = ref()
const openForm = (type: string, id?: number) => {
  if (type == 'couponRecord') {
    formRef5.value.open(type, id)
  }else{
    formRef.value.open(type, id)
  }
  
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await Api.deleteCoupon(id)
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
    const data = await Api.exportCoupon(queryParams)
    download.excel(data, '优惠券.xls')
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

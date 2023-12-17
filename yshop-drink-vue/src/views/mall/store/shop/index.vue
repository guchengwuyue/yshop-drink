<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="68px"
    >
      <el-form-item label="店铺名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入店铺名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="店铺电话" prop="mobile">
        <el-input
          v-model="queryParams.mobile"
          placeholder="请输入店铺电话"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button type="primary" @click="openForm('create')" v-hasPermi="['store:shop:create']">
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['store:shop:export']"
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
      <el-table-column label="店铺名称" align="center" prop="name" width="150" />
      <el-table-column label="店铺电话" align="center" prop="mobile" width="150" />
      <el-table-column label="门店图片" align="center" prop="image" width="150">
        <template #default="scope">
          <el-image style="width: 100px; height: 100px" :src="scope.row.image"  />
        </template>
      </el-table-column>
      <el-table-column label="经度" align="center" prop="lng" width="150" />
      <el-table-column label="纬度" align="center" prop="lat"  width="150" />
      <el-table-column label="外卖配送距离" align="center" prop="distance" width="150">
        <template #default="scope">
          <span v-if="scope.row.distance == 0">
            到店消费
          </span>
          <span v-else>
            {{ scope.row.distance }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="起送价钱" align="center" prop="minPrice" />
      <el-table-column label="配送价格" align="center" prop="deliveryPrice" />
      <el-table-column label="是否营业" align="center" prop="status">
        <template #default="scope">
          <span v-if="scope.row.status == 1">是</span>
          <span v-else>否</span>
         </template>
      </el-table-column>
      <el-table-column
        label="营业开始时间"
        align="center"
        prop="startTime"
        :formatter="dateFormatter2"
        width="150"
      />
      <el-table-column
        label="营业结束时间"
        align="center"
        prop="endTime"
        :formatter="dateFormatter2"
        width="150"
      />
      <el-table-column
        label="添加时间"
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
            v-hasPermi="['store:shop:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['store:shop:delete']"
          >
            删除
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
  <ShopForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts" name="StoreShop">
import { dateFormatter, dateFormatter2 } from '@/utils/formatTime'
import download from '@/utils/download'
import * as ShopApi from '@/api/mall/store/shop'
import ShopForm from './ShopForm.vue'
const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const total = ref(0) // 列表的总页数
const list = ref([]) // 列表的数据
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  name: null,
  mobile: null,
  image: null,
  images: null,
  address: null,
  addressMap: null,
  lng: null,
  lat: null,
  distance: null,
  minPrice: null,
  deliveryPrice: null,
  notice: null,
  status: null,
  adminId: null,
  uniprintId: null,
  createTime: [],
  startTime: [],
  endTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await ShopApi.getShopPage(queryParams)
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
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await ShopApi.deleteShop(id)
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
    const data = await ShopApi.exportShop(queryParams)
    download.excel(data, '门店管理.xls')
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

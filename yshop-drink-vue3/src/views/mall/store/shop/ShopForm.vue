<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="50%">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="150px"
      v-loading="formLoading"
    >
      <el-form-item label="店铺名称" prop="name">
        <el-input v-model="formData.name" placeholder="请输入店铺名称" />
      </el-form-item>
      <el-form-item label="店铺电话" prop="mobile">
        <el-input v-model="formData.mobile" placeholder="请输入店铺电话" />
      </el-form-item>
      <el-form-item label="门店头像" prop="image">
          <Materials v-model="formData.image" num="1" type="image" />
      </el-form-item>
      <el-form-item label="门店组图" prop="images">
        <Materials v-model="formData.images" num="5" type="image" />
      </el-form-item>
      <el-form-item label="营业开始时间" prop="startTime">
        <el-time-picker v-model="formData.startTime" placeholder="选择营业开始时间" />
      </el-form-item>
      <el-form-item label="营业结束时间" prop="endTime">
        <el-time-picker v-model="formData.endTime" placeholder="选择营业结束时间" />
      </el-form-item>
      <el-form-item label="选择地图定位" prop="addressMap">
        <el-button @click="openForm"  type="primary">点击选择地址获取经纬度</el-button>
      </el-form-item>
      <el-form-item label="地图定位地址" prop="addressMap">
        <el-input v-model="formData.addressMap" placeholder="请输入地图定位地址" />
      </el-form-item>
      <el-form-item label="经度" prop="lng">
        <el-input v-model="formData.lng" placeholder="请输入经度" />
      </el-form-item>
      <el-form-item label="纬度" prop="lat">
        <el-input v-model="formData.lat" placeholder="请输入纬度" />
      </el-form-item>
      <el-form-item label="详细地址" prop="address">
        <el-input v-model="formData.address" placeholder="请输入详细地址" />
      </el-form-item>
      <el-form-item label="外卖配送距离/千米" prop="distance">
        <el-input v-model="formData.distance" placeholder="请输入外卖配送距离,单位为千米。0表示不送外卖" />
      </el-form-item>
      <el-form-item label="起送价钱" prop="minPrice">
        <el-input v-model="formData.minPrice" placeholder="请输入起送价钱" />
      </el-form-item>
      <el-form-item label="配送价格" prop="deliveryPrice">
        <el-input v-model="formData.deliveryPrice" placeholder="请输入配送价格" />
      </el-form-item>
      <el-form-item label="公告" prop="notice">
        <el-input type="textarea" rows="5"  v-model="formData.notice" placeholder="请输入公告" />
      </el-form-item>
      <el-form-item label="绑定管理员" prop="adminId" v-if ='isShow'>
        <el-select
          v-model="formData.adminId"
          multiple
          placeholder="选择用户"
        >
          <el-option
            v-for="item in adminUsers"
            :key="item.id"
            :label="item.nickname"
            :value="item.id"
          />
        </el-select>
        <div style="color: red;">绑定管理员用于分店管理</div>
      </el-form-item>
      <el-form-item label="是否营业" prop="status">
        <el-radio-group v-model="formData.status">
          <el-radio :label="1">是</el-radio>
          <el-radio :label="0">否</el-radio>
        </el-radio-group>
      </el-form-item>

     
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
  <MyMap ref="formRef1" @mapConfirm="mapConfirm" />
</template>
<script setup lang="ts">
import * as ShopApi from '@/api/mall/store/shop'
import * as UserApi from '@/api/system/user'
import MyMap from './map.vue'
import { CACHE_KEY, useCache } from '@/hooks/web/useCache'

const { wsCache } = useCache()

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  name: undefined,
  mobile: undefined,
  image: undefined,
  images: undefined,
  address: undefined,
  addressMap: undefined,
  lng: undefined,
  lat: undefined,
  distance: undefined,
  minPrice: undefined,
  deliveryPrice: undefined,
  notice: undefined,
  status: undefined,
  adminId: undefined,
  uniprintId: undefined,
  startTime: undefined,
  endTime: undefined
})
const formRules = reactive({
  name: [{ required: true, message: '店铺名称不能为空', trigger: 'blur' }],
  mobile: [{ required: true, message: '店铺电话不能为空', trigger: 'blur' }],
  image: [{ required: true, message: '图片不能为空', trigger: 'blur' }],
  images: [{ required: true, message: '多张图片不能为空', trigger: 'blur' }],
  address: [{ required: true, message: '详细地址不能为空', trigger: 'blur' }],
  addressMap: [{ required: true, message: '地图定位地址不能为空', trigger: 'blur' }],
  lng: [{ required: true, message: '经度不能为空', trigger: 'blur' }],
  lat: [{ required: true, message: '纬度不能为空', trigger: 'blur' }],
  distance: [{ required: true, message: '外卖配送距离,单位为千米。0表示不送外卖不能为空', trigger: 'blur' }],
  minPrice: [{ required: true, message: '起送价钱不能为空', trigger: 'blur' }],
  deliveryPrice: [{ required: true, message: '配送价格不能为空', trigger: 'blur' }],
  notice: [{ required: true, message: '公告不能为空', trigger: 'blur' }],
  status: [{ required: true, message: '是否营业:0=否,1=是不能为空', trigger: 'blur' }],
  adminId: [{ required: true, message: '管理员id不能为空', trigger: 'blur' }],
  startTime: [{ required: true, message: '营业开始时间不能为空', trigger: 'blur' }],
  endTime: [{ required: true, message: '营业结束时间不能为空', trigger: 'blur' }]
})
const formRef = ref() // 表单 Ref

const adminUsers = ref([])
const isShow = ref(true)

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  const user = wsCache.get(CACHE_KEY.USER)

  if(user.user.shopId > 0) {
    isShow.value = false
  }
  dialogVisible.value = true
  dialogTitle.value = t('action.' + type)
  formType.value = type
  resetForm()
  getList()
  // 修改时，设置数据
  if (id) {
    formLoading.value = true
    try {
      formData.value = await ShopApi.getShop(id)
      formData.value.adminId = formData.value.adminId.map(Number)
    } finally {
      formLoading.value = false
    }
  }
}
defineExpose({ open }) // 提供 open 方法，用于打开弹窗

/** 提交表单 */
const emit = defineEmits(['success']) // 定义 success 事件，用于操作成功后的回调
const submitForm = async () => {
  // 校验表单
  if (!formRef) return
  const valid = await formRef.value.validate()
  if (!valid) return
  // 提交请求
  formLoading.value = true
  try {
    const data = formData.value as unknown as ShopApi.ShopVO
    if (formType.value === 'create') {
      await ShopApi.createShop(data)
      message.success(t('common.createSuccess'))
    } else {
      await ShopApi.updateShop(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    // 发送操作成功的事件
    emit('success')
  } finally {
    formLoading.value = false
  }
}

const formRef1 = ref() // 表单 Ref
const openForm = () => {
  formRef1.value.open()
}

const mapConfirm = (mapResult) => {
  console.log('mapResult:',mapResult)
  formData.value.addressMap = mapResult.address
  formData.value.lng = mapResult.point.lng
  formData.value.lat = mapResult.point.lat
}

const getList = async () => {
  try {
    const data = await UserApi.getSimpleUserList()
    let arr = data.filter(elem => elem.id !== 1);
    adminUsers.value = arr

  } finally {
    
  }
}




/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    name: undefined,
    mobile: undefined,
    image: undefined,
    images: undefined,
    address: undefined,
    addressMap: undefined,
    lng: undefined,
    lat: undefined,
    distance: undefined,
    minPrice: undefined,
    deliveryPrice: undefined,
    notice: undefined,
    status: 1,
    adminId: undefined,
    uniprintId: undefined,
    startTime: undefined,
    endTime: undefined
  }
  formRef.value?.resetFields()
}
</script>
<style scoped>
.map {
  width: 100%;
  height: 300px;
}
</style>

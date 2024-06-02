<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
  
      <el-form-item label="展示店铺" prop="shopId">
        <el-select
          v-model="formData.shopId"
          placeholder="选择店铺"
        >
         <el-option
            :key="0"
            label="全部"
            :value="0"
          />
          <el-option
            v-for="item in shopList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="图片" prop="image">
          <Materials v-model="formData.image" num="1" type="image" />
      </el-form-item>
      <el-form-item label="权重" prop="weigh">
        <el-input v-model="formData.weigh" placeholder="请输入权重" />
      </el-form-item>
      <el-form-item label="是否可用" prop="isSwitch">
        <el-radio-group v-model="formData.isSwitch">
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
</template>
<script setup lang="ts">
import * as AdsApi from '@/api/mall/shop/ads'
import * as ShopApi from '@/api/mall/store/shop'

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  image: undefined,
  isSwitch: undefined,
  weigh: undefined,
  shopId: 0
})
const formRules = reactive({
  image: [{ required: true, message: '图片不能为空', trigger: 'blur' }],
  weigh: [{ required: true, message: '权重不能为空', trigger: 'blur' }],
  shopId: [{ required: true, message: '请选择店铺', trigger: 'blur' }]
})
const formRef = ref() // 表单 Ref
const shopList = ref([])

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = t('action.' + type)
  formType.value = type
  resetForm()
  getList()
  // 修改时，设置数据
  if (id) {
    formLoading.value = true
    try {
      formData.value = await AdsApi.getAds(id)
      formData.value.shopId = Number(formData.value.shopId)
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
    const data = formData.value as unknown as AdsApi.AdsVO
    if (formType.value === 'create') {
      await AdsApi.createAds(data)
      message.success(t('common.createSuccess'))
    } else {
      await AdsApi.updateAds(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    // 发送操作成功的事件
    emit('success')
  } finally {
    formLoading.value = false
  }
}

const getList = async () => {
  try {
    const data = await ShopApi.getShopList()
    shopList.value = data

  } finally {
    
  }
}


/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    image: undefined,
    isSwitch: 1,
    weigh: undefined,
    shopId: 0
  }
  formRef.value?.resetFields()
}
</script>

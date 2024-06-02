<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="150px"
      v-loading="formLoading"
    >
      <el-form-item label="收货人姓名" prop="realName">
        <el-input v-model="formData.realName" placeholder="请输入收货人姓名" />
      </el-form-item>
      <el-form-item label="收货人电话" prop="phone">
        <el-input v-model="formData.phone" placeholder="请输入收货人电话" />
      </el-form-item>
      <el-form-item label="收货人地址" prop="address">
        <el-input v-model="formData.address" placeholder="请输入收货人所在省" />
      </el-form-item>
      <!-- <el-form-item label="收货人所在市" prop="city">
        <el-input v-model="formData.city" placeholder="请输入收货人所在市" />
      </el-form-item>
      <el-form-item label="收货人所在区" prop="district">
        <el-input v-model="formData.district" placeholder="请输入收货人所在区" />
      </el-form-item> -->
      <el-form-item label="收货人详细地址" prop="detail">
        <el-input v-model="formData.detail" placeholder="请输入收货人详细地址" />
      </el-form-item>
      <el-form-item label="是否默认" prop="isDefault">
         <el-radio-group v-model="formData.isDefault">
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
import * as UserAddressApi from '@/api/member/userAddress'

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  uid: undefined,
  realName: undefined,
  phone: undefined,
  province: undefined,
  city: undefined,
  cityId: undefined,
  district: undefined,
  detail: undefined,
  postCode: undefined,
  longitude: undefined,
  latitude: undefined,
  isDefault: undefined,
  address: ''
})
const formRules = reactive({
  realName: [{ required: true, message: '收货人姓名不能为空', trigger: 'blur' }],
  phone: [{ required: true, message: '收货人电话不能为空', trigger: 'blur' }],
  address: [{ required: true, message: '收货人地址不能为空', trigger: 'blur' }],
  detail: [{ required: true, message: '收货人详细地址不能为空', trigger: 'blur' }],
  isDefault: [{ required: true, message: '是否默认不能为空', trigger: 'blur' }]
})
const formRef = ref() // 表单 Ref

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = t('action.' + type)
  formType.value = type
  resetForm()
  // 修改时，设置数据
  if (id) {
    formLoading.value = true
    try {
      formData.value = await UserAddressApi.getUserAddress(id)
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
    const data = formData.value as unknown as UserAddressApi.UserAddressVO
    if (formType.value === 'create') {
      await UserAddressApi.createUserAddress(data)
      message.success(t('common.createSuccess'))
    } else {
      await UserAddressApi.updateUserAddress(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    // 发送操作成功的事件
    emit('success')
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    uid: undefined,
    realName: undefined,
    phone: undefined,
    province: undefined,
    city: undefined,
    cityId: undefined,
    district: undefined,
    detail: undefined,
    postCode: undefined,
    longitude: undefined,
    latitude: undefined,
    isDefault: undefined,
    address: ''
  }
  formRef.value?.resetFields()
}
</script>

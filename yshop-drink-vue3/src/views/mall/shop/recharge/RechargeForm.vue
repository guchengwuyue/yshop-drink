<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="标题" prop="name">
        <el-input v-model="formData.name" placeholder="请输入标题" />
      </el-form-item>
      <el-form-item label="价值" prop="value">
        <el-input v-model="formData.value" placeholder="请输入价值" />
      </el-form-item>
      <el-form-item label="销售价" prop="sellPrice">
        <el-input v-model="formData.sellPrice" placeholder="请输入销售价" />
      </el-form-item>
      <el-form-item label="权重" prop="weigh">
        <el-input v-model="formData.weigh" placeholder="请输入权重" />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-radio-group v-model="formData.status">
          <el-radio :label="1">显示</el-radio>
          <el-radio :label="0">隐藏</el-radio>
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
import * as RechargeApi from '@/api/mall/shop/recharge'

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  name: undefined,
  sales: undefined,
  value: undefined,
  weigh: undefined,
  status: undefined,
  sellPrice: undefined
})
const formRules = reactive({
  name: [{ required: true, message: '标题不能为空', trigger: 'blur' }],
  value: [{ required: true, message: '价值不能为空', trigger: 'blur' }],
  sellPrice: [{ required: true, message: '销售价不能为空', trigger: 'blur' }]
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
      formData.value = await RechargeApi.getRecharge(id)
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
    const data = formData.value as unknown as RechargeApi.RechargeVO
    if (formType.value === 'create') {
      await RechargeApi.createRecharge(data)
      message.success(t('common.createSuccess'))
    } else {
      await RechargeApi.updateRecharge(data)
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
    name: undefined,
    sales: undefined,
    value: undefined,
    weigh: undefined,
    status: 1,
    sellPrice: undefined
  }
  formRef.value?.resetFields()
}
</script>

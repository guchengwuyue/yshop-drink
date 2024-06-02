<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
    <el-form-item label="选择类型" prop="orderType">
         <el-radio-group model-value="send">
          <el-radio label="send" >发货</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="发货类型" prop="deliveryType">
         <el-radio-group model-value="normal">
          <el-radio label="normal">手动填写</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="快递公司" prop="expressSn">
          <el-select v-model="formData.expressSn" placeholder="选择快递公司" @change="selectExpress" >
            <el-option label="选择快递公司" value="" />
            <el-option
              v-for="item in express"
              :key="item.code"
              :label="item.name"
              :value="item.code"
            />
          </el-select>
      </el-form-item>
      <el-form-item label="快递单号" prop="expressNumber">
        <el-input v-model="formData.expressNumber" placeholder="请输入快递单号" class="input-width" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import * as OrderApi from '@/api/score/order'
import * as ExpressApi from '@/api/express'

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  userId: undefined,
  productId: undefined,
  number: undefined,
  score: undefined,
  totalScore: undefined,
  ip: undefined,
  expressSn: undefined,
  expressNumber: undefined,
  expressCompany: undefined,
  customerName: undefined,
  customerPhone: undefined,
  customerAddress: undefined,
  status: undefined,
  havePaid: undefined,
  haveDelivered: undefined,
  haveReceived: undefined
})
const express = ref([])
const formRules = reactive({
  expressSn: [{ required: true, message: '请选择快递公司', trigger: 'blur' }],
  expressNumber: [{ required: true, message: '快递单号不能为空', trigger: 'blur' }]
})
const formRef = ref() // 表单 Ref

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = t('action.' + type)
  formType.value = type
  resetForm()
  express.value = await ExpressApi.getExpressList()
  // 修改时，设置数据
  if (id) {
    formLoading.value = true
    try {
      formData.value = await OrderApi.getOrder(id)
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
    const data = formData.value as unknown as OrderApi.OrderVO
    await OrderApi.updateOrder(data)
    message.success(t('common.updateSuccess'))
    dialogVisible.value = false
    // 发送操作成功的事件
    emit('success')
  } finally {
    formLoading.value = false
  }
}

const selectExpress = (val) => {
  let obj = {};
  obj = express.value.find((item)=>{ // 这里的userList就是上面遍历的数据源
      return item.code === val; // 筛选出匹配数据
  })
  formData.value.expressCompany = obj.name
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    userId: undefined,
    productId: undefined,
    number: undefined,
    score: undefined,
    totalScore: undefined,
    ip: undefined,
    expressNumber: undefined,
    expressCompany: undefined,
    customerName: undefined,
    customerPhone: undefined,
    customerAddress: undefined,
    status: undefined,
    havePaid: undefined,
    haveDelivered: undefined,
    haveReceived: undefined
  }
  formRef.value?.resetFields()
}
</script>

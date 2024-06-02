<template>
   <div>
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
    >
      <el-form-item label="appId" prop="eBusinessID">
        <el-input v-model="formData.ebusinessID" placeholder="请输入快递鸟appId" />
      </el-form-item>
      <el-form-item label="appKey" prop="apiKey">
        <el-input v-model="formData.apiKey" type="password" placeholder="请输入快递鸟appKey" />
      </el-form-item>
      <el-form-item label="是否收费套餐" prop="isFree">
        <el-radio-group v-model="formData.isFree">
          <el-radio :label="false">是</el-radio>
          <el-radio :label="true">否</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label=" " prop="">
        <el-button @click="submitForm" type="primary">确 定</el-button>
        <el-button @click="dialogVisible = false">取 消</el-button>
      </el-form-item>
      <el-form-item label=" " prop="">
        <span><span style="color: red;">yshop-pro系统使用的第三方快递鸟api实现了快递查询，注册地址请点击，</span>
          <a href="http://www.kdniao.com/reg?from=cbb-yx " style="color:blue">我注册！</a></span>
      </el-form-item>
    </el-form>
    </div>
</template>
<script setup lang="ts">
import * as ExpressApi from '@/api/express'

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(true) // 弹窗的是否展示
// const dialogTitle = ref('快递鸟配置') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
// const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  ebusinessID: "",
  apiKey: "",
  isFree: true

})
const formRules = reactive({
  appId: [{ required: true, message: '快递鸟appId不能为空', trigger: 'blur' }],
  appKey: [{ required: true, message: '快递鸟appKey不能为空', trigger: 'blur' }]
})
const formRef = ref() // 表单 Ref


/** 提交表单 */
//const emit = defineEmits(['success']) // 定义 success 事件，用于操作成功后的回调
const submitForm = async () => {
  // 校验表单
  if (!formRef) return
  const valid = await formRef.value.validate()
  if (!valid) return
  // 提交请求
  formLoading.value = true
  try {
    const data = formData.value
    await ExpressApi.postExpressSet(data)
    message.success(t('common.createSuccess'))
    // 发送操作成功的事件
   // emit('success')
  } finally {
    formLoading.value = false
  }
}

/** 初始化 **/
onMounted(() => {
   getList()
})

const getList = async () => {
  const data = await ExpressApi.getExpressSet()
  if (data) {
     formData.value = data
  }
 
}
// /** 重置表单 */
// const resetForm = () => {
//   formData.value = {
//   appId: undefined,
//   appKey: undefined
//   }
//   formRef.value?.resetFields()
// }
</script>

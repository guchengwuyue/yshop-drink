<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="类型" prop="type">
        <el-select v-model="formData.type" placeholder="请选择类型">
          <el-option label="选择类型" value="" />
          <el-option label="页面" value="pages" />
          <el-option label="跳转小程" value="miniprogram" />
          <el-option label="内容" value="content" />
          <el-option label="电话" value="call" />
        </el-select>
      </el-form-item>
      <el-form-item label="标题" prop="name">
        <el-input v-model="formData.name" placeholder="请输入标题" />
      </el-form-item>
      <el-form-item label="图标" prop="image">
          <Materials v-model="formData.image" num="1" type="image" />
      </el-form-item>
      <el-form-item label="小程序app_id" prop="appId">
        <el-input v-model="formData.appId" placeholder="请输入小程序app_id" />
      </el-form-item>
      <el-form-item label="页面路径" prop="pages">
        <el-input v-model="formData.pages" placeholder="请输入页面路径" />
      </el-form-item>
      <el-form-item label="内容">
        <Editor v-model="formData.content" height="150px" />
      </el-form-item>
      <el-form-item label="电话" prop="phone">
        <el-input v-model="formData.phone" placeholder="请输入电话" />
      </el-form-item>
      <el-form-item label="权重" prop="weigh">
        <el-input v-model="formData.weigh" placeholder="请输入权重" />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-radio-group v-model="formData.status">
          <el-radio :label="1">上架</el-radio>
          <el-radio :label="0">下架</el-radio>
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
import * as ServiceApi from '@/api/mall/shop/service'

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  name: undefined,
  image: undefined,
  type: undefined,
  content: '',
  pid: undefined,
  appId: undefined,
  pages: undefined,
  phone: undefined,
  weigh: undefined,
  status: 1
})
const formRules = reactive({
  type: [{ required: true, message: '类型不能为空', trigger: 'blur' }],
  name: [{ required: true, message: '标题不能为空', trigger: 'blur' }],
  image: [{ required: true, message: '图标不能为空', trigger: 'blur' }]
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
      formData.value = await ServiceApi.getService(id)
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
    const data = formData.value as unknown as ServiceApi.ServiceVO
    if (formType.value === 'create') {
      await ServiceApi.createService(data)
      message.success(t('common.createSuccess'))
    } else {
      await ServiceApi.updateService(data)
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
    image: undefined,
    type: undefined,
    content: '',
    pid: undefined,
    appId: undefined,
    pages: undefined,
    phone: undefined,
    weigh: undefined,
    status: 1
  }
  formRef.value?.resetFields()
}
</script>

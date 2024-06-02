<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="修改余额">
          <el-radio-group v-model="formData.ptype">
            <el-radio :label="1">增加</el-radio>
            <el-radio :label="2">减少</el-radio>
          </el-radio-group>
      </el-form-item>
      <el-form-item label="余额">
        <el-input v-model="formData.money" style="width: 370px;" placeholder="请输入余额" />
      </el-form-item>
      <el-form-item label="修改积分">
        <el-radio-group v-model="formData.itype">
            <el-radio :label="1">增加</el-radio>
            <el-radio :label="2">减少</el-radio>
         </el-radio-group>
      </el-form-item>
      <el-form-item label="积分">
        <el-input v-model="formData.integral" style="width: 370px;"  placeholder="请输入积分" />
      </el-form-item>
    </el-form>
      
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import * as UserApi from '@/api/member/user'

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  ptype: 1,
  money: undefined,
  itype: 1,
  integral: undefined
})
const formRules = reactive({
  money: [{ required: true, message: '金额不能为空', trigger: 'blur' }],
  integral: [{ required: true, message: '积分不能为空', trigger: 'blur' }]
})
const formRef = ref() // 表单 Ref

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = t('action.' + type)
   formType.value = type
  resetForm()
  // formLoading.value = true
  formData.value.id = id
  console.log(type,id)
  
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
    const data = formData.value as unknown as UserApi.UserVO
    await UserApi.updateMony(data)
    message.success(t('common.updateSuccess'))
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
    ptype: 1,
    money: undefined,
    itype: 1,
    integral: undefined
  }
  formRef.value?.resetFields()
}
</script>

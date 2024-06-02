<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="用户账户" prop="username">
        <el-input v-model="formData.username" placeholder="请输入用户账户" />
      </el-form-item>
      <el-form-item label="用户昵称" prop="nickname">
        <el-input v-model="formData.nickname" placeholder="请输入用户昵称" />
      </el-form-item>
      <el-form-item label="手机号码" prop="mobile">
        <el-input v-model="formData.mobile" placeholder="请输入手机号码" />
      </el-form-item>
      <el-form-item label="真实姓名" prop="realName">
        <el-input v-model="formData.realName" placeholder="请输入真实姓名" />
      </el-form-item>
      <el-form-item label="生日" prop="birthday">
        <el-input v-model="formData.birthday" placeholder="请输入生日" />
      </el-form-item>
      <el-form-item label="用户备注" prop="mark">
        <el-input v-model="formData.mark" placeholder="请输入用户备注" />
      </el-form-item>
      <el-form-item label="用户余额" prop="nowMoney">
        <el-input v-model="formData.nowMoney" placeholder="请输入用户余额" />
      </el-form-item>
      <el-form-item label="佣金金额" prop="brokeragePrice">
        <el-input v-model="formData.brokeragePrice" placeholder="请输入佣金金额" />
      </el-form-item>
      <el-form-item label="用户积分" prop="integral">
        <el-input v-model="formData.integral" placeholder="请输入用户剩余积分" />
      </el-form-item>
      <!-- <el-form-item label="等级" prop="level">
        <el-input v-model="formData.level" placeholder="请输入等级" />
      </el-form-item> -->
      <!-- <el-form-item label="是否为推广员" prop="isPromoter">
        <el-input v-model="formData.isPromoter" placeholder="请输入是否为推广员" />
      </el-form-item> -->
      <el-form-item label="详细地址" prop="addres">
        <el-input v-model="formData.addres" placeholder="请输入详细地址" />
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
  username: undefined,
  password: undefined,
  realName: undefined,
  birthday: undefined,
  cardId: undefined,
  mark: undefined,
  partnerId: undefined,
  groupId: undefined,
  nickname: undefined,
  avatar: undefined,
  mobile: undefined,
  addIp: undefined,
  lastIp: undefined,
  nowMoney: undefined,
  brokeragePrice: undefined,
  integral: undefined,
  signNum: undefined,
  status: undefined,
  level: undefined,
  spreadUid: undefined,
  spreadTime: undefined,
  userType: undefined,
  isPromoter: undefined,
  payCount: undefined,
  spreadCount: undefined,
  addres: undefined,
  adminid: undefined,
  loginType: undefined,
  wxProfile: undefined
})
const formRules = reactive({
  username: [{ required: true, message: '用户账户不能为空', trigger: 'blur' }],
  nickname: [{ required: true, message: '用户昵称不能为空', trigger: 'blur' }],
  mobile: [{ required: true, message: '佣手机号码不能为空', trigger: 'blur' }]
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
      formData.value = await UserApi.getUser(id)
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
    const data = formData.value as unknown as UserApi.UserVO
    if (formType.value === 'create') {
      await UserApi.createUser(data)
      message.success(t('common.createSuccess'))
    } else {
      await UserApi.updateUser(data)
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
    username: undefined,
    password: undefined,
    realName: undefined,
    birthday: undefined,
    cardId: undefined,
    mark: undefined,
    partnerId: undefined,
    groupId: undefined,
    nickname: undefined,
    avatar: undefined,
    mobile: undefined,
    addIp: undefined,
    lastIp: undefined,
    nowMoney: undefined,
    brokeragePrice: undefined,
    integral: undefined,
    signNum: undefined,
    status: undefined,
    level: undefined,
    spreadUid: undefined,
    spreadTime: undefined,
    userType: undefined,
    isPromoter: undefined,
    payCount: undefined,
    spreadCount: undefined,
    addres: undefined,
    adminid: undefined,
    loginType: undefined,
    wxProfile: undefined
  }
  formRef.value?.resetFields()
}
</script>

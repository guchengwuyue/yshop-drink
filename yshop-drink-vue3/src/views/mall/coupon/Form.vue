<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
    >
      <el-form-item label="展示店铺" prop="shopId">
        <el-select
          v-model="formData.shopId"
          placeholder="选择店铺"
        >
          <el-option
            v-for="item in shopList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="可用类型" prop="type">
        <el-radio-group v-model="formData.type">
          <el-radio :label="0">通用</el-radio>
          <el-radio :label="1">自取</el-radio>
          <el-radio :label="2">外卖</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="兑换码" prop="exchangeCode">
        <el-input v-model="formData.exchangeCode" placeholder="请输入兑换码" />
      </el-form-item>
      <el-form-item label="图片" prop="image">
          <Materials v-model="formData.image" num="1" type="image" />
      </el-form-item>
      <el-form-item label="优惠券名称" prop="title">
        <el-input v-model="formData.title" placeholder="请输入优惠券名称" />
      </el-form-item>
      <el-form-item label="消费多少可用" prop="least">
        <el-input v-model="formData.least" placeholder="请输入消费多少可用" />
      </el-form-item>
      <el-form-item label="优惠券金额" prop="value">
        <el-input v-model="formData.value" placeholder="请输入优惠券金额" />
      </el-form-item>
      <el-form-item label="开始时间" prop="startTime">
        <el-date-picker
          v-model="formData.startTime"
          type="date"
          value-format="x"
          placeholder="选择开始时间"
        />
      </el-form-item>
      <el-form-item label="结束时间" prop="endTime">
        <el-date-picker
          v-model="formData.endTime"
          type="date"
          value-format="x"
          placeholder="选择结束时间"
        />
      </el-form-item>
      <el-form-item label="发行数量" prop="distribute">
        <el-input v-model="formData.distribute" placeholder="请输入发行数量" />
      </el-form-item>
      <el-form-item label="所需积分" prop="score">
        <el-input v-model="formData.score" placeholder="请输入所需积分" />
      </el-form-item>
      <el-form-item label="限领数量" prop="limit">
        <el-input v-model="formData.limit" placeholder="请输入限领数量" />
      </el-form-item>
      <el-form-item label="使用说明" prop="instructions">
        <el-input type="textarea" rows="5"  v-model="formData.instructions" placeholder="请输入使用说明" />
      </el-form-item>
      <el-form-item label="是否上架" prop="isSwitch">
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
import * as Api from '@/api/mall/coupon/'
import * as ShopApi from '@/api/mall/store/shop'

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  shopId: undefined,
  shopName: undefined,
  title: undefined,
  isSwitch: undefined,
  least: undefined,
  value: undefined,
  startTime: undefined,
  endTime: undefined,
  weigh: undefined,
  type: undefined,
  exchangeCode: undefined,
  receive: undefined,
  distribute: undefined,
  score: undefined,
  instructions: undefined,
  image: undefined,
  limit: undefined
})
const shopList = ref([])
const formRules = reactive({
  shopId: [{ required: true, message: '店铺id,0表示通用不能为空', trigger: 'blur' }],
  title: [{ required: true, message: '优惠券名称不能为空', trigger: 'blur' }],
  least: [{ required: true, message: '消费多少可用不能为空', trigger: 'blur' }],
  value: [{ required: true, message: '优惠券金额不能为空', trigger: 'blur' }],
  startTime: [{ required: true, message: '开始时间不能为空', trigger: 'blur' }],
  endTime: [{ required: true, message: '结束时间不能为空', trigger: 'blur' }],
  type: [{ required: true, message: '可用类型不能为空', trigger: 'blur' }],
  distribute: [{ required: true, message: '发行数量不能为空', trigger: 'blur' }],
  limit: [{ required: true, message: '限领数量不能为空', trigger: 'blur' }],
  instructions: [{ required: true, message: '使用说明不能为空', trigger: 'blur' }]
})
const formRef = ref() // 表单 Ref

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
      formData.value = await Api.getCoupon(id)
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
    const data = formData.value as unknown as Api.VO
    if (formType.value === 'create') {
      await Api.createCoupon(data)
      message.success(t('common.createSuccess'))
    } else {
      await Api.updateCoupon(data)
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
    shopId: undefined,
    shopName: undefined,
    title: undefined,
    isSwitch: 1,
    least: undefined,
    value: undefined,
    startTime: undefined,
    endTime: undefined,
    weigh: undefined,
    type: 0,
    exchangeCode: undefined,
    receive: undefined,
    distribute: undefined,
    score: undefined,
    instructions: undefined,
    image: undefined,
    limit: undefined
  }
  formRef.value?.resetFields()
}
</script>

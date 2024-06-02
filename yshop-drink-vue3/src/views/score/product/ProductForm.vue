<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="60%">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="产品标题" prop="title">
        <el-input v-model="formData.title" placeholder="请输入产品标题" />
      </el-form-item>
      <el-form-item label="主图" prop="image">
          <Materials v-model="formData.image" num="1" type="image" />
      </el-form-item>
      <el-form-item label="组图" prop="images">
        <Materials v-model="formData.images" num="5" type="image" />
      </el-form-item>
      <el-form-item label="详情" prop="desc">
        <vue-ueditor-wrap v-model="formData.desc" :config="myConfig" @before-init="addCustomDialog"    style="width: 90%;" />
      </el-form-item>
      <el-form-item label="消耗积分" prop="score">
        <el-input v-model="formData.score" placeholder="请输入消耗积分" />
      </el-form-item>
      <el-form-item label="库存" prop="stock">
        <el-input v-model="formData.stock" placeholder="请输入库存" />
      </el-form-item>
      <el-form-item label="权重" prop="weigh">
        <el-input v-model="formData.weigh" placeholder="请输入权重" />
      </el-form-item>
      <el-form-item label="是否上架" prop="isSwitch">
        <el-radio-group v-model="formData.isSwitch">
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
import * as ProductApi from '@/api/score/product'

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  title: undefined,
  image: undefined,
  images: undefined,
  desc: undefined,
  score: undefined,
  weigh: undefined,
  stock: undefined,
  sales: undefined,
  switch: undefined
})
const formRules = reactive({
  title: [{ required: true, message: '产品标题不能为空', trigger: 'blur' }],
  image: [{ required: true, message: '主图不能为空', trigger: 'blur' }],
  images: [{ required: true, message: '组图不能为空', trigger: 'blur' }],
  desc: [{ required: true, message: '详情不能为空', trigger: 'blur' }],
  score: [{ required: true, message: '消耗积分不能为空', trigger: 'blur' }],
  stock: [{ required: true, message: '库存不能为空', trigger: 'blur' }]
})
const myConfig = reactive( {
      autoHeightEnabled: false, // 编辑器不自动被内容撑高
      initialFrameHeight: 500, // 初始容器高度
      initialFrameWidth: '100%', // 初始容器宽度
      UEDITOR_HOME_URL: '/UEditor/',
      serverUrl: '',
      zIndex: 9999,
      toolbars: [
        [
          'undo',
          'redo',
          '|',
          'bold',
          'italic',
          'underline',
          'fontborder',
          'strikethrough',
          'superscript',
          'subscript',
          'removeformat',
          'formatmatch',
          'autotypeset',
          'blockquote',
          'pasteplain',
          '|',
          'forecolor',
          'backcolor',
          'insertorderedlist',
          'insertunorderedlist',
          'selectall',
          'cleardoc',
          '|',
          'rowspacingtop',
          'rowspacingbottom',
          'lineheight',
          '|',
          'customstyle',
          'paragraph',
          'fontfamily',
          'fontsize',
          '|',
          'directionalityltr',
          'directionalityrtl',
          'indent',
          '|',
          'justifyleft',
          'justifycenter',
          'justifyright',
          'justifyjustify',
          '|',
          'touppercase',
          'tolowercase',
          '|',
          'imagenone',
          'imageleft',
          'imageright',
          'imagecenter',
        ],
      ],
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
      formData.value = await ProductApi.getProduct(id)
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
    const data = formData.value as unknown as ProductApi.ProductVO
    if (formType.value === 'create') {
      await ProductApi.createProduct(data)
      message.success(t('common.createSuccess'))
    } else {
      await ProductApi.updateProduct(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    // 发送操作成功的事件
    emit('success')
  } finally {
    formLoading.value = false
  }
}

const addCustomDialog  = () => {
      window.UE.registerUI('yshop', function (editor, uiName) {
        let dialog = new window.UE.ui.Dialog({
          iframeUrl: '/yshop/materia/index',
          editor: editor,
          name: uiName,
          title: '上传图片',
          cssRules: 'width:1200px;height:650px;padding:20px;'
        });
        this.dialog = dialog;

        var btn = new window.UE.ui.Button({
          name: 'dialog-button',
          title: '上传图片',
          cssRules: `background-image: url(../../../assets/imgs/icons.png);background-position: -726px -77px;`,
          onclick: function () {
            dialog.render();
            dialog.open();
          }
        });

        return btn;
      }, 37);
}
/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    title: undefined,
    image: undefined,
    images: undefined,
    desc: undefined,
    score: undefined,
    weigh: undefined,
    stock: undefined,
    sales: undefined,
    switch: undefined
  }
  formRef.value?.resetFields()
}
</script>

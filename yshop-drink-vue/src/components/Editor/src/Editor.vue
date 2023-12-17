<script lang="ts" name="Editor" setup>
import { PropType } from 'vue'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import { i18nChangeLanguage, IDomEditor, IEditorConfig } from '@wangeditor/editor'
import { propTypes } from '@/utils/propTypes'
import { isNumber } from '@/utils/is'
import { ElMessage } from 'element-plus'
import { useLocaleStore } from '@/store/modules/locale'
import { getAccessToken, getTenantId } from '@/utils/auth'


type InsertFnType = (url: string, alt: string, href: string) => void

const localeStore = useLocaleStore()

const currentLocale = computed(() => localeStore.getCurrentLocale)

i18nChangeLanguage(unref(currentLocale).lang)

const props = defineProps({
  editorId: propTypes.string.def('wangeEditor-1'),
  height: propTypes.oneOfType([Number, String]).def('500px'),
  editorConfig: {
    type: Object as PropType<Partial<IEditorConfig>>,
    default: () => undefined
  },
  readonly: propTypes.bool.def(false),
  modelValue: propTypes.string.def('')
})

const emit = defineEmits(['change', 'update:modelValue'])

// 编辑器实例，必须用 shallowRef
const editorRef = shallowRef<IDomEditor>()

const valueHtml = ref('')

// class MyModalMenu implements IModalMenu {    // TS 语法
// // class MyModalMenu {                       // JS 语法

//     constructor() {
//         this.title = 'My menu'
//         // this.iconSvg = '<svg >...</svg>'
//         this.tag = 'button'
//         this.showModal = true
//         this.modalWidth = 1000
//     }

//     // 菜单是否需要激活（如选中加粗文本，“加粗”菜单会激活），用不到则返回 false
//     isActive(editor: IDomEditor): boolean {    // TS 语法
//     // isActive(editor) {
//                          // JS 语法
//        console.log(editor)
//         return false
//     }

//     // 获取菜单执行时的 value ，用不到则返回空 字符串或 false
//     getValue(editor: IDomEditor): string | boolean {    // TS 语法
//     // getValue(editor) {                               // JS 语法
//       console.log(editor)
//       return ''
//     }

//     // 菜单是否需要禁用（如选中 H1 ，“引用”菜单被禁用），用不到则返回 false
//     isDisabled(editor: IDomEditor): boolean {   // TS 语法
//     // isDisabled(editor) {
//       console.log(editor)                   // JS 语法
//         return false
//     }

//     // 点击菜单时触发的函数
//     exec(editor: IDomEditor, value: string | boolean) {   // TS 语法
//     // exec(editor, value) {                              // JS 语法
//         // Modal menu ，这个函数不用写，空着即可
//         console.log(editor,value)
//     }

//     // 弹出框 modal 的定位：1. 返回某一个 SlateNode； 2. 返回 null （根据当前选区自动定位）
//     getModalPositionNode(editor: IDomEditor): SlateNode | null {  // TS 语法
//     // getModalPositionNode(editor) {                             // JS 语法
//       return null // modal 依据选区定位
//         console.log(editor)
//     }

//     // 定义 modal 内部的 DOM Element
//     getModalContentElem(editor: IDomEditor): DOMElement {   // TS 语法
//     // getModalContentElem(editor) {                        // JS 语法

//       //   const content = $('<div></div>')
//       //   const button = $('<button>do something</button>')
//       //   content.append(button)

//       //   button.on('click', () => {
//       //       editor.insertText(' hello ')
//       //   })
//      // const content = document.createElement('<iframe src="/yshop/materia/index"></iframe>');
//     // const valueHtml = ref("<p>")
//         const content    = '<iframe width="100%" src="/yshop/materia/index"></iframe>'
//     console.log(editor)
//       // return content[0] // 返回 DOM Element 类型
//       //return '/yshop/materia/index'
//      // window.open('/yshop/materia/index')
//     // var html = ' 
//       return content
//      // const valueHtml = ref('<frame src="/yshop/materia/index"></frame>')
//      // return valueHtml.value

//         // PS：也可以把 $content 缓存下来，这样不用每次重复创建、重复绑定事件，优化性能
//     }
// }

// const menu1Conf = {
//   key: 'menu12', // 定义 menu key ：要保证唯一、不重复（重要）
//   factory() {
//     return new MyModalMenu() // 把 `YourMenuClass` 替换为你菜单的 class
//   },
// }

// Boot.registerMenu(menu1Conf)

// // 工具栏配置
// const toolbarConfig = {
//     // toolbarKeys: ['headerSelect', 'bold', 'my-menu-1'],
//     // excludeKeys: [],
//     insertKeys: {
//         index: 0,
//         keys: 'menu12'
//     }
// }


watch(
  () => props.modelValue,
  (val: string) => {
    if (val === unref(valueHtml)) return
    valueHtml.value = val
  },
  {
    immediate: true
  }
)

// 监听
watch(
  () => valueHtml.value,
  (val: string) => {
    emit('update:modelValue', val)
  }
)

const handleCreated = (editor: IDomEditor) => {
  editorRef.value = editor
}

// 编辑器配置
const editorConfig = computed((): IEditorConfig => {
  return Object.assign(
    {
      placeholder: '请输入内容...',
      readOnly: props.readonly,
      customAlert: (s: string, t: string) => {
        switch (t) {
          case 'success':
            ElMessage.success(s)
            break
          case 'info':
            ElMessage.info(s)
            break
          case 'warning':
            ElMessage.warning(s)
            break
          case 'error':
            ElMessage.error(s)
            break
          default:
            ElMessage.info(s)
            break
        }
      },
      autoFocus: false,
      scroll: true,
      MENU_CONF: {
        ['uploadImage']: {
          server: import.meta.env.VITE_UPLOAD_URL,
          // 单个文件的最大体积限制，默认为 2M
          maxFileSize: 5 * 1024 * 1024,
          // 最多可上传几个文件，默认为 100
          maxNumberOfFiles: 10,
          // 选择文件时的类型限制，默认为 ['image/*'] 。如不想限制，则设置为 []
          allowedFileTypes: ['image/*'],

          // 自定义上传参数，例如传递验证的 token 等。参数会被添加到 formData 中，一起上传到服务端。
          meta: { updateSupport: 0 },
          // 将 meta 拼接到 url 参数中，默认 false
          metaWithUrl: true,

          // 自定义增加 http  header
          headers: {
            Accept: '*',
            Authorization: 'Bearer ' + getAccessToken(),
            'tenant-id': getTenantId()
          },

          // 跨域是否传递 cookie ，默认为 false
          withCredentials: true,

          // 超时时间，默认为 10 秒
          timeout: 5 * 1000, // 5 秒

          // form-data fieldName，后端接口参数名称，默认值wangeditor-uploaded-image
          fieldName: 'file',

          // 上传之前触发
          onBeforeUpload(file: File) {
            console.log(file)
            return file
          },
          // 上传进度的回调函数
          onProgress(progress: number) {
            // progress 是 0-100 的数字
            console.log('progress', progress)
          },
          onSuccess(file: File, res: any) {
            console.log('onSuccess', file, res)
          },
          onFailed(file: File, res: any) {
            alert(res.message)
            console.log('onFailed', file, res)
          },
          onError(file: File, err: any, res: any) {
            alert(err.message)
            console.error('onError', file, err, res)
          },
          // 自定义插入图片
          customInsert(res: any, insertFn: InsertFnType) {
            insertFn(res.data, 'image', res.data)
          }
        }
      },
      uploadImgShowBase64: true
    },
  
    props.editorConfig || {}
  )
})

const editorStyle = computed(() => {
  return {
    height: isNumber(props.height) ? `${props.height}px` : props.height
  }
})

// 回调函数
const handleChange = (editor: IDomEditor) => {
  emit('change', editor)
}

// 组件销毁时，及时销毁编辑器
onBeforeUnmount(() => {
  const editor = unref(editorRef.value)
  if (editor === null) return

  // 销毁，并移除 editor
  editor?.destroy()
})

const getEditorRef = async (): Promise<IDomEditor> => {
  await nextTick()
  return unref(editorRef.value) as IDomEditor
}

defineExpose({
  getEditorRef
})
</script>

<template>
  <div class="border-1 border-solid border-[var(--tags-view-border-color)] z-99">
    <!-- 工具栏 -->
    <Toolbar
      :editor="editorRef"
      :editorId="editorId"
      class="border-bottom-1 border-solid border-[var(--tags-view-border-color)]"
    />
    <!-- 编辑器 -->
    <Editor
      v-model="valueHtml"
      :defaultConfig="editorConfig"
      :editorId="editorId"
      :style="editorStyle"
      @on-change="handleChange"
      @on-created="handleCreated"
    />
  </div>
</template>

<style src="@wangeditor/editor/dist/css/style.css"></style>

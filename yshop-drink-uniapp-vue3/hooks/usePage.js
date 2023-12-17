// mouse.js
import { ref, onMounted, onUnmounted } from 'vue'
import { onReady, onReachBottom } from '@dcloudio/uni-app'

export const usePage = getPage => {
  // 页码,默认为1
  const page = ref(1)

  // 页大小,默认为10
  const limit = ref(10)

  // 关键字
  const keyword = ref('')

  // 类别
  const type = ref('')

  // 分类ID
  const sid = ref('')

  // 是否新品,不为空的字符串即可
  const news = ref('')

  // 是否积分兑换商品
  const isIntegral = ref('')

  // 到底了
  const loadend = ref(false)

  // 加载中
  const loading = ref(false)

  const dataList = ref([])

  const handleGetDataList = async () => {
    console.log('--> % handleGetDataList % loading:\n', loading.value)
    console.log('--> % handleGetDataList % loadend:\n', loadend.value)
    if (loading.value || loadend.value) return

    loading.value = true
    const products = await getPage({
      page: page.value,
      limit: limit.value,
      keyword: keyword.value,
      type: type.value,
      sid: sid.value,
      news: news.value,
      isIntegral: isIntegral.value,
    })
    console.log('--> % handleGetDataList % products:\n', products)
    if (products) {
      if (products.length <= 0) {
        loadend.value = true
      }
      dataList.value = dataList.value.concat(products)
    }
    loading.value = false
  }

  const handleRefresh = () => {
    loadend.value = false
    loading.value = false
    dataList.value = []
    handleGetDataList()
  }

  onReady(() => {
    console.log('onReady')
    // handleGetDataList()
  })

  onReachBottom(() => {
    if (loading.value) return
    page.value += 1
  })

  // 通过返回值暴露所管理的状态
  return {
    type,
    dataList,
    page,
    limit,
    keyword,
    loading,
    loadend,
    refresh: handleRefresh,
  }
}

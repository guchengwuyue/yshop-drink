<template>
  <ContentWrap>
    <IFrame v-if="!loading" :src="url" />
  </ContentWrap>
</template>
<script setup lang="ts" name="InfraDruid">
import * as ConfigApi from '@/api/infra/config'

const loading = ref(true) // 是否加载中
const url = ref(import.meta.env.VITE_BASE_URL + '/druid/index.html')

/** 初始化 */
onMounted(async () => {
  try {
    const data = await ConfigApi.getConfigKey('url.druid')
    if (data && data.length > 0) {
      url.value = data
    }
  } finally {
    loading.value = false
  }
})
</script>

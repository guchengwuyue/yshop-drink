// mouse.js
import { ref, onMounted, onUnmounted, getCurrentInstance } from 'vue'
import { onReady, onReachBottom } from '@dcloudio/uni-app'

export const useGlobalProperties = () => {
  const instance = getCurrentInstance()
  return instance.appContext.app.config.globalProperties
}

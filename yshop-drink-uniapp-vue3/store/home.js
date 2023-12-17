import { defineStore } from 'pinia'

import cookie from '@/utils/cookie'
import { getUserInfo } from '@/api/user'

export const useMainStore = defineStore('home', {
  state: () => ({
    banner: [],
  }),
  getters: {
    // setUserInfo(state) {
    //   // 自动完成! ✨
    //   return state.user = state
    // },
  },
  actions: {
    setAccessToken(user) {
      cookie.set('accessToken', user)
      return getUserInfo()
      
    },
    getUserInfo(user) {
      getUserInfo()
    },
    getAddressList() {
      getAddressList
    }
  },
})

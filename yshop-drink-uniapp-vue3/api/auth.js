import api from './api'

/**
 * 使用手机 + 验证码登录
 */
export function userLogin(data) {
  return api.post('/member/auth/sms-login', data, { login: false })
}

/**
 * 使用手机 + 验证码登录  member/auth/send-sms-code
 */
export function smsSend(data) {
  return api.post('/member/auth/send-sms-code', data, { login: false })
}

/**
 * 小程序 member/auth/auth-miniapp-login         
 */
export function userLoginForWechatMini(data) {
  return api.post('/member/auth/auth-miniapp-login', data, { login: false })
}

/**
 * userAuthSession   
 */
export function userAuthSession(data) {
  return api.post('/member/auth/auth-session', data, { login: false })
}

/**
 * wechatAuth   
 */
export function wechatAuth(data) {
  return api.get('/member/auth/auth-wechat-login', data, { login: false })
}


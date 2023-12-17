import api from './api'

/**
 * 获得banner列表 
 */
export function shopNearby(data) {
  return api.get('/store/nearby', data, { login: false })
}
/**
 * 获取首页信息
 */
export function menuGoods(data) {
  return api.get('/product/products', data, { login: false })
}


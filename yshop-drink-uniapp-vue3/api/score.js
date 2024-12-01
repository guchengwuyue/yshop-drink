import api from './api'

/**
 * 详情 
 */
export function scoreShopDetail(data) {
  return api.get('/score-product/detail', data, { login: false })
}
/**
 * 列表 
 */
export function scoreShopIndex(data) {
  return api.get('/score-product/list', data, { login: false })
}

/**
 * 提交
 */
export function scoreShopExchange(data) {
  return api.post('/score-order/submit', data, { login: false })
}

/**
 * 订单列表 
 */
export function scoreShopOrder(data) {
  return api.get('/score-order/list', data, { login: false })
}

/**
 * 订单列表 
 */
export function scoreShopOrderDetail(data) {
  return api.get('/score-order/detail', data, { login: false })
}

/**
 * 确认收货
 */
export function scoreShopReceive(data) {
  return api.get('/score-order/take', data, { login: false })
}


/**
 * 查询物流 
 */
export function getLogistic(data) {
  return api.get('/express/getLogistic', data, { login: false })
}



export const VUE_APP_API_URL = 'http://localhost:48081/app-api'
//export const VUE_APP_API_URL = 'https://apidc.yixiang.co/app-api'
export const VUE_APP_RESOURCES_URL = 'https://h5.yixiang.co/static'
export const VUE_APP_UPLOAD_URL = VUE_APP_API_URL + '/infra/file/upload'
export const APP_ID = 'wxdbdbc123c8c30b45'

const orderListStatus = {}

// -1:申请退款
// -2:退货成功
// 0:待发货；
// 1:待收货；
// 2:已收货；
// 3:待评价；
// -1:已退款

export const orderStatus = {
  0: '未支付',
  1: '待发货',
  2: '待收货',
  3: '待评价',
  4: '已完成',
  5: '退款中',
  6: '已退款',
  7: '退款',
}

export const orderReStatus = {
  0: '等待买家付款',
  // 1: '等待卖家发货',
  1: '卖家已发货',
  2: '等待买家待评价',
  3: '订单已完成',
  4: '订单退款中',
  5: '订单已退款',
  6: '退款已完成',
}

// export const orderReStatus = {
//   0: '等待买家付款',
//   1: '等待卖家发货',
//   2: '卖家已发货',
//   3: '等待买家待评价',
//   4: '订单已完成',
//   5: '订单退款中',
//   6: '订单已退款',
//   7: '退款已完成',
// }

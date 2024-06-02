import request from '@/config/axios'

export interface StoreOrderVO {
  id: number
  orderId: string
  extendOrderId: string
  uid: number
  realName: string
  userPhone: string
  userAddress: string
  cartId: string
  freightPrice: number
  totalNum: number
  totalPrice: number
  totalPostage: number
  payPrice: number
  payPostage: number
  deductionPrice: number
  couponId: number
  couponPrice: number
  paid: byte
  payTime: Date
  payType: string
  status: boolean
  refundStatus: byte
  refundReasonWapImg: string
  refundReasonWapExplain: string
  refundReasonTime: Date
  refundReasonWap: string
  refundReason: string
  refundPrice: number
  deliverySn: string
  deliveryName: string
  deliveryType: string
  deliveryId: string
  gainIntegral: number
  useIntegral: number
  payIntegral: number
  backIntegral: number
  mark: string
  unique: string
  remark: string
  merId: number
  combinationId: number
  pinkId: number
  cost: number
  seckillId: number
  bargainId: number
  verifyCode: string
  storeId: number
  shippingType: boolean
  isChannel: byte
  isSystemDel: boolean
}

// 查询订单列表
export const getStoreOrderPage = async (params: StoreOrderPageReqVO) => {
  return await request.get({ url: `/order/store-order/page`, params })
}

// 查询订单详情
export const getStoreOrder = async (id: number) => {
  return await request.get({ url: `/order/store-order/get?id=` + id })
}

// 新增订单
export const createStoreOrder = async (data: StoreOrderVO) => {
  return await request.post({ url: `/order/store-order/create`, data })
}

// 修改订单
export const updateStoreOrder = async (data: StoreOrderVO) => {
  return await request.put({ url: `/order/store-order/update`, data })
}

// 删除订单
export const deleteStoreOrder = async (id: number) => {
  return await request.delete({ url: `/order/store-order/delete?id=` + id })
}

export const payStoreOrder = async (id: number) => {
  return await request.get({ url: `/order/store-order/pay?id=` + id })
}

export const takeStoreOrder = async (id: number) => {
  return await request.get({ url: `/order/store-order/take?id=` + id })
}

export const rufundStoreOrder = async (data) => {
  return await request.post({ url: `/order/store-order/refund`,data })
}


export const getStoreOrderRecordList = async (id: number) => {
  return await request.get({ url: `/order/store-order/record-list?id=` + id })
}



// 导出订单 Excel
export const exportStoreOrder = async (params) => {
  return await request.download({ url: `/order/store-order/export-excel`, params })
}

export const getLogistic = async (param1,param2) => {
  return await request.get({ url: `/order/express/getLogistic?shipperCode=` + param1 + `&logisticCode=` + param2})
}

export const getOrderHtml = async (param1,param2) => {
  return await request.get({ url: `/order/store-order/printOrder?id=` + param1 + `&electId=` + param2})
}

export const getShopCount = async () => {
  return await request.get({ url: `/order/store-order/count`})
}

export const orderNoticeUrl = async () => {
  return await request.get({ url: `/order/store-order/notice`})
}


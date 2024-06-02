import request from '@/config/axios'

export interface OrderVO {
  id: number
  userId: number
  productId: number
  number: number
  score: number
  totalScore: number
  ip: string
  expressNumber: string
  expressCompany: string
  customerName: string
  customerPhone: string
  customerAddress: string
  status: boolean
  havePaid: number
  haveDelivered: number
  haveReceived: number
}

// 查询积分商城订单列表
export const getOrderPage = async (params: OrderPageReqVO) => {
  return await request.get({ url: `/score/order/page`, params })
}

// 查询积分商城订单详情
export const getOrder = async (id: number) => {
  return await request.get({ url: `/score/order/get?id=` + id })
}

// 新增积分商城订单
export const createOrder = async (data: OrderVO) => {
  return await request.post({ url: `/score/order/create`, data })
}

// 修改积分商城订单
export const updateOrder = async (data: OrderVO) => {
  return await request.put({ url: `/score/order/update`, data })
}

// 删除积分商城订单
export const deleteOrder = async (id: number) => {
  return await request.delete({ url: `/score/order/delete?id=` + id })
}

// 导出积分商城订单 Excel
export const exportOrder = async (params) => {
  return await request.download({ url: `/score/order/export-excel`, params })
}

export const getLogistic = async (param1,param2) => {
  return await request.get({ url: `/order/express/getLogistic?shipperCode=` + param1 + `&logisticCode=` + param2})
}

// 收货
export const takeStoreOrder = async (id) => {
  return await request.get({ url: `/score/order/take?id=` + id })
}

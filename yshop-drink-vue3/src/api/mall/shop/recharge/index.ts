import request from '@/config/axios'

export interface RechargeVO {
  id: number
  name: string
  sales: number
  value: number
  weigh: number
  status: boolean
  sellPrice: number
}

// 查询充值金额管理列表
export const getRechargePage = async (params: RechargePageReqVO) => {
  return await request.get({ url: `/shop/recharge/page`, params })
}

// 查询充值金额管理详情
export const getRecharge = async (id: number) => {
  return await request.get({ url: `/shop/recharge/get?id=` + id })
}

// 新增充值金额管理
export const createRecharge = async (data: RechargeVO) => {
  return await request.post({ url: `/shop/recharge/create`, data })
}

// 修改充值金额管理
export const updateRecharge = async (data: RechargeVO) => {
  return await request.put({ url: `/shop/recharge/update`, data })
}

// 删除充值金额管理
export const deleteRecharge = async (id: number) => {
  return await request.delete({ url: `/shop/recharge/delete?id=` + id })
}

// 导出充值金额管理 Excel
export const exportRecharge = async (params) => {
  return await request.download({ url: `/shop/recharge/export-excel`, params })
}

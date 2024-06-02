import request from '@/config/axios'

export interface UserBillVO {
  id: number
  uid: number
  linkId: string
  pm: byte
  title: string
  category: string
  type: string
  number: number
  balance: number
  mark: string
  status: boolean
}

// 查询用户账单列表
export const getUserBillPage = async (params: UserBillPageReqVO) => {
  return await request.get({ url: `/member/user-bill/page`, params })
}

// 查询用户账单详情
export const getUserBill = async (id: number) => {
  return await request.get({ url: `/member/user-bill/get?id=` + id })
}

// 新增用户账单
export const createUserBill = async (data: UserBillVO) => {
  return await request.post({ url: `/member/user-bill/create`, data })
}

// 修改用户账单
export const updateUserBill = async (data: UserBillVO) => {
  return await request.put({ url: `/member/user-bill/update`, data })
}

// 删除用户账单
export const deleteUserBill = async (id: number) => {
  return await request.delete({ url: `/member/user-bill/delete?id=` + id })
}

// 导出用户账单 Excel
export const exportUserBill = async (params) => {
  return await request.download({ url: `/member/user-bill/export-excel`, params })
}

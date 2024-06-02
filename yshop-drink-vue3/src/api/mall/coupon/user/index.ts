import request from '@/config/axios'

export interface UserVO {
  id: number
  shopId: string
  shopName: string
  title: string
  least: number
  value: number
  starttime: number
  endtime: number
  createtime: number
  updatetime: number
  type: boolean
  score: number
  instructions: string
  image: string
  userId: number
  status: boolean
  couponId: number
  exchangeCode: string
}
export const getUserList = async (id) => {
  return await request.get({ url: `/coupon/user/list?couponId=`+id })
}
// 查询用户领的优惠券列表
export const getUserPage = async (params: UserPageReqVO) => {
  return await request.get({ url: `/coupon/user/page`, params })
}

// 查询用户领的优惠券详情
export const getUser = async (id: number) => {
  return await request.get({ url: `/coupon/user/get?id=` + id })
}

// 新增用户领的优惠券
export const createUser = async (data: UserVO) => {
  return await request.post({ url: `/coupon/user/create`, data })
}

// 修改用户领的优惠券
export const updateUser = async (data: UserVO) => {
  return await request.put({ url: `/coupon/user/update`, data })
}

// 删除用户领的优惠券
export const deleteUser = async (id: number) => {
  return await request.delete({ url: `/coupon/user/delete?id=` + id })
}

// 导出用户领的优惠券 Excel
export const exportUser = async (params) => {
  return await request.download({ url: `/coupon/user/export-excel`, params })
}

import request from '@/config/axios'

export interface UserAddressVO {
  id: number
  uid: number
  realName: string
  phone: string
  province: string
  city: string
  cityId: number
  district: string
  detail: string
  postCode: string
  longitude: string
  latitude: string
  isDefault: byte
}

// 查询用户地址列表
export const getUserAddressPage = async (params: UserAddressPageReqVO) => {
  return await request.get({ url: `/member/user-address/page`, params })
}

// 查询用户地址详情
export const getUserAddress = async (id: number) => {
  return await request.get({ url: `/member/user-address/get?id=` + id })
}

// 新增用户地址
export const createUserAddress = async (data: UserAddressVO) => {
  return await request.post({ url: `/member/user-address/create`, data })
}

// 修改用户地址
export const updateUserAddress = async (data: UserAddressVO) => {
  return await request.put({ url: `/member/user-address/update`, data })
}

// 删除用户地址
export const deleteUserAddress = async (id: number) => {
  return await request.delete({ url: `/member/user-address/delete?id=` + id })
}

// 导出用户地址 Excel
export const exportUserAddress = async (params) => {
  return await request.download({ url: `/member/user-address/export-excel`, params })
}

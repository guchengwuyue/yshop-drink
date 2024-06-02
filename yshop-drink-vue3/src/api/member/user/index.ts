import request from '@/config/axios'

export interface UserVO {
  id: number
  username: string
  password: string
  realName: string
  birthday: number
  cardId: string
  mark: string
  partnerId: number
  groupId: number
  nickname: string
  avatar: string
  phone: string
  addIp: string
  lastIp: string
  nowMoney: number
  brokeragePrice: number
  integral: number
  signNum: number
  status: boolean
  level: byte
  spreadUid: number
  spreadTime: Date
  userType: string
  isPromoter: byte
  payCount: number
  spreadCount: number
  addres: string
  adminid: number
  loginType: string
  wxProfile: string
}

// 查询用户列表
export const getUserPage = async (params: UserPageReqVO) => {
  return await request.get({ url: `/member/user/page`, params })
}

// 查询用户详情
export const getUser = async (id: number) => {
  return await request.get({ url: `/member/user/get?id=` + id })
}

// 新增用户
export const createUser = async (data: UserVO) => {
  return await request.post({ url: `/member/user/create`, data })
}

// 修改用户
export const updateUser = async (data: UserVO) => {
  return await request.put({ url: `/member/user/update`, data })
}

// 修改余额
export const updateMony = async (data: UserVO) => {
  return await request.put({ url: `/member/user/updateMony`, data })
}

// 删除用户
export const deleteUser = async (id: number) => {
  return await request.delete({ url: `/member/user/delete?id=` + id })
}

// 导出用户 Excel
export const exportUser = async (params) => {
  return await request.download({ url: `/member/user/export-excel`, params })
}

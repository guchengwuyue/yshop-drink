import request from '@/config/axios'

export interface ServiceVO {
  id: number
  name: string
  image: string
  type: string
  content: string
  pid: number
  appId: string
  pages: string
  phone: string
  weigh: number
  status: boolean
}

// 查询我的服务列表
export const getServicePage = async (params: ServicePageReqVO) => {
  return await request.get({ url: `/shop/service/page`, params })
}

// 查询我的服务详情
export const getService = async (id: number) => {
  return await request.get({ url: `/shop/service/get?id=` + id })
}

// 新增我的服务
export const createService = async (data: ServiceVO) => {
  return await request.post({ url: `/shop/service/create`, data })
}

// 修改我的服务
export const updateService = async (data: ServiceVO) => {
  return await request.put({ url: `/shop/service/update`, data })
}

// 删除我的服务
export const deleteService = async (id: number) => {
  return await request.delete({ url: `/shop/service/delete?id=` + id })
}

// 导出我的服务 Excel
export const exportService = async (params) => {
  return await request.download({ url: `/shop/service/export-excel`, params })
}

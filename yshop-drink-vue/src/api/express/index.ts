import request from '@/config/axios'

export interface ExpressVO {
  id: number
  code: string
  name: string
  sort: number
}
// 查询快递公司列表
export const getExpressList = async () => {
  return await request.get({ url: `/order/express/list` })
}

// 查询快递公司列表
export const getExpressPage = async (params: ExpressPageReqVO) => {
  return await request.get({ url: `/order/express/page`, params })
}

// 查询快递公司详情
export const getExpress = async (id: number) => {
  return await request.get({ url: `/order/express/get?id=` + id })
}

// 查询快递鸟公司配置详情
export const getExpressSet = async () => {
  return await request.get({ url: `/order/express/set` })
}

export const postExpressSet = async (data) => {
  return await request.post({ url: `/order/express/set`,data })
}



// 新增快递公司
export const createExpress = async (data: ExpressVO) => {
  return await request.post({ url: `/order/express/create`, data })
}

// 修改快递公司
export const updateExpress = async (data: ExpressVO) => {
  return await request.put({ url: `/order/express/update`, data })
}

// 删除快递公司
export const deleteExpress = async (id: number) => {
  return await request.delete({ url: `/order/express/delete?id=` + id })
}

// 导出快递公司 Excel
export const exportExpress = async (params) => {
  return await request.download({ url: `/order/express/export-excel`, params })
}

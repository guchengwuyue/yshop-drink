import request from '@/config/axios'

export interface ProductVO {
  id: number
  title: string
  image: string
  images: string
  desc: string
  score: number
  weigh: number
  stock: number
  sales: number
  switch: boolean
}

// 查询积分产品列表
export const getProductPage = async (params: ProductPageReqVO) => {
  return await request.get({ url: `/score/product/page`, params })
}

// 查询积分产品详情
export const getProduct = async (id: number) => {
  return await request.get({ url: `/score/product/get?id=` + id })
}

// 新增积分产品
export const createProduct = async (data: ProductVO) => {
  return await request.post({ url: `/score/product/create`, data })
}

// 修改积分产品
export const updateProduct = async (data: ProductVO) => {
  return await request.put({ url: `/score/product/update`, data })
}

// 删除积分产品
export const deleteProduct = async (id: number) => {
  return await request.delete({ url: `/score/product/delete?id=` + id })
}

// 导出积分产品 Excel
export const exportProduct = async (params) => {
  return await request.download({ url: `/score/product/export-excel`, params })
}

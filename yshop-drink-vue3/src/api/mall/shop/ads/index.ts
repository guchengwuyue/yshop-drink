import request from '@/config/axios'

export interface AdsVO {
  id: number
  image: string
  switch: boolean
  weigh: number
  shopId: string
}

// 查询广告图管理列表
export const getAdsPage = async (params: AdsPageReqVO) => {
  return await request.get({ url: `/shop/ads/page`, params })
}

// 查询广告图管理详情
export const getAds = async (id: number) => {
  return await request.get({ url: `/shop/ads/get?id=` + id })
}

// 新增广告图管理
export const createAds = async (data: AdsVO) => {
  return await request.post({ url: `/shop/ads/create`, data })
}

// 修改广告图管理
export const updateAds = async (data: AdsVO) => {
  return await request.put({ url: `/shop/ads/update`, data })
}

// 删除广告图管理
export const deleteAds = async (id: number) => {
  return await request.delete({ url: `/shop/ads/delete?id=` + id })
}

// 导出广告图管理 Excel
export const exportAds = async (params) => {
  return await request.download({ url: `/shop/ads/export-excel`, params })
}

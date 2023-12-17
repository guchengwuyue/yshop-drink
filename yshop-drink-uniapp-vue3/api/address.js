import api from './api'

// 删除用户地址 
export function addressDelete(data) {
  return api.post(`/address/del/${data.id}`, undefined, { login: true })
}

// 设置默认地址  
export function shopGetDistanceFromLocation(data) {
  return api.post(`/address/getDistanceFromLocation`, data, { login: true })
}

// 添加或修改地址
export function getAddressAddAndEdit(data) {
  return api.post(`/address/addAndEdit`, data, { login: true })
}

// 用户地址列表 l
export function addressAll(data) {
  return api.get(`/address/list`, data, { login: true })
}


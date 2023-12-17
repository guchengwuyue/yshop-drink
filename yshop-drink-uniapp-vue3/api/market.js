import api from './api'

/**
 * shopGetList 
 */
export function shopGetList(data) {
  return api.get('/store/list', data, { login: false })
}


export function menuAds(data) {
  return api.get('/ad/list', data, { login: false })
}

import { parseUrl } from '@/utils'

export function navigateTo(location, complete, fail, success) {
  console.log({
    url: parseUrl(location),
    complete,
    fail,
    success,
  })
  uni.navigateTo({
    url: parseUrl(location),
    complete,
    fail,
    success,
  })
}

export function replace(location, complete, fail, success) {
  uni.redirectTo({
    url: parseUrl(location),
    complete,
    fail,
    success,
  })
}

export function reLaunch(location, complete, fail, success) {
  uni.reLaunch({
    url: parseUrl(location),
    complete,
    fail,
    success,
  })
}

export function go(delta) {
  uni.navigateBack({
    delta,
  })
}

export function back() {
	const mypage = getCurrentPages()
	if(mypage.length == 1) {
		uni.switchTab({
			url: '/pages/index/index'
		})
		return
	}
  uni.navigateBack({
    delta: 1,
    success: function (e) {},
    fail: function (e) {
		console.log('aaaa:')
		
	},
  })
}

export function switchTab(location, complete, fail, success) {
  uni.switchTab({
    url: parseUrl(location),
    complete,
    fail,
    success,
  })
}

export default {
  back,
  navigateTo,
  replace,
  reLaunch,
  switchTab,
}

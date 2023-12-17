
const doc = null
const CACHE_KEY = 'clear_0.0.1'
// const doc = window.document;

function get(key) {
  return uni.getStorageSync(key)
}

function all() {
  return uni.getStorageInfoSync()
}

function set(key, data, time) {
  console.log("--> % set % key:\n", key)
  console.log("--> % set % data:\n", data)
  if (!key) {
    return
  }
  uni.setStorageSync(key, data)
}

function remove(key) {
  if (!key || !_has(key)) {
    return
  }
  uni.removeStorageSync(key)
}

function clearAll() {
  const res = uni.getStorageInfoSync()
  res.keys.map(item => {
    if (item == 'redirect' || item == 'spread' || item == CACHE_KEY) {
      return
    }
    remove(item)
  })
}

function _has(key) {
  if (!key) {
    return
  }
  let value = uni.getStorageSync(key)
  if (value) {
    return true
  }
  return false
}

export default {
  get,
  all,
  set,
  remove,
  clearAll,
  has: _has,
  CACHE_KEY,
}

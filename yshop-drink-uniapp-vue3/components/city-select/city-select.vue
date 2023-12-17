<template>
  <view>
    <text
      class="uni-input"
      @tap="open"
    >{{ value }}</text>
    <uv-popup
      ref="popup"
      mode="bottom"
    >
      <view class="cityselect">
        <view class="cityselect-header">
          <view class="cityselect-title">
            <text>请选择地址</text>
          </view>
          <view class="cityselect-nav">
            <view
              class="item"
              v-if="provinceActive"
              @tap="changeNav(0)"
            >
              <text>{{ provinceActive.name }}</text>
            </view>
            <view
              class="item"
              v-if="cityActive"
              @tap="changeNav(1)"
            >
              <text>{{ cityActive.name }}</text>
            </view>
            <view
              class="item"
              v-if="districtActive"
              @tap="changeNav(2)"
            >
              <text>{{ districtActive.name }}</text>
            </view>
            <view
              class="item active"
              v-else
            >
              <text>请选择</text>
            </view>
          </view>
        </view>
        <view class="cityselect-content">
          <swiper
            class="swiper"
            disable-touch="true"
            touchable="false"
            :current="current"
          >
            <swiper-item>
              <scroll-view
                scroll-y
                class="cityScroll"
              >
                <view>
                  <view
                    class="cityselect-item"
                    v-for="(item, index) in province"
                    :key="index"
                    @tap="selectProvince(item, index)"
                  >
                    <view
                      class="cityselect-item-box"
                      :class="{ active: isProvinceActive(item) }"
                    >
                      <text>{{ item.name }}</text>
                    </view>
                  </view>
                </view>
              </scroll-view>
            </swiper-item>
            <swiper-item>
              <scroll-view
                scroll-y
                class="cityScroll"
              >
                <view>
                  <view
                    class="cityselect-item"
                    v-for="(item, index) in city"
                    :key="index"
                    @tap="selectCity(item, index)"
                  >
                    <view
                      class="cityselect-item-box"
                      :class="{ active: isCityActive(item) }"
                    >
                      <text>{{ item.name }}</text>
                    </view>
                  </view>
                </view>
              </scroll-view>
            </swiper-item>
            <swiper-item>
              <scroll-view
                scroll-y
                class="cityScroll"
              >
                <view>
                  <view
                    class="cityselect-item"
                    v-for="(item, index) in district"
                    :key="index"
                    @tap="selectDistrict(item, index)"
                  >
                    <view
                      class="cityselect-item-box"
                      :class="{ active: isDistrictActive(item) }"
                    >
                      <text>{{ item.name }}</text>
                    </view>
                  </view>
                </view>
              </scroll-view>
            </swiper-item>
          </swiper>
        </view>
      </view>
    </uv-popup>
  </view>
</template>

<script setup>

import { ref, watch,onMounted } from "vue"
const props = defineProps(['items', 'defaultValue'])

const emit = defineEmits(['callback'])


const items = ref(props.items || [])
// const defaultValue = ref(props.defaultValue)
// console.log("--> % defaultValue:\n", defaultValue)

const value = ref(props.value || '请选择')
const province = ref(props.items)
const provinceActive = ref(null)
const city = ref([])
const cityActive = ref(null)
const district = ref([])
const districtActive = ref(null)
const current = ref(0)
const popup = ref(null)


watch(() => props.items, (next) => {
  province.value = next
})

watch(() => props.defaultValue, (next) => {
  console.log("--> % defaultValue % next:\n", next)
  value.value = `${next.province.name} ${next.city.name} ${next.district.name}`
  setDefaultValue(items.value, next)
})


onMounted(() => {
  // setDefaultValue(items, props.defaultValue)
})

const isProvinceActive = (item) => {
  return provinceActive.value && item.value == provinceActive.value.value
}

const isCityActive = (item) => {
  return cityActive.value && item.value == cityActive.value.value
}

const isDistrictActive = (item) => {
  return districtActive.value && item.value == districtActive.value.value
}

const setDefaultValue = (items, value) => {
  if (!items || !value) {
    return
  }
  province.value = items
  items.map(prov => {
    console.log("--> % setDefaultValue % prov:\n", prov)
    if (prov.name == value.province.name) {
      city.value = prov.id
      provinceActive.value = {
        value: prov.id,
        name: value.province.name,
      }
      prov.children.map(city => {
        if (city.name == value.city.name) {
          district.value = city.children
          cityActive.value = {
            value: city.id,
            name: value.city.name,
          }
          city.children.map(district => {
            if (district.name == value.district.name) {
              districtActive.value = {
                value: city.id,
                name: value.district.name,
              }
            }
          })
        }
      })
    }
  })
  console.log(provinceActive.value, cityActive.value, districtActive.value)
}

const open = () => {
  province.value = props.items
  provinceActive.value = null
  cityActive.value = null
  districtActive.value = null
  city.value = []
  district.value = []
  current.value = 0
  popup.value.open()

  setDefaultValue(items.value, props.defaultValue.value)
}

const changeNav = (index) => {
  if (index == 0) {
    provinceActive.value = null
  }
  if (index == 1) {
    cityActive.value = null
    districtActive.value = null
  }
  if (index == 2) {
    districtActive.value = null
  }
  current.value = index
}

const selectProvince = (selectItem, index) => {
  provinceActive.value = selectItem
  city.value = selectItem.children
  current.value = 1
}

const selectCity = (selectItem, index) => {
  cityActive.value = selectItem
  district.value = selectItem.children
  current.value = 2
}

const selectDistrict = (selectItem, index) => {
  console.log("--> % selectDistrict % selectItem:\n", selectItem)
  districtActive.value = selectItem
  value.value = `${provinceActive.value?.name} ${cityActive.value?.name} ${districtActive.value?.name}`
  console.log({
    province: {
      id: provinceActive.value?.id,
      name: provinceActive.value?.name,
    },
    city: {
      id: cityActive.value?.id,
      name: cityActive.value?.name,
    },
    district: {
      id: districtActive.value?.id,
      name: districtActive.value?.name,
    },
  })
  emit('callback', {
    province: {
      id: provinceActive.value?.id,
      name: provinceActive.value?.name,
    },
    city: {
      id: cityActive.value?.id,
      name: cityActive.value?.name,
    },
    district: {
      id: districtActive.value?.id,
      name: districtActive.value?.name,
    },
  })
  popup.value.close()
}
</script>

<style lang="less">
.cityselect {
  width: 100%;
  height: 75%;
  background-color: #fff;
  z-index: 1502;
  position: relative;
  padding-bottom: 0;
  padding-bottom: constant(safe-area-inset-bottom);
  padding-bottom: env(safe-area-inset-bottom);

  .cityScroll {
    height: 100%;
  }

  .swiper {
    height: 800rpx;
  }
}

.cityselect-header {
  width: 100%;
  z-index: 1;
}

.cityselect-title {
  width: 100%;
  font-size: 30rpx;
  text-align: center;
  height: 95rpx;
  line-height: 95rpx;
  position: relative;

  &:cityselect-title:after {
    height: 1px;
    position: absolute;
    z-index: 0;
    bottom: 0;
    left: 0;
    content: '';
    width: 100%;
    background-image: linear-gradient(0deg, #ececec 50%, transparent 0);
  }
}

.cityselect-nav {
  width: 100%;
  padding-left: 20rpx;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: flex-start;

  .item {
    font-size: 26rpx;
    color: #222;
    display: block;
    height: 80rpx;
    line-height: 92rpx;
    padding: 0 16rpx;
    position: relative;
    margin-right: 30rpx;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    max-width: 40%;

    &.active {
      color: #f23030 !important;
      border-bottom: 1rpx solid #f23030;
    }
  }
}

.cityselect-content {
  height: 100%;
  width: 100%;
}

.cityselect-item {
  .cityselect-item-box {
    display: block;
    padding: 0 40rpx;
    position: relative;
    overflow: hidden;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    word-break: break-all;
    text-overflow: ellipsis;
    line-height: 64rpx;
    max-height: 65rpx;
    font-size: 26rpx;
    color: #333;

    &.active {
      color: #f23030 !important;
    }

    &:after {
      content: '';
      height: 1rpx;
      position: absolute;
      z-index: 0;
      bottom: 0;
      left: 0;
      width: 100%;
      background-image: linear-gradient(0deg, #ececec 50%, transparent 0);
    }
  }
}
</style>

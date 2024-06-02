<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" >
    <baidu-map
      style="height: 400px;width: 100%"
      :ak="ak"
      :map-click="false"
      :center="center"
      :zoom="zoom"
      :scroll-wheel-zoom="true"
      @click="getClickInfo"
      @moving="syncCenterAndZoom"
      @moveend="syncCenterAndZoom"
      @ready="onBaiduMapReady"
      @zoomend="syncCenterAndZoom"
    >
      <bm-view style="width: 100%; height: 100%;" />
      <bm-marker :position="{lng: center.lng, lat: center.lat}" :dragging="true" animation="BMAP_ANIMATION_BOUNCE" />
      <bm-control :offset="{width: '10px', height: '10px'}">
        <bm-auto-complete v-model="keyword" :sug-style="{zIndex: 999999}">
          <el-input v-model="keyword" type="text" placeholder="请输入地名关键字" clearable />
        </bm-auto-complete>
      </bm-control>
      <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT" show-address-bar auto-location />
      <bm-local-search :keyword="keyword" :auto-viewport="true" :panel="false" />
    </baidu-map>

    <template #footer>
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button icon="el-icon-place" type="primary" @click="confirm">确 定</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { BaiduMap, BmControl, BmAutoComplete, BmLocalSearch, BmMarker, BmGeolocation } from 'vue-baidu-map-3x'

// const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const ak = ref(import.meta.env.VITE_BAIDU_MAP_AK)
const zoom = ref(15)
const BMap = ref({})
const center = ref({ lng: 116.404, lat: 39.915 })
const choosedLocation = ref({ point: {}, address: '',province: '', city: '', district: '', addr: '' })

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('地图选择') // 弹窗的标题
const keyword = ref('')// 地区搜索的信息


/** 打开弹窗 */
const open = async () => {
  dialogVisible.value = true
}
defineExpose({ open }) // 提供 open 方法，用于打开弹窗

// 初始化地图
const onBaiduMapReady = (e) => {
  BMap.value = e.BMap
  if (BMap.value) {
    // 获取定位地址信息并赋值给声明变量
    const geolocation = new BMap.value.Geolocation()
    // 通过 getCurrentPosition() 方法：获取当前的位置信息
    geolocation.getCurrentPosition(res => {
      console.log('返回详细地址和经纬度', res)
      if(res){
        const { lng, lat } = res.point
        const { province, city, district, street, street_number } = res.address

        center.value = { lng, lat }
        choosedLocation.value = { province, city, district, addr: street + street_number, lng, lat }
      }
    
    })
  }
}

/** *
 * 地图点击事件。
 */
const getClickInfo = (e) => {
  // 调整地图中心位置
  center.value.lng = e.point.lng
  center.value.lat = e.point.lat

  // 此时已经可以获取到BMap类
  if (BMap.value) {
    // 创建地址解析器的实例
    const geoCoder = new BMap.value.Geocoder()
    // getLocation() 类--利用坐标获取地址的详细信息
    // getPoint() 类--获取位置对应的坐标
    geoCoder.getLocation(e.point, function(res) {
      console.log('获取经纬度', e.point, '获取详细地址', res)
      const addrComponent = res.addressComponents
      const surroundingPois = res.surroundingPois
      const province = addrComponent.province
      const city = addrComponent.city
      const district = addrComponent.district
      const point = e.point
      const address =  res.address
      console.log('获取经纬度1', point, '获取详细地址1', address)
      let addr = addrComponent.street
      if (surroundingPois.length > 0 && surroundingPois[0].title) {
        if (addr) {
          addr += `-${surroundingPois[0].title}`
        } else {
          addr += `${surroundingPois[0].title}`
        }
      } else {
        addr += addrComponent.streetNumber
      }
      choosedLocation.value = { point,address,province, city, district, addr, ...center.value }

      console.log('aa:',choosedLocation.value)
    })
  }
}

const syncCenterAndZoom = (e) => {
  // 返回地图当前的缩放级别
  zoom .value= e.target.getZoom()
}

/** *
 * 确认
 */
const emit = defineEmits(['mapConfirm']) 
const confirm = () => {
  console.log('choosedLocation.value', choosedLocation.value.address)
  if(choosedLocation.value.address == '') {
    return message.error('请选择定位地址')
  }
  dialogVisible.value = false
    // 发送操作成功的事件
  emit('mapConfirm',choosedLocation.value)
}



</script>
<style scoped>
.map {
  width: 100%;
  height: 300px;
}
</style>

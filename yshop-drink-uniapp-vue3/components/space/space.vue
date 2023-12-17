<template>
  <view
    :class="classObject"
    :style="[getStyle]"
  >
    <slot></slot>
  </view>
</template>

<script setup>
import { ref, watch, onMounted } from "vue"
const props = defineProps([
  'direction',
  'align',
  'wrap',
  'justify',
  'size',
  'height',
  'padding',
  'flex',
  'border'
])

console.log("--> % props:\n", props)


const classObject = ref({})

const handleClassObject = (props) => {
  console.log("--> % handleClassObject % props:\n", props.value)
  let className = 'yshop-space'
  let direction = props.direction
  let align = props.align
  let wrap = props.wrap
  let justify = props.justify
  let flex = props.flex
  let border = props.border
  if (border) {
    className += ' yshop-space-border'
  }
  if (direction) {
    className += ` yshop-space-${direction}`
  }
  if (wrap) {
    className += ` yshop-space-${wrap}`
  }
  if (align) {
    className += ` yshop-space-align-${align}`
  }
  if (justify) {
    className += ` yshop-space-justify-${justify}`
  }
  console.log("--> % handleClassObject % className:\n", className)

  classObject.value = className
}

const getStyle = ({
  size = 6,
  wrap,
  height,
  padding,
  flex
}) => {
  let innerStyle = {}
  // let size = size.value
  // let wrap = wrap.value
  // let height = height.value
  // let padding = padding.value

  if (height) {
    innerStyle.height = `${height}rpx`
  }
  if (typeof size === 'number') {
    innerStyle.gap = size + 'px'
  }


  if (wrap) {
    innerStyle.flexWrap = 'wrap'
  }

  if (typeof size === 'string') {
    switch (size) {
      case 'small':
        innerStyle.gap = '8rpx'
        break
      case 'middle':
        innerStyle.gap = '16rpx'
        break
      case 'large':
        innerStyle.gap = '24rpx'
        break
    }
  }


  if (typeof padding === 'string') {
    innerStyle.padding = `${padding}rpx`
  }

  if (Object.prototype.toString.call(padding) === '[object Array]') {
    if (typeof padding === 'object') {
      if (padding.length == 1) {
        innerStyle.padding = `${padding[0]}rpx`
      }
      if (padding.length == 2) {
        innerStyle.padding = `${padding[0]}rpx ${padding[1]}rpx`
      }
    }
  }

  if (Object.prototype.toString.call(size) === '[object Array]') {
    if (typeof size === 'object') {
      if (size.length == 1) {
        innerStyle.gap = `${size[0]}rpx`
      }
      if (size.length == 2) {
        innerStyle.gap = `${size[0]}rpx ${size[1]}rpx`
      }
    }
  }



  if (flex) {
    innerStyle.flex = flex
  }

  return innerStyle
}

onMounted(() => {
  handleClassObject(props)
  getStyle(props)
})

</script>

<style lang="less">
space {
  width: 100%;
}

.yshop-space {
  display: flex;
  width: 100%;
  flex: 1;

  &-vertical {
    flex-direction: column;
  }

  &-align-center {
    align-items: center;
  }

  &-align-start {
    align-items: flex-start;
  }

  &-align-end {
    align-items: flex-end;
  }

  &-justify-center {
    justify-content: center;
  }

  &-justify-between {
    justify-content: space-between;
  }

  &-justify-around {
    justify-content: space-around;
  }

  &-align-end {
    align-items: flex-end;
  }

  &-align-baseline {
    align-items: baseline;
  }

  &-item:empty {
    display: none;
  }

  &-border {
    border-bottom: 1rpx solid #f9f9f9;
  }
}

// #ifdef APP-PLUS
.yshop-space {
  &>view {
    margin-right: 8rpx;
  }

  &>text {
    margin-right: 8rpx;
  }

  &>image {
    margin-right: 8rpx;
  }

  .u-tag-wrapper {
    margin-right: 8rpx;
  }

  .u-tag {
    margin-right: 8rpx;
  }

}

// #endif
</style>

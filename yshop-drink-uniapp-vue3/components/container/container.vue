<template>
  <view :class="['container', min ? 'container-min' : '']">
    <slot></slot>
  </view>
</template>

<script setup>
import { ref, } from 'vue';
const props = defineProps(['min'])
const min = ref(props?.min != undefined)
</script>

<style lang="less">
.container {
  padding: 0 34rpx;

  &-min {
    padding: 0 10rpx;

  }
}
</style>

<template>
  <view
    :class="['card', props.class]"
    :style="{ width: width ? width + 'rpx' : '100%' }"
  >
    <slot></slot>
  </view>
</template>

<script setup>
import { ref } from 'vue';
const props = defineProps(['class', 'width'])
const className = ref(props.class)
const width = ref(props.width)
</script>

<style lang="less">
.card {
  width: 100%;
  background-color: #fff;
  border-radius: 7.5rpx;
  box-sizing: border-box;
  overflow: hidden;
}
</style>

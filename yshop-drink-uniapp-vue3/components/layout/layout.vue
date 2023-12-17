<template>
  <view class="layout">
    <slot />
  </view>
</template>

<script setup>
import { ref, defineProps, reactive } from 'vue';
const props = defineProps(['size'])
</script>

<style lang="less">
.layout {
  min-height: 100%;
}
</style>

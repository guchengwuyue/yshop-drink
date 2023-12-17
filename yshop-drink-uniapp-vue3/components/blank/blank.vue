<template>
  <view
    class="blank"
    :style="{ height: size + 'px' }"
  ></view>
</template>

<script setup>
import { ref } from 'vue';
const props = defineProps(['size'])
const size = ref(props.size)
</script>

<style lang="less">
</style>

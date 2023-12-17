<template>
  <uv-upload
    :fileList="list"
    name="1"
    multiple
    :maxCount="10"
    @afterRead="afterRead"
    @delete="handleDeletePic"
  ></uv-upload>
</template>

<script setup>
import { VUE_APP_UPLOAD_URL } from '@/config';
import { ref } from 'vue';

const props = defineProps(['modelValue'])
console.log("--> % modelValue:\n", props.modelValue)
const emit = defineEmits(['update:modelValue'])

const list = ref(props.modelValue)

const handleDeletePic = (event) => {
  list.value.splice(event.index, 1)

  emit('update:modelValue', list.value)
}

const afterRead = async (event) => {
  // 当设置 multiple 为 true 时, file 为数组格式，否则为对象格式
  let lists = [].concat(event.file)
  let fileListLen = list.value.length
  lists.map((item) => {
    list.value.push({
      ...item,
      status: 'uploading',
      message: '上传中'
    })
  })
  for (let i = 0; i < lists.length; i++) {
    const result = await uploadFilePromise(lists[i].url)
    console.log("gxs --> % afterRead % result:\n", result)
    let item = list.value[fileListLen]
    list.value.splice(fileListLen, 1, Object.assign(item, {
      status: 'success',
      message: '',
      url: result
    }))
    fileListLen++
  }
  emit('update:modelValue', list.value)
}

const uploadFilePromise = (url) => {
  return new Promise((resolve, reject) => {
    let a = uni.uploadFile({
      url: VUE_APP_UPLOAD_URL, // 仅为示例，非真实的接口地址
      filePath: url,
      name: 'file',
      formData: {
        user: 'test'
      },
      success: (res) => {
        console.log("gxs --> % returnnewPromise % res:\n", res)
        setTimeout(() => {
          resolve(res.data.data)
        }, 10)
      }
    });
  })
}

</script>

<style lang="less">
.activity {

  &-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20rpx;

    &-info {
      display: flex;
      align-items: flex-end;
    }

    &-title {
      line-height: 45rpx;
      font-size: 32rpx;
      color: #333333;
    }

    &-subtitle {
      margin-left: 10rpx;

      line-height: 33rpx;
      font-size: 24rpx;
      color: #EE6D46;
    }

    &-more {
      display: flex;
      align-items: center;


      &-info {
        line-height: 33rpx;
        font-size: 24rpx;
        color: #999999;
      }

      .image {
        margin-left: 10rpx;
        display: block;
        width: 20rpx;
        height: 20rpx;
      }
    }

    &-body {}

  }
}
</style>

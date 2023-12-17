<template>
  <uv-button
    round
    size="mini"
    block
    type="primary"
    @click="startCountdown"
  >
    {{ buttonText }}
  </uv-button>
</template>

<script setup>
import {
  ref,
  computed,
  onUnmounted
} from 'vue'
import {
  sendSmsCode
} from '@/api/auth'

const props = defineProps(['mobile', 'scene'])

const countingDown = ref(false); // 是否正在倒计时
const countdownSeconds = ref(60); // 倒计时的总秒数
const timer = ref(null); // 倒计时的总秒数

const buttonText = computed(() => {
  return countingDown.value ? `${countdownSeconds.value} 秒` : '发送验证码';
});

const startCountdown = () => {
  if (!countingDown.value) {
    countingDown.value = true;
    handleSendSmsCode()
  }
};


const handleSendSmsCode = () => {
  uni.showLoading({
    title: '发送验证码中'
  });
  console.log("gxs --> % handleSendSmsCode % props.mobile:\n", props.mobile)
  sendSmsCode({
    "mobile": props.mobile,
    "scene": props.scene
  }).then(res => {
    uni.hideLoading();
    uni.showToast({
      icon: 'none',
      title: '验证码已发送',
      duration: 2000
    });
    timer.value = setInterval(() => {
      countdownSeconds.value--;
      console.log("gxs --> % timer % countdownSeconds.value:\n", countdownSeconds.value)
      if (countdownSeconds.value <= 0) {
        clearInterval(timer.value);
        countdownSeconds.value = 60; // 倒计时结束后重置为初始值
        countingDown.value = false;
      }
    }, 1000);
  }).catch(error => {
    countingDown.value = false;

  })
}

onUnmounted(() => {
  clearInterval(timer.value);
})
</script>

<style lang="less">
.goods {
  position: relative;
  padding: 30rpx 0;

  &-card {
    display: flex;
    flex-direction: column;

    .goods {
      &-content {
        padding: 0 20rpx;
        display: flex;
        flex-direction: column;
      }

      &-info {
        margin-top: 15rpx;
      }

      &-thumb {
        margin-bottom: 15rpx;
        width: 100%;
        height: 203rpx;

        &-img {
          width: 100%;
          height: 100%;
          display: block;
        }
      }

    }
  }

  &-header {}

  &-thumb {
    background: #FAFAFA;
  }

  &-content {}

  &-storeName {
    line-height: 40rpx;
    font-size: 28rpx;
    font-weight: 500;
    color: #333333;
  }



  &-price {
    &-row {
      display: flex;
      align-items: center;

      .goods-price {}
    }

    &-primary {
      line-height: 42rpx;
      font-size: 30rpx;
      font-weight: 500;
      color: #EE6D46;
    }

    &-default {
      line-height: 40rpx;
      font-size: 28rpx;
      font-weight: 500;
      color: #333333;
    }

    &-original {
      margin-left: 9rpx;
      line-height: 28rpx;
      font-size: 20rpx;
      color: #999999;
      text-decoration: line-through;
    }

  }


  &-desc {
    line-height: 33rpx;
    font-size: 24rpx;
    color: #999999;
  }

  &-info {
    display: flex;
    align-items: flex-end;
    justify-content: space-between;

    &-left {}

    &-action {
      &-btn {}

      &-desc {
        line-height: 28rpx;
        font-size: 20rpx;
        color: #999999;
      }
    }


  }

  &-image {
    &-img {}
  }

  &-list {
    display: flex;
    flex-direction: row;
    padding: 14rpx;
    box-sizing: border-box;
    width: 100%;

    .goods {
      &-thumb {
        margin-bottom: 0;
        width: 220rpx;
        height: 220rpx;

        &-img {
          width: 100%;
          height: 100%;
          display: block;
        }
      }

      &-content {
        padding-right: 40rpx;
        margin-left: 30rpx;
        flex: 1;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
      }
    }



    &-model {
      display: flex;


      margin-bottom: 28rpx;

      &-border {
        display: flex;
        align-items: center;
        height: 40rpx;
        border: 1px solid #CCCCCC;
        opacity: 1;
        border-radius: 0rpx;
        padding: 0 10rpx;
      }

      &-info {}

      &-label {
        line-height: 38rpx;
        font-size: 24rpx;
        color: #999999;
        margin-right: 10rpx;
      }

      &-value {
        line-height: 38rpx;
        font-size: 24rpx;
        color: #333333;
        margin-right: 10rpx;
      }

      &-action {
        width: 11rpx;
        height: 7rpx;
      }
    }

  }

  &-min {
    .goods {
      &-thumb {
        margin-bottom: 0;
        width: 150rpx;
        height: 150rpx;

        &-img {
          width: 100%;
          height: 100%;
          display: block;
        }
      }
    }
  }

  // .goods {
  //   padding: 16rpx 14rpx;

  //   &-header {
  //     display: flex;
  //     align-items: flex-start;
  //   }

  //   &-thumb {

  //     width: 220rpx;
  //     height: 220rpx;

  //     &-img {
  //       width: 100%;
  //       height: 100%;
  //       display: block;
  //     }
  //   }

  //   &-content {
  //     margin-top: 24rpx;
  //     margin-left: 40rpx;
  //     flex: 1
  //   }

  //   &-storeName {
  //     line-height: 40rpx;
  //     font-size: 28rpx;
  //     font-weight: 500;
  //     color: #333333;
  //     margin-bottom: 35rpx;
  //   }

  //   &-info {
  //     display: flex;
  //     align-items: center;
  //     justify-content: space-between;

  //     &-left {
  //       display: flex;
  //       align-items: flex-end;

  //     }

  //     &-action {
  //       &-btn {}

  //       &-desc {
  //         color: #999999;
  //         font-size: 24rpx;
  //         line-height: 40rpx;
  //       }
  //     }

  //   }


  //   &-price {
  //     &-default {
  //       line-height: 28rpx;
  //       font-size: 20rpx;
  //       color: #999999;

  //     }

  //     &-primary {
  //       line-height: 42rpx;
  //       font-size: 30rpx;
  //       font-weight: 500;
  //       color: #EE6D46;
  //       margin-left: 5rpx;
  //     }
  //   }



  //   &-desc {
  //     color: #999999;
  //     font-size: 24rpx;
  //     line-height: 40rpx;
  //   }


  &-model {
    display: inline-flex;
    align-items: center;
    width: auto;
    height: 40rpx;
    border: 1px solid #CCCCCC;
    opacity: 1;
    border-radius: 0rpx;
    padding: 0 10rpx;

    margin-bottom: 28rpx;

    &-label {
      line-height: 38rpx;
      font-size: 24rpx;
      color: #999999;
    }

    &-value {
      line-height: 38rpx;
      font-size: 24rpx;
      color: #333333;
      margin-right: 10rpx;
    }

    &-action {
      width: 11rpx;
      height: 7rpx;
    }
  }

  //   &-model-info {
  //     display: inline-flex;
  //     align-items: center;
  //     width: auto;
  //     height: 40rpx;
  //     opacity: 1;
  //     border-radius: 0rpx;
  //     margin-bottom: 28rpx;

  //     &-label {
  //       line-height: 38rpx;
  //       font-size: 24rpx;
  //       color: #999999;
  //     }

  //     &-value {
  //       line-height: 38rpx;
  //       font-size: 24rpx;
  //       color: #333333;
  //       margin-right: 10rpx;
  //     }

  //     &-action {
  //       width: 11rpx;
  //       height: 7rpx;
  //     }
  //   }
  // }
}

.buy-progress {
  display: flex;
  align-items: center;
  justify-content: space-between;

  &-info {
    flex: 1;

    &-desc {
      color: #999999;
      font-size: 24rpx;
      line-height: 32rpx;
    }
  }

  &-action {
    margin-left: 17rpx;
  }
}

.buy-num {

  &-info-desc {
    color: #999999;
    font-size: 24rpx;
    line-height: 32rpx;
  }
}
</style>

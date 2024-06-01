<template>
	<layout>
		<uv-navbar
		  :fixed="false"
		  :title="title"
		  left-arrow
		  @leftClick="$onClickLeft"
		/>
		<view class="wrap">
		<view class="top"></view>
		<view class="content">
			<view class="title">欢迎登录</view>
			
			<input class="u-border-bottom" type="number" v-model="mobile" placeholder="请输入手机号" />
			<view class="tips">未注册的手机号验证后自动创建账号</view>

			
			<view style="display: flex;">
				<view style="width: 50%;">
					<input style="height: 100rpx;"  class="u-border-bottom" type="number" v-model="captcha" placeholder="请输入验证码" />
				</view>
				<view style="width: 50%;">
					<button style="height: 100rpx;" @tap="getCaptcha" :style="[captchaStyle]" class="getCaptcha">
						{{captchaText}}
						<uv-code :seconds="seconds" @end="endCaptcha" @start="startCaptcha" ref="uCode" @change="changeCapcha"></uv-code>
					<!-- 	<u-verification-code :seconds="seconds" @end="endCaptcha" @change="changeCapcha" @start="startCaptcha" ref="uCode" >
							
						</u-verification-code> -->
					</button>
				</view>
			</view>
			
			<button @tap="submit" style="color:#fff;background-color: #f9ae3d;" type="primary" class="login">登录</button>
			
		</view>
		<view class="buttom">
			<view class="loginType">
				<!-- #ifdef MP-WEIXIN -->
				<button type="primary" size="default" class="login-btn" open-type="getPhoneNumber" @getphonenumber="loginForWechatMini">
				<!-- 	<image src="/static/images/mine/wechat.png"></image> -->
					手机号快捷登录
				</button>
				<!-- #endif -->
			</view>
			<view class="hint">
			<!-- 	<label class="label"> -->
					<radio value="isChecked" @tap.stop="onChange" />
					我已经阅读并遵守
					<text class="link" @tap="serv(29,'用户协议')">《用户协议》</text>与
						<text class="link"  @tap="serv(30,'隐私政策')">《隐私政策》</text>
			<!-- 	</label> -->
			</view>
		</view>
		<uv-toast ref="uToast"></uv-toast>
	</view>
	</layout>
</template>

<script setup>
import {
  ref,
  computed
} from 'vue'
import { onLoad,onShow } from '@dcloudio/uni-app'
import { useMainStore } from '@/store/store'
import {
  userAuthSession,
  userLoginForWechatMini,
  smsSend,
  userLogin
} from '@/api/auth'
import * as util  from '@/utils/util'
import { mobile as testMobible } from '@/uni_modules/uv-ui-tools/libs/function/test'
const main = useMainStore()
const title = ref('登录')
const mobile = ref('')
const captcha = ref('')
const captchaText = ref('获取验证码')	
const password = ref('')
const seconds = ref(60)
const isChecked = ref(false)
const openid = ref(main.openid)
const uToast = ref()
const uCode = ref()

const captchaStyle = computed(() => {
  let style = {};
  if(mobile.value && captchaText.value == '获取验证码') {
  	style.color = "#fff";
  	style.backgroundColor = '#f9ae3d';
  }
  return style;
});

onShow(() => {
   
	// #ifdef MP-WEIXIN
	if(!openid.value){
		wechatMiniLogin();
	}
	
	// #endif
})


const wechatMiniLogin = () => {
	//this.$u.toast('登录中');
	uni.login({
		provider: 'weixin'
	}).then(async (res) => {
		let data = await userAuthSession({
			code: res.code
		});
		if (data) {
			console.log('data.openId001:',data.openId)
			main.SET_OPENID(data.openId)
			openid.value = data.openId
		}
	});
}



const loginForWechatMini = async (e) => {
	if(!isChecked.value){
		uToast.value.show({
			message: '请勾选下面协议',
			type: 'error'
		});
		return
	}
	if (e.detail.encryptedData && e.detail.iv) {
		let data = await userLoginForWechatMini({
			encryptedData: e.detail.encryptedData,
			iv: e.detail.iv,
			openid: openid.value
		});
		if (data) {
			main.SET_MEMBER(data.userInfo);
			main.SET_TOKEN(data.accessToken);
			uToast.value.show({
				title: '登录成功',
				type: 'success'
			});
			setTimeout(function() {
				uni.navigateBack();
			}, 2000);
		}
	}
}

const getCaptcha = async () => {
			
	if (testMobible(mobile.value) == false) {
		uToast.value.show({
			message: '手机号码格式不对',
			type: 'error'
		});
		return
	}
	
	let data = await smsSend({
		mobile: mobile.value,
		scene: 1
	});		
	if (data) {
		uCode.value.start();
		
	}
}

// 验证码开始计时	
const startCaptcha = () => {
	console.log('start')
}
// 验证码结束
const endCaptcha = () => {
	console.log('end')
	captchaText.value = '获取验证码';
}
const changeCapcha = (text)  => {
	console.log('change:' + text)
	captchaText.value = text;
}
// 提交
const submit = () => {
	
	if (testMobible(mobile.value) == false) {
		uToast.value.show({
			message: '手机号码格式不对',
			type: 'error'
		});
		return
	}
	
	if(!isChecked.value){
		uToast.value.show({
			message: '请勾选下面协议',
			type: 'error'
		});
		return
	}
	
	login()

}

// 登录
const login = async () => {
	let from = 'routine'
	// #ifdef H5
	from = 'h5'
	if(util.isWeixin()){
		from = 'wechat'
	}
	
	// #endif
	let data = await userLogin({
		mobile: mobile.value,
		code: captcha.value,
		from: from,
		openid: openid.value
	})
	if (data) {
		uni.setStorage({
			key: 'userinfo',
			data: data.userInfo
		});
		uni.setStorage({
			key: 'accessToken',
			data: data.accessToken
		});
		main.SET_MEMBER(data.userInfo);
		main.SET_TOKEN(data.accessToken);
		uToast.value.show({
			message: '登录成功',
			type: 'success'
		});
		setTimeout(function() {
			uni.navigateBack();
		}, 2000);
	}
}

const serv = (id,name) => {
	uni.navigateTo({
			url: '/pages/components/pages/mine/content?id=' + id + '&name=' + name
	})
}

const onChange = () => {
	isChecked.value = !isChecked.value
}

</script>

<style lang="scss" scoped>
.wrap {
	background-color: #ffffff;
	font-size: 28rpx;
	position: relative;
	height: 100%;
	.content {
		width: 600rpx;
		margin: 0 auto;

		.title {
			text-align: left;
			font-size: 60rpx;
			font-weight: 500;
			margin-bottom: 100rpx;
		}
		input {
			text-align: left;
			margin-bottom: 10rpx;
			padding-bottom: 6rpx;
		}
		.tips {
			color: $uv-info;
			margin-bottom: 60rpx;
			margin-top: 8rpx;
		}
		.getCaptcha {
			background-color: rgb(253, 243, 208);
			color: $uv-tips-color;
			border: none;
			font-size: 30rpx;
			padding: 12rpx 0;
			
			&::after {
				border: none;
			}
		}
		.login {
			background-color: rgb(253, 243, 208);
			color: $uv-tips-color;
			border: none;
			font-size: 30rpx;
			padding: 12rpx 0;
			margin-top: 40rpx;
			&::after {
				border: none;
			}
		}
		.alternative {
			color: $uv-tips-color;
			display: flex;
			justify-content: space-between;
			margin-top: 30rpx;
		}
	}
	.buttom {
		//position: absolute;
		bottom: 0;
		//display: flex;
		//flex-direction: column;
		//align-items: center;
		//justify-content: center;
		.loginType {
			padding: 80rpx;
			//justify-content:space-between;
			
			.login-btn {
				background-color: #1aad19!important;
				width: 100%;
				border-radius: 50rem !important;
				display: flex;
				align-items: center;
				justify-content: center;
				padding: 10rpx 0;
				text-align: center;
				image {
					width: 36rpx;
					height: 30rpx;
					margin-right: 10rpx;
					vertical-align: middle;
				}
			}
		}
		
		.hint {
			display: flex;
			align-items: center;
			justify-content: center;
			padding: 20rpx 40rpx;
			font-size: 20rpx;
			color: $uv-tips-color;
			
			.link {
				color: $uv-warning;
			}
		}
	}
}
</style>

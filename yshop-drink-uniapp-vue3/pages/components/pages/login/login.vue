<template>
	<layout>
		<uv-navbar
		  :fixed="false"
		  :title="title"
		  left-arrow
		  @leftClick="$onClickLeft"
		/>
		<view class="login-page">
		<view class="login-page__form">
			<view class="login-page__title">欢迎登录</view>
			
			<input class="login-page__input u-border-bottom" type="number" v-model="mobile" placeholder="请输入手机号" />
			<view class="login-page__tips">未注册的手机号验证后自动创建账号</view>

			<view class="login-page__captcha-row">
				<view class="login-page__captcha-col">
					<input class="login-page__input login-page__input--captcha u-border-bottom" type="number" v-model="captcha" placeholder="请输入验证码" />
				</view>
				<view class="login-page__captcha-col">
					<button
						class="login-page__btn login-page__btn--captcha"
						:class="{ 'login-page__btn--active': isCaptchaBtnActive }"
						@tap="getCaptcha"
					>
						{{captchaText}}
						<uv-code :seconds="seconds" @end="endCaptcha" @start="startCaptcha" ref="uCode" @change="changeCapcha"></uv-code>
					</button>
				</view>
			</view>
			
			<button @tap="submit" type="primary" class="login-page__btn login-page__btn--submit login-page__btn--active">登录</button>
			
		</view>
		<view class="login-page__footer">
			<view class="login-page__oauth">
				<!-- #ifdef MP-WEIXIN -->
				<button type="primary" size="default" class="login-page__wechat-btn" open-type="getPhoneNumber" @getphonenumber="loginForWechatMini">
					手机号快捷登录
				</button>
				<!-- #endif -->
			</view>
			<view class="login-page__agreement">
					<radio value="isChecked" @tap.stop="onChange" />
					我已经阅读并遵守
					<text class="login-page__link" @tap="serv(29,'用户协议')">《用户协议》</text>与
						<text class="login-page__link" @tap="serv(30,'隐私政策')">《隐私政策》</text>
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

const isCaptchaBtnActive = computed(() => {
	return Boolean(mobile.value) && captchaText.value === '获取验证码';
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
$login-content-width: 600rpx;
$login-title-size: 60rpx;
$login-title-margin-bottom: 100rpx;
$login-input-height: 100rpx;
$login-btn-font-size: 30rpx;
$login-btn-padding-y: 12rpx;
$login-submit-margin-top: 40rpx;
$login-oauth-padding: 80rpx;
$login-wechat-btn-bg: #1aad19;
$login-wechat-icon-width: 36rpx;
$login-wechat-icon-height: 30rpx;
$login-agreement-font-size: 20rpx;
$login-agreement-padding-y: 20rpx;
$login-agreement-padding-x: 40rpx;

.login-page {
	--login-content-width: #{$login-content-width};
	--login-input-height: #{$login-input-height};
	--login-btn-font-size: #{$login-btn-font-size};

	background-color: $text-color-white;
	font-size: $font-size-base;
	position: relative;
	height: 100%;

	&__form {
		width: var(--login-content-width);
		margin: 0 auto;
	}

	&__title {
		text-align: left;
		font-size: $login-title-size;
		font-weight: 500;
		margin-bottom: $login-title-margin-bottom;
	}

	&__input {
		text-align: left;
		margin-bottom: 10rpx;
		padding-bottom: 6rpx;

		&--captcha {
			height: var(--login-input-height);
		}
	}

	&__tips {
		color: $uv-info;
		margin: 8rpx 0 60rpx;
	}

	&__captcha-row {
		display: flex;
	}

	&__captcha-col {
		width: 50%;
	}

	&__btn {
		height: var(--login-input-height);
		border: none;
		font-size: var(--login-btn-font-size);
		padding: $login-btn-padding-y 0;
		background-color: $uv-warning-light;
		color: $uv-tips-color;

		&::after {
			border: none;
		}

		&--active {
			background-color: $uv-warning;
			color: $text-color-white;
		}

		&--submit {
			margin-top: $login-submit-margin-top;
		}
	}

	&__footer {
		bottom: 0;
	}

	&__oauth {
		padding: $login-oauth-padding;
	}

	&__wechat-btn {
		width: 100%;
		border-radius: 50rem;
		display: flex;
		align-items: center;
		justify-content: center;
		padding: 10rpx 0;
		text-align: center;
		background-color: $login-wechat-btn-bg !important;

		image {
			width: $login-wechat-icon-width;
			height: $login-wechat-icon-height;
			margin-right: 10rpx;
			vertical-align: middle;
		}
	}

	&__agreement {
		display: flex;
		align-items: center;
		justify-content: center;
		padding: $login-agreement-padding-y $login-agreement-padding-x;
		font-size: $login-agreement-font-size;
		color: $uv-tips-color;
	}

	&__link {
		color: $uv-warning;
	}
}
</style>

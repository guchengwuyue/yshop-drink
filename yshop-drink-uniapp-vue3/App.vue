<script setup>
import { onHide,onLaunch,onShow } from '@dcloudio/uni-app'
import { storeToRefs } from 'pinia'
import { useMainStore } from '@/store/store'
const main = useMainStore()
import { isWeixin,parseQuery } from '@/utils/util'
import cookie from '@/utils/cookie'
import {
  userAuthSession,
  wechatAuth
} from '@/api/auth'
import { APP_ID } from '@/config'

onLaunch(() => {
	console.log('App Launch')
})

onShow(() => {
    console.log('App Show')
   
	// 检查用户登录情况
	// #ifdef H5
	if(isWeixin()){
		oAuth()
		// H5编译的代码
		// 判断是否是微信浏览器
	}
	// #endif
	// #ifdef MP-WEIXIN
	wechatMiniLogin();
	// #endif
})

onHide(() => {
   console.log('App Hide')
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
			main.SET_OPENID(data.openId)
			if (data.hasOwnProperty('userInfo') && data.accessToken && data.accessToken != '') {
				main.SET_MEMBER(data.userInfo);
				main.SET_TOKEN(data.accessToken);
			}
		}
	});
}

const getAuthUrl = (appId) => {
	  // #ifdef H5
	  // #endif
	  cookie.set('redirect', window.location.href)
	  const url = `${location.origin}/h5/#/pages/index/index`
	  cookie.set('index_url',url)
	  let redirect_uri = encodeURIComponent(url)
	
	  const state = 'STATE'
	  return `https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appId}&redirect_uri=${redirect_uri}&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect`
 }
 
const oAuth = async() => {
	  const WX_AUTH = 'wx_auth'
	  return new Promise((resolve, reject) => {
		     
		const accessToken = uni.getStorageSync('accessToken');
		if (cookie.has('wx_auth') && accessToken && main.isLogin) {
		  reject()
		  return
		}
		const { code } = parseQuery()
		if (!code) {
		  //公众号appid
		  const appid = APP_ID;
		  location.href = getAuthUrl(appid)
		  return
		} else {
		 auth(code)
		}
		resolve()
	  }).catch(error => {
		console.log(error)
	  })
}

const auth = async(code) => {
	console.log('获取微信授权:',code)
	let data = await wechatAuth({'code':code})
	cookie.set('wx_auth', code)
	if (data) {
		main.SET_OPENID(data.openId)
		main.SET_MEMBER(data.userInfo);
		main.SET_TOKEN(data.accessToken);
		
	
	}

}



</script>

<style lang="scss">
@import '~@/static/style/app.scss';
//@import 'static/iconfont/iconfont.scss';
//@import url('./static/style/style.less');
@import 'static/style/yshop.css';

// /*每个页面公共css */
// page {
//   background-color: #f5f5f5;
// }
</style>

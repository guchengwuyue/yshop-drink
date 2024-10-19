<template>
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<view class="container d-flex flex-column">
		<view class="flex-fill form">
			<list-cell :hover="false">
				<view class="form-input w-100 d-flex align-items-center">
					<view class="label">头像</view>
					<view class="input flex-fill">
						<view class="form-input w-100 d-flex align-items-center"  style="position: relative;">
							<view class="flex user-box align-items-center">
								<view class="mr-1">
									<uv-avatar :src="member.avatar" size="60"></uv-avatar>
								</view>
								<view v-if="!member.avatar" class="flex-1" style="right:0;height: 60rpx;">
									<button style="background-color: #09b4f1;color:#fff;" size='mini' open-type="chooseAvatar" @chooseavatar="chooseavatar" type="success">更改头像</button>
								</view>
							</view>
						</view>
					</view>
				</view>
				
			</list-cell>
			<list-cell :hover="false">
				<view class="form-input w-100 d-flex align-items-center">
					<view class="label">昵称</view>
					<view class="input flex-fill">
						<input type="nickname" placeholder="请填写昵称" placeholder-class="text-color-assist font-size-base" 
						v-model="member.nickname">
					</view>
				</view>
			</list-cell>
			<list-cell :hover="false">
				<view class="form-input w-100 d-flex align-items-center" style="position: relative;">
					<view class="label">手机号码</view>
					<view class="input flex-fill">
						<input type="text" v-model="member.mobile" disabled>
					</view>
				</view>
			</list-cell>
			<list-cell :hover="false">
				<view class="form-input w-100 d-flex align-items-center">
					<view class="label">性别</view>
					<view class="input flex-fill">
						<view class="radio-group">
							<view class="radio" :class="{'checked': member.gender == '0'}" style="margin-right: 10rpx;" @tap="member.gender=0">先生</view>
							<view class="radio" :class="{'checked': member.gender == '1'}" @tap="member.gender=1">女士</view>
						</view>
					</view>
				</view>
			</list-cell>
			<list-cell :hover="false" :arrow="!member.birthday">
				<view class="form-input w-100 d-flex align-items-center">
					<view class="label">生日</view>
					<view class="input flex-fill">
						<picker mode="date" :value="member.birthday" :start="startDate" :end="endDate" @change="handleDateChange">
							{{member.birthday ? member.birthday : '无'}}
						</picker>
					</view>
				</view>
			</list-cell>
			<list-cell :hover="false" last>
				<view class="form-input w-100 d-flex align-items-center">
					<view class="label">加入时间</view>
					<view class="input flex-fill">
						<input type="text" v-model="member.createTime" disabled>
					</view>
				</view>
			</list-cell>
		</view>
		<view class="btn-box d-flex align-items-center  just-content-center">
			<button type="primary" class="save-btn" @tap="save">保存</button>
		</view>

	</view>
</template>

<script setup>
import {
  ref,
  computed
} from 'vue'
import { useMainStore } from '@/store/store'
import { storeToRefs } from 'pinia'
import { onLoad,onShow} from '@dcloudio/uni-app'
import { formatDateTime } from '@/utils/util'
import {
  userEdit
} from '@/api/user'
import { VUE_APP_UPLOAD_URL } from '@/config';
const main = useMainStore()
const { openid, lang } = storeToRefs(main)

const title = ref('用户设置')
//const currentDate = ref('')
const date = ref('')
const member = ref({})

onLoad(() => {
	member.value = main.member;
	//this.$util
	member.value.createTime = formatDateTime(member.value.createTime);
})	
onShow(() => {
	date.value = getDate({format: true})
})

const startDate = computed(() => { 
	return getDate('start');
})
const endDate = computed(() => { 
	return getDate('end');
})

const chooseavatar = (e) => {
	console.log('detal:',e.detail);
	uni.uploadFile({
		url: VUE_APP_UPLOAD_URL, 
		filePath: e.detail.avatarUrl,
		name: 'file',
		header: {
			Authorization: 'Bearer ' + member.value.accessToken,
			lang: lang.value,
			'content-type': 'application/x-www-form-urlencoded;charset=UTF-8'
		},
		success(uploadFileResult){
			console.log('uploadFileResult:',uploadFileResult)
			if (uploadFileResult) {
				const upload = JSON.parse(uploadFileResult.data);
				console.log('upload:',upload.data)
				member.value.avatar = upload.data;
			}
		}, 
		fail(e){
			console.log('网络链接错误');
			console.log(e)
		}
	});
}
const getDate = (type) => {
	const date = new Date();
	let year = date.getFullYear();
	let month = date.getMonth() + 1;
	let day = date.getDate();

	if (type === 'start') {
		year = year - 60;
	} else if (type === 'end') {
		year = year + 2;
	}
	month = month > 9 ? month : '0' + month;;
	day = day > 9 ? day : '0' + day;
	return `${year}-${month}-${day}`;
}
const handleDateChange = (e) => {
	member.value.birthday = e.target.value
}
const save = async() => {
	let data = await userEdit({
		nickname: member.value.nickname,
		mobile: member.value.mobile,
		gender: member.value.gender,
		birthday: member.value.birthday,
		avatar: member.value.avatar
	});
	if (data) {
		const member2 = Object.assign(main.member, member.value)
		main.SET_MEMBER(member2)	
	}
	
}
	
</script>

<style lang="scss" scoped>
page {
	height: 100%;
}

.container {
	padding: 20rpx 30rpx;
}

.form {
	border-radius: 8rpx;
}

.form-input {
	.label {
		width: 160rpx;
		font-size: $font-size-base;
		color: $text-color-base;
	}
	
	.input {
	}
	
	.radio-group {
		display: flex;
		justify-content: flex-start;
		
		.radio {
			padding: 10rpx 30rpx;
			border-radius: 6rpx;
			border: 2rpx solid $text-color-assist;
			color: $text-color-assist;
			font-size: $font-size-base;
			
			&.checked {
				background-color: $color-primary;
				color: $text-color-white;
				border: 2rpx solid $color-primary;
			}
		}
	}
}

.btn-box {
	height: calc((100vh - 40rpx) / 2);
}
.save-btn {
	width: 90%;
	border-radius: 50rem !important;
	font-size: $font-size-lg;
}
.logout-btn {
	width: 90%;
	border-radius: 50rem !important;
	font-size: $font-size-lg;
	background-color:rgb(237, 81, 29);
}
</style>

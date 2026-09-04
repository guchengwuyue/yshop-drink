<template>
	<uv-navbar
	  :fixed="false"
	  :title="title"
	  left-arrow
	  @leftClick="$onClickLeft"
	/>
	<view class="userinfo-page d-flex flex-column">
		<view class="flex-fill userinfo-page__form">
			<list-cell :hover="false">
				<view class="userinfo-page__field w-100 d-flex align-items-center">
					<view class="userinfo-page__label">头像</view>
					<view class="userinfo-page__input flex-fill">
						<view class="userinfo-page__field userinfo-page__field--relative w-100 d-flex align-items-center">
							<view class="userinfo-page__avatar-row d-flex align-items-center">
								<view class="mr-1">
									<uv-avatar :src="member.avatar" size="60"></uv-avatar>
								</view>
								<view v-if="!member.avatar" class="flex-1 userinfo-page__change-avatar">
									<button size='mini' open-type="chooseAvatar" @chooseavatar="chooseavatar" type="success">更改头像</button>
								</view>
							</view>
						</view>
					</view>
				</view>
				
			</list-cell>
			<list-cell :hover="false">
				<view class="userinfo-page__field w-100 d-flex align-items-center">
					<view class="userinfo-page__label">昵称</view>
					<view class="userinfo-page__input flex-fill">
						<input type="nickname" placeholder="请填写昵称" placeholder-class="text-color-assist font-size-base" 
						v-model="member.nickname">
					</view>
				</view>
			</list-cell>
			<list-cell :hover="false">
				<view class="userinfo-page__field userinfo-page__field--relative w-100 d-flex align-items-center">
					<view class="userinfo-page__label">手机号码</view>
					<view class="userinfo-page__input flex-fill">
						<input type="text" v-model="member.mobile" disabled>
					</view>
				</view>
			</list-cell>
			<list-cell :hover="false">
				<view class="userinfo-page__field w-100 d-flex align-items-center">
					<view class="userinfo-page__label">性别</view>
					<view class="userinfo-page__input flex-fill">
						<view class="userinfo-page__radio-group">
							<view
								class="userinfo-page__radio"
								:class="{ 'userinfo-page__radio--checked': member.gender == '0' }"
								@tap="member.gender=0"
							>先生</view>
							<view
								class="userinfo-page__radio"
								:class="{ 'userinfo-page__radio--checked': member.gender == '1' }"
								@tap="member.gender=1"
							>女士</view>
						</view>
					</view>
				</view>
			</list-cell>
			<list-cell :hover="false" :arrow="!member.birthday">
				<view class="userinfo-page__field w-100 d-flex align-items-center">
					<view class="userinfo-page__label">生日</view>
					<view class="userinfo-page__input flex-fill">
						<picker mode="date" :value="member.birthday" :start="startDate" :end="endDate" @change="handleDateChange">
							{{member.birthday ? member.birthday : '无'}}
						</picker>
					</view>
				</view>
			</list-cell>
			<list-cell :hover="false" last>
				<view class="userinfo-page__field w-100 d-flex align-items-center">
					<view class="userinfo-page__label">加入时间</view>
					<view class="userinfo-page__input flex-fill">
						<input type="text" v-model="member.createTime" disabled>
					</view>
				</view>
			</list-cell>
		</view>
		<view class="userinfo-page__actions d-flex align-items-center just-content-center">
			<button type="primary" class="userinfo-page__save-btn" @tap="save">保存</button>
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
// 用户设置页局部 token（与 uni.scss 全局变量配合）
$userinfo-padding-y: 20rpx;
$userinfo-padding-x: $spacing-row-lg;
$userinfo-form-radius: 8rpx;
$userinfo-label-width: 160rpx;
$userinfo-radio-gap: 10rpx;
$userinfo-radio-padding-y: 10rpx;
$userinfo-radio-padding-x: 30rpx;
$userinfo-radio-border-width: 2rpx;
$userinfo-avatar-btn-height: 60rpx;
$userinfo-save-btn-width: 90%;
$userinfo-save-btn-radius: 50rem;

page {
	height: 100%;
}

.userinfo-page {
	--userinfo-padding-y: #{$userinfo-padding-y};
	--userinfo-padding-x: #{$userinfo-padding-x};
	--userinfo-label-width: #{$userinfo-label-width};
	--userinfo-radio-gap: #{$userinfo-radio-gap};
	--userinfo-avatar-btn-height: #{$userinfo-avatar-btn-height};
	--userinfo-save-btn-width: #{$userinfo-save-btn-width};

	padding: var(--userinfo-padding-y) var(--userinfo-padding-x);

	&__form {
		border-radius: $userinfo-form-radius;
	}

	&__field {
		&--relative {
			position: relative;
		}
	}

	&__label {
		width: var(--userinfo-label-width);
		font-size: $font-size-base;
		color: $text-color-base;
	}

	&__radio-group {
		display: flex;
		justify-content: flex-start;
		gap: var(--userinfo-radio-gap);
	}

	&__radio {
		padding: $userinfo-radio-padding-y $userinfo-radio-padding-x;
		border-radius: $border-radius-base;
		border: $userinfo-radio-border-width solid $text-color-assist;
		color: $text-color-assist;
		font-size: $font-size-base;

		&--checked {
			background-color: $color-primary;
			color: $text-color-white;
			border-color: $color-primary;
		}
	}

	&__actions {
		height: calc((100vh - var(--userinfo-padding-y) * 2) / 2);
	}

	&__save-btn {
		width: var(--userinfo-save-btn-width);
		border-radius: $userinfo-save-btn-radius !important;
		font-size: $font-size-lg;
	}

	&__change-avatar {
		right: 0;
		height: var(--userinfo-avatar-btn-height);

		button {
			background-color: $color-primary;
			color: $text-color-white;
		}
	}
}
</style>

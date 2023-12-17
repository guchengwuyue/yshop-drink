<template>
	<layout>
		<uv-navbar
		  :fixed="false"
		  :title="title"
		  left-arrow
		  @leftClick="$onClickLeft"
		/>
		<view>
			<rich-text :nodes="content"></rich-text>
		</view>
	</layout>
</template>

<script setup>
import {
  ref
} from 'vue'
import { onLoad,onShow} from '@dcloudio/uni-app'
import {
  mineServiceContent
} from '@/api/user'


const title = ref('内容')
const content = ref('')

onLoad((option) => {
	if (option.name) {
		title.value = option.name
	}
	if (option.id) {
		getContent(option.id);
	}
})	
const getContent = async(id) => {
	let data = await mineServiceContent({id:id});
	if (data) {
		content.value = data.content;
	}
}


</script>

<style lang="scss" scoped>
	
</style>

<template>
	<uv-popup ref="popup" @change="change">
		<view class="video-view" v-if="show">
			<video class="video" :src="getSec" :autoplay="autoplay"></video>
		</view>
	</uv-popup>
</template>
<script>
	export default {
		props: {
			src: {
				type: String,
				default: ''
			},
			autoplay: {
				type: Boolean,
				default: true
			}
		},
		data() {
			return {
				videoSrc: '',
				show: false
			}
		},
		computed: {
			getSec() {
				return this.src || this.videoSrc;
			}
		},
		methods: {
			open(url) {
				this.videoSrc = url;
				this.$refs.popup.open();
			},
			close() {
				this.$refs.popup.close();
			},
			change(e) {
				this.show = e.show;
			}
		}
	}
</script>
<style scoped lang="scss">
	.video-view {
		width: 750rpx;
		.video {
			width: 750rpx;
		}
	}
</style>
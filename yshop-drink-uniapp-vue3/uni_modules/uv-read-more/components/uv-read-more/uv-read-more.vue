<template>
	<view class="uv-read-more">
		<view
		    class="uv-read-more__content"
		    :style="{
				height: isLongContent && status === 'close' ? $uv.addUnit(showHeight) : $uv.addUnit(contentHeight,'px'),
				textIndent: textIndent
			}"
		>
			<view
			    class="uv-read-more__content__inner"
			    ref="uv-read-more__content__inner"
			    :class="[elId]"
			>
				<slot></slot>
			</view>
		</view>
		<view
		    class="uv-read-more__toggle"
		    :style="[innerShadowStyle]"
		    v-if="isLongContent"
		>
			<slot name="toggle">
				<view
				    class="uv-read-more__toggle__text"
				    @tap="toggleReadMore"
				>
					<uv-text
					    :text="status === 'close' ? closeText : openText"
					    :color="color"
					    :size="fontSize"
					    :lineHeight="fontSize"
					    margin="0 5px 0 0"
					></uv-text>
					<view class="uv-read-more__toggle__icon">
						<uv-icon
						    :color="color"
						    :size="fontSize + 2"
						    :name="status === 'close' ? 'arrow-down' : 'arrow-up'"
						></uv-icon>
					</view>
				</view>
			</slot>
		</view>
	</view>
</template>

<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	// #ifdef APP-NVUE
	const dom = uni.requireNativePlugin('dom')
	// #endif
	import props from './props.js';
	/**
	 * readMore 阅读更多
	 * @description 该组件一般用于内容较长，预先收起一部分，点击展开全部内容的场景。
	 * @tutorial https://www.uvui.cn/components/readMore.html
	 * @property {String | Number}	showHeight	内容超出此高度才会显示展开全文按钮，单位px（默认 400 ）
	 * @property {Boolean}			toggle		展开后是否显示收起按钮（默认 false ）
	 * @property {String}			closeText	关闭时的提示文字（默认 '展开阅读全文' ）
	 * @property {String}			openText	展开时的提示文字（默认 '收起' ）
	 * @property {String}			color		提示文字的颜色（默认 '#2979ff' ）
	 * @property {String | Number}	fontSize	提示文字的大小，单位px （默认 14 ）
	 * @property {Object}			shadowStyle	显示阴影的样式
	 * @property {String}			textIndent	段落首行缩进的字符个数 （默认 '2em' ）
	 * @property {String | Number}	name		用于在 open 和 close 事件中当作回调参数返回
	 * @event {Function} open 内容被展开时触发
	 * @event {Function} close 内容被收起时触发
	 * @example <uv-read-more><rich-text :nodes="content"></rich-text></uv-read-more>
	 */
	export default {
		name: 'uv-read-more',
		mixins: [mpMixin, mixin, props],
		data() {
			return {
				isLongContent: false, // 是否需要隐藏一部分内容
				status: 'close', // 当前隐藏与显示的状态，close-收起状态，open-展开状态
				elId: '', // 生成唯一class
				contentHeight: 100, // 内容高度
			}
		},
		computed: {
			// 展开后无需阴影，收起时才需要阴影样式
			innerShadowStyle() {
				if (this.status === 'open') return {}
				else return this.shadowStyle
			}
		},
		created() {
			this.elId = this.$uv.guid();
		},
		mounted() {
			this.init()
		},
		methods: {
			async init() {
				this.getContentHeight().then(height => {
					this.contentHeight = height
					// 判断高度，如果真实内容高度大于占位高度，则显示收起与展开的控制按钮
					if (height > this.$uv.getPx(this.showHeight)) {
						this.isLongContent = true
						this.status = 'close'
					}
				})
			},
			// 获取内容的高度
			async getContentHeight() {
				// 延时一定时间再获取节点
				await this.$uv.sleep(30)
				return new Promise(resolve => {
					// #ifndef APP-NVUE
					this.$uvGetRect('.' + this.elId).then(res => {
						resolve(res.height)
					})
					// #endif

					// #ifdef APP-NVUE
					const ref = this.$refs['uv-read-more__content__inner']
					dom.getComponentRect(ref, (res) => {
						resolve(res.size.height)
					})
					// #endif
				})
			},
			// 展开或者收起
			toggleReadMore() {
				this.status = this.status === 'close' ? 'open' : 'close'
				// 如果toggle为false，隐藏"收起"部分的内容
				if (this.toggle == false) this.isLongContent = false
				// 发出打开或者收齐的事件
				this.$emit(this.status, this.name)
			}
		}
	}
</script>

<style lang="scss" scoped>
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
	@import '@/uni_modules/uv-ui-tools/libs/css/color.scss';
.uv-read-more {

	&__content {
		overflow: hidden;
		color: $uv-content-color;
		font-size: 15px;
		text-align: left;
	}

	&__toggle {
		@include flex;
		justify-content: center;

		&__text {
			@include flex;
			align-items: center;
			justify-content: center;
			margin-top: 5px;
		}
	}
}
</style>

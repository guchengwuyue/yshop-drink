<template>
	<!-- #ifndef APP-NVUE -->
	<uv-popup 
		ref="pickerColorPopup"
		mode="bottom"
		:close-on-click-overlay="closeOnClickOverlay"
		@change="popupChange">
		<view class="uv-pick-color">
			<uv-toolbar
				:show="showToolbar"
				:cancelColor="cancelColor"
				:confirmColor="confirmColor"
				:cancelText="cancelText"
				:confirmText="confirmText"
				:title="title"
				:show-border="true"
				@cancel="cancelHandler"
				@confirm="confirmHandler"></uv-toolbar>
			<view 
				class="uv-pick-color__box"
				:style="{
					background:`rgb(${bgcolor.r},${bgcolor.g},${bgcolor.b})`
				}"
			>
				<!-- #ifdef H5 -->
				<view
					class="uv-pick-color__box__bg drag-box"
					@tap.stop.prevent="touchstart($event, 0)"
					@touchstart.stop.prevent="touchstart($event, 0)"
					@touchmove.stop.prevent="touchmove($event, 0)"
					@touchend.stop.prevent="touchend($event, 0)"
				>
				<!-- #endif -->
				<!-- #ifndef H5 -->
				<view
					class="uv-pick-color__box__bg drag-box"
					@touchstart.stop.prevent="touchstart($event, 0)"
					@touchmove.stop.prevent="touchmove($event, 0)"
					@touchend.stop.prevent="touchend($event, 0)"
				>
				<!-- #endif -->
					<view class="uv-pick-color__box__bg-mask"></view>
					<view 
						class="uv-pick-color__box__bg-pointer"
						:style="[pointerStyle]"
					>
					</view>
				</view>
			</view>
			<view class="uv-pick-color__control">
				<view class="uv-pick-color__control__alpha">
					<view 
						class="uv-pick-color__control__alpha--color"
						:style="{ background:`rgba(${rgba.r},${rgba.g},${rgba.b},${rgba.a})` }"
					></view>
				</view>
				<view class="uv-pick-color__control__item">
					<!-- #ifdef H5 -->
					<view
						class="uv-pick-color__control__item__drag drag-box"
						@tap.stop="touchstart($event, 1)"
						@touchstart.stop="touchstart($event, 1)"
						@touchmove.stop="touchmove($event, 1)"
						@touchend.stop="touchend($event, 1)"
					>
					<!-- #endif -->
					<!-- #ifndef H5 -->
					<view
						class="uv-pick-color__control__item__drag drag-box"
						@touchstart.stop="touchstart($event, 1)"
						@touchmove.stop="touchmove($event, 1)"
						@touchend.stop="touchend($event, 1)"
					>
					<!-- #endif -->
						<view class="uv-pick-color__control__item__drag--hue"></view>
						<view 
							class="uv-pick-color__control__item__drag--circle"
							:style="{ 
								left: $uv.getPx(site[1].left - 10,true)
							}"
						></view>
					</view>
					<!-- #ifdef H5 -->
					<view
						class="uv-pick-color__control__item__drag drag-box"
						@tap.stop="touchstart($event, 2)"
						@touchstart.stop="touchstart($event, 2)"
						@touchmove.stop="touchmove($event, 2)"
						@touchend.stop="touchend($event, 2)"
					>
					<!-- #endif -->
					<!-- #ifndef H5 -->
					<view
						class="uv-pick-color__control__item__drag drag-box"
						@touchstart.stop="touchstart($event, 2)"
						@touchmove.stop="touchmove($event, 2)"
						@touchend.stop="touchend($event, 2)"
					>
					<!-- #endif -->
						<view class="uv-pick-color__control__item__drag--alpha"></view>
						<view 
							class="uv-pick-color__control__item__drag--circle"
							:style="{ 
								left: $uv.getPx(site[2].left - 10,true)
							}"
						></view>
					</view>
				</view>
			</view>
			<view class="uv-pick-color__result">
				<view 
					class="uv-pick-color__result__select"
					hover-class="uv-hover-class"
					@click.stop="select"
				>
					<text class="text">切换</text>
					<text class="text">模式</text>
				</view>
				<view class="uv-pick-color__result__item" v-if="mode">
					<view class="uv-pick-color__result__item--value uv-border">
						<text>{{hex}}</text>
					</view>
					<view class="uv-pick-color__result__item--hex">
						<text>HEX</text>
					</view>
				</view>
				<template v-else>
					<view class="uv-pick-color__result__item">
						<view class="uv-pick-color__result__item--value uv-border">
							<text>{{rgba.r}}</text>
						</view>
						<view class="uv-pick-color__result__item--rgba">
							<text>R</text>
						</view>
					</view>
					<view class="uv-pick-color__result__gap"></view>
					<view class="uv-pick-color__result__item">
						<view class="uv-pick-color__result__item--value uv-border">
							<text>{{rgba.g}}</text>
						</view>
						<view class="uv-pick-color__result__item--rgba">
							<text>G</text>
						</view>
					</view>
					<view class="uv-pick-color__result__gap"></view>
					<view class="uv-pick-color__result__item">
						<view class="uv-pick-color__result__item--value uv-border">
							<text>{{rgba.b}}</text>
						</view>
						<view class="uv-pick-color__result__item--rgba">
							<text>B</text>
						</view>
					</view>
					<view class="uv-pick-color__result__gap"></view>
					<view class="uv-pick-color__result__item">
						<view class="uv-pick-color__result__item--value uv-border">
							<text>{{rgba.a}}</text>
						</view>
						<view class="uv-pick-color__result__item--rgba">
							<text>A</text>
						</view>
					</view>
				</template>
			</view>
			<view class="uv-pick-color__prefab">
				<view 
					class="uv-pick-color__prefab__item"
					v-for="(item,index) in colorList"
					:key="index"
				>
					<view 
						class="uv-pick-color__prefab__item--color"
						:style="{ background:`rgba(${item.r},${item.g},${item.b},${item.a})` }"
						@click.stop="setColorBySelect(item)"
					></view>
				</view>
			</view>
		</view>
	</uv-popup>
	<!-- #endif -->
	<!-- #ifdef APP-NVUE -->
	<view>
		<text>nvue暂不支持uv-pick-color组件</text>
	</view>
	<!-- #endif -->
</template>
<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js';
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js';
	import { rgbToHsb, hsbToRgb, rgbToHex, colorList } from './colors.js';
	import props from './props.js';
	export default {
		name: 'uv-pick-color',
		emits: ['confirm', 'cancel', 'close', 'change'],
		mixins: [mpMixin, mixin, props],
		computed: {
			pointerStyle() {
				const style = {};
				style.top = this.$uv.addUnit(this.site[0].top - 8);
				style.left = this.$uv.addUnit(this.site[0].left - 8);
				return style;
			}
		},
		data() {
			return {
				showToolbar: false,
				// rgba 颜色
				rgba: {
					r: 0,
					g: 0,
					b: 0,
					a: 1
				},
				// hsb 颜色
				hsb: {
					h: 0,
					s: 0,
					b: 0
				},
				site: [{
					top: 0,
					left: 0
				}, {
					left: 0
				}, {
					left: 0
				}],
				index: 0,
				bgcolor: {
					r: 255,
					g: 0,
					b: 0,
					a: 1
				},
				hex: '#000000',
				mode: true,
				colorList: colorList
			};
		},
		watch: {
			prefabColor(newVal) {
				this.colorList = newVal;
			}
		},
		created() {
			// #ifdef APP-NVUE
			return this.$uv.error('nvue暂不支持uv-pick-color组件');
			// #endif
			this.rgba = this.color;
			if (this.prefabColor.length) this.colorList = this.prefabColor;
		},
		methods: {
			open() {
				this.$refs.pickerColorPopup.open();
				this.showToolbar = true;
				this.$nextTick(async () => {
					await this.$uv.sleep(350);
					this.getSelectorQuery();
				})
			},
			close() {
				this.$refs.pickerColorPopup.close();
			},
			popupChange(e) {
				if(!e.show) this.$emit('close');
			},
			// 点击工具栏的取消按钮
			cancelHandler() {
				this.$emit('cancel');
				this.close();
			},
			// 点击工具栏的确定按钮
			confirmHandler() {
				this.$emit('confirm', {
					rgba: this.rgba,
					hex: this.hex
				})
				this.close();
			},
			// 初始化
			init() {
				// hsb 颜色
				this.hsb = rgbToHsb(this.rgba);
				this.setValue(this.rgba);
			},
			async getSelectorQuery() {
				const data = await this.$uvGetRect('.drag-box',true);
				this.position = data;
				this.setColorBySelect(this.rgba);
			},
			// 选择模式
			select() {
				this.mode = !this.mode;
			},
			touchstart(e, index) {
				const { clientX, clientY } = e.touches[0];
				this.pageX = clientX;
				this.pageY = clientY;
				this.setPosition(clientX, clientY, index);
			},
			touchmove(e, index) {
				const { clientX, clientY } = e.touches[0];
				this.moveX = clientX;
				this.moveY = clientY;
				this.setPosition(clientX, clientY, index);
			},
			touchend(e, index) {},
			/**
			 * 设置位置
			 */
			setPosition(x, y, index) {
				this.index = index;
				const { top, left, width, height } = this.position[index];
				// 设置最大最小值
				this.site[index].left = Math.max(0, Math.min(parseInt(x - left), width));
				if (index === 0) {
					this.site[index].top = Math.max(0, Math.min(parseInt(y - top), height));
					// 设置颜色
					this.hsb.s = parseInt((100 * this.site[index].left) / width);
					this.hsb.b = parseInt(100 - (100 * this.site[index].top) / height);
					this.setColor();
					this.setValue(this.rgba);
				} else {
					this.setControl(index, this.site[index].left);
				}
			},
			/**
			 * 设置 rgb 颜色
			 */
			setColor() {
				const rgb = hsbToRgb(this.hsb);
				this.rgba.r = rgb.r;
				this.rgba.g = rgb.g;
				this.rgba.b = rgb.b;
			},
			/**
			 * 设置二进制颜色
			 * @param {Object} rgb
			 */
			setValue(rgb) {
				this.hex = `#${(rgbToHex(rgb))}`;
			},
			setControl(index, x) {
				const {
					top,
					left,
					width,
					height
				} = this.position[index];

				if (index === 1) {
					this.hsb.h = parseInt((360 * x) / width);
					this.bgcolor = hsbToRgb({
						h: this.hsb.h,
						s: 100,
						b: 100
					});
					this.setColor()
				} else {
					this.rgba.a = +(x / width).toFixed(1);
				}
				this.setValue(this.rgba);
			},
			setColorBySelect(getrgb) {
				const { r, g, b, a } = getrgb;
				let rgb = {};
				rgb = {
					r: r ? parseInt(r) : 0,
					g: g ? parseInt(g) : 0,
					b: b ? parseInt(b) : 0,
					a: a ? a : 0
				};
				this.rgba = rgb;
				this.hsb = rgbToHsb(rgb);
				this.changeViewByHsb();
			},
			changeViewByHsb() {
				const [a, b, c] = this.position;
				this.site[0].left = parseInt(this.hsb.s * a.width / 100);
				this.site[0].top = parseInt((100 - this.hsb.b) * a.height / 100);
				this.setColor(this.hsb.h);
				this.setValue(this.rgba);
				this.bgcolor = hsbToRgb({
					h: this.hsb.h,
					s: 100,
					b: 100
				});
				this.site[1].left = this.hsb.h / 360 * b.width;
				this.site[2].left = this.rgba.a * c.width;
			}
		}
	};
</script>
<style scoped lang="scss">
	$show-border: 1;
	@import '@/uni_modules/uv-ui-tools/libs/css/variable.scss';
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
	$height: 400rpx;
	/* #ifndef APP-NVUE */
	.uv-pick-color {
		&__box {
			position: relative;
			height: $height;
			background: rgb(255, 0, 0);
			margin: 20rpx;
			&__bg {
				position: absolute;
				top: 0;
				left: 0;
				right: 0;
				bottom: 0;
				background: linear-gradient(to right, #fff, rgba(255, 255, 255, 0));
				&-mask {
					position: absolute;
					top: 0;
					left: 0;
					right: 0;
					bottom: 0;
					height: $height;
					background: linear-gradient(to top, #000, rgba(0, 0, 0, 0));
				}
				&-pointer {
					position: absolute;
					top: -8px;
					left: -8px;
					z-index: 2;
					width: 16px;
					height: 16px;
					border: 1px #fff solid;
					border-radius: 8px;
				}
			}
		}
		&__control {
			@include flex;
			padding: 10rpx 20rpx;
			&__alpha {
				width: 100rpx;
				height: 100rpx;
				border-radius: 50rpx;
				background-color: #fff;
				background-image: linear-gradient(45deg, #eee 25%, transparent 25%, transparent 75%, #eee 75%, #eee),linear-gradient(45deg, #eee 25%, transparent 25%, transparent 75%, #eee 75%, #eee);
				background-size: 36rpx 36rpx;
				background-position: 0 0, 18rpx 18rpx;
				border: 1px #eee solid;
				overflow: hidden;
				&--color {
					width: 100%;
					height: 100%;
				}
			}
			&__item {
				flex: 1;
				@include flex(column);
				justify-content: space-between;
				height: 100rpx;
				padding: 6rpx 0 6rpx 30rpx;
			}
			&__item__drag {
				position: relative;
				height: 16px;
				background-color: #fff;
				background-image: linear-gradient(45deg, #eee 25%, transparent 25%, transparent 75%, #eee 75%, #eee),
				linear-gradient(45deg, #eee 25%, transparent 25%, transparent 75%, #eee 75%, #eee);
				background-size: 32rpx 32rpx;
				background-position: 0 0, 16rpx 16rpx;
				&--hue {
					width: 100%;
					height: 100%;
					background: linear-gradient(to right, #f00 0%, #ff0 17%, #0f0 33%, #0ff 50%, #00f 67%, #f0f 83%, #f00 100%);
				}
				&--alpha {
					width: 100%;
					height: 100%;
					background: linear-gradient(to right, rgba(0, 0, 0, 0) 0%, rgb(0, 0, 0));
				}
				&--circle {
					position: absolute;
					top: -2px;
					width: 20px;
					height: 20px;
					box-sizing: border-box;
					border-radius: 10px;
					background: #fff;
					box-shadow: 0 0 2px 1px rgba(0, 0, 0, 0.1);
				}
			}
		}
		&__result {
			@include flex;
			padding: 20rpx;
			text-align: center;
			&__select {
				@include flex(column);
				justify-content: center;
				width: 100rpx;
				height: 100rpx;
				margin-right: 10px;
				border-radius: 10rpx;
				box-shadow: 1px 1px 2px 1px rgba(0, 0, 0, 0.1);
				font-size: 14px;
				color: #999;
			}
			&__item {
				flex: 1;
				height: 100rpx;
				&--value {
					height: 50rpx;
					line-height: 50rpx;
					border-radius: 4rpx;
					font-size: 28rpx;
					color: #999;
				}
				&--hex,
				&--rgba {
					height: 50rpx;
					line-height: 50rpx;
					font-size: 30rpx;
				}
			}
			&__gap {
				width: 10px;
				height: 10px;
			}
		}
		&__prefab {
			@include flex;
			margin: 0 -8rpx;
			padding: 0 20rpx 20rpx;
			flex-wrap: wrap;
			&__item {
				width: 50rpx;
				height: 50rpx;
				margin: 8rpx;
				border-radius: 6rpx;
				background-color: #fff;
				background-image: linear-gradient(45deg, #eee 25%, transparent 25%, transparent 75%, #eee 75%, #eee),
					linear-gradient(45deg, #eee 25%, transparent 25%, transparent 75%, #eee 75%, #eee);
				background-size: 36rpx 36rpx;
				background-position: 0 0, 18rpx 18rpx;
				border: 1px #eee solid;
				&--color {
					width: 100%;
					height: 100%;
					border-radius: 6rpx;
				}
			}
		}
	}
	/* #endif */
</style>
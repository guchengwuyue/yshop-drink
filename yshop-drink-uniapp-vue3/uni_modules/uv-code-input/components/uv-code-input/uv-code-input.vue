<template>
	<view class="uv-code-input">
		<view
			class="uv-code-input__item"
			:style="[itemStyle(index)]"
			v-for="(item, index) in codeLength"
			:key="index"
		>
			<view
				class="uv-code-input__item__dot"
				v-if="dot && codeArray.length > index"
			></view>
			<text
				v-else
				:style="{
					fontSize: $uv.addUnit(fontSize),
					fontWeight: bold ? 'bold' : 'normal',
					color: color
				}"
			>{{codeArray[index]}}</text>
			<view
				class="uv-code-input__item__line"
				v-if="mode === 'line'"
				:style="[lineStyle]"
			></view>
			<!-- #ifndef APP-PLUS -->
			<view v-if="isFocus && codeArray.length === index" :style="{backgroundColor: color}" class="uv-code-input__item__cursor"></view>
			<!-- #endif -->
		</view>
		<input
			:disabled="disabledKeyboard"
			type="number"
			:focus="focus"
			:value="inputValue"
			:maxlength="maxlength"
			:adjustPosition="adjustPosition"
			class="uv-code-input__input"
			@input="inputHandler"
			:style="{
				height: $uv.addUnit(size) 
			}"
			@focus="isFocus = true"
			@blur="isFocus = false"
		/>
	</view>
</template>

<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	import props from './props.js';
	/**
	 * CodeInput 验证码输入
	 * @description 该组件一般用于验证用户短信验证码的场景，也可以结合uvui的键盘组件使用
	 * @tutorial https://www.uvui.cn/components/codeInput.html
	 * @property {String | Number}	value / v-model				预置值
	 * @property {String | Number}	maxlength			最大输入长度 （默认 6 ）
	 * @property {Boolean}			dot					是否用圆点填充 （默认 false ）
	 * @property {String}			mode				显示模式，box-盒子模式，line-底部横线模式 （默认 'box' ）
	 * @property {Boolean}			hairline			是否细边框 （默认 false ）
	 * @property {String | Number}	space				字符间的距离 （默认 10 ）
	 * @property {Boolean}			focus				是否自动获取焦点 （默认 false ）
	 * @property {Boolean}			bold				字体和输入横线是否加粗 （默认 false ）
	 * @property {String}			color				字体颜色 （默认 '#606266' ）
	 * @property {String | Number}	fontSize			字体大小，单位px （默认 18 ）
	 * @property {String | Number}	size				输入框的大小，宽等于高 （默认 35 ）
	 * @property {Boolean}			disabledKeyboard	是否隐藏原生键盘，如果想用自定义键盘的话，需设置此参数为true （默认 false ）
	 * @property {String}			borderColor			边框和线条颜色 （默认 '#c9cacc' ）
	 * @property {Boolean}			disabledDot			是否禁止输入"."符号 （默认 true ）
	 * 
	 * @event {Function}	change	输入内容发生改变时触发，具体见上方说明			value：当前输入的值
	 * @event {Function}	finish	输入字符个数达maxlength值时触发，见上方说明	value：当前输入的值
	 * @example	<uv-code-input v-model="value4" :focus="true"></uv-code-input>
	 */
	export default {
		name: 'uv-code-input',
		mixins: [mpMixin, mixin, props],
		data() {
			return {
				inputValue: '',
				isFocus: this.focus
			}
		},
		created() {
			const value = String(this.value) || String(this.modelValue);
			this.inputValue = String(value).substring(0, this.maxlength);
		},
		watch: {
			value(newVal) {
				// 转为字符串，超出部分截掉
				this.inputValue = String(newVal).substring(0, this.maxlength);
				if (this.disabledKeyboard) {
					this.customInput();
				}
			},
			modelValue(newVal) {
				// 转为字符串，超出部分截掉
				this.inputValue = String(newVal).substring(0, this.maxlength);
				if (this.disabledKeyboard) {
					this.customInput();
				}
			}
		},
		computed: {
			// 根据长度，循环输入框的个数，因为头条小程序数值不能用于v-for
			codeLength() {
				return new Array(Number(this.maxlength))
			},
			// 循环item的样式
			itemStyle() {
				return index => {
					const addUnit = this.$uv.addUnit
					const style = {
						width: addUnit(this.size),
						height: addUnit(this.size)
					}
					// 盒子模式下，需要额外进行处理
					if (this.mode === 'box') {
						// 设置盒子的边框，如果是细边框，则设置为0.5px宽度
						style.border = `${this.hairline ? 0.5 : 1}px solid ${this.borderColor}`
						// 如果盒子间距为0的话
						if (this.$uv.getPx(this.space) === 0) {
							// 给第一和最后一个盒子设置圆角
							if (index === 0) {
								style.borderTopLeftRadius = '3px'
								style.borderBottomLeftRadius = '3px'
							}
							if (index === this.codeLength.length - 1) {
								style.borderTopRightRadius = '3px'
								style.borderBottomRightRadius = '3px'
							}
							// 最后一个盒子的右边框需要保留
							if (index !== this.codeLength.length - 1) {
								style.borderRight = 'none'
							}
						}
					}
					if (index !== this.codeLength.length - 1) {
						// 设置验证码字符之间的距离，通过margin-right设置，最后一个字符，无需右边框
						style.marginRight = addUnit(this.space)
					} else {
						// 最后一个盒子的有边框需要保留
						style.marginRight = 0
					}
					return style
				}
			},
			// 将输入的值，转为数组，给item历遍时，根据当前的索引显示数组的元素
			codeArray() {
				return String(this.inputValue).split('')
			},
			// 下划线模式下，横线的样式
			lineStyle() {
				const style = {}
				style.height = this.hairline ? '1px' : '4px'
				style.width = this.$uv.addUnit(this.size)
				// 线条模式下，背景色即为边框颜色
				style.backgroundColor = this.borderColor
				return style
			}
		},
		methods: {
			// 监听输入框的值发生变化
			inputHandler(e) {
				const value = e.detail.value
				this.inputValue = value
				// 是否允许输入“.”符号
				if (this.disabledDot) {
					this.$nextTick(() => {
						this.inputValue = value.replace('.', '')
					})
				}
				// 未达到maxlength之前，发送change事件，达到后发送finish事件
				this.$emit('change', value)
				// 修改通过v-model双向绑定的值
				this.$emit('input', value)
				this.$emit('update:modelValue', value)
				// 达到用户指定输入长度时，发出完成事件
				if (String(value).length >= Number(this.maxlength)) {
					this.$emit('finish', value)
				}
			},
			// 自定义键盘输入值监听
			customInput() {
				const value = this.inputValue;
				// 是否允许输入“.”符号
				if (this.disabledDot) {
					this.$nextTick(() => {
						this.inputValue = value.replace('.', '')
					})
				}
				// 未达到maxlength之前，发送change事件，达到后发送finish事件
				this.$emit('change', value)
				// 达到用户指定输入长度时，发出完成事件
				if (String(value).length >= Number(this.maxlength)) {
					this.$emit('finish', value)
				}
			}
		}
	}
</script>
<style lang="scss" scoped>
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
	@import '@/uni_modules/uv-ui-tools/libs/css/color.scss';
	$uv-code-input-cursor-width: 1px;
	$uv-code-input-cursor-height: 40%;
	$uv-code-input-cursor-animation-duration: 1s;
	$uv-code-input-cursor-animation-name: uv-cursor-flicker;
	.uv-code-input {
		@include flex;
		position: relative;
		overflow: hidden;
		&__item {
			@include flex;
			justify-content: center;
			align-items: center;
			position: relative;
			&__text {
				font-size: 15px;
				color: $uv-content-color;
			}
			&__dot {
				width: 7px;
				height: 7px;
				border-radius: 100px;
				background-color: $uv-content-color;
			}
			&__line {
				position: absolute;
				bottom: 0;
				height: 4px;
				border-radius: 100px;
				width: 40px;
				background-color: $uv-content-color;
			}
			/* #ifndef APP-PLUS */
			&__cursor {
				position: absolute;
				top: 50%;
				left: 50%;
				transform: translate(-50%, -50%);
				width: $uv-code-input-cursor-width;
				height: $uv-code-input-cursor-height;
				animation: $uv-code-input-cursor-animation-duration uv-cursor-flicker infinite;
			}
			/* #endif */
		}
		&__input {
			// 之所以需要input输入框，是因为有它才能唤起键盘
			// 这里将它设置为两倍的屏幕宽度，再将左边的一半移出屏幕，为了不让用户看到输入的内容
			position: absolute;
			left: -750rpx;
			width: 1500rpx;
			top: 0;
			background-color: transparent;
			text-align: left;
		}
	}
	/* #ifndef APP-PLUS */
	@keyframes uv-cursor-flicker {
		0% {
			opacity: 0;
		}
		50% {
			opacity: 1;
		}
		100% {
			opacity: 0;
		}
	}
	/* #endif */
</style>
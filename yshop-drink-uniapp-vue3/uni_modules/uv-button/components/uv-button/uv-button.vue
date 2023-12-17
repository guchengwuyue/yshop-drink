<template>
	<view 
		class="uv-button-wrapper"
		:style="[btnWrapperStyle]"
	>
    <!-- #ifndef APP-NVUE -->
		<!-- #ifdef MP -->
		<!-- 为了解决微信小程序动态设置hover-class点击态不消失的BUG -->
		<view class="uv-button-wrapper--dis" v-if="disabled || loading"></view>
		<button
		  :hover-start-time="Number(hoverStartTime)"
		  :hover-stay-time="Number(hoverStayTime)"
		  :form-type="formType"
		  :open-type="openType"
		  :app-parameter="appParameter"
		  :hover-stop-propagation="hoverStopPropagation"
		  :send-message-title="sendMessageTitle"
		  :send-message-path="sendMessagePath"
		  :lang="lang"
		  :data-name="dataName"
		  :session-from="sessionFrom"
		  :send-message-img="sendMessageImg"
		  :show-message-card="showMessageCard"
		  @getphonenumber="onGetPhoneNumber"
		  @getuserinfo="onGetUserInfo"
		  @error="onError"
		  @opensetting="onOpenSetting"
		  @launchapp="onLaunchApp"
			@contact="onContact"
			@chooseavatar="onChooseavatar"
			@agreeprivacyauthorization="onAgreeprivacyauthorization"
			@addgroupapp="onAddgroupapp"
			@chooseaddress="onChooseaddress"
			@subscribe="onSubscribe"
			@login="onLogin"
			@im="onIm"
		  hover-class="uv-button--active"
		  class="uv-button uv-reset-button"
		  :style="[baseColor, $uv.addStyle(customStyle)]"
		  @tap="clickHandler"
		  :class="bemClass"
		>
		<!-- #endif -->
    <!-- #ifndef MP -->
    <button
      :hover-start-time="Number(hoverStartTime)"
      :hover-stay-time="Number(hoverStayTime)"
      :form-type="formType"
      :open-type="openType"
      :app-parameter="appParameter"
      :hover-stop-propagation="hoverStopPropagation"
      :send-message-title="sendMessageTitle"
      :send-message-path="sendMessagePath"
      :lang="lang"
      :data-name="dataName"
      :session-from="sessionFrom"
      :send-message-img="sendMessageImg"
      :show-message-card="showMessageCard"
      :hover-class="!disabled && !loading ? 'uv-button--active' : ''"
      class="uv-button uv-reset-button"
      :style="[baseColor, $uv.addStyle(customStyle)]"
      @tap="clickHandler"
      :class="bemClass"
    >
    <!-- #endif -->
      <template v-if="loading">
        <uv-loading-icon
          :mode="loadingMode"
          :size="loadingSize * 1.15"
          :color="loadingColor"
        ></uv-loading-icon>
          <text
            class="uv-button__loading-text"
            :style="[
							{ fontSize: textSize + 'px' },
							$uv.addStyle(customTextStyle)
						]"
          >{{ loadingText || text }}</text>
      </template>
      <template v-else>
        <uv-icon
          v-if="icon"
          :name="icon"
          :color="iconColorCom"
          :size="getIconSize"
          :customStyle="{ marginRight: '2px' }"
        ></uv-icon>
        <slot>
          <text
            class="uv-button__text"
            :style="[
							{ fontSize: textSize + 'px' },
							$uv.addStyle(customTextStyle)
						]"
            >{{ text }}</text>
        </slot>
				<slot name="suffix"></slot>
      </template>
    </button>
    <!-- #endif -->
    <!-- #ifdef APP-NVUE -->
    <view
      :hover-start-time="Number(hoverStartTime)"
      :hover-stay-time="Number(hoverStayTime)"
      class="uv-button"
      :hover-class="
        !disabled && !loading && !color && (plain || type === 'info')
          ? 'uv-button--active--plain'
          : !disabled && !loading && !plain
          ? 'uv-button--active'
          : ''
      "
      @tap="clickHandler"
      :class="bemClass"
      :style="[baseColor, $uv.addStyle(customStyle)]"
    >
      <template v-if="loading">
        <uv-loading-icon
          :mode="loadingMode"
          :size="loadingSize * 1.15"
          :color="loadingColor"
        ></uv-loading-icon>
        <text
          class="uv-button__loading-text"
          :style="[nvueTextStyle,$uv.addStyle(customTextStyle)]"
          :class="[plain && `uv-button__text--plain--${type}`]"
          >{{ loadingText || text }}</text>
      </template>
      <template v-else>
        <uv-icon
          v-if="icon"
          :name="icon"
          :color="iconColorCom"
          :size="getIconSize"
        ></uv-icon>
        <text
          class="uv-button__text"
          :style="[
            {
              marginLeft: icon ? '2px' : 0,
            },
            nvueTextStyle,
						$uv.addStyle(customTextStyle)
          ]"
          :class="[plain && `uv-button__text--plain--${type}`]"
          >{{ text }}</text>
				<slot name="suffix"></slot>
      </template>
    </view>
    <!-- #endif -->
	</view>
</template>

<script>
import throttle from '@/uni_modules/uv-ui-tools/libs/function/throttle.js';
import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
import button from '@/uni_modules/uv-ui-tools/libs/mixin/button.js'
import openType from '@/uni_modules/uv-ui-tools/libs/mixin/openType.js'
import props from "./props.js";
/**
 * button 按钮
 * @description Button 按钮
 * @tutorial https://www.uvui.cn/components/button.html
 * @property {Boolean}			hairline				是否显示按钮的细边框 (默认 true )
 * @property {String}			type					按钮的预置样式，info，primary，error，warning，success (默认 'info' )
 * @property {String}			size					按钮尺寸，large，normal，mini （默认 normal）
 * @property {String}			shape					按钮形状，circle（两边为半圆），square（带圆角） （默认 'square' ）
 * @property {Boolean}			plain					按钮是否镂空，背景色透明 （默认 false）
 * @property {Boolean}			disabled				是否禁用 （默认 false）
 * @property {Boolean}			loading					按钮名称前是否带 loading 图标(App-nvue 平台，在 ios 上为雪花，Android上为圆圈) （默认 false）
 * @property {String | Number}	loadingText				加载中提示文字
 * @property {String}			loadingMode				加载状态图标类型 （默认 'spinner' ）
 * @property {String | Number}	loadingSize				加载图标大小 （默认 15 ）
 * @property {String}			openType				开放能力，具体请看uniapp稳定关于button组件部分说明
 * @property {String}			formType				用于 <form> 组件，点击分别会触发 <form> 组件的 submit/reset 事件
 * @property {String}			appParameter			打开 APP 时，向 APP 传递的参数，open-type=launchApp时有效 （注：只微信小程序、QQ小程序有效）
 * @property {Boolean}			hoverStopPropagation	指定是否阻止本节点的祖先节点出现点击态，微信小程序有效（默认 true ）
 * @property {String}			lang					指定返回用户信息的语言，zh_CN 简体中文，zh_TW 繁体中文，en 英文（默认 en ）
 * @property {String}			sessionFrom				会话来源，openType="contact"时有效
 * @property {String}			sendMessageTitle		会话内消息卡片标题，openType="contact"时有效
 * @property {String}			sendMessagePath			会话内消息卡片点击跳转小程序路径，openType="contact"时有效
 * @property {String}			sendMessageImg			会话内消息卡片图片，openType="contact"时有效
 * @property {Boolean}			showMessageCard			是否显示会话内消息卡片，设置此参数为 true，用户进入客服会话会在右下角显示"可能要发送的小程序"提示，用户点击后可以快速发送小程序消息，openType="contact"时有效（默认false）
 * @property {String}			dataName				额外传参参数，用于小程序的data-xxx属性，通过target.dataset.name获取
 * @property {String | Number}	throttleTime			节流，一定时间内只能触发一次 （默认 0 )
 * @property {String | Number}	hoverStartTime			按住后多久出现点击态，单位毫秒 （默认 0 )
 * @property {String | Number}	hoverStayTime			手指松开后点击态保留时间，单位毫秒 （默认 200 )
 * @property {String | Number}	text					按钮文字，之所以通过props传入，是因为slot传入的话（注：nvue中无法控制文字的样式）
 * @property {String}			icon					按钮图标
 * @property {String}			iconColor				按钮图标颜色
 * @property {String}			color					按钮颜色，支持传入linear-gradient渐变色
 * @property {Object}			customStyle				定义需要用到的外部样式
 * @event {Function}	click			非禁止并且非加载中，才能点击
 * @event {Function}	getphonenumber	open-type="getPhoneNumber"时有效
 * @event {Function}	getuserinfo		用户点击该按钮时，会返回获取到的用户信息，从返回参数的detail中获取到的值同uni.getUserInfo
 * @event {Function}	error			当使用开放能力时，发生错误的回调
 * @event {Function}	opensetting		在打开授权设置页并关闭后回调
 * @event {Function}	launchapp		打开 APP 成功的回调
 * @example <uv-button>月落</uv-button>
 */
export default {
		name: "uv-button",
		// #ifdef MP
		mixins: [mpMixin, mixin, button, openType, props],
		// #endif
		// #ifndef MP
		mixins: [mpMixin, mixin, props],
		// #endif
		emits: ['click'],
		data() {
			return {};
		},
		computed: {
			// 生成bem风格的类名
			bemClass() {
				// this.bem为一个computed变量，在mixin中
				if (!this.color) {
					return this.bem("button",
						["type", "shape", "size"],
						["disabled", "plain", "hairline"]);
				} else {
					// 由于nvue的原因，在有color参数时，不需要传入type，否则会生成type相关的类型，影响最终的样式
					return this.bem("button",
						["shape", "size"],
						["disabled", "plain", "hairline"]);
				}
			},
			loadingColor() {
				if (this.plain) {
					// 如果有设置color值，则用color值，否则使用type主题颜色
					return this.color ? this.color : '#3c9cff';
				}
				if (this.type === "info") {
					return "#c9c9c9";
				}
				return "rgb(200, 200, 200)";
			},
			iconColorCom() {
				// 如果是镂空状态，设置了color就用color值，否则使用主题颜色，
				// uv-icon的color能接受一个主题颜色的值
				if (this.iconColor) return this.iconColor;
				if (this.plain) {
					return this.color ? this.color : this.type;
				} else {
					return this.type === "info" ? "#000000" : "#ffffff";
				}
			},
			baseColor() {
				let style = {};
				if (this.color) {
					// 针对自定义了color颜色的情况，镂空状态下，就是用自定义的颜色
					style.color = this.plain ? this.color : "white";
					if (!this.plain) {
						// 非镂空，背景色使用自定义的颜色
						style["background-color"] = this.color;
					}
					if (this.color.indexOf("gradient") !== -1) {
						// 如果自定义的颜色为渐变色，不显示边框，以及通过backgroundImage设置渐变色
						// weex文档说明可以写borderWidth的形式，为什么这里需要分开写？
						// 因为weex是阿里巴巴为了部门业绩考核而做的你懂的东西，所以需要这么写才有效
						style.borderTopWidth = 0;
						style.borderRightWidth = 0;
						style.borderBottomWidth = 0;
						style.borderLeftWidth = 0;
						if (!this.plain) {
							style.backgroundImage = this.color;
						}
					} else {
						// 非渐变色，则设置边框相关的属性
						style.borderColor = this.color;
						style.borderWidth = "1px";
						style.borderStyle = "solid";
					}
				}
				return style;
			},
			// nvue版本按钮的字体不会继承父组件的颜色，需要对每一个text组件进行单独的设置
			nvueTextStyle() {
				let style = {};
				// 针对自定义了color颜色的情况，镂空状态下，就是用自定义的颜色
				if (this.type === "info") {
					style.color = "#323233";
				}
				if (this.color) {
					style.color = this.plain ? this.color : "white";
				}
				style.fontSize = this.textSize + "px";
				return style;
			},
			// 字体大小
			textSize() {
				let fontSize = 14,
					{ size } = this;
				if (size === "large") fontSize = 16;
				if (size === "normal") fontSize = 14;
				if (size === "small") fontSize = 12;
				if (size === "mini") fontSize = 10;
				return fontSize;
			},
			// 设置图标大小
			getIconSize() {
				const size = this.iconSize ? this.iconSize : this.textSize * 1.35;
				return this.$uv.addUnit(size);
			},
			// 设置外层盒子的宽度，其他样式不需要
			btnWrapperStyle() {
				const style = {};
				const customStyle = this.$uv.addStyle(this.customStyle);
				if(customStyle.width) style.width = customStyle.width;
				return style;
			}
		},
		methods: {
			clickHandler() {
				// 非禁止并且非加载中，才能点击
				if (!this.disabled && !this.loading) {
					// 进行节流控制，每this.throttle毫秒内，只在开始处执行
					throttle(() => {
						this.$emit("click");
					}, this.throttleTime);
				}
			}
		}
	}
</script>

<style lang="scss" scoped>
$show-reset-button: 1;
@import '@/uni_modules/uv-ui-tools/libs/css/variable.scss';
@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
@import '@/uni_modules/uv-ui-tools/libs/css/color.scss';

/* #ifndef APP-NVUE */
@import "./vue.scss";
/* #endif */

/* #ifdef APP-NVUE */
@import "./nvue.scss";
/* #endif */

$uv-button-uv-button-height: 40px !default;
$uv-button-text-font-size: 15px !default;
$uv-button-loading-text-font-size: 15px !default;
$uv-button-loading-text-margin-left: 4px !default;
$uv-button-large-width: 100% !default;
$uv-button-large-height: 50px !default;
$uv-button-normal-padding: 0 12px !default;
$uv-button-large-padding: 0 15px !default;
$uv-button-normal-font-size: 14px !default;
$uv-button-small-min-width: 60px !default;
$uv-button-small-height: 30px !default;
$uv-button-small-padding: 0px 8px !default;
$uv-button-mini-padding: 0px 8px !default;
$uv-button-small-font-size: 12px !default;
$uv-button-mini-height: 22px !default;
$uv-button-mini-font-size: 10px !default;
$uv-button-mini-min-width: 50px !default;
$uv-button-disabled-opacity: 0.5 !default;
$uv-button-info-color: #323233 !default;
$uv-button-info-background-color: #fff !default;
$uv-button-info-border-color: #ebedf0 !default;
$uv-button-info-border-width: 1px !default;
$uv-button-info-border-style: solid !default;
$uv-button-success-color: #fff !default;
$uv-button-success-background-color: $uv-success !default;
$uv-button-success-border-color: $uv-button-success-background-color !default;
$uv-button-success-border-width: 1px !default;
$uv-button-success-border-style: solid !default;
$uv-button-primary-color: #fff !default;
$uv-button-primary-background-color: $uv-primary !default;
$uv-button-primary-border-color: $uv-button-primary-background-color !default;
$uv-button-primary-border-width: 1px !default;
$uv-button-primary-border-style: solid !default;
$uv-button-error-color: #fff !default;
$uv-button-error-background-color: $uv-error !default;
$uv-button-error-border-color: $uv-button-error-background-color !default;
$uv-button-error-border-width: 1px !default;
$uv-button-error-border-style: solid !default;
$uv-button-warning-color: #fff !default;
$uv-button-warning-background-color: $uv-warning !default;
$uv-button-warning-border-color: $uv-button-warning-background-color !default;
$uv-button-warning-border-width: 1px !default;
$uv-button-warning-border-style: solid !default;
$uv-button-block-width: 100% !default;
$uv-button-circle-border-top-right-radius: 100px !default;
$uv-button-circle-border-top-left-radius: 100px !default;
$uv-button-circle-border-bottom-left-radius: 100px !default;
$uv-button-circle-border-bottom-right-radius: 100px !default;
$uv-button-square-border-top-right-radius: 3px !default;
$uv-button-square-border-top-left-radius: 3px !default;
$uv-button-square-border-bottom-left-radius: 3px !default;
$uv-button-square-border-bottom-right-radius: 3px !default;
$uv-button-icon-min-width: 1em !default;
$uv-button-plain-background-color: #fff !default;
$uv-button-hairline-border-width: 0.5px !default;

.uv-button {
    height: $uv-button-uv-button-height;
    position: relative;
    align-items: center;
    justify-content: center;
    @include flex;
    /* #ifndef APP-NVUE */
    box-sizing: border-box;
    /* #endif */
    flex-direction: row;

    &__text {
        font-size: $uv-button-text-font-size;
    }

    &__loading-text {
        font-size: $uv-button-loading-text-font-size;
        margin-left: $uv-button-loading-text-margin-left;
    }

    &--large {
        /* #ifndef APP-NVUE */
        width: $uv-button-large-width;
        /* #endif */
        height: $uv-button-large-height;
        padding: $uv-button-large-padding;
    }

    &--normal {
        padding: $uv-button-normal-padding;
        font-size: $uv-button-normal-font-size;
    }

    &--small {
        /* #ifndef APP-NVUE */
        min-width: $uv-button-small-min-width;
        /* #endif */
        height: $uv-button-small-height;
        padding: $uv-button-small-padding;
        font-size: $uv-button-small-font-size;
    }

    &--mini {
        height: $uv-button-mini-height;
        font-size: $uv-button-mini-font-size;
        /* #ifndef APP-NVUE */
        min-width: $uv-button-mini-min-width;
        /* #endif */
        padding: $uv-button-mini-padding;
    }

    &--disabled {
        opacity: $uv-button-disabled-opacity;
    }

    &--info {
        color: $uv-button-info-color;
        background-color: $uv-button-info-background-color;
        border-color: $uv-button-info-border-color;
        border-width: $uv-button-info-border-width;
        border-style: $uv-button-info-border-style;
    }

    &--success {
        color: $uv-button-success-color;
        background-color: $uv-button-success-background-color;
        border-color: $uv-button-success-border-color;
        border-width: $uv-button-success-border-width;
        border-style: $uv-button-success-border-style;
    }

    &--primary {
        color: $uv-button-primary-color;
        background-color: $uv-button-primary-background-color;
        border-color: $uv-button-primary-border-color;
        border-width: $uv-button-primary-border-width;
        border-style: $uv-button-primary-border-style;
    }

    &--error {
        color: $uv-button-error-color;
        background-color: $uv-button-error-background-color;
        border-color: $uv-button-error-border-color;
        border-width: $uv-button-error-border-width;
        border-style: $uv-button-error-border-style;
    }

    &--warning {
        color: $uv-button-warning-color;
        background-color: $uv-button-warning-background-color;
        border-color: $uv-button-warning-border-color;
        border-width: $uv-button-warning-border-width;
        border-style: $uv-button-warning-border-style;
    }

    &--block {
        @include flex;
        width: $uv-button-block-width;
    }

    &--circle {
        border-top-right-radius: $uv-button-circle-border-top-right-radius;
        border-top-left-radius: $uv-button-circle-border-top-left-radius;
        border-bottom-left-radius: $uv-button-circle-border-bottom-left-radius;
        border-bottom-right-radius: $uv-button-circle-border-bottom-right-radius;
    }

    &--square {
        border-bottom-left-radius: $uv-button-square-border-top-right-radius;
        border-bottom-right-radius: $uv-button-square-border-top-left-radius;
        border-top-left-radius: $uv-button-square-border-bottom-left-radius;
        border-top-right-radius: $uv-button-square-border-bottom-right-radius;
    }

    &__icon {
        /* #ifndef APP-NVUE */
        min-width: $uv-button-icon-min-width;
        line-height: inherit !important;
        vertical-align: top;
        /* #endif */
    }

    &--plain {
        background-color: $uv-button-plain-background-color;
    }

    &--hairline {
        border-width: $uv-button-hairline-border-width !important;
    }
}
</style>

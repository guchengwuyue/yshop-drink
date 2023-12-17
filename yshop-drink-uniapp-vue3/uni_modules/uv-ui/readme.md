<p align="center">
    <span>&nbsp;&nbsp;&nbsp;&nbsp;</span><img alt="logo" src="https://www.uvui.cn/common/logo.png" width="120" height="120">
</p>
<h3 align="center">uv-ui</h3>
<h3 align="center">兼容vue3+2多平台快速开发的UI框架</h3>

[![star](https://gitee.com/climblee/uv-ui/badge/star.svg?theme=gvp)](https://gitee.com/climblee/uv-ui)
[![star](https://gitee.com/climblee/uv-ui/badge/fork.svg?theme=gvp)](https://gitee.com/climblee/uv-ui)
[![star](https://img.shields.io/github/stars/climblee/uv-ui?style=flat-square&logo=GitHub)](https://github.com/climblee/uv-ui)
[![issues](https://img.shields.io/github/issues/climblee/uv-ui?style=flat-square&logo=GitHub)](https://github.com/climblee/uv-ui/issues)
[![Website](https://img.shields.io/badge/uvui-up-blue?style=flat-square)](https://www.uvui.cn)
[![version](https://img.shields.io/badge/version-1.1.15-brightgreen.svg)](https://www.uvui.cn/components/changelog.html)
[![license](https://img.shields.io/github/license/climblee/uv-ui?style=flat-square)](https://en.wikipedia.org/wiki/MIT_License)

## 温馨提示：如需下载uv-ui示例项目，请不要使用【下载插件ZIP】按钮。

### uvui官方群1：<a href="http://qm.qq.com/cgi-bin/qm/qr?_wv=1027&k=aaoyDvfV7nSee9vzWfzyZM1vKlu3xrNZ&authKey=pdU6HDpyzGUoc1QvQvCmzCbwzsoHgBbToF%2F0ChP4lNjPIPgWHGRE4I99XYGiTcNI&noverify=0&group_code=549833913" target="_blank">549833913</a>
### uvui官方群2：<a href="http://qm.qq.com/cgi-bin/qm/qr?_wv=1027&k=2QbEeKBn6ysCyQ09V4mgst1W8onxFybQ&authKey=aMTGL5zCYwsinG%2FeH0qMlAqXAdyKr5AjXVf2oMnsmj7NCg%2F2HraMU%2FNFxELLIPvp&noverify=0&group_code=206060892" target="_blank">206060892</a>

## uvui特点

1. **uv-ui的前世今生**，`uv-ui` 是基于 `uview2.x` 版本改造而来。重命名也是为了避开发布冲突和很多组件 `u-`在  `nvue` 中不能使用的情况，所以这才诞生了`uv-ui`。感谢 `uview-ui` 作者的开源奉献，再次为开源点赞。 同时 `uv-ui` 也是无条件开源。

2. **全端兼容**，`uv-ui`支持vue3、vue2、app-vue、app-nvue、h5、小程序等。`uv-ui`的组件都是多端自适应的，底层会抹平很多小程序平台的差异或bug。

3. **扩展配置**，`uv-ui`内置的方法默认不再挂载到`uni`对象之上，也就意味着默认情况下不能在项目中直接使用`uni.$uv.xxx`使用内置方法。但是可以通过扩展可以解决，通过如下方式进行配置即可，使用方式请参考[扩展配置](https://www.uvui.cn/components/setting.html)。其中包括[ JS工具库](https://www.uvui.cn/components/setting.html#%E6%89%A9%E5%B1%95%E9%85%8D%E7%BD%AE-js%E5%B7%A5%E5%85%B7%E5%BA%93)、[ 自定义主题](https://www.uvui.cn/components/setting.html#%E6%89%A9%E5%B1%95%E9%85%8D%E7%BD%AE-%E8%87%AA%E5%AE%9A%E4%B9%89%E4%B8%BB%E9%A2%98)、[ 基础样式](https://www.uvui.cn/components/setting.html#%E6%89%A9%E5%B1%95%E9%85%8D%E7%BD%AE-%E5%9F%BA%E7%A1%80%E6%A0%B7%E5%BC%8F)、[ setconfig](https://www.uvui.cn/components/setting.html#%E6%89%A9%E5%B1%95%E9%85%8D%E7%BD%AE-setconfig)等。

## 预览

通过微信（APP下载不支持微信扫码）或浏览器扫码查看演示效果。

<img src="https://mp-31ed6237-7b7b-4597-8448-a53d6d6031cf.cdn.bspapp.com/uvui/download-wx.png" width="200" />&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://mp-31ed6237-7b7b-4597-8448-a53d6d6031cf.cdn.bspapp.com/uvui/download-app.png" width="200" />&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://mp-31ed6237-7b7b-4597-8448-a53d6d6031cf.cdn.bspapp.com/uvui/download-h5.png" width="200" />

## 链接

- [官方文档](https://www.uvui.cn)
- [演示地址](https://h5.uvui.cn)
- [更新日志](https://www.uvui.cn/components/changelog.html)
- [关于我们](https://www.uvui.cn/cooperation/about.html)
- <a href="#list">组件列表</a>

## 交流反馈

欢迎加入我们的QQ群交流反馈：[点此跳转](https://www.uvui.cn/components/addQQGroup.html)

## 快速开始

方式一：`uv-ui` 强烈建议通过 `下载插件并导入HbuilderX` 导入组件。

方式二：下载完整 [uv-ui项目](https://ext.dcloud.net.cn/plugin?id=12287) 将 `uni_modules` 复制到自己的项目。

方式三：通过 `npm i @climblee/uv-ui` 下载，此方法需要配置 easycom，配置详情可查看[安装](https://www.uvui.cn/components/install.html)。

请通过[快速上手](https://www.uvui.cn/components/quickstart.html)了解更详细的内容。

**注意：导入插件后，建议`HBuilderX`重新运行项目，可能新导入的插件不能实时更新而导致不能运行。**

## 使用方法

组件导入 `uni_modules` 后，直接在项目中使用，无需通过import引入组件。

```html
<template>
    <uv-icon name="baidu" size="30" color="#909399"></uv-icon>
</template>
```

## 扩展功能

`uv-ui` 内置了强大的工具函数、请求封装等，可以根据自身需求进行扩展配置，详情请查看[扩展配置](https://www.uvui.cn/components/setting.html)。

**注意：只有[扩展配置](https://www.uvui.cn/components/setting.html)后才能在自己的项目页面中使用组件库内置方法和变量等**。

<div id="list"></div>

## 组件列表

下表为 `uv-ui` 的扩展组件清单，点击每个组件**点击下载&安装**即可在详情页面导入组件到项目下，导入后建议重新运行即可直接使用，组件无需import和注册。

| 组件名 | 组件说明 |
| --- | --- |
| uv-calendars | [新版日历（推荐）](https://www.uvui.cn/components/calendars.html) |
| uv-drop-down | [下拉筛选](https://www.uvui.cn/components/dropDown.html) |
| uv-scroll-list | [横向滚动列表](https://www.uvui.cn/components/scrollList.html) |
| uv-vtabs | [垂直选项卡](https://www.uvui.cn/components/vtabs.html) |
| uv-pick-color | [颜色选择器](https://www.uvui.cn/components/pickColor.html) |
| uv-qrcode | [二维码](https://www.uvui.cn/components/qrcode.html) |
| uv-waterfall | [瀑布流](https://www.uvui.cn/components/waterfall.html) |
| uv-row | [Layout 布局](https://www.uvui.cn/components/layout.html) |
| uv-icon | [图标](https://www.uvui.cn/components/icon.html) |
| uv-button | [按钮](https://www.uvui.cn/components/button.html) |
| uv-text | [文本](https://www.uvui.cn/components/text.html) |
| uv-link | [超链接](https://www.uvui.cn/components/link.html) |
| uv-image | [图片](https://www.uvui.cn/components/image.html) |
| uv-transition | [动画](https://www.uvui.cn/components/transition.html) |
| uv-form | [表单](https://www.uvui.cn/components/form.html) |
| uv-input | [增强输入框](https://www.uvui.cn/components/input.html) |
| uv-textarea | [增强文本域](https://www.uvui.cn/components/textarea.html) |
| uv-checkbox | [复选框](https://www.uvui.cn/components/checkbox.html) |
| uv-radio | [单选框](https://www.uvui.cn/components/radio.html) |
| uv-switch | [开关选择器](https://www.uvui.cn/components/switch.html) |
| uv-calendar | [日历](https://www.uvui.cn/components/calendar.html) |
| uv-picker | [选择器](https://www.uvui.cn/components/picker.html) |
| uv-datetime-picker | [时间选择器](https://www.uvui.cn/components/datetimePicker.html) |
| uv-code | [验证码倒计时](https://www.uvui.cn/components/code.html) |
| uv-keyboard | [键盘](https://www.uvui.cn/components/keyboard.html) |
| uv-rate | [评分](https://www.uvui.cn/components/rate.html) |
| uv-search | [多功能搜索框](https://www.uvui.cn/components/search.html) |
| uv-number-box | [步进器](https://www.uvui.cn/components/numberBox.html) |
| uv-upload | [上传](https://www.uvui.cn/components/upload.html) |
| uv-slider | [滑动选择器](https://www.uvui.cn/components/slider.html) |
| uv-list | [列表](https://www.uvui.cn/components/list.html) |
| uv-index-list | [索引列表](https://www.uvui.cn/components/indexList.html) |
| uv-tags | [标签](https://www.uvui.cn/components/tag.html) |
| uv-line-progress | [线形进度条](https://www.uvui.cn/components/lineProgress.html) |
| uv-badge | [徽标数](https://www.uvui.cn/components/badge.html) |
| uv-count-down | [倒计时](https://www.uvui.cn/components/countDown.html) |
| uv-count-to | [数字滚动](https://www.uvui.cn/components/countTo.html) |
| uv-avatar | [头像](https://www.uvui.cn/components/avatar.html) |
| uv-skeleton | [骨架屏](https://www.uvui.cn/components/skeleton.html) |
| uv-loading-icon | [加载动画](https://www.uvui.cn/components/loadingIcon.html) |
| uv-loading-page | [加载页](https://www.uvui.cn/components/loadingPage.html) |
| uv-load-more | [加载更多](https://www.uvui.cn/components/loadMore.html) |
| uv-empty | [内容为空](https://www.uvui.cn/components/empty.html) |
| uv-tooltip | [长按提示](https://www.uvui.cn/components/tooltip.html) |
| uv-alert | [警告提示](https://www.uvui.cn/components/alert.html) |
| uv-toast | [消息提示](https://www.uvui.cn/components/toast.html) |
| uv-notice-bar | [滚动通知](https://www.uvui.cn/components/noticeBar.html) |
| uv-notify | [消息提示](https://www.uvui.cn/components/notify.html) |
| uv-no-network | [无网络提示](https://www.uvui.cn/components/noNetwork.html) |
| uv-popup | [弹出层](https://www.uvui.cn/components/popup.html) |
| uv-modal | [模态框](https://www.uvui.cn/components/modal.html) |
| uv-cell | [单元格](https://www.uvui.cn/components/cell.html) |
| uv-swipe-action | [滑动单元格](https://www.uvui.cn/components/swipeAction.html) |
| uv-swiper | [轮播图](https://www.uvui.cn/components/swiper.html) |
| uv-collapse | [折叠面板](https://www.uvui.cn/components/collapse.html) |
| uv-grid | [宫格布局](https://www.uvui.cn/components/grid.html) |
| uv-album | [相册](https://www.uvui.cn/components/album.html) |
| uv-tabbar | [底部导航栏](https://www.uvui.cn/components/tabbar.html) |
| uv-back-top | [返回顶部](https://www.uvui.cn/components/backTop.html) |
| uv-navbar | [自定义导航栏](https://www.uvui.cn/components/navbar.html) |
| uv-action-sheet | [底部操作菜单](https://www.uvui.cn/components/actionSheet.html) |
| uv-tabs | [标签选项卡](https://www.uvui.cn/components/tabs.html) |
| uv-steps | [步骤条](https://www.uvui.cn/components/steps.html) |
| uv-subsection | [分段器](https://www.uvui.cn/components/subsection.html) |
| uv-sticky | [吸顶](https://www.uvui.cn/components/sticky.html) |
| uv-parse | [富文本解析器](https://www.uvui.cn/components/parse.html) |
| uv-overlay | [遮罩层](https://www.uvui.cn/components/overlay.html) |
| uv-code-input | [验证码输入](https://www.uvui.cn/components/codeInput.html) |
| uv-read-more | [展开阅读更多](https://www.uvui.cn/components/readMore.html) |
| uv-line | [线条](https://www.uvui.cn/components/line.html) |
| uv-gap | [间隔槽](https://www.uvui.cn/components/gap.html) |
| uv-divider | [分割线](https://www.uvui.cn/components/divider.html) |

## 版权信息
uv-ui遵循[MIT](https://en.wikipedia.org/wiki/MIT_License)开源协议，意味着您无需支付任何费用，也无需授权，即可将uv-ui应用到您的产品中。

## 作者想说
- 开源真的不易，不图大家的钱财，所以希望大家多多鼓励支持，希望不要恶意评论，有问题加群快速解决。
- 遇到BUG，是一件很正常的事情，是程序肯定就有BUG，所以希望大家能以理解的心态去提出BUG，然后作者才有动力去努力修复。
- 最后觉得好用的小伙伴，不要吝啬你的双手，给个好评就是给我们最大的鼓励。

# 恶评者手下留情，有事加QQ群解决：549833913
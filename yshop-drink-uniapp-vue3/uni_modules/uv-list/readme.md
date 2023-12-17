## List 列表

> **组件名：uv-list**

List列表组件，包含基本列表样式、默认插槽机制、可扩展插槽机制、长列表性能优化、多端兼容。

在vue页面里，它默认使用页面级滚动，这样做的目的是性能更加友好。在app-nvue页面里，它默认使用原生list组件滚动，这样的长列表，在滚动出屏幕外后，系统会回收不可见区域的渲染内存资源，不会造成滚动越长手机越卡的问题。

uv-list组件是父容器，里面的核心是uv-list-item子组件，它代表列表中的一个可重复行，子组件可以无限循环。

uv-list-item有很多风格，uv-list-item组件通过内置的属性，满足一些常用的场景。当内置属性不满足需求时，可以通过扩展插槽来自定义列表内容，插槽包括：默认插槽（完全自定义内容）、具名插槽（header | body | footer），根据需求进行扩展。

内置属性可以覆盖的场景包括：导航列表、设置列表、小图标列表等，其他不能满足的场景使用插槽进行扩展。

# <a href="https://www.uvui.cn/components/list.html" target="_blank">查看文档</a>

## [下载完整示例项目](https://ext.dcloud.net.cn/plugin?name=uv-ui) <small>（请不要 下载插件ZIP）</small>

### [更多插件，请关注uv-ui组件库](https://ext.dcloud.net.cn/plugin?name=uv-ui)

<a href="https://ext.dcloud.net.cn/plugin?name=uv-ui" target="_blank">

![image](https://mp-a667b617-c5f1-4a2d-9a54-683a67cff588.cdn.bspapp.com/uv-ui/banner.png)

</a>

#### 如使用过程中有任何问题反馈，或者您对uv-ui有一些好的建议，欢迎加入uv-ui官方交流群：<a href="https://www.uvui.cn/components/addQQGroup.html" target="_blank">官方QQ群</a>
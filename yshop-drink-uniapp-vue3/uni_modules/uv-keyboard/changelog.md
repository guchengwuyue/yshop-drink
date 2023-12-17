## 1.0.5（2023-10-12）
1. 增加disKeys参数，mode = "car"下，被禁用的键，如：['I','O']
2. 增加customabc参数，mode = "car"下，是否启用自定义中英文切换内容模式，为了兼容支付宝等小程序不兼容嵌套插槽，导致同时显示自定义内容和原始内容
3. 增加ref方法changeCarMode，mode = "car"下， 调用此方法可以切换中英文模式
4. 增加@changeCarInputMode，mode = "car"下，调用此方法可以进行切换中英文
5. 增加插槽abc，mode = "car"下，自定义中英文切换内容，具体参考[车牌键盘自定义中英文切换及禁用键等设置](https://www.uvui.cn/components/keyboard.html#车牌键盘自定义中英文切换及禁用键等设置)
## 1.0.4（2023-09-04）
1. 优化，修改文件名称
## 1.0.3（2023-09-04）
1. 修复键盘change回调事件产生冲突的BUG
## 1.0.2（2023-07-02）
uv-keyboard  由于弹出层uv-popup的修改，打开和关闭方法更改，详情参考文档：https://www.uvui.cn/components/keyboard.html
## 1.0.1（2023-05-16）
1. 优化组件依赖，修改后无需全局引入，组件导入即可使用
2. 优化部分功能
## 1.0.0（2023-05-10）
uv-keyboard 键盘

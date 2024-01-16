

## 平台简介

意向点餐(扫码点餐)系统，在线点餐(外卖与自取)小程序模式，支持多门店模式，基础技术Java，uniapp(vue3)（支持H5、微信小程序）
采用当前流行技术组合的前后端分离点餐系统： SpringBoot、Spring Security OAuth2、MybatisPlus、SpringSecurity、jwt、redis、Vue3的前后端分离的系统， 
包含外卖与自取、商品管理(多规格sku)、店铺管理、云小票打印、图片素材库、订单管理、积分兑换、充值、优惠券、充值、多门店、微信公众号、门店移动端、分销商、桌面扫码点餐、拼团、邀请有礼、会员卡等功能，更适合企业或个人二次开发.

官网地址：[https://www.yixiang.co/](https://www.yixiang.co/)



## 演示地址

| 后台登陆：  | https://dc.yixiang.co   账号/密码：yshop002/123456789  |
|---|---|
| 门店登陆： | https://dc.yixiang.co    账号/密码：yixiang001/123456789  |
|  移动端演示：关注右边公众号点击菜单其他系统体验点餐小程序与点餐H5，其中如果演示使用验证码登陆的点击下验证码默认验证码是9999 | ![输入图片说明](77a93e8c07a913b838a756abadb383b9.png) |

## 视频资料
如果对您有帮助，您可以点右上角 "Star" 支持一下，这样我们才有继续免费下去的动力，谢谢！ QQ交流群 (入群前，请在网页右上角点 "Star" )，群里有视频教程哦！！

交流QQ群：544263002

## 项目说明
    

```
    yshop-drink.             Java工程
    yshop-drink-vue          后台前端vue3工程
    yshop-drink-uniapp-vue3  移动端uniapp(vue3版本)工程，支持微信小程序、h5
```


## 本地快速启动
  ##### 1、环境要求
   
    ```
        jdk8
        mysql8
        redis6+
        node14+
    
    ```
  ##### 2、开发工具
   
    ```
        idea
        vscode
        hbuilder
    
    ```
 ##### 3、后端启动


-   3.1 请使用idea打开Java工程，自动会安装依赖
-   3.2 创建数据库且导入工程目录下sql/yixiang-drink.sql 文件
-   3.3 找到项目下的yshop-server 的yml,如图：
     ![输入图片说明](image.png)
-   3.4 工程下输入 

   ` mvn clean install package '-Dmaven.test.skip=true`
-   3.5 启动项目，如图
    ![输入图片说明](1702544439568.jpg)

##### 4、后台vue启动

 - 4.1 vscode 打开vue工程，在目录下输入命令，npm install
 - 4.2 配置api如图
 ![输入图片说明](1702544756749.jpg)
 - 4.3 本地启动 npm run front

##### 5 移动端uniapp启动
 
  - 5.1 hbuilder导入uniapp项目，
  - 5.2 配置api
   ![输入图片说明](WX20231214-171211@2x.png)
  - 5.3 配置小程序
   ![输入图片说明](WX20231214-171416@2x.png)
  - 5.4 运行小程序
    ![输入图片说明](WX20231214-171514@2x.png)
  - 5.5 运行h5
   
    ![输入图片说明](1702545370856.jpg)
-



## 小程序截图

| ![输入图片说明](%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20231211104701.jpg) |  ![输入图片说明](%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20231211104710.jpg) |
|---|---|
| ![输入图片说明](%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20231211104713.jpg)  |  ![输入图片说明](WechatIMG64.jpeg) |
| ![输入图片说明](WechatIMG66.jpeg) |  ![输入图片说明](WechatIMG65.jpeg) |

## 后台截图

| ![输入图片说明](WX20231208-120203@2x.png) | ![输入图片说明](%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20231211104714.jpg)|
|---|---|
| ![输入图片说明](WX20231208-120320@2x.png)  | ![输入图片说明](WX20231208-120338@2x.png) |


## 技术栈
Spring Boot，

Spring Security oauth2，

MyBatis，

MyBatisPlus，

Redis，

lombok，

hutool

Vue3，

Element UI，

uniapp(vue3)

## 特别鸣谢


- ruoyi-vue-pro:https://gitee.com/zhijiantianya/ruoyi-vue-pro
- element-plus:https://element-plus.gitee.io/zh-CN/
- vue:https://cn.vuejs.org/
- pay-java-parent:https://gitee.com/egzosn/pay-java-parent
- uvui：https://www.uvui.cn/
- uniapp:https://uniapp.dcloud.net.cn/


## 开源协议

本项目采用比 Apache 2.0 更宽松的 [MIT License](https://gitee.com/zhijiantianya/ruoyi-vue-pro/blob/master/LICENSE) 开源协议，个人与企业可 100% 免费使用，不用保留类作者、Copyright 信息。


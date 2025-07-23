

## 平台简介

意象点餐(扫码点餐)系统，在线点餐(外卖与自取)小程序模式，支持多门店模式，SaaS多租户模式,基础技术Java17，springboot3、vue3、uniapp(vue3)（支持H5、微信小程序）
采用当前流行技术组合的前后端分离点餐系统： SpringBoot3、Spring Security OAuth2、MybatisPlus、SpringSecurity、jwt、redis、Vue3的前后端分离的系统， 
包含外卖与自取、商品管理(多规格sku)、店铺管理、云小票打印、图片素材库、订单管理、积分兑换(积分+金额)、充值、优惠券、充值、多门店、微信公众号、商家中心、提前预约、桌面扫码点餐(单人或者多人协同)、收银台(支持扫码枪与扫码盒子收款)、会员卡、桌台点餐等功能，更适合企业或个人二次开发.

官网地址：[https://www.yixiang.co/](https://www.yixiang.co/)



## 演示地址

| 后台登陆：  | https://dc.yixiang.co   账号/密码：admin/admin123  |
|---|---|
| 门店登陆： | https://dc.yixiang.co   账号/密码：yixiang001/123456789  |
|  移动端演示：关注右边公众号点击菜单其他系统体验点餐小程序与点餐H5，其中如果演示使用验证码登陆的点击下验证码默认验证码是9999 | ![输入图片说明](assets/77a93e8c07a913b838a756abadb383b9.png) |

## 视频资料
如果对您有帮助，您可以点右上角 "Star" 支持一下，这样我们才有继续免费下去的动力，谢谢！ QQ交流群 (入群前，请在网页右上角点 "Star" )，群里有视频教程与开发文档哦！！

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
        jdk17
        mysql8
        redis6+
        node16+
        maven3.8+
    
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
-   3.3 找到项目下的yshop-server 的yml,修改数据库相关信息和redis相关信息，如图：
     ![输入图片说明](assets/image.png)
-   3.4 工程下输入
    ``` 
    mvn clean install package '-Dmaven.test.skip=true
    ```
-   3.5 启动项目，如图
    ![输入图片说明](assets/1702544439568.jpg)

##### 4、后台vue启动

 - 4.1 vscode 打开vue工程，在目录下输入命令: 
    ``` 
    pnpm install
    ```
 - 4.2 配置api如图
 ![输入图片说明](assets/1702544756749.jpg)
 - 4.3 本地启动:
    ```
     npm run dev
    ```

##### 5 移动端uniapp启动
 
  - 5.1 hbuilder导入uniapp项目，
  - 5.2 配置api
   ![输入图片说明](assets/WX20231214-171211@2x.png)
  - 5.3 配置小程序
   ![输入图片说明](assets/WX20231214-171416@2x.png)
  - 5.4 运行小程序
    ![输入图片说明](assets/WX20231214-171514@2x.png)
  - 5.5 运行h5
   
    ![输入图片说明](assets/1702545370856.jpg)
-



## 小程序截图

| ![输入图片说明](assets/20240901175929.png)| ![输入图片说明](assets/202409011759291.png) |
|---|---|
| ![输入图片说明](assets/20240901175927.jpg)  |  ![输入图片说明](assets/202409011759281.png) |
| ![输入图片说明](assets/20240901175928.png) |  |

## 后台截图

| ![输入图片说明](assets/WX20231208-120203@2x.png) | ![输入图片说明](assets/20231211104714.jpg)|
|---|---|
| ![输入图片说明](assets/WX20231208-120320@2x.png)  | ![输入图片说明](assets/WX20231208-120338@2x.png) |


## 技术栈
- Spring Boot3

- Spring Security oauth2

- MyBatis

- MyBatisPlus

- Redis

- lombok

- hutool

- Vue3

- Element UI

- uniapp(vue3)

## 特别鸣谢


- ruoyi-vue-pro:https://gitee.com/zhijiantianya/ruoyi-vue-pro
- element-plus:https://element-plus.gitee.io/zh-CN/
- vue:https://cn.vuejs.org/
- pay-java-parent:https://gitee.com/egzosn/pay-java-parent
- uvui：https://www.uvui.cn/
- uniapp:https://uniapp.dcloud.net.cn/


## 开源协议

本项目采用比 Apache 2.0 更宽松的 [MIT License](https://gitee.com/guchengwuyue/yshop-drink/blob/master/LICENSE) 开源协议，个人与企业可 100% 免费使用，不用保留类作者、Copyright 信息。


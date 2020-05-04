### **供货商城升级版即将来袭：**


 **介绍：** 

供货商城升级版又名yshop-mini商城系统，属于供货商城升级版

 **技术栈：** 

管理后台继续基于ruoyi，
在此技术上增加:
mybatisPlus
redis
lombok
mapstruct
jwt
vue,WxJava等

 **模块划分：** 

```
- yshop-base 基础模块
    - config 配置等
    - coupon 优惠券
    - handler mybatis handler
    - mall
    - order 订单
    - oss 云存储
    - redis 常用配置与订单过期，自动收货监听等
    - system
    - weixin 支付、公众号等
- yshop-common common通用工具
- yshop-framework 核心
- yshop-mall 商城
- yixiang-generator 代码生成模块 支持mybatisplus
- yixiang-quartr 定时任务
- yixiang-admin 管理后台
- yixiang-app api模块 jwt+自定义注解验证
```
 

**`功能特色** ‘

 1、spu,多规格sku商品管理
<table>
    <tr>
        <td><img src="https://images.gitee.com/uploads/images/2020/0504/192604_7db25918_477893.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2020/0504/192638_a4adcc1b_477893.png"/></td
    </tr>	 
</table>

2、多级商品分类管理、商品评价管理、订单管理、实时物流、运费模板、用户购物车、用户收藏、优惠券、积分、OSS(七牛、腾讯、阿里)、素材管理等

<table>
    <tr>
        <td><img src="https://images.gitee.com/uploads/images/2020/0504/192708_127ceda5_477893.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2020/0504/192733_5402d25d_477893.png"/></td>
    </tr>	 
</table>


 **-提前预定更优惠** qq群交流：1080453739










-
-
-
-



### **下面是供货老版本商城概况：**


本商城系统是一套完整的商城系统，包括小程序+小程序API+后台商城管理！！

   **系统基础组合来源：** 

程序员就要坚持着不要重复造轮子想法，所有就在码云里找自己合适的轮子：：

1、若依管理后台:用它做后台管理

2、Light Security：用它主要来对接小程序API

当然他们本身已经完成了很多功能，这里不多说，大家可以直接去码云搜索即可，后面会放上他们连接。

本系统主要包括三部分：

第一：商城后台，完成完整的商城管理系统，目前基本已经完成，至于有bug大家可以直接issue或者进群交流（后面会打出来的(⊙o⊙)？！！），
功能大概包括：商品管理，商品多规格属性管理，商品分类，商品品牌，商品订单，会员信息等等。
涉及到技术： **springboot+Thymeleaf+mybatis** 

大致效果如下：
<table>
    <tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0830/180715_7394c4c4_477893.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2019/0830/180732_b2055281_477893.png"/></td>
    </tr>
    <tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0830/180740_8cc475d4_477893.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2019/0830/180752_45e9b4ce_477893.png"/></td>
    </tr>
    <tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0830/180800_31df8e35_477893.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2019/0830/180809_4147d2e2_477893.png"/></td>
    </tr>	 
</table>


    

第二：小程序API，目前API模块已经基本完成,
涉及到技术： **springboot+mybatis-plus+jwt+mapstruct+lombok+redis+swagger** 

大致如下效果：

<table>
    <tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0914/145813_85d0bdc7_477893.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2019/0916/164354_34a2cf90_477893.png"/></td>
    </tr>
     <tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0928/174546_7531f0ca_477893.png"/></td>
        <td></td>
    </tr>	 
</table>



第三：mpvue小程序，小程序主要是用 **mpvue** 框架开发，这里就不说它的好处。



 mpvue 小程序运行步骤：

1、安装node环境
     node 是8版本

2、设置npm淘宝镜像

npm set registry https://registry.npm.taobao.org/

3、npm install

4、npm run dev


大致效果如下：
<table>
    <tr>
        <td><img  src="https://images.gitee.com/uploads/images/2019/0830/181113_1b7f255a_477893.jpeg"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2019/0830/181123_0c86c94c_477893.jpeg"/></td>
    </tr>
    <tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0830/181132_b729a89b_477893.jpeg"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2019/0830/181140_08e362ad_477893.jpeg"/></td>
    </tr>
    <tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0830/181150_b5e0cd3e_477893.jpeg"/></td>
        <td></td>
    </tr>	 
</table>




**学习交流** 


其他：欢迎star或者提出你们宝贵的建议哦！！！

****Requirement:****  
 - JDK1.8+,
 - mysql5.7+

 **特别鸣谢** 

若依系统：[ruoyi](https://gitee.com/y_project/RuoYi)

简单jwt权限：[Light Security](https://gitee.com/itmuch/light-security)
     
  

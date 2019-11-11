  **商城概况：**

本商城系统是一套完整的商城系统，包括小程序+小程序API+后台商城管理，这个项目属于我原有的一个laravel项目，
现在迁移Java springboot平台，大概情况这样，废话不多说！！

#### 推荐另外前后端分离商城系统，如下：

|     |  后台系统源码 |   后台系统前端源码  |
|---  |--- | --- |
|   码云  |  https://gitee.com/guchengwuyue/yshopmall  | https://gitee.com/guchengwuyue/yshopmall_qd |
|   github   |   https://github.com/guchengwuyue/yshopmall | https://github.com/guchengwuyue/yshopmall_qd  |

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



第三：mpvue小程序，小程序主要是用 **mpvue** 框架开发，这里就不说它的好处。（小程序代码，可以加群(后面有)共享文件获取）,

小程序在线体验地址：

![输入图片说明](https://images.gitee.com/uploads/images/2019/0928/175139_e07592c8_477893.jpeg "qrcode_for_gh_be92ee602481_258.jpg")，关注公众号菜单Java商城即可体验哦。

<table>
    <tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/1009/202320_8a00efb0_477893.jpeg"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2019/1009/202331_5d5548e7_477893.jpeg"/></td>
    </tr>	 
</table>


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

qq群:907721261

其他：欢迎star或者提出你们宝贵的建议哦！！！

****Requirement:****  
 - JDK1.8+,
 - mysql5.7+

 **特别鸣谢** 

若依系统：[ruoyi](https://gitee.com/y_project/RuoYi)

简单jwt权限：[Light Security](https://gitee.com/itmuch/light-security)
     
  

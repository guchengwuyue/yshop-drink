  **商城概况：**

    本商城系统是一套完整的商城系统，包括小程序+小程序API+后台管理，这个项目属于我原有的一个php项目，现在迁移Java语音平台，所以本身这个系统是有完整的php版本，但是原有版本是一个客户肯定是没法开源的，所以我准备用另个语言重新它然后开源出去，大概情况这样，废话不多啦！！

   **系统基础组合来源：** 

    程序员就要坚持着不要重复造轮子想法，所有就在码云里找自己合适的轮子，终于花费了一些时间找到自己毕竟满意：
    1、若依管理后台:用它做后台管理
    2、Light Security：用它主要来对接小程序API
    当然他们本身已经完成了很多功能，这里不多说，大家可以直接去码云搜索即可，后面会放上他们连接。
    本系统主要分为三步走：
    第一步：迁移后台，完成完整的商城管理系统，目前基本已经完成，至于有bug大家可以直接issue或者进群交流（后面会打出来的(⊙o⊙)？！！），
    功能大概包括：商品管理，商品多规格属性管理，商品分类，商品品牌，商品订单，会员信息等等。
    涉及到技术：springboot+Thymeleaf+mybatis

    大致效果如下：
    

    第二步：迁移小程序API，此步骤刚刚启动，大家别慌，但是项目里我已经把API该有的已经封装在了系统了
    涉及到技术：springboot+mybatis-plus+jwt+mapstruct+lombok+redis+swagger
    大致如下效果：
      1、http://localhost:8009/auth/login 登陆授权


    2、http://localhost:8009/user/info  获取用户信息

    第三步：迁移小程序，小程序主要是用mpvue框架开发，这里就不说它的好处。
    大致效果如下：


 **学习交流** 
    qq群:

  ****Requirement:****  
    - JDK1.8+,
    - mysql5.7+

 **特别鸣谢** 

  [ruoyi](https://gitee.com/y_project/RuoYi)
  [Light Security](https://gitee.com/itmuch/light-security)
     
  

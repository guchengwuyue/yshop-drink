package co.yixiang.yshop.module.shop.controller.app.service.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
* 我的服务
*/
@Data
public class AppServiceVO {

    @Schema(description = "id", required = true, example = "6335")
    private Long id;

    @Schema(description = "标题", required = true, example = "张三")
    private String name;

    @Schema(description = "图标", required = true)
    private String image;

    @Schema(description = "类型:pages=页面,miniprogram=跳转小程序,menu=菜单,content=内容,call=电话", required = true, example = "1")
    private String type;

    @Schema(description = "详情", required = true)
    private String content;

    @Schema(description = "父级id", required = true, example = "25371")
    private Integer pid;

    @Schema(description = "小程序app_id", required = true, example = "20728")
    private String appId;

    @Schema(description = "页面路径", required = true)
    private String pages;

    @Schema(description = "电话", required = true)
    private String phone;


}

package co.yixiang.yshop.module.shop.controller.admin.service.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import jakarta.validation.constraints.*;

/**
* 我的服务 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ServiceBaseVO {

    @Schema(description = "标题", required = true, example = "张三")
    @NotNull(message = "标题不能为空")
    private String name;

    @Schema(description = "图标", required = true)
    @NotNull(message = "图标不能为空")
    private String image;

    @Schema(description = "类型:pages=页面,miniprogram=跳转小程序,menu=菜单,content=内容,call=电话", required = true, example = "1")
    @NotNull(message = "类型不能为空")
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

    @Schema(description = "权重", required = true)
    private Integer weigh;

    @Schema(description = "状态:0=下架,1=上架", required = true, example = "2")
    private Integer status;

}

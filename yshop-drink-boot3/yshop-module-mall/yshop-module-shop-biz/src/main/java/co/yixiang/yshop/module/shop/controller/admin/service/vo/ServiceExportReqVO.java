package co.yixiang.yshop.module.shop.controller.admin.service.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import co.yixiang.yshop.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static co.yixiang.yshop.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 我的服务 Excel 导出 Request VO，参数和 ServicePageReqVO 是一致的")
@Data
public class ServiceExportReqVO {

    @Schema(description = "标题", example = "张三")
    private String name;

    @Schema(description = "图标")
    private String image;

    @Schema(description = "类型:pages=页面,miniprogram=跳转小程序,menu=菜单,content=内容,call=电话", example = "1")
    private String type;

    @Schema(description = "详情")
    private String content;

    @Schema(description = "父级id", example = "25371")
    private Integer pid;

    @Schema(description = "小程序app_id", example = "20728")
    private String appId;

    @Schema(description = "页面路径")
    private String pages;

    @Schema(description = "电话")
    private String phone;

    @Schema(description = "权重")
    private Integer weigh;

    @Schema(description = "状态:0=下架,1=上架", example = "2")
    private Boolean status;

    @Schema(description = "添加时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}

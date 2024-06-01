package co.yixiang.yshop.module.score.controller.admin.scoreorder.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import co.yixiang.yshop.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static co.yixiang.yshop.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 积分商城订单 Excel 导出 Request VO，参数和 ScoreOrderPageReqVO 是一致的")
@Data
public class ScoreOrderExportReqVO {

    @Schema(description = "用户id", example = "28397")
    private Integer userId;

    @Schema(description = "商品id", example = "31639")
    private Integer productId;

    @Schema(description = "数量")
    private Integer number;

    @Schema(description = "单个商品积分")
    private Integer score;

    @Schema(description = "总消耗积分")
    private Integer totalScore;

    @Schema(description = "下单ip")
    private String ip;

    @Schema(description = "快递编号")
    private String expressNumber;

    @Schema(description = "快递公司")
    private String expressCompany;

    @Schema(description = "收货名称", example = "王五")
    private String customerName;

    @Schema(description = "收货电话")
    private String customerPhone;

    @Schema(description = "收货地址")
    private String customerAddress;

    @Schema(description = "订单状态:0=取消订单,1=正常啊", example = "1")
    private Boolean status;

    @Schema(description = "已支付:0=否", example = "12822")
    private Integer havePaid;

    @Schema(description = "已发货:0=否")
    private Integer haveDelivered;

    @Schema(description = "已收货:0=否")
    private Integer haveReceived;

    @Schema(description = "添加时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}

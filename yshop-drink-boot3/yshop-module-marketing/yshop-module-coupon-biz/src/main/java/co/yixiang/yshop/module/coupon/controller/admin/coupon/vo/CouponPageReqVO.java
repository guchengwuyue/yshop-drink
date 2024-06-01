package co.yixiang.yshop.module.coupon.controller.admin.coupon.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import co.yixiang.yshop.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static co.yixiang.yshop.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 优惠券分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CouponPageReqVO extends PageParam {

    @Schema(description = "店铺id,0表示通用", example = "8915")
    private String shopId;

    @Schema(description = "店铺名称逗号隔开", example = "王五")
    private String shopName;

    @Schema(description = "优惠券名称")
    private String title;

    @Schema(description = "是否上架")
    private Integer isSwitch;

    @Schema(description = "消费多少可用")
    private BigDecimal least;

    @Schema(description = "优惠券金额")
    private BigDecimal value;

    @Schema(description = "开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] startTime;

    @Schema(description = "结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] endTime;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "权重")
    private Integer weigh;

    @Schema(description = "可用类型:0=通用,1=自取,2=外卖", example = "2")
    private Integer type;

    @Schema(description = "兑换码")
    private String exchangeCode;

    @Schema(description = "已领取")
    private Integer receive;

    @Schema(description = "发行数量")
    private Integer distribute;

    @Schema(description = "所需积分")
    private Integer score;

    @Schema(description = "使用说明")
    private String instructions;

    @Schema(description = "图片")
    private String image;

    @Schema(description = "限领数量")
    private Integer limit;

}

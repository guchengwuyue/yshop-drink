package co.yixiang.yshop.module.coupon.controller.admin.couponuser.vo;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import co.yixiang.yshop.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;

import static co.yixiang.yshop.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 用户领的优惠券 Excel 导出 Request VO，参数和 CouponUserPageReqVO 是一致的")
@Data
public class CouponUserExportReqVO {

    @Schema(description = "店铺id,0表示通用", example = "32582")
    private String shopId;

    @Schema(description = "店铺名称逗号隔开", example = "yshop")
    private String shopName;

    @Schema(description = "优惠券名称")
    private String title;

    @Schema(description = "消费多少可用")
    private BigDecimal least;

    @Schema(description = "优惠券金额")
    private BigDecimal value;

    @Schema(description = "开始时间", required = true)
    @NotNull(message = "开始时间不能为空")
    private LocalDateTime startTime;

    @Schema(description = "结束时间", required = true)
    @NotNull(message = "结束时间不能为空")
    private LocalDateTime endTime;

    @Schema(description = "创建时间", required = true)
    @NotNull(message = "创建时间不能为空")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", required = true)
    @NotNull(message = "更新时间不能为空")
    private LocalDateTime updateTime;

    @Schema(description = "可用类型:0=通用,1=自取,2=外卖", example = "2")
    private Boolean type;

    @Schema(description = "消耗积分")
    private Integer score;

    @Schema(description = "使用说明")
    private String instructions;

    @Schema(description = "图片")
    private String image;

    @Schema(description = "用户id", example = "20961")
    private Integer userId;

    @Schema(description = "已使用:0=否,1=是", example = "1")
    private Boolean status;

    @Schema(description = "优惠券id", example = "23870")
    private Integer couponId;

    @Schema(description = "兑换码")
    private String exchangeCode;

}

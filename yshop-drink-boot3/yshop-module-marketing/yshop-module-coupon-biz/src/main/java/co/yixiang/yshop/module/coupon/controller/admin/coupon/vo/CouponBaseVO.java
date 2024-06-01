package co.yixiang.yshop.module.coupon.controller.admin.coupon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static co.yixiang.yshop.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
* 优惠券 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class CouponBaseVO {

    @Schema(description = "店铺id,0表示通用", required = true, example = "8915")
    private String shopId;

    private String shopName;

    @Schema(description = "优惠券名称", required = true)
    @NotNull(message = "优惠券名称不能为空")
    private String title;

    @Schema(description = "是否上架", required = true)
    @NotNull(message = "是否上架不能为空")
    private Integer isSwitch;

    @Schema(description = "消费多少可用", required = true)
    @NotNull(message = "消费多少可用不能为空")
    private BigDecimal least;

    @Schema(description = "优惠券金额", required = true)
    @NotNull(message = "优惠券金额不能为空")
    private BigDecimal value;

    @Schema(description = "开始时间", required = true)
    @NotNull(message = "开始时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime startTime;

    @Schema(description = "结束时间", required = true)
    @NotNull(message = "结束时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endTime;

    @Schema(description = "权重", required = true)
    private Integer weigh;

    @Schema(description = "可用类型:0=通用,1=自取,2=外卖", required = true, example = "2")
    @NotNull(message = "可用类型不能为空")
    private Integer type;

    @Schema(description = "兑换码")
    private String exchangeCode;

    @Schema(description = "已领取", required = true)
    private Integer receive;

    @Schema(description = "发行数量", required = true)
    @NotNull(message = "发行数量不能为空")
    private Integer distribute;

    @Schema(description = "所需积分", required = true)
    private Integer score;

    @Schema(description = "使用说明", required = true)
    private String instructions;

    @Schema(description = "图片", required = true)
    private String image;

    @Schema(description = "限领数量")
    @NotNull(message = "限领数量不能为空")
    private Integer limit;

}

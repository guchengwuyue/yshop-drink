package co.yixiang.yshop.module.coupon.controller.admin.couponuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import jakarta.validation.constraints.*;

/**
* 用户领的优惠券 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class CouponUserBaseVO {

    @Schema(description = "店铺id,0表示通用", required = true, example = "32582")
    @NotNull(message = "店铺id,0表示通用不能为空")
    private String shopId;

    @Schema(description = "店铺名称逗号隔开", example = "yshop")
    private String shopName;

    @Schema(description = "优惠券名称", required = true)
    @NotNull(message = "优惠券名称不能为空")
    private String title;

    @Schema(description = "消费多少可用", required = true)
    @NotNull(message = "消费多少可用不能为空")
    private BigDecimal least;

    @Schema(description = "优惠券金额", required = true)
    @NotNull(message = "优惠券金额不能为空")
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

    @Schema(description = "可用类型:0=通用,1=自取,2=外卖", required = true, example = "2")
    @NotNull(message = "可用类型:0=通用,1=自取,2=外卖不能为空")
    private Integer type;

    @Schema(description = "消耗积分", required = true)
    @NotNull(message = "消耗积分不能为空")
    private Integer score;

    @Schema(description = "使用说明", required = true)
    @NotNull(message = "使用说明不能为空")
    private String instructions;

    @Schema(description = "图片", required = true)
    @NotNull(message = "图片不能为空")
    private String image;

    @Schema(description = "用户id", required = true, example = "20961")
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    @Schema(description = "已使用:0=否,1=是", required = true, example = "1")
    @NotNull(message = "已使用:0=否,1=是不能为空")
    private Integer status;

    @Schema(description = "优惠券id", required = true, example = "23870")
    @NotNull(message = "优惠券id不能为空")
    private Integer couponId;

    @Schema(description = "兑换码")
    private String exchangeCode;

}

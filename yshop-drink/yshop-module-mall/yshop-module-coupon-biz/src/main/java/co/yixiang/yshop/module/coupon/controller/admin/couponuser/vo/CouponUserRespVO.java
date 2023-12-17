package co.yixiang.yshop.module.coupon.controller.admin.couponuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 用户领的优惠券 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CouponUserRespVO extends CouponUserBaseVO {

    @Schema(description = "id", required = true, example = "5159")
    private Integer id;

}

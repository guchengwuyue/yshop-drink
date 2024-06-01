package co.yixiang.yshop.module.coupon.controller.admin.couponuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 用户领的优惠券更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CouponUserUpdateReqVO extends CouponUserBaseVO {

    @Schema(description = "id", required = true, example = "5159")
    @NotNull(message = "id不能为空")
    private Integer id;

}

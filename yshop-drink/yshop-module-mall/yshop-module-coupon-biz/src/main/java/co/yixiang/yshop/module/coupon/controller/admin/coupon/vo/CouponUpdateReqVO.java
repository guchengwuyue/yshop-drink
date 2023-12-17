package co.yixiang.yshop.module.coupon.controller.admin.coupon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 优惠券更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CouponUpdateReqVO extends CouponBaseVO {

    @Schema(description = "id", required = true, example = "1582")
    @NotNull(message = "id不能为空")
    private Long id;

}

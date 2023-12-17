package co.yixiang.yshop.module.coupon.controller.admin.coupon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 优惠券 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CouponRespVO extends CouponBaseVO {

    @Schema(description = "id", required = true, example = "1582")
    private Long id;

    @Schema(description = "创建时间", required = true)
    private LocalDateTime createTime;

}

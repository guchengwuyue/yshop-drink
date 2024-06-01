package co.yixiang.yshop.module.coupon.controller.admin.couponuser.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 用户领的优惠券创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CouponUserCreateReqVO extends CouponUserBaseVO {

}

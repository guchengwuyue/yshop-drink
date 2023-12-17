package co.yixiang.yshop.module.coupon.controller.app.coupon.vo;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 领取优惠券 vo
 *
 * @author yshop
 */
@Data
public class AppReceVO {

    /**
     * id
     */
    private Long id;
    /**
     * 优惠券兑换码
     */
    private String code;


}

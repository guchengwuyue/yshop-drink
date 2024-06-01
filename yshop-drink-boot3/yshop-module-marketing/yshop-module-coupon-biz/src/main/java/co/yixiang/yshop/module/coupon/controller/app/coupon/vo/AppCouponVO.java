package co.yixiang.yshop.module.coupon.controller.app.coupon.vo;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 优惠券 vo
 *
 * @author yshop
 */
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppCouponVO {

    /**
     * id
     */
    private Long id;
    /**
     * 店铺id,0表示通用
     */
    private String shopId;
    /**
     * 店铺名称逗号隔开
     */
    private String shopName;
    /**
     * 优惠券名称
     */
    private String title;
    /**
     * 是否上架
     */
    private Integer isSwitch;
    /**
     * 消费多少可用
     */
    private BigDecimal least;
    /**
     * 优惠券金额
     */
    private BigDecimal value;
    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    private LocalDateTime endTime;
    /**
     * 权重
     */
    private Integer weigh;
    /**
     * 可用类型:0=通用,1=自取,2=外卖
     */
    private Integer type;
    /**
     * 兑换码
     */
    private String exchangeCode;
    /**
     * 已领取
     */
    private Integer receive;
    /**
     * 发行数量
     */
    private Integer distribute;
    /**
     * 所需积分
     */
    private Integer score;
    /**
     * 使用说明
     */
    private String instructions;
    /**
     * 图片
     */
    private String image;
    /**
     * 限领数量
     */
    private Integer limit;

    private LocalDateTime createTime;

    /**
     * 是否已领取
     */
    private Integer isReceive;

}

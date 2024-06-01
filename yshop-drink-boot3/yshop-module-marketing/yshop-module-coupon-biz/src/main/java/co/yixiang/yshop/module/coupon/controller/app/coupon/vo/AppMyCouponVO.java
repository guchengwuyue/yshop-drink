package co.yixiang.yshop.module.coupon.controller.app.coupon.vo;

import co.yixiang.yshop.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户领的优惠券vo
 *
 * @author yshop
 */
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppMyCouponVO {

    /**
     * id
     */
    private Integer id;
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
     * 可用类型:0=通用,1=自取,2=外卖
     */
    private Integer type;
    /**
     * 消耗积分
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
     * 用户id
     */
    private Integer userId;
    /**
     * 已使用:0=否,1=是
     */
    private Integer status;
    /**
     * 优惠券id
     */
    private Integer couponId;
    /**
     * 兑换码
     */
    private String exchangeCode;

    private LocalDateTime createTime;

}

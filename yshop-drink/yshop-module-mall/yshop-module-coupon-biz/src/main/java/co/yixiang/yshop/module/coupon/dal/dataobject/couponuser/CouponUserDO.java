package co.yixiang.yshop.module.coupon.dal.dataobject.couponuser;

import lombok.*;

import java.time.LocalDateTime;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import co.yixiang.yshop.framework.mybatis.core.dataobject.BaseDO;

/**
 * 用户领的优惠券 DO
 *
 * @author yshop
 */
@TableName("yshop_coupon_user")
@KeySequence("yshop_coupon_user_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponUserDO extends BaseDO {

    /**
     * id
     */
    @TableId
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

}

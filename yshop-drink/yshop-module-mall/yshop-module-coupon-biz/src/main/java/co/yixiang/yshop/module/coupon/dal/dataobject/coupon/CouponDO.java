package co.yixiang.yshop.module.coupon.dal.dataobject.coupon;

import co.yixiang.yshop.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 优惠券 DO
 *
 * @author yshop
 */
@TableName(value = "yshop_coupon")
@KeySequence("yshop_coupon_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponDO extends BaseDO {

    /**
     * id
     */
    @TableId
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
    @TableField(value = "`limit`")
    private Integer limit;

}

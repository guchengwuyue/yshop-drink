package co.yixiang.yshop.module.score.dal.dataobject.scoreorder;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import co.yixiang.yshop.framework.mybatis.core.dataobject.BaseDO;

/**
 * 积分商城订单 DO
 *
 * @author yshop
 */
@TableName("yshop_score_order")
@KeySequence("yshop_score_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoreOrderDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 用户id
     */
    private Long uid;

    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 数量
     */
    private Integer number;
    /**
     * 单个商品积分
     */
    private Integer score;
    /**
     * 总消耗积分
     */
    private Integer totalScore;
    /**
     * 下单ip
     */
    private String ip;

    /**
     * 快递公司编码
     */
    private String expressSn;
    /**
     * 快递编号
     */
    private String expressNumber;
    /**
     * 快递公司
     */
    private String expressCompany;
    /**
     * 收货名称
     */
    private String customerName;
    /**
     * 收货电话
     */
    private String customerPhone;
    /**
     * 收货地址
     */
    private String customerAddress;
    /**
     * 订单状态:0=取消订单,1=正常啊
     */
    private Boolean status;
    /**
     * 已支付:0=否
     */
    private Integer havePaid;
    /**
     * 已发货:0=否
     */
    private Integer haveDelivered;
    /**
     * 已收货:0=否
     */
    private Integer haveReceived;

}

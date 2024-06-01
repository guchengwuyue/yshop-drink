package co.yixiang.yshop.module.order.dal.dataobject.storeorder;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import co.yixiang.yshop.framework.mybatis.core.dataobject.BaseDO;

/**
 * 订单 DO
 *
 * @author yshop
 */
@TableName("yshop_store_order")
@KeySequence("yshop_store_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreOrderDO extends BaseDO {

    /**
     * 订单ID
     */
    @TableId
    private Long id;
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 额外订单号
     */
    private String extendOrderId;
    /**
     * 用户id
     */
    private Long uid;
    /**
     * 用户姓名
     */
    private String realName;
    /**
     * 用户电话
     */
    private String userPhone;
    /**
     * 详细地址
     */
    private String userAddress;
    /**
     * 购物车id
     */
    private String cartId;
    /**
     * 运费金额
     */
    private BigDecimal freightPrice;
    /**
     * 订单商品总数
     */
    private Integer totalNum;
    /**
     * 订单总价
     */
    private BigDecimal totalPrice;
    /**
     * 邮费
     */
    private BigDecimal totalPostage;
    /**
     * 实际支付金额
     */
    private BigDecimal payPrice;
    /**
     * 支付邮费
     */
    private BigDecimal payPostage;
    /**
     * 抵扣金额
     */
    private BigDecimal deductionPrice;
    /**
     * 优惠券id
     */
    private Integer couponId;
    /**
     * 优惠券金额
     */
    private BigDecimal couponPrice;
    /**
     * 支付状态
     */
    private Integer paid;
    /**
     * 支付时间
     */
    private LocalDateTime payTime;
    /**
     * 支付方式
     */
    private String payType;

    /**
     * 订单类型 购买类型:takein=自取,takeout=外卖
     */
    private String orderType;
    /**
     * 订单状态（-1 : 申请退款 -2 : 退货成功 0：待发货；1：待收货；2：已收货；3：已完成；-1：已退款）
     */
    private Integer status;
    /**
     * 0 未退款 1 申请中 2 已退款
     */
    private Integer refundStatus;
    /**
     * 退款图片
     */
    private String refundReasonWapImg;
    /**
     * 退款用户说明
     */
    private String refundReasonWapExplain;
    /**
     * 退款时间
     */
    private LocalDateTime refundReasonTime;
    /**
     * 前台退款原因
     */
    private String refundReasonWap;
    /**
     * 不退款的理由
     */
    private String refundReason;
    /**
     * 退款金额
     */
    private BigDecimal refundPrice;
    /**
     * 快递公司编号
     */
    private String deliverySn;
    /**
     * 快递名称/送货人姓名
     */
    private String deliveryName;
    /**
     * 发货类型
     */
    private String deliveryType;
    /**
     * 快递单号/手机号
     */
    private String deliveryId;

    /**
     * 发货时间
     */
    private LocalDateTime deliveryTime;
    /**
     * 消费赚取积分
     */
    private BigDecimal gainIntegral;
    /**
     * 使用积分
     */
    private BigDecimal useIntegral;
    /**
     * 实际支付积分
     */
    private BigDecimal payIntegral;
    /**
     * 给用户退了多少积分
     */
    private BigDecimal backIntegral;
    /**
     * 备注
     */
    private String mark;
    /**
     * 唯一id(md5加密)类似id
     */
    @TableField(value = "`unique`")
    private String unique;
    /**
     * 管理员备注
     */
    private String remark;
    /**
     * 商户ID
     */
    private Integer merId;
    /**
     * 拼团产品id0一般产品
     */
    private Long combinationId;
    /**
     * 拼团id 0没有拼团
     */
    private Long pinkId;
    /**
     * 成本价
     */
    private BigDecimal cost;
    /**
     * 秒杀产品ID
     */
    private Long seckillId;
    /**
     * 砍价id
     */
    private Integer bargainId;
    /**
     * 核销码
     */
    private String verifyCode;
    /**
     * 门店id
     */
    private Integer storeId;
    /**
     * 配送方式 1=快递 ，2=门店自提
     */
    private Integer shippingType;
    /**
     * 支付渠道(0微信公众号1微信小程序)
     */
    private Integer isChannel;
    /**
     * 系统删除
     */
    private Integer isSystemDel;

    /**
     * 门店ID
     */
    private Long shopId;

    /**
     * 门店名称
     */
    private String shopName;

    /**
     * 取餐时间
     */
    private LocalDateTime getTime;

    /**
     * 取餐👌
     */
    private Long numberId;




}

package co.yixiang.app.common.persistence.model;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订单主表
 * </p>
 *
 * @author hupeng
 * @since 2019-06-26
 */
@TableName("store_order")
@Data
public class StoreOrder extends Model<StoreOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    /**
     * 订单编号
     */
    @TableField("order_sn")
    private String orderSn;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 订单状态 0-未确认 1-已确认(已收货） 2-已评价
     */
    @TableField("order_status")
    private Integer orderStatus;
    /**
     * 发货状态 0-未发货 1-已发货 2-退货中 3-退货完成
     */
    @TableField("shipping_status")
    private Integer shippingStatus;
    /**
     * 支付状态 0-未支付 1-已支付
     */
    @TableField("pay_status")
    private Integer payStatus;
    /**
     * 收货人
     */
    private String consignee;
    /**
     * 国家
     */
    private Integer country;
    /**
     * 省份
     */
    private Integer province;
    /**
     * 城市
     */
    private Integer city;
    /**
     * 县区
     */
    private Integer district;
    /**
     * 乡镇
     */
    private Integer twon;
    /**
     * 地区地址
     */
    @TableField("city_info")
    private String cityInfo;
    /**
     * 地址详细地址
     */
    private String address;
    /**
     * 邮政编码
     */
    private String zipcode;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 邮件
     */
    private String email;
    /**
     * 收货方式 1-物流/快递 2-自取
     */
    @TableField("receive_type")
    private Integer receiveType;
    /**
     * 物流code 单号
     */
    @TableField("shipping_code")
    private String shippingCode;
    /**
     * 物流名称
     */
    @TableField("shipping_name")
    private String shippingName;
    /**
     * 支付code
     */
    @TableField("pay_code")
    private String payCode;
    /**
     * 支付方式1-微信2-余额3-转账
     */
    @TableField("pay_method")
    private Integer payMethod;
    /**
     * 支付方式为转账的转账id
     */
    @TableField("recharge_id")
    private Integer rechargeId;
    /**
     * 支付方式名称
     */
    @TableField("pay_name")
    private String payName;
    /**
     * 发票抬头
     */
    @TableField("invoice_title")
    private String invoiceTitle;
    /**
     * 纳税人识别号
     */
    private String taxpayer;
    /**
     * 商品总价
     */
    @TableField("goods_price")
    private BigDecimal goodsPrice;
    /**
     * 邮费
     */
    @TableField("shipping_price")
    private BigDecimal shippingPrice;
    /**
     * 使用余额
     */
    @TableField("user_money")
    private BigDecimal userMoney;
    /**
     * 优惠券抵扣
     */
    @TableField("coupon_price")
    private BigDecimal couponPrice;

    /**
     * 使用积分
     */
    private Integer integral;
    /**
     * 使用积分抵多少钱
     */
    @TableField("integral_money")
    private BigDecimal integralMoney;
    /**
     * 应付款金额
     */
    @TableField("order_amount")
    private BigDecimal orderAmount;
    /**
     * 订单总价
     */
    @TableField("total_amount")
    private BigDecimal totalAmount;
    /**
     * 下单时间
     */
    @TableField(value = "add_time",fill = FieldFill.INSERT)
    private Date addTime;
    /**
     * 最后新发货时间
     */
    @TableField("shipping_time")
    private Integer shippingTime;
    /**
     * 收货确认时间
     */
    @TableField("confirm_time")
    private Integer confirmTime;
    /**
     * 支付时间
     */
    @TableField("pay_time")
    private Integer payTime;
    /**
     * 第三方平台交易流水号
     */
    @TableField("transaction_id")
    private String transactionId;
    /**
     * 活动ID
     */
    @TableField("prom_id")
    private Integer promId;
    /**
     * 订单类型：0普通订单4预售订单5虚拟订单6拼团订单
     */
    @TableField("prom_type")
    private Integer promType;
    /**
     * 活动id
     */
    @TableField("order_prom_id")
    private Integer orderPromId;
    /**
     * 活动优惠金额
     */
    @TableField("order_prom_amount")
    private BigDecimal orderPromAmount;
    @TableField("edit_money")
    private String editMoney;
    /**
     * 价格调整(会员折扣金额)
     */
    private BigDecimal discount;
    /**
     * 用户备注
     */
    @TableField("user_note")
    private String userNote;
    /**
     * 管理员备注
     */
    @TableField("admin_note")
    private String adminNote;
    /**
     * 父单单号
     */
    @TableField("parent_sn")
    private String parentSn;
    /**
     * 0-普通订单 1-未分成 2-已分成
     */
    @TableField("is_distribut")
    private Integer isDistribut;
    /**
     * 订金
     */
    @TableField("paid_money")
    private BigDecimal paidMoney;
    /**
     * 删除|取消时间
     */
    @TableField("deleted_time")
    private Integer deletedTime;
    /**
     * 用户假删除标识,1:删除,0未删除
     */
    private Integer deleted;





}

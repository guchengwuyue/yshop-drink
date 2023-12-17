package co.yixiang.yshop.module.order.controller.admin.storeorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
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

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 订单 Excel VO
 *
 * @author yshop
 */
@Data
public class StoreOrderExcelVO {

    @ExcelProperty("订单ID")
    private Long id;

    @ExcelProperty("订单号")
    private String orderId;

    @ExcelProperty("额外订单号")
    private String extendOrderId;

    @ExcelProperty("用户id")
    private Long uid;

    @ExcelProperty("用户姓名")
    private String realName;

    @ExcelProperty("用户电话")
    private String userPhone;

    @ExcelProperty("详细地址")
    private String userAddress;

    @ExcelProperty("购物车id")
    private String cartId;

    @ExcelProperty("运费金额")
    private BigDecimal freightPrice;

    @ExcelProperty("订单商品总数")
    private Integer totalNum;

    @ExcelProperty("订单总价")
    private BigDecimal totalPrice;

    @ExcelProperty("邮费")
    private BigDecimal totalPostage;

    @ExcelProperty("实际支付金额")
    private BigDecimal payPrice;

    @ExcelProperty("支付邮费")
    private BigDecimal payPostage;

    @ExcelProperty("抵扣金额")
    private BigDecimal deductionPrice;

    @ExcelProperty("优惠券id")
    private Integer couponId;

    @ExcelProperty("优惠券金额")
    private BigDecimal couponPrice;

    @ExcelProperty("支付状态")
    private Integer paid;

    @ExcelProperty("支付时间")
    private LocalDateTime payTime;

    @ExcelProperty("支付方式")
    private String payType;

    @ExcelProperty("订单状态（-1 : 申请退款 -2 : 退货成功 0：待发货；1：待收货；2：已收货；3：已完成；-1：已退款）")
    private Integer status;

    @ExcelProperty("0 未退款 1 申请中 2 已退款")
    private Integer refundStatus;

    @ExcelProperty("退款图片")
    private String refundReasonWapImg;

    @ExcelProperty("退款用户说明")
    private String refundReasonWapExplain;

    @ExcelProperty("退款时间")
    private LocalDateTime refundReasonTime;

    @ExcelProperty("前台退款原因")
    private String refundReasonWap;

    @ExcelProperty("不退款的理由")
    private String refundReason;

    @ExcelProperty("退款金额")
    private BigDecimal refundPrice;

    @ExcelProperty("快递公司编号")
    private String deliverySn;

    @ExcelProperty("快递名称/送货人姓名")
    private String deliveryName;

    @ExcelProperty("发货类型")
    private String deliveryType;

    @ExcelProperty("快递单号/手机号")
    private String deliveryId;

    @ExcelProperty("消费赚取积分")
    private BigDecimal gainIntegral;

    @ExcelProperty("使用积分")
    private BigDecimal useIntegral;

    @ExcelProperty("实际支付积分")
    private BigDecimal payIntegral;

    @ExcelProperty("给用户退了多少积分")
    private BigDecimal backIntegral;

    @ExcelProperty("备注")
    private String mark;

    @ExcelProperty("唯一id(md5加密)类似id")
    private String unique;

    @ExcelProperty("管理员备注")
    private String remark;

    @ExcelProperty("商户ID")
    private Integer merId;

    @ExcelProperty("拼团产品id0一般产品")
    private Long combinationId;

    @ExcelProperty("拼团id 0没有拼团")
    private Long pinkId;

    @ExcelProperty("成本价")
    private BigDecimal cost;

    @ExcelProperty("秒杀产品ID")
    private Long seckillId;

    @ExcelProperty("砍价id")
    private Integer bargainId;

    @ExcelProperty("核销码")
    private String verifyCode;

    @ExcelProperty("门店id")
    private Integer storeId;

    @ExcelProperty("配送方式 1=快递 ，2=门店自提")
    private Integer shippingType;

    @ExcelProperty("支付渠道(0微信公众号1微信小程序)")
    private Integer isChannel;

    @ExcelProperty("系统删除")
    private Integer isSystemDel;

    @ExcelProperty("添加时间")
    private LocalDateTime createTime;

}

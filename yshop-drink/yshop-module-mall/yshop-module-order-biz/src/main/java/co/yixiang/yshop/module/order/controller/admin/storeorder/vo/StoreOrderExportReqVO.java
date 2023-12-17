package co.yixiang.yshop.module.order.controller.admin.storeorder.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import co.yixiang.yshop.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static co.yixiang.yshop.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 订单 Excel 导出 Request VO，参数和 StoreOrderPageReqVO 是一致的")
@Data
public class StoreOrderExportReqVO {

    @Schema(description = "订单号", example = "20527")
    private String orderId;

    @Schema(description = "额外订单号", example = "12452")
    private String extendOrderId;

    @Schema(description = "用户id", example = "8323")
    private Long uid;

    @Schema(description = "用户姓名", example = "张三")
    private String realName;

    @Schema(description = "用户电话")
    private String userPhone;

    @Schema(description = "详细地址")
    private String userAddress;

    @Schema(description = "购物车id", example = "23301")
    private String cartId;

    @Schema(description = "运费金额", example = "637")
    private BigDecimal freightPrice;

    @Schema(description = "订单商品总数")
    private Integer totalNum;

    @Schema(description = "订单总价", example = "31659")
    private BigDecimal totalPrice;

    @Schema(description = "邮费")
    private BigDecimal totalPostage;

    @Schema(description = "实际支付金额", example = "19682")
    private BigDecimal payPrice;

    @Schema(description = "支付邮费")
    private BigDecimal payPostage;

    @Schema(description = "抵扣金额", example = "16463")
    private BigDecimal deductionPrice;

    @Schema(description = "优惠券id", example = "3299")
    private Integer couponId;

    @Schema(description = "优惠券金额", example = "22157")
    private BigDecimal couponPrice;

    @Schema(description = "支付状态", example = "11728")
    private Integer paid;

    @Schema(description = "支付时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] payTime;

    @Schema(description = "支付方式", example = "2")
    private String payType;

    @Schema(description = "订单状态（-1 : 申请退款 -2 : 退货成功 0：待发货；1：待收货；2：已收货；3：已完成；-1：已退款）", example = "1")
    private Integer status;

    @Schema(description = "0 未退款 1 申请中 2 已退款", example = "2")
    private Integer refundStatus;

    @Schema(description = "退款图片")
    private String refundReasonWapImg;

    @Schema(description = "退款用户说明")
    private String refundReasonWapExplain;

    @Schema(description = "退款时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] refundReasonTime;

    @Schema(description = "前台退款原因")
    private String refundReasonWap;

    @Schema(description = "不退款的理由", example = "不喜欢")
    private String refundReason;

    @Schema(description = "退款金额", example = "7547")
    private BigDecimal refundPrice;

    @Schema(description = "快递公司编号")
    private String deliverySn;

    @Schema(description = "快递名称/送货人姓名", example = "张三")
    private String deliveryName;

    @Schema(description = "发货类型", example = "1")
    private String deliveryType;

    @Schema(description = "快递单号/手机号", example = "24798")
    private String deliveryId;

    @Schema(description = "消费赚取积分")
    private BigDecimal gainIntegral;

    @Schema(description = "使用积分")
    private BigDecimal useIntegral;

    @Schema(description = "实际支付积分")
    private BigDecimal payIntegral;

    @Schema(description = "给用户退了多少积分")
    private BigDecimal backIntegral;

    @Schema(description = "备注")
    private String mark;

    @Schema(description = "唯一id(md5加密)类似id")
    private String unique;

    @Schema(description = "管理员备注", example = "随便")
    private String remark;

    @Schema(description = "商户ID", example = "8499")
    private Integer merId;

    @Schema(description = "拼团产品id0一般产品", example = "3865")
    private Long combinationId;

    @Schema(description = "拼团id 0没有拼团", example = "8463")
    private Long pinkId;

    @Schema(description = "成本价")
    private BigDecimal cost;

    @Schema(description = "秒杀产品ID", example = "21525")
    private Long seckillId;

    @Schema(description = "砍价id", example = "5132")
    private Integer bargainId;

    @Schema(description = "核销码")
    private String verifyCode;

    @Schema(description = "门店id", example = "12064")
    private Integer storeId;

    @Schema(description = "配送方式 1=快递 ，2=门店自提", example = "2")
    private Integer shippingType;

    @Schema(description = "支付渠道(0微信公众号1微信小程序)")
    private Integer isChannel;

    @Schema(description = "系统删除")
    private Integer isSystemDel;

    @Schema(description = "添加时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}

package co.yixiang.yshop.module.order.controller.app.order.vo;


import co.yixiang.yshop.module.order.dal.dataobject.storeordercartinfo.StoreOrderCartInfoDO;
import co.yixiang.yshop.module.order.service.storeorder.dto.StatusDto;
import co.yixiang.yshop.module.product.controller.app.cart.vo.AppStoreCartQueryVo;
import co.yixiang.yshop.module.store.controller.app.storeshop.vo.AppStoreShopVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2023-6-19
 */
@Data
@Schema(description = "用户 APP - 订单表查询参数")
public class AppStoreOrderQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "订单ID", required = true)
    private Long id;

    @Schema(description = "订单号", required = true)
    private String orderId;

    private String extendOrderId;

    @Schema(description = "用户id", required = true)
    private Long uid;

    @Schema(description = "用户姓名", required = true)
    private String realName;

    @Schema(description = "用户电话", required = true)
    private String userPhone;

    @Schema(description = "详细地址", required = true)
    private String userAddress;

    @Schema(description = "购物车id", required = true)
    private String cartId;

    @Schema(description = "购物车信息", required = true)
    private List<StoreOrderCartInfoDO> cartInfo;

    @Schema(description = "订单信息合集", required = true)
    private StatusDto statusDto;

    @Schema(description = "运费金额", required = true)
    private BigDecimal freightPrice;

    @Schema(description = "订单商品总数", required = true)
    private Integer totalNum;

    @Schema(description = "订单总价", required = true)
    private BigDecimal totalPrice;

    @Schema(description = "邮费", required = true)
    private BigDecimal totalPostage;

    @Schema(description = "实际支付金额", required = true)
    private BigDecimal payPrice;

    @Schema(description = "实际支付积分", required = true)
    private BigDecimal payIntegral;

    @Schema(description = "支付邮费", required = true)
    private BigDecimal payPostage;

    @Schema(description = "抵扣金额", required = true)
    private BigDecimal deductionPrice;

    @Schema(description = "优惠券id", required = true)
    private Integer couponId;

    @Schema(description = "优惠券金额", required = true)
    private BigDecimal couponPrice;

    @Schema(description = "支付状态", required = true)
    private Integer paid;

    @Schema(description = "支付时间", required = true)
    private LocalDateTime payTime;

    @Schema(description = "支付方式", required = true)
    private String payType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Schema(description = "创建时间", required = true)
    private LocalDateTime createTime;

    @Schema(description = "订单状态（-1 : 申请退款 -2 : 退货成功 0：待发货；1：待收货；2：已收货；3：待评价；-1：已退款", required = true)
    private Integer status;

    @Schema(description = "0 未退款 1 申请中 2 已退款", required = true)
    private Integer refundStatus;

    @Schema(description = "退款图片", required = true)
    private String refundReasonWapImg;

    @Schema(description = "退款用户说明", required = true)
    private String refundReasonWapExplain;

    @Schema(description = "退款时间", required = true)
    private LocalDateTime refundReasonTime;

    @Schema(description = "前台退款原因", required = true)
    private String refundReasonWap;

    @Schema(description = "不退款的理由", required = true)
    private String refundReason;

    @Schema(description = "退款金额", required = true)
    private BigDecimal refundPrice;

    @Schema(description = "快递名称/送货人姓名", required = true)
    private String deliveryName;

    @Schema(description = "快递公司编号", required = true)
    private String deliverySn;

    @Schema(description = "发货类型", required = true)
    private String deliveryType;

    @Schema(description = "快递单号/手机号", required = true)
    private String deliveryId;

    @Schema(description = "发货时间", required = true)
    private LocalDateTime deliveryTime;

    @Schema(description = "消费赚取积分", required = true)
    private BigDecimal gainIntegral;

    @Schema(description = "使用积分", required = true)
    private BigDecimal useIntegral;

    @Schema(description = "给用户退了多少积分", required = true)
    private BigDecimal backIntegral;

    @Schema(description = "备注", required = true)
    private String mark;

    @Schema(description = "确认订单返回的key", required = true)
    private String unique;

    @Schema(description = "管理员备注", required = true)
    private String remark;

    @Schema(description = "拼团产品id0一般产品", required = true)
    private Long combinationId;

    @Schema(description = "拼团id 0没有拼团", required = true)
    private Long pinkId;

    @Schema(description = "成本价", required = true)
    private BigDecimal cost;

    @Schema(description = "秒杀产品ID", required = true)
    private Long seckillId;


    @Schema(description = "配送方式 1=快递 ，2=门店自提\"", required = true)
    private Integer shippingType;

    @Schema(description = "取餐时间", required = true)
    private LocalDateTime getTime;

    @Schema(description = "取餐号", required = true)
    private Integer numberId;

    @Schema(description = "购买类型:takein=自取,takeout=外卖", required = true)
    private String orderType;

    @Schema(description = "门店", required = true)
    private AppStoreShopVO shop;

    @Schema(description = "门店id", required = true)
    private Long shopId;

    @Schema(description = "前面等等的制作数量", required = true)
    private Long preNum;

    @Schema(description = "店铺名称", required = true)
    private String shopName;

    @Schema(description = "桌面ID", required = true)
    private Long deskId;

    @Schema(description = "桌号", required = true)
    private String deskNumber;

    @Schema(description = "就餐人数", required = true)
    private Integer deskPeople;





}

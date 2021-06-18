package co.yixiang.app.modular.shop.service.dto;


import co.yixiang.app.common.persistence.model.StoreOrderGoods;
import co.yixiang.app.common.persistence.model.StoreOrderGoods;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


@Data
public class OrderDTO  implements Serializable{

    private static final long serialVersionUID = 1L;


    private Integer orderId;

    private String orderSn;

    private Integer userId;
    /**
     * 订单状态 0-未确认 1-已确认(已收货） 2-已评价
     */
    private Integer orderStatus;
    /**
     * 发货状态 0-未发货 1-已发货 2-退货中 3-退货完成
     */
    private Integer shippingStatus;
    /**
     * 支付状态 0-未支付 1-已支付
     */
    private Integer payStatus;
    /**
     * 收货人
     */
    private String consignee;


    /**
     * 地区地址
     */
    private String cityInfo;
    /**
     * 地址详细地址
     */
    private String address;

    /**
     * 手机
     */
    private String mobile;


    /**
     * 物流code 单号
     */
    private String shippingCode;
    /**
     * 物流名称
     */
    private String shippingName;


    /**
     * 优惠券抵扣
     */
    private BigDecimal couponPrice;


    /**
     * 应付款金额
     */
    private BigDecimal orderAmount;
    /**
     * 订单总价
     */
    private BigDecimal totalAmount;

    private List<StoreOrderGoods> goodsList;

    private int status;


}

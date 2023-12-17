package co.yixiang.yshop.module.order.dal.dataobject.storeordercartinfo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import co.yixiang.yshop.framework.mybatis.core.dataobject.BaseDO;

/**
 * 订单购物详情 DO
 *
 * @author yshop
 */
@TableName("yshop_store_order_cart_info")
@KeySequence("yshop_store_order_cart_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreOrderCartInfoDO  {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 订单id
     */
    private Long oid;
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 购物车id
     */
    private Long cartId;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 购买东西的详细信息
     */
    private String cartInfo;
    /**
     * 唯一id
     */
    @TableField(value = "`unique`")
    private String unique;
    /**
     * 是否能售后0不能1能
     */
    private Integer isAfterSales;

    /**
     * 商品名称
     */
    private String title;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 规格
     */
    private String spec;

    /**
     * 价格
     */
    private BigDecimal price;


}

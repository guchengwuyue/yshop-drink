package com.ruoyi.app.common.persistence.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.ruoyi.app.modular.shop.service.dto.CartAttrDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 订单商品表与订单主表关联
 * </p>
 *
 * @author hupeng
 * @since 2019-09-13
 */
@TableName("store_order_goods")
@Data
public class StoreOrderGoods extends Model<StoreOrderGoods> {

    private static final long serialVersionUID = 1L;

    /**
     * 表id自增
     */
    @TableId(value = "rec_id", type = IdType.AUTO)
    private Integer recId;
    /**
     * 订单id
     */
    @TableField("order_id")
    private Integer orderId;
    /**
     * 商品id
     */
    @TableField("goods_id")
    private Integer goodsId;
    /**
     * 商品名称
     */
    @TableField("goods_name")
    private String goodsName;

    @TableField("goods_logo")
    private String goodsLogo;
    /**
     * 商品货号
     */
    @TableField("goods_sn")
    private String goodsSn;
    /**
     * 购买数量
     */
    @TableField("goods_num")
    private Integer goodsNum;
    /**
     * 市场价
     */
    @TableField("market_price")
    private BigDecimal marketPrice;
    /**
     * 本店价(供货价)
     */
    @TableField("goods_price")
    private BigDecimal goodsPrice;
    /**
     * 商品成本价
     */
    @TableField("cost_price")
    private BigDecimal costPrice;
    /**
     * 会员折扣价
     */
    @TableField("member_goods_price")
    private BigDecimal memberGoodsPrice;
    /**
     * 购买商品赠送积分
     */
    @TableField("give_integral")
    private Integer giveIntegral;
    /**
     * 商品规格key
     */
    @TableField("spec_key")
    private String specKey;
    /**
     * 规格对应的中文名字
     */
    @TableField("spec_key_name")
    private String specKeyName;
    /**
     * 条码
     */
    @TableField("bar_code")
    private String barCode;
    /**
     * 是否评价
     */
    @TableField("is_comment")
    private Integer isComment;
    /**
     * 0 普通订单,1 限时抢购, 2 团购 , 3 促销优惠,4预售
     */
    @TableField("prom_type")
    private Integer promType;
    /**
     * 活动id
     */
    @TableField("prom_id")
    private Integer promId;
    /**
     * 0未发货，1已发货，2已换货，3已退货
     */
    @TableField("is_send")
    private Integer isSend;
    /**
     * 发货单ID
     */
    @TableField("delivery_id")
    private Integer deliveryId;
    /**
     * sku
     */
    private String sku;

    @TableField(exist = false)
    private List<CartAttrDTO> specList;



}

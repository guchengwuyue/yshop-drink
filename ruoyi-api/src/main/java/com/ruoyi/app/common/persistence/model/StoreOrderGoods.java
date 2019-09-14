package com.ruoyi.app.common.persistence.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 订单商品表与订单主表关联
 * </p>
 *
 * @author hupeng
 * @since 2019-09-13
 */
@TableName("store_order_goods")
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


    public Integer getRecId() {
        return recId;
    }

    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getMemberGoodsPrice() {
        return memberGoodsPrice;
    }

    public void setMemberGoodsPrice(BigDecimal memberGoodsPrice) {
        this.memberGoodsPrice = memberGoodsPrice;
    }

    public Integer getGiveIntegral() {
        return giveIntegral;
    }

    public void setGiveIntegral(Integer giveIntegral) {
        this.giveIntegral = giveIntegral;
    }

    public String getSpecKey() {
        return specKey;
    }

    public void setSpecKey(String specKey) {
        this.specKey = specKey;
    }

    public String getSpecKeyName() {
        return specKeyName;
    }

    public void setSpecKeyName(String specKeyName) {
        this.specKeyName = specKeyName;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Integer getIsComment() {
        return isComment;
    }

    public void setIsComment(Integer isComment) {
        this.isComment = isComment;
    }

    public Integer getPromType() {
        return promType;
    }

    public void setPromType(Integer promType) {
        this.promType = promType;
    }

    public Integer getPromId() {
        return promId;
    }

    public void setPromId(Integer promId) {
        this.promId = promId;
    }

    public Integer getIsSend() {
        return isSend;
    }

    public void setIsSend(Integer isSend) {
        this.isSend = isSend;
    }

    public Integer getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Override
    protected Serializable pkVal() {
        return this.recId;
    }

    @Override
    public String toString() {
        return "StoreOrderGoods{" +
        ", recId=" + recId +
        ", orderId=" + orderId +
        ", goodsId=" + goodsId +
        ", goodsName=" + goodsName +
        ", goodsSn=" + goodsSn +
        ", goodsNum=" + goodsNum +
        ", marketPrice=" + marketPrice +
        ", goodsPrice=" + goodsPrice +
        ", costPrice=" + costPrice +
        ", memberGoodsPrice=" + memberGoodsPrice +
        ", giveIntegral=" + giveIntegral +
        ", specKey=" + specKey +
        ", specKeyName=" + specKeyName +
        ", barCode=" + barCode +
        ", isComment=" + isComment +
        ", promType=" + promType +
        ", promId=" + promId +
        ", isSend=" + isSend +
        ", deliveryId=" + deliveryId +
        ", sku=" + sku +
        "}";
    }
}

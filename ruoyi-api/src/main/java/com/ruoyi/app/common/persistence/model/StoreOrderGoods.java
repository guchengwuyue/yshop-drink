package com.ruoyi.app.common.persistence.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 订单商品表与订单主表关联
 * </p>
 *
 * @author hupeng
 * @since 2019-06-26
 */
public class StoreOrderGoods extends Model<StoreOrderGoods> {

    private static final long serialVersionUID=1L;

    /**
     * 表id自增
     */
    @TableId(value = "rec_id", type = IdType.AUTO)
    private Integer recId;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品货号
     */
    private String goodsSn;

    /**
     * 购买数量
     */
    private Integer goodsNum;

    /**
     * 市场价
     */
    private BigDecimal marketPrice;

    /**
     * 本店价(供货价)
     */
    private BigDecimal goodsPrice;

    /**
     * 商品成本价
     */
    private BigDecimal costPrice;

    /**
     * 会员折扣价
     */
    private BigDecimal memberGoodsPrice;

    /**
     * 购买商品赠送积分
     */
    private Integer giveIntegral;

    /**
     * 商品规格key
     */
    private String specKey;

    /**
     * 规格对应的中文名字
     */
    private String specKeyName;

    /**
     * 条码
     */
    private String barCode;

    /**
     * 是否评价
     */
    private Boolean isComment;

    /**
     * 0 普通订单,1 限时抢购, 2 团购 , 3 促销优惠,4预售
     */
    private Boolean promType;

    /**
     * 活动id
     */
    private Integer promId;

    /**
     * 0未支付1已支付
     */
    private Boolean payStatus;

    /**
     * 0未发货，1已发货，2已换货，3已退货
     */
    private Boolean isSend;

    /**
     * 发货单ID
     */
    private Integer deliveryId;

    /**
     * sku
     */
    private String sku;

    /**
     * 订单开始时间
     */
    private Integer startTime;

    /**
     * 订单结束时间
     */
    private Integer endTime;


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

    public Boolean getComment() {
        return isComment;
    }

    public void setComment(Boolean isComment) {
        this.isComment = isComment;
    }

    public Boolean getPromType() {
        return promType;
    }

    public void setPromType(Boolean promType) {
        this.promType = promType;
    }

    public Integer getPromId() {
        return promId;
    }

    public void setPromId(Integer promId) {
        this.promId = promId;
    }

    public Boolean getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Boolean payStatus) {
        this.payStatus = payStatus;
    }

    public Boolean getSend() {
        return isSend;
    }

    public void setSend(Boolean isSend) {
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

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.recId;
    }

    @Override
    public String toString() {
        return "StoreOrderGoods{" +
        "recId=" + recId +
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
        ", payStatus=" + payStatus +
        ", isSend=" + isSend +
        ", deliveryId=" + deliveryId +
        ", sku=" + sku +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        "}";
    }
}

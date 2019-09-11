package com.ruoyi.app.common.persistence.model;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 购物车表
 * </p>
 *
 * @author hupeng
 * @since 2019-09-10
 */
@TableName("store_cart")
public class StoreCart extends Model<StoreCart> {

    private static final long serialVersionUID = 1L;

    /**
     * 购物车表
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * session
     */
    @TableField("session_id")
    private String sessionId;
    /**
     * 商品id
     */
    @TableField("goods_id")
    private Integer goodsId;
    /**
     * 商品货号
     */
    @TableField("goods_sn")
    private String goodsSn;
    /**
     * 商品名称
     */
    @TableField("goods_name")
    private String goodsName;
    /**
     * 零售价
     */
    @TableField("market_price")
    private BigDecimal marketPrice;
    /**
     * 本店价(进货价)
     */
    @TableField("goods_price")
    private BigDecimal goodsPrice;
    /**
     * 会员折扣价
     */
    @TableField("member_goods_price")
    private BigDecimal memberGoodsPrice;
    /**
     * 购买数量
     */
    @TableField("goods_num")
    private Integer goodsNum;
    /**
     * 商品规格key 对应store_spec_goods_price 表
     */
    @TableField("spec_key")
    private String specKey;
    /**
     * 商品规格组合名称
     */
    @TableField("spec_key_name")
    private String specKeyName;
    /**
     * 商品条码
     */
    @TableField("bar_code")
    private String barCode;
    /**
     * 购物车选中状态
     */
    private Integer selected;
    /**
     * 加入购物车的时间
     */
    @TableField(value = "add_time",fill = FieldFill.INSERT)
    private Date addTime;
    /**
     * 0 普通订单,1 限时抢购, 2 团购 , 3 促销优惠
     */
    @TableField("prom_type")
    private Integer promType;
    /**
     * 活动id
     */
    @TableField("prom_id")
    private Integer promId;
    /**
     * sku
     */
    private String sku;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
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

    public BigDecimal getMemberGoodsPrice() {
        return memberGoodsPrice;
    }

    public void setMemberGoodsPrice(BigDecimal memberGoodsPrice) {
        this.memberGoodsPrice = memberGoodsPrice;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
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

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StoreCart{" +
        ", id=" + id +
        ", userId=" + userId +
        ", sessionId=" + sessionId +
        ", goodsId=" + goodsId +
        ", goodsSn=" + goodsSn +
        ", goodsName=" + goodsName +
        ", marketPrice=" + marketPrice +
        ", goodsPrice=" + goodsPrice +
        ", memberGoodsPrice=" + memberGoodsPrice +
        ", goodsNum=" + goodsNum +
        ", specKey=" + specKey +
        ", specKeyName=" + specKeyName +
        ", barCode=" + barCode +
        ", selected=" + selected +
        ", addTime=" + addTime +
        ", promType=" + promType +
        ", promId=" + promId +
        ", sku=" + sku +
        "}";
    }
}

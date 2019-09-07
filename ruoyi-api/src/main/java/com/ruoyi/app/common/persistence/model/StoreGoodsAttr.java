package com.ruoyi.app.common.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 商品关联的属性表(与商品相关)
 * </p>
 *
 * @author hupeng
 * @since 2019-09-07
 */
@TableName("store_goods_attr")
public class StoreGoodsAttr extends Model<StoreGoodsAttr> {

    private static final long serialVersionUID = 1L;

    /**
     * 商品属性id自增
     */
    @TableId(value = "goods_attr_id", type = IdType.AUTO)
    private Integer goodsAttrId;
    /**
     * 商品id
     */
    @TableField("goods_id")
    private Integer goodsId;
    /**
     * 属性id
     */
    @TableField("attr_id")
    private Integer attrId;
    /**
     * 属性值
     */
    @TableField("attr_value")
    private String attrValue;
    /**
     * 属性价格
     */
    @TableField("attr_price")
    private String attrPrice;


    public Integer getGoodsAttrId() {
        return goodsAttrId;
    }

    public void setGoodsAttrId(Integer goodsAttrId) {
        this.goodsAttrId = goodsAttrId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public String getAttrPrice() {
        return attrPrice;
    }

    public void setAttrPrice(String attrPrice) {
        this.attrPrice = attrPrice;
    }

    @Override
    protected Serializable pkVal() {
        return this.goodsAttrId;
    }

    @Override
    public String toString() {
        return "StoreGoodsAttr{" +
        ", goodsAttrId=" + goodsAttrId +
        ", goodsId=" + goodsId +
        ", attrId=" + attrId +
        ", attrValue=" + attrValue +
        ", attrPrice=" + attrPrice +
        "}";
    }
}

package com.ruoyi.shop.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品关联的属性(与商品相关)对象 store_goods_attr
 * 
 * @author hupeng
 * @date 2019-08-26
 */
public class StoreGoodsAttr extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品属性id自增 */
    private Integer goodsAttrId;

    /** 商品id */
    @Excel(name = "商品id")
    private Integer goodsId;

    /** 属性id */
    @Excel(name = "属性id")
    private Integer attrId;

    /** 属性值 */
    @Excel(name = "属性值")
    private String attrValue;

    /** 属性价格 */
    @Excel(name = "属性价格")
    private String attrPrice;

    public void setGoodsAttrId(Integer goodsAttrId) 
    {
        this.goodsAttrId = goodsAttrId;
    }

    public Integer getGoodsAttrId() 
    {
        return goodsAttrId;
    }
    public void setGoodsId(Integer goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Integer getGoodsId() 
    {
        return goodsId;
    }
    public void setAttrId(Integer attrId) 
    {
        this.attrId = attrId;
    }

    public Integer getAttrId() 
    {
        return attrId;
    }
    public void setAttrValue(String attrValue) 
    {
        this.attrValue = attrValue;
    }

    public String getAttrValue() 
    {
        return attrValue;
    }
    public void setAttrPrice(String attrPrice) 
    {
        this.attrPrice = attrPrice;
    }

    public String getAttrPrice() 
    {
        return attrPrice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("goodsAttrId", getGoodsAttrId())
            .append("goodsId", getGoodsId())
            .append("attrId", getAttrId())
            .append("attrValue", getAttrValue())
            .append("attrPrice", getAttrPrice())
            .toString();
    }
}

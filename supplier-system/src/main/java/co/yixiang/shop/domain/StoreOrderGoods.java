package co.yixiang.shop.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import co.yixiang.common.annotation.Excel;
import co.yixiang.common.core.domain.BaseEntity;

/**
 * 订单商品与订单主关联对象 store_order_goods
 * 
 * @author hupeng
 * @date 2019-08-29
 */
public class StoreOrderGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 表id自增 */
    private Integer recId;

    /** 订单id */
    @Excel(name = "订单id")
    private Integer orderId;

    /** 商品id */
    @Excel(name = "商品id")
    private Integer goodsId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品货号 */
    @Excel(name = "商品货号")
    private String goodsSn;

    /** 购买数量 */
    @Excel(name = "购买数量")
    private Integer goodsNum;

    /** 市场价 */
    @Excel(name = "市场价")
    private Double marketPrice;

    /** 本店价(供货价) */
    @Excel(name = "本店价(供货价)")
    private Double goodsPrice;

    /** 商品成本价 */
    @Excel(name = "商品成本价")
    private Double costPrice;

    /** 会员折扣价 */
    @Excel(name = "会员折扣价")
    private Double memberGoodsPrice;

    /** 购买商品赠送积分 */
    @Excel(name = "购买商品赠送积分")
    private Integer giveIntegral;

    /** 商品规格key */
    @Excel(name = "商品规格key")
    private String specKey;

    /** 规格对应的中文名字 */
    @Excel(name = "规格对应的中文名字")
    private String specKeyName;

    /** 条码 */
    @Excel(name = "条码")
    private String barCode;

    /** 是否评价 */
    @Excel(name = "是否评价")
    private Integer isComment;

    /** 0 普通订单,1 限时抢购, 2 团购 , 3 促销优惠,4预售 */
    @Excel(name = "0 普通订单,1 限时抢购, 2 团购 , 3 促销优惠,4预售")
    private Integer promType;

    /** 活动id */
    @Excel(name = "活动id")
    private Long promId;

    /** 0未发货，1已发货，2已换货，3已退货 */
    @Excel(name = "0未发货，1已发货，2已换货，3已退货")
    private Integer isSend;

    /** 发货单ID */
    @Excel(name = "发货单ID")
    private Long deliveryId;

    /** sku */
    @Excel(name = "sku")
    private String sku;

    public void setRecId(Integer recId) 
    {
        this.recId = recId;
    }

    public Integer getRecId() 
    {
        return recId;
    }
    public void setOrderId(Integer orderId) 
    {
        this.orderId = orderId;
    }

    public Integer getOrderId() 
    {
        return orderId;
    }
    public void setGoodsId(Integer goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Integer getGoodsId() 
    {
        return goodsId;
    }
    public void setGoodsName(String goodsName) 
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName() 
    {
        return goodsName;
    }
    public void setGoodsSn(String goodsSn) 
    {
        this.goodsSn = goodsSn;
    }

    public String getGoodsSn() 
    {
        return goodsSn;
    }
    public void setGoodsNum(Integer goodsNum) 
    {
        this.goodsNum = goodsNum;
    }

    public Integer getGoodsNum() 
    {
        return goodsNum;
    }
    public void setMarketPrice(Double marketPrice) 
    {
        this.marketPrice = marketPrice;
    }

    public Double getMarketPrice() 
    {
        return marketPrice;
    }
    public void setGoodsPrice(Double goodsPrice) 
    {
        this.goodsPrice = goodsPrice;
    }

    public Double getGoodsPrice() 
    {
        return goodsPrice;
    }
    public void setCostPrice(Double costPrice) 
    {
        this.costPrice = costPrice;
    }

    public Double getCostPrice() 
    {
        return costPrice;
    }
    public void setMemberGoodsPrice(Double memberGoodsPrice) 
    {
        this.memberGoodsPrice = memberGoodsPrice;
    }

    public Double getMemberGoodsPrice() 
    {
        return memberGoodsPrice;
    }
    public void setGiveIntegral(Integer giveIntegral) 
    {
        this.giveIntegral = giveIntegral;
    }

    public Integer getGiveIntegral() 
    {
        return giveIntegral;
    }
    public void setSpecKey(String specKey) 
    {
        this.specKey = specKey;
    }

    public String getSpecKey() 
    {
        return specKey;
    }
    public void setSpecKeyName(String specKeyName) 
    {
        this.specKeyName = specKeyName;
    }

    public String getSpecKeyName() 
    {
        return specKeyName;
    }
    public void setBarCode(String barCode) 
    {
        this.barCode = barCode;
    }

    public String getBarCode() 
    {
        return barCode;
    }
    public void setIsComment(Integer isComment) 
    {
        this.isComment = isComment;
    }

    public Integer getIsComment() 
    {
        return isComment;
    }
    public void setPromType(Integer promType) 
    {
        this.promType = promType;
    }

    public Integer getPromType() 
    {
        return promType;
    }
    public void setPromId(Long promId) 
    {
        this.promId = promId;
    }

    public Long getPromId() 
    {
        return promId;
    }
    public void setIsSend(Integer isSend) 
    {
        this.isSend = isSend;
    }

    public Integer getIsSend() 
    {
        return isSend;
    }
    public void setDeliveryId(Long deliveryId) 
    {
        this.deliveryId = deliveryId;
    }

    public Long getDeliveryId() 
    {
        return deliveryId;
    }
    public void setSku(String sku) 
    {
        this.sku = sku;
    }

    public String getSku() 
    {
        return sku;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recId", getRecId())
            .append("orderId", getOrderId())
            .append("goodsId", getGoodsId())
            .append("goodsName", getGoodsName())
            .append("goodsSn", getGoodsSn())
            .append("goodsNum", getGoodsNum())
            .append("marketPrice", getMarketPrice())
            .append("goodsPrice", getGoodsPrice())
            .append("costPrice", getCostPrice())
            .append("memberGoodsPrice", getMemberGoodsPrice())
            .append("giveIntegral", getGiveIntegral())
            .append("specKey", getSpecKey())
            .append("specKeyName", getSpecKeyName())
            .append("barCode", getBarCode())
            .append("isComment", getIsComment())
            .append("promType", getPromType())
            .append("promId", getPromId())
            .append("isSend", getIsSend())
            .append("deliveryId", getDeliveryId())
            .append("sku", getSku())
            .toString();
    }
}

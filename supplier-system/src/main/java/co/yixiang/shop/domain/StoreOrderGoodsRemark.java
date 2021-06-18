package co.yixiang.shop.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import co.yixiang.common.annotation.Excel;
import co.yixiang.common.core.domain.BaseEntity;

/**
 * 订单商品备注对象 store_order_goods_remark
 * 
 * @author hupeng
 * @date 2019-08-29
 */
public class StoreOrderGoodsRemark extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 订单id */
    @Excel(name = "订单id")
    private Integer orderId;

    /** 商品id */
    @Excel(name = "商品id")
    private Integer goodsId;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("goodsId", getGoodsId())
            .append("remark", getRemark())
            .toString();
    }
}

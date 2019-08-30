package com.ruoyi.shop.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 优惠券记录对象 store_coupon_list
 * 
 * @author hupeng
 * @date 2019-08-29
 */
public class StoreCouponList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 表id */
    private Integer id;

    /** 优惠券 对应coupon表id */
    @Excel(name = "优惠券 对应coupon表id")
    private Integer cid;

    /** 发放类型 1 按订单发放 2 注册 3 邀请 4 按用户发放 */
    @Excel(name = "发放类型 1 按订单发放 2 注册 3 邀请 4 按用户发放")
    private Integer type;

    /** 用户id */
    @Excel(name = "用户id")
    private Integer uid;

    /** 订单id */
    @Excel(name = "订单id")
    private Integer orderId;

    /** 优惠券来自订单ID */
    @Excel(name = "优惠券来自订单ID")
    private Long getOrderId;

    /** 使用时间 */
    @Excel(name = "使用时间")
    private Long useTime;

    /** 优惠券兑换码 */
    @Excel(name = "优惠券兑换码")
    private String code;

    /** 发放时间 */
    @Excel(name = "发放时间")
    private Long sendTime;

    /** 0未使用1已使用2已过期 */
    @Excel(name = "0未使用1已使用2已过期")
    private Integer status;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setCid(Integer cid) 
    {
        this.cid = cid;
    }

    public Integer getCid() 
    {
        return cid;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setUid(Integer uid) 
    {
        this.uid = uid;
    }

    public Integer getUid() 
    {
        return uid;
    }
    public void setOrderId(Integer orderId) 
    {
        this.orderId = orderId;
    }

    public Integer getOrderId() 
    {
        return orderId;
    }
    public void setGetOrderId(Long getOrderId) 
    {
        this.getOrderId = getOrderId;
    }

    public Long getGetOrderId() 
    {
        return getOrderId;
    }
    public void setUseTime(Long useTime) 
    {
        this.useTime = useTime;
    }

    public Long getUseTime() 
    {
        return useTime;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setSendTime(Long sendTime) 
    {
        this.sendTime = sendTime;
    }

    public Long getSendTime() 
    {
        return sendTime;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cid", getCid())
            .append("type", getType())
            .append("uid", getUid())
            .append("orderId", getOrderId())
            .append("getOrderId", getGetOrderId())
            .append("useTime", getUseTime())
            .append("code", getCode())
            .append("sendTime", getSendTime())
            .append("status", getStatus())
            .toString();
    }
}

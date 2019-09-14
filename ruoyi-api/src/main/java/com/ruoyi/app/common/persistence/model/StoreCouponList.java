package com.ruoyi.app.common.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 优惠券记录表
 * </p>
 *
 * @author hupeng
 * @since 2019-09-13
 */
@TableName("store_coupon_list")
public class StoreCouponList extends Model<StoreCouponList> {

    private static final long serialVersionUID = 1L;

    /**
     * 表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 优惠券 对应coupon表id
     */
    private Integer cid;
    /**
     * 发放类型 1 按订单发放 2 注册 3 邀请 4 按用户发放
     */
    private Integer type;
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 订单id
     */
    @TableField("order_id")
    private Integer orderId;
    /**
     * 优惠券来自订单ID
     */
    @TableField("get_order_id")
    private Integer getOrderId;
    /**
     * 使用时间
     */
    @TableField("use_time")
    private Integer useTime;
    /**
     * 优惠券兑换码
     */
    private String code;
    /**
     * 发放时间
     */
    @TableField("send_time")
    private Integer sendTime;
    /**
     * 0未使用1已使用2已过期
     */
    private Integer status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGetOrderId() {
        return getOrderId;
    }

    public void setGetOrderId(Integer getOrderId) {
        this.getOrderId = getOrderId;
    }

    public Integer getUseTime() {
        return useTime;
    }

    public void setUseTime(Integer useTime) {
        this.useTime = useTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSendTime() {
        return sendTime;
    }

    public void setSendTime(Integer sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StoreCouponList{" +
        ", id=" + id +
        ", cid=" + cid +
        ", type=" + type +
        ", uid=" + uid +
        ", orderId=" + orderId +
        ", getOrderId=" + getOrderId +
        ", useTime=" + useTime +
        ", code=" + code +
        ", sendTime=" + sendTime +
        ", status=" + status +
        "}";
    }
}

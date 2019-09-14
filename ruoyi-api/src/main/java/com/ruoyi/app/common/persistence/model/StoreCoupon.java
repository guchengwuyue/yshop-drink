package com.ruoyi.app.common.persistence.model;


import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 优惠券主表
 * </p>
 *
 * @author hupeng
 * @since 2019-09-13
 */
@TableName("store_coupon")
public class StoreCoupon extends Model<StoreCoupon> {

    private static final long serialVersionUID = 1L;

    /**
     * 表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 优惠券名字
     */
    private String name;
    /**
     * 发放类型 0下单赠送1 指定发放 2 免费领取 3线下发放
     */
    private Integer type;
    /**
     * 优惠券金额
     */
    private BigDecimal money;
    /**
     * 使用条件，满多少金额
     */
    private BigDecimal condition;
    /**
     * 发放数量
     */
    private Integer createnum;
    /**
     * 已领取数量
     */
    @TableField("send_num")
    private Integer sendNum;
    /**
     * 已使用数量
     */
    @TableField("use_num")
    private Integer useNum;
    /**
     * 发放开始时间
     */
    @TableField("send_start_time")
    private Integer sendStartTime;
    /**
     * 发放结束时间
     */
    @TableField("send_end_time")
    private Integer sendEndTime;
    /**
     * 使用开始时间
     */
    @TableField("use_start_time")
    private Date useStartTime;
    /**
     * 使用结束时间
     */
    @TableField("use_end_time")
    private Date useEndTime;
    /**
     * 添加时间
     */
    @TableField("add_time")
    private Integer addTime;
    /**
     * 状态：1有效,2无效
     */
    private Integer status;
    /**
     * 使用范围：0全店通用1指定商品可用2指定分类商品可用
     */
    @TableField("use_type")
    private Integer useType;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getCondition() {
        return condition;
    }

    public void setCondition(BigDecimal condition) {
        this.condition = condition;
    }

    public Integer getCreatenum() {
        return createnum;
    }

    public void setCreatenum(Integer createnum) {
        this.createnum = createnum;
    }

    public Integer getSendNum() {
        return sendNum;
    }

    public void setSendNum(Integer sendNum) {
        this.sendNum = sendNum;
    }

    public Integer getUseNum() {
        return useNum;
    }

    public void setUseNum(Integer useNum) {
        this.useNum = useNum;
    }

    public Integer getSendStartTime() {
        return sendStartTime;
    }

    public void setSendStartTime(Integer sendStartTime) {
        this.sendStartTime = sendStartTime;
    }

    public Integer getSendEndTime() {
        return sendEndTime;
    }

    public void setSendEndTime(Integer sendEndTime) {
        this.sendEndTime = sendEndTime;
    }

    public Date getUseStartTime() {
        return useStartTime;
    }

    public void setUseStartTime(Date useStartTime) {
        this.useStartTime = useStartTime;
    }

    public Date getUseEndTime() {
        return useEndTime;
    }

    public void setUseEndTime(Date useEndTime) {
        this.useEndTime = useEndTime;
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUseType() {
        return useType;
    }

    public void setUseType(Integer useType) {
        this.useType = useType;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StoreCoupon{" +
        ", id=" + id +
        ", name=" + name +
        ", type=" + type +
        ", money=" + money +
        ", condition=" + condition +
        ", createnum=" + createnum +
        ", sendNum=" + sendNum +
        ", useNum=" + useNum +
        ", sendStartTime=" + sendStartTime +
        ", sendEndTime=" + sendEndTime +
        ", useStartTime=" + useStartTime +
        ", useEndTime=" + useEndTime +
        ", addTime=" + addTime +
        ", status=" + status +
        ", useType=" + useType +
        "}";
    }
}

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
 * 积分/余额日志(流水)表
 * </p>
 *
 * @author hupeng
 * @since 2019-09-15
 */
@TableName("store_points_money_log")
public class StorePointsMoneyLog extends Model<StorePointsMoneyLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 日志id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 关联的订单id 充值d订单或者消费订单
     */
    @TableField("order_id")
    private Integer orderId;
    /**
     * 所有者的用户id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 积分或者红包日志标题
     */
    private String title;
    /**
     * 积分变动数量，亿级
     */
    @TableField("points_number")
    private Integer pointsNumber;
    /**
     * 红包变动数量，亿级
     */
    @TableField("money_number")
    private BigDecimal moneyNumber;
    /**
     * 会员红包或者积分变动类型：1支入，2-支出
     */
    private Integer type;
    /**
     * 添加时间
     */
    @TableField("create_time")
    private Integer createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPointsNumber() {
        return pointsNumber;
    }

    public void setPointsNumber(Integer pointsNumber) {
        this.pointsNumber = pointsNumber;
    }

    public BigDecimal getMoneyNumber() {
        return moneyNumber;
    }

    public void setMoneyNumber(BigDecimal moneyNumber) {
        this.moneyNumber = moneyNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StorePointsMoneyLog{" +
        ", id=" + id +
        ", orderId=" + orderId +
        ", userId=" + userId +
        ", title=" + title +
        ", pointsNumber=" + pointsNumber +
        ", moneyNumber=" + moneyNumber +
        ", type=" + type +
        ", createTime=" + createTime +
        "}";
    }
}

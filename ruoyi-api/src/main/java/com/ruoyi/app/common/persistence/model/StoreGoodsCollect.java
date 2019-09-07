package com.ruoyi.app.common.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 商品收藏表
 * </p>
 *
 * @author hupeng
 * @since 2019-09-07
 */
@TableName("store_goods_collect")
public class StoreGoodsCollect extends Model<StoreGoodsCollect> {

    private static final long serialVersionUID = 1L;

    /**
     * 表id
     */
    @TableId(value = "collect_id", type = IdType.AUTO)
    private Integer collectId;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 商品id
     */
    @TableField("goods_id")
    private Integer goodsId;
    /**
     * 添加时间
     */
    @TableField("add_time")
    private Integer addTime;


    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.collectId;
    }

    @Override
    public String toString() {
        return "StoreGoodsCollect{" +
        ", collectId=" + collectId +
        ", userId=" + userId +
        ", goodsId=" + goodsId +
        ", addTime=" + addTime +
        "}";
    }
}

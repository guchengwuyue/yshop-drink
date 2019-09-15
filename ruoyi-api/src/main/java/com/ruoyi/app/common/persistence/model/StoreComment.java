package com.ruoyi.app.common.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 商品评论表
 * </p>
 *
 * @author hupeng
 * @since 2019-09-15
 */
@TableName("store_comment")
public class StoreComment extends Model<StoreComment> {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;
    /**
     * 商品id
     */
    @TableField("goods_id")
    private Integer goodsId;
    /**
     * email邮箱
     */
    private String email;
    /**
     * 用户名
     */
    private String username;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 添加时间
     */
    @TableField("add_time")
    private Integer addTime;
    /**
     * ip地址
     */
    @TableField("ip_address")
    private String ipAddress;
    /**
     * 是否显示
     */
    @TableField("is_show")
    private Integer isShow;
    /**
     * 父id
     */
    @TableField("parent_id")
    private Integer parentId;
    /**
     * 评论用户
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 晒单图片
     */
    private String img;
    /**
     * 订单id
     */
    @TableField("order_id")
    private Integer orderId;
    /**
     * 物流评价等级
     */
    @TableField("deliver_rank")
    private Integer deliverRank;
    /**
     * 商品评价等级
     */
    @TableField("goods_rank")
    private Integer goodsRank;
    /**
     * 商家服务态度评价等级
     */
    @TableField("service_rank")
    private Integer serviceRank;
    /**
     * 被赞数
     */
    @TableField("zan_num")
    private Integer zanNum;
    /**
     * 点赞用户id
     */
    @TableField("zan_userid")
    private String zanUserid;
    /**
     * 是否匿名评价:0不是，1是
     */
    @TableField("is_anonymous")
    private Integer isAnonymous;
    /**
     * 订单商品表ID
     */
    @TableField("rec_id")
    private Integer recId;


    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDeliverRank() {
        return deliverRank;
    }

    public void setDeliverRank(Integer deliverRank) {
        this.deliverRank = deliverRank;
    }

    public Integer getGoodsRank() {
        return goodsRank;
    }

    public void setGoodsRank(Integer goodsRank) {
        this.goodsRank = goodsRank;
    }

    public Integer getServiceRank() {
        return serviceRank;
    }

    public void setServiceRank(Integer serviceRank) {
        this.serviceRank = serviceRank;
    }

    public Integer getZanNum() {
        return zanNum;
    }

    public void setZanNum(Integer zanNum) {
        this.zanNum = zanNum;
    }

    public String getZanUserid() {
        return zanUserid;
    }

    public void setZanUserid(String zanUserid) {
        this.zanUserid = zanUserid;
    }

    public Integer getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(Integer isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public Integer getRecId() {
        return recId;
    }

    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    @Override
    protected Serializable pkVal() {
        return this.commentId;
    }

    @Override
    public String toString() {
        return "StoreComment{" +
        ", commentId=" + commentId +
        ", goodsId=" + goodsId +
        ", email=" + email +
        ", username=" + username +
        ", content=" + content +
        ", addTime=" + addTime +
        ", ipAddress=" + ipAddress +
        ", isShow=" + isShow +
        ", parentId=" + parentId +
        ", userId=" + userId +
        ", img=" + img +
        ", orderId=" + orderId +
        ", deliverRank=" + deliverRank +
        ", goodsRank=" + goodsRank +
        ", serviceRank=" + serviceRank +
        ", zanNum=" + zanNum +
        ", zanUserid=" + zanUserid +
        ", isAnonymous=" + isAnonymous +
        ", recId=" + recId +
        "}";
    }
}

package co.yixiang.app.common.persistence.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 商城会员信息
 * </p>
 *
 * @author hupeng
 * @since 2019-06-17
 */
@TableName("store_member")
public class StoreMember extends Model<StoreMember> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * vip编号
     */
    @TableField("vip_no")
    private String vipNo;
    /**
     * 会员微信OPENID
     */
    private String openid;


    /**
     * 会员手机号
     */
    private String phone;
    /**
     * 登录密码
     */
    private String password;

    /**
     * 会员昵称
     */
    private String nickname;
    /**
     * 会员头像
     */
    private String headimg;

    /**
     * 性别
     */
    private String sex;
    /**
     * 会员级别
     */
    private Integer level;
    /**
     * 消费累计额度 用于用户等级
     */
    @TableField("total_amount")
    private BigDecimal totalAmount;
    /**
     * 用户金额
     */
    @TableField("user_money")
    private BigDecimal userMoney;
    /**
     * 累积分佣金额
     */
    @TableField("distribut_money")
    private BigDecimal distributMoney;
    /**
     * 冻结金额
     */
    @TableField("frozen_money")
    private Double frozenMoney;
    /**
     * 用户下线总数
     */
    @TableField("underling_number")
    private Integer underlingNumber;
    /**
     * 消费积分(可用积分)
     */
    @TableField("pay_points")
    private Integer payPoints;

    /**
     * 默认收货地址
     */
    @TableField("address_id")
    private Integer addressId;
    /**
     * 会员个性签名
     */
    private String remark;
    /**
     * 会员状态0待审核 1-已审核 2-已拒绝
     */
    private Integer status;

    @TableField("create_time")
    private Date createTime;
    /**
     * 创建时间
     */
    @TableField("create_at")
    private Date createAt;
    /**
     * 审核时间
     */
    @TableField("status_time")
    private Long statusTime;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getVipNo() {
        return vipNo;
    }

    public void setVipNo(String vipNo) {
        this.vipNo = vipNo;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(BigDecimal userMoney) {
        this.userMoney = userMoney;
    }

    public BigDecimal getDistributMoney() {
        return distributMoney;
    }

    public void setDistributMoney(BigDecimal distributMoney) {
        this.distributMoney = distributMoney;
    }

    public Double getFrozenMoney() {
        return frozenMoney;
    }

    public void setFrozenMoney(Double frozenMoney) {
        this.frozenMoney = frozenMoney;
    }

    public Integer getUnderlingNumber() {
        return underlingNumber;
    }

    public void setUnderlingNumber(Integer underlingNumber) {
        this.underlingNumber = underlingNumber;
    }

    public Integer getPayPoints() {
        return payPoints;
    }

    public void setPayPoints(Integer payPoints) {
        this.payPoints = payPoints;
    }


    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Long getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Long statusTime) {
        this.statusTime = statusTime;
    }



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StoreMember{" +
        "id=" + id +
        ", vipNo=" + vipNo +
        ", openid=" + openid +
        ", phone=" + phone +
        ", password=" + password +
        ", nickname=" + nickname +
        ", headimg=" + headimg +
        ", sex=" + sex +
        ", level=" + level +
        ", totalAmount=" + totalAmount +
        ", userMoney=" + userMoney +
        ", distributMoney=" + distributMoney +
        ", frozenMoney=" + frozenMoney +
        ", underlingNumber=" + underlingNumber +
        ", payPoints=" + payPoints +
        ", addressId=" + addressId +
        ", remark=" + remark +
        ", status=" + status +
        ", createTime=" + createTime +
        ", createAt=" + createAt +
        ", statusTime=" + statusTime +
        "}";
    }
}

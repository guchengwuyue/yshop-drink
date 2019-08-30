package com.ruoyi.app.common.persistence.model;


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
     * 店铺ID
     */
    @TableField("store_id")
    private Integer storeId;
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
     * 商家版小程序openid
     */
    @TableField("seller_openid")
    private String sellerOpenid;
    /**
     * 公众号openid
     */
    @TableField("wx_openid")
    private String wxOpenid;
    /**
     * unionid唯一标识
     */
    private String unionid;
    /**
     * 会员手机号
     */
    private String phone;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 余额支付密码
     */
    @TableField("pay_password")
    private String payPassword;
    /**
     * 会员昵称
     */
    private String nickname;
    /**
     * 会员头像
     */
    private String headimg;
    /**
     * 用户类型 1-普通用户 2-商家用户
     */
    @TableField("user_type")
    private Integer userType;
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
     * 冻结积分
     */
    @TableField("frozen_points")
    private Integer frozenPoints;
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
    private Long createTime;
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
    @TableField("login_at")
    private Date loginAt;
    /**
     * 登录次数
     */
    @TableField("login_num")
    private Integer loginNum;
    /**
     * 是否为分销商 0 否 1 是
     */
    @TableField("is_distribut")
    private Integer isDistribut;
    /**
     * 第一个上级
     */
    @TableField("first_leader")
    private Long firstLeader;
    /**
     * 第二个上级
     */
    @TableField("second_leader")
    private Long secondLeader;
    /**
     * 第三个上级
     */
    @TableField("third_leader")
    private Long thirdLeader;
    /**
     * 是否是超管 1-是 0不是
     */
    @TableField("is_super")
    private Integer isSuper;
    /**
     * 分销推广二维码图片
     */
    @TableField("spread_qrcode")
    private String spreadQrcode;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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

    public String getSellerOpenid() {
        return sellerOpenid;
    }

    public void setSellerOpenid(String sellerOpenid) {
        this.sellerOpenid = sellerOpenid;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
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

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
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

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
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

    public Integer getFrozenPoints() {
        return frozenPoints;
    }

    public void setFrozenPoints(Integer frozenPoints) {
        this.frozenPoints = frozenPoints;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
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

    public Date getLoginAt() {
        return loginAt;
    }

    public void setLoginAt(Date loginAt) {
        this.loginAt = loginAt;
    }

    public Integer getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    public Integer getIsDistribut() {
        return isDistribut;
    }

    public void setIsDistribut(Integer isDistribut) {
        this.isDistribut = isDistribut;
    }

    public Long getFirstLeader() {
        return firstLeader;
    }

    public void setFirstLeader(Long firstLeader) {
        this.firstLeader = firstLeader;
    }

    public Long getSecondLeader() {
        return secondLeader;
    }

    public void setSecondLeader(Long secondLeader) {
        this.secondLeader = secondLeader;
    }

    public Long getThirdLeader() {
        return thirdLeader;
    }

    public void setThirdLeader(Long thirdLeader) {
        this.thirdLeader = thirdLeader;
    }

    public Integer getIsSuper() {
        return isSuper;
    }

    public void setIsSuper(Integer isSuper) {
        this.isSuper = isSuper;
    }

    public String getSpreadQrcode() {
        return spreadQrcode;
    }

    public void setSpreadQrcode(String spreadQrcode) {
        this.spreadQrcode = spreadQrcode;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StoreMember{" +
        "id=" + id +
        ", storeId=" + storeId +
        ", vipNo=" + vipNo +
        ", openid=" + openid +
        ", sellerOpenid=" + sellerOpenid +
        ", wxOpenid=" + wxOpenid +
        ", unionid=" + unionid +
        ", phone=" + phone +
        ", password=" + password +
        ", payPassword=" + payPassword +
        ", nickname=" + nickname +
        ", headimg=" + headimg +
        ", userType=" + userType +
        ", sex=" + sex +
        ", level=" + level +
        ", totalAmount=" + totalAmount +
        ", userMoney=" + userMoney +
        ", distributMoney=" + distributMoney +
        ", frozenMoney=" + frozenMoney +
        ", underlingNumber=" + underlingNumber +
        ", payPoints=" + payPoints +
        ", frozenPoints=" + frozenPoints +
        ", addressId=" + addressId +
        ", remark=" + remark +
        ", status=" + status +
        ", createTime=" + createTime +
        ", createAt=" + createAt +
        ", statusTime=" + statusTime +
        ", loginAt=" + loginAt +
        ", loginNum=" + loginNum +
        ", isDistribut=" + isDistribut +
        ", firstLeader=" + firstLeader +
        ", secondLeader=" + secondLeader +
        ", thirdLeader=" + thirdLeader +
        ", isSuper=" + isSuper +
        ", spreadQrcode=" + spreadQrcode +
        "}";
    }
}

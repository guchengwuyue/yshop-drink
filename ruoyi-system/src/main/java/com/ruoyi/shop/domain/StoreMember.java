package com.ruoyi.shop.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 商城会员信息对象 store_member
 * 
 * @author hupeng
 * @date 2019-08-30
 */
public class StoreMember extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** vip编号 */
    private String vipNo;

    /** 会员微信OPENID */
    private String openid;

    /** 会员手机号 */
    @Excel(name = "会员手机号")
    private String phone;

    /** 登录密码 */
    private String password;

    /** 会员昵称 */
    @Excel(name = "会员昵称")
    private String nickname;

    /** 会员头像 */
    @Excel(name = "会员头像")
    private String headimg;

    /** 性别 */
    private String sex;

    /** 会员级别 */
    @Excel(name = "会员级别")
    private Integer level;

    /** 消费累计额度 用于用户等级 */
    private Double totalAmount;

    /** 用户金额 */
    @Excel(name = "用户金额")
    private Double userMoney;

    /** 累积分佣金额 */
    private Double distributMoney;

    /** 冻结金额 */
    private Double frozenMoney;

    /** 用户下线总数 */
    private Integer underlingNumber;

    /** 消费积分 */
    @Excel(name = "消费积分")
    private Integer payPoints;

    /** 默认收货地址 */
    private Integer addressId;

    /** 会员状态0待审核 1-已审核 2-已拒绝 */
    private Integer status;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createAt;

    /** 审核时间 */
    private Long statusTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setVipNo(String vipNo) 
    {
        this.vipNo = vipNo;
    }

    public String getVipNo() 
    {
        return vipNo;
    }
    public void setOpenid(String openid) 
    {
        this.openid = openid;
    }

    public String getOpenid() 
    {
        return openid;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }

    public String getNickname() 
    {
        return nickname;
    }
    public void setHeadimg(String headimg) 
    {
        this.headimg = headimg;
    }

    public String getHeadimg() 
    {
        return headimg;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setLevel(Integer level) 
    {
        this.level = level;
    }

    public Integer getLevel() 
    {
        return level;
    }
    public void setTotalAmount(Double totalAmount) 
    {
        this.totalAmount = totalAmount;
    }

    public Double getTotalAmount() 
    {
        return totalAmount;
    }
    public void setUserMoney(Double userMoney) 
    {
        this.userMoney = userMoney;
    }

    public Double getUserMoney() 
    {
        return userMoney;
    }
    public void setDistributMoney(Double distributMoney) 
    {
        this.distributMoney = distributMoney;
    }

    public Double getDistributMoney() 
    {
        return distributMoney;
    }
    public void setFrozenMoney(Double frozenMoney) 
    {
        this.frozenMoney = frozenMoney;
    }

    public Double getFrozenMoney() 
    {
        return frozenMoney;
    }
    public void setUnderlingNumber(Integer underlingNumber) 
    {
        this.underlingNumber = underlingNumber;
    }

    public Integer getUnderlingNumber() 
    {
        return underlingNumber;
    }
    public void setPayPoints(Integer payPoints) 
    {
        this.payPoints = payPoints;
    }

    public Integer getPayPoints() 
    {
        return payPoints;
    }
    public void setAddressId(Integer addressId) 
    {
        this.addressId = addressId;
    }

    public Integer getAddressId() 
    {
        return addressId;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setCreateAt(Date createAt) 
    {
        this.createAt = createAt;
    }

    public Date getCreateAt() 
    {
        return createAt;
    }
    public void setStatusTime(Long statusTime) 
    {
        this.statusTime = statusTime;
    }

    public Long getStatusTime() 
    {
        return statusTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("vipNo", getVipNo())
            .append("openid", getOpenid())
            .append("phone", getPhone())
            .append("password", getPassword())
            .append("nickname", getNickname())
            .append("headimg", getHeadimg())
            .append("sex", getSex())
            .append("level", getLevel())
            .append("totalAmount", getTotalAmount())
            .append("userMoney", getUserMoney())
            .append("distributMoney", getDistributMoney())
            .append("frozenMoney", getFrozenMoney())
            .append("underlingNumber", getUnderlingNumber())
            .append("payPoints", getPayPoints())
            .append("addressId", getAddressId())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("createAt", getCreateAt())
            .append("statusTime", getStatusTime())
            .toString();
    }
}

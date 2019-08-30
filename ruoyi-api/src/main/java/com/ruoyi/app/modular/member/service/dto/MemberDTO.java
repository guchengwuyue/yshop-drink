package com.ruoyi.app.modular.member.service.dto;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
public class MemberDTO implements Serializable  {

    private Long id;

    private Integer storeId;
    /**
     * vip编号
     */
    private String vipNo;
    /**
     * 会员微信OPENID
     */
    //private String openid;
    /**
     * 商家版小程序openid
     */
    //private String sellerOpenid;
    /**
     * 公众号openid
     */
    //private String wxOpenid;
    /**
     * unionid唯一标识
     */
    private String unionid;
    /**
     * 会员手机号
     */
    private String phone;

    //private String password;

   // private String payPassword;
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
    private BigDecimal totalAmount;
    /**
     * 用户金额
     */
    private BigDecimal userMoney;
    /**
     * 累积分佣金额
     */
    private BigDecimal distributMoney;
    /**
     * 冻结金额
     */
    private Double frozenMoney;
    /**
     * 用户下线总数
     */
    private Integer underlingNumber;
    /**
     * 消费积分(可用积分)
     */
    private Integer payPoints;
    /**
     * 冻结积分
     */
    private Integer frozenPoints;
    /**
     * 默认收货地址
     */
    private Integer addressId;
    /**
     * 会员个性签名
     */
    private String remark;
    /**
     * 会员状态0待审核 1-已审核 2-已拒绝
     */
    private Integer status;
    private Long createTime;
    /**
     * 创建时间
     */
    private Date createAt;
    /**
     * 审核时间
     */
    private Long statusTime;
    private Date loginAt;
    /**
     * 登录次数
     */
    private Integer loginNum;
    /**
     * 是否为分销商 0 否 1 是
     */
    private Integer isDistribut;
    /**
     * 第一个上级
     */
    private Long firstLeader;
    /**
     * 第二个上级
     */
    private Long secondLeader;
    /**
     * 第三个上级
     */
    private Long thirdLeader;
    /**
     * 是否是超管 1-是 0不是
     */
    private Integer isSuper;
    /**
     * 分销推广二维码图片
     */
    private String spreadQrcode;



}

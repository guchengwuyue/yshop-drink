package co.yixiang.app.modular.member.service.dto;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;


@Data
public class MemberDTO implements Serializable  {

    private Long id;

    private Integer storeId;

    /**
     * unionid唯一标识
     */
    private String unionid;
    /**
     * 会员手机号
     */
    private String phone;

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
    private BigDecimal totalAmount;
    /**
     * 用户金额
     */
    private BigDecimal userMoney;

    /**
     * 冻结金额
     */
    private Double frozenMoney;


    /**
     * 消费积分(可用积分)
     */
    private Integer payPoints;

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

    private Map<String,Integer> countInfo;




}

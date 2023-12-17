package co.yixiang.yshop.module.member.dal.dataobject.user;

import co.yixiang.yshop.framework.common.enums.CommonStatusEnum;
import co.yixiang.yshop.framework.tenant.core.db.TenantBaseDO;

import co.yixiang.yshop.module.member.api.user.dto.WechatUserDto;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 会员用户 DO
 *
 * uk_mobile 索引：基于 {@link #mobile} 字段
 *
 * @author yshop
 */
@TableName(value = "yshop_user", autoResultMap = true)
@KeySequence("member_user_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberUserDO extends TenantBaseDO {

    /**
     * 用户ID
     */
    @TableId
    private Long id;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 帐号状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;

    /**
     * 手机
     */
    private String mobile;
    /**
     * 加密后的密码
     *
     * 因为目前使用 {@link BCryptPasswordEncoder} 加密器，所以无需自己处理 salt 盐
     */
    private String password;
    /**
     * 注册 IP
     */
    private String registerIp;
    /**
     * 最后登录IP
     */
    private String loginIp;
    /**
     * 最后登录时间
     */
    private LocalDateTime loginDate;

    /**
     * 用户账户(跟accout一样)
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 身份证号码
     */
    private String cardId;
    /**
     * 用户备注
     */
    private String mark;
    /**
     * 合伙人id
     */
    private Integer partnerId;
    /**
     * 用户分组id
     */
    private Integer groupId;

    /**
     * 添加ip
     */
    private String addIp;
    /**
     * 最后一次登录ip
     */
    private String lastIp;
    /**
     * 用户余额
     */
    private BigDecimal nowMoney;
    /**
     * 佣金金额
     */
    private BigDecimal brokeragePrice;
    /**
     * 用户剩余积分
     */
    private BigDecimal integral;
    /**
     * 连续签到天数
     */
    private Integer signNum;

    /**
     * 等级
     */
    private Integer level;
    /**
     * 推广元id
     */
    private Long spreadUid;
    /**
     * 推广员关联时间
     */
    private LocalDateTime spreadTime;
    /**
     * 用户类型
     */
    private String userType;
    /**
     * 是否为推广员
     */
    private Integer isPromoter;
    /**
     * 用户购买次数
     */
    private Integer payCount;
    /**
     * 下级人数
     */
    private Integer spreadCount;
    /**
     * 详细地址
     */
    private String addres;
    /**
     * 管理员编号
     */
    private Integer adminid;
    /**
     * 用户登陆类型，h5,wechat,routine
     */
    private String loginType;

    //公众号openid
    private String openid;

    //小程序openid
    private String routineOpenid;
    //性别
    private Integer gender;


    /** 微信用户json信息 */
    @TableField(typeHandler = FastjsonTypeHandler.class)
    @Deprecated
    private  WechatUserDto wxProfile;

}

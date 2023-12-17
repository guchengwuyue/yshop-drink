package co.yixiang.yshop.module.member.controller.admin.user.vo;

import co.yixiang.yshop.framework.desensitize.core.slider.annotation.MobileDesensitize;
import co.yixiang.yshop.module.member.api.user.dto.WechatUserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
* 用户 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class UserBaseVO {

    @Schema(description = "用户账户(跟accout一样)", example = "王五")
    private String username;

    @Schema(description = "真实姓名", example = "王五")
    private String realName;

    @Schema(description = "用户昵称", example = "李四")
    private String nickname;

    @Schema(description = "用户头像")
    private String avatar;

    @MobileDesensitize
    @Schema(description = "手机号码")
    private String mobile;

    @Schema(description = "添加ip")
    private String addIp;

    @Schema(description = "用户余额", required = true)
    @NotNull(message = "用户余额不能为空")
    private BigDecimal nowMoney;

    @Schema(description = "佣金金额", required = true, example = "14395")
    @NotNull(message = "佣金金额不能为空")
    private BigDecimal brokeragePrice;

    @Schema(description = "用户剩余积分", required = true)
    @NotNull(message = "用户剩余积分不能为空")
    private BigDecimal integral;

    @Schema(description = "1为正常，0为禁止", required = true, example = "2")
    @NotNull(message = "1为正常，0为禁止不能为空")
    private Integer status;

    @Schema(description = "是否为推广员", required = true)
    @NotNull(message = "是否为推广员不能为空")
    private Integer isPromoter;

    @Schema(description = "用户购买次数", example = "16061")
    private Integer payCount;

    @Schema(description = "下级人数", example = "4960")
    private Integer spreadCount;

    @Schema(description = "详细地址", required = true)
    @NotNull(message = "详细地址不能为空")
    private String addres;

    @Schema(description = "管理员编号 ", example = "29490")
    private Integer adminid;

    @Schema(description = "用户登陆类型，h5,wechat,routine", required = true, example = "2")
    @NotNull(message = "用户登陆类型，h5,wechat,routine不能为空")
    private String loginType;

    @Schema(description = "微信用户json信息")
    private WechatUserDto wxProfile;

    @Schema(description = "生日")
    private String birthday;

    /**
     * 最后一次登录ip
     */
    private String loginIp;

    /**
     * 最后一次登录ip
     */
    private String lastIp;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 推广元id
     */
    private Long spreadUid;

    /**
     * 身份证号码
     */
    private String cardId;



}

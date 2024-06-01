package co.yixiang.yshop.module.member.controller.app.user.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static co.yixiang.yshop.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * <p>
 * 用户表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2023-6-18
 */
@Data
@Schema(description = "用户 APP - 用户表查 Response VO")
public class AppUserQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id", required = true)
    private Long id;

    @Schema(description = "用户账户(跟accout一样)\"", required = true)
    private String username;

    @Schema(description = "用户账号", required = true)
    private String account;

    @Schema(description = "优惠券数量", required = true)
    private Long couponCount = 0L;

    @Schema(description = "订单详情数据", required = true)
    private AppUserOrderCountVo orderStatusNum;


    private Integer statu;

    @Schema(description = "总的签到天数", required = true)
    private Long sumSignDay;

    @Schema(description = "当天是否签到", required = true)
    private Integer isDaySign;

    @Schema(description = "昨天是否签到", required = true)
    private Integer isYesterDaySign;

    @Schema(description = "核销权限", required = true)
    private Integer checkStatus;

    @Schema(description = "用户昵称", required = true)
    private String nickname;

    @Schema(description = "用户头像", required = true)
    private String avatar;

    @Schema(description = "手机号码", required = true)
    private String mobile;

    @Schema(description = "用户余额", required = true)
    private BigDecimal nowMoney;

    @Schema(description = "用户花费的金额", required = true)
    private BigDecimal sumMoney;

    @Schema(description = "佣金金额", required = true)
    private BigDecimal brokeragePrice;

    @Schema(description = "用户剩余积分", required = true)
    private BigDecimal integral;

    @Schema(description = "连续签到天数", required = true)
    private Integer signNum;

    @Schema(description = "推广元id", required = true)
    private Long spreadUid;

    @Schema(description = "用户类型", required = true)
    private String userType;

    @Schema(description = "是否为推广员", required = true)
    private Integer isPromoter;

    @Schema(description = "用户购买次数", required = true)
    private Integer payCount;

    @Schema(description = "下级人数", required = true)
    private Integer spreadCount;

    @Schema(description = "详细地址", required = true)
    private String addres;

    private Integer gender;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private String birthday;


    @Schema(description = "用户登陆类型，h5,wechat,routine", required = true)
    private String loginType;



}

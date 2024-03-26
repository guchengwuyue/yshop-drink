package co.yixiang.yshop.module.member.controller.admin.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import static co.yixiang.yshop.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 用户更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserUpdateReqVO extends UserBaseVO {

    @Schema(description = "用户id", required = true, example = "16370")
    @NotNull(message = "用户id不能为空")
    private Long id;

    @Schema(description = "用户密码（跟pwd）")
    private String password;

    @Schema(description = "生日")
    private String birthday;

    @Schema(description = "身份证号码", example = "29961")
    private String cardId;

    @Schema(description = "用户备注")
    private String mark;

    @Schema(description = "合伙人id", example = "4234")
    private Integer partnerId;

    @Schema(description = "用户分组id", example = "12625")
    private Integer groupId;

    @Schema(description = "最后一次登录ip")
    private String lastIp;

    @Schema(description = "连续签到天数", required = true)
    //@NotNull(message = "连续签到天数不能为空")
    private Integer signNum;

    @Schema(description = "等级", required = true)
    //@NotNull(message = "等级不能为空")
    private Integer level;

    @Schema(description = "推广元id", example = "5747")
    private Long spreadUid;

    @Schema(description = "推广员关联时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime spreadTime;

    @Schema(description = "用户类型", required = true, example = "1")
   // @NotNull(message = "用户类型不能为空")
    private String userType;

}

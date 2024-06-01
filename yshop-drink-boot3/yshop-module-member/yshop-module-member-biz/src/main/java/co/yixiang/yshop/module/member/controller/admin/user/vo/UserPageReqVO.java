package co.yixiang.yshop.module.member.controller.admin.user.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import co.yixiang.yshop.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static co.yixiang.yshop.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 用户分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserPageReqVO extends PageParam {

    @Schema(description = "用户账户(跟accout一样)", example = "王五")
    private String username;

    @Schema(description = "真实姓名", example = "王五")
    private String realName;

    @Schema(description = "用户昵称", example = "李四")
    private String nickname;

    @Schema(description = "手机号码")
    private String mobile;

    @Schema(description = "添加时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "登录类型")
    private String loginType;

}

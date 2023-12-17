package co.yixiang.yshop.module.member.controller.admin.user.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import co.yixiang.yshop.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static co.yixiang.yshop.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 用户 Excel 导出 Request VO，参数和 UserPageReqVO 是一致的")
@Data
public class UserExportReqVO {

    @Schema(description = "用户账户(跟accout一样)", example = "王五")
    private String username;

    @Schema(description = "真实姓名", example = "王五")
    private String realName;

    @Schema(description = "用户昵称", example = "李四")
    private String nickname;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "添加时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}

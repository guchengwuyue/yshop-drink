package co.yixiang.yshop.module.member.controller.admin.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 用户 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserRespVO extends UserBaseVO {

    @Schema(description = "用户id", required = true, example = "16370")
    private Long id;

    @Schema(description = "添加时间")
    private LocalDateTime createTime;

}

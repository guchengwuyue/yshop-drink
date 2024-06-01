package co.yixiang.yshop.module.member.controller.admin.userbill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 用户账单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserBillRespVO extends UserBillBaseVO {

    @Schema(description = "用户账单id", required = true, example = "22559")
    private Long id;

    @Schema(description = "添加时间", required = true)
    private LocalDateTime createTime;

}

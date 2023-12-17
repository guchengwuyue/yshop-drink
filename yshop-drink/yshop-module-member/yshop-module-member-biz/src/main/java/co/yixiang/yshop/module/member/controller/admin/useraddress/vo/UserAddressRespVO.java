package co.yixiang.yshop.module.member.controller.admin.useraddress.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 用户地址 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserAddressRespVO extends UserAddressBaseVO {

    @Schema(description = "用户地址id", required = true, example = "24169")
    private Long id;

    @Schema(description = "添加时间", required = true)
    private LocalDateTime createTime;

}

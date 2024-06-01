package co.yixiang.yshop.module.member.controller.admin.useraddress.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.*;


@Schema(description = "管理后台 - 用户地址更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserAddressUpdateReqVO extends UserAddressBaseVO {

    @Schema(description = "用户地址id", required = true, example = "24169")
    @NotNull(message = "用户地址id不能为空")
    private Long id;

}

package co.yixiang.yshop.module.member.controller.admin.userbill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 用户账单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserBillUpdateReqVO extends UserBillBaseVO {

    @Schema(description = "用户账单id", required = true, example = "22559")
    @NotNull(message = "用户账单id不能为空")
    private Long id;

}

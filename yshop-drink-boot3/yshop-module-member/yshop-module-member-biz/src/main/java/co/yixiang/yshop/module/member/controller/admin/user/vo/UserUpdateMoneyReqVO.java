package co.yixiang.yshop.module.member.controller.admin.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Schema(description = "管理后台 - 用户更新 Request VO")
@Data
@ToString(callSuper = true)
public class UserUpdateMoneyReqVO {

    @Schema(description = "用户id", required = true, example = "16370")
    @NotNull(message = "用户id不能为空")
    private Long id;

    @Schema(description = "修改金额类型")
    private Integer ptype;

    @Schema(description = "金额")
    @NotNull(message = "金额不能为空")
    private String money;

    @Schema(description = "修改积分类型")
    private Integer itype;

    @Schema(description = "积分")
    @NotNull(message = "积分不能为空")
    private String integral;



}

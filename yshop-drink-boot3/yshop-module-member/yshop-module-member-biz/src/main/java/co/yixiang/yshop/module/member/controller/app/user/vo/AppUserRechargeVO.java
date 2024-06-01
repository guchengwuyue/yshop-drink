package co.yixiang.yshop.module.member.controller.app.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;



/**
 * 用户充值 vo
 *
 * @author yshop
 */
@Data
public class AppUserRechargeVO {

    @Schema(description = "充值ID", required = true)
    @NotBlank(message = "请选择要充值的面值")
    private String rechargeId;




}

package co.yixiang.yshop.module.member.controller.app.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(description = "用户 APP - 微信小程序手机登录 VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppWxMiniLoginVO {


    @Schema(description = "登录encryptedData", required = true, example = "word")
    @NotEmpty(message = "登录encryptedData不能为空")
    private String encryptedData;

    @Schema(description = "登录iv", required = true, example = "word")
    @NotEmpty(message = "登录iv不能为空")
    private String iv;

    @Schema(description = "登录openid", required = true, example = "word")
    @NotEmpty(message = "登录openid不能为空")
    private String openid;

}

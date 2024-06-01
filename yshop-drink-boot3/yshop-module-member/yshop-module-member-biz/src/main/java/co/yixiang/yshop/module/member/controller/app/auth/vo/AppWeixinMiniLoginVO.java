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
public class AppWeixinMiniLoginVO {


    @Schema(description = "登录 code,小程序通过 wx.login 方法获得", required = true, example = "word")
    @NotEmpty(message = "登录 code 不能为空")
    private String code;

}

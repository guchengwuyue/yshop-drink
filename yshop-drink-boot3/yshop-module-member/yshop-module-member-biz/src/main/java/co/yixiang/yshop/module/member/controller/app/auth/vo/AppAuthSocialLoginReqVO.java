package co.yixiang.yshop.module.member.controller.app.auth.vo;

import co.yixiang.yshop.framework.common.validation.InEnum;
import co.yixiang.yshop.module.system.enums.social.SocialTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(description = "用户 APP - 社交快捷登录 Request VO，使用 code 授权码")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppAuthSocialLoginReqVO {

    @Schema(description = "社交平台的类型,参见 SysUserSocialTypeEnum 枚举值", required = true, example = "10")
    @InEnum(SocialTypeEnum.class)
    @NotNull(message = "社交平台的类型不能为空")
    private Integer type;

    @Schema(description = "授权码", required = true, example = "1024")
    @NotEmpty(message = "授权码不能为空")
    private String code;

    @Schema(description = "state", required = true, example = "9b2ffbc1-7425-4155-9894-9d5c08541d62")
    @NotEmpty(message = "state 不能为空")
    private String state;

}

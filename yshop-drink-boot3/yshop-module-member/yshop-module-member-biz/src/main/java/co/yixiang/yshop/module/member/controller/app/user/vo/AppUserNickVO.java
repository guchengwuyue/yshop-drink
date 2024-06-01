package co.yixiang.yshop.module.member.controller.app.user.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户信息 vo
 *
 * @author yshop
 */
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUserNickVO {

    /**
     * 用户nickname
     */
    @NotBlank(message = "用户昵称不能为空")
    private String nickname;
    /**
     * 用户birthday
     */
    @NotBlank(message = "生日不能为空")
    private String birthday;
    /**
     * avatar
     */
    @NotBlank(message = "用户头像不能为空")
    private String avatar;
    /**
     * gender
     */
    private Integer gender;
    /**
     * mobile
     */
    private String mobile;




}

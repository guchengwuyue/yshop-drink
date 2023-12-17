package co.yixiang.yshop.module.member.api.user.dto;

import lombok.*;

/**
 * @ClassName WechatUserDTO
 * @Author hupeng <610796224@qq.com>
 * @Date 2023/7/18
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WechatUserDto {

    private String openid;

    private String unionId;

    private String routineOpenid;

    private String nickname;

    private String headimgurl;

    private String sex;

    private String city;

    private String language;

    private String province;

    private String country;

    private Boolean subscribe;

    private Long subscribeTime;

}

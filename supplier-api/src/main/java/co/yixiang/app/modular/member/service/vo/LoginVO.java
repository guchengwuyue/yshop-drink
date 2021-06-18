package co.yixiang.app.modular.member.service.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginVO {
    //@NotBlank(message = "code必传")
    private String code;

    private String encrypted_data;

    private String iv;





}

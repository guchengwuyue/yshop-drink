package co.yixiang.yshop.module.member.controller.app.address.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName AddressParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2023/6/28
 **/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppAddressParam implements Serializable {

    @Schema(description = "地址ID", required = true)
    private String id;

    @NotBlank
    @Size(min = 1, max = 30,message = "长度超过了限制")
    @Schema(description = "收货地址真实名字", required = true)
    private String realName;

    @Schema(description = "收货地址邮编", required = true)
    private String postCode;

    @Schema(description = "是否默认收货地址 1是 0否", required = true)
    private Integer isDefault;

    @NotBlank
    @Size(min = 1, max = 60,message = "长度超过了限制")
    @Schema(description = "收货详细地址", required = true)
    private String detail;

    @NotBlank
    @Schema(description = "收货手机号码", required = true)
    private String phone;

    @Schema(description = "收货地址详情", required = true)
    private String address;

    @Schema(description = "经度", required = true)
    private String longitude;

    @Schema(description = "纬度", required = true)
    private String latitude;
}

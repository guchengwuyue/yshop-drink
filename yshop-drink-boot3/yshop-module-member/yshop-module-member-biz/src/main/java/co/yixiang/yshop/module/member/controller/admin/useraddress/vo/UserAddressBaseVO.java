package co.yixiang.yshop.module.member.controller.admin.useraddress.vo;

import co.yixiang.yshop.framework.desensitize.core.slider.annotation.MobileDesensitize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;


/**
* 用户地址 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class UserAddressBaseVO {

    @Schema(description = "用户id", required = true, example = "25124")
    @NotNull(message = "用户id不能为空")
    private Long uid;

    @Schema(description = "收货人姓名", required = true, example = "李四")
    @NotNull(message = "收货人姓名不能为空")
    private String realName;

    @MobileDesensitize
    @Schema(description = "收货人电话", required = true)
    @NotNull(message = "收货人电话不能为空")
    private String phone;

    @Schema(description = "收货人所在省", required = true)
    @NotNull(message = "收货人所在省不能为空")
    private String province;

    @Schema(description = "收货人所在市", required = true)
    @NotNull(message = "收货人所在市不能为空")
    private String city;

    @Schema(description = "城市id", example = "15595")
    private Integer cityId;

    @Schema(description = "收货人所在区", required = true)
    @NotNull(message = "收货人所在区不能为空")
    private String district;

    @Schema(description = "收货人详细地址", required = true)
    @NotNull(message = "收货人详细地址不能为空")
    private String detail;

    @Schema(description = "邮编", required = true)
    @NotNull(message = "邮编不能为空")
    private String postCode;

    @Schema(description = "经度", required = true)
    @NotNull(message = "经度不能为空")
    private String longitude;

    @Schema(description = "纬度", required = true)
    @NotNull(message = "纬度不能为空")
    private String latitude;

    @Schema(description = "是否默认", required = true)
    @NotNull(message = "是否默认不能为空")
    private Integer isDefault;

    private String address;

}

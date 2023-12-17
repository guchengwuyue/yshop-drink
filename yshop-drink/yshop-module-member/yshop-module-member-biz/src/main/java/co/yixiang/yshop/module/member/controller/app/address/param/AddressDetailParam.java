package co.yixiang.yshop.module.member.controller.app.address.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName AddressDetailParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2023/6/28
 **/
@Data
public class AddressDetailParam implements Serializable {

    @Schema(description = "城市ID", required = true)
    private Integer cityId;

    @Schema(description = "城市", required = true)
    private String city;

    @Schema(description = "地区", required = true)
    private String district;

    @Schema(description = "省份", required = true)
    private String province;
}

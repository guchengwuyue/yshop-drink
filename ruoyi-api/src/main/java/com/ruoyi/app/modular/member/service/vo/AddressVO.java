package com.ruoyi.app.modular.member.service.vo;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 商城会员收货地址
 * </p>
 *
 * @author hupeng
 * @since 2019-09-13
 */

@Data
public class AddressVO {


    private String id;


    @NotBlank(message="收货人姓名不能为空")
    private String username;

    @NotBlank(message="收货手机号不能为空")
    private String phone;

    @NotBlank(message="所在地址不能为空")
    private String cityInfo;

    @NotBlank(message="详细地址不能为空")
    private String address;


}

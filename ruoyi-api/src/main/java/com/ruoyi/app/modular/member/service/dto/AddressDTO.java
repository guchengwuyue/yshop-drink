package com.ruoyi.app.modular.member.service.dto;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商城会员收货地址
 * </p>
 *
 * @author hupeng
 * @since 2019-09-13
 */

@Data
public class AddressDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 收货人姓名
     */
    private String username;
    /**
     * 收货手机号
     */
    private String phone;

    private String cityInfo;

    private String address;

    private Integer isDefault;

}

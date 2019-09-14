package com.ruoyi.app.modular.shop.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName CouponDTO
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/9/14
 **/

@Data
public class CouponDTO implements Serializable {
    private int id;
    private String name;
    private Double money;
    private Double condition;
    private Date useEndTime;
}

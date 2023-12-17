/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.yshop.module.order.service.storeorder.dto;

import lombok.Data;

import java.util.Map;

/**
 * @ClassName StoreOrderCartInfo
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/10/14
 **/


@Data
public class StoreOrderCartInfoDto {


    private Integer id;


    private Integer oid;


    private Integer cartId;


    private String cartInfo;


    private String unique;

    private Map<String,Object> cartInfoMap;


}

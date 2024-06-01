/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.yshop.module.order.service.storeorder.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName OrderTimeDataDTO
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/25
 **/
@Data
public class OrderTimeDataDto implements Serializable {
    private Double todayPrice;  //今日成交额
    private Integer todayCount; //今日订单数
    private Double proPrice;  //昨日成交额
    private Integer proCount;//昨日订单数
    private Double monthPrice;//本月成交额
    private Integer monthCount;//本月订单数

    private Integer lastWeekCount;//上周
    private Double lastWeekPrice; //上周

    private Long userCount;
    private Long orderCount;
    private Double priceCount;
    private Long goodsCount;
}

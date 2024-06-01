package co.yixiang.yshop.module.order.controller.admin.storeorder.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName OrderTimeDataDTO
 * @Author hupeng <610796224@qq.com>
 * @Date 2023/7/26
 **/
@Data
public class ShoperOrderTimeDataVo implements Serializable {

    /**今日成交额*/
    private Double todayPrice;

    /**今日订单数*/
    private Long todayCount;

    /**昨日成交额*/
    private Double proPrice;

    /**昨日订单数*/
    private Long proCount;

    /**本月成交额*/
    private Double monthPrice;

    /**本月订单数*/
    private Long monthCount;

    /**上周订单数*/
    private Long lastWeekCount;

    /**上周成交额*/
    private Double lastWeekPrice;
}

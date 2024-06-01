package co.yixiang.yshop.module.order.service.storeorder.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName PriceGroup
 * @Author hupeng <610796224@qq.com>
 * @Date 2023/6/18
 **/
@Data
public class PriceGroupDto {

    private BigDecimal costPrice;

    private BigDecimal storeFreePostage;

    private BigDecimal storePostage;

    private BigDecimal totalPrice;

    private BigDecimal vipPrice;

    private BigDecimal payIntegral;
}

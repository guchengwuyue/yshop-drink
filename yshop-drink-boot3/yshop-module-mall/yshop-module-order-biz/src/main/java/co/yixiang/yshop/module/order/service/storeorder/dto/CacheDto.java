package co.yixiang.yshop.module.order.service.storeorder.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName CacheDto
 * @Author hupeng <610796224@qq.com>
 * @Date 2023/6/19
 **/
@Data
public class CacheDto implements Serializable {
    private PriceGroupDto priceGroup;
    private OtherDto other;
}

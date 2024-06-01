package co.yixiang.yshop.module.order.service.storeorder.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ProductAttrDto
 * @Author hupeng <610796224@qq.com>
 * @Date 2023/6/27
 **/
@Data
public class ProductAttrDto implements Serializable {

    private Long productId;

    private String sku;

    private Double price;

    private String image;
}

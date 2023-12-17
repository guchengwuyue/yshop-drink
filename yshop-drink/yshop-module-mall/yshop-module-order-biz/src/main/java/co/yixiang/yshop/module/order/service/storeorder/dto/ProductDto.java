package co.yixiang.yshop.module.order.service.storeorder.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ProductVo
 * @Author hupeng <610796224@qq.com>
 * @Date 20123/6/27
 **/
@Data
public class ProductDto implements Serializable {

    private String image;

    private Double price;

    private String storeName;

    private ProductAttrDto attrInfo;

}

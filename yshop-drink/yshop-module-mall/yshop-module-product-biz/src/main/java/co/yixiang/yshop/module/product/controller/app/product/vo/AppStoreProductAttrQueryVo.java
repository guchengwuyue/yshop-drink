package co.yixiang.yshop.module.product.controller.app.product.vo;

import co.yixiang.yshop.module.product.service.storeproduct.dto.AttrValueDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 商品属性表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2023-6-12
 */
@Data
@Schema(description = "用户 APP - 商品属性表vo")
public class AppStoreProductAttrQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "ID", required = true)
    private Long id;

    @Schema(description = "商品ID", required = true)
    private Long productId;

    @Schema(description = "属性名", required = true)
    private String attrName;

    @Schema(description = "属性值", required = true)
    private String attrValues;

    @Schema(description = "属性值集合", required = true)
    private List<AttrValueDto> attrValue;

    @Schema(description = "属性列表", required = true)
    private List<String> attrValueArr;

}

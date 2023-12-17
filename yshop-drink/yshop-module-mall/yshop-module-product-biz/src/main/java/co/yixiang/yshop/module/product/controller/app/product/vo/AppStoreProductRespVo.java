package co.yixiang.yshop.module.product.controller.app.product.vo;


import co.yixiang.yshop.module.product.dal.dataobject.storeproductattrvalue.StoreProductAttrValueDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2023-6-12
 */
@Data
@Schema(description = "商品 APP - 获取商品列表查询结果数")
public class AppStoreProductRespVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id", required = true)
    private Long id;

    @Schema(description = "商品图片", required = true)
    private String image;


    @Schema(description = "是否收藏", required = true)
    private Boolean userCollect = false;

    @Schema(description = "是否喜欢", required = true)
    private Boolean userLike = false;

    @Schema(description = "轮播图，多个用,分割", required = true)
    private String sliderImage;

    @Schema(description = "商品属性信息", required = true)
    private StoreProductAttrValueDO attrInfo;

    @Schema(description = "商品名称", required = true)
    private String storeName;

    @Schema(description = "商品简介", required = true)
    private String storeInfo;

    @Schema(description = "关键字", required = true)
    private String keyword;

    @Schema(description = "商品价格", required = true)
    private BigDecimal price;

    @Schema(description = "会员价格", required = true)
    private BigDecimal vipPrice;

    @Schema(description = "市场价", required = true)
    private BigDecimal otPrice;

    @Schema(description = "邮费", required = true)
    private BigDecimal postage;

    @Schema(description = "单位名", required = true)
    private String unitName;

    @Schema(description = "销量", required = true)
    private Integer sales;

    @Schema(description = "库存", required = true)
    private Integer stock;

    @Schema(description = "产品描述", required = true)
    private String description;

    @Schema(description = "获得积分", required = true)
    private BigDecimal giveIntegral;

    @Schema(description = "需要多少积分", required = true)
    private Integer integral;

    @Schema(description = "状态（0：未上架，1：上架）", required = true)
    private Integer isShow;

    @Schema(description = "运费模板id", required = true)
    private Integer tempId;

    @Schema(description = "产品规格", required = true)
    private List<AppStoreProductAttrQueryVo> productAttr = new ArrayList();

    @Schema(description = "属性集合", required = true)
    private Map<String, StoreProductAttrValueDO> productValue = new LinkedHashMap<>();

}

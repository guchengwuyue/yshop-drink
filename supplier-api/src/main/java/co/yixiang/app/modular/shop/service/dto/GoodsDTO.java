package co.yixiang.app.modular.shop.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class GoodsDTO implements Serializable {


    private Integer goodsId;

    private Integer cateId;

    private String goodsSn;

    private String goodsName;

    private Integer storeCount;

    private BigDecimal marketPrice;
    /**
     * 本店价(供货价)
     */
    private BigDecimal shopPrice;

    /**
     * 商品关键词
     */
    private String keywords;
    /**
     * 商品简单描述
     */
    private String goodsRemark;
    /**
     * 商品详细描述
     */
    private String goodsContent;

    /**
     * 商品上传原始图(主图)
     */
    private String goodsLogo;

    /**
     * 商品销量
     */
    private Integer salesSum;

    private Boolean isCollect;


}

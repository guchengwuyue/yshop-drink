package co.yixiang.yshop.module.express.kdniao.model.dto;

import lombok.*;


/**
 * 电子面单 DTO
 *
 * @author yshop
 */
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KdniaoElectronicsOrderGoodsDTO {

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品编号
     */
    private String goodsCode;
    /**
     * 商品件数
     */
    private Integer goodsQuantity;
    /**
     * 商品价格
     */
    private Double goodsPrice;
    /**
     * 商品重量kg
     */
    private Double goodsWeight;
    /**
     * 商品描述
     */
    private String GoodsDesc;
    /**
     * 商品体积m³
     */
    private Double GoodsVol;





}

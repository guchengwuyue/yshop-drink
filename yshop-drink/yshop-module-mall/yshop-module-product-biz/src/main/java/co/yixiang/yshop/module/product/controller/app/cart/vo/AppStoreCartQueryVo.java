package co.yixiang.yshop.module.product.controller.app.cart.vo;

import co.yixiang.yshop.module.product.controller.app.product.vo.AppStoreProductRespVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 购物车表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2023-6-13
 */
@Data
@Schema(description = "用户 APP - 购物车数据vo")
public class AppStoreCartQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "购物车表ID", required = true)
    private Long id;

    @Schema(description = "用户ID", required = true)
    private Long uid;

    @Schema(description = "类型", required = true)
    private String type;

    @Schema(description = "商品ID", required = true)
    private Long productId;

    @Schema(description = "商品属性", required = true)
    private String productAttrUnique;

    @Schema(description = "商品数量", required = true)
    private Integer cartNum;


    @Schema(description = "拼团id", required = true)
    private Long combinationId;

    @Schema(description = "秒杀产品ID", required = true)
    private Long seckillId;

    @Schema(description = "砍价id", required = true)
    private Long bargainId;

    @Schema(description = "商品信息", required = true)
    private AppStoreProductRespVo productInfo;

    @Schema(description = "成本价", required = true)
    private BigDecimal costPrice;

    @Schema(description = "真实价格", required = true)
    private BigDecimal truePrice;

    @Schema(description = "真实库存", required = true)
    private Integer trueStock;

    @Schema(description = "vip真实价格", required = true)
    private BigDecimal vipTruePrice;

    @Schema(description = "唯一id", required = true)
    private String unique;

    @Schema(description = "是否评价 大于0表示已经评价", required = true)
    private Long isReply;

}

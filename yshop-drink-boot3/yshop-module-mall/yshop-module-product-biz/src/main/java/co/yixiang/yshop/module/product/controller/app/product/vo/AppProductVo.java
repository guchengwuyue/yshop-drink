package co.yixiang.yshop.module.product.controller.app.product.vo;

import co.yixiang.yshop.module.product.dal.dataobject.storeproductattrvalue.StoreProductAttrValueDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品dto
 * </p>
 *
 * @author hupeng
 * @date 2023-6-12
 */
@Data
@Schema(description = "用户 APP - 商品详情vo")
public class AppProductVo {

    @Schema(description = "商品信息列表", required = true)
    private List<AppStoreProductRespVo> goodList = new ArrayList();

    @Schema(description = "价格", required = true)
    private String priceName = "";

    @Schema(description = "产品规格", required = true)
    private List<AppStoreProductAttrQueryVo> productAttr = new ArrayList();

    @Schema(description = "属性集合", required = true)
    private Map<String, StoreProductAttrValueDO>  productValue = new LinkedHashMap<>();

    @Schema(description = "评论信息", required = true)
    private AppStoreProductReplyQueryVo reply;

    @Schema(description = "回复渠道", required = true)
    private String replyChance;

    @Schema(description = "回复数", required = true)
    private Long replyCount;


    @Schema(description = "商品信息", required = true)
    private AppStoreProductRespVo storeInfo;

    @Schema(description = "腾讯地图key", required = true)
    private String mapKey;


    @Schema(description = "用户ID", required = true)
    private Integer uid = 0;

    @Schema(description = "模版名称", required = true)
    private String tempName;

}

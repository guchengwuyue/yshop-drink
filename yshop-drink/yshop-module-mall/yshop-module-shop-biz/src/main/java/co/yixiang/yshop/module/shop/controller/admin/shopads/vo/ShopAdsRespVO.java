package co.yixiang.yshop.module.shop.controller.admin.shopads.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 广告图管理 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopAdsRespVO extends ShopAdsBaseVO {

    @Schema(description = "id", required = true, example = "24753")
    private Long id;

    @Schema(description = "添加时间")
    private LocalDateTime createTime;

}

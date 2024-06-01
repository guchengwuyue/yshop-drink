package co.yixiang.yshop.module.shop.controller.admin.shopads.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 广告图管理更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopAdsUpdateReqVO extends ShopAdsBaseVO {

    @Schema(description = "id", required = true, example = "24753")
    @NotNull(message = "id不能为空")
    private Long id;

}

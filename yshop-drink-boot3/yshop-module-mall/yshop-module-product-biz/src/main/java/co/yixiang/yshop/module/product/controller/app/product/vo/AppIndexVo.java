package co.yixiang.yshop.module.product.controller.app.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "用户 APP - 首页数据")
public class AppIndexVo {



    @Schema(description = "首发新品", required = true)
    private List<AppStoreProductRespVo> firstList;

    @Schema(description = "热门榜单", required = true)
    private List<AppStoreProductRespVo> likeInfo;

    @Schema(description = "地图key", required = true)
    private String mapKey;


}

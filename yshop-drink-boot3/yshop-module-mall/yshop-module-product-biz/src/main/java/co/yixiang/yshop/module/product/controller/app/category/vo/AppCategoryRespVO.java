package co.yixiang.yshop.module.product.controller.app.category.vo;

import co.yixiang.yshop.module.product.controller.app.product.vo.AppStoreProductRespVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
@Schema(description = "用户 APP - 商品分类 Response VO")
public class AppCategoryRespVO {

    @Schema(description = "分类编号", required = true, example = "2")
    private Long id;

    @Schema(description = "父分类编号", required = true, example = "1")
    @NotNull(message = "父分类编号不能为空")
    private Long parentId;

    @Schema(description = "分类名称", required = true, example = "办公文具")
    @NotBlank(message = "分类名称不能为空")
    private String name;

    @Schema(description = "分类图片", required = true)
    @NotBlank(message = "分类图片不能为空")
    private String picUrl;

    @Schema(description = "商品列表", required = true)
    private List<AppStoreProductRespVo> goodsList;

}

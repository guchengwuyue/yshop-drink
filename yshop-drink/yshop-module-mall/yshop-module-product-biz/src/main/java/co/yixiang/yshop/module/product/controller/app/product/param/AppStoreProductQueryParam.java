package co.yixiang.yshop.module.product.controller.app.product.param;

import co.yixiang.yshop.framework.common.params.QueryParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品表 查询参数对象
 * </p>
 *
 * @author hupeng
 * @date 2023-6-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户 APP - 商品表查询参数")
public class AppStoreProductQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @Schema(description = "类别", required = true)
    private String type;

    @Schema(description = "分类ID", required = true)
    private String sid;

    @Schema(description = "是否新品,不为空的字符串即可", required = true)
    private String news;

    @Schema(description = "是否积分兑换商品", required = true)
    private Integer isIntegral;

    @Schema(description = "关键字", required = true)
    private String keyword;

    @Schema(description = "门店ID", required = true)
    private Integer shopId;
}

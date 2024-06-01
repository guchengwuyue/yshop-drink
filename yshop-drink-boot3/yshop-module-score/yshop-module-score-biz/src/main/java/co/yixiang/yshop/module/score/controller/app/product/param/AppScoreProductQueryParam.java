package co.yixiang.yshop.module.score.controller.app.product.param;

import co.yixiang.yshop.framework.common.params.QueryParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品查询参数对象
 * </p>
 *
 * @author hupeng
 * @date 2023-11-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户 APP - 积分商品表查询参数")
public class AppScoreProductQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
    @Schema(description = "关键字", required = true)
    private String keyword;


}

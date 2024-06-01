package co.yixiang.yshop.module.score.controller.app.order.param;

import co.yixiang.yshop.framework.common.params.QueryParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;

/**
 * <p>
 * 积分订单参数对象
 * </p>
 *
 * @author hupeng
 * @date 2023-11-30
 */
@Data
@Schema(description = "用户 APP - 积分订单参数对象")
public class AppScoreOrderParam {

    @Schema(description = "积分商品ID", required = true)
    @NotBlank(message = "参数有误")
    private String productId;

    @Schema(description = "地址ID", required = true)
    @NotBlank(message = "请选择地址")
    private String addressId;

    @Schema(description = "数量", required = true)
    private String num;



}

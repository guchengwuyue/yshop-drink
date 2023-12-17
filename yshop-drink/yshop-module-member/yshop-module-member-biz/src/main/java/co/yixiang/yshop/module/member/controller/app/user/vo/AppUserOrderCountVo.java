package co.yixiang.yshop.module.member.controller.app.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @ClassName OrderCountDTO
 * @Author hupeng <610796224@qq.com>
 * @Date 2023/6/18
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "用户 APP - 用户 OrderCountDTO")
public class AppUserOrderCountVo implements Serializable {

    /**订单支付没有退款 数量*/
    @Schema(description = "订单支付没有退款数量", required = true)
    private Long orderCount;

    /**订单支付没有退款 支付总金额*/
    @Schema(description = "订单支付没有退款支付总金额", required = true)
    private Double sumPrice;

    /**订单待支付 数量*/
    @Schema(description = "订单待支付数量", required = true)
    private Long unpaidCount;

    /**订单待发货数量*/
    @Schema(description = "订单待发货数量", required = true)
    private Long unshippedCount;

    /**订单待收货数量*/
    @Schema(description = "订单待收货数量", required = true)
    private Long receivedCount;

    /**订单待评价数量*/
    @Schema(description = "订单待评价数量", required = true)
    private Long evaluatedCount;

    /**订单已完成数量*/
    @Schema(description = "订单已完成数量", required = true)
    private Long completeCount;

    /**订单退款数量*/
    @Schema(description = "订单退款数量", required = true)
    private Long refundCount;
}

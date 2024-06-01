package co.yixiang.yshop.module.order.controller.app.order.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @ClassName ComputeOrderParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2023/6/19
 **/
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppComputeOrderParam {

    @Schema(description = "地址ID", required = true)
    private String addressId;

    @Schema(description = "优惠券ID", required = true)
    private String couponId;

    @Schema(description = "支付方式", required = true)
    private String payType;

    @Schema(description = "使用积分 1-表示使用", required = true)
    private String useIntegral;

    @Schema(description = "配送方式 1=快递 ，2=门店自提", required = true)
    private String shippingType;

    @Schema(description = "拼团ID", required = true)
    private String pinkId;

    @Schema(description = "拼团产品ID", required = true)
    private String combinationId;
}

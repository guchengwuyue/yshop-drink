package co.yixiang.yshop.module.order.controller.app.order.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ExpressParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2023/7/26
 **/
@Data
public class AppExpressParam implements Serializable {

    @Schema(description = "订单编号", required = true)
    private String orderCode;

    @Schema(description = "快递公司编码", required = true)
    private String shipperCode;

    @Schema(description = "物流单号", required = true)
    private String logisticCode;
}

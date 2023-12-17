package co.yixiang.yshop.module.order.controller.app.order.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ClassName RefundParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2023/6/23
 **/
@Data
public class AppRefundParam implements Serializable {

    @Schema(description = "退款备注", required = true)
    private String refundReasonWapExplain;

    @Schema(description = "退款图片", required = true)
    private String refundReasonWapImg;

    @NotBlank(message = "请填写退款原因")
    @Schema(description = "退款原因", required = true)
    private String text;

    @NotBlank(message = "参数错误")
    @Schema(description = "订单号", required = true)
    private String uni;
}

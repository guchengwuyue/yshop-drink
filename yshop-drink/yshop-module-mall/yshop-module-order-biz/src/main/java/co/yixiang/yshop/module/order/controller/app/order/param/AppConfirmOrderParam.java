package co.yixiang.yshop.module.order.controller.app.order.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName 确认订单ConfirmOrderDTO
 * @Author hupeng <610796224@qq.com>
 * @Date 2023/6/18
 **/
@Getter
@Setter
public class AppConfirmOrderParam {

    @NotBlank(message = "请提交购买的商品")
    @Schema(description = "购物车ID", required = true)
    private String cartId;
}

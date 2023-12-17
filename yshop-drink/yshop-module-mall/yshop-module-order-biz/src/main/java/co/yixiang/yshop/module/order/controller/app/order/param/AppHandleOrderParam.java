package co.yixiang.yshop.module.order.controller.app.order.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName HandleOrderParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2023/6/23
 **/
@Getter
@Setter
public class AppHandleOrderParam {

    @NotBlank(message = "参数有误")
    @Schema(description = "订单ID", required = true)
    private String id;
}

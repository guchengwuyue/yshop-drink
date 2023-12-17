package co.yixiang.yshop.module.order.controller.admin.storeorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 订单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StoreOrderUpdateReqVO extends StoreOrderBaseVO {

    @Schema(description = "订单ID", required = true, example = "31716")
    @NotNull(message = "订单ID不能为空")
    private Long id;

    /**
     *     updateOrder: '修改订单',
     *     orderSend: '订单发货',
     *     remark: '备注',
     *     sendInfo: '配送信息',
     */
    @Schema(description = "更新类型", required = true, example = "31716")
    private String updateType;

}

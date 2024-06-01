package co.yixiang.yshop.module.order.controller.admin.storeorder.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 订单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StoreOrderCreateReqVO extends StoreOrderBaseVO {

}

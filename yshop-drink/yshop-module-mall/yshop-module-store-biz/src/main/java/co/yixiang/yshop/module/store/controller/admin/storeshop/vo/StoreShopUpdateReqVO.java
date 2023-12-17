package co.yixiang.yshop.module.store.controller.admin.storeshop.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 门店管理更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StoreShopUpdateReqVO extends StoreShopBaseVO {

    @Schema(description = "id", required = true, example = "25450")
    @NotNull(message = "id不能为空")
    private Long id;

}

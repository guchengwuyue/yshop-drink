package co.yixiang.yshop.module.product.controller.admin.storeproductreply.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 评论更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StoreProductReplyUpdateReqVO extends StoreProductReplyBaseVO {

    @Schema(description = "评论ID", required = true, example = "7419")
    @NotNull(message = "评论ID不能为空")
    private Long id;

}

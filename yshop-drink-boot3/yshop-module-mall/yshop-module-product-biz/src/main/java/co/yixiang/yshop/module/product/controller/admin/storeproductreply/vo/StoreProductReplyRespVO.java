package co.yixiang.yshop.module.product.controller.admin.storeproductreply.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 评论 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StoreProductReplyRespVO extends StoreProductReplyBaseVO {

    @Schema(description = "评论ID", required = true, example = "7419")
    private Long id;

    @Schema(description = "评论时间", required = true)
    private LocalDateTime createTime;

}

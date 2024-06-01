package co.yixiang.yshop.module.score.controller.admin.scoreproduct.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 积分产品 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ScoreProductRespVO extends ScoreProductBaseVO {

    @Schema(description = "id", required = true, example = "8624")
    private Long id;

    @Schema(description = "添加时间")
    private LocalDateTime createTime;

}

package co.yixiang.yshop.module.score.controller.admin.scoreproduct.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 积分产品更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ScoreProductUpdateReqVO extends ScoreProductBaseVO {

    @Schema(description = "id", required = true, example = "8624")
    @NotNull(message = "id不能为空")
    private Long id;

}

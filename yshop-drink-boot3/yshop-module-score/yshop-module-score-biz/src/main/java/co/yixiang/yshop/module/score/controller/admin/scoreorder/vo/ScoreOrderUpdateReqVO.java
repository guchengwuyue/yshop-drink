package co.yixiang.yshop.module.score.controller.admin.scoreorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 积分商城订单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ScoreOrderUpdateReqVO extends ScoreOrderBaseVO {

    @Schema(description = "id", required = true, example = "19735")
    @NotNull(message = "id不能为空")
    private Long id;

}

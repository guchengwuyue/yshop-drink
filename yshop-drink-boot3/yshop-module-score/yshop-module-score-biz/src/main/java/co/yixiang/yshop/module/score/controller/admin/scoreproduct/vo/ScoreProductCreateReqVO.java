package co.yixiang.yshop.module.score.controller.admin.scoreproduct.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 积分产品创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ScoreProductCreateReqVO extends ScoreProductBaseVO {

}

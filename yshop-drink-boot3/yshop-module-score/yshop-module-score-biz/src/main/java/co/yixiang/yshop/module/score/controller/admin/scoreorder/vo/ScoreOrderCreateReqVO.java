package co.yixiang.yshop.module.score.controller.admin.scoreorder.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 积分商城订单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ScoreOrderCreateReqVO extends ScoreOrderBaseVO {

}

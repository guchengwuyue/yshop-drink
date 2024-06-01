package co.yixiang.yshop.module.score.controller.admin.scoreorder.vo;

import co.yixiang.yshop.module.member.controller.admin.user.vo.UserRespVO;
import co.yixiang.yshop.module.score.controller.admin.scoreproduct.vo.ScoreProductRespVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 积分商城订单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ScoreOrderRespVO extends ScoreOrderBaseVO {

    @Schema(description = "id", required = true, example = "19735")
    private Long id;

    @Schema(description = "添加时间")
    private LocalDateTime createTime;

    @Schema(description = "商品明细")
    private ScoreProductRespVO scoreProductRespVO;

    @Schema(description = "用户信息", required = true)
    private UserRespVO userRespVO;

}

package co.yixiang.yshop.module.express.controller.admin.express.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 快递公司 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExpressRespVO extends ExpressBaseVO {

    @Schema(description = "快递公司id", required = true, example = "27172")
    private Integer id;

    @Schema(description = "添加时间")
    private LocalDateTime createTime;

}

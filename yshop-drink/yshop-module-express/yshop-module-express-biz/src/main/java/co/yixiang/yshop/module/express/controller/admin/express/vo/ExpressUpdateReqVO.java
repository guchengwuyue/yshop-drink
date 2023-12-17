package co.yixiang.yshop.module.express.controller.admin.express.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 快递公司更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExpressUpdateReqVO extends ExpressBaseVO {

    @Schema(description = "快递公司id", required = true, example = "27172")
    @NotNull(message = "快递公司id不能为空")
    private Integer id;

}

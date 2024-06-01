package co.yixiang.yshop.module.express.controller.admin.express.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 快递公司 Excel 导出 Request VO，参数和 ExpressPageReqVO 是一致的")
@Data
public class ExpressExportReqVO {

    @Schema(description = "快递公司简称")
    private String code;

    @Schema(description = "快递公司全称", example = "yshop")
    private String name;

}

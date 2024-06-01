package co.yixiang.yshop.module.express.controller.admin.express.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
* 快递公司 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ExpressBaseVO {

    @Schema(description = "快递公司简称", required = true)
    @NotNull(message = "快递公司简称不能为空")
    private String code;

    @Schema(description = "快递公司全称", required = true, example = "yshop")
    @NotNull(message = "快递公司全称不能为空")
    private String name;

    @Schema(description = "排序", required = true)
    @NotNull(message = "排序不能为空")
    private Integer sort;

}

package co.yixiang.yshop.module.score.controller.admin.scoreproduct.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import jakarta.validation.constraints.*;

/**
* 积分产品 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ScoreProductBaseVO {

    @Schema(description = "产品标题", required = true)
    @NotNull(message = "产品标题不能为空")
    private String title;

    @Schema(description = "主图", required = true)
    @NotNull(message = "主图不能为空")
    private String image;

    @Schema(description = "组图", required = true)
    @NotNull(message = "组图不能为空")
    private List<String> images;

    @Schema(description = "详情", required = true)
    @NotNull(message = "详情不能为空")
    private String desc;

    @Schema(description = "消耗积分", required = true)
    @NotNull(message = "消耗积分不能为空")
    private Integer score;

    @Schema(description = "权重", required = true)
    private Integer weigh;

    @Schema(description = "库存", required = true)
    @NotNull(message = "库存不能为空")
    private Integer stock;

    @Schema(description = "销售量", required = true)
    private Integer sales;

    @Schema(description = "是否上架:0=否,1=是", required = true)
    private Integer isSwitch;

}

package co.yixiang.yshop.module.score.controller.app.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
* 积分产品 AppScoreProductVO VO
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class AppScoreProductVO {

    @Schema(description = "id", required = true, example = "8624")
    private Long id;

    @Schema(description = "产品标题", required = true)
    private String title;

    @Schema(description = "主图", required = true)
    private String image;

    @Schema(description = "组图", required = true)
    private List<String> images;

    @Schema(description = "详情", required = true)
    private String desc;

    @Schema(description = "消耗积分", required = true)
    private Integer score;


    @Schema(description = "库存", required = true)
    private Integer stock;

    @Schema(description = "销售量", required = true)
    private Integer sales;



}

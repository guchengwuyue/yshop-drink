package co.yixiang.yshop.module.score.controller.admin.scoreproduct.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import co.yixiang.yshop.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static co.yixiang.yshop.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 积分产品分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ScoreProductPageReqVO extends PageParam {

    @Schema(description = "产品标题")
    private String title;

    @Schema(description = "主图")
    private String image;

    @Schema(description = "组图")
    private List<String> images;

    @Schema(description = "详情")
    private String desc;

    @Schema(description = "消耗积分")
    private Integer score;

    @Schema(description = "权重")
    private Integer weigh;

    @Schema(description = "库存")
    private Integer stock;

    @Schema(description = "销售量")
    private Integer sales;

    @Schema(description = "是否上架:0=否,1=是")
    private Integer isSwitch;

    @Schema(description = "添加时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}

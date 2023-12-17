package co.yixiang.yshop.module.shop.controller.admin.shopads.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import co.yixiang.yshop.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static co.yixiang.yshop.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 广告图管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopAdsPageReqVO extends PageParam {

    @Schema(description = "图片")
    private String image;

    @Schema(description = "是否显现")
    private Integer isSwitch;

    @Schema(description = "权重")
    private Integer weigh;

    @Schema(description = "所支持的店铺id用','区分，0代表全部", example = "2901")
    private String shopName;

    @Schema(description = "添加时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}

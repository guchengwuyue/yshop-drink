package co.yixiang.yshop.module.store.controller.app.storeshop.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static co.yixiang.yshop.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
* 门店管理VO
*/
@Data
public class AppStoreShopVO {

    private Long id;

    @Schema(description = "店铺名称", required = true, example = "李四")
    private String name;

    @Schema(description = "店铺电话", required = true)
    private String mobile;

    @Schema(description = "图片", required = true)
    private String image;

    @Schema(description = "多张图片", required = true)
    private List<String> images;

    @Schema(description = "详细地址", required = true)
    private String address;

    @Schema(description = "地图定位地址", required = true)
    private String addressMap;

    @Schema(description = "经度", required = true)
    private String lng;

    @Schema(description = "纬度", required = true)
    private String lat;

    @Schema(description = "外卖配送距离,单位为千米。0表示不送外卖", required = true)
    private Integer distance;

    @Schema(description = "计算出来的距离", required = true)
    private Long dis;

    @Schema(description = "起送价钱", required = true, example = "15157")
    private BigDecimal minPrice;

    @Schema(description = "配送价格", required = true, example = "11771")
    private BigDecimal deliveryPrice;

    @Schema(description = "公告", required = true)
    private String notice;


    @Schema(description = "营业开始时间", required = true)
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date startTime;

    @Schema(description = "营业结束时间", required = true)
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date endTime;

}

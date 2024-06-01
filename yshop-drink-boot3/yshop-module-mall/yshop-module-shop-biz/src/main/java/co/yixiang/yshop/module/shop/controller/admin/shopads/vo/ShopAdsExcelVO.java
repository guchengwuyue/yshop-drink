package co.yixiang.yshop.module.shop.controller.admin.shopads.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 广告图管理 Excel VO
 *
 * @author yshop
 */
@Data
public class ShopAdsExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("图片")
    private String image;

    @ExcelProperty("是否显现")
    private Integer isSwitch;

    @ExcelProperty("权重")
    private Integer weigh;

    @ExcelProperty("所支持的店铺id用','区分，0代表全部")
    private String shopId;

    @ExcelProperty("添加时间")
    private LocalDateTime createTime;

}

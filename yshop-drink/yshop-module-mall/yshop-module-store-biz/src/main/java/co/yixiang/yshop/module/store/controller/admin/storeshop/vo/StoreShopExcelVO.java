package co.yixiang.yshop.module.store.controller.admin.storeshop.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 门店管理 Excel VO
 *
 * @author yshop
 */
@Data
public class StoreShopExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("店铺名称")
    private String name;

    @ExcelProperty("店铺电话")
    private String mobile;

    @ExcelProperty("图片")
    private String image;

    @ExcelProperty("多张图片")
    private List<String> images;

    @ExcelProperty("详细地址")
    private String address;

    @ExcelProperty("地图定位地址")
    private String addressMap;

    @ExcelProperty("经度")
    private String lng;

    @ExcelProperty("纬度")
    private String lat;

    @ExcelProperty("外卖配送距离,单位为千米。0表示不送外卖")
    private Integer distance;

    @ExcelProperty("起送价钱")
    private BigDecimal minPrice;

    @ExcelProperty("配送价格")
    private BigDecimal deliveryPrice;

    @ExcelProperty("公告")
    private String notice;

    @ExcelProperty("是否营业:0=否,1=是")
    private Integer status;

    @ExcelProperty("管理员id")
    private List<String> adminId;

    @ExcelProperty("打印机id")
    private String uniprintId;

    @ExcelProperty("添加时间")
    private LocalDateTime createTime;

    @ExcelProperty("营业开始时间")
    private Date startTime;

    @ExcelProperty("营业结束时间")
    private Date endTime;

}

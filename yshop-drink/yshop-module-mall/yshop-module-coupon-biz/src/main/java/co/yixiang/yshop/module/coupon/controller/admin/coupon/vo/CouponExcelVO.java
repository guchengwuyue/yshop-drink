package co.yixiang.yshop.module.coupon.controller.admin.coupon.vo;

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
 * 优惠券 Excel VO
 *
 * @author yshop
 */
@Data
public class CouponExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("店铺id,0表示通用")
    private String shopId;

    @ExcelProperty("店铺名称逗号隔开")
    private String shopName;

    @ExcelProperty("优惠券名称")
    private String title;

    @ExcelProperty("是否上架")
    private Integer isSwitch;

    @ExcelProperty("消费多少可用")
    private BigDecimal least;

    @ExcelProperty("优惠券金额")
    private BigDecimal value;

    @ExcelProperty("开始时间")
    private LocalDateTime startTime;

    @ExcelProperty("结束时间")
    private LocalDateTime endTime;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("权重")
    private Integer weigh;

    @ExcelProperty("可用类型:0=通用,1=自取,2=外卖")
    private Integer type;

    @ExcelProperty("兑换码")
    private String exchangeCode;

    @ExcelProperty("已领取")
    private Integer receive;

    @ExcelProperty("发行数量")
    private Integer distribute;

    @ExcelProperty("所需积分")
    private Integer score;

    @ExcelProperty("使用说明")
    private String instructions;

    @ExcelProperty("图片")
    private String image;

    @ExcelProperty("限领数量")
    private Integer limit;

}

package co.yixiang.yshop.module.coupon.controller.admin.couponuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 用户领的优惠券 Excel VO
 *
 * @author yshop
 */
@Data
public class CouponUserExcelVO {

    @ExcelProperty("id")
    private Integer id;

    @ExcelProperty("店铺id,0表示通用")
    private String shopId;

    @ExcelProperty("店铺名称逗号隔开")
    private String shopName;

    @ExcelProperty("优惠券名称")
    private String title;

    @ExcelProperty("消费多少可用")
    private BigDecimal least;

    @ExcelProperty("优惠券金额")
    private BigDecimal value;

    @ExcelProperty("开始时间")
    private Integer starttime;

    @ExcelProperty("结束时间")
    private Integer endtime;

    @ExcelProperty("创建时间")
    private Integer createtime;

    @ExcelProperty("更新时间")
    private Integer updatetime;

    @ExcelProperty("可用类型:0=通用,1=自取,2=外卖")
    private Integer type;

    @ExcelProperty("消耗积分")
    private Integer score;

    @ExcelProperty("使用说明")
    private String instructions;

    @ExcelProperty("图片")
    private String image;

    @ExcelProperty("用户id")
    private Integer userId;

    @ExcelProperty("已使用:0=否,1=是")
    private Integer status;

    @ExcelProperty("优惠券id")
    private Integer couponId;

    @ExcelProperty("兑换码")
    private String exchangeCode;

}

package co.yixiang.yshop.module.score.controller.admin.scoreorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 积分商城订单 Excel VO
 *
 * @author yshop
 */
@Data
public class ScoreOrderExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("用户id")
    private Integer userId;

    @ExcelProperty("商品id")
    private Integer productId;

    @ExcelProperty("数量")
    private Integer number;

    @ExcelProperty("单个商品积分")
    private Integer score;

    @ExcelProperty("总消耗积分")
    private Integer totalScore;

    @ExcelProperty("下单ip")
    private String ip;

    @ExcelProperty("快递编号")
    private String expressNumber;

    @ExcelProperty("快递公司")
    private String expressCompany;

    @ExcelProperty("收货名称")
    private String customerName;

    @ExcelProperty("收货电话")
    private String customerPhone;

    @ExcelProperty("收货地址")
    private String customerAddress;

    @ExcelProperty("订单状态:0=取消订单,1=正常啊")
    private Boolean status;

    @ExcelProperty("已支付:0=否")
    private Integer havePaid;

    @ExcelProperty("已发货:0=否")
    private Integer haveDelivered;

    @ExcelProperty("已收货:0=否")
    private Integer haveReceived;

    @ExcelProperty("添加时间")
    private LocalDateTime createTime;

}

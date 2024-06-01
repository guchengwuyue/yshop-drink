package co.yixiang.yshop.module.score.controller.admin.scoreproduct.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 积分产品 Excel VO
 *
 * @author yshop
 */
@Data
public class ScoreProductExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("产品标题")
    private String title;

    @ExcelProperty("主图")
    private String image;

    @ExcelProperty("组图")
    private List<String> images;

    @ExcelProperty("详情")
    private String desc;

    @ExcelProperty("消耗积分")
    private Integer score;

    @ExcelProperty("权重")
    private Integer weigh;

    @ExcelProperty("库存")
    private Integer stock;

    @ExcelProperty("销售量")
    private Integer sales;

    @ExcelProperty("是否上架:0=否,1=是")
    private Integer isSwitch;

    @ExcelProperty("添加时间")
    private LocalDateTime createTime;

}

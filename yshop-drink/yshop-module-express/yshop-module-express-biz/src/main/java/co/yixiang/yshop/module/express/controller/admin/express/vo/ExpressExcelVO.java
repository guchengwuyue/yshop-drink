package co.yixiang.yshop.module.express.controller.admin.express.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 快递公司 Excel VO
 *
 * @author yshop
 */
@Data
public class ExpressExcelVO {

    @ExcelProperty("快递公司id")
    private Integer id;

    @ExcelProperty("快递公司简称")
    private String code;

    @ExcelProperty("快递公司全称")
    private String name;

    @ExcelProperty("排序")
    private Integer sort;

    @ExcelProperty("添加时间")
    private LocalDateTime createTime;

}

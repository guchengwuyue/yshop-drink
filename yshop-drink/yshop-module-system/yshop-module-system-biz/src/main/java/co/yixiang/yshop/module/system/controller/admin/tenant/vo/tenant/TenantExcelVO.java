package co.yixiang.yshop.module.system.controller.admin.tenant.vo.tenant;

import co.yixiang.yshop.module.system.enums.DictTypeConstants;
import lombok.*;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import co.yixiang.yshop.framework.excel.core.annotations.DictFormat;
import co.yixiang.yshop.framework.excel.core.convert.DictConvert;


/**
 * 租户 Excel VO
 *
 * @author yshop
 */
@Data
public class TenantExcelVO {

    @ExcelProperty("租户编号")
    private Long id;

    @ExcelProperty("租户名")
    private String name;

    @ExcelProperty("联系人")
    private String contactName;

    @ExcelProperty("联系手机")
    private String contactMobile;

    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.COMMON_STATUS)
    private Integer status;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}

package co.yixiang.yshop.module.system.controller.admin.logger.vo.loginlog;

import co.yixiang.yshop.framework.excel.core.annotations.DictFormat;
import co.yixiang.yshop.framework.excel.core.convert.DictConvert;
import co.yixiang.yshop.module.system.enums.DictTypeConstants;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 登录日志 Excel 导出响应 VO
 */
@Data
public class LoginLogExcelVO {

    @ExcelProperty("日志主键")
    private Long id;

    @ExcelProperty("用户账号")
    private String username;

    @ExcelProperty(value = "日志类型", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.LOGIN_TYPE)
    private Integer logType;

    @ExcelProperty(value = "登录结果", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.LOGIN_RESULT)
    private Integer result;

    @ExcelProperty("登录 IP")
    private String userIp;

    @ExcelProperty("浏览器 UA")
    private String userAgent;

    @ExcelProperty("登录时间")
    private LocalDateTime createTime;

}

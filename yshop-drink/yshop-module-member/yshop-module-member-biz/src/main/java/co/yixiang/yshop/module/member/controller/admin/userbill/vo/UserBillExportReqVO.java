package co.yixiang.yshop.module.member.controller.admin.userbill.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import co.yixiang.yshop.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static co.yixiang.yshop.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 用户账单 Excel 导出 Request VO，参数和 UserBillPageReqVO 是一致的")
@Data
public class UserBillExportReqVO {

    @Schema(description = "用户uid", example = "9419")
    private Long uid;

    @Schema(description = "关联id", example = "18439")
    private String linkId;

    @Schema(description = "0 = 支出 1 = 获得")
    private Byte pm;

    @Schema(description = "账单标题")
    private String title;

    @Schema(description = "明细种类")
    private String category;

    @Schema(description = "明细类型", example = "2")
    private String type;

    @Schema(description = "明细数字")
    private BigDecimal number;

    @Schema(description = "剩余")
    private BigDecimal balance;

    @Schema(description = "备注")
    private String mark;

    @Schema(description = "添加时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "0 = 带确定 1 = 有效 -1 = 无效", example = "1")
    private Boolean status;

}

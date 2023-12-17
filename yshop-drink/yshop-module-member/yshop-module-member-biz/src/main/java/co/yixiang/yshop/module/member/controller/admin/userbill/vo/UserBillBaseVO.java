package co.yixiang.yshop.module.member.controller.admin.userbill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
* 用户账单 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class UserBillBaseVO {

    @Schema(description = "用户uid", required = true, example = "9419")
    @NotNull(message = "用户uid不能为空")
    private Long uid;

    @Schema(description = "关联id", required = true, example = "18439")
    @NotNull(message = "关联id不能为空")
    private String linkId;

    @Schema(description = "0 = 支出 1 = 获得", required = true)
    @NotNull(message = "0 = 支出 1 = 获得不能为空")
    private Byte pm;

    @Schema(description = "账单标题", required = true)
    @NotNull(message = "账单标题不能为空")
    private String title;

    @Schema(description = "明细种类", required = true)
    @NotNull(message = "明细种类不能为空")
    private String category;

    @Schema(description = "明细类型", required = true, example = "2")
    @NotNull(message = "明细类型不能为空")
    private String type;

    @Schema(description = "明细数字", required = true)
    @NotNull(message = "明细数字不能为空")
    private BigDecimal number;

    @Schema(description = "剩余", required = true)
    @NotNull(message = "剩余不能为空")
    private BigDecimal balance;

    @Schema(description = "备注", required = true)
    @NotNull(message = "备注不能为空")
    private String mark;

    @Schema(description = "0 = 带确定 1 = 有效 -1 = 无效", required = true, example = "1")
    @NotNull(message = "0 = 带确定 1 = 有效 -1 = 无效不能为空")
    private Integer status;

}

package co.yixiang.yshop.module.order.controller.admin.storeorder.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import co.yixiang.yshop.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static co.yixiang.yshop.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 订单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StoreOrderPageReqVO extends PageParam {

    @Schema(description = "取餐号", example = "20527")
    private String numberId;

    @Schema(description = "订单号", example = "20527")
    private String orderId;

    @Schema(description = "用户姓名", example = "张三")
    private String realName;

    @Schema(description = "用户电话")
    private String userPhone;

    @Schema(description = "添加时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "订单状态搜索值")
    private Integer orderStatus;

    @Schema(description = "支付状态搜索值")
    private String payStatus;

}

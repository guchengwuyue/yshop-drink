package co.yixiang.yshop.module.product.controller.admin.storeproductreply.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static co.yixiang.yshop.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
* 评论 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class StoreProductReplyBaseVO {

    @Schema(description = "用户ID", required = true, example = "12573")
    @NotNull(message = "用户ID不能为空")
    private Long uid;

    @Schema(description = "订单ID", required = true, example = "5646")
    @NotNull(message = "订单ID不能为空")
    private Long oid;

    @Schema(description = "唯一id", required = true)
    @NotNull(message = "唯一id不能为空")
    private String unique;

    @Schema(description = "产品id", required = true, example = "23509")
    @NotNull(message = "产品id不能为空")
    private Long productId;

    @Schema(description = "某种商品类型(普通商品、秒杀商品）", required = true, example = "2")
    @NotNull(message = "某种商品类型(普通商品、秒杀商品）不能为空")
    private String replyType;

    @Schema(description = "商品分数", required = true)
    @NotNull(message = "商品分数不能为空")
    private Integer productScore;

    @Schema(description = "服务分数", required = true)
    @NotNull(message = "服务分数不能为空")
    private Integer serviceScore;

    @Schema(description = "评论内容", required = true)
    @NotNull(message = "评论内容不能为空")
    private String comment;

    @Schema(description = "评论图片", required = true)
    @NotNull(message = "评论图片不能为空")
    private String pics;

    @Schema(description = "管理员回复内容")
    private String merchantReplyContent;

    @Schema(description = "管理员回复时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime merchantReplyTime;

    @Schema(description = "0未回复1已回复", required = true)
    @NotNull(message = "0未回复1已回复不能为空")
    private Integer isReply;

}

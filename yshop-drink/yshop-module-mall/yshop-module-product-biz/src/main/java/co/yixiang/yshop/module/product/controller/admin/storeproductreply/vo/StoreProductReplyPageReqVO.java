package co.yixiang.yshop.module.product.controller.admin.storeproductreply.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import co.yixiang.yshop.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static co.yixiang.yshop.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 评论分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StoreProductReplyPageReqVO extends PageParam {

    @Schema(description = "用户昵称", example = "12573")
    private String nickname;

    @Schema(description = "用户ID", example = "12573")
    private Long uid;

    @Schema(description = "订单ID", example = "5646")
    private Long oid;

    @Schema(description = "唯一id")
    private String unique;

    @Schema(description = "产品id", example = "23509")
    private Long productId;

    @Schema(description = "某种商品类型(普通商品、秒杀商品）", example = "2")
    private String replyType;

    @Schema(description = "商品分数")
    private Boolean productScore;

    @Schema(description = "服务分数")
    private Boolean serviceScore;

    @Schema(description = "评论内容")
    private String comment;

    @Schema(description = "评论图片")
    private String pics;

    @Schema(description = "评论时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "管理员回复内容")
    private String merchantReplyContent;

    @Schema(description = "管理员回复时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] merchantReplyTime;

    @Schema(description = "0未回复1已回复")
    private Boolean isReply;

}

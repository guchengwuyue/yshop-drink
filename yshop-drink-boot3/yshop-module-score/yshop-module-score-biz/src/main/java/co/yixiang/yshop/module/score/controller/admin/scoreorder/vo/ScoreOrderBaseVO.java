package co.yixiang.yshop.module.score.controller.admin.scoreorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import jakarta.validation.constraints.*;

/**
* 积分商城订单 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ScoreOrderBaseVO {

    @Schema(description = "用户id", required = true, example = "28397")
    @NotNull(message = "用户id不能为空")
    private Long uid;

    @Schema(description = "订单编号", required = true, example = "28397")
    private String orderId;

    @Schema(description = "商品id", required = true, example = "31639")
    @NotNull(message = "商品id不能为空")
    private Long productId;

    @Schema(description = "数量", required = true)
    @NotNull(message = "数量不能为空")
    private Integer number;

    @Schema(description = "单个商品积分", required = true)
    @NotNull(message = "单个商品积分不能为空")
    private Integer score;

    @Schema(description = "总消耗积分", required = true)
    @NotNull(message = "总消耗积分不能为空")
    private Integer totalScore;

    @Schema(description = "下单ip")
    private String ip;

    @Schema(description = "快递公司编码", required = true)
    private String expressSn;

    @Schema(description = "快递编号", required = true)
    @NotNull(message = "快递编号不能为空")
    private String expressNumber;

    @Schema(description = "快递公司", required = true)
    @NotNull(message = "快递公司不能为空")
    private String expressCompany;

    @Schema(description = "收货名称", example = "王五")
    private String customerName;

    @Schema(description = "收货电话")
    private String customerPhone;

    @Schema(description = "收货地址")
    private String customerAddress;

    @Schema(description = "订单状态:0=取消订单,1=正常啊", required = true, example = "1")
    @NotNull(message = "订单状态:0=取消订单,1=正常啊不能为空")
    private Boolean status;

    @Schema(description = "已支付:0=否", required = true, example = "12822")
    @NotNull(message = "已支付:0=否不能为空")
    private Integer havePaid;

    @Schema(description = "已发货:0=否", required = true)
    @NotNull(message = "已发货:0=否不能为空")
    private Integer haveDelivered;

    @Schema(description = "已收货:0=否", required = true)
    @NotNull(message = "已收货:0=否不能为空")
    private Integer haveReceived;

}

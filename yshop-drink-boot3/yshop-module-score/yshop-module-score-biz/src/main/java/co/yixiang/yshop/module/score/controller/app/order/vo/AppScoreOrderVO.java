package co.yixiang.yshop.module.score.controller.app.order.vo;

import co.yixiang.yshop.module.score.controller.app.product.vo.AppScoreProductVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
* 积分商城订单 AppScoreOrderVO VO，提供给添加、修改、详细的子 VO 使用
*/
@Data
public class AppScoreOrderVO {

    private Long id;

    @Schema(description = "用户id", required = true, example = "28397")
    private Long uid;

    @Schema(description = "订单编号", required = true, example = "28397")
    private String orderId;

    @Schema(description = "商品id", required = true, example = "31639")
    private Long productId;

    @Schema(description = "数量", required = true)
    private Integer number;

    @Schema(description = "单个商品积分", required = true)
    private Integer score;

    @Schema(description = "总消耗积分", required = true)
    @NotNull(message = "总消耗积分不能为空")
    private Integer totalScore;

    @Schema(description = "下单ip")
    private String ip;

    @Schema(description = "快递公司编号", required = true)
    private String expressSn;

    @Schema(description = "快递编号", required = true)
    private String expressNumber;

    @Schema(description = "快递公司", required = true)
    private String expressCompany;

    @Schema(description = "收货名称", example = "王五")
    private String customerName;

    @Schema(description = "收货电话")
    private String customerPhone;

    @Schema(description = "收货地址")
    private String customerAddress;

    @Schema(description = "订单状态:0=取消订单,1=正常啊", required = true, example = "1")
    private Boolean status;

    @Schema(description = "已支付:0=否", required = true, example = "12822")
    private Integer havePaid;

    @Schema(description = "已发货:0=否", required = true)
    private Integer haveDelivered;

    @Schema(description = "已收货:0=否", required = true)
    private Integer haveReceived;

    @Schema(description = "订单状态字符串", required = true)
    private String statusText;

    private LocalDateTime createTime;

    private AppScoreProductVO product;

}

package co.yixiang.yshop.module.pay.mq.message;

import co.yixiang.yshop.framework.mq.core.stream.AbstractStreamMessage;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PayNoticeMessage extends AbstractStreamMessage {

    /**
     * 订单编号
     */
    @NotNull(message = "订单编号编号不能为空")
    private String orderId;

    //支付类型
    private String payType;

    @Override
    public String getStreamKey() {
        return "order.pay.notice";
    }
}

package co.yixiang.yshop.module.order.mq.consumer;

import co.yixiang.yshop.framework.mq.redis.core.stream.AbstractRedisStreamMessageListener;
import co.yixiang.yshop.module.order.service.storeorder.AppStoreOrderService;
import co.yixiang.yshop.module.pay.mq.message.PayNoticeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * 消息队列处理支付消息
 */
@Component
@Slf4j
public class PayNoticeConsumer extends AbstractRedisStreamMessageListener<PayNoticeMessage> {

    @Resource
    private AppStoreOrderService appStoreOrderService;

    @Override
    public void onMessage(PayNoticeMessage message) {
        log.info("[onMessage][支付消息内容({})]", message);
        appStoreOrderService.paySuccess(message.getOrderId(),message.getPayType());

    }
}

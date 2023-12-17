package co.yixiang.yshop.module.system.mq.consumer.sms;

import co.yixiang.yshop.module.system.mq.message.sms.SmsTemplateRefreshMessage;
import co.yixiang.yshop.framework.mq.core.pubsub.AbstractChannelMessageListener;
import co.yixiang.yshop.module.system.service.sms.SmsTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 针对 {@link SmsTemplateRefreshMessage} 的消费者
 *
 * @author yshop
 */
@Component
@Slf4j
public class SmsTemplateRefreshConsumer extends AbstractChannelMessageListener<SmsTemplateRefreshMessage> {

    @Resource
    private SmsTemplateService smsTemplateService;

    @Override
    public void onMessage(SmsTemplateRefreshMessage message) {
        log.info("[onMessage][收到 SmsTemplate 刷新消息]");
        smsTemplateService.initLocalCache();
    }

}

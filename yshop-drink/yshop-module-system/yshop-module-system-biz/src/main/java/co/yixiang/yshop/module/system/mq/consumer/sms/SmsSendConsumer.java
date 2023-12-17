package co.yixiang.yshop.module.system.mq.consumer.sms;

import co.yixiang.yshop.module.system.mq.message.sms.SmsSendMessage;
import co.yixiang.yshop.module.system.service.sms.SmsSendService;
import co.yixiang.yshop.framework.mq.core.stream.AbstractStreamMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 针对 {@link SmsSendMessage} 的消费者
 *
 * @author zzf
 */
@Component
@Slf4j
public class SmsSendConsumer extends AbstractStreamMessageListener<SmsSendMessage> {

    @Resource
    private SmsSendService smsSendService;

    @Override
    public void onMessage(SmsSendMessage message) {
        log.info("[onMessage][消息内容({})]", message);
        smsSendService.doSendSms(message);
    }

}

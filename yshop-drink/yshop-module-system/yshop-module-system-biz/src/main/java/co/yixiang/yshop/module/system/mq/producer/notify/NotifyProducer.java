package co.yixiang.yshop.module.system.mq.producer.notify;

import co.yixiang.yshop.framework.mq.core.RedisMQTemplate;
import co.yixiang.yshop.module.system.mq.message.notify.NotifyTemplateRefreshMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Notify 站内信相关消息的 Producer
 *
 * @author xrcoder
 * @since 2022-08-06
 */
@Slf4j
@Component
public class NotifyProducer {

    @Resource
    private RedisMQTemplate redisMQTemplate;


    /**
     * 发送 {@link NotifyTemplateRefreshMessage} 消息
     */
    public void sendNotifyTemplateRefreshMessage() {
        NotifyTemplateRefreshMessage message = new NotifyTemplateRefreshMessage();
        redisMQTemplate.send(message);
    }


}

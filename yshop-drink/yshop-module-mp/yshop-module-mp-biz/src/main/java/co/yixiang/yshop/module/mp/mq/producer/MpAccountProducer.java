package co.yixiang.yshop.module.mp.mq.producer;

import co.yixiang.yshop.framework.mq.core.RedisMQTemplate;
import co.yixiang.yshop.module.mp.mq.message.MpAccountRefreshMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 公众号账号 Producer
 *
 * @author yshop
 */
@Component
public class MpAccountProducer {

    @Resource
    private RedisMQTemplate redisMQTemplate;

    /**
     * 发送 {@link MpAccountRefreshMessage} 消息
     */
    public void sendAccountRefreshMessage() {
        MpAccountRefreshMessage message = new MpAccountRefreshMessage();
        redisMQTemplate.send(message);
    }

}

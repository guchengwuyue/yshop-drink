package co.yixiang.yshop.module.system.mq.producer.auth;

import co.yixiang.yshop.framework.mq.core.RedisMQTemplate;
import co.yixiang.yshop.module.system.mq.message.auth.OAuth2ClientRefreshMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * OAuth 2.0 客户端相关消息的 Producer
 */
@Component
public class OAuth2ClientProducer {

    @Resource
    private RedisMQTemplate redisMQTemplate;

    /**
     * 发送 {@link OAuth2ClientRefreshMessage} 消息
     */
    public void sendOAuth2ClientRefreshMessage() {
        OAuth2ClientRefreshMessage message = new OAuth2ClientRefreshMessage();
        redisMQTemplate.send(message);
    }

}

package co.yixiang.yshop.module.mp.mq.consumer;

import co.yixiang.yshop.framework.mq.core.pubsub.AbstractChannelMessageListener;
import co.yixiang.yshop.module.mp.mq.message.MpAccountRefreshMessage;
import co.yixiang.yshop.module.mp.service.account.MpAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 针对 {@link MpAccountRefreshMessage} 的消费者
 *
 * @author yshop
 */
@Component
@Slf4j
public class MpAccountRefreshConsumer extends AbstractChannelMessageListener<MpAccountRefreshMessage> {

    @Resource
    private MpAccountService mpAccountService;

    @Override
    public void onMessage(MpAccountRefreshMessage message) {
        log.info("[onMessage][收到 Account 刷新消息]");
        mpAccountService.initLocalCache();
    }

}

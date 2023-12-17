package co.yixiang.yshop.module.infra.mq.consumer.file;

import co.yixiang.yshop.framework.mq.core.pubsub.AbstractChannelMessageListener;
import co.yixiang.yshop.module.infra.mq.message.file.FileConfigRefreshMessage;
import co.yixiang.yshop.module.infra.service.file.FileConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 针对 {@link FileConfigRefreshMessage} 的消费者
 *
 * @author yshop
 */
@Component
@Slf4j
public class FileConfigRefreshConsumer extends AbstractChannelMessageListener<FileConfigRefreshMessage> {

    @Resource
    private FileConfigService fileConfigService;

    @Override
    public void onMessage(FileConfigRefreshMessage message) {
        log.info("[onMessage][收到 FileConfig 刷新消息]");
        fileConfigService.initLocalCache();
    }

}

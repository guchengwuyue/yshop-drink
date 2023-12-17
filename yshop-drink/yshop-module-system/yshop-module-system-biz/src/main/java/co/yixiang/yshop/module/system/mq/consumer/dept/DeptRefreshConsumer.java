package co.yixiang.yshop.module.system.mq.consumer.dept;

import co.yixiang.yshop.framework.mq.core.pubsub.AbstractChannelMessageListener;
import co.yixiang.yshop.module.system.mq.message.dept.DeptRefreshMessage;
import co.yixiang.yshop.module.system.service.dept.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 针对 {@link DeptRefreshMessage} 的消费者
 *
 * @author yshop
 */
@Component
@Slf4j
public class DeptRefreshConsumer extends AbstractChannelMessageListener<DeptRefreshMessage> {

    @Resource
    private DeptService deptService;

    @Override
    public void onMessage(DeptRefreshMessage message) {
        log.info("[onMessage][收到 Dept 刷新消息]");
        deptService.initLocalCache();
    }

}

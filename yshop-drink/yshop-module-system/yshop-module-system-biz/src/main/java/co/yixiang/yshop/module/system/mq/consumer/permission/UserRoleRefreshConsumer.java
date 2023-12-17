package co.yixiang.yshop.module.system.mq.consumer.permission;

import co.yixiang.yshop.framework.mq.core.pubsub.AbstractChannelMessageListener;
import co.yixiang.yshop.module.system.mq.message.permission.UserRoleRefreshMessage;
import co.yixiang.yshop.module.system.service.permission.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 针对 {@link UserRoleRefreshMessage} 的消费者
 *
 * @author yshop
 */
@Component
@Slf4j
public class UserRoleRefreshConsumer extends AbstractChannelMessageListener<UserRoleRefreshMessage> {

    @Resource
    private PermissionService permissionService;

    @Override
    public void onMessage(UserRoleRefreshMessage message) {
        log.info("[onMessage][收到 User 与 Role 的关联刷新消息]");
        permissionService.initLocalCache();
    }

}

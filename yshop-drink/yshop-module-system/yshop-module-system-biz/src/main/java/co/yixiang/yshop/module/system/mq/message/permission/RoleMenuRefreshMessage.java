package co.yixiang.yshop.module.system.mq.message.permission;

import co.yixiang.yshop.framework.mq.core.pubsub.AbstractChannelMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色与菜单数据刷新 Message
 *
 * @author yshop
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleMenuRefreshMessage extends AbstractChannelMessage {

    @Override
    public String getChannel() {
        return "system.role-menu.refresh";
    }

}

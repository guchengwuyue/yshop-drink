package co.yixiang.yshop.module.system.mq.message.permission;

import co.yixiang.yshop.framework.mq.core.pubsub.AbstractChannelMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单数据刷新 Message
 *
 * @author yshop
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuRefreshMessage extends AbstractChannelMessage {

    @Override
    public String getChannel() {
        return "system.menu.refresh";
    }

}

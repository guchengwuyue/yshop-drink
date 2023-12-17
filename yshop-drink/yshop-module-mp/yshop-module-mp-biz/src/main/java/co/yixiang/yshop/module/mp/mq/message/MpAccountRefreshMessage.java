package co.yixiang.yshop.module.mp.mq.message;

import co.yixiang.yshop.framework.mq.core.pubsub.AbstractChannelMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 公众号账号刷新 Message
 *
 * @author yshop
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MpAccountRefreshMessage extends AbstractChannelMessage {

    @Override
    public String getChannel() {
        return "mp.account.refresh";
    }

}

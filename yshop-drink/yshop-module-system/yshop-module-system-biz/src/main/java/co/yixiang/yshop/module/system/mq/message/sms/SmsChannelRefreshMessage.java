package co.yixiang.yshop.module.system.mq.message.sms;

import co.yixiang.yshop.framework.mq.core.pubsub.AbstractChannelMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 短信渠道的数据刷新 Message
 *
 * @author yshop
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SmsChannelRefreshMessage extends AbstractChannelMessage {

    @Override
    public String getChannel() {
        return "system.sms-channel.refresh";
    }

}

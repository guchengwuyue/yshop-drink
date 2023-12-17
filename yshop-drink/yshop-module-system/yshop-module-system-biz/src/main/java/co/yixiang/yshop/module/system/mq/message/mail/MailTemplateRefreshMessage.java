package co.yixiang.yshop.module.system.mq.message.mail;

import co.yixiang.yshop.framework.mq.core.pubsub.AbstractChannelMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 邮箱模板的数据刷新 Message
 *
 * @author wangjingyi
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MailTemplateRefreshMessage extends AbstractChannelMessage {

    @Override
    public String getChannel() {
        return "system.mail-template.refresh";
    }

}

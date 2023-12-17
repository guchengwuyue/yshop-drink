package co.yixiang.yshop.module.system.mq.producer.sms;

import co.yixiang.yshop.framework.common.core.KeyValue;
import co.yixiang.yshop.module.system.mq.message.sms.SmsChannelRefreshMessage;
import co.yixiang.yshop.module.system.mq.message.sms.SmsSendMessage;
import co.yixiang.yshop.module.system.mq.message.sms.SmsTemplateRefreshMessage;
import co.yixiang.yshop.framework.mq.core.RedisMQTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Sms 短信相关消息的 Producer
 *
 * @author zzf
 * @date 2021/3/9 16:35
 */
@Slf4j
@Component
public class SmsProducer {

    @Resource
    private RedisMQTemplate redisMQTemplate;

    /**
     * 发送 {@link SmsChannelRefreshMessage} 消息
     */
    public void sendSmsChannelRefreshMessage() {
        SmsChannelRefreshMessage message = new SmsChannelRefreshMessage();
        redisMQTemplate.send(message);
    }

    /**
     * 发送 {@link SmsTemplateRefreshMessage} 消息
     */
    public void sendSmsTemplateRefreshMessage() {
        SmsTemplateRefreshMessage message = new SmsTemplateRefreshMessage();
        redisMQTemplate.send(message);
    }

    /**
     * 发送 {@link SmsSendMessage} 消息
     *
     * @param logId 短信日志编号
     * @param mobile 手机号
     * @param channelId 渠道编号
     * @param apiTemplateId 短信模板编号
     * @param templateParams 短信模板参数
     */
    public void sendSmsSendMessage(Long logId, String mobile,
                                   Long channelId, String apiTemplateId, List<KeyValue<String, Object>> templateParams) {
        SmsSendMessage message = new SmsSendMessage().setLogId(logId).setMobile(mobile);
        message.setChannelId(channelId).setApiTemplateId(apiTemplateId).setTemplateParams(templateParams);
        redisMQTemplate.send(message);
    }

}

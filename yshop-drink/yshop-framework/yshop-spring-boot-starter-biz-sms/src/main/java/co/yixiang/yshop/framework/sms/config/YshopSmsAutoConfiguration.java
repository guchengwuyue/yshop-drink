package co.yixiang.yshop.framework.sms.config;

import co.yixiang.yshop.framework.sms.core.client.SmsClientFactory;
import co.yixiang.yshop.framework.sms.core.client.impl.SmsClientFactoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 短信配置类
 *
 * @author yshop
 */
@AutoConfiguration
public class YshopSmsAutoConfiguration {

    @Bean
    public SmsClientFactory smsClientFactory() {
        return new SmsClientFactoryImpl();
    }

}

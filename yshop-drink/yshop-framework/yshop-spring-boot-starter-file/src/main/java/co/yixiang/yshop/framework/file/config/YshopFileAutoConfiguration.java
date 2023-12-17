package co.yixiang.yshop.framework.file.config;

import co.yixiang.yshop.framework.file.core.client.FileClientFactory;
import co.yixiang.yshop.framework.file.core.client.FileClientFactoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 文件配置类
 *
 * @author yshop
 */
@AutoConfiguration
public class YshopFileAutoConfiguration {

    @Bean
    public FileClientFactory fileClientFactory() {
        return new FileClientFactoryImpl();
    }

}

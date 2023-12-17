package co.yixiang.yshop.module.member.framework.web.config;

import co.yixiang.yshop.framework.swagger.config.YshopSwaggerAutoConfiguration;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * member 模块的 web 组件的 Configuration
 *
 * @author yshop
 */
@Configuration(proxyBeanMethods = false)
public class MemberWebConfiguration {

    /**
     * member 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi memberGroupedOpenApi() {
        return YshopSwaggerAutoConfiguration.buildGroupedOpenApi("member");
    }

}

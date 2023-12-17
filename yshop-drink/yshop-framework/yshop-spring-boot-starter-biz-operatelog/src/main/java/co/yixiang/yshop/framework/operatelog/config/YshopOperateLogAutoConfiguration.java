package co.yixiang.yshop.framework.operatelog.config;

import co.yixiang.yshop.framework.operatelog.core.aop.OperateLogAspect;
import co.yixiang.yshop.framework.operatelog.core.service.OperateLogFrameworkService;
import co.yixiang.yshop.framework.operatelog.core.service.OperateLogFrameworkServiceImpl;
import co.yixiang.yshop.module.system.api.logger.OperateLogApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class YshopOperateLogAutoConfiguration {

    @Bean
    public OperateLogAspect operateLogAspect() {
        return new OperateLogAspect();
    }

    @Bean
    public OperateLogFrameworkService operateLogFrameworkService(OperateLogApi operateLogApi) {
        return new OperateLogFrameworkServiceImpl(operateLogApi);
    }

}

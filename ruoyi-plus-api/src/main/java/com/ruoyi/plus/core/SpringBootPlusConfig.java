/**
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ruoyi.plus.core;

import com.ruoyi.plus.common.aop.LogAop;
import com.ruoyi.plus.common.web.interceptor.*;
import com.ruoyi.plus.resource.web.interceptor.DownloadInterceptor;
import com.ruoyi.plus.resource.web.interceptor.ResourceInterceptor;
import com.ruoyi.plus.resource.web.interceptor.UploadInterceptor;
import com.ruoyi.plus.security.interceptor.JwtInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring-boot-plus配置
 * @author geekidea
 * @date 2019/8/4
 * @since 1.2.0-RELEASE
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({SpringBootPlusProperties.class})
public class SpringBootPlusConfig {

    /**
     * 配置日志AOP
     * @param springBootPlusProperties
     * @return
     */
    @Bean
    public LogAop logAop(SpringBootPlusProperties springBootPlusProperties){
        LogAop logAop = new LogAop();
        logAop.setRequestLogFormat(springBootPlusProperties.isRequestLogFormat());
        logAop.setResponseLogFormat(springBootPlusProperties.isResponseLogFormat());
        log.info("init LogAop success");
        return logAop;
    }

    /**
     * JWT TOKEN验证拦截器
     * @return
     */
    @Bean
    public JwtInterceptor jwtInterceptor(){
        JwtInterceptor jwtInterceptor = new JwtInterceptor();
        return jwtInterceptor;
    }

    /**
     * 权限拦截器
     * @return
     */
    @Bean
    public PermissionInterceptor permissionInterceptor(){
        PermissionInterceptor permissionInterceptor = new PermissionInterceptor();
        return permissionInterceptor;
    }

    /**
     * TOKEN超时拦截器
     * @return
     */
    @Bean
    public TokenTimeoutInterceptor tokenTimeoutInterceptor(){
        TokenTimeoutInterceptor tokenTimeoutInterceptor = new TokenTimeoutInterceptor();
        return tokenTimeoutInterceptor;
    }

    /**
     * 上传拦截器
     * @return
     */
    @Bean
    public UploadInterceptor uploadInterceptor(){
        UploadInterceptor uploadInterceptor = new UploadInterceptor();
        return uploadInterceptor;
    }

    /**
     * 资源拦截器
     * @return
     */
    @Bean
    public ResourceInterceptor resourceInterceptor(){
        ResourceInterceptor resourceInterceptor = new ResourceInterceptor();
        return resourceInterceptor;
    }

    /**
     * 下载拦截器
     * @return
     */
    @Bean
    public DownloadInterceptor downloadInterceptor(){
        DownloadInterceptor downloadInterceptor = new DownloadInterceptor();
        return downloadInterceptor;
    }
}

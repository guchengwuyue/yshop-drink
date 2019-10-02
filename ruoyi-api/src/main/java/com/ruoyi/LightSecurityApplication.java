package com.ruoyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author hupeng <610796224@qq.com>
 */
@SpringBootApplication
//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan(basePackages = {"com.ruoyi.app.common.persistence.dao","com.ruoyi.app.modular.*.dao"})
public class LightSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(LightSecurityApplication.class, args);
    }
}

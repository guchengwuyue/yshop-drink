/**
 * Copyright (C) 2018-2021
 * All rights reserved, Designed By www.yixiang.co
 * 注意：本软件为www.yixiang.co开发研制
 */
package co.yixiang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hupeng <610796224@qq.com>
 */
@SpringBootApplication
//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan(basePackages = {"co.yixiang.app.common.persistence.dao","co.yixiang.app.modular.*.dao"})
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  ==========   ლ(´ڡ`ლ)ﾞ  \n" +
                "\n移动端API启动成功 \n官网：https://www.yixiang.co 提供技术支持ﾞ  \n");
    }
}

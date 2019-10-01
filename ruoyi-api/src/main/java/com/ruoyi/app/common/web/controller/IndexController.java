

package com.ruoyi.app.common.web.controller;

import com.ruoyi.app.common.api.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
@Slf4j
public class IndexController {

    @RequestMapping("/index")
    public ApiResult<String> index(){
        log.debug("index...");
        return ApiResult.ok("Welcome to Spring Boot Plus Project...");
    }
}

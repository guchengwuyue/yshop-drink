/**
 * Copyright (C) 2018-2021
 * All rights reserved, Designed By www.yixiang.co
 * 注意：本软件为www.yixiang.co开发研制
 */

package co.yixiang.app.common.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;


@Controller
@RequestMapping("/docs")
@ApiIgnore
public class ApiDocController extends BaseController {

    /**
    * swaggerUI
    */
    @GetMapping("")
    public String swaggerUI(){
        return "redirect:/swagger-ui.html";
    }

}

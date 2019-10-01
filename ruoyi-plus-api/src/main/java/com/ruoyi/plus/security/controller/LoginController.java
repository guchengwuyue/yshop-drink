/**
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ruoyi.plus.security.controller;

import com.ruoyi.plus.common.api.ApiCode;
import com.ruoyi.plus.common.api.ApiResult;
import com.ruoyi.plus.common.web.controller.BaseController;
import com.ruoyi.plus.security.param.LoginParam;
import com.ruoyi.plus.security.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *
 * </p>
 * @author geekidea
 * @date 2019-05-15
 **/
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;

    @PostMapping("")
    public ApiResult login(@Validated @RequestBody LoginParam loginParam) throws Exception{
        return loginService.login(loginParam);
    }

    @GetMapping("/non")
    public ApiResult nonLogin() throws Exception{
        log.info("nonLogin....");
        return ApiResult.fail(ApiCode.UNAUTHORIZED);
    }
}

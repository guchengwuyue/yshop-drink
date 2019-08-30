package com.ruoyi.app.modular.auth.controller;

import com.itmuch.lightsecurity.annotation.PreAuthorize;
import com.itmuch.lightsecurity.jwt.JwtOperator;
import com.itmuch.lightsecurity.jwt.User;
import com.itmuch.lightsecurity.jwt.UserOperator;
import com.ruoyi.app.common.R;
import com.ruoyi.app.common.persistence.model.StoreMember;
import com.ruoyi.app.modular.member.service.IMemberService;
import com.ruoyi.app.modular.member.service.vo.LoginVO;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @ClassName 登陆授权
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/6/27
 **/
@RequestMapping(value = "/auth")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//@Api(value = "认证授权模块", tags = "认证授权模块", description = "认证授权模块")
public class AuthController {
    private final UserOperator userOperator;
    private final JwtOperator operator;
    private final IMemberService memberService;

    /**
     * 获取当前登录用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/user")
    //@ApiOperation(value = "获取用户信息",notes = "获取用户信息")
    public User user() {
        return userOperator.getUser();
    }


    /**
     * 模拟登录，颁发token
     *
     * @return token字符串
     */
    @PostMapping("/login")
   // @ApiOperation(value = "获取token",notes = "获取token")
    public R loginReturnToken(@Validated @RequestBody LoginVO loginVO) {
        String openid = "oqwKL5Zm8oINt29qiXdbtgYA9EDo";
        StoreMember member = memberService.login(openid);
        if(member == null){
            R.error(401,"用户登陆失败");
        }
        User user = User.builder()
                .id(member.getId().intValue())
                .username(member.getNickname())
                .roles(Arrays.asList("user", "admin"))
                .build();
        String token = operator.generateToken(user);
        HashMap<String,String> map = new HashMap<>();
        map.put("token",token);
        return R.success(map);
    }
}
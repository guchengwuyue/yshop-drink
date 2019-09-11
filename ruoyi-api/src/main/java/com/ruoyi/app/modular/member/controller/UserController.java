package com.ruoyi.app.modular.member.controller;


import com.itmuch.lightsecurity.annotation.PreAuthorize;
import com.itmuch.lightsecurity.jwt.User;
import com.itmuch.lightsecurity.jwt.UserOperator;
import com.ruoyi.app.common.R;
import com.ruoyi.app.common.persistence.model.StoreMember;
import com.ruoyi.app.modular.member.service.IMemberService;
import com.ruoyi.app.modular.member.service.dto.MemberDTO;
import com.ruoyi.app.modular.member.service.mapper.MemberMapper;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import wiki.xsx.core.handler.StringHandler;
//import wiki.xsx.core.util.RedisUtil;

/**
 * @ClassName 个人中心
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/6/27
 **/

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "个人中心", tags = "个人中心模块", description = "个人中心")
public class UserController {
    private final UserOperator userOperator;
    private final IMemberService memberService;
    private final MemberMapper memberMapper;

    @GetMapping(value = "/info")
    //@PreAuthorize("hasAllRoles('user')")
    @ApiOperation(value = "获取个人信息",notes = "获取个人信息")
    public R userInfo(){
        //redis测试
        //StringHandler stringHandler = RedisUtil.getStringHandler();
        //stringHandler.set("name","hupeng");
        User user = userOperator.getUser();
        StoreMember member = memberService.getById(user.getId());
        MemberDTO memberDTO = memberMapper.toDto(member);
        return R.success(memberDTO);
    }

    @GetMapping(value = "/super")
    @PreAuthorize("hasAllRoles('user','admin')")
    @ApiOperation(value = "超管测试信息",notes = "超管测试信息")
    public R superInfo(){
        return R.success("超级管理员才可以访问的权限");
    }
}

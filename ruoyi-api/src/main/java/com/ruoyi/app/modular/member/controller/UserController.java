package com.ruoyi.app.modular.member.controller;


import com.itmuch.lightsecurity.annotation.PreAuthorize;
import com.itmuch.lightsecurity.jwt.User;
import com.itmuch.lightsecurity.jwt.UserOperator;
import com.ruoyi.app.common.R;
import com.ruoyi.app.common.persistence.model.StoreMember;
import com.ruoyi.app.modular.member.service.IMemberService;
import com.ruoyi.app.modular.member.service.dto.MemberDTO;
import com.ruoyi.app.modular.member.service.impl.AddressServiceImpl;
import com.ruoyi.app.modular.member.service.mapper.MemberMapper;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import com.ruoyi.app.modular.member.service.vo.AddressVO;
import com.ruoyi.app.modular.shop.service.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
//import wiki.xsx.core.handler.StringHandler;
//import wiki.xsx.core.util.RedisUtil;

/**
 * @ClassName 个人中心
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/6/27
 **/

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "个人中心", tags = "个人中心模块", description = "个人中心")
public class UserController {
    private final UserOperator userOperator;
    private final IMemberService memberService;
    private final MemberMapper memberMapper;
    private final AddressServiceImpl addressService;

    @GetMapping(value = "/shop/user-my-info")
    @ApiOperation(value = "我的个人信息",notes = "我的个人信息")
    public R userInfo(){
        //redis测试
        //StringHandler stringHandler = RedisUtil.getStringHandler();
        //stringHandler.set("name","hupeng");
        User user = userOperator.getUser();
        StoreMember member = memberService.getById(user.getId());
        MemberDTO memberDTO = memberMapper.toDto(member);
        return R.success(memberDTO);
    }

    @GetMapping(value = "/shop/user-my-address")
    @ApiOperation(value = "我的地址列表",notes = "我的地址列表")
    public R addressList(@Validated @RequestBody PageVO pageVO){
        int userId = userOperator.getUser().getId();
        return R.success(addressService.getList(pageVO,userId));
    }

    @PostMapping(value = "/shop/user-address-add-edit")
    @ApiOperation(value = "添加或修改地址",notes = "添加或修改地址")
    public R addAndEditAdress(@Validated @RequestBody AddressVO addressVO){
        int userId = userOperator.getUser().getId();
        boolean result = addressService.addAndEdit(addressVO,userId);
        if(result){
            return R.success("操作成功");
        }else{
            return R.error(4000,"操作失败");
        }
    }





    /**
    @GetMapping(value = "/super")
    @PreAuthorize("hasAllRoles('user','admin')")
    @ApiOperation(value = "超管测试信息",notes = "超管测试信息")
    public R superInfo(){
        return R.success("超级管理员才可以访问的权限");
    }
    **/
}

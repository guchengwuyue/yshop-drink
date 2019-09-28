package com.ruoyi.app.modular.auth.controller;



import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itmuch.lightsecurity.annotation.PreAuthorize;
import com.itmuch.lightsecurity.jwt.JwtOperator;
import com.itmuch.lightsecurity.jwt.User;
import com.itmuch.lightsecurity.jwt.UserOperator;
import com.ruoyi.app.common.R;
import com.ruoyi.app.common.persistence.model.StoreMember;
import com.ruoyi.app.common.utils.JsonUtils;
import com.ruoyi.app.modular.member.service.IMemberService;
import com.ruoyi.app.modular.member.service.vo.LoginVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

/**
 * @ClassName 登陆授权
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/6/27
 **/
//@RequestMapping(value = "/")
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "认证授权模块", tags = "认证授权模块", description = "认证授权模块")
public class AuthController {
    private final UserOperator userOperator;
    private final JwtOperator operator;
    private final IMemberService memberService;
    private final WxMaService wxService;

    /**
     * 获取当前登录用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/get/user")
    //@ApiOperation(value = "获取用户信息",notes = "获取用户信息")
    public User user() {
        return userOperator.getUser();
    }


    /**
     * 小程序登陆，颁发token，暂时模拟openid
     *
     * @return token字符串
     */
    @PostMapping("/oauth/access_token")
    @ApiOperation(value = "获取token",notes = "获取token")
    public R loginReturnToken(@Validated @RequestBody LoginVO loginVO) {
        Boolean isProduct = true; //true 开启真实小程序环境
        String openid = "orIMY4xGhMmipwFZoSL1vOhUNFZ0";
        WxMaUserInfo userInfo = null;
        try{
            if(isProduct){
                WxMaJscode2SessionResult session = wxService.getUserService()
                        .getSessionInfo(loginVO.getCode());
                //log.info(session.getSessionKey());
                //log.info(session.getOpenid());
                //TODO 可以增加自己的逻辑，关联业务相关数据

                String sessionKey = session.getSessionKey();
                openid = session.getOpenid();
                // 解密用户信息
                userInfo = wxService.getUserService()
                        .getUserInfo(sessionKey, loginVO.getEncrypted_data(), loginVO.getIv());

            }

            StoreMember member = memberService.login(openid);
            //System.out.println(member);
            User user = null;
            if(member == null){
                //新用户插入数据
                StoreMember newMember = new StoreMember();
                newMember.setOpenid(userInfo.getOpenId());
                newMember.setNickname(userInfo.getNickName());
                newMember.setHeadimg(userInfo.getAvatarUrl());
                newMember.setSex(userInfo.getGender());
                newMember.setCreateTime(new Date());
                memberService.save(newMember);
                user = User.builder()
                        .id(newMember.getId().intValue())
                        .username(newMember.getNickname())
                        .build();
            }else{
                user = User.builder()
                        .id(member.getId().intValue())
                        .username(member.getNickname())
                        .build();
            }


            String token = operator.generateToken(user);
            HashMap<String,String> map = new HashMap<>();
            map.put("access_token",token);
            return R.success(map);
        } catch (WxErrorException e) {
            //System.out.println(e.getMessage());
            log.error(e.getMessage());
            return R.error(4000,e.getMessage());
        }


    }
}
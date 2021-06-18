/**
 * Copyright (C) 2018-2021
 * All rights reserved, Designed By www.yixiang.co
 * 注意：本软件为www.yixiang.co开发研制
 */
package co.yixiang.app.modular.auth.controller;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import co.yixiang.app.common.R;
import co.yixiang.app.common.persistence.model.StoreMember;
import co.yixiang.app.modular.member.service.IMemberService;
import co.yixiang.app.modular.member.service.vo.LoginVO;
import com.baomidou.shaun.core.profile.TokenProfile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.baomidou.shaun.core.mgt.SecurityManager;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 * @ClassName 登陆授权
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/6/27
 **/
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "认证授权模块", tags = "认证授权模块", description = "认证授权模块")
public class AuthController {
    private final IMemberService memberService;
    private final WxMaService wxService;
    private final SecurityManager securityManager;





    /**
     * 小程序登陆，颁发token，暂时模拟openid
     *
     * @return token字符串
     */
    @PostMapping("/oauth/access_token")
    @ApiOperation(value = "获取token",notes = "获取token")
    public R loginReturnToken(@Validated @RequestBody LoginVO loginVO) {
        Boolean isProduct = false; //true 开启真实小程序环境
        String openid = "orIMY4xGhMmipwFZoSL1vOhUNFZ0";
        WxMaUserInfo userInfo = null;
        try{
            if(isProduct){
                WxMaJscode2SessionResult session = wxService.getUserService()
                        .getSessionInfo(loginVO.getCode());
                  log.info(session.getSessionKey());
                  log.info(session.getOpenid());
//                //TODO 可以增加自己的逻辑，关联业务相关数据
//
                String sessionKey = session.getSessionKey();
                openid = session.getOpenid();
                // 解密用户信息
                userInfo = wxService.getUserService()
                        .getUserInfo(sessionKey, loginVO.getEncrypted_data(), loginVO.getIv());

            }

            StoreMember member = memberService.login(openid);
            //User user = null;
            if(member == null){
                //新用户插入数据
                StoreMember newMember = new StoreMember();
                newMember.setOpenid(userInfo.getOpenId());
                newMember.setNickname(userInfo.getNickName());
                newMember.setHeadimg(userInfo.getAvatarUrl());
                newMember.setSex(userInfo.getGender());
                newMember.setCreateTime(new Date());
                memberService.save(newMember);
                member = newMember;
            }

            final TokenProfile profile = new TokenProfile();
            profile.setId(member.getId().toString());
            profile.setUsername(member.getNickname());
            profile.addRole("user_role");
            final String token = securityManager.login(profile);

            //String token ="";
            HashMap<String,String> map = new HashMap<>();
            map.put("access_token",token);
            return R.success(map);
        } catch (WxErrorException e) {
            log.error(e.getMessage());
            return R.error(4000,e.getMessage());
        }


    }
}
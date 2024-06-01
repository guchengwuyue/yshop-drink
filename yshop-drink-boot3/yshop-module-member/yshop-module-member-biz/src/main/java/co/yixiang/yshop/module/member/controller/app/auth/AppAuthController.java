package co.yixiang.yshop.module.member.controller.app.auth;

import cn.hutool.core.util.StrUtil;
import co.yixiang.yshop.framework.common.pojo.CommonResult;
import co.yixiang.yshop.framework.security.config.SecurityProperties;
import co.yixiang.yshop.framework.security.core.annotations.PreAuthenticated;
import co.yixiang.yshop.framework.security.core.util.SecurityFrameworkUtils;
import co.yixiang.yshop.module.member.controller.app.auth.vo.*;
import co.yixiang.yshop.module.member.service.auth.MemberAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static co.yixiang.yshop.framework.common.pojo.CommonResult.success;
import static co.yixiang.yshop.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "用户 APP - 认证")
@RestController
@RequestMapping("/member/auth")
@Validated
@Slf4j
public class AppAuthController {

    @Resource
    private MemberAuthService authService;

    @Resource
    private SecurityProperties securityProperties;

    @Value("${yshop.info.isActive}")
    private Boolean isActive;


    @PostMapping("/login")
    @Operation(summary = "使用手机 + 密码登录")
    public CommonResult<AppAuthLoginRespVO> login(@RequestBody @Valid AppAuthLoginReqVO reqVO) {
        return success(authService.login(reqVO));
    }

    @PostMapping("/logout")
    @PermitAll
    @Operation(summary = "登出系统")
    public CommonResult<Boolean> logout(HttpServletRequest request) {
        String token = SecurityFrameworkUtils.obtainAuthorization(request, securityProperties.getTokenHeader(),securityProperties.getTokenParameter());
        if (StrUtil.isNotBlank(token)) {
            authService.logout(token);
        }
        return success(true);
    }

    @PostMapping("/refresh-token")
    @Operation(summary = "刷新令牌")
    @Parameter(name = "refreshToken", description = "刷新令牌", required = true)
    public CommonResult<AppAuthLoginRespVO> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        return success(authService.refreshToken(refreshToken));
    }

    // ========== 短信登录相关 ==========

    @PostMapping("/sms-login")
    @Operation(summary = "使用手机 + 验证码登录")
    public CommonResult<AppAuthLoginRespVO> smsLogin(@RequestBody @Valid AppAuthSmsLoginReqVO reqVO) {
        return success(authService.smsLogin(reqVO));
    }

    @PostMapping("/send-sms-code")
    @Operation(summary = "发送手机验证码")
    public CommonResult<Boolean> sendSmsCode(@RequestBody @Valid AppAuthSmsSendReqVO reqVO) {
        authService.sendSmsCode(getLoginUserId(), reqVO);
        return success(true);
    }

//    @PostMapping("/reset-password")
//    @Operation(summary = "重置密码", description = "用户忘记密码时使用")
//    @PreAuthenticated
//    public CommonResult<Boolean> resetPassword(@RequestBody @Valid AppAuthResetPasswordReqVO reqVO) {
//        authService.resetPassword(reqVO);
//        return success(true);
//    }

    @PostMapping("/update-password")
    @Operation(summary = "修改用户密码", description = "用户修改密码时使用")
    @PreAuthenticated
    public CommonResult<Boolean> updatePassword(@RequestBody @Valid AppAuthUpdatePasswordReqVO reqVO) {
        authService.updatePassword(getLoginUserId(), reqVO);
        return success(true);
    }

    // ========== 社交登录相关 ==========

//    @GetMapping("/social-auth-redirect")
//    @Operation(summary = "社交授权的跳转")
//    @Parameters({
//            @Parameter(name = "type", description = "社交类型", required = true),
//            @Parameter(name = "redirectUri", description = "回调路径")
//    })
//    public CommonResult<String> socialAuthRedirect(@RequestParam("type") Integer type,
//                                                   @RequestParam("redirectUri") String redirectUri) {
//        return CommonResult.success(authService.getSocialAuthorizeUrl(type, redirectUri));
//    }
//
//    @PostMapping("/social-login")
//    @Operation(summary = "社交快捷登录，使用 code 授权码", description = "适合未登录的用户，但是社交账号已绑定用户")
//    public CommonResult<AppAuthLoginRespVO> socialLogin(@RequestBody @Valid AppAuthSocialLoginReqVO reqVO) {
//        return success(authService.socialLogin(reqVO));
//    }

    @PostMapping("/weixin-mini-app-login")
    @Operation(summary = "微信小程序的一键登录")
    public CommonResult<AppAuthLoginRespVO> weixinMiniAppLogin(@RequestBody @Valid AppAuthWeixinMiniAppLoginReqVO reqVO) {
        return success(authService.weixinMiniAppLogin(reqVO));
    }

    @PostMapping("/auth-session")
    @Operation(summary = "微信小程序登录")
    public CommonResult<AppAuthLoginRespVO> weixinMiniAppLogin2(@RequestBody @Valid AppWeixinMiniLoginVO loginVO) {
        AppAuthLoginRespVO appAuthLoginRespVO = authService.weixinMiniAppLogin2(loginVO);
        appAuthLoginRespVO.setIsActive(isActive);
        return success(appAuthLoginRespVO);
    }

    @PostMapping("/auth-miniapp-login")
    @Operation(summary = "微信小程序登录")
    public CommonResult<AppAuthLoginRespVO> weixinMiniAppLogin3(@RequestBody @Valid AppWxMiniLoginVO appWxMiniLoginVO) {
        return success(authService.weixinMiniAppLogin3(appWxMiniLoginVO.getEncryptedData(),appWxMiniLoginVO.getIv()
                ,appWxMiniLoginVO.getOpenid()));
    }



}

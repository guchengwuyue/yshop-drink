package co.yixiang.yshop.module.member.service.auth;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.yshop.framework.common.enums.CommonStatusEnum;
import co.yixiang.yshop.framework.common.enums.UserTypeEnum;
import co.yixiang.yshop.framework.common.util.monitor.TracerUtils;
import co.yixiang.yshop.framework.common.util.servlet.ServletUtils;
import co.yixiang.yshop.module.member.api.user.dto.WechatUserDto;
import co.yixiang.yshop.module.member.controller.app.auth.vo.*;
import co.yixiang.yshop.module.member.convert.auth.AuthConvert;
import co.yixiang.yshop.module.member.convert.user.UserConvert;
import co.yixiang.yshop.module.member.dal.dataobject.user.MemberUserDO;
import co.yixiang.yshop.module.member.dal.mysql.user.MemberUserMapper;
import co.yixiang.yshop.module.member.dal.redis.order.MiniRedisDAO;
import co.yixiang.yshop.module.member.enums.LoginTypeEnum;
import co.yixiang.yshop.module.member.service.user.MemberUserService;
import co.yixiang.yshop.module.system.api.logger.LoginLogApi;
import co.yixiang.yshop.module.system.api.logger.dto.LoginLogCreateReqDTO;
import co.yixiang.yshop.module.system.api.oauth2.OAuth2TokenApi;
import co.yixiang.yshop.module.system.api.oauth2.dto.OAuth2AccessTokenCreateReqDTO;
import co.yixiang.yshop.module.system.api.oauth2.dto.OAuth2AccessTokenRespDTO;
import co.yixiang.yshop.module.system.api.sms.SmsCodeApi;
import co.yixiang.yshop.module.system.api.social.SocialUserApi;
import co.yixiang.yshop.module.system.api.social.dto.SocialUserBindReqDTO;
import co.yixiang.yshop.module.system.enums.logger.LoginLogTypeEnum;
import co.yixiang.yshop.module.system.enums.logger.LoginResultEnum;
import co.yixiang.yshop.module.system.enums.oauth2.OAuth2ClientConstants;
import co.yixiang.yshop.module.system.enums.sms.SmsSceneEnum;
import co.yixiang.yshop.module.system.enums.social.SocialTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.annotations.VisibleForTesting;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;

import static co.yixiang.yshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static co.yixiang.yshop.framework.common.util.servlet.ServletUtils.getClientIP;
import static co.yixiang.yshop.module.member.enums.ErrorCodeConstants.*;

/**
 * 会员的认证 Service 接口
 *
 * @author yshop
 */
@Service
@Slf4j
public class MemberAuthServiceImpl implements MemberAuthService {

    @Resource
    private MemberUserService userService;
    @Resource
    private SmsCodeApi smsCodeApi;
    @Resource
    private LoginLogApi loginLogApi;
    @Resource
    private SocialUserApi socialUserApi;
    @Resource
    private OAuth2TokenApi oauth2TokenApi;

    @Resource
    private WxMaService wxMaService;
    @Resource
    private WxMpService mpService;

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private MemberUserMapper userMapper;
    @Resource
    private MiniRedisDAO miniRedisDAO;

    @Override
    public AppAuthLoginRespVO login(AppAuthLoginReqVO reqVO) {
        // 使用手机 + 密码，进行登录。
        MemberUserDO user = login0(reqVO.getMobile(), reqVO.getPassword());

        // 如果 socialType 非空，说明需要绑定社交用户
        if (reqVO.getSocialType() != null) {
            socialUserApi.bindSocialUser(new SocialUserBindReqDTO(user.getId(), getUserType().getValue(),
                    reqVO.getSocialType(), reqVO.getSocialCode(), reqVO.getSocialState()));
        }

        // 创建 Token 令牌，记录登录日志
        return createTokenAfterLoginSuccess(user, reqVO.getMobile(), LoginLogTypeEnum.LOGIN_MOBILE);
    }

    @Override
    @Transactional
    public AppAuthLoginRespVO smsLogin(AppAuthSmsLoginReqVO reqVO) {
        // 校验验证码
        String userIp = getClientIP();
        smsCodeApi.useSmsCode(AuthConvert.INSTANCE.convert(reqVO, SmsSceneEnum.MEMBER_LOGIN.getScene(), userIp));
        MemberUserDO user = null;

        if(StrUtil.isNotBlank(reqVO.getOpenid())){
            LambdaQueryWrapper<MemberUserDO> lambdaQueryWrapper =  new LambdaQueryWrapper<>();
            //根据openid查用户是否存在
            if(LoginTypeEnum.ROUNTINE.getValue().equals(reqVO.getFrom())){
                lambdaQueryWrapper.eq(MemberUserDO::getRoutineOpenid,reqVO.getOpenid());
            }else {
                lambdaQueryWrapper.eq(MemberUserDO::getOpenid,reqVO.getOpenid());
            }

            user = userMapper.selectOne(lambdaQueryWrapper);
        }

        if(user == null){
            // 获得获得注册用户
            user = userService.createUserIfAbsent(reqVO.getMobile(), userIp,reqVO.getFrom());
            Assert.notNull(user, "获取用户失败，结果为空");

            if(StrUtil.isNotBlank(reqVO.getOpenid())){
                if(LoginTypeEnum.ROUNTINE.getValue().equals(reqVO.getFrom())){
                    user.setRoutineOpenid(reqVO.getOpenid());
                }else {
                    user.setOpenid(reqVO.getOpenid());
                }

                userService.updateById(user);
            }
        }else {
            if(StrUtil.isBlank(user.getMobile())){
                user.setMobile(reqVO.getMobile());
                userService.updateById(user);
            }
        }

        // 如果 socialType 非空，说明需要绑定社交用户
        if (reqVO.getSocialType() != null) {
            socialUserApi.bindSocialUser(new SocialUserBindReqDTO(user.getId(), getUserType().getValue(),
                    reqVO.getSocialType(), reqVO.getSocialCode(), reqVO.getSocialState()));
        }

        // 创建 Token 令牌，记录登录日志
        AppAuthLoginRespVO appAuthLoginRespVO = createTokenAfterLoginSuccess(user, reqVO.getMobile(),
                LoginLogTypeEnum.LOGIN_SMS);

        appAuthLoginRespVO.setUserInfo(UserConvert.INSTANCE.convert3(user));
        return appAuthLoginRespVO;
        // 创建 Token 令牌，记录登录日志
       // return createTokenAfterLoginSuccess(user, reqVO.getMobile(), LoginLogTypeEnum.LOGIN_SMS);
    }

    @Override
    public AppAuthLoginRespVO socialLogin(AppAuthSocialLoginReqVO reqVO) {
        // 使用 code 授权码，进行登录。然后，获得到绑定的用户编号
        Long userId = socialUserApi.getBindUserId(UserTypeEnum.MEMBER.getValue(), reqVO.getType(),
                reqVO.getCode(), reqVO.getState());
        if (userId == null) {
            throw exception(AUTH_THIRD_LOGIN_NOT_BIND);
        }

        // 自动登录
        MemberUserDO user = userService.getUser(userId);
        if (user == null) {
            throw exception(USER_NOT_EXISTS);
        }

        // 创建 Token 令牌，记录登录日志
        return createTokenAfterLoginSuccess(user, user.getMobile(), LoginLogTypeEnum.LOGIN_SOCIAL);
    }

    @Override
    public AppAuthLoginRespVO weixinMiniAppLogin(AppAuthWeixinMiniAppLoginReqVO reqVO) {
        // 获得对应的手机号信息
        WxMaPhoneNumberInfo phoneNumberInfo;
        try {
            phoneNumberInfo = wxMaService.getUserService().getNewPhoneNoInfo(reqVO.getPhoneCode());
        } catch (Exception exception) {
            throw exception(AUTH_WEIXIN_MINI_APP_PHONE_CODE_ERROR);
        }
        // 获得获得注册用户
        MemberUserDO user = userService.createUserIfAbsent(phoneNumberInfo.getPurePhoneNumber(), getClientIP(),
                LoginTypeEnum.ROUNTINE.getValue());
        Assert.notNull(user, "获取用户失败，结果为空");

        // 绑定社交用户
        socialUserApi.bindSocialUser(new SocialUserBindReqDTO(user.getId(), getUserType().getValue(),
                SocialTypeEnum.WECHAT_MINI_APP.getType(), reqVO.getLoginCode(), ""));

        // 创建 Token 令牌，记录登录日志
        return createTokenAfterLoginSuccess(user, user.getMobile(), LoginLogTypeEnum.LOGIN_SOCIAL);
    }


    /**
     * 微信小程序的一键登录
     *
     * @param loginVO 登录信息
     * @return 登录结果
     */
    @Override
    public AppAuthLoginRespVO weixinMiniAppLogin2(AppWeixinMiniLoginVO loginVO){
        try {
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(loginVO.getCode());
            log.info(session.getOpenid());
            miniRedisDAO.set(session.getSessionKey(),session.getOpenid());
            //根据openid查用户是否存在
            MemberUserDO memberUserDO = userMapper.selectOne(new LambdaQueryWrapper<MemberUserDO>()
                    .eq(MemberUserDO::getRoutineOpenid,session.getOpenid()));
            AppAuthLoginRespVO appAuthLoginRespVO = new AppAuthLoginRespVO();

            if(memberUserDO != null) {
                appAuthLoginRespVO = createTokenAfterLoginSuccess(memberUserDO, memberUserDO.getMobile(),
                        LoginLogTypeEnum.LOGIN_SOCIAL);
                appAuthLoginRespVO.setUserInfo(UserConvert.INSTANCE.convert3(memberUserDO));
                //return appAuthLoginRespVO;
            }
            appAuthLoginRespVO.setOpenId(session.getOpenid());
            return appAuthLoginRespVO;

        } catch (WxErrorException e) {
            log.error(e.getMessage());
            throw exception(MINI_AUTH_LOGIN_BAD);
        }
    }


    /**
     * 微信小程序的一键登录
     *
     * @param encryptedData encryptedData
     * @param encryptedData encryptedData
     * @param encryptedData encryptedData
     * @return 登录结果
     */
    @Override
    public AppAuthLoginRespVO weixinMiniAppLogin3(String encryptedData, String iv, String openid){
        // 解密用户信息
        String sessionKey = miniRedisDAO.get(openid);
        if(StrUtil.isBlank(sessionKey)) {
            throw exception(MINI_AUTH_LOGIN_BAD2);
        }

        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxMaService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);

        // 用户已经存在
        MemberUserDO memberUserDO = userMapper.selectByMobile(phoneNoInfo.getPhoneNumber());
        if (memberUserDO == null) {
            // 获得获得注册用户
            memberUserDO = userService.createUserIfAbsent(phoneNoInfo.getPhoneNumber(), getClientIP(),
                    LoginTypeEnum.ROUNTINE.getValue());

            memberUserDO.setRoutineOpenid(openid);

            memberUserDO.setNickname("yshop用户_" + memberUserDO.getId());
            userMapper.updateById(memberUserDO);

        }
        if(StrUtil.isBlank(memberUserDO.getRoutineOpenid())){
            memberUserDO.setRoutineOpenid(openid);
            userMapper.updateById(memberUserDO);
        }

        // 创建 Token 令牌，记录登录日志
        AppAuthLoginRespVO appAuthLoginRespVO = createTokenAfterLoginSuccess(memberUserDO, memberUserDO.getMobile(),
                LoginLogTypeEnum.LOGIN_SOCIAL);
        appAuthLoginRespVO.setOpenId(openid);
        appAuthLoginRespVO.setUserInfo(UserConvert.INSTANCE.convert3(memberUserDO));
        return appAuthLoginRespVO;
    }


    private AppAuthLoginRespVO createTokenAfterLoginSuccess(MemberUserDO user, String mobile, LoginLogTypeEnum logType) {
        // 插入登陆日志
        createLoginLog(user.getId(), mobile, logType, LoginResultEnum.SUCCESS);
        // 创建 Token 令牌
        OAuth2AccessTokenRespDTO accessTokenRespDTO = oauth2TokenApi.createAccessToken(new OAuth2AccessTokenCreateReqDTO()
                .setUserId(user.getId()).setUserType(getUserType().getValue())
                .setClientId(OAuth2ClientConstants.CLIENT_ID_DEFAULT));
        // 构建返回结果
        return AuthConvert.INSTANCE.convert(accessTokenRespDTO);
    }

    @Override
    public String getSocialAuthorizeUrl(Integer type, String redirectUri) {
        return socialUserApi.getAuthorizeUrl(type, redirectUri);
    }

    private MemberUserDO login0(String mobile, String password) {
        final LoginLogTypeEnum logTypeEnum = LoginLogTypeEnum.LOGIN_MOBILE;
        // 校验账号是否存在
        MemberUserDO user = userService.getUserByMobile(mobile);
        if (user == null) {
            createLoginLog(null, mobile, logTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (!userService.isPasswordMatch(password, user.getPassword())) {
            createLoginLog(user.getId(), mobile, logTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        // 校验是否禁用
        if (ObjectUtil.notEqual(user.getStatus(), CommonStatusEnum.ENABLE.getStatus())) {
            createLoginLog(user.getId(), mobile, logTypeEnum, LoginResultEnum.USER_DISABLED);
            throw exception(AUTH_LOGIN_USER_DISABLED);
        }
        return user;
    }

    private void createLoginLog(Long userId, String mobile, LoginLogTypeEnum logType, LoginResultEnum loginResult) {
        // 插入登录日志
        LoginLogCreateReqDTO reqDTO = new LoginLogCreateReqDTO();
        reqDTO.setLogType(logType.getType());
        reqDTO.setTraceId(TracerUtils.getTraceId());
        reqDTO.setUserId(userId);
        reqDTO.setUserType(getUserType().getValue());
        reqDTO.setUsername(mobile);
        reqDTO.setUserAgent(ServletUtils.getUserAgent());
        reqDTO.setUserIp(getClientIP());
        reqDTO.setResult(loginResult.getResult());
        loginLogApi.createLoginLog(reqDTO);
        // 更新最后登录时间
        if (userId != null && Objects.equals(LoginResultEnum.SUCCESS.getResult(), loginResult.getResult())) {
            userService.updateUserLogin(userId, getClientIP());
        }
    }

    @Override
    public void logout(String token) {
        // 删除访问令牌
        OAuth2AccessTokenRespDTO accessTokenRespDTO = oauth2TokenApi.removeAccessToken(token);
        if (accessTokenRespDTO == null) {
            return;
        }
        // 删除成功，则记录登出日志
        createLogoutLog(accessTokenRespDTO.getUserId());
    }

    @Override
    public void updatePassword(Long userId, AppAuthUpdatePasswordReqVO reqVO) {
        // 检验旧密码
        MemberUserDO userDO = checkOldPassword(userId, reqVO.getOldPassword());

        // 更新用户密码
        // TODO yshop：需要重构到用户模块
        userMapper.updateById(MemberUserDO.builder().id(userDO.getId())
                .password(passwordEncoder.encode(reqVO.getPassword())).build());
    }

    @Override
    public void resetPassword(AppAuthResetPasswordReqVO reqVO) {
        // 检验用户是否存在
        MemberUserDO userDO = checkUserIfExists(reqVO.getMobile());

        // 使用验证码
        smsCodeApi.useSmsCode(AuthConvert.INSTANCE.convert(reqVO, SmsSceneEnum.MEMBER_FORGET_PASSWORD,
                getClientIP()));

        // 更新密码
        userMapper.updateById(MemberUserDO.builder().id(userDO.getId())
                .password(passwordEncoder.encode(reqVO.getPassword())).build());
    }

    @Override
    public void sendSmsCode(Long userId, AppAuthSmsSendReqVO reqVO) {
        // TODO 要根据不同的场景，校验是否有用户
        smsCodeApi.sendSmsCode(AuthConvert.INSTANCE.convert(reqVO).setCreateIp(getClientIP()));
    }

    @Override
    public AppAuthLoginRespVO refreshToken(String refreshToken) {
        OAuth2AccessTokenRespDTO accessTokenDO = oauth2TokenApi.refreshAccessToken(refreshToken, OAuth2ClientConstants.CLIENT_ID_DEFAULT);
        return AuthConvert.INSTANCE.convert(accessTokenDO);
    }

    /**
     * 校验旧密码
     *
     * @param id          用户 id
     * @param oldPassword 旧密码
     * @return MemberUserDO 用户实体
     */
    @VisibleForTesting
    public MemberUserDO checkOldPassword(Long id, String oldPassword) {
        MemberUserDO user = userMapper.selectById(id);
        if (user == null) {
            throw exception(USER_NOT_EXISTS);
        }
        // 参数：未加密密码，编码后的密码
        if (!passwordEncoder.matches(oldPassword,user.getPassword())) {
            throw exception(USER_PASSWORD_FAILED);
        }
        return user;
    }

    public MemberUserDO checkUserIfExists(String mobile) {
        MemberUserDO user = userMapper.selectByMobile(mobile);
        if (user == null) {
            throw exception(USER_NOT_EXISTS);
        }
        return user;
    }

    private void createLogoutLog(Long userId) {
        LoginLogCreateReqDTO reqDTO = new LoginLogCreateReqDTO();
        reqDTO.setLogType(LoginLogTypeEnum.LOGOUT_SELF.getType());
        reqDTO.setTraceId(TracerUtils.getTraceId());
        reqDTO.setUserId(userId);
        reqDTO.setUserType(getUserType().getValue());
        reqDTO.setUsername(getMobile(userId));
        reqDTO.setUserAgent(ServletUtils.getUserAgent());
        reqDTO.setUserIp(getClientIP());
        reqDTO.setResult(LoginResultEnum.SUCCESS.getResult());
        loginLogApi.createLoginLog(reqDTO);
    }

    private String getMobile(Long userId) {
        if (userId == null) {
            return null;
        }
        MemberUserDO user = userService.getUser(userId);
        return user != null ? user.getMobile() : null;
    }

    private UserTypeEnum getUserType() {
        return UserTypeEnum.MEMBER;
    }

}

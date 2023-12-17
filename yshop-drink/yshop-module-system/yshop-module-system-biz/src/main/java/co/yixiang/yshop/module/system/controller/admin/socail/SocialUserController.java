package co.yixiang.yshop.module.system.controller.admin.socail;

import co.yixiang.yshop.framework.common.enums.UserTypeEnum;
import co.yixiang.yshop.framework.common.pojo.CommonResult;
import co.yixiang.yshop.module.system.controller.admin.socail.vo.SocialUserBindReqVO;
import co.yixiang.yshop.module.system.controller.admin.socail.vo.SocialUserUnbindReqVO;
import co.yixiang.yshop.module.system.convert.social.SocialUserConvert;
import co.yixiang.yshop.module.system.service.social.SocialUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static co.yixiang.yshop.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "管理后台 - 社交用户")
@RestController
@RequestMapping("/system/social-user")
@Validated
public class SocialUserController {

    @Resource
    private SocialUserService socialUserService;

    @PostMapping("/bind")
    @Operation(summary = "社交绑定，使用 code 授权码")
    public CommonResult<Boolean> socialBind(@RequestBody @Valid SocialUserBindReqVO reqVO) {
        socialUserService.bindSocialUser(SocialUserConvert.INSTANCE.convert(getLoginUserId(), UserTypeEnum.ADMIN.getValue(), reqVO));
        return CommonResult.success(true);
    }

    @DeleteMapping("/unbind")
    @Operation(summary = "取消社交绑定")
    public CommonResult<Boolean> socialUnbind(@RequestBody SocialUserUnbindReqVO reqVO) {
        socialUserService.unbindSocialUser(getLoginUserId(), UserTypeEnum.ADMIN.getValue(), reqVO.getType(), reqVO.getOpenid());
        return CommonResult.success(true);
    }

}

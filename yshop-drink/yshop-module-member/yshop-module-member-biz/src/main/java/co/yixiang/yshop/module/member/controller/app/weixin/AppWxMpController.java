package co.yixiang.yshop.module.member.controller.app.weixin;

import co.yixiang.yshop.framework.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static co.yixiang.yshop.framework.common.pojo.CommonResult.success;

@Tag(name = "微信公众号")
@RestController
@RequestMapping("/member/wx-mp")
@Validated
@Slf4j
public class AppWxMpController {

    @Resource
    private WxMpService mpService;

    @GetMapping("/create-jsapi-signature")
    @Operation(summary = "创建微信 JS SDK 初始化所需的签名",
        description = "参考 https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/JS-SDK.html 文档")
    public CommonResult<WxJsapiSignature> createJsapiSignature(@RequestParam("url") String url) throws WxErrorException {
        return success(mpService.createJsapiSignature(url));
    }

}

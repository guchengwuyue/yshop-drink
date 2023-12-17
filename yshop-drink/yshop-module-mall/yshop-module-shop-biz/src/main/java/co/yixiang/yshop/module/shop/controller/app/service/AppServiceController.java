/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.yshop.module.shop.controller.app.service;


import co.yixiang.yshop.framework.common.enums.ShopCommonEnum;
import co.yixiang.yshop.framework.common.pojo.CommonResult;
import co.yixiang.yshop.module.shop.controller.app.service.vo.AppServiceVO;
import co.yixiang.yshop.module.shop.convert.service.ServiceConvert;
import co.yixiang.yshop.module.shop.dal.dataobject.service.ServiceDO;
import co.yixiang.yshop.module.shop.service.service.AppServiceService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static co.yixiang.yshop.framework.common.pojo.CommonResult.success;

/**
 * <p>
 * 首页广告控制器
 * </p>
 *
 * @author hupeng
 * @since 2023-8-11
 */
@Slf4j
@RestController
@Tag(name = "用户 APP - 服务菜单")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/service")
public class AppServiceController {

    private final AppServiceService appServiceService;


    @GetMapping("/list")
    @Operation(summary = "服务菜单列表")
    public CommonResult<List<AppServiceVO>> getList() {
        List<ServiceDO> appShopAdsVOS = appServiceService.list(new LambdaQueryWrapper<ServiceDO>()
                .eq(ServiceDO::getStatus, ShopCommonEnum.IS_STATUS_1.getValue()));
        return success(ServiceConvert.INSTANCE.convertList03(appShopAdsVOS));
    }

    @GetMapping("/content")
    @Operation(summary = "服务菜单列表")
    public CommonResult<AppServiceVO> getContent(@RequestParam("id") Integer id) {
        ServiceDO appShopAdsVO = appServiceService.getById(id);
        return success(ServiceConvert.INSTANCE.convert03(appShopAdsVO));
    }



}


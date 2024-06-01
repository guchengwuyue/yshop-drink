/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.yshop.module.shop.controller.app.ad;


import co.yixiang.yshop.framework.common.pojo.CommonResult;
import co.yixiang.yshop.framework.security.core.annotations.PreAuthenticated;
import co.yixiang.yshop.module.shop.controller.app.ad.vo.AppShopAdsVO;
import co.yixiang.yshop.module.shop.convert.shopads.ShopAdsConvert;
import co.yixiang.yshop.module.shop.dal.dataobject.shopads.ShopAdsDO;
import co.yixiang.yshop.module.shop.service.shopads.AppShopAdsService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static co.yixiang.yshop.framework.common.pojo.CommonResult.success;

/**
 * <p>
 * 首页广告控制器
 * </p>
 *
 * @author hupeng
 * @since 2023-8-10
 */
@Slf4j
@RestController
@Tag(name = "用户 APP - 广告")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/ad")
public class AppAdController {

    private final AppShopAdsService appShopAdsService;

    @Value("${yshop.info.isActive}")
    private Boolean isActive;

    @GetMapping("/list")
    @Operation(summary = "查询广告列表")
    @Parameter(name = "shop_id", description = "店铺id", required = true, example = "10      ")
    public CommonResult<Map<String,Object>> getList(@RequestParam("shop_id") Long shopId) {
//        List<ShopAdsDO> appShopAdsVOS = appShopAdsService.list(new LambdaQueryWrapper<ShopAdsDO>()
//                .eq(ShopAdsDO::getId,0).or().apply(shopId > 0,
//                        "FIND_IN_SET ('" + shopId + "',shop_id)"));
        List<ShopAdsDO> appShopAdsVOS = appShopAdsService.list(new LambdaQueryWrapper<ShopAdsDO>()
                .eq(ShopAdsDO::getShopId,0).or().eq(ShopAdsDO::getShopId,shopId));
        Map<String,Object> map = new HashMap<>();
        map.put("list",ShopAdsConvert.INSTANCE.convertList03(appShopAdsVOS));
        map.put("isActive",isActive);
        return success(map);
    }



}


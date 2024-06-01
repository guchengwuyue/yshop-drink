/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.yshop.module.store.controller.app.storeshop;

import co.yixiang.yshop.framework.common.pojo.CommonResult;
import co.yixiang.yshop.module.store.controller.app.storeshop.vo.AppStoreShopVO;
import co.yixiang.yshop.module.store.convert.storeshop.StoreShopConvert;
import co.yixiang.yshop.module.store.dal.dataobject.storeshop.StoreShopDO;
import co.yixiang.yshop.module.store.service.storeshop.AppStoreShopService;
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
 * 门店控制器
 * </p>
 *
 * @author hupeng
 * @since 2023-8-14
 */
@Slf4j
@RestController
@Tag(name = "用户 APP - 门店")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/store")
public class AppStoreController {

    private final AppStoreShopService appStoreShopService;


    @GetMapping("/nearby")
    @Operation(summary = "获取最近的店铺")
    public CommonResult<AppStoreShopVO> getNearby(@RequestParam("lng") double lon,
                                                      @RequestParam("lat") double lat,
                                                      @RequestParam("kw") String name,
                                                      @RequestParam("shop_id") Integer shopId) {
        List<AppStoreShopVO> list = appStoreShopService.getStoreList(lon,lat,name,shopId);
        if(list != null ){
            return success(list.get(0));
        }
        return success(new AppStoreShopVO());
    }

    @GetMapping("/list")
    @Operation(summary = "服务菜单列表")
    public CommonResult<List<AppStoreShopVO>> getList(@RequestParam("lng") double lon,
                                                      @RequestParam("lat") double lat,
                                                      @RequestParam("kw") String name,
                                                      @RequestParam("shop_id") Integer shopId) {

        return success(appStoreShopService.getStoreList(lon,lat,name,shopId));
    }

    @GetMapping("/getShop")
    @Operation(summary = "获取最近的店铺")
    public CommonResult<AppStoreShopVO> getShop(@RequestParam("shop_id") Integer shopId) {
        StoreShopDO storeShopDO = appStoreShopService.getById(shopId);
        return success(StoreShopConvert.INSTANCE.convert02(storeShopDO));
    }



}


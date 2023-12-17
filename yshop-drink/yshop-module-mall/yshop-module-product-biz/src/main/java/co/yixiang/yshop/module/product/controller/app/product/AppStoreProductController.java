/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.yshop.module.product.controller.app.product;


import co.yixiang.yshop.framework.common.pojo.CommonResult;
import co.yixiang.yshop.module.product.controller.app.category.vo.AppCategoryRespVO;
import co.yixiang.yshop.module.product.controller.app.product.param.AppStoreProductQueryParam;
import co.yixiang.yshop.module.product.service.storeproduct.AppStoreProductService;
import co.yixiang.yshop.module.product.service.storeproductreply.AppStoreProductReplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static co.yixiang.yshop.framework.common.pojo.CommonResult.success;

/**
 * <p>
 * 商品控制器
 * </p>
 *
 * @author hupeng
 * @since 2023-8-16
 */
@Slf4j
@RestController
@Tag(name = "用户 APP - 商品")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/product")
public class AppStoreProductController {

    private final AppStoreProductService storeProductService;
    private final AppStoreProductReplyService appStoreProductReplyService;





    /**
     * 获取产品列表
     */
    @GetMapping("/products")
    @Operation(summary = "商品列表")
    public CommonResult<List<AppCategoryRespVO>> goodsList(AppStoreProductQueryParam productQueryParam){
        return success(storeProductService.getGoodsList(productQueryParam));
    }









}


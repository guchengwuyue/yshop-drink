/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.yshop.module.coupon.controller.app.coupon;

import co.yixiang.yshop.framework.common.enums.ShopCommonEnum;
import co.yixiang.yshop.framework.common.pojo.CommonResult;
import co.yixiang.yshop.framework.security.core.annotations.PreAuthenticated;
import co.yixiang.yshop.module.coupon.controller.app.coupon.vo.AppCouponVO;
import co.yixiang.yshop.module.coupon.controller.app.coupon.vo.AppMyCouponVO;
import co.yixiang.yshop.module.coupon.controller.app.coupon.vo.AppReceVO;
import co.yixiang.yshop.module.coupon.dal.dataobject.couponuser.CouponUserDO;
import co.yixiang.yshop.module.coupon.service.coupon.AppCouponService;
import co.yixiang.yshop.module.coupon.service.couponuser.AppCouponUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static co.yixiang.yshop.framework.common.pojo.CommonResult.success;
import static co.yixiang.yshop.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

/**
 * <p>
 * 优惠券控制器
 * </p>
 *
 * @author hupeng
 * @since 2023-8-20
 */
@Slf4j
@RestController
@Tag(name = "用户 APP - 优惠券")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/coupon")
public class AppCouponController {

    private final AppCouponUserService appCouponUserService;
    private final AppCouponService appCouponService;


    @PreAuthenticated
    @GetMapping("/count")
    @Operation(summary = "获取可用优惠券数量")
    public CommonResult<Long> getCount(@RequestParam("shop_id") Integer shopId,
                                                      @RequestParam("type") Integer type) {
        Long uid = getLoginUserId();
        LocalDateTime nowTime = LocalDateTime.now();
        Long count = appCouponUserService.count(new LambdaQueryWrapper<CouponUserDO>()
                .eq(CouponUserDO::getUserId,uid)
                .eq(CouponUserDO::getShopId,shopId)
                .lt(CouponUserDO::getStartTime,nowTime)
                .gt(CouponUserDO::getEndTime,nowTime)
                .and(i->i.eq(CouponUserDO::getType,type).or().eq(CouponUserDO::getType,0))
                .eq(CouponUserDO::getStatus, ShopCommonEnum.IS_STATUS_0));

        return success(count);
    }

    /**
     * 获取我的优惠券
     */
    @PreAuthenticated
    @GetMapping("/my")
    @Parameters({
            @Parameter(name = "shopId", description = "店铺ID",
                     example = "1"),
            @Parameter(name = "type", description = "0-未使用 1-已使用 2-已过期",
                    example = "1"),
            @Parameter(name = "page", description = "页码,默认为1",
                    required = true, example = "1"),
            @Parameter(name = "pagesize", description = "页大小,默认为10",
                    required = true, example = "10      ")
    })
    @Operation(summary = "获取我的优惠券")
    public CommonResult<List<AppMyCouponVO>> myList(@RequestParam(value = "shopId",required = false)  Long shopId,
                                                    @RequestParam(value = "type",defaultValue = "0") int type,
                                                    @RequestParam(value = "page",defaultValue = "1") int page,
                                                    @RequestParam(value = "pagesize",defaultValue = "10") int pagesize){
        Long uid = getLoginUserId();
        return success(appCouponUserService.getList(uid,shopId,type,page,pagesize));
    }

    /**
     * 获取未被领取优惠券
     */
    @PreAuthenticated
    @GetMapping("/not")
    @Parameters({
            @Parameter(name = "shopId", description = "店铺ID",
                     example = "1"),
            @Parameter(name = "page", description = "页码,默认为1",
                    required = true, example = "1"),
            @Parameter(name = "pagesize", description = "页大小,默认为10",
                    required = true, example = "10      ")
    })
    @Operation(summary = "获取未被领取优惠券")
    public CommonResult<List<AppCouponVO>> myNotList(@RequestParam(value = "id",required = false)  Long shopId,
                                                     @RequestParam(value = "page",defaultValue = "1") int page,
                                                     @RequestParam(value = "pagesize",defaultValue = "10") int pagesize){
        Long uid = getLoginUserId();
        return success(appCouponService.getNotList(uid,shopId,page,pagesize));
    }

    /**
     * 领取优惠券
     */
    @PreAuthenticated
    @PostMapping("/receive")
    @Parameters({
            @Parameter(name = "id", description = "优惠券ID",
                    example = "1"),
            @Parameter(name = "code", description = "优惠券兑换码",
                    example = "1")
    })
    @Operation(summary = "获取未被领取优惠券")
    public CommonResult<Boolean> receive(@RequestBody AppReceVO appReceVO){
        Long uid = getLoginUserId();
        appCouponService.receive(uid,appReceVO.getId(),appReceVO.getCode());
        return success(true);
    }


}


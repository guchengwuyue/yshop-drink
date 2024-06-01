/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.yshop.module.member.controller.app.address;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.yshop.framework.common.pojo.CommonResult;
import co.yixiang.yshop.framework.common.util.yshop.LocationUtils;
import co.yixiang.yshop.framework.ip.core.Area;
import co.yixiang.yshop.framework.ip.core.utils.AreaUtils;
import co.yixiang.yshop.framework.security.core.annotations.PreAuthenticated;
import co.yixiang.yshop.module.member.controller.app.address.param.AppAddressParam;
import co.yixiang.yshop.module.member.controller.app.address.vo.AppUserAddressLocationVo;
import co.yixiang.yshop.module.member.controller.app.address.vo.AppUserAddressQueryVo;
import co.yixiang.yshop.module.member.controller.app.address.vo.AreaNodeRespVO;
import co.yixiang.yshop.module.member.convert.useraddress.AreaConvert;
import co.yixiang.yshop.module.member.service.useraddress.AppUserAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static co.yixiang.yshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static co.yixiang.yshop.framework.common.pojo.CommonResult.success;
import static co.yixiang.yshop.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static co.yixiang.yshop.module.member.enums.ErrorCodeConstants.USER_ADDRESS_PARAM_NOT_EXISTS;

/**
 * <p>
 * 用户地前端控制器
 * </p>
 *
 * @author hupeng
 * @since 2023-6-28
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "用户 APP - 用户地址")
@RequestMapping("/address")
public class AppUserAddressController {

    private final AppUserAddressService appUserAddressService;


    @GetMapping("/city_list")
    @Operation(summary = "城市列表")
    public CommonResult<List<AreaNodeRespVO>> getTest() {
        Area area = AreaUtils.getArea(Area.ID_CHINA);
        Assert.notNull(area, "获取不到中国");
        return success(AreaConvert.INSTANCE.convertList(area.getChildren()));
    }

    /**
    * 添加或修改地址
    */
    @PreAuthenticated
    @PostMapping("/addAndEdit")
    @Operation(summary = "添加或修改地址")
    public CommonResult<Long> addYxUserAddress(@RequestBody @Valid AppAddressParam param){
        Long uid = getLoginUserId();
        Long id = appUserAddressService.addAndEdit(uid,param);
        return success(id);
    }

    /**
     * 设置默认地址
     */
    @PreAuthenticated
    @PostMapping("/default/set/{id}")
    @Parameter(name = "id", description = "地址id", required = true)
    @Operation(summary = "设置默认地址")
    public CommonResult<Boolean> setDefault(@PathVariable String id){
        Long uid = getLoginUserId();
        appUserAddressService.setDefault(uid,id);
        return success(true);
    }



    /**
    * 删除用户地址
    */
    @PreAuthenticated
    @PostMapping("/del/{id}")
    @Operation(summary = "删除用户地址")
    public CommonResult<Boolean> deleteYxUserAddress(@PathVariable String id){
        if(StrUtil.isBlank(id) || !NumberUtil.isNumber(id)){
            throw exception(USER_ADDRESS_PARAM_NOT_EXISTS);
        }
        appUserAddressService.removeById(id);
        return success(true);
    }


    /**
     * 用户地址列表
     */
    @PreAuthenticated
    @GetMapping("/list")
    @Operation(summary = "用户地址列表")
    public CommonResult<List<AppUserAddressQueryVo>> getYxUserAddressPageList(@RequestParam(value = "page",defaultValue = "1") int page,
                                                                           @RequestParam(value = "limit",defaultValue = "10") int limit){
        Long uid = getLoginUserId();
        return success(appUserAddressService.getList(uid,page,limit));
    }

    /**
     * 用户地址列表
     */
    @PostMapping("/getDistanceFromLocation")
    @Operation(summary = "用户地址列表")
    public CommonResult<Double> getDistanceFromLocation( @RequestBody AppUserAddressLocationVo addressLocation){
        return success(LocationUtils.getDistance(addressLocation.getLat(),addressLocation.getLng(),
                addressLocation.getLat2(),addressLocation.getLng2()));
    }





}


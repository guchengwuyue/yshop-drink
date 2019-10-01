package com.ruoyi.app.modular.distribution.web.controller;

import com.ruoyi.app.modular.distribution.entity.StoreSubstitute;
import com.ruoyi.app.modular.distribution.service.StoreSubstituteService;
import com.ruoyi.app.distribution.web.param.StoreSubstituteQueryParam;
import com.ruoyi.app.distribution.web.vo.StoreSubstituteQueryVo;
import com.ruoyi.app.common.web.controller.BaseController;
import com.ruoyi.app.common.api.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.ruoyi.app.common.web.vo.Paging;
import com.ruoyi.app.common.web.param.IdParam;

/**
 * <p>
 * 代发货表 前端控制器
 * </p>
 *
 * @author hupeng
 * @since 2019-09-30
 */
@Slf4j
@RestController
@RequestMapping("/storeSubstitute")
@Api("代发货表 API")
public class StoreSubstituteController extends BaseController {

    @Autowired
    private StoreSubstituteService storeSubstituteService;

    /**
    * 添加代发货表
    */
    @PostMapping("/add")
    @ApiOperation(value = "添加StoreSubstitute对象",notes = "添加代发货表",response = ApiResult.class)
    public ApiResult<Boolean> addStoreSubstitute(@Valid @RequestBody StoreSubstitute storeSubstitute) throws Exception{
        boolean flag = storeSubstituteService.save(storeSubstitute);
        return ApiResult.result(flag);
    }

    /**
    * 修改代发货表
    */
    @PostMapping("/update")
    @ApiOperation(value = "修改StoreSubstitute对象",notes = "修改代发货表",response = ApiResult.class)
    public ApiResult<Boolean> updateStoreSubstitute(@Valid @RequestBody StoreSubstitute storeSubstitute) throws Exception{
        boolean flag = storeSubstituteService.updateById(storeSubstitute);
        return ApiResult.result(flag);
    }

    /**
    * 删除代发货表
    */
    @PostMapping("/delete")
    @ApiOperation(value = "删除StoreSubstitute对象",notes = "删除代发货表",response = ApiResult.class)
    public ApiResult<Boolean> deleteStoreSubstitute(@Valid @RequestBody IdParam idParam) throws Exception{
        boolean flag = storeSubstituteService.removeById(idParam.getId());
        return ApiResult.result(flag);
    }

    /**
    * 获取代发货表
    */
    @PostMapping("/info")
    @ApiOperation(value = "获取StoreSubstitute对象详情",notes = "查看代发货表",response = StoreSubstituteQueryVo.class)
    public ApiResult<StoreSubstituteQueryVo> getStoreSubstitute(@Valid @RequestBody IdParam idParam) throws Exception{
        StoreSubstituteQueryVo storeSubstituteQueryVo = storeSubstituteService.getStoreSubstituteById(idParam.getId());
        return ApiResult.ok(storeSubstituteQueryVo);
    }

    /**
     * 代发货表分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取StoreSubstitute分页列表",notes = "代发货表分页列表",response = StoreSubstituteQueryVo.class)
    public ApiResult<Paging<StoreSubstituteQueryVo>> getStoreSubstitutePageList(@Valid @RequestBody(required = false) StoreSubstituteQueryParam storeSubstituteQueryParam) throws Exception{
        Paging<StoreSubstituteQueryVo> paging = storeSubstituteService.getStoreSubstitutePageList(storeSubstituteQueryParam);
        return ApiResult.ok(paging);
    }

}


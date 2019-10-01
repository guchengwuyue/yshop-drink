package com.ruoyi.plus.system.web.controller;

import com.ruoyi.plus.system.entity.SysUser;
import com.ruoyi.plus.system.service.SysUserService;
import com.ruoyi.plus.system.web.param.SysUserQueryParam;
import com.ruoyi.plus.system.web.vo.SysUserQueryVo;
import com.ruoyi.plus.common.web.controller.BaseController;
import com.ruoyi.plus.common.api.ApiResult;
    import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.ruoyi.plus.common.web.vo.Paging;
import com.ruoyi.plus.common.web.param.IdParam;

/**
 * <p>
 * SystemUser 前端控制器
 * </p>
 *
 * @author geekidea
 * @since 2019-08-26
 */
@Slf4j
@RestController
@RequestMapping("/sysUser")
@Api("SystemUser API")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    /**
    * Save SystemUser
    */
    @PostMapping("/add")
    @ApiOperation(value = "Save SysUser",notes = "Save SystemUser",response = ApiResult.class)
    public ApiResult<Boolean> addSysUser(@Valid @RequestBody SysUser sysUser) throws Exception{
        boolean flag = sysUserService.save(sysUser);
        return ApiResult.result(flag);
    }

    /**
    * Update SystemUser
    */
    @PostMapping("/update")
    @ApiOperation(value = "Update SysUser",notes = "Update SystemUser",response = ApiResult.class)
    public ApiResult<Boolean> updateSysUser(@Valid @RequestBody SysUser sysUser) throws Exception{
        boolean flag = sysUserService.updateById(sysUser);
        return ApiResult.result(flag);
    }

    /**
    * Delete SystemUser
    */
    @PostMapping("/delete")
    @ApiOperation(value = "Delete SysUser",notes = "Delete SystemUser",response = ApiResult.class)
    public ApiResult<Boolean> deleteSysUser(@Valid @RequestBody IdParam idParam) throws Exception{
        boolean flag = sysUserService.removeById(idParam.getId());
        return ApiResult.result(flag);
    }

    /**
    * Get SystemUser
    */
    @PostMapping("/info")
    @ApiOperation(value = "Get SysUser Detail",notes = "SystemUser Info",response = SysUserQueryVo.class)
    public ApiResult<SysUserQueryVo> getSysUser(@Valid @RequestBody IdParam idParam) throws Exception{
        SysUserQueryVo sysUserQueryVo = sysUserService.getSysUserById(idParam.getId());
        return ApiResult.ok(sysUserQueryVo);
    }

    /**
     * SystemUser Pagination
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "Get SysUserPagination",notes = "SystemUser Pagination",response = SysUserQueryVo.class)
    public ApiResult<Paging<SysUserQueryVo>> getSysUserPageList(@Valid @RequestBody(required = false) SysUserQueryParam sysUserQueryParam) throws Exception{
        Paging<SysUserQueryVo> paging = sysUserService.getSysUserPageList(sysUserQueryParam);
        return ApiResult.ok(paging);
    }

}


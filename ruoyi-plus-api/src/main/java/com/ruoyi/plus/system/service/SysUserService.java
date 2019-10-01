package com.ruoyi.plus.system.service;

import com.ruoyi.plus.system.entity.SysUser;
import com.ruoyi.plus.common.service.BaseService;
import com.ruoyi.plus.system.web.param.SysUserQueryParam;
import com.ruoyi.plus.system.web.vo.SysUserQueryVo;
import com.ruoyi.plus.common.web.vo.Paging;

import java.io.Serializable;

/**
 * <p>
 * SystemUser 服务类
 * </p>
 *
 * @author geekidea
 * @since 2019-08-26
 */
public interface SysUserService extends BaseService<SysUser> {

    /**
     * 根据ID获取查询对象
     * @param id
     * @return
     */
    SysUserQueryVo getSysUserById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     * @param sysUserQueryParam
     * @return
     */
    Paging<SysUserQueryVo> getSysUserPageList(SysUserQueryParam sysUserQueryParam) throws Exception;

}

package com.ruoyi.plus.system.service;

import com.ruoyi.plus.system.entity.SysLog;
import com.ruoyi.plus.common.service.BaseService;
import com.ruoyi.plus.system.web.param.SysLogQueryParam;
import com.ruoyi.plus.system.web.vo.SysLogQueryVo;
import com.ruoyi.plus.common.web.vo.Paging;

import java.io.Serializable;

/**
 * <p>
 * 系统日志 服务类
 * </p>
 *
 * @author geekidea
 * @since 2019-08-04
 */
public interface SysLogService extends BaseService<SysLog> {

    /**
     * 根据ID获取查询对象
     * @param id
     * @return
     */
    SysLogQueryVo getSysLogById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     * @param sysLogQueryParam
     * @return
     */
    Paging<SysLogQueryVo> getSysLogPageList(SysLogQueryParam sysLogQueryParam) throws Exception;

}

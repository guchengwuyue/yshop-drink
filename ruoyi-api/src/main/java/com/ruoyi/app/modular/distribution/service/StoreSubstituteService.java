package com.ruoyi.app.modular.distribution.service;

import com.ruoyi.app.modular.distribution.entity.StoreSubstitute;
import com.ruoyi.app.common.service.BaseService;
import com.ruoyi.app.distribution.web.param.StoreSubstituteQueryParam;
import com.ruoyi.app.distribution.web.vo.StoreSubstituteQueryVo;
import com.ruoyi.app.common.web.vo.Paging;

import java.io.Serializable;

/**
 * <p>
 * 代发货表 服务类
 * </p>
 *
 * @author hupeng
 * @since 2019-09-30
 */
public interface StoreSubstituteService extends BaseService<StoreSubstitute> {

    /**
     * 根据ID获取查询对象
     * @param id
     * @return
     */
    StoreSubstituteQueryVo getStoreSubstituteById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     * @param storeSubstituteQueryParam
     * @return
     */
    Paging<StoreSubstituteQueryVo> getStoreSubstitutePageList(StoreSubstituteQueryParam storeSubstituteQueryParam) throws Exception;

}

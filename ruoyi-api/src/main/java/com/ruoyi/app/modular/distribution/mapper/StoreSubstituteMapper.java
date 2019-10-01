package com.ruoyi.app.modular.distribution.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.app.modular.distribution.entity.StoreSubstitute;
import com.ruoyi.app.distribution.web.param.StoreSubstituteQueryParam;
import com.ruoyi.app.distribution.web.vo.StoreSubstituteQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 * 代发货表 Mapper 接口
 * </p>
 *
 * @author hupeng
 * @since 2019-09-30
 */
@Repository
public interface StoreSubstituteMapper extends BaseMapper<StoreSubstitute> {

    /**
     * 根据ID获取查询对象
     * @param id
     * @return
     */
    StoreSubstituteQueryVo getStoreSubstituteById(Serializable id);

    /**
     * 获取分页对象
     * @param page
     * @param storeSubstituteQueryParam
     * @return
     */
    IPage<StoreSubstituteQueryVo> getStoreSubstitutePageList(@Param("page") Page page, @Param("param") StoreSubstituteQueryParam storeSubstituteQueryParam);

}

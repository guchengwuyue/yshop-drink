package com.ruoyi.app.modular.distribution.service.impl;

import com.ruoyi.app.modular.distribution.entity.StoreSubstitute;
import com.ruoyi.app.modular.distribution.mapper.StoreSubstituteMapper;
import com.ruoyi.app.modular.distribution.service.StoreSubstituteService;
import com.ruoyi.app.distribution.web.param.StoreSubstituteQueryParam;
import com.ruoyi.app.distribution.web.vo.StoreSubstituteQueryVo;
import com.ruoyi.app.common.service.impl.BaseServiceImpl;
import com.ruoyi.app.common.web.vo.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.io.Serializable;


/**
 * <p>
 * 代发货表 服务实现类
 * </p>
 *
 * @author hupeng
 * @since 2019-09-30
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class StoreSubstituteServiceImpl extends BaseServiceImpl<StoreSubstituteMapper, StoreSubstitute> implements StoreSubstituteService {

    @Autowired
    private StoreSubstituteMapper storeSubstituteMapper;

    @Override
    public StoreSubstituteQueryVo getStoreSubstituteById(Serializable id) throws Exception{
        return storeSubstituteMapper.getStoreSubstituteById(id);
    }

    @Override
    public Paging<StoreSubstituteQueryVo> getStoreSubstitutePageList(StoreSubstituteQueryParam storeSubstituteQueryParam) throws Exception{
        Page page = setPageParam(storeSubstituteQueryParam,OrderItem.desc("create_time"));
        IPage<StoreSubstituteQueryVo> iPage = storeSubstituteMapper.getStoreSubstitutePageList(page,storeSubstituteQueryParam);
        return new Paging(iPage);
    }

}

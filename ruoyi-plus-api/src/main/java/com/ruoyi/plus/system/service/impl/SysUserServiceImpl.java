package com.ruoyi.plus.system.service.impl;

import com.ruoyi.plus.system.entity.SysUser;
import com.ruoyi.plus.system.mapper.SysUserMapper;
import com.ruoyi.plus.system.service.SysUserService;
import com.ruoyi.plus.system.web.param.SysUserQueryParam;
import com.ruoyi.plus.system.web.vo.SysUserQueryVo;
import com.ruoyi.plus.common.service.impl.BaseServiceImpl;
import com.ruoyi.plus.common.web.vo.Paging;
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
 * SystemUser 服务实现类
 * </p>
 *
 * @author geekidea
 * @since 2019-08-26
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUserQueryVo getSysUserById(Serializable id) throws Exception{
        return sysUserMapper.getSysUserById(id);
    }

    @Override
    public Paging<SysUserQueryVo> getSysUserPageList(SysUserQueryParam sysUserQueryParam) throws Exception{
        Page page = setPageParam(sysUserQueryParam,OrderItem.desc("create_time"));
        IPage<SysUserQueryVo> iPage = sysUserMapper.getSysUserPageList(page,sysUserQueryParam);
        return new Paging(iPage);
    }

}

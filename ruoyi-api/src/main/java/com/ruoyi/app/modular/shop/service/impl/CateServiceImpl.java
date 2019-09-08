package com.ruoyi.app.modular.shop.service.impl;


//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.common.persistence.dao.StoreGoodsCateMapper;
import com.ruoyi.app.common.persistence.model.StoreGoodsCate;
import com.ruoyi.app.common.utils.TreeUtil;
import com.ruoyi.app.modular.shop.service.ICateService;
import com.ruoyi.app.modular.shop.service.dto.CateDTO;
import com.ruoyi.app.modular.shop.service.mapper.CateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CateServiceImpl extends ServiceImpl<StoreGoodsCateMapper, StoreGoodsCate>  implements ICateService {

    private final CateMapper cateMapper;

    @Override
    public List<CateDTO> getList() {
        QueryWrapper<StoreGoodsCate> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted",0);
        List<CateDTO> list = cateMapper.toDto(baseMapper.selectList(wrapper));
        return TreeUtil.list2TreeConverter(list,0);
    }
}

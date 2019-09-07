package com.ruoyi.app.modular.shop.service.impl;


//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.common.persistence.dao.StoreAdsMapper;
import com.ruoyi.app.common.persistence.model.StoreAds;
import com.ruoyi.app.modular.shop.service.IAdService;

import com.ruoyi.app.modular.shop.service.dto.AdsDTO;
import com.ruoyi.app.modular.shop.service.mapper.AdsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdServiceImpl extends ServiceImpl<StoreAdsMapper,StoreAds>  implements IAdService {

    private final AdsMapper adsMapper;

    @Override
    public List<AdsDTO> getList() {
        QueryWrapper<StoreAds> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        return adsMapper.toDto(baseMapper.selectList(wrapper));
    }
}

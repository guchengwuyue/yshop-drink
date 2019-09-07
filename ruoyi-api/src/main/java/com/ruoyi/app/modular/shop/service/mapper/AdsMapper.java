package com.ruoyi.app.modular.shop.service.mapper;


import com.ruoyi.app.common.persistence.model.StoreAds;
import com.ruoyi.app.modular.mapper.EntityMapper;
import com.ruoyi.app.modular.shop.service.dto.AdsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author hupeng
* @date 2019-09-07
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdsMapper extends EntityMapper<AdsDTO, StoreAds> {

}
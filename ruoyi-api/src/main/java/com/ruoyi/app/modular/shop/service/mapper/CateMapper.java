package com.ruoyi.app.modular.shop.service.mapper;


import com.ruoyi.app.common.persistence.model.StoreGoodsCate;
import com.ruoyi.app.modular.mapper.EntityMapper;
import com.ruoyi.app.modular.shop.service.dto.CateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author hupeng
* @date 2019-09-07
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CateMapper extends EntityMapper<CateDTO, StoreGoodsCate> {

}
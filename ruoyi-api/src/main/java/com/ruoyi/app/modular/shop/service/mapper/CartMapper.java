package com.ruoyi.app.modular.shop.service.mapper;



import com.ruoyi.app.common.persistence.model.StoreCart;
import com.ruoyi.app.modular.mapper.EntityMapper;
import com.ruoyi.app.modular.shop.service.dto.CartDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author hupeng
* @date 2019-09-10
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartMapper extends EntityMapper<CartDTO, StoreCart> {

}
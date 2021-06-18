package co.yixiang.app.modular.shop.service.mapper;


import co.yixiang.app.common.persistence.model.StoreGoods;
import co.yixiang.app.modular.shop.service.dto.GoodsDTO;
import co.yixiang.app.modular.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author hupeng
* @date 2019-09-07
*/
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GoodsMapper extends EntityMapper<GoodsDTO, StoreGoods> {

}
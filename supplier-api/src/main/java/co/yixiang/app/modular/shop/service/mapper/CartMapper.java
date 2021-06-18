package co.yixiang.app.modular.shop.service.mapper;



import co.yixiang.app.common.persistence.model.StoreCart;
import co.yixiang.app.modular.shop.service.dto.CartAttrDTO;
import co.yixiang.app.common.persistence.model.StoreCart;
import co.yixiang.app.modular.mapper.EntityMapper;
import co.yixiang.app.modular.shop.service.dto.CartAttrDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author hupeng
* @date 2019-09-10
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartMapper extends EntityMapper<CartAttrDTO, StoreCart> {

}
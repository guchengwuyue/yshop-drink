package co.yixiang.app.modular.member.service.mapper;



import co.yixiang.app.common.persistence.model.StoreMemberAddress;
import co.yixiang.app.modular.mapper.EntityMapper;
import co.yixiang.app.modular.member.service.dto.AddressDTO;
import co.yixiang.app.common.persistence.model.StoreMemberAddress;
import co.yixiang.app.modular.mapper.EntityMapper;
import co.yixiang.app.modular.member.service.dto.AddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author hupeng
* @date 2019-06-05
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper extends EntityMapper<AddressDTO, StoreMemberAddress> {

}
package co.yixiang.yshop.module.system.convert.ip;

import co.yixiang.yshop.framework.ip.core.Area;
import co.yixiang.yshop.module.system.controller.admin.ip.vo.AreaNodeRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AreaConvert {

    AreaConvert INSTANCE = Mappers.getMapper(AreaConvert.class);

    List<AreaNodeRespVO> convertList(List<Area> list);

}

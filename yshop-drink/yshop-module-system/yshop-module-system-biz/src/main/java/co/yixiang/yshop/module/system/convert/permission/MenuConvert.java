package co.yixiang.yshop.module.system.convert.permission;

import co.yixiang.yshop.module.system.controller.admin.permission.vo.menu.MenuCreateReqVO;
import co.yixiang.yshop.module.system.controller.admin.permission.vo.menu.MenuRespVO;
import co.yixiang.yshop.module.system.controller.admin.permission.vo.menu.MenuSimpleRespVO;
import co.yixiang.yshop.module.system.controller.admin.permission.vo.menu.MenuUpdateReqVO;
import co.yixiang.yshop.module.system.dal.dataobject.permission.MenuDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MenuConvert {

    MenuConvert INSTANCE = Mappers.getMapper(MenuConvert.class);

    List<MenuRespVO> convertList(List<MenuDO> list);

    MenuDO convert(MenuCreateReqVO bean);

    MenuDO convert(MenuUpdateReqVO bean);

    MenuRespVO convert(MenuDO bean);

    List<MenuSimpleRespVO> convertList02(List<MenuDO> list);

}

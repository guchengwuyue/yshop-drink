package co.yixiang.yshop.module.member.convert.useraddress;

import java.util.*;

import co.yixiang.yshop.framework.common.pojo.PageResult;

import co.yixiang.yshop.module.member.controller.app.address.vo.AppUserAddressQueryVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import co.yixiang.yshop.module.member.controller.admin.useraddress.vo.*;
import co.yixiang.yshop.module.member.dal.dataobject.useraddress.UserAddressDO;

/**
 * 用户地址 Convert
 *
 * @author yshop
 */
@Mapper
public interface UserAddressConvert {

    UserAddressConvert INSTANCE = Mappers.getMapper(UserAddressConvert.class);

    UserAddressDO convert(UserAddressCreateReqVO bean);

    UserAddressDO convert(UserAddressUpdateReqVO bean);

    UserAddressRespVO convert(UserAddressDO bean);

    List<UserAddressRespVO> convertList(List<UserAddressDO> list);

    List<AppUserAddressQueryVo> convertList02(List<UserAddressDO> list);

    PageResult<UserAddressRespVO> convertPage(PageResult<UserAddressDO> page);

}

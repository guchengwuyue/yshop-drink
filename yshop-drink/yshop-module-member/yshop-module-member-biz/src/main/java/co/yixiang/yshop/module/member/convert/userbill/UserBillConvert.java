package co.yixiang.yshop.module.member.convert.userbill;

import java.util.*;

import co.yixiang.yshop.framework.common.pojo.PageResult;

import co.yixiang.yshop.module.member.controller.app.user.vo.AppUserBillVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import co.yixiang.yshop.module.member.controller.admin.userbill.vo.*;
import co.yixiang.yshop.module.member.dal.dataobject.userbill.UserBillDO;

/**
 * 用户账单 Convert
 *
 * @author yshop
 */
@Mapper
public interface UserBillConvert {

    UserBillConvert INSTANCE = Mappers.getMapper(UserBillConvert.class);

    UserBillDO convert(UserBillCreateReqVO bean);

    UserBillDO convert(UserBillUpdateReqVO bean);

    UserBillRespVO convert(UserBillDO bean);

    List<UserBillRespVO> convertList(List<UserBillDO> list);

    List<AppUserBillVO> convertList02(List<UserBillDO> list);

    PageResult<UserBillRespVO> convertPage(PageResult<UserBillDO> page);


}

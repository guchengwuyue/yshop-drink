package co.yixiang.yshop.module.member.convert.user;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.member.api.user.dto.MemberUserRespDTO;
import co.yixiang.yshop.module.member.controller.admin.user.vo.UserCreateReqVO;
import co.yixiang.yshop.module.member.controller.admin.user.vo.UserRespVO;
import co.yixiang.yshop.module.member.controller.admin.user.vo.UserUpdateReqVO;
import co.yixiang.yshop.module.member.controller.app.user.vo.AppUserInfoRespVO;
import co.yixiang.yshop.module.member.controller.app.user.vo.AppUserQueryVo;
import co.yixiang.yshop.module.member.dal.dataobject.user.MemberUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    AppUserInfoRespVO convert(MemberUserDO bean);

    MemberUserRespDTO convert2(MemberUserDO bean);

    AppUserQueryVo convert3(MemberUserDO bean);

    UserRespVO convert4(MemberUserDO bean);

    List<MemberUserRespDTO> convertList2(List<MemberUserDO> list);

    MemberUserDO convert(UserCreateReqVO bean);

    MemberUserDO convert(UserUpdateReqVO bean);

   UserRespVO convert(MemberUserDO bean,Boolean bool);

    List<UserRespVO> convertList(List<MemberUserDO> list);

    PageResult<UserRespVO> convertPage(PageResult<MemberUserDO> page);

}

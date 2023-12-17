package co.yixiang.yshop.module.system.convert.logger;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.system.api.logger.dto.LoginLogCreateReqDTO;
import co.yixiang.yshop.module.system.controller.admin.logger.vo.loginlog.LoginLogExcelVO;
import co.yixiang.yshop.module.system.controller.admin.logger.vo.loginlog.LoginLogRespVO;
import co.yixiang.yshop.module.system.dal.dataobject.logger.LoginLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LoginLogConvert {

    LoginLogConvert INSTANCE = Mappers.getMapper(LoginLogConvert.class);

    PageResult<LoginLogRespVO> convertPage(PageResult<LoginLogDO> page);

    List<LoginLogExcelVO> convertList(List<LoginLogDO> list);

    LoginLogDO convert(LoginLogCreateReqDTO bean);

}

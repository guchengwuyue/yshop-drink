package co.yixiang.yshop.module.infra.convert.logger;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.infra.api.logger.dto.ApiErrorLogCreateReqDTO;
import co.yixiang.yshop.module.infra.controller.admin.logger.vo.apierrorlog.ApiErrorLogExcelVO;
import co.yixiang.yshop.module.infra.controller.admin.logger.vo.apierrorlog.ApiErrorLogRespVO;
import co.yixiang.yshop.module.infra.dal.dataobject.logger.ApiErrorLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * API 错误日志 Convert
 *
 * @author yshop
 */
@Mapper
public interface ApiErrorLogConvert {

    ApiErrorLogConvert INSTANCE = Mappers.getMapper(ApiErrorLogConvert.class);

    ApiErrorLogRespVO convert(ApiErrorLogDO bean);

    PageResult<ApiErrorLogRespVO> convertPage(PageResult<ApiErrorLogDO> page);

    List<ApiErrorLogExcelVO> convertList02(List<ApiErrorLogDO> list);

    ApiErrorLogDO convert(ApiErrorLogCreateReqDTO bean);

}

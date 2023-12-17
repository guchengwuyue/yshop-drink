package co.yixiang.yshop.module.infra.convert.logger;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.infra.api.logger.dto.ApiAccessLogCreateReqDTO;
import co.yixiang.yshop.module.infra.controller.admin.logger.vo.apiaccesslog.ApiAccessLogExcelVO;
import co.yixiang.yshop.module.infra.controller.admin.logger.vo.apiaccesslog.ApiAccessLogRespVO;
import co.yixiang.yshop.module.infra.dal.dataobject.logger.ApiAccessLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * API 访问日志 Convert
 *
 * @author yshop
 */
@Mapper
public interface ApiAccessLogConvert {

    ApiAccessLogConvert INSTANCE = Mappers.getMapper(ApiAccessLogConvert.class);

    ApiAccessLogRespVO convert(ApiAccessLogDO bean);

    List<ApiAccessLogRespVO> convertList(List<ApiAccessLogDO> list);

    PageResult<ApiAccessLogRespVO> convertPage(PageResult<ApiAccessLogDO> page);

    List<ApiAccessLogExcelVO> convertList02(List<ApiAccessLogDO> list);

    ApiAccessLogDO convert(ApiAccessLogCreateReqDTO bean);

}

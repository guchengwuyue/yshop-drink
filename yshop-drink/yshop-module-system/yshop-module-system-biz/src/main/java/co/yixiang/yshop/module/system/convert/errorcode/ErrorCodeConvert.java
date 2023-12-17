package co.yixiang.yshop.module.system.convert.errorcode;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.system.api.errorcode.dto.ErrorCodeAutoGenerateReqDTO;
import co.yixiang.yshop.module.system.api.errorcode.dto.ErrorCodeRespDTO;
import co.yixiang.yshop.module.system.controller.admin.errorcode.vo.ErrorCodeCreateReqVO;
import co.yixiang.yshop.module.system.controller.admin.errorcode.vo.ErrorCodeExcelVO;
import co.yixiang.yshop.module.system.controller.admin.errorcode.vo.ErrorCodeRespVO;
import co.yixiang.yshop.module.system.controller.admin.errorcode.vo.ErrorCodeUpdateReqVO;
import co.yixiang.yshop.module.system.dal.dataobject.errorcode.ErrorCodeDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 错误码 Convert
 *
 * @author yshop
 */
@Mapper
public interface ErrorCodeConvert {

    ErrorCodeConvert INSTANCE = Mappers.getMapper(ErrorCodeConvert.class);

    ErrorCodeDO convert(ErrorCodeCreateReqVO bean);

    ErrorCodeDO convert(ErrorCodeUpdateReqVO bean);

    ErrorCodeRespVO convert(ErrorCodeDO bean);

    List<ErrorCodeRespVO> convertList(List<ErrorCodeDO> list);

    PageResult<ErrorCodeRespVO> convertPage(PageResult<ErrorCodeDO> page);

    List<ErrorCodeExcelVO> convertList02(List<ErrorCodeDO> list);

    ErrorCodeDO convert(ErrorCodeAutoGenerateReqDTO bean);

    List<ErrorCodeRespDTO> convertList03(List<ErrorCodeDO> list);

}

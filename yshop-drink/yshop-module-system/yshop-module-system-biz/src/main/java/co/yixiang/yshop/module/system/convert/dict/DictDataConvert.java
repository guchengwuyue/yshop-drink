package co.yixiang.yshop.module.system.convert.dict;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.system.api.dict.dto.DictDataRespDTO;
import co.yixiang.yshop.module.system.controller.admin.dict.vo.data.*;
import co.yixiang.yshop.module.system.dal.dataobject.dict.DictDataDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper
public interface DictDataConvert {

    DictDataConvert INSTANCE = Mappers.getMapper(DictDataConvert.class);

    List<DictDataSimpleRespVO> convertList(List<DictDataDO> list);

    DictDataRespVO convert(DictDataDO bean);

    PageResult<DictDataRespVO> convertPage(PageResult<DictDataDO> page);

    DictDataDO convert(DictDataUpdateReqVO bean);

    DictDataDO convert(DictDataCreateReqVO bean);

    List<DictDataExcelVO> convertList02(List<DictDataDO> bean);

    DictDataRespDTO convert02(DictDataDO bean);

}

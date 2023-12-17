package co.yixiang.yshop.module.infra.convert.file;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.infra.controller.admin.file.vo.file.FileRespVO;
import co.yixiang.yshop.module.infra.dal.dataobject.file.FileDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FileConvert {

    FileConvert INSTANCE = Mappers.getMapper(FileConvert.class);

    FileRespVO convert(FileDO bean);

    PageResult<FileRespVO> convertPage(PageResult<FileDO> page);

}

package co.yixiang.yshop.module.system.convert.dept;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.system.controller.admin.dept.vo.post.*;
import co.yixiang.yshop.module.system.dal.dataobject.dept.PostDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostConvert {

    PostConvert INSTANCE = Mappers.getMapper(PostConvert.class);

    List<PostSimpleRespVO> convertList02(List<PostDO> list);

    PageResult<PostRespVO> convertPage(PageResult<PostDO> page);

    PostRespVO convert(PostDO id);

    PostDO convert(PostCreateReqVO bean);

    PostDO convert(PostUpdateReqVO reqVO);

    List<PostExcelVO> convertList03(List<PostDO> list);

}

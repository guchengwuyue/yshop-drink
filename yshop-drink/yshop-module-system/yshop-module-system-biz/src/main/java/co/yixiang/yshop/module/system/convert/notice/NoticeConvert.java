package co.yixiang.yshop.module.system.convert.notice;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.system.controller.admin.notice.vo.NoticeCreateReqVO;
import co.yixiang.yshop.module.system.controller.admin.notice.vo.NoticeRespVO;
import co.yixiang.yshop.module.system.controller.admin.notice.vo.NoticeUpdateReqVO;
import co.yixiang.yshop.module.system.dal.dataobject.notice.NoticeDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NoticeConvert {

    NoticeConvert INSTANCE = Mappers.getMapper(NoticeConvert.class);

    PageResult<NoticeRespVO> convertPage(PageResult<NoticeDO> page);

    NoticeRespVO convert(NoticeDO bean);

    NoticeDO convert(NoticeUpdateReqVO bean);

    NoticeDO convert(NoticeCreateReqVO bean);

}

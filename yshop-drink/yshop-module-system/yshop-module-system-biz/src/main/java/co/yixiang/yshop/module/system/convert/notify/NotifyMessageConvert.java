package co.yixiang.yshop.module.system.convert.notify;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.system.controller.admin.notify.vo.message.NotifyMessageRespVO;
import co.yixiang.yshop.module.system.dal.dataobject.notify.NotifyMessageDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 站内信 Convert
 *
 * @author xrcoder
 */
@Mapper
public interface NotifyMessageConvert {

    NotifyMessageConvert INSTANCE = Mappers.getMapper(NotifyMessageConvert.class);

    NotifyMessageRespVO convert(NotifyMessageDO bean);

    List<NotifyMessageRespVO> convertList(List<NotifyMessageDO> list);

    PageResult<NotifyMessageRespVO> convertPage(PageResult<NotifyMessageDO> page);


}

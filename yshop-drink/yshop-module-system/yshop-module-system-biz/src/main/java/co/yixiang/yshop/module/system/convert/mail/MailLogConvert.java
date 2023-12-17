package co.yixiang.yshop.module.system.convert.mail;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.system.controller.admin.mail.vo.log.MailLogRespVO;
import co.yixiang.yshop.module.system.dal.dataobject.mail.MailLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MailLogConvert {

    MailLogConvert INSTANCE = Mappers.getMapper(MailLogConvert.class);

    PageResult<MailLogRespVO> convertPage(PageResult<MailLogDO> pageResult);

    MailLogRespVO convert(MailLogDO bean);

}

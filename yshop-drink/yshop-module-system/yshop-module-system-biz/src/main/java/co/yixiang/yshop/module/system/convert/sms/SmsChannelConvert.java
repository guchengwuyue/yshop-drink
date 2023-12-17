package co.yixiang.yshop.module.system.convert.sms;

import co.yixiang.yshop.module.system.controller.admin.sms.vo.channel.SmsChannelCreateReqVO;
import co.yixiang.yshop.module.system.controller.admin.sms.vo.channel.SmsChannelRespVO;
import co.yixiang.yshop.module.system.controller.admin.sms.vo.channel.SmsChannelSimpleRespVO;
import co.yixiang.yshop.module.system.controller.admin.sms.vo.channel.SmsChannelUpdateReqVO;
import co.yixiang.yshop.module.system.dal.dataobject.sms.SmsChannelDO;
import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.framework.sms.core.property.SmsChannelProperties;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 短信渠道 Convert
 *
 * @author yshop
 */
@Mapper
public interface SmsChannelConvert {

    SmsChannelConvert INSTANCE = Mappers.getMapper(SmsChannelConvert.class);

    SmsChannelDO convert(SmsChannelCreateReqVO bean);

    SmsChannelDO convert(SmsChannelUpdateReqVO bean);

    SmsChannelRespVO convert(SmsChannelDO bean);

    List<SmsChannelRespVO> convertList(List<SmsChannelDO> list);

    PageResult<SmsChannelRespVO> convertPage(PageResult<SmsChannelDO> page);

    List<SmsChannelProperties> convertList02(List<SmsChannelDO> list);

    List<SmsChannelSimpleRespVO> convertList03(List<SmsChannelDO> list);

}

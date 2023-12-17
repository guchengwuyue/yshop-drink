package co.yixiang.yshop.module.system.convert.auth;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.system.controller.admin.oauth2.vo.client.OAuth2ClientCreateReqVO;
import co.yixiang.yshop.module.system.controller.admin.oauth2.vo.client.OAuth2ClientRespVO;
import co.yixiang.yshop.module.system.controller.admin.oauth2.vo.client.OAuth2ClientUpdateReqVO;
import co.yixiang.yshop.module.system.dal.dataobject.oauth2.OAuth2ClientDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * OAuth2 客户端 Convert
 *
 * @author yshop
 */
@Mapper
public interface OAuth2ClientConvert {

    OAuth2ClientConvert INSTANCE = Mappers.getMapper(OAuth2ClientConvert.class);

    OAuth2ClientDO convert(OAuth2ClientCreateReqVO bean);

    OAuth2ClientDO convert(OAuth2ClientUpdateReqVO bean);

    OAuth2ClientRespVO convert(OAuth2ClientDO bean);

    List<OAuth2ClientRespVO> convertList(List<OAuth2ClientDO> list);

    PageResult<OAuth2ClientRespVO> convertPage(PageResult<OAuth2ClientDO> page);

}

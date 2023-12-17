package co.yixiang.yshop.module.shop.convert.service;

import java.util.*;

import co.yixiang.yshop.framework.common.pojo.PageResult;

import co.yixiang.yshop.module.shop.controller.app.service.vo.AppServiceVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import co.yixiang.yshop.module.shop.controller.admin.service.vo.*;
import co.yixiang.yshop.module.shop.dal.dataobject.service.ServiceDO;

/**
 * 我的服务 Convert
 *
 * @author yshop
 */
@Mapper
public interface ServiceConvert {

    ServiceConvert INSTANCE = Mappers.getMapper(ServiceConvert.class);

    ServiceDO convert(ServiceCreateReqVO bean);

    ServiceDO convert(ServiceUpdateReqVO bean);

    ServiceRespVO convert(ServiceDO bean);

    List<ServiceRespVO> convertList(List<ServiceDO> list);

    List<AppServiceVO> convertList03(List<ServiceDO> list);

    AppServiceVO convert03(ServiceDO bean);

    PageResult<ServiceRespVO> convertPage(PageResult<ServiceDO> page);

    List<ServiceExcelVO> convertList02(List<ServiceDO> list);

}

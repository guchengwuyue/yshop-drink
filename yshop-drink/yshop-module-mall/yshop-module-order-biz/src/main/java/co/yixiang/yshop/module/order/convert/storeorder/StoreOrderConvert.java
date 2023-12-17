package co.yixiang.yshop.module.order.convert.storeorder;

import java.util.*;

import co.yixiang.yshop.framework.common.pojo.PageResult;

import co.yixiang.yshop.module.order.controller.app.order.vo.AppStoreOrderQueryVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import co.yixiang.yshop.module.order.controller.admin.storeorder.vo.*;
import co.yixiang.yshop.module.order.dal.dataobject.storeorder.StoreOrderDO;

/**
 * 订单 Convert
 *
 * @author yshop
 */
@Mapper
public interface StoreOrderConvert {

    StoreOrderConvert INSTANCE = Mappers.getMapper(StoreOrderConvert.class);

    StoreOrderDO convert(StoreOrderCreateReqVO bean);

    StoreOrderDO convert(StoreOrderUpdateReqVO bean);

    StoreOrderRespVO convert(StoreOrderDO bean);

    AppStoreOrderQueryVo convert1(StoreOrderDO bean);

    List<StoreOrderRespVO> convertList(List<StoreOrderDO> list);

    List<AppStoreOrderQueryVo> convertList01(List<StoreOrderDO> list);

    PageResult<StoreOrderRespVO> convertPage(PageResult<StoreOrderDO> page);

    List<StoreOrderExcelVO> convertList02(List<StoreOrderDO> list);

}

package co.yixiang.yshop.module.order.dal.mysql.ordernumber;

import co.yixiang.yshop.framework.mybatis.core.mapper.BaseMapperX;
import co.yixiang.yshop.module.order.dal.dataobject.ordernumber.OrderNumberDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单 Mapper
 *
 * @author yshop
 */
@Mapper
public interface OrderNumberMapper extends BaseMapperX<OrderNumberDO> {

}

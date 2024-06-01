package co.yixiang.yshop.module.score.dal.mysql.scoreorder;

import java.util.*;

import co.yixiang.yshop.framework.common.enums.OrderInfoEnum;
import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import co.yixiang.yshop.framework.mybatis.core.mapper.BaseMapperX;
import co.yixiang.yshop.module.score.dal.dataobject.scoreorder.ScoreOrderDO;
import co.yixiang.yshop.module.score.enums.OrderStatusEnum;
import org.apache.ibatis.annotations.Mapper;
import co.yixiang.yshop.module.score.controller.admin.scoreorder.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 积分商城订单 Mapper
 *
 * @author yshop
 */
@Mapper
public interface ScoreOrderMapper extends BaseMapperX<ScoreOrderDO> {

    default PageResult<ScoreOrderDO> selectPage(ScoreOrderPageReqVO reqVO) {
        LambdaQueryWrapperX<ScoreOrderDO> wrapper = new LambdaQueryWrapperX();
        wrapper.eqIfPresent(ScoreOrderDO::getOrderId, reqVO.getOrderId())
                .likeIfPresent(ScoreOrderDO::getCustomerName, reqVO.getCustomerName())
                .eqIfPresent(ScoreOrderDO::getCustomerPhone, reqVO.getCustomerPhone())
                .orderByDesc(ScoreOrderDO::getId);
        switch (OrderStatusEnum.toType(reqVO.getType())) {
            case STATUS__1:
                break;
            //待发货
            case STATUS_0:
                wrapper.eq(ScoreOrderDO::getHavePaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                        .eq(ScoreOrderDO::getHaveDelivered, OrderInfoEnum.STATUS_0.getValue())
                        .eq(ScoreOrderDO::getHaveReceived, OrderInfoEnum.STATUS_0.getValue());
                break;
            //待收货
            case STATUS_1:
                wrapper.eq(ScoreOrderDO::getHavePaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                        .eq(ScoreOrderDO::getHaveDelivered, OrderInfoEnum.STATUS_1.getValue())
                        .eq(ScoreOrderDO::getHaveReceived, OrderInfoEnum.STATUS_0.getValue());
                break;
            //已完成
            case STATUS_2:
                wrapper.eq(ScoreOrderDO::getHavePaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                        .eq(ScoreOrderDO::getHaveDelivered, OrderInfoEnum.STATUS_1.getValue())
                        .eq(ScoreOrderDO::getHaveReceived, OrderInfoEnum.STATUS_1.getValue());
                break;
            default:
        }
        return selectPage(reqVO, wrapper);
    }

    default List<ScoreOrderDO> selectList(ScoreOrderExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ScoreOrderDO>()
                .eqIfPresent(ScoreOrderDO::getProductId, reqVO.getProductId())
                .eqIfPresent(ScoreOrderDO::getNumber, reqVO.getNumber())
                .eqIfPresent(ScoreOrderDO::getScore, reqVO.getScore())
                .eqIfPresent(ScoreOrderDO::getTotalScore, reqVO.getTotalScore())
                .eqIfPresent(ScoreOrderDO::getIp, reqVO.getIp())
                .eqIfPresent(ScoreOrderDO::getExpressNumber, reqVO.getExpressNumber())
                .eqIfPresent(ScoreOrderDO::getExpressCompany, reqVO.getExpressCompany())
                .likeIfPresent(ScoreOrderDO::getCustomerName, reqVO.getCustomerName())
                .eqIfPresent(ScoreOrderDO::getCustomerPhone, reqVO.getCustomerPhone())
                .eqIfPresent(ScoreOrderDO::getCustomerAddress, reqVO.getCustomerAddress())
                .eqIfPresent(ScoreOrderDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ScoreOrderDO::getHavePaid, reqVO.getHavePaid())
                .eqIfPresent(ScoreOrderDO::getHaveDelivered, reqVO.getHaveDelivered())
                .eqIfPresent(ScoreOrderDO::getHaveReceived, reqVO.getHaveReceived())
                .betweenIfPresent(ScoreOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ScoreOrderDO::getId));
    }



}

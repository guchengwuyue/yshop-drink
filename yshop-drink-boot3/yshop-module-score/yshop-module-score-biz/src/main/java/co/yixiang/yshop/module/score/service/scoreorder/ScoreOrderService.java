package co.yixiang.yshop.module.score.service.scoreorder;

import java.util.*;
import jakarta.validation.*;
import co.yixiang.yshop.module.score.controller.admin.scoreorder.vo.*;
import co.yixiang.yshop.module.score.dal.dataobject.scoreorder.ScoreOrderDO;
import co.yixiang.yshop.framework.common.pojo.PageResult;

/**
 * 积分商城订单 Service 接口
 *
 * @author yshop
 */
public interface ScoreOrderService {

    /**
     * 创建积分商城订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrder(@Valid ScoreOrderCreateReqVO createReqVO);

    /**
     * 更新积分商城订单
     *
     * @param updateReqVO 更新信息
     */
    void updateOrder(@Valid ScoreOrderUpdateReqVO updateReqVO);

    /**
     * 删除积分商城订单
     *
     * @param id 编号
     */
    void deleteOrder(Long id);

    /**
     * 收货
     *
     * @param id 编号
     */
    void takeOrder(Long id);

    /**
     * 获得积分商城订单
     *
     * @param id 编号
     * @return 积分商城订单
     */
    ScoreOrderRespVO getOrder(Long id);

    /**
     * 获得积分商城订单列表
     *
     * @param ids 编号
     * @return 积分商城订单列表
     */
    List<ScoreOrderDO> getOrderList(Collection<Long> ids);

    /**
     * 获得积分商城订单分页
     *
     * @param pageReqVO 分页查询
     * @return 积分商城订单分页
     */
    PageResult<ScoreOrderRespVO> getOrderPage(ScoreOrderPageReqVO pageReqVO);

    /**
     * 获得积分商城订单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 积分商城订单列表
     */
    List<ScoreOrderDO> getOrderList(ScoreOrderExportReqVO exportReqVO);

}

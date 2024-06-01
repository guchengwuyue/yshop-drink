package co.yixiang.yshop.module.score.service.scoreorder;

import co.yixiang.yshop.module.score.controller.app.order.param.AppScoreOrderParam;
import co.yixiang.yshop.module.score.controller.app.order.vo.AppScoreOrderVO;
import co.yixiang.yshop.module.score.dal.dataobject.scoreorder.ScoreOrderDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 积分商城订单 Service 接口
 *
 * @author yshop
 */
public interface AppScoreOrderService extends IService<ScoreOrderDO> {

    /**
     * 提交
     * @param uid
     * @param appScoreOrderParam
     */
    void submit(Long uid,AppScoreOrderParam appScoreOrderParam);

    /**
     * 订单列表
     * @param uid 用户id
     * @param type
     * @param page page
     * @param limit limit
     * @return list
     */
    List<AppScoreOrderVO> orderList(Long uid, int type, int page, int limit);

    /**
     * 订单详情
     * @param uid 用户id
     * @param id
     */
    AppScoreOrderVO orderDetail(Long uid, Long id);

    /**
     * 收货
     * @param uid
     * @param id
     */
    void take(Long uid, Long id);


}

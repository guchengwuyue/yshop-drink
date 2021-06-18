package co.yixiang.app.modular.shop.service;




import co.yixiang.app.common.persistence.model.StoreOrder;
import co.yixiang.app.common.persistence.model.StorePointsMoneyLog;
import com.baomidou.mybatisplus.extension.service.IService;
import co.yixiang.app.modular.shop.service.dto.OrderDTO;
import co.yixiang.app.modular.shop.service.vo.OrderVo;


import java.util.List;
import java.util.Map;


public interface IOrderService extends IService<StoreOrder> {

    String addOrderOfOne(OrderVo orderVo,int userId);

    String addOrderOfTwo(OrderVo orderVo,int userId);

    int checkStore(int goodsId,String spec_key);

    void updateCouponStatus(int id);

    List<OrderDTO> orderList(int status, int userId, int page,int limit);

    OrderDTO orderDetail(String orderId,int userId);

    void orderHandle(int orderId,int type,int userId);

    List<StorePointsMoneyLog> pointsMoneyLogs(int type, int userId, int page, int limit);

    Map<String,Integer> countInfo(int userId);

    StoreOrder orderInfo(String orderId,int userId);

    void payYue(StoreOrder order);

    void notifyHandle(StoreOrder order);

}

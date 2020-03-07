package com.ruoyi.app.modular.shop.service;




import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.app.common.persistence.model.StoreOrder;
import com.ruoyi.app.common.persistence.model.StorePointsMoneyLog;
import com.ruoyi.app.modular.shop.service.dto.OrderDTO;
import com.ruoyi.app.modular.shop.service.vo.OrderVo;
import com.ruoyi.app.modular.shop.service.vo.PageVO;
import org.springframework.core.annotation.Order;


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

    List<StorePointsMoneyLog> pointsMoneyLogs(int type, int userId, int page,int limit);

    Map<String,Integer> countInfo(int userId);

    StoreOrder orderInfo(String orderId,int userId);

    void payYue(StoreOrder order);

    void notifyHandle(StoreOrder order);

}

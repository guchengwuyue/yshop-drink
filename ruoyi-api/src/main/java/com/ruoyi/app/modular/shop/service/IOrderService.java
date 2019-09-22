package com.ruoyi.app.modular.shop.service;




import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.app.common.persistence.model.StoreOrder;
import com.ruoyi.app.common.persistence.model.StorePointsMoneyLog;
import com.ruoyi.app.modular.shop.service.dto.OrderDTO;
import com.ruoyi.app.modular.shop.service.vo.OrderVo;
import com.ruoyi.app.modular.shop.service.vo.PageVO;


import java.util.List;
import java.util.Map;


public interface IOrderService extends IService<StoreOrder> {

    public String addOrderOfOne(OrderVo orderVo,int userId);

    public String addOrderOfTwo(OrderVo orderVo,int userId);

    public int checkStore(int goodsId,String spec_key);

    public void updateCouponStatus(int id);

    public List<OrderDTO> orderList(int status, int userId, int page,int limit);

    public OrderDTO orderDetail(String orderId,int userId);

    public void orderHandle(int orderId,int type,int userId);

    public List<StorePointsMoneyLog> pointsMoneyLogs(int type, int userId, int page,int limit);

    public Map<String,Integer> countInfo(int userId);

}

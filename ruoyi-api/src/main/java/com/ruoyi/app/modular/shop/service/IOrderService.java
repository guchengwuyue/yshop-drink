package com.ruoyi.app.modular.shop.service;




import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.app.common.persistence.model.StoreOrder;
import com.ruoyi.app.modular.shop.service.vo.OrderVo;


import java.util.List;


public interface IOrderService extends IService<StoreOrder> {

    public String addOrderOfOne(OrderVo orderVo,int userId);

    public String addOrderOfTwo(OrderVo orderVo,int userId);

    public int checkStore(int goodsId,String spec_key);

    public void updateCouponStatus(int id);
}

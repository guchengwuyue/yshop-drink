package co.yixiang.yshop.module.order.service.storeordercartinfo;

import co.yixiang.yshop.module.order.dal.dataobject.storeorder.StoreOrderDO;
import co.yixiang.yshop.module.order.dal.dataobject.storeordercartinfo.StoreOrderCartInfoDO;
import co.yixiang.yshop.module.product.controller.app.cart.vo.AppStoreCartQueryVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 订单购物详情 Service 接口
 *
 * @author yshop
 */
public interface StoreOrderCartInfoService extends IService<StoreOrderCartInfoDO> {

    /**
     * 添加购物车商品信息
     * @param oid 订单id
     * @param orderId 订单号
     * @param productIds 商品id
     * @param numbers 商品数量
     * @param specs 商品规格
     */
    void saveCartInfo(Long oid, String orderId,List<String> productIds,List<String> numbers,List<String> specs);



}

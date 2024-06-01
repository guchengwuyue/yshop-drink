package co.yixiang.yshop.module.order.service.storeorderstatus;

import co.yixiang.yshop.module.order.dal.dataobject.storeorderstatus.StoreOrderStatusDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 订单操作记录 Service 接口
 *
 * @author yshop
 */
public interface StoreOrderStatusService extends IService<StoreOrderStatusDO> {
    /**
     * 添加订单操作记录
     * @param oid 订单id
     * @param changetype 操作状态
     * @param changeMessage 操作内容
     */
    void create(Long uid,Long oid,String changetype,String changeMessage);


}

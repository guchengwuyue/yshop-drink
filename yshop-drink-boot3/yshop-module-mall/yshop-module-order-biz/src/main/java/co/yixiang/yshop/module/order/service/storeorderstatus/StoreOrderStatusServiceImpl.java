package co.yixiang.yshop.module.order.service.storeorderstatus;

import co.yixiang.yshop.module.order.dal.dataobject.storeorderstatus.StoreOrderStatusDO;
import co.yixiang.yshop.module.order.dal.mysql.storeorderstatus.StoreOrderStatusMapper;
import co.yixiang.yshop.module.order.service.storeorder.AsyncStoreOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;

/**
 * 订单操作记录 Service 实现类
 *
 * @author yshop
 */
@Service
@Validated
public class StoreOrderStatusServiceImpl extends ServiceImpl<StoreOrderStatusMapper, StoreOrderStatusDO> implements StoreOrderStatusService {

    @Resource
    private StoreOrderStatusMapper storeOrderStatusMapper;
    @Resource
    private AsyncStoreOrderService asyncStoreOrderService;
    /**
     * 添加订单操作记录
     * @param oid 订单id
     * @param changetype 操作状态
     * @param changeMessage 操作内容
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void create(Long uid,Long oid, String changetype, String changeMessage) {
        StoreOrderStatusDO storeOrderStatus = new StoreOrderStatusDO();
        storeOrderStatus.setOid(oid);
        storeOrderStatus.setChangeType(changetype);
        storeOrderStatus.setChangeMessage(changeMessage);
        this.baseMapper.insert(storeOrderStatus);

        //异步统计
        asyncStoreOrderService.orderData(uid);

    }

}

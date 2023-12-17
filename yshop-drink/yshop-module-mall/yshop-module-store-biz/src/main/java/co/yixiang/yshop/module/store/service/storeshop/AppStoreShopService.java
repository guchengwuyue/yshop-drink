package co.yixiang.yshop.module.store.service.storeshop;

import co.yixiang.yshop.module.store.controller.app.storeshop.vo.AppStoreShopVO;
import co.yixiang.yshop.module.store.dal.dataobject.storeshop.StoreShopDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 门店管理 Service 接口
 *
 * @author yshop
 */
public interface AppStoreShopService extends IService<StoreShopDO> {

    /**
     * 获取门店
     * @param lon 经度
     * @param lat 纬度
     * @param name 店铺名称
     * @return
     */
    List<AppStoreShopVO> getStoreList(double lon,double lat,String name,Integer shopId);

}

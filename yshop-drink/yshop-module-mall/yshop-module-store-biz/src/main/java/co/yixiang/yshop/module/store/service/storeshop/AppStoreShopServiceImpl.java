package co.yixiang.yshop.module.store.service.storeshop;

import co.yixiang.yshop.module.store.controller.app.storeshop.vo.AppStoreShopVO;
import co.yixiang.yshop.module.store.dal.dataobject.storeshop.StoreShopDO;
import co.yixiang.yshop.module.store.dal.mysql.storeshop.StoreShopMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

/**
 * 门店管理 Service 实现类
 *
 * @author yshop
 */
@Service
@Validated
public class AppStoreShopServiceImpl extends ServiceImpl<StoreShopMapper, StoreShopDO> implements AppStoreShopService {

    @Resource
    private StoreShopMapper shopMapper;


    /**
     * 获取门店
     * @param lon 经度
     * @param lat 纬度
     * @param name 店铺名称
     * @return
     */
    @Override
    public List<AppStoreShopVO> getStoreList(double lon, double lat,String name,Integer shopId) {
        return shopMapper.getStoreList(lon,lat,name,shopId);
    }
}

package co.yixiang.app.modular.shop.service;




import co.yixiang.app.common.persistence.model.StoreAds;
import com.baomidou.mybatisplus.extension.service.IService;
import co.yixiang.app.common.persistence.model.StoreAds;
import co.yixiang.app.modular.shop.service.dto.AdsDTO;

import java.util.List;


public interface IAdService extends IService<StoreAds> {
    List<AdsDTO> getList();

}

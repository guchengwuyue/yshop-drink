package com.ruoyi.app.modular.shop.service;




import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.app.common.persistence.model.StoreAds;
import com.ruoyi.app.modular.shop.service.dto.AdsDTO;

import java.util.List;


public interface IAdService extends IService<StoreAds> {
    List<AdsDTO> getList();

}

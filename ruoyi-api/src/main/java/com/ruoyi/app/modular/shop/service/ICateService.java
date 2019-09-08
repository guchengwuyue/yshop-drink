package com.ruoyi.app.modular.shop.service;




import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.app.common.persistence.model.StoreGoodsCate;
import com.ruoyi.app.modular.shop.service.dto.CateDTO;

import java.util.List;


public interface ICateService extends IService<StoreGoodsCate> {
    public List<CateDTO> getList();
}

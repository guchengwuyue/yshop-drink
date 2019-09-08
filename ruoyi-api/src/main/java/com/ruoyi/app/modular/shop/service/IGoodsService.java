package com.ruoyi.app.modular.shop.service;




import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.app.common.persistence.model.StoreGoods;
import com.ruoyi.app.modular.shop.service.dto.GoodsDTO;

import java.util.List;


public interface IGoodsService extends IService<StoreGoods> {
    public List<GoodsDTO> getList(int cateId,int page,int limit,
                                  int userId,String keywords,int order);

    public boolean isCollect(int goodsId,int userId);

    public List<StoreGoods> collectGoods(int page,int limit,int userId);
}

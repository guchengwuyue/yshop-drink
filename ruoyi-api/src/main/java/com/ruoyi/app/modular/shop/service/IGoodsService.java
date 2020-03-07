package com.ruoyi.app.modular.shop.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.app.common.persistence.model.StoreGoods;
import com.ruoyi.app.common.persistence.model.StoreSpecGoodsPrice;
import com.ruoyi.app.modular.shop.service.dto.CartDTO;
import com.ruoyi.app.modular.shop.service.dto.GoodsDTO;
import com.ruoyi.app.modular.shop.service.dto.SpecItemDTO;
import com.ruoyi.app.modular.shop.service.vo.CartVO;
import com.ruoyi.app.modular.shop.service.vo.PageVO;

import java.util.List;
import java.util.Map;


public interface IGoodsService extends IService<StoreGoods> {
    List<GoodsDTO> getList(int cateId,int page,int limit,
                                  int userId,String keywords,int order);

    boolean isCollect(int goodsId,int userId);

    List<StoreGoods> collectGoods(int page,int limit,int userId);

    Map<String,StoreSpecGoodsPrice> goodsSpecPrice(int goodsId);

    boolean addOrCancelCollect(int goodsId,int userId,int type);

    boolean addCart(CartVO cartVO,int userId);

    boolean delCart(String goodsIds,int userId);

    List<CartDTO> cartList(PageVO pageVO,int userId);

    Map<String, List<SpecItemDTO>> goodsSpec(int goodsId);
}

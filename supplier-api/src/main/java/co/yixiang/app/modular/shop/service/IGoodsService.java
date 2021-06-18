package co.yixiang.app.modular.shop.service;


import co.yixiang.app.common.persistence.model.StoreGoods;
import co.yixiang.app.common.persistence.model.StoreSpecGoodsPrice;
import com.baomidou.mybatisplus.extension.service.IService;
import co.yixiang.app.common.persistence.model.StoreGoods;
import co.yixiang.app.common.persistence.model.StoreSpecGoodsPrice;
import co.yixiang.app.modular.shop.service.dto.CartDTO;
import co.yixiang.app.modular.shop.service.dto.GoodsDTO;
import co.yixiang.app.modular.shop.service.dto.SpecItemDTO;
import co.yixiang.app.modular.shop.service.vo.CartVO;
import co.yixiang.app.modular.shop.service.vo.PageVO;

import java.util.List;
import java.util.Map;


public interface IGoodsService extends IService<StoreGoods> {
    List<GoodsDTO> getList(int cateId,int page,int limit,
                                  int userId,String keywords,int order);

    boolean isCollect(int goodsId,int userId);

    List<StoreGoods> collectGoods(int page,int limit,int userId);

    Map<String, StoreSpecGoodsPrice> goodsSpecPrice(int goodsId);

    boolean addOrCancelCollect(int goodsId,int userId,int type);

    boolean addCart(CartVO cartVO,int userId);

    boolean delCart(String goodsIds,int userId);

    List<CartDTO> cartList(PageVO pageVO,int userId);

    Map<String, List<SpecItemDTO>> goodsSpec(int goodsId);
}

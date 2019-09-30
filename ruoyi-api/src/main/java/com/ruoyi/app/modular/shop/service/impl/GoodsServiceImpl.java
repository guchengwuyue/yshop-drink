package com.ruoyi.app.modular.shop.service.impl;


//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmuch.lightsecurity.exception.LightSecurityException;
import com.ruoyi.app.common.exception.BadRequestException;
import com.ruoyi.app.common.exception.EntityExistException;
import com.ruoyi.app.common.persistence.dao.StoreCartMapper;
import com.ruoyi.app.common.persistence.dao.StoreGoodsCollectMapper;
import com.ruoyi.app.common.persistence.dao.StoreGoodsMapper;
import com.ruoyi.app.common.persistence.dao.StoreSpecGoodsPriceMapper;
import com.ruoyi.app.common.persistence.model.StoreCart;
import com.ruoyi.app.common.persistence.model.StoreGoods;
import com.ruoyi.app.common.persistence.model.StoreGoodsCollect;
import com.ruoyi.app.common.persistence.model.StoreSpecGoodsPrice;
import com.ruoyi.app.modular.shop.service.IGoodsService;
import com.ruoyi.app.modular.shop.service.dto.CartDTO;
import com.ruoyi.app.modular.shop.service.dto.GoodsDTO;
import com.ruoyi.app.modular.shop.service.dto.ItemDTO;
import com.ruoyi.app.modular.shop.service.dto.SpecItemDTO;
import com.ruoyi.app.modular.shop.service.mapper.CartMapper;
import com.ruoyi.app.modular.shop.service.mapper.CateMapper;
import com.ruoyi.app.modular.shop.service.mapper.GoodsMapper;
import com.ruoyi.app.modular.shop.service.vo.CartAttrVO;
import com.ruoyi.app.modular.shop.service.vo.CartVO;
import com.ruoyi.app.modular.shop.service.vo.PageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GoodsServiceImpl extends ServiceImpl<StoreGoodsMapper, StoreGoods>  implements IGoodsService {

    private final GoodsMapper goodsMapper;
    private final StoreGoodsMapper storeGoodsMapper;
    private final StoreGoodsCollectMapper storeGoodsCollectMapper;
    private final StoreSpecGoodsPriceMapper storeSpecGoodsPriceMapper;
    private final StoreCartMapper storeCartMapper;
    private final CartMapper cartMapper;


    /**
     * 商品列表
     * @param cateId
     * @param page
     * @param limit
     * @param userId
     * @param keywords
     * @param order
     * @return
     */
    @Override
    public List<GoodsDTO> getList(int cateId, int page, int limit, int userId,
                                  String keywords,int order) {

        QueryWrapper<StoreGoods> wrapper = new QueryWrapper<>();

        if(cateId > 0){
            wrapper.eq("cate_id",cateId);
        }
        if(StrUtil.isNotEmpty(keywords)){
            wrapper.like("goods_name",keywords);
        }

        //todo order = 1 推荐  order=2  新品
        switch (order){
            case 1:
                wrapper.eq("is_recommend",1);
                break;
            case 2:
                wrapper.eq("is_new",1);
                break;
        }
        Page<StoreGoods> pageModel = new Page<>(page, limit);

        IPage<StoreGoods> pageList = storeGoodsMapper.selectPage(pageModel,wrapper);

        List<GoodsDTO> list = goodsMapper.toDto(pageList.getRecords());

        for (GoodsDTO goodsDTO : list) {
            goodsDTO.setIsCollect(isCollect(goodsDTO.getGoodsId(),userId));
        }

        return list;
        //return null;
    }

    /**
     * 收藏判断
     * @param goodsId
     * @param userId
     * @return
     */
    @Override
    public boolean isCollect(int goodsId, int userId) {
        QueryWrapper<StoreGoodsCollect> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_id",goodsId).eq("user_id",userId);
        StoreGoodsCollect goodsCollect = storeGoodsCollectMapper.selectOne(wrapper);
        if(ObjectUtil.isNotNull(goodsCollect)) return true;
        return false;
    }


    @Override
    public List<StoreGoods> collectGoods(int page,int limit,int userId) {
        Page<StoreGoods> pageModel = new Page<>(page, limit);
        List<StoreGoods> list = baseMapper.collectGoods(pageModel,userId);
        return list;
    }


    @Override
    public Map<String,StoreSpecGoodsPrice> goodsSpecPrice(int goodsId) {
        QueryWrapper<StoreSpecGoodsPrice> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_id",goodsId);
        List<StoreSpecGoodsPrice> specGoodsPriceList = storeSpecGoodsPriceMapper
                .selectList(wrapper);
        if(ObjectUtil.isNull(specGoodsPriceList)){
            System.out.println("2222");
            throw new EntityExistException(specGoodsPriceList.getClass());
        }
        Map<String, StoreSpecGoodsPrice> listMap = specGoodsPriceList.stream().collect(
                Collectors.toMap(StoreSpecGoodsPrice::getKey,o->o));

        return listMap;
    }

    public Map<String, List<SpecItemDTO>> goodsSpec(int goodsId){

        String keys = storeSpecGoodsPriceMapper.goodsSpecKey(goodsId);
        if(StrUtil.isEmpty(keys)){
            throw new LightSecurityException("商品规格未添加");
        }
        String[] strArr = keys.split("_");
        Map<String,List<SpecItemDTO>> itemMap = new HashMap<>();
        List<ItemDTO> items =  storeSpecGoodsPriceMapper
                .goodsSpec(Arrays.asList(strArr));

        List<SpecItemDTO> listItem = null;
        for (ItemDTO itemDTO : items) {
            SpecItemDTO specItemDTO = new SpecItemDTO();
            specItemDTO.setItemId(itemDTO.getId());
            specItemDTO.setItem(itemDTO.getItem());

            if(!itemMap.containsKey(itemDTO.getName())){
                listItem = new ArrayList<>();
            }
            listItem.add(specItemDTO);

            itemMap.put(itemDTO.getName(),listItem);
        }
        return itemMap;
    }


    /**
     * 添加或者取消收藏
     * @param goodsId
     * @param userId
     * @param type
     * @return
     */
    @Override
    public boolean addOrCancelCollect(int goodsId, int userId, int type) {
        if(type == 1){//1-添加 2-取消
            boolean isCollect = isCollect(goodsId,userId);
            if(isCollect)  throw new LightSecurityException("已经收藏过");
            StoreGoodsCollect storeGoodsCollect = new StoreGoodsCollect();
            storeGoodsCollect.setGoodsId(goodsId);
            storeGoodsCollect.setUserId(userId);
            storeGoodsCollectMapper.insert(storeGoodsCollect);
            return true;
        }else{
            QueryWrapper<StoreGoodsCollect> wrapper = new QueryWrapper<>();
            wrapper.eq("goods_id",goodsId).eq("user_id",userId);
            int result = storeGoodsCollectMapper.delete(wrapper);
            if(result > 0) return true;
            return false;
        }
    }


    /**
     * 添加购物车
     * @param cartVO
     * @param userId
     * @return
     */
    @Override
    public boolean addCart(CartVO cartVO,int userId) {
        StoreGoods storeGoods = getById(cartVO.getGoodsId());

        for (CartAttrVO attrVO : cartVO.getCartAttr()) {
            QueryWrapper<StoreSpecGoodsPrice> wrapper = new QueryWrapper<>();
            QueryWrapper<StoreCart> cartQueryWrapper = new QueryWrapper<>();
            wrapper.eq("goods_id",cartVO.getGoodsId()).eq("`key`",attrVO.getSpecKey());
            StoreSpecGoodsPrice storeSpecGoodsPrice = storeSpecGoodsPriceMapper
                    .selectOne(wrapper);
            cartQueryWrapper.eq("goods_id",cartVO.getGoodsId())
                    .eq("user_id",userId).eq("spec_key",attrVO.getSpecKey());
            StoreCart storeCart = storeCartMapper.selectOne(cartQueryWrapper);
            if(storeCart != null){
                storeCart.setGoodsNum(attrVO.getGoodsNum());
                storeCartMapper.updateById(storeCart);
                continue;
            }

            StoreCart cart = new StoreCart();
            cart.setGoodsNum(attrVO.getGoodsNum());
            cart.setGoodsPrice(storeSpecGoodsPrice.getPrice());
            cart.setGoodsId(storeGoods.getGoodsId());
            cart.setUserId(userId);
            cart.setGoodsSn(storeGoods.getGoodsSn());
            cart.setGoodsName(storeGoods.getGoodsName());
            cart.setSpecKey(attrVO.getSpecKey());
            cart.setSpecKeyName(storeSpecGoodsPrice.getKeyName());
            cart.setGoodsLogo(storeGoods.getGoodsLogo());

            int result =  storeCartMapper.insert(cart);
            if(result == 0) return false;

        }


        return true;
    }


    @Override
    public boolean delCart(String goodsIds,int userId) {
        String[] goodsArr = goodsIds.split(",");
        QueryWrapper<StoreCart> cartQueryWrapper = new QueryWrapper<>();
        cartQueryWrapper.in("goods_id",Arrays.asList(goodsArr)).eq("user_id",userId);
        int result = storeCartMapper.delete(cartQueryWrapper);
        if(result > 0) return true;
        return false;
    }


    /**
     * 购物车列表
     * @param pageVO
     * @param userId
     * @return
     */
    @Override
    public List<CartDTO> cartList(PageVO pageVO,int userId) {
        QueryWrapper<StoreCart> cartQueryWrapper = new QueryWrapper<>();
        cartQueryWrapper.eq("user_id",userId).groupBy("goods_id");
        Page<StoreCart> pageModel = new Page<>(pageVO.getPage(), pageVO.getLimit());
        IPage<StoreCart> storeCarts = storeCartMapper
                .selectPage(pageModel,cartQueryWrapper);
        List<StoreCart> storeCartList = storeCarts.getRecords();
        List<CartDTO> carts = new ArrayList<>();

        for (StoreCart storeCart : storeCartList) {
            CartDTO cartDTO = new CartDTO();
            StoreGoods storeGoods = getById(storeCart.getGoodsId());
            cartDTO.setGoodsId(storeCart.getGoodsId());
            cartDTO.setGoodsName(storeGoods.getGoodsName());
            cartDTO.setGoodsSn(storeGoods.getGoodsSn());
            cartDTO.setGoodsLogo(storeGoods.getGoodsLogo());
            QueryWrapper<StoreCart> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id",userId).eq("goods_id",storeCart.getGoodsId());
            cartDTO.setSpecList(cartMapper.toDto(storeCartMapper.selectList(wrapper)));
            carts.add(cartDTO);
        }
        return carts;
    }
}

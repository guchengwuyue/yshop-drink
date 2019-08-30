package com.ruoyi.shop.mapper;

import com.ruoyi.shop.domain.StoreGoods;
import java.util.List;

/**
 * 商品主Mapper接口
 * 
 * @author hupeng
 * @date 2019-08-25
 */
public interface StoreGoodsMapper 
{
    /**
     * 查询商品主
     * 
     * @param goodsId 商品主ID
     * @return 商品主
     */
    public StoreGoods selectStoreGoodsById(Integer goodsId);

    /**
     * 查询商品主列表
     * 
     * @param storeGoods 商品主
     * @return 商品主集合
     */
    public List<StoreGoods> selectStoreGoodsList(StoreGoods storeGoods);

    /**
     * 新增商品主
     * 
     * @param storeGoods 商品主
     * @return 结果
     */
    public int insertStoreGoods(StoreGoods storeGoods);

    /**
     * 修改商品主
     * 
     * @param storeGoods 商品主
     * @return 结果
     */
    public int updateStoreGoods(StoreGoods storeGoods);

    /**
     * 删除商品主
     * 
     * @param goodsId 商品主ID
     * @return 结果
     */
    public int deleteStoreGoodsById(Integer goodsId);

    /**
     * 批量删除商品主
     * 
     * @param goodsIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteStoreGoodsByIds(String[] goodsIds);
}

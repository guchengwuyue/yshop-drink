package com.ruoyi.shop.mapper;

import com.ruoyi.shop.domain.StoreSpecGoodsPrice;
import java.util.List;

/**
 * 商品规格关联价格Mapper接口
 * 
 * @author hupeng
 * @date 2019-08-26
 */
public interface StoreSpecGoodsPriceMapper 
{
    /**
     * 查询商品规格关联价格
     * 
     * @param itemId 商品规格关联价格ID
     * @return 商品规格关联价格
     */
    public StoreSpecGoodsPrice selectStoreSpecGoodsPriceById(Long itemId);

    /**
     * 查询商品规格关联价格列表
     * 
     * @param storeSpecGoodsPrice 商品规格关联价格
     * @return 商品规格关联价格集合
     */
    public List<StoreSpecGoodsPrice> selectStoreSpecGoodsPriceList(StoreSpecGoodsPrice storeSpecGoodsPrice);

    public String selectStoreSpecGoodsPriceItemStr(Long goodsId);

    /**
     * 新增商品规格关联价格
     * 
     * @param storeSpecGoodsPrice 商品规格关联价格
     * @return 结果
     */
    public int insertStoreSpecGoodsPrice(StoreSpecGoodsPrice storeSpecGoodsPrice);

    /**
     * 修改商品规格关联价格
     * 
     * @param storeSpecGoodsPrice 商品规格关联价格
     * @return 结果
     */
    public int updateStoreSpecGoodsPrice(StoreSpecGoodsPrice storeSpecGoodsPrice);

    /**
     * 删除商品规格关联价格
     * 
     * @param itemId 商品规格关联价格ID
     * @return 结果
     */
    public int deleteStoreSpecGoodsPriceById(Long itemId);

    /**
     * 批量删除商品规格关联价格
     * 
     * @param itemIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteStoreSpecGoodsPriceByIds(String[] itemIds);

    public int delStoreSpecGoodsPriceByGoodsId(int goodsId);
}

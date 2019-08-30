package com.ruoyi.shop.service;

import com.ruoyi.shop.domain.StoreGoodsAttribute;
import java.util.List;

/**
 * 商品的属性(独立)Service接口
 * 
 * @author hupeng
 * @date 2019-08-24
 */
public interface IStoreGoodsAttributeService 
{
    /**
     * 查询商品的属性(独立)
     * 
     * @param attrId 商品的属性(独立)ID
     * @return 商品的属性(独立)
     */
    public StoreGoodsAttribute selectStoreGoodsAttributeById(Long attrId);

    /**
     * 查询商品的属性(独立)列表
     * 
     * @param storeGoodsAttribute 商品的属性(独立)
     * @return 商品的属性(独立)集合
     */
    public List<StoreGoodsAttribute> selectStoreGoodsAttributeList(StoreGoodsAttribute storeGoodsAttribute);

    /**
     * 新增商品的属性(独立)
     * 
     * @param storeGoodsAttribute 商品的属性(独立)
     * @return 结果
     */
    public int insertStoreGoodsAttribute(StoreGoodsAttribute storeGoodsAttribute);

    /**
     * 修改商品的属性(独立)
     * 
     * @param storeGoodsAttribute 商品的属性(独立)
     * @return 结果
     */
    public int updateStoreGoodsAttribute(StoreGoodsAttribute storeGoodsAttribute);

    /**
     * 批量删除商品的属性(独立)
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStoreGoodsAttributeByIds(String ids);

    /**
     * 删除商品的属性(独立)信息
     * 
     * @param attrId 商品的属性(独立)ID
     * @return 结果
     */
    public int deleteStoreGoodsAttributeById(Long attrId);
}

package com.ruoyi.shop.service;

import com.ruoyi.shop.domain.StoreGoodsAttr;
import java.util.List;

/**
 * 商品关联的属性(与商品相关)Service接口
 * 
 * @author hupeng
 * @date 2019-08-26
 */
public interface IStoreGoodsAttrService 
{
    /**
     * 查询商品关联的属性(与商品相关)
     * 
     * @param goodsAttrId 商品关联的属性(与商品相关)ID
     * @return 商品关联的属性(与商品相关)
     */
    public StoreGoodsAttr selectStoreGoodsAttrById(Integer goodsAttrId);

    /**
     * 查询商品关联的属性(与商品相关)列表
     * 
     * @param storeGoodsAttr 商品关联的属性(与商品相关)
     * @return 商品关联的属性(与商品相关)集合
     */
    public List<StoreGoodsAttr> selectStoreGoodsAttrList(StoreGoodsAttr storeGoodsAttr);

    /**
     * 新增商品关联的属性(与商品相关)
     * 
     * @param storeGoodsAttr 商品关联的属性(与商品相关)
     * @return 结果
     */
    public int insertStoreGoodsAttr(StoreGoodsAttr storeGoodsAttr);

    /**
     * 修改商品关联的属性(与商品相关)
     * 
     * @param storeGoodsAttr 商品关联的属性(与商品相关)
     * @return 结果
     */
    public int updateStoreGoodsAttr(StoreGoodsAttr storeGoodsAttr);

    /**
     * 批量删除商品关联的属性(与商品相关)
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStoreGoodsAttrByIds(String ids);

    /**
     * 删除商品关联的属性(与商品相关)信息
     * 
     * @param goodsAttrId 商品关联的属性(与商品相关)ID
     * @return 结果
     */
    public int deleteStoreGoodsAttrById(Integer goodsAttrId);
}

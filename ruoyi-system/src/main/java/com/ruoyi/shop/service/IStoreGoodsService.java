package com.ruoyi.shop.service;

import com.ruoyi.shop.domain.StoreGoods;
import java.util.List;
import java.util.Map;

/**
 * 商品主Service接口
 * 
 * @author hupeng
 * @date 2019-08-25
 */
public interface IStoreGoodsService 
{


    /**
     * 更新或者新增规格属性
     * @param specDataMap
     * @param attrDataMap
     * @param goodsId
     * @param goodsType
     * @return
     */
    public int saveSpecAttr(Map<String,Map<String,String>> specDataMap,
                            Map<String,String> attrDataMap,String goodsId,String goodsType);

    /**
     * 获取规格字符串
     * @param goodsId
     * @return
     */
    public String getGoodsItems(Long goodsId);

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
     * 批量删除商品主
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStoreGoodsByIds(String ids);

    /**
     * 删除商品主信息
     * 
     * @param goodsId 商品主ID
     * @return 结果
     */
    public int deleteStoreGoodsById(Integer goodsId);
}

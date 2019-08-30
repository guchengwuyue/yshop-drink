package com.ruoyi.shop.service;

import com.ruoyi.shop.domain.StoreGoodsType;
import java.util.List;

/**
 * 商品类型(商品模型)Service接口
 * 
 * @author hupeng
 * @date 2019-08-24
 */
public interface IStoreGoodsTypeService 
{
    /**
     * 查询商品类型(商品模型)
     * 
     * @param id 商品类型(商品模型)ID
     * @return 商品类型(商品模型)
     */
    public StoreGoodsType selectStoreGoodsTypeById(Integer id);

    /**
     * 查询商品类型(商品模型)列表
     * 
     * @param storeGoodsType 商品类型(商品模型)
     * @return 商品类型(商品模型)集合
     */
    public List<StoreGoodsType> selectStoreGoodsTypeList(StoreGoodsType storeGoodsType);

    /**
     * 新增商品类型(商品模型)
     * 
     * @param storeGoodsType 商品类型(商品模型)
     * @return 结果
     */
    public int insertStoreGoodsType(StoreGoodsType storeGoodsType);

    /**
     * 修改商品类型(商品模型)
     * 
     * @param storeGoodsType 商品类型(商品模型)
     * @return 结果
     */
    public int updateStoreGoodsType(StoreGoodsType storeGoodsType);

    /**
     * 批量删除商品类型(商品模型)
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStoreGoodsTypeByIds(String ids);

    /**
     * 删除商品类型(商品模型)信息
     * 
     * @param id 商品类型(商品模型)ID
     * @return 结果
     */
    public int deleteStoreGoodsTypeById(Integer id);
}

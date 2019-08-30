package com.ruoyi.shop.mapper;

import com.ruoyi.shop.domain.StoreGoodsCate;
import java.util.List;

/**
 * 商城商品分类Mapper接口
 * 
 * @author hupeng
 * @date 2019-08-22
 */
public interface StoreGoodsCateMapper 
{
    /**
     * 查询商城商品分类
     * 
     * @param id 商城商品分类ID
     * @return 商城商品分类
     */
    public StoreGoodsCate selectStoreGoodsCateById(Long id);

    /**
     * 查询商城商品分类列表
     * 
     * @param storeGoodsCate 商城商品分类
     * @return 商城商品分类集合
     */
    public List<StoreGoodsCate> selectStoreGoodsCateList(StoreGoodsCate storeGoodsCate);

    /**
     * 新增商城商品分类
     * 
     * @param storeGoodsCate 商城商品分类
     * @return 结果
     */
    public int insertStoreGoodsCate(StoreGoodsCate storeGoodsCate);

    /**
     * 修改商城商品分类
     * 
     * @param storeGoodsCate 商城商品分类
     * @return 结果
     */
    public int updateStoreGoodsCate(StoreGoodsCate storeGoodsCate);

    /**
     * 删除商城商品分类
     * 
     * @param id 商城商品分类ID
     * @return 结果
     */
    public int deleteStoreGoodsCateById(Long id);

    /**
     * 批量删除商城商品分类
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStoreGoodsCateByIds(String[] ids);
}

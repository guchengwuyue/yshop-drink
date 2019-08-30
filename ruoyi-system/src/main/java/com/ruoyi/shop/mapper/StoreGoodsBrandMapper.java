package com.ruoyi.shop.mapper;

import com.ruoyi.shop.domain.StoreGoodsBrand;
import java.util.List;

/**
 * 产品品牌Mapper接口
 * 
 * @author hupeng
 * @date 2019-08-23
 */
public interface StoreGoodsBrandMapper 
{
    /**
     * 查询产品品牌
     * 
     * @param id 产品品牌ID
     * @return 产品品牌
     */
    public StoreGoodsBrand selectStoreGoodsBrandById(Long id);

    /**
     * 查询产品品牌列表
     * 
     * @param storeGoodsBrand 产品品牌
     * @return 产品品牌集合
     */
    public List<StoreGoodsBrand> selectStoreGoodsBrandList(StoreGoodsBrand storeGoodsBrand);

    /**
     * 新增产品品牌
     * 
     * @param storeGoodsBrand 产品品牌
     * @return 结果
     */
    public int insertStoreGoodsBrand(StoreGoodsBrand storeGoodsBrand);

    /**
     * 修改产品品牌
     * 
     * @param storeGoodsBrand 产品品牌
     * @return 结果
     */
    public int updateStoreGoodsBrand(StoreGoodsBrand storeGoodsBrand);

    /**
     * 删除产品品牌
     * 
     * @param id 产品品牌ID
     * @return 结果
     */
    public int deleteStoreGoodsBrandById(Long id);

    /**
     * 批量删除产品品牌
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStoreGoodsBrandByIds(String[] ids);
}

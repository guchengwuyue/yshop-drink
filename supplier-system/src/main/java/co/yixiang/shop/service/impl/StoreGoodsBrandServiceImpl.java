package co.yixiang.shop.service.impl;

import java.util.List;

import co.yixiang.shop.domain.StoreGoodsBrand;
import co.yixiang.shop.mapper.StoreGoodsBrandMapper;
import co.yixiang.shop.service.IStoreGoodsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.yixiang.shop.mapper.StoreGoodsBrandMapper;
import co.yixiang.shop.domain.StoreGoodsBrand;
import co.yixiang.shop.service.IStoreGoodsBrandService;
import co.yixiang.common.core.text.Convert;

/**
 * 产品品牌Service业务层处理
 * 
 * @author hupeng
 * @date 2019-08-23
 */
@Service
public class StoreGoodsBrandServiceImpl implements IStoreGoodsBrandService
{
    @Autowired
    private StoreGoodsBrandMapper storeGoodsBrandMapper;

    /**
     * 查询产品品牌
     * 
     * @param id 产品品牌ID
     * @return 产品品牌
     */
    @Override
    public StoreGoodsBrand selectStoreGoodsBrandById(Long id)
    {
        return storeGoodsBrandMapper.selectStoreGoodsBrandById(id);
    }

    /**
     * 查询产品品牌列表
     * 
     * @param storeGoodsBrand 产品品牌
     * @return 产品品牌
     */
    @Override
    public List<StoreGoodsBrand> selectStoreGoodsBrandList(StoreGoodsBrand storeGoodsBrand)
    {
        return storeGoodsBrandMapper.selectStoreGoodsBrandList(storeGoodsBrand);
    }

    /**
     * 新增产品品牌
     * 
     * @param storeGoodsBrand 产品品牌
     * @return 结果
     */
    @Override
    public int insertStoreGoodsBrand(StoreGoodsBrand storeGoodsBrand)
    {
        return storeGoodsBrandMapper.insertStoreGoodsBrand(storeGoodsBrand);
    }

    /**
     * 修改产品品牌
     * 
     * @param storeGoodsBrand 产品品牌
     * @return 结果
     */
    @Override
    public int updateStoreGoodsBrand(StoreGoodsBrand storeGoodsBrand)
    {
        return storeGoodsBrandMapper.updateStoreGoodsBrand(storeGoodsBrand);
    }

    /**
     * 删除产品品牌对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteStoreGoodsBrandByIds(String ids)
    {
        return storeGoodsBrandMapper.deleteStoreGoodsBrandByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品品牌信息
     * 
     * @param id 产品品牌ID
     * @return 结果
     */
    public int deleteStoreGoodsBrandById(Long id)
    {
        return storeGoodsBrandMapper.deleteStoreGoodsBrandById(id);
    }
}

package co.yixiang.shop.service.impl;

import java.util.List;
import java.util.ArrayList;
import co.yixiang.common.core.domain.Ztree;
import co.yixiang.common.exception.BusinessException;
import co.yixiang.shop.domain.StoreGoodsCate;
import co.yixiang.shop.mapper.StoreGoodsCateMapper;
import co.yixiang.shop.service.IStoreGoodsCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.yixiang.shop.mapper.StoreGoodsCateMapper;
import co.yixiang.shop.domain.StoreGoodsCate;
import co.yixiang.shop.service.IStoreGoodsCateService;
import co.yixiang.common.core.text.Convert;

/**
 * 商城商品分类Service业务层处理
 * 
 * @author hupeng
 * @date 2019-08-22
 */
@Service
public class StoreGoodsCateServiceImpl implements IStoreGoodsCateService
{
    @Autowired
    private StoreGoodsCateMapper storeGoodsCateMapper;

    /**
     * 查询商城商品分类
     * 
     * @param id 商城商品分类ID
     * @return 商城商品分类
     */
    @Override
    public StoreGoodsCate selectStoreGoodsCateById(Long id)
    {
        return storeGoodsCateMapper.selectStoreGoodsCateById(id);
    }

    /**
     * 查询商城商品分类列表
     * 
     * @param storeGoodsCate 商城商品分类
     * @return 商城商品分类
     */
    @Override
    public List<StoreGoodsCate> selectStoreGoodsCateList(StoreGoodsCate storeGoodsCate)
    {
        return storeGoodsCateMapper.selectStoreGoodsCateList(storeGoodsCate);
    }

    /**
     * 新增商城商品分类
     * 
     * @param storeGoodsCate 商城商品分类
     * @return 结果
     */
    @Override
    public int insertStoreGoodsCate(StoreGoodsCate storeGoodsCate)
    {
        return storeGoodsCateMapper.insertStoreGoodsCate(storeGoodsCate);
    }

    /**
     * 修改商城商品分类
     * 
     * @param storeGoodsCate 商城商品分类
     * @return 结果
     */
    @Override
    public int updateStoreGoodsCate(StoreGoodsCate storeGoodsCate)
    {
        return storeGoodsCateMapper.updateStoreGoodsCate(storeGoodsCate);
    }

    /**
     * 删除商城商品分类对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteStoreGoodsCateByIds(String ids)
    {
        return storeGoodsCateMapper.deleteStoreGoodsCateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商城商品分类信息
     * 
     * @param id 商城商品分类ID
     * @return 结果
     */
    public int deleteStoreGoodsCateById(Long id)
    {
        StoreGoodsCate goodsCate = new StoreGoodsCate();
        goodsCate.setPid(id);
        List<StoreGoodsCate> goodsCates = storeGoodsCateMapper
                .selectStoreGoodsCateList(goodsCate);
        if(!goodsCates.isEmpty())  throw new BusinessException("请先删除子分类");
        return storeGoodsCateMapper.deleteStoreGoodsCateById(id);
    }

    /**
     * 查询商城商品分类树列表
     * 
     * @return 所有商城商品分类信息
     */
    @Override
    public List<Ztree> selectStoreGoodsCateTree()
    {
        List<StoreGoodsCate> storeGoodsCateList = storeGoodsCateMapper.selectStoreGoodsCateList(new StoreGoodsCate());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (StoreGoodsCate storeGoodsCate : storeGoodsCateList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(storeGoodsCate.getId());
            ztree.setpId(storeGoodsCate.getPid());
            ztree.setName(storeGoodsCate.getCateTitle());
            ztree.setTitle(storeGoodsCate.getCateTitle());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}

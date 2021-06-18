package co.yixiang.shop.service.impl;

import java.util.List;

import co.yixiang.shop.domain.StoreOrderGoods;
import co.yixiang.shop.mapper.StoreOrderGoodsMapper;
import co.yixiang.shop.service.IStoreOrderGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.yixiang.shop.mapper.StoreOrderGoodsMapper;
import co.yixiang.shop.domain.StoreOrderGoods;
import co.yixiang.shop.service.IStoreOrderGoodsService;
import co.yixiang.common.core.text.Convert;

/**
 * 订单商品与订单主关联Service业务层处理
 * 
 * @author hupeng
 * @date 2019-08-29
 */
@Service
public class StoreOrderGoodsServiceImpl implements IStoreOrderGoodsService
{
    @Autowired
    private StoreOrderGoodsMapper storeOrderGoodsMapper;

    /**
     * 查询订单商品与订单主关联
     * 
     * @param recId 订单商品与订单主关联ID
     * @return 订单商品与订单主关联
     */
    @Override
    public StoreOrderGoods selectStoreOrderGoodsById(Integer recId)
    {
        return storeOrderGoodsMapper.selectStoreOrderGoodsById(recId);
    }

    /**
     * 查询订单商品与订单主关联列表
     * 
     * @param storeOrderGoods 订单商品与订单主关联
     * @return 订单商品与订单主关联
     */
    @Override
    public List<StoreOrderGoods> selectStoreOrderGoodsList(StoreOrderGoods storeOrderGoods)
    {
        return storeOrderGoodsMapper.selectStoreOrderGoodsList(storeOrderGoods);
    }

    /**
     * 新增订单商品与订单主关联
     * 
     * @param storeOrderGoods 订单商品与订单主关联
     * @return 结果
     */
    @Override
    public int insertStoreOrderGoods(StoreOrderGoods storeOrderGoods)
    {
        return storeOrderGoodsMapper.insertStoreOrderGoods(storeOrderGoods);
    }

    /**
     * 修改订单商品与订单主关联
     * 
     * @param storeOrderGoods 订单商品与订单主关联
     * @return 结果
     */
    @Override
    public int updateStoreOrderGoods(StoreOrderGoods storeOrderGoods)
    {
        return storeOrderGoodsMapper.updateStoreOrderGoods(storeOrderGoods);
    }

    /**
     * 删除订单商品与订单主关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteStoreOrderGoodsByIds(String ids)
    {
        return storeOrderGoodsMapper.deleteStoreOrderGoodsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单商品与订单主关联信息
     * 
     * @param recId 订单商品与订单主关联ID
     * @return 结果
     */
    public int deleteStoreOrderGoodsById(Integer recId)
    {
        return storeOrderGoodsMapper.deleteStoreOrderGoodsById(recId);
    }
}

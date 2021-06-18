package co.yixiang.shop.service.impl;

import java.util.List;

import co.yixiang.shop.domain.StoreOrderGoodsRemark;
import co.yixiang.shop.mapper.StoreOrderGoodsRemarkMapper;
import co.yixiang.shop.service.IStoreOrderGoodsRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.yixiang.shop.mapper.StoreOrderGoodsRemarkMapper;
import co.yixiang.shop.domain.StoreOrderGoodsRemark;
import co.yixiang.shop.service.IStoreOrderGoodsRemarkService;
import co.yixiang.common.core.text.Convert;

/**
 * 订单商品备注Service业务层处理
 * 
 * @author hupeng
 * @date 2019-08-29
 */
@Service
public class StoreOrderGoodsRemarkServiceImpl implements IStoreOrderGoodsRemarkService
{
    @Autowired
    private StoreOrderGoodsRemarkMapper storeOrderGoodsRemarkMapper;

    /**
     * 查询订单商品备注
     * 
     * @param id 订单商品备注ID
     * @return 订单商品备注
     */
    @Override
    public StoreOrderGoodsRemark selectStoreOrderGoodsRemarkById(Integer id)
    {
        return storeOrderGoodsRemarkMapper.selectStoreOrderGoodsRemarkById(id);
    }

    /**
     * 查询订单商品备注列表
     * 
     * @param storeOrderGoodsRemark 订单商品备注
     * @return 订单商品备注
     */
    @Override
    public List<StoreOrderGoodsRemark> selectStoreOrderGoodsRemarkList(StoreOrderGoodsRemark storeOrderGoodsRemark)
    {
        return storeOrderGoodsRemarkMapper.selectStoreOrderGoodsRemarkList(storeOrderGoodsRemark);
    }

    /**
     * 新增订单商品备注
     * 
     * @param storeOrderGoodsRemark 订单商品备注
     * @return 结果
     */
    @Override
    public int insertStoreOrderGoodsRemark(StoreOrderGoodsRemark storeOrderGoodsRemark)
    {
        return storeOrderGoodsRemarkMapper.insertStoreOrderGoodsRemark(storeOrderGoodsRemark);
    }

    /**
     * 修改订单商品备注
     * 
     * @param storeOrderGoodsRemark 订单商品备注
     * @return 结果
     */
    @Override
    public int updateStoreOrderGoodsRemark(StoreOrderGoodsRemark storeOrderGoodsRemark)
    {
        return storeOrderGoodsRemarkMapper.updateStoreOrderGoodsRemark(storeOrderGoodsRemark);
    }

    /**
     * 删除订单商品备注对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteStoreOrderGoodsRemarkByIds(String ids)
    {
        return storeOrderGoodsRemarkMapper.deleteStoreOrderGoodsRemarkByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单商品备注信息
     * 
     * @param id 订单商品备注ID
     * @return 结果
     */
    public int deleteStoreOrderGoodsRemarkById(Integer id)
    {
        return storeOrderGoodsRemarkMapper.deleteStoreOrderGoodsRemarkById(id);
    }
}

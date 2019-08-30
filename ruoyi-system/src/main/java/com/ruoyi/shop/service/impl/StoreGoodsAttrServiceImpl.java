package com.ruoyi.shop.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.shop.mapper.StoreGoodsAttrMapper;
import com.ruoyi.shop.domain.StoreGoodsAttr;
import com.ruoyi.shop.service.IStoreGoodsAttrService;
import com.ruoyi.common.core.text.Convert;

/**
 * 商品关联的属性(与商品相关)Service业务层处理
 * 
 * @author hupeng
 * @date 2019-08-26
 */
@Service
public class StoreGoodsAttrServiceImpl implements IStoreGoodsAttrService 
{
    @Autowired
    private StoreGoodsAttrMapper storeGoodsAttrMapper;

    /**
     * 查询商品关联的属性(与商品相关)
     * 
     * @param goodsAttrId 商品关联的属性(与商品相关)ID
     * @return 商品关联的属性(与商品相关)
     */
    @Override
    public StoreGoodsAttr selectStoreGoodsAttrById(Integer goodsAttrId)
    {
        return storeGoodsAttrMapper.selectStoreGoodsAttrById(goodsAttrId);
    }

    /**
     * 查询商品关联的属性(与商品相关)列表
     * 
     * @param storeGoodsAttr 商品关联的属性(与商品相关)
     * @return 商品关联的属性(与商品相关)
     */
    @Override
    public List<StoreGoodsAttr> selectStoreGoodsAttrList(StoreGoodsAttr storeGoodsAttr)
    {
        return storeGoodsAttrMapper.selectStoreGoodsAttrList(storeGoodsAttr);
    }

    /**
     * 新增商品关联的属性(与商品相关)
     * 
     * @param storeGoodsAttr 商品关联的属性(与商品相关)
     * @return 结果
     */
    @Override
    public int insertStoreGoodsAttr(StoreGoodsAttr storeGoodsAttr)
    {
        return storeGoodsAttrMapper.insertStoreGoodsAttr(storeGoodsAttr);
    }

    /**
     * 修改商品关联的属性(与商品相关)
     * 
     * @param storeGoodsAttr 商品关联的属性(与商品相关)
     * @return 结果
     */
    @Override
    public int updateStoreGoodsAttr(StoreGoodsAttr storeGoodsAttr)
    {
        return storeGoodsAttrMapper.updateStoreGoodsAttr(storeGoodsAttr);
    }

    /**
     * 删除商品关联的属性(与商品相关)对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteStoreGoodsAttrByIds(String ids)
    {
        return storeGoodsAttrMapper.deleteStoreGoodsAttrByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商品关联的属性(与商品相关)信息
     * 
     * @param goodsAttrId 商品关联的属性(与商品相关)ID
     * @return 结果
     */
    public int deleteStoreGoodsAttrById(Integer goodsAttrId)
    {
        return storeGoodsAttrMapper.deleteStoreGoodsAttrById(goodsAttrId);
    }
}

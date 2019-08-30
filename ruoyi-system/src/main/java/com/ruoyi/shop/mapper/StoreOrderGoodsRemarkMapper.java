package com.ruoyi.shop.mapper;

import com.ruoyi.shop.domain.StoreOrderGoodsRemark;
import java.util.List;

/**
 * 订单商品备注Mapper接口
 * 
 * @author hupeng
 * @date 2019-08-29
 */
public interface StoreOrderGoodsRemarkMapper 
{
    /**
     * 查询订单商品备注
     * 
     * @param id 订单商品备注ID
     * @return 订单商品备注
     */
    public StoreOrderGoodsRemark selectStoreOrderGoodsRemarkById(Integer id);

    /**
     * 查询订单商品备注列表
     * 
     * @param storeOrderGoodsRemark 订单商品备注
     * @return 订单商品备注集合
     */
    public List<StoreOrderGoodsRemark> selectStoreOrderGoodsRemarkList(StoreOrderGoodsRemark storeOrderGoodsRemark);

    /**
     * 新增订单商品备注
     * 
     * @param storeOrderGoodsRemark 订单商品备注
     * @return 结果
     */
    public int insertStoreOrderGoodsRemark(StoreOrderGoodsRemark storeOrderGoodsRemark);

    /**
     * 修改订单商品备注
     * 
     * @param storeOrderGoodsRemark 订单商品备注
     * @return 结果
     */
    public int updateStoreOrderGoodsRemark(StoreOrderGoodsRemark storeOrderGoodsRemark);

    /**
     * 删除订单商品备注
     * 
     * @param id 订单商品备注ID
     * @return 结果
     */
    public int deleteStoreOrderGoodsRemarkById(Integer id);

    /**
     * 批量删除订单商品备注
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStoreOrderGoodsRemarkByIds(String[] ids);
}

package com.ruoyi.shop.service;

import com.ruoyi.shop.domain.StoreCouponList;
import java.util.List;

/**
 * 优惠券记录Service接口
 * 
 * @author hupeng
 * @date 2019-08-29
 */
public interface IStoreCouponListService 
{
    /**
     * 查询优惠券记录
     * 
     * @param id 优惠券记录ID
     * @return 优惠券记录
     */
    public StoreCouponList selectStoreCouponListById(Integer id);

    /**
     * 查询优惠券记录列表
     * 
     * @param storeCouponList 优惠券记录
     * @return 优惠券记录集合
     */
    public List<StoreCouponList> selectStoreCouponListList(StoreCouponList storeCouponList);

    /**
     * 新增优惠券记录
     * 
     * @param storeCouponList 优惠券记录
     * @return 结果
     */
    public int insertStoreCouponList(StoreCouponList storeCouponList);

    /**
     * 修改优惠券记录
     * 
     * @param storeCouponList 优惠券记录
     * @return 结果
     */
    public int updateStoreCouponList(StoreCouponList storeCouponList);

    /**
     * 批量删除优惠券记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStoreCouponListByIds(String ids);

    /**
     * 删除优惠券记录信息
     * 
     * @param id 优惠券记录ID
     * @return 结果
     */
    public int deleteStoreCouponListById(Integer id);
}

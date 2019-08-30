package com.ruoyi.shop.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.shop.mapper.StoreCouponMapper;
import com.ruoyi.shop.domain.StoreCoupon;
import com.ruoyi.shop.service.IStoreCouponService;
import com.ruoyi.common.core.text.Convert;

/**
 * 优惠券主Service业务层处理
 * 
 * @author hupeng
 * @date 2019-08-29
 */
@Service
public class StoreCouponServiceImpl implements IStoreCouponService 
{
    @Autowired
    private StoreCouponMapper storeCouponMapper;

    /**
     * 查询优惠券主
     * 
     * @param id 优惠券主ID
     * @return 优惠券主
     */
    @Override
    public StoreCoupon selectStoreCouponById(Integer id)
    {
        return storeCouponMapper.selectStoreCouponById(id);
    }

    /**
     * 查询优惠券主列表
     * 
     * @param storeCoupon 优惠券主
     * @return 优惠券主
     */
    @Override
    public List<StoreCoupon> selectStoreCouponList(StoreCoupon storeCoupon)
    {
        return storeCouponMapper.selectStoreCouponList(storeCoupon);
    }

    /**
     * 新增优惠券主
     * 
     * @param storeCoupon 优惠券主
     * @return 结果
     */
    @Override
    public int insertStoreCoupon(StoreCoupon storeCoupon)
    {
        return storeCouponMapper.insertStoreCoupon(storeCoupon);
    }

    /**
     * 修改优惠券主
     * 
     * @param storeCoupon 优惠券主
     * @return 结果
     */
    @Override
    public int updateStoreCoupon(StoreCoupon storeCoupon)
    {
        return storeCouponMapper.updateStoreCoupon(storeCoupon);
    }

    /**
     * 删除优惠券主对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteStoreCouponByIds(String ids)
    {
        return storeCouponMapper.deleteStoreCouponByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除优惠券主信息
     * 
     * @param id 优惠券主ID
     * @return 结果
     */
    public int deleteStoreCouponById(Integer id)
    {
        return storeCouponMapper.deleteStoreCouponById(id);
    }
}

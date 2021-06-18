package co.yixiang.shop.mapper;

import co.yixiang.shop.domain.StoreCoupon;

import java.util.List;

/**
 * 优惠券主Mapper接口
 * 
 * @author hupeng
 * @date 2019-08-29
 */
public interface StoreCouponMapper 
{
    /**
     * 查询优惠券主
     * 
     * @param id 优惠券主ID
     * @return 优惠券主
     */
    public StoreCoupon selectStoreCouponById(Integer id);

    /**
     * 查询优惠券主列表
     * 
     * @param storeCoupon 优惠券主
     * @return 优惠券主集合
     */
    public List<StoreCoupon> selectStoreCouponList(StoreCoupon storeCoupon);

    /**
     * 新增优惠券主
     * 
     * @param storeCoupon 优惠券主
     * @return 结果
     */
    public int insertStoreCoupon(StoreCoupon storeCoupon);

    /**
     * 修改优惠券主
     * 
     * @param storeCoupon 优惠券主
     * @return 结果
     */
    public int updateStoreCoupon(StoreCoupon storeCoupon);

    /**
     * 删除优惠券主
     * 
     * @param id 优惠券主ID
     * @return 结果
     */
    public int deleteStoreCouponById(Integer id);

    /**
     * 批量删除优惠券主
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStoreCouponByIds(String[] ids);
}

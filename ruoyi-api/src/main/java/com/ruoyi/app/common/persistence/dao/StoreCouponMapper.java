package com.ruoyi.app.common.persistence.dao;

import com.ruoyi.app.common.persistence.model.StoreCoupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.app.modular.shop.service.dto.CouponDTO;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 优惠券主表 Mapper 接口
 * </p>
 *
 * @author hupeng
 * @since 2019-09-13
 */
public interface StoreCouponMapper extends BaseMapper<StoreCoupon> {

    @Select("select s.id,c.money from store_coupon_list s left join  store_coupon c " +
            "on s.cid=c.id where s.id=#{id}")
    CouponDTO findLog(int id);

    @Update("update store_coupon set use_num=use_num+1 where id=#{id}")
    void incUserNum(int id);

}

package co.yixiang.app.common.persistence.dao;

import co.yixiang.app.common.persistence.model.StoreCoupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import co.yixiang.app.modular.shop.service.dto.CouponDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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

    @Select("<script>select s.id,c.money,c.name,c.condition,c.use_end_time as useEndTime from store_coupon_list s left join store_coupon c " +
            "on s.cid=c.id where s.uid=#{userId} and s.status=0" +
            " <if test='orderTotalPrice > 0'>and c.condition <![CDATA[<= ]]> #{orderTotalPrice}</if>" +
            " and c.use_start_time <![CDATA[<= ]]> now() and c.use_end_time <![CDATA[>= ]]> now()</script>")
    List<CouponDTO> couponList(@Param("userId") int userId,
                               @Param("orderTotalPrice") double orderTotalPrice);

}

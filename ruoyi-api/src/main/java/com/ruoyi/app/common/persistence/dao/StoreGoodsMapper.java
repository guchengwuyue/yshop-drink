package com.ruoyi.app.common.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.app.common.persistence.model.StoreGoods;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * <p>
 * 商品主表 Mapper 接口
 * </p>
 *
 * @author hupeng
 * @since 2019-09-05
 */
@Mapper
public interface StoreGoodsMapper extends BaseMapper<StoreGoods> {

    @Results({
            @Result(property = "goodsId",column = "goods_id"),
            @Result(property = "goodsName",column = "goods_name"),
            @Result(property = "goodsSn",column = "goods_sn"),
            @Result(property = "goodsLogo",column = "goods_logo"),
            @Result(property = "shopPrice",column = "shop_price"),
            @Result(property = "marketPrice",column = "market_price")
    })
    @Select("select g.* from store_goods_collect c left join store_goods g " +
            "on c.goods_id=g.goods_id where c.user_id=#{userId}")
    List<StoreGoods> collectGoods(Page page, @Param("userId") int userId);
}

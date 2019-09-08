package com.ruoyi.app.common.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.app.common.persistence.model.StoreGoods;
import com.ruoyi.app.common.persistence.model.StoreGoodsCollect;
import com.ruoyi.app.modular.shop.service.dto.GoodsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p>
 * 商品收藏表 Mapper 接口
 * </p>
 *
 * @author hupeng
 * @since 2019-09-07
 */
@Mapper
public interface StoreGoodsCollectMapper extends BaseMapper<StoreGoodsCollect> {


}

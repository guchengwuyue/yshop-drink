package com.ruoyi.app.modular.shop.service.impl;


//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.common.persistence.dao.StoreGoodsCollectMapper;
import com.ruoyi.app.common.persistence.dao.StoreGoodsMapper;
import com.ruoyi.app.common.persistence.model.StoreGoods;
import com.ruoyi.app.common.persistence.model.StoreGoodsCollect;
import com.ruoyi.app.modular.shop.service.IGoodsService;
import com.ruoyi.app.modular.shop.service.dto.GoodsDTO;
import com.ruoyi.app.modular.shop.service.mapper.GoodsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GoodsServiceImpl extends ServiceImpl<StoreGoodsMapper, StoreGoods>  implements IGoodsService {

    private final GoodsMapper goodsMapper;
    private final StoreGoodsMapper storeGoodsMapper;
    private final StoreGoodsCollectMapper storeGoodsCollectMapper;


    /**
     * 商品列表
     * @param cateId
     * @param page
     * @param limit
     * @param userId
     * @param keywords
     * @param order
     * @return
     */
    @Override
    public List<GoodsDTO> getList(int cateId, int page, int limit, int userId,
                                  String keywords,int order) {

        QueryWrapper<StoreGoods> wrapper = new QueryWrapper<>();

        if(cateId > 0){
            wrapper.eq("cate_id",cateId);
        }
        if(StrUtil.isNotEmpty(keywords)){
            wrapper.like("goods_name",keywords);
        }

        //todo order = 1 推荐  order=2  新品
        switch (order){
            case 1:
                wrapper.eq("is_recommend",1);
                break;
            case 2:
                wrapper.eq("is_new",1);
                break;
        }
        Page<StoreGoods> pageModel = new Page<>(page, limit);

        IPage<StoreGoods> pageList = storeGoodsMapper.selectPage(pageModel,wrapper);

        List<GoodsDTO> list = goodsMapper.toDto(pageList.getRecords());

        for (GoodsDTO goodsDTO : list) {
            goodsDTO.setIsCollect(isCollect(goodsDTO.getGoodsId(),userId));
        }

        return list;
        //return null;
    }

    /**
     * 收藏判断
     * @param goodsId
     * @param userId
     * @return
     */
    @Override
    public boolean isCollect(int goodsId, int userId) {
        QueryWrapper<StoreGoodsCollect> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_id",goodsId).eq("user_id",userId);
        StoreGoodsCollect goodsCollect = storeGoodsCollectMapper.selectOne(wrapper);
        if(ObjectUtil.isNotNull(goodsCollect)) return true;
        return false;
    }


    @Override
    public List<StoreGoods> collectGoods(int page,int limit,int userId) {
        System.out.println("userId"+userId);
        Page<StoreGoods> pageModel = new Page<>(page, limit);
        List<StoreGoods> list = baseMapper.collectGoods(pageModel,userId);
        //System.out.println(list);
        return list;
    }
}

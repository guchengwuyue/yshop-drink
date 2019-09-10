package com.ruoyi.app.modular.shop.controller;

import com.itmuch.lightsecurity.jwt.UserOperator;
import com.ruoyi.app.common.R;
import com.ruoyi.app.common.exception.BadRequestException;
import com.ruoyi.app.common.persistence.model.StoreSpecGoodsPrice;
import com.ruoyi.app.modular.shop.service.dto.CateDTO;
import com.ruoyi.app.modular.shop.service.dto.GoodsDTO;
import com.ruoyi.app.modular.shop.service.dto.SpecItemDTO;
import com.ruoyi.app.modular.shop.service.impl.CateServiceImpl;
import com.ruoyi.app.modular.shop.service.impl.GoodsServiceImpl;
import com.ruoyi.app.modular.shop.service.mapper.GoodsMapper;
import com.ruoyi.app.modular.shop.service.vo.CollectVO;
import com.ruoyi.app.modular.shop.service.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MallController
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/9/8
 **/

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "商城模块", tags = "商城模块", description = "商城模块")
public class MallController {
    private final CateServiceImpl cateService;
    private final GoodsServiceImpl goodsService;
    private final UserOperator userOperator;
    private final GoodsMapper goodsMapper;

    @GetMapping("/shop/mall-lists")
    @ApiOperation(value = "商品分类模块列表",notes = "商品分类模块列表")
    public R lists(@Validated @RequestBody PageVO pageVO){
        int userId = userOperator.getUser().getId();
        List<CateDTO> goodsCates = cateService.getList();
        List<GoodsDTO> recommedGoods = goodsService.getList(0,
                pageVO.getPage(),pageVO.getLimit(),userId,"",1);
        List<GoodsDTO> newGoods = goodsService.getList(0,pageVO.getPage(),
                pageVO.getLimit(),userId,"",2);

        List<GoodsDTO> collectGoods = goodsMapper.toDto(goodsService.collectGoods(pageVO.getPage(),pageVO.getLimit(),userId));

        Map<String,Object> map = new HashMap<>();
        map.put("goodsCates",goodsCates);
        map.put("recommedGoods",recommedGoods);
        map.put("newGoods",newGoods);
        map.put("collectGoods",collectGoods);
        return R.success(map);
    }

    @GetMapping("/shop/mall-goods-list")
    @ApiOperation(value = "获取商品列表",notes = "获取商品列表")
    public R goodsList(@RequestParam(value = "page",defaultValue = "0") int page,
                       @RequestParam(value = "limit",defaultValue = "10") int limit,
                       @RequestParam(value = "cat_id",defaultValue = "0") int catId,
                       @RequestParam(value = "keywords",defaultValue = "") String keywords){

        int userId = userOperator.getUser().getId();
        List<GoodsDTO> goods = goodsService.getList(catId,page,
                limit,userId,keywords,0);

        Map<String,Object> map = new HashMap<>();
        map.put("list",goods);
        return R.success(map);
    }

    @GetMapping("/shop/mall-goods-detail")
    @ApiOperation(value = "商品详情",notes = "商品详情")
    public R goodsDetail(@RequestParam(value = "goods_id",defaultValue = "0") int goodsId){
        Map<String, StoreSpecGoodsPrice> specPrice = goodsService.goodsSpecPrice(goodsId);
        GoodsDTO goodsDetail = goodsMapper.toDto(goodsService.getById(goodsId));
        Map<String, SpecItemDTO> specs = goodsService.goodsSpec(goodsId);

        Map<String,Object> map = new HashMap<>();
        map.put("detail",goodsDetail);
        map.put("spec_price",specPrice);
        map.put("specs",specs);
        return R.success(map);
    }

    @PostMapping("/shop/mall-add-collect")
    public R addCollect(@Validated @RequestBody CollectVO collectVO){
        int userId = userOperator.getUser().getId();
        //throw new BadRequestException("已经收藏过");
        goodsService.addOrCancelCollect(collectVO.getGoodsId(),userId,1);

        return null;
    }
}

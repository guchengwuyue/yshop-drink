/**
 * Copyright (C) 2018-2021
 * All rights reserved, Designed By www.yixiang.co
 * 注意：本软件为www.yixiang.co开发研制
 */
package co.yixiang.app.modular.shop.controller;

import co.yixiang.app.common.persistence.model.WechatNewsArticle;
import co.yixiang.app.modular.shop.service.IAdService;
import co.yixiang.app.modular.shop.service.IGoodsService;
import co.yixiang.app.modular.shop.service.INewsService;
import co.yixiang.app.modular.shop.service.dto.AdsDTO;
import co.yixiang.app.modular.shop.service.dto.GoodsDTO;
import co.yixiang.app.modular.shop.service.dto.NewsDTO;
import co.yixiang.app.common.R;
import co.yixiang.app.common.persistence.model.WechatNewsArticle;
import co.yixiang.app.modular.shop.service.IAdService;
import co.yixiang.app.modular.shop.service.IGoodsService;
import co.yixiang.app.modular.shop.service.INewsService;
import co.yixiang.app.modular.shop.service.dto.AdsDTO;
import co.yixiang.app.modular.shop.service.dto.GoodsDTO;
import co.yixiang.app.modular.shop.service.dto.NewsDTO;
import com.baomidou.shaun.core.annotation.HasRole;
import com.baomidou.shaun.core.context.ProfileHolder;
import com.baomidou.shaun.core.profile.TokenProfile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName IndexController
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/9/7
 **/

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "首页模块", tags = "首页模块", description = "首页模块")
public class IndexController {
    private final IGoodsService goodsService;
    private final INewsService newsService;
    private final IAdService adService;


    @GetMapping("/shop/index-list")
    @ApiOperation(value = "首页信息",notes = "首页信息")
    public R index(){
        int userId = 0;
        List<GoodsDTO> recommedGoods = goodsService.getList(0,1,3,userId,"",1);
        List<GoodsDTO> newGoods = goodsService.getList(0,1,6,userId,"",2);
        List<NewsDTO> newsList = newsService.getList(1,3);
        List<AdsDTO> ads = adService.getList();

        Map<String,Object> map = new HashMap<>();
        map.put("recommedGoods",recommedGoods);
        map.put("newGoods",newGoods);
        map.put("newsList",newsList);
        map.put("ads",ads);

        return R.success(map);
    }


    @GetMapping("/shop/news-lists")
    @ApiOperation(value = "新闻列表",notes = "新闻列表")
    public R news(@RequestParam(value = "page",defaultValue = "0") int page,
                  @RequestParam(value = "limit",defaultValue = "10") int limit){
        List<NewsDTO> newsList = newsService.getList(page,limit);
        return R.success(newsList);
    }

    @GetMapping("/shop/news-detail")
    @ApiOperation(value = "新闻详情",notes = "新闻详情")
    public R newsDetail(@RequestParam(value = "id",defaultValue = "0") int id){
        WechatNewsArticle newsArticle = newsService.getById(id);
        return R.success(newsArticle);
    }


}

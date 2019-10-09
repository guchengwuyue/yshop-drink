package com.ruoyi.app.modular.shop.controller;

import com.itmuch.lightsecurity.jwt.UserOperator;
import com.ruoyi.app.common.R;
import com.ruoyi.app.common.persistence.model.WechatNewsArticle;
import com.ruoyi.app.modular.shop.service.dto.AdsDTO;
import com.ruoyi.app.modular.shop.service.dto.GoodsDTO;
import com.ruoyi.app.modular.shop.service.dto.NewsDTO;
import com.ruoyi.app.modular.shop.service.impl.AdServiceImpl;
import com.ruoyi.app.modular.shop.service.impl.GoodsServiceImpl;
import com.ruoyi.app.modular.shop.service.impl.NewsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final UserOperator userOperator;
    private final GoodsServiceImpl goodsService;
    private final NewsServiceImpl newsService;
    private final AdServiceImpl adService;


    @GetMapping("/shop/index-list")
    @ApiOperation(value = "首页信息",notes = "首页信息")
    public R index(){
        //int userId = userOperator.getUser().getId();
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
        //return null;
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

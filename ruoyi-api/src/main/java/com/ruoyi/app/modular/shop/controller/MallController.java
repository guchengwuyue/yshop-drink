package com.ruoyi.app.modular.shop.controller;

import com.itmuch.lightsecurity.jwt.UserOperator;
import com.ruoyi.app.common.R;
import com.ruoyi.app.modular.shop.service.dto.CateDTO;
import com.ruoyi.app.modular.shop.service.dto.GoodsDTO;
import com.ruoyi.app.modular.shop.service.impl.CateServiceImpl;
import com.ruoyi.app.modular.shop.service.impl.GoodsServiceImpl;
import com.ruoyi.app.modular.shop.service.vo.PageVO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/shop/mall-lists")
    public R lists(@Validated @RequestBody PageVO pageVO){
        int userId = userOperator.getUser().getId();
        List<CateDTO> goodsCates = cateService.getList();
        List<GoodsDTO> recommedGoods = goodsService.getList(0,
                pageVO.getPage(),pageVO.getLimit(),userId,"",1);
        List<GoodsDTO> newGoods = goodsService.getList(0,pageVO.getPage(),
                pageVO.getLimit(),userId,"",2);

        goodsService.collectGoods(pageVO.getPage(),pageVO.getLimit(),userId);

        Map<String,Object> map = new HashMap<>();
        map.put("goodsCates",goodsCates);
        map.put("recommedGoods",recommedGoods);
        map.put("newGoods",newGoods);
        return R.success(map);
    }
}

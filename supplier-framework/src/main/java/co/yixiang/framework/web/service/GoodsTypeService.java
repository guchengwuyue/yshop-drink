package co.yixiang.framework.web.service;

import co.yixiang.shop.service.IStoreGoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName GoodsTypeService
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/8/24
 **/
@Service("goodstype")
public class GoodsTypeService {
    @Autowired
    private IStoreGoodsTypeService iStoreGoodsTypeService;

    /**
     * 获取name
     * @param id
     * @return
     */
    public String getName(Integer id){
        return iStoreGoodsTypeService.selectStoreGoodsTypeById(id).getName();
    }
}

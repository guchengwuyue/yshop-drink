package co.yixiang.yshop.module.product.service.storeproduct;

import co.yixiang.yshop.module.product.controller.app.category.vo.AppCategoryRespVO;
import co.yixiang.yshop.module.product.controller.app.product.param.AppStoreProductQueryParam;
import co.yixiang.yshop.module.product.controller.app.product.vo.AppProductVo;
import co.yixiang.yshop.module.product.controller.app.product.vo.AppStoreProductRespVo;
import co.yixiang.yshop.module.product.dal.dataobject.storeproduct.StoreProductDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 商品 AppService 接口
 *
 * @author yshop
 */
public interface AppStoreProductService extends IService<StoreProductDO> {

    /**
     * 商品列表
     * @param page 页码
     * @param limit 条数
     * @param order ProductEnum
     * @return List
     */
    List<AppStoreProductRespVo> getList(int page, int limit, int order);

    /**
     * 商品列表
     * @param productQueryParam AppStoreProductQueryParam
     * @return list
     */
    List<AppCategoryRespVO> getGoodsList(AppStoreProductQueryParam productQueryParam);


    /**
     * 返回普通商品库存
     * @param productId 商品id
     * @param unique sku唯一值
     * @return int
     */
    int getProductStock(Long productId, String unique,String type);

    /**
     * 获取单个商品
     * @param id 商品id
     * @return YxStoreProductQueryVo
     */
    AppStoreProductRespVo getStoreProductById(Long id);

    /**
     * 减少库存与增加销量
     * @param num 数量
     * @param productId 商品id
     * @param unique sku
     */
    void decProductStock(int num, Long productId, String unique,Long activityId,String type);

    /**
     * 增加库存 减少销量
     * @param num 数量
     * @param productId 商品id
     * @param unique sku唯一值
     */
    void incProductStock(Integer num, Long productId, String unique,Long activityId, String type);

    /**
     * 检测商品/秒杀/砍价/拼团库存
     * @param uid  用户ID
     * @param productId  产品ID
     * @param cartNum 购买数量
     * @param productAttrUnique  商品属性Unique
     */
    void checkProductStock(Long uid, Long productId, Integer cartNum, String productAttrUnique);


}

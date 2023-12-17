package co.yixiang.yshop.module.product.service.storeproductattr;

import co.yixiang.yshop.module.product.dal.dataobject.storeproductattr.StoreProductAttrDO;
import co.yixiang.yshop.module.product.service.storeproduct.dto.FromatDetailDto;
import co.yixiang.yshop.module.product.service.storeproduct.dto.ProductFormatDto;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商品属性 Service 接口
 *
 * @author yshop
 */
public interface AppStoreProductAttrService extends IService<StoreProductAttrDO>  {

    /**
     * 获取商品sku属性
     * @param productId 商品id
     * @return map
     */
    Map<String, Object> getProductAttrDetail(long productId);


}

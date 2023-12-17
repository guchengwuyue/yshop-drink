package co.yixiang.yshop.module.product.api.product;

import co.yixiang.yshop.framework.common.enums.ShopCommonEnum;
import co.yixiang.yshop.framework.common.exception.ErrorCode;
import co.yixiang.yshop.module.product.dal.dataobject.storeproduct.StoreProductDO;
import co.yixiang.yshop.module.product.enums.product.ProductTypeEnum;
import co.yixiang.yshop.module.product.service.storeproduct.AppStoreProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

import static co.yixiang.yshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static co.yixiang.yshop.module.product.enums.ErrorCodeConstants.STORE_PRODUCT_NOT_EXISTS;

@Service
public class ProductApiImpl implements ProductApi {
//
//    @Resource
//    private AppStoreProductService appStoreProductService;
//    /**
//     * 检测商品库存
//     *
//     * @param uid               用户ID
//     * @param productId         产品ID
//     * @param cartNum           购买数量
//     * @param productAttrUnique 商品属性Unique
//     * @param combinationId     拼团产品ID
//     */
//   @Override
//   public void checkProductStock(Long uid, Long productId, Integer cartNum, String productAttrUnique) {
//        StoreProductDO product = appStoreProductService
//                .lambdaQuery().eq(StoreProductDO::getId, productId)
//                .eq(StoreProductDO::getIsShow, ShopCommonEnum.SHOW_1.getValue())
//                .one();
//        if (product == null) {
//            throw exception(STORE_PRODUCT_NOT_EXISTS);
//        }
//
//        int stock = appStoreProductService.getProductStock(productId, productAttrUnique, ProductTypeEnum.PINK.getValue());
//        if (stock < cartNum) {
//            throw exception(new ErrorCode(1008003010, product.getStoreName() + "库存不足" + cartNum));
//        }
//
//    }

}

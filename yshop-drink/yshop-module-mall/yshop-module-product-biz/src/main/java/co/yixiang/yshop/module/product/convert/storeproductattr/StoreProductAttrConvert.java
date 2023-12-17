package co.yixiang.yshop.module.product.convert.storeproductattr;

import co.yixiang.yshop.module.product.controller.admin.storeproduct.vo.StoreProductRespVO;
import co.yixiang.yshop.module.product.controller.app.product.vo.AppStoreProductAttrQueryVo;
import co.yixiang.yshop.module.product.dal.dataobject.storeproduct.StoreProductDO;
import co.yixiang.yshop.module.product.dal.dataobject.storeproductattr.StoreProductAttrDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 商品属性 Convert
 *
 * @author yshop
 */
@Mapper
public interface StoreProductAttrConvert {

    StoreProductAttrConvert INSTANCE = Mappers.getMapper(StoreProductAttrConvert.class);

    AppStoreProductAttrQueryVo convert(StoreProductAttrDO bean);
    

}

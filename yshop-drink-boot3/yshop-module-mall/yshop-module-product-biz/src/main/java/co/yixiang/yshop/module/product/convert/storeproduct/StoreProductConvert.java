package co.yixiang.yshop.module.product.convert.storeproduct;

import java.util.*;

import co.yixiang.yshop.framework.common.pojo.PageResult;

import co.yixiang.yshop.module.product.controller.app.product.vo.AppStoreProductRespVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import co.yixiang.yshop.module.product.controller.admin.storeproduct.vo.*;
import co.yixiang.yshop.module.product.dal.dataobject.storeproduct.StoreProductDO;

/**
 * 商品 Convert
 *
 * @author yshop
 */
@Mapper
public interface StoreProductConvert {

    StoreProductConvert INSTANCE = Mappers.getMapper(StoreProductConvert.class);

    StoreProductDO convert(StoreProductCreateReqVO bean);

    StoreProductDO convert(StoreProductUpdateReqVO bean);

    StoreProductRespVO convert(StoreProductDO bean);

    AppStoreProductRespVo convert01(StoreProductDO bean);

    List<StoreProductRespVO> convertList(List<StoreProductDO> list);

    PageResult<StoreProductRespVO> convertPage(PageResult<StoreProductDO> page);

    List<StoreProductExcelVO> convertList02(List<StoreProductDO> list);

    List<AppStoreProductRespVo> convertList03(List<StoreProductDO> list);
}

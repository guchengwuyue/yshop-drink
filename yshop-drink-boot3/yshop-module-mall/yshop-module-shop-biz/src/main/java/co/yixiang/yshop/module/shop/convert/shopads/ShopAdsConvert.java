package co.yixiang.yshop.module.shop.convert.shopads;

import java.util.*;

import co.yixiang.yshop.framework.common.pojo.PageResult;

import co.yixiang.yshop.module.shop.controller.app.ad.vo.AppShopAdsVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import co.yixiang.yshop.module.shop.controller.admin.shopads.vo.*;
import co.yixiang.yshop.module.shop.dal.dataobject.shopads.ShopAdsDO;

/**
 * 广告图管理 Convert
 *
 * @author yshop
 */
@Mapper
public interface ShopAdsConvert {

    ShopAdsConvert INSTANCE = Mappers.getMapper(ShopAdsConvert.class);

    ShopAdsDO convert(ShopAdsCreateReqVO bean);

    ShopAdsDO convert(ShopAdsUpdateReqVO bean);

    ShopAdsRespVO convert(ShopAdsDO bean);

    List<ShopAdsRespVO> convertList(List<ShopAdsDO> list);

    List<AppShopAdsVO> convertList03(List<ShopAdsDO> list);

    PageResult<ShopAdsRespVO> convertPage(PageResult<ShopAdsDO> page);

    List<ShopAdsExcelVO> convertList02(List<ShopAdsDO> list);

}

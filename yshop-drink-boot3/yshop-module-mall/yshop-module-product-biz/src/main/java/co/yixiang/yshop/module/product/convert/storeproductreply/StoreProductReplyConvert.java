package co.yixiang.yshop.module.product.convert.storeproductreply;

import java.util.*;

import co.yixiang.yshop.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import co.yixiang.yshop.module.product.controller.admin.storeproductreply.vo.*;
import co.yixiang.yshop.module.product.dal.dataobject.storeproductreply.StoreProductReplyDO;

/**
 * 评论 Convert
 *
 * @author yshop
 */
@Mapper
public interface StoreProductReplyConvert {

    StoreProductReplyConvert INSTANCE = Mappers.getMapper(StoreProductReplyConvert.class);


    StoreProductReplyDO convert(StoreProductReplyUpdateReqVO bean);

    StoreProductReplyRespVO convert(StoreProductReplyDO bean);

    List<StoreProductReplyRespVO> convertList(List<StoreProductReplyDO> list);

    PageResult<StoreProductReplyRespVO> convertPage(PageResult<StoreProductReplyDO> page);


}

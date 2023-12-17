package co.yixiang.yshop.module.coupon.dal.mysql.coupon;

import java.util.*;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import co.yixiang.yshop.framework.mybatis.core.mapper.BaseMapperX;
import co.yixiang.yshop.framework.security.core.util.SecurityFrameworkUtils;
import co.yixiang.yshop.module.coupon.dal.dataobject.coupon.CouponDO;
import org.apache.ibatis.annotations.Mapper;
import co.yixiang.yshop.module.coupon.controller.admin.coupon.vo.*;

/**
 * 优惠券 Mapper
 *
 * @author yshop
 */
@Mapper
public interface CouponMapper extends BaseMapperX<CouponDO> {

    default PageResult<CouponDO> selectPage(CouponPageReqVO reqVO) {
        Long shopId = SecurityFrameworkUtils.getLoginUser().getShopId();
        if(shopId == 0) {
            reqVO.setShopId(null);
        }else {
            reqVO.setShopId(shopId.toString());
        }
        return selectPage(reqVO, new LambdaQueryWrapperX<CouponDO>()
                .eqIfPresent(CouponDO::getShopId, reqVO.getShopId())
                .likeIfPresent(CouponDO::getShopName, reqVO.getShopName())
                .eqIfPresent(CouponDO::getTitle, reqVO.getTitle())
                .orderByDesc(CouponDO::getId));
    }

    default List<CouponDO> selectList(CouponExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CouponDO>()
                .eqIfPresent(CouponDO::getShopId, reqVO.getShopId())
                .likeIfPresent(CouponDO::getShopName, reqVO.getShopName())
                .eqIfPresent(CouponDO::getTitle, reqVO.getTitle())
                .eqIfPresent(CouponDO::getIsSwitch, reqVO.getIsSwitch())
                .eqIfPresent(CouponDO::getLeast, reqVO.getLeast())
                .eqIfPresent(CouponDO::getValue, reqVO.getValue())
                .betweenIfPresent(CouponDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(CouponDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(CouponDO::getWeigh, reqVO.getWeigh())
                .eqIfPresent(CouponDO::getType, reqVO.getType())
                .eqIfPresent(CouponDO::getExchangeCode, reqVO.getExchangeCode())
                .eqIfPresent(CouponDO::getReceive, reqVO.getReceive())
                .eqIfPresent(CouponDO::getDistribute, reqVO.getDistribute())
                .eqIfPresent(CouponDO::getScore, reqVO.getScore())
                .eqIfPresent(CouponDO::getInstructions, reqVO.getInstructions())
                .eqIfPresent(CouponDO::getImage, reqVO.getImage())
                .eqIfPresent(CouponDO::getLimit, reqVO.getLimit())
                .orderByDesc(CouponDO::getId));
    }

}

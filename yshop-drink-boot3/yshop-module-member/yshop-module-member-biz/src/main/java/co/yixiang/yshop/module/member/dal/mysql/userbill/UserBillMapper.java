package co.yixiang.yshop.module.member.dal.mysql.userbill;

import java.util.*;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import co.yixiang.yshop.framework.mybatis.core.mapper.BaseMapperX;
import co.yixiang.yshop.module.member.dal.dataobject.userbill.UserBillDO;
import org.apache.ibatis.annotations.Mapper;
import co.yixiang.yshop.module.member.controller.admin.userbill.vo.*;

/**
 * 用户账单 Mapper
 *
 * @author yshop
 */
@Mapper
public interface UserBillMapper extends BaseMapperX<UserBillDO> {

    default PageResult<UserBillDO> selectPage(UserBillPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserBillDO>()
                .eqIfPresent(UserBillDO::getUid, reqVO.getUid())
                .eqIfPresent(UserBillDO::getLinkId, reqVO.getLinkId())
                .eqIfPresent(UserBillDO::getPm, reqVO.getPm())
                .eqIfPresent(UserBillDO::getTitle, reqVO.getTitle())
                .eqIfPresent(UserBillDO::getCategory, reqVO.getCategory())
                .eqIfPresent(UserBillDO::getType, reqVO.getType())
                .eqIfPresent(UserBillDO::getNumber, reqVO.getNumber())
                .eqIfPresent(UserBillDO::getBalance, reqVO.getBalance())
                .eqIfPresent(UserBillDO::getMark, reqVO.getMark())
                .betweenIfPresent(UserBillDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(UserBillDO::getStatus, reqVO.getStatus())
                .orderByDesc(UserBillDO::getId));
    }

    default List<UserBillDO> selectList(UserBillExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<UserBillDO>()
                .eqIfPresent(UserBillDO::getUid, reqVO.getUid())
                .eqIfPresent(UserBillDO::getLinkId, reqVO.getLinkId())
                .eqIfPresent(UserBillDO::getPm, reqVO.getPm())
                .eqIfPresent(UserBillDO::getTitle, reqVO.getTitle())
                .eqIfPresent(UserBillDO::getCategory, reqVO.getCategory())
                .eqIfPresent(UserBillDO::getType, reqVO.getType())
                .eqIfPresent(UserBillDO::getNumber, reqVO.getNumber())
                .eqIfPresent(UserBillDO::getBalance, reqVO.getBalance())
                .eqIfPresent(UserBillDO::getMark, reqVO.getMark())
                .betweenIfPresent(UserBillDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(UserBillDO::getStatus, reqVO.getStatus())
                .orderByDesc(UserBillDO::getId));
    }

}

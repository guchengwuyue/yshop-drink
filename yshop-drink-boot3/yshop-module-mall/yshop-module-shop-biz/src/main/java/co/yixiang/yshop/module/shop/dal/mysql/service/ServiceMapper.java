package co.yixiang.yshop.module.shop.dal.mysql.service;

import java.util.*;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import co.yixiang.yshop.framework.mybatis.core.mapper.BaseMapperX;
import co.yixiang.yshop.module.shop.dal.dataobject.service.ServiceDO;
import org.apache.ibatis.annotations.Mapper;
import co.yixiang.yshop.module.shop.controller.admin.service.vo.*;

/**
 * 我的服务 Mapper
 *
 * @author yshop
 */
@Mapper
public interface ServiceMapper extends BaseMapperX<ServiceDO> {

    default PageResult<ServiceDO> selectPage(ServicePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ServiceDO>()
                .likeIfPresent(ServiceDO::getName, reqVO.getName())
                .eqIfPresent(ServiceDO::getImage, reqVO.getImage())
                .eqIfPresent(ServiceDO::getType, reqVO.getType())
                .eqIfPresent(ServiceDO::getContent, reqVO.getContent())
                .eqIfPresent(ServiceDO::getPid, reqVO.getPid())
                .eqIfPresent(ServiceDO::getAppId, reqVO.getAppId())
                .eqIfPresent(ServiceDO::getPages, reqVO.getPages())
                .eqIfPresent(ServiceDO::getPhone, reqVO.getPhone())
                .eqIfPresent(ServiceDO::getWeigh, reqVO.getWeigh())
                .eqIfPresent(ServiceDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ServiceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ServiceDO::getId));
    }

    default List<ServiceDO> selectList(ServiceExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ServiceDO>()
                .likeIfPresent(ServiceDO::getName, reqVO.getName())
                .eqIfPresent(ServiceDO::getImage, reqVO.getImage())
                .eqIfPresent(ServiceDO::getType, reqVO.getType())
                .eqIfPresent(ServiceDO::getContent, reqVO.getContent())
                .eqIfPresent(ServiceDO::getPid, reqVO.getPid())
                .eqIfPresent(ServiceDO::getAppId, reqVO.getAppId())
                .eqIfPresent(ServiceDO::getPages, reqVO.getPages())
                .eqIfPresent(ServiceDO::getPhone, reqVO.getPhone())
                .eqIfPresent(ServiceDO::getWeigh, reqVO.getWeigh())
                .eqIfPresent(ServiceDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ServiceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ServiceDO::getId));
    }

}

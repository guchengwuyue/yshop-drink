package co.yixiang.yshop.module.system.dal.mysql.logger;

import co.yixiang.yshop.framework.common.exception.enums.GlobalErrorCodeConstants;
import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.framework.mybatis.core.mapper.BaseMapperX;
import co.yixiang.yshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import co.yixiang.yshop.module.system.controller.admin.logger.vo.operatelog.OperateLogExportReqVO;
import co.yixiang.yshop.module.system.controller.admin.logger.vo.operatelog.OperateLogPageReqVO;
import co.yixiang.yshop.module.system.dal.dataobject.logger.OperateLogDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface OperateLogMapper extends BaseMapperX<OperateLogDO> {

    default PageResult<OperateLogDO> selectPage(OperateLogPageReqVO reqVO, Collection<Long> userIds) {
        LambdaQueryWrapperX<OperateLogDO> query = new LambdaQueryWrapperX<OperateLogDO>()
                .likeIfPresent(OperateLogDO::getModule, reqVO.getModule())
                .inIfPresent(OperateLogDO::getUserId, userIds)
                .eqIfPresent(OperateLogDO::getType, reqVO.getType())
                .betweenIfPresent(OperateLogDO::getStartTime, reqVO.getStartTime());
        if (Boolean.TRUE.equals(reqVO.getSuccess())) {
            query.eq(OperateLogDO::getResultCode, GlobalErrorCodeConstants.SUCCESS.getCode());
        } else if (Boolean.FALSE.equals(reqVO.getSuccess())) {
            query.gt(OperateLogDO::getResultCode, GlobalErrorCodeConstants.SUCCESS.getCode());
        }
        query.orderByDesc(OperateLogDO::getId); // 降序
        return selectPage(reqVO, query);
    }

    default List<OperateLogDO> selectList(OperateLogExportReqVO reqVO, Collection<Long> userIds) {
        LambdaQueryWrapperX<OperateLogDO> query = new LambdaQueryWrapperX<OperateLogDO>()
                .likeIfPresent(OperateLogDO::getModule, reqVO.getModule())
                .inIfPresent(OperateLogDO::getUserId, userIds)
                .eqIfPresent(OperateLogDO::getType, reqVO.getType())
                .betweenIfPresent(OperateLogDO::getStartTime, reqVO.getStartTime());
        if (Boolean.TRUE.equals(reqVO.getSuccess())) {
            query.eq(OperateLogDO::getResultCode, GlobalErrorCodeConstants.SUCCESS.getCode());
        } else if (Boolean.FALSE.equals(reqVO.getSuccess())) {
            query.gt(OperateLogDO::getResultCode, GlobalErrorCodeConstants.SUCCESS.getCode());
        }
        query.orderByDesc(OperateLogDO::getId); // 降序
        return selectList(query);
    }

}

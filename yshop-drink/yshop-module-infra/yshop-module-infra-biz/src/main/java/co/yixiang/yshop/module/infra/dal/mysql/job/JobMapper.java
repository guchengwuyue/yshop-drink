package co.yixiang.yshop.module.infra.dal.mysql.job;

import co.yixiang.yshop.module.infra.controller.admin.job.vo.job.JobExportReqVO;
import co.yixiang.yshop.module.infra.controller.admin.job.vo.job.JobPageReqVO;
import co.yixiang.yshop.module.infra.dal.dataobject.job.JobDO;
import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.framework.mybatis.core.mapper.BaseMapperX;
import co.yixiang.yshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 定时任务 Mapper
 *
 * @author yshop
 */
@Mapper
public interface JobMapper extends BaseMapperX<JobDO> {

    default JobDO selectByHandlerName(String handlerName) {
        return selectOne(JobDO::getHandlerName, handlerName);
    }

    default PageResult<JobDO> selectPage(JobPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<JobDO>()
                .likeIfPresent(JobDO::getName, reqVO.getName())
                .eqIfPresent(JobDO::getStatus, reqVO.getStatus())
                .likeIfPresent(JobDO::getHandlerName, reqVO.getHandlerName())
        );
    }

    default List<JobDO> selectList(JobExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<JobDO>()
                .likeIfPresent(JobDO::getName, reqVO.getName())
                .eqIfPresent(JobDO::getStatus, reqVO.getStatus())
                .likeIfPresent(JobDO::getHandlerName, reqVO.getHandlerName())
        );
    }

}

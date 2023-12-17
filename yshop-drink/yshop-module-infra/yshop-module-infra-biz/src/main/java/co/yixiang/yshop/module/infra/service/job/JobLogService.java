package co.yixiang.yshop.module.infra.service.job;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.framework.quartz.core.service.JobLogFrameworkService;
import co.yixiang.yshop.module.infra.controller.admin.job.vo.log.JobLogExportReqVO;
import co.yixiang.yshop.module.infra.controller.admin.job.vo.log.JobLogPageReqVO;
import co.yixiang.yshop.module.infra.dal.dataobject.job.JobLogDO;

import java.util.Collection;
import java.util.List;

/**
 * Job 日志 Service 接口
 *
 * @author yshop
 */
public interface JobLogService extends JobLogFrameworkService {

    /**
     * 获得定时任务
     *
     * @param id 编号
     * @return 定时任务
     */
    JobLogDO getJobLog(Long id);

    /**
     * 获得定时任务列表
     *
     * @param ids 编号
     * @return 定时任务列表
     */
    List<JobLogDO> getJobLogList(Collection<Long> ids);

    /**
     * 获得定时任务分页
     *
     * @param pageReqVO 分页查询
     * @return 定时任务分页
     */
    PageResult<JobLogDO> getJobLogPage(JobLogPageReqVO pageReqVO);

    /**
     * 获得定时任务列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 定时任务分页
     */
    List<JobLogDO> getJobLogList(JobLogExportReqVO exportReqVO);

}

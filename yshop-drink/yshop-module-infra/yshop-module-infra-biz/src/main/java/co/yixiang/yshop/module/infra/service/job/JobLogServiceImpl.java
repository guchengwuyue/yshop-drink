package co.yixiang.yshop.module.infra.service.job;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.infra.controller.admin.job.vo.log.JobLogExportReqVO;
import co.yixiang.yshop.module.infra.controller.admin.job.vo.log.JobLogPageReqVO;
import co.yixiang.yshop.module.infra.dal.dataobject.job.JobLogDO;
import co.yixiang.yshop.module.infra.dal.mysql.job.JobLogMapper;
import co.yixiang.yshop.module.infra.enums.job.JobLogStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Job 日志 Service 实现类
 *
 * @author yshop
 */
@Service
@Validated
@Slf4j
public class JobLogServiceImpl implements JobLogService {

    @Resource
    private JobLogMapper jobLogMapper;

    @Override
    public Long createJobLog(Long jobId, LocalDateTime beginTime, String jobHandlerName, String jobHandlerParam, Integer executeIndex) {
        JobLogDO log = JobLogDO.builder().jobId(jobId).handlerName(jobHandlerName).handlerParam(jobHandlerParam).executeIndex(executeIndex)
                .beginTime(beginTime).status(JobLogStatusEnum.RUNNING.getStatus()).build();
        jobLogMapper.insert(log);
        return log.getId();
    }

    @Override
    @Async
    public void updateJobLogResultAsync(Long logId, LocalDateTime endTime, Integer duration, boolean success, String result) {
        try {
            JobLogDO updateObj = JobLogDO.builder().id(logId).endTime(endTime).duration(duration)
                    .status(success ? JobLogStatusEnum.SUCCESS.getStatus() : JobLogStatusEnum.FAILURE.getStatus()).result(result).build();
            jobLogMapper.updateById(updateObj);
        } catch (Exception ex) {
            log.error("[updateJobLogResultAsync][logId({}) endTime({}) duration({}) success({}) result({})]",
                    logId, endTime, duration, success, result);
        }
    }

    @Override
    public JobLogDO getJobLog(Long id) {
        return jobLogMapper.selectById(id);
    }

    @Override
    public List<JobLogDO> getJobLogList(Collection<Long> ids) {
        return jobLogMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<JobLogDO> getJobLogPage(JobLogPageReqVO pageReqVO) {
        return jobLogMapper.selectPage(pageReqVO);
    }

    @Override
    public List<JobLogDO> getJobLogList(JobLogExportReqVO exportReqVO) {
        return jobLogMapper.selectList(exportReqVO);
    }

}

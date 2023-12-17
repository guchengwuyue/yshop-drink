package co.yixiang.yshop.module.infra.service.logger;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.infra.api.logger.dto.ApiErrorLogCreateReqDTO;
import co.yixiang.yshop.module.infra.controller.admin.logger.vo.apierrorlog.ApiErrorLogExportReqVO;
import co.yixiang.yshop.module.infra.controller.admin.logger.vo.apierrorlog.ApiErrorLogPageReqVO;
import co.yixiang.yshop.module.infra.dal.dataobject.logger.ApiErrorLogDO;

import java.util.List;

/**
 * API 错误日志 Service 接口
 *
 * @author yshop
 */
public interface ApiErrorLogService {

    /**
     * 创建 API 错误日志
     *
     * @param createReqDTO API 错误日志
     */
    void createApiErrorLog(ApiErrorLogCreateReqDTO createReqDTO);

    /**
     * 获得 API 错误日志分页
     *
     * @param pageReqVO 分页查询
     * @return API 错误日志分页
     */
    PageResult<ApiErrorLogDO> getApiErrorLogPage(ApiErrorLogPageReqVO pageReqVO);

    /**
     * 获得 API 错误日志列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return API 错误日志分页
     */
    List<ApiErrorLogDO> getApiErrorLogList(ApiErrorLogExportReqVO exportReqVO);

    /**
     * 更新 API 错误日志已处理
     *
     * @param id API 日志编号
     * @param processStatus 处理结果
     * @param processUserId 处理人
     */
    void updateApiErrorLogProcess(Long id, Integer processStatus, Long processUserId);

}

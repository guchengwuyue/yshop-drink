package co.yixiang.yshop.framework.apilog.core.service;

import cn.hutool.core.bean.BeanUtil;
import co.yixiang.yshop.module.infra.api.logger.ApiAccessLogApi;
import co.yixiang.yshop.module.infra.api.logger.dto.ApiAccessLogCreateReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

/**
 * API 访问日志 Framework Service 实现类
 *
 * 基于 {@link ApiAccessLogApi} 服务，记录访问日志
 *
 * @author yshop
 */
@RequiredArgsConstructor
public class ApiAccessLogFrameworkServiceImpl implements ApiAccessLogFrameworkService {

    private final ApiAccessLogApi apiAccessLogApi;

    @Override
    @Async
    public void createApiAccessLog(ApiAccessLog apiAccessLog) {
        ApiAccessLogCreateReqDTO reqDTO = BeanUtil.copyProperties(apiAccessLog, ApiAccessLogCreateReqDTO.class);
        apiAccessLogApi.createApiAccessLog(reqDTO);
    }

}

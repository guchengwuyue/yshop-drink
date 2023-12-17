package co.yixiang.yshop.module.system.api.logger;

import co.yixiang.yshop.module.system.api.logger.dto.OperateLogCreateReqDTO;
import co.yixiang.yshop.module.system.service.logger.OperateLogService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * 操作日志 API 实现类
 *
 * @author yshop
 */
@Service
@Validated
public class OperateLogApiImpl implements OperateLogApi {

    @Resource
    private OperateLogService operateLogService;

    @Override
    public void createOperateLog(OperateLogCreateReqDTO createReqDTO) {
        operateLogService.createOperateLog(createReqDTO);
    }

}

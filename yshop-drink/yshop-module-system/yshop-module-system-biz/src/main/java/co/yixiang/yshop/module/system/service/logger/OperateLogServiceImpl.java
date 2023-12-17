package co.yixiang.yshop.module.system.service.logger;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.framework.common.util.string.StrUtils;
import co.yixiang.yshop.module.system.api.logger.dto.OperateLogCreateReqDTO;
import co.yixiang.yshop.module.system.controller.admin.logger.vo.operatelog.OperateLogExportReqVO;
import co.yixiang.yshop.module.system.controller.admin.logger.vo.operatelog.OperateLogPageReqVO;
import co.yixiang.yshop.module.system.convert.logger.OperateLogConvert;
import co.yixiang.yshop.module.system.dal.dataobject.logger.OperateLogDO;
import co.yixiang.yshop.module.system.dal.dataobject.user.AdminUserDO;
import co.yixiang.yshop.module.system.dal.mysql.logger.OperateLogMapper;
import co.yixiang.yshop.module.system.service.user.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static co.yixiang.yshop.framework.common.util.collection.CollectionUtils.convertSet;
import static co.yixiang.yshop.module.system.dal.dataobject.logger.OperateLogDO.JAVA_METHOD_ARGS_MAX_LENGTH;
import static co.yixiang.yshop.module.system.dal.dataobject.logger.OperateLogDO.RESULT_MAX_LENGTH;

@Service
@Validated
@Slf4j
public class OperateLogServiceImpl implements OperateLogService {

    @Resource
    private OperateLogMapper operateLogMapper;

    @Resource
    private AdminUserService userService;

    @Override
    public void createOperateLog(OperateLogCreateReqDTO createReqDTO) {
        OperateLogDO logDO = OperateLogConvert.INSTANCE.convert(createReqDTO);
        logDO.setJavaMethodArgs(StrUtils.maxLength(logDO.getJavaMethodArgs(), JAVA_METHOD_ARGS_MAX_LENGTH));
        logDO.setResultData(StrUtils.maxLength(logDO.getResultData(), RESULT_MAX_LENGTH));
        operateLogMapper.insert(logDO);
    }

    @Override
    public PageResult<OperateLogDO> getOperateLogPage(OperateLogPageReqVO reqVO) {
        // 处理基于用户昵称的查询
        Collection<Long> userIds = null;
        if (StrUtil.isNotEmpty(reqVO.getUserNickname())) {
            userIds = convertSet(userService.getUserListByNickname(reqVO.getUserNickname()), AdminUserDO::getId);
            if (CollUtil.isEmpty(userIds)) {
                return PageResult.empty();
            }
        }
        // 查询分页
        return operateLogMapper.selectPage(reqVO, userIds);
    }

    @Override
    public List<OperateLogDO> getOperateLogList(OperateLogExportReqVO reqVO) {
        // 处理基于用户昵称的查询
        Collection<Long> userIds = null;
        if (StrUtil.isNotEmpty(reqVO.getUserNickname())) {
            userIds = convertSet(userService.getUserListByNickname(reqVO.getUserNickname()), AdminUserDO::getId);
            if (CollUtil.isEmpty(userIds)) {
                return Collections.emptyList();
            }
        }
        // 查询列表
        return operateLogMapper.selectList(reqVO, userIds);
    }

}

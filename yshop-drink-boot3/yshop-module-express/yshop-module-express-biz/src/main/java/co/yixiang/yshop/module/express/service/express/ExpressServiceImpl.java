package co.yixiang.yshop.module.express.service.express;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.express.controller.admin.express.vo.ExpressCreateReqVO;
import co.yixiang.yshop.module.express.controller.admin.express.vo.ExpressExportReqVO;
import co.yixiang.yshop.module.express.controller.admin.express.vo.ExpressPageReqVO;
import co.yixiang.yshop.module.express.controller.admin.express.vo.ExpressUpdateReqVO;
import co.yixiang.yshop.module.express.convert.express.ExpressConvert;
import co.yixiang.yshop.module.express.dal.dataobject.express.ExpressDO;
import co.yixiang.yshop.module.express.dal.mysql.express.ExpressMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.List;

import static co.yixiang.yshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static co.yixiang.yshop.module.express.enums.ErrorCodeConstants.EXPRESS_NOT_EXISTS;


/**
 * 快递公司 Service 实现类
 *
 * @author yshop
 */
@Service
@Validated
public class ExpressServiceImpl implements ExpressService {

    @Resource
    private ExpressMapper expressMapper;

    @Override
    public Integer createExpress(ExpressCreateReqVO createReqVO) {
        // 插入
        ExpressDO express = ExpressConvert.INSTANCE.convert(createReqVO);
        expressMapper.insert(express);
        // 返回
        return express.getId();
    }

    @Override
    public void updateExpress(ExpressUpdateReqVO updateReqVO) {
        // 校验存在
        validateExpressExists(updateReqVO.getId());
        // 更新
        ExpressDO updateObj = ExpressConvert.INSTANCE.convert(updateReqVO);
        expressMapper.updateById(updateObj);
    }

    @Override
    public void deleteExpress(Integer id) {
        // 校验存在
        validateExpressExists(id);
        // 删除
        expressMapper.deleteById(id);
    }

    private void validateExpressExists(Integer id) {
        if (expressMapper.selectById(id) == null) {
            throw exception(EXPRESS_NOT_EXISTS);
        }
    }

    @Override
    public ExpressDO getExpress(Integer id) {
        return expressMapper.selectById(id);
    }

    @Override
    public List<ExpressDO> getExpressList() {
        return expressMapper.selectList();
    }

    @Override
    public PageResult<ExpressDO> getExpressPage(ExpressPageReqVO pageReqVO) {
        return expressMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ExpressDO> getExpressList(ExpressExportReqVO exportReqVO) {
        return expressMapper.selectList(exportReqVO);
    }

}

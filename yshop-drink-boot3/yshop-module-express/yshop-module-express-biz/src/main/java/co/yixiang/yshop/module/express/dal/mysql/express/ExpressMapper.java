package co.yixiang.yshop.module.express.dal.mysql.express;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.framework.mybatis.core.mapper.BaseMapperX;
import co.yixiang.yshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import co.yixiang.yshop.module.express.controller.admin.express.vo.ExpressExportReqVO;
import co.yixiang.yshop.module.express.controller.admin.express.vo.ExpressPageReqVO;
import co.yixiang.yshop.module.express.dal.dataobject.express.ExpressDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 快递公司 Mapper
 *
 * @author yshop
 */
@Mapper
public interface ExpressMapper extends BaseMapperX<ExpressDO> {

    default PageResult<ExpressDO> selectPage(ExpressPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ExpressDO>()
                .eqIfPresent(ExpressDO::getCode, reqVO.getCode())
                .likeIfPresent(ExpressDO::getName, reqVO.getName())
                .orderByDesc(ExpressDO::getId));
    }

    default List<ExpressDO> selectList(ExpressExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ExpressDO>()
                .eqIfPresent(ExpressDO::getCode, reqVO.getCode())
                .likeIfPresent(ExpressDO::getName, reqVO.getName())
                .orderByDesc(ExpressDO::getId));
    }

}

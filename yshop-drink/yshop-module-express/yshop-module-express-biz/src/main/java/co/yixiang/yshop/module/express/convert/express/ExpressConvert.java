package co.yixiang.yshop.module.express.convert.express;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.express.controller.admin.express.vo.ExpressCreateReqVO;
import co.yixiang.yshop.module.express.controller.admin.express.vo.ExpressExcelVO;
import co.yixiang.yshop.module.express.controller.admin.express.vo.ExpressRespVO;
import co.yixiang.yshop.module.express.controller.admin.express.vo.ExpressUpdateReqVO;
import co.yixiang.yshop.module.express.dal.dataobject.express.ExpressDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 快递公司 Convert
 *
 * @author yshop
 */
@Mapper
public interface ExpressConvert {

    ExpressConvert INSTANCE = Mappers.getMapper(ExpressConvert.class);

    ExpressDO convert(ExpressCreateReqVO bean);

    ExpressDO convert(ExpressUpdateReqVO bean);

    ExpressRespVO convert(ExpressDO bean);

    List<ExpressRespVO> convertList(List<ExpressDO> list);

    PageResult<ExpressRespVO> convertPage(PageResult<ExpressDO> page);

    List<ExpressExcelVO> convertList02(List<ExpressDO> list);

}

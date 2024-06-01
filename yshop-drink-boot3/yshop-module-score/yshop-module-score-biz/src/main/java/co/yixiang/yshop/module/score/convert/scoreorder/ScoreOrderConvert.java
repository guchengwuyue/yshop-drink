package co.yixiang.yshop.module.score.convert.scoreorder;

import java.util.*;

import co.yixiang.yshop.framework.common.pojo.PageResult;

import co.yixiang.yshop.module.score.controller.app.order.vo.AppScoreOrderVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import co.yixiang.yshop.module.score.controller.admin.scoreorder.vo.*;
import co.yixiang.yshop.module.score.dal.dataobject.scoreorder.ScoreOrderDO;

/**
 * 积分商城订单 Convert
 *
 * @author yshop
 */
@Mapper
public interface ScoreOrderConvert {

    ScoreOrderConvert INSTANCE = Mappers.getMapper(ScoreOrderConvert.class);

    ScoreOrderDO convert(ScoreOrderCreateReqVO bean);

    ScoreOrderDO convert(ScoreOrderUpdateReqVO bean);

    ScoreOrderRespVO convert(ScoreOrderDO bean);

    AppScoreOrderVO convert01(ScoreOrderDO bean);

    List<ScoreOrderRespVO> convertList(List<ScoreOrderDO> list);

    List<AppScoreOrderVO> convertList01(List<ScoreOrderDO> list);

    PageResult<ScoreOrderRespVO> convertPage(PageResult<ScoreOrderDO> page);

    List<ScoreOrderExcelVO> convertList02(List<ScoreOrderDO> list);

}

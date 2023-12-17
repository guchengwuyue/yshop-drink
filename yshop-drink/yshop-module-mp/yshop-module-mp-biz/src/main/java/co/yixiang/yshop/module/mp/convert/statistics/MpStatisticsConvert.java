package co.yixiang.yshop.module.mp.convert.statistics;

import co.yixiang.yshop.module.mp.controller.admin.statistics.vo.MpStatisticsInterfaceSummaryRespVO;
import co.yixiang.yshop.module.mp.controller.admin.statistics.vo.MpStatisticsUpstreamMessageRespVO;
import co.yixiang.yshop.module.mp.controller.admin.statistics.vo.MpStatisticsUserCumulateRespVO;
import co.yixiang.yshop.module.mp.controller.admin.statistics.vo.MpStatisticsUserSummaryRespVO;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeInterfaceResult;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeMsgResult;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeUserCumulate;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeUserSummary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MpStatisticsConvert {

    MpStatisticsConvert INSTANCE = Mappers.getMapper(MpStatisticsConvert.class);

    List<MpStatisticsUserSummaryRespVO> convertList01(List<WxDataCubeUserSummary> list);

    List<MpStatisticsUserCumulateRespVO> convertList02(List<WxDataCubeUserCumulate> list);

    List<MpStatisticsUpstreamMessageRespVO> convertList03(List<WxDataCubeMsgResult> list);

    @Mappings({
            @Mapping(source = "refDate", target = "refDate", dateFormat = "yyyy-MM-dd"),
            @Mapping(source = "msgUser", target = "messageUser"),
            @Mapping(source = "msgCount", target = "messageCount"),
    })
    MpStatisticsUpstreamMessageRespVO convert(WxDataCubeMsgResult bean);

    List<MpStatisticsInterfaceSummaryRespVO> convertList04(List<WxDataCubeInterfaceResult> list);

    @Mapping(source = "refDate", target = "refDate", dateFormat = "yyyy-MM-dd")
    MpStatisticsInterfaceSummaryRespVO convert(WxDataCubeInterfaceResult bean);
}

package co.yixiang.yshop.module.express.kdniao.model.vo;

import co.yixiang.yshop.module.express.kdniao.enums.KdniaoLogisticsCodeEnum;
import co.yixiang.yshop.module.express.kdniao.enums.KdniaoLogisticsStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> 快递鸟-物流-参数 </p>
 *
 * @author hupeng
 * @date 2023/7/21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KdniaoLogisticsVO {

    /**
     * {@link KdniaoLogisticsCodeEnum }
     */
    //快递公司编码
    private String logisticsCode;

    //快递单号
    private String logisticsNo;

    //事件轨迹集
    private List<TraceItem> traceList;

    //送货人名称
    private String delivererName;


    //送货人电话号码
    private String delivererPhone;

    //所在城市
    private String location;

    /**
     * {@link KdniaoLogisticsStatusEnum }
     */
    //物流状态
    private Integer status;

    //状态名称
    private String statusName;

    /**
     * {@link KdniaoLogisticsStatusEnum }
     */
    //增值物流状态
    private Integer statusEx;

    //增值物流状态名称
    private String statusExName;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    //事件轨迹集
    public static class TraceItem {
        //状态
        private Integer status;

        //状态名称
        private String statusName;

        //描述
        private String desc;

        //时间
        private String time;

        //所在城市
        private String location;
    }

    public void handleData() {
        this.statusName = KdniaoLogisticsStatusEnum.getEnum(this.status).getDesc();
        this.statusExName = KdniaoLogisticsStatusEnum.getEnum(this.statusEx).getDesc();
        if (CollectionUtils.isEmpty(this.traceList)) {
            this.traceList = new ArrayList();
        }
        this.traceList.forEach(item -> item.statusName = KdniaoLogisticsStatusEnum.getEnum(item.status).getDesc());
    }

}

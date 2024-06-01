package co.yixiang.yshop.module.express.kdniao.model.vo;


import co.yixiang.yshop.module.express.kdniao.enums.KdniaoLogisticsStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 快递鸟-物流-响应参数
 * </p>
 * @author hupeng
 * @date 2023/7/21
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class KdniaoApiVO {

    /**
     * {@link KdniaoLogisticsStatusEnum }
     * 增值物流状态：
     * 0-暂无轨迹信息
     * 1-已揽收
     * 2-在途中
     * 201-到达派件城市, 202-派件中, 211-已放入快递柜或驿站,
     * 3-已签收
     * 301-正常签收, 302-派件异常后最终签收, 304-代收签收, 311-快递柜或驿站签收,
     * 4-问题件
     * 401-发货无信息, 402-超时未签收, 403-超时未更新, 404-拒收(退件), 405-派件异常, 406-退货签收, 407-退货未签收, 412-快递柜或驿站超时未取
     */

    //增值物流状态
    private Integer StateEx;

    //增值物流状态名称
    private String statusExName;

    //快递单号
    private String LogisticCode;

    //快递公司编码
    private String ShipperCode;


    //失败原因
    private String Reason;


    //事件轨迹集
    private List<TraceItem> Traces;

    /**
     * {@link KdniaoLogisticsStatusEnum }
     */
    //物流状态：0-暂无轨迹信息,1-已揽收,2-在途中,3-签收,4-问题件
    private Integer State;

    //状态名称
    private String statusName;

    //用户ID
    private String EBusinessID;

    //送货人
    private String DeliveryMan;


    //送货人电话号码
    private String DeliveryManTel;

    //成功与否 true/false
    private String Success;


    //所在城市
    private String Location;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    //事件轨迹集
    public static class TraceItem {
        /**
         * {@link KdniaoLogisticsStatusEnum }
         */
        //当前状态(同StateEx)
        private Integer Action;

        //状态名称
        private String actionName;

        //描述
        private String AcceptStation;

        //时间
        private String AcceptTime;

        //所在城市
        private String Location;
    }


    public void handleData() {
        this.statusName = KdniaoLogisticsStatusEnum.getEnum(this.State).getDesc();
        this.statusExName = KdniaoLogisticsStatusEnum.getEnum(this.StateEx).getDesc();
        if (CollectionUtils.isEmpty(this.Traces)) {
            this.Traces = new ArrayList();
        }
        this.Traces.forEach(item -> item.actionName = KdniaoLogisticsStatusEnum.getEnum(item.Action).getDesc());
    }

}

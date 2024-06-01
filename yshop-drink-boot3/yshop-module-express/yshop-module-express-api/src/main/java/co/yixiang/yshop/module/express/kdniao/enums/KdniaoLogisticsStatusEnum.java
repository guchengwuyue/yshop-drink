package co.yixiang.yshop.module.express.kdniao.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * <p> 物流状态枚举 </p>
 *
 * @author hupeng
 * @date 2023/7/219
 */
@Getter
@AllArgsConstructor
public enum KdniaoLogisticsStatusEnum {

    /**
     * 暂无轨迹信息
     */
    NO_TRACE(0, "暂无轨迹信息"),
    /**
     * 已揽收
     */
    HAVE_PAID(1, "已揽收"),
    /**
     * 已揽收 -----------------------------------------------------------------------------
     */
    ON_THE_WAY(2, "在途中"),
    /**
     * 到达派件城市
     */
    ARRIVE_AT_THE_DISPATCH_CITY(201, "到达派件城市"),
    /**
     * 派件中
     */
    IN_THE_DELIVERY(202, "派件中"),
    /**
     * 已放入快递柜或驿站
     */
    HAS_STORED(211, "已放入快递柜或驿站"),
    /**
     * 签收 -----------------------------------------------------------------------------
     */
    SIGN(3, "签收"),
    /**
     * 正常签收
     */
    SIGN_NORMAL(301, "正常签收"),
    /**
     * 派件异常后最终签收
     */
    SIGN_ABNORMAL(302, "派件异常后最终签收"),
    /**
     * 代收签收
     */
    SIGN_COLLECTION(304, "代收签收"),
    /**
     * 快递柜或驿站签收
     */
    SIGN_STORED(311, "快递柜或驿站签收"),
    /**
     * 问题件 -----------------------------------------------------------------------------
     */
    PROBLEM_SHIPMENT(4, "问题件"),
    /**
     * 发货无信息
     */
    DELIVERY_NO_INFO(401, "发货无信息"),
    /**
     * 超时未签收
     */
    NO_SIGN_OVER_TIME(402, "超时未签收"),
    /**
     * 超时未更新
     */
    NOT_UPDATED_DUE_TO_TIMEOUT(403, "超时未更新"),
    /**
     * 拒收(退件)
     */
    REJECTION(404, "拒收(退件)"),
    /**
     * 派件异常
     */
    SEND_A_ABNORMAL(405, "派件异常"),
    /**
     * 退货签收
     */
    RETURN_TO_SIGN_FOR(406, "退货签收"),
    /**
     * 退货未签收
     */
    RETURN_NOT_SIGNED_FOR(407, "退货未签收"),
    /**
     * 快递柜或驿站超时未取
     */
    STORED_OVER_TIME(412, "快递柜或驿站超时未取"),
    /**
     * -
     */
    DEFAULT(0, "-");

    /**
     * 状态
     */
    private final Integer status;
    /**
     * 描述
     */
    private final String desc;

    private static final List<KdniaoLogisticsStatusEnum> LIST = new ArrayList();

    static {
        LIST.addAll(Arrays.asList(KdniaoLogisticsStatusEnum.values()));
    }

    /**
     * 根据物流状态查找相应枚举
     */
    public static KdniaoLogisticsStatusEnum getEnum(Integer status) {
        for (KdniaoLogisticsStatusEnum itemEnum : LIST) {
            if (itemEnum.getStatus().equals(status)) {
                return itemEnum;
            }
        }
        return KdniaoLogisticsStatusEnum.DEFAULT;
    }

}

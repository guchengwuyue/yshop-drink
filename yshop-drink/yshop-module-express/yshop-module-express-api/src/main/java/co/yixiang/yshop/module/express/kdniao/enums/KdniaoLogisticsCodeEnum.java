package co.yixiang.yshop.module.express.kdniao.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * <p> 物流公司-对应编码-枚举 </p>
 *
 * @author hupeng
 * @date 2023/7/219
 */
@Getter
@AllArgsConstructor
public enum KdniaoLogisticsCodeEnum {

    /**
     * 申通
     */
    STO("STO", "申通"),
    /**
     * 中通
     */
    ZTO("ZTO", "中通"),
    /**
     * 圆通
     */
    YTO("YTO", "圆通"),
    /**
     * 韵达
     */
    YD("YD", "韵达"),
    /**
     * 顺丰
     */
    SF("SF", "顺丰");

    /**
     * 物流编码
     */
    private final String code;
    /**
     * 物流名
     */
    private final String name;


    private static final List<KdniaoLogisticsCodeEnum> LIST = new ArrayList();

    static {
        LIST.addAll(Arrays.asList(KdniaoLogisticsCodeEnum.values()));
    }

    /**
     * 根据值查找相应枚举
     */
    @SneakyThrows(Exception.class)
    public static KdniaoLogisticsCodeEnum getEnumByName(String name) {
        for (KdniaoLogisticsCodeEnum itemEnum : LIST) {
            if (itemEnum.getName().equals(name)) {
                return itemEnum;
            }
        }
        throw new Exception("暂无此物流编码信息，请联系系统管理员！");
    }

}

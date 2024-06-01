package co.yixiang.yshop.module.score.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author hupeng
 * 订单相关枚举
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    STATUS__1(-1,"全部订单"),
    STATUS_0(0,"待发货"),
    STATUS_1(1,"待收货"),
    STATUS_2(2,"已完成"),
    STATUS_6(6,"已删除");



    private Integer value;
    private String desc;

    public static OrderStatusEnum toType(int value) {
        return Stream.of(OrderStatusEnum.values())
                .filter(p -> p.value == value)
                .findAny()
                .orElse(null);
    }


}

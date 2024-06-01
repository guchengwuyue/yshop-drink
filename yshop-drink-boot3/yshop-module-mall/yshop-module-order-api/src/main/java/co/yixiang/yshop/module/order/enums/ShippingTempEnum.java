package co.yixiang.yshop.module.order.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hupeng
 * 运费模板类型枚举
 */
@Getter
@AllArgsConstructor
public enum ShippingTempEnum {
    TYPE_1(1,"按件数"),
    TYPE_2(2,"按重量"),
    TYPE_3(3,"按体积");

    private Integer value;
    private String desc;
}

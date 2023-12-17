package co.yixiang.yshop.module.order.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hupeng
 * 售后类型枚举
 */
@Getter
@AllArgsConstructor
public enum AfterTypeEnum {
    TYPE_1(1,"同意"),
    TYPE_2(2,"拒绝"),
    SERVICE_TYPE_0(0,"仅退款"),
    SERVICE_TYPE_1(1,"退货退款");

    private Integer value;
    private String desc;
}

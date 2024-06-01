package co.yixiang.yshop.module.order.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hupeng
 * 售后状态枚举
 */
@Getter
@AllArgsConstructor
public enum AfterChangeTypeEnum {
    STATE_0(0,"售后订单生成"),
    STATE_1(1,"后台审核成功"),
    STATE_2(2,"用户发货"),
    STATE_3(3,"打款"),
    STATE_4(4,"审核失败"),
    STATE_5(5,"用户撤销");

    private Integer value;
    private String desc;
}

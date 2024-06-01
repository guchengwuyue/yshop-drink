package co.yixiang.yshop.module.order.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hupeng
 * 售后状态枚举
 */
@Getter
@AllArgsConstructor
public enum AfterStatusEnum {
    STATE_0(0,"正常"),
    STATE_1(1,"用户取消"),
    STATE_2(2,"商家拒绝");

    private Integer value;
    private String desc;
}

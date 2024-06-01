package co.yixiang.yshop.module.store.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hupeng
 * 类型枚举
 */
@Getter
@AllArgsConstructor
public enum WithdrawalStatusEnum {
    STATUS_0(0,"未审核"),
    STATUS_1(1,"待到账"),
    STATUS_2(2,"审核拒绝"),
    STATUS_3(3,"已到账");

    private Integer value;
    private String desc;
}

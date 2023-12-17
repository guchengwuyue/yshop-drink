/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.yshop.module.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author hupeng
 * 后台订单相关枚举
 */
@Getter
@AllArgsConstructor
public enum AdminOrderStatusEnum {

	STATUS_0(0,"未支付"),
	STATUS_1(1,"未发货"),
	STATUS_2(2,"待收货"),
	STATUS_3(3,"待评价"),
	STATUS_4(4,"交易完成"),
	STATUS_5(5,"已退款"),
	STATUS_6(6,"已删除");




	private Integer value;
	private String desc;

	public static AdminOrderStatusEnum toType(int value) {
		return Stream.of(AdminOrderStatusEnum.values())
				.filter(p -> p.value == value)
				.findAny()
				.orElse(null);
	}


}

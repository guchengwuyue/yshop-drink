/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.yshop.module.coupon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author hupeng
 * 优惠券相关枚举
 */
@Getter
@AllArgsConstructor
public enum CouponStatusEnum {
	STATUS_4(4,"所有"),
	STATUS_0(0,"未使用"),
	STATUS_1(1,"已使用"),
	STATUS_2(2,"已失效");



	private Integer value;
	private String desc;

	public static CouponStatusEnum toType(int value) {
		return Stream.of(CouponStatusEnum.values())
				.filter(p -> p.value == value)
				.findAny()
				.orElse(null);
	}


}

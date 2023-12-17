/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.yshop.module.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hupeng
 * 应用来源相关枚举
 */
@Getter
@AllArgsConstructor
public enum UpdateOrderEnum {

	UPDATE_ORDER("updateOrder","修改订单"),
	ORDER_SEND("orderSend","订单发货"),
	RMARK("remark","备注"),
	SEND_INFO("sendInfo","配送信息");


	private String value;
	private String desc;


}

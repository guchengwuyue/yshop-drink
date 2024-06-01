package co.yixiang.yshop.module.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author hupeng
 * 订单操作相关枚举
 */
@Getter
@AllArgsConstructor
public enum OrderLogEnum {

	ORDER_TAKE_DESK("desk","扫码点餐"),
	ORDER_TAKE_OUT("takeout","外卖"),
	ORDER_TAKE_IN("takein","自取"),
	PINK_ORDER_FAIL_1("ORDER_EXIST","订单生成失败，你已经参加该团了，请先支付订单"),
	PINK_ORDER_FAIL_2("ORDER_EXIST","订单生成失败，你已经在该团内不能再参加了"),
	REFUND_ORDER_SUCCESS("refund_price_success","退款成功"),
	ORDER_EDIT("order_edit","订单改价"),
	REMOVE_ORDER("remove_order","删除订单"),
	EVAL_ORDER("order_eval","用户评价"),
	REFUND_ORDER_APPLY("apply_refund","用户申请退款"),
	TAKE_ORDER_DELIVERY("user_take_delivery","用户已收货"),
	PAY_ORDER_FAIL("PAY_DEFICIENCY","余额不足"),
	PAY_ORDER_SUCCESS("pay_success","用户付款成功"),
	CREATE_ORDER_SUCCESS("SUCCESS","订单创建成功"),
	CREATE_ORDER("yshop_create_order","订单生成"),
	NONE_ORDER("NONE","订单OK"),
	DELIVERY_GOODS("delivery_goods", "订单发货"),
	OFFLINE_PAY("offline_pay", "线下支付"),
	EXTEND_ORDER("EXTEND_ORDER","订单已生成");


	private String value;
	private String desc;



	public static OrderLogEnum toType(String value) {
		return Stream.of(OrderLogEnum.values())
				.filter(p -> p.value.equals(value))
				.findAny()
				.orElse(null);
	}

	public static String getDesc(String value) {
		return toType(value) == null ? null : toType(value).desc;
	}

}

package co.yixiang.yshop.module.coupon.enums;
import co.yixiang.yshop.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {
    ErrorCode COUPON_NOT_EXISTS = new ErrorCode(1008018000, "优惠券不存在");
    ErrorCode COUPON_USER_NOT_EXISTS = new ErrorCode(1008018001, "用户领的优惠券不存在");
    ErrorCode COUPON_RECEIVED = new ErrorCode(1008018001, "优惠券已经领取过");
    ErrorCode COUPON_RECEIVE_ZERO = new ErrorCode(1008018001, "优惠券已经被领完");
}


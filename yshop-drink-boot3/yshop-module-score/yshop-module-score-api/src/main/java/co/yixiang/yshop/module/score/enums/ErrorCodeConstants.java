package co.yixiang.yshop.module.score.enums;

import co.yixiang.yshop.framework.common.exception.ErrorCode;
public interface ErrorCodeConstants {
    ErrorCode ORDER_NOT_EXISTS = new ErrorCode(1008018000, "积分商城订单不存在");
    ErrorCode PRODUCT_NOT_EXISTS = new ErrorCode(1008018001, "积分产品不存在");
    ErrorCode SCORE_NOT = new ErrorCode(1008018002, "积分不足");
    ErrorCode PRODUCT_NOT_STOCK = new ErrorCode(1008018002, "库存不足");
}


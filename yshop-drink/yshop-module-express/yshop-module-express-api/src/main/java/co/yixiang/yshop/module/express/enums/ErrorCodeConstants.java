package co.yixiang.yshop.module.express.enums;

import co.yixiang.yshop.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {
    // ========== 快递公司 1008008000 ==========
    ErrorCode EXPRESS_NOT_EXISTS = new ErrorCode(1008008000, "快递公司不存在");
    // ========== 电子面单  ==========
    ErrorCode ELECTRONICS_ORDER_NOT_EXISTS = new ErrorCode(1008009000, "电子面单不存在");
}


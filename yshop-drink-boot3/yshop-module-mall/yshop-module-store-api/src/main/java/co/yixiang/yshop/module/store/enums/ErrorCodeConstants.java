package co.yixiang.yshop.module.store.enums;

import co.yixiang.yshop.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {
    ErrorCode SHOP_NOT_EXISTS = new ErrorCode(1008016000, "门店管理不存在");
    ErrorCode WEB_PRINT_NOT_EXISTS = new ErrorCode(1008016001, "易联云打印机不存在");
    ErrorCode STORE_REVENUE_NOT_EXISTS = new ErrorCode(1008016002, "店铺收支明细不存在");
    ErrorCode WITHDRAWAL_NOT_EXISTS = new ErrorCode(1008016003, "提现管理不存在");
    ErrorCode USER_BANK_NOT_EXISTS = new ErrorCode(1008016004, "提现账户不存在");
}

package co.yixiang.yshop.module.order.enums;

import co.yixiang.yshop.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {
    // ========== 订单1008007000==========
    ErrorCode STORE_ORDER_NOT_EXISTS = new ErrorCode(1008007000, "订单不存在");
    ErrorCode STORE_ORDER_CART_INFO_NOT_EXISTS = new ErrorCode(1008007001, "订单购物详情不存在");
    ErrorCode STORE_ORDER_STATUS_NOT_EXISTS = new ErrorCode(1008007002, "订单操作记录不存在");
    ErrorCode STORE_AFTER_SALES_NOT_EXISTS = new ErrorCode(1008007003, "售后记录不存在");
    ErrorCode STORE_AFTER_SALES_ITEM_NOT_EXISTS = new ErrorCode(1008007004, "售后子不存在");
    ErrorCode STORE_AFTER_SALES_STATUS_NOT_EXISTS = new ErrorCode(1008007005, "售后订单操作详情不存在");
    ErrorCode INVALID_PRODUCT = new ErrorCode(1008007005, "有失效的商品请重新提交");
    ErrorCode VALID_PRODUCT_EMPTY = new ErrorCode(1008007005, "请提交购买的商品");
    ErrorCode PARAM_ERROR = new ErrorCode(1008007006, "参数错误");
    ErrorCode ORDER_EXPIRED = new ErrorCode(1008007007, "订单已过期,请刷新当前页面");
    ErrorCode SELECT_ADDRESS = new ErrorCode(1008007008, "请选择收货地址");
    ErrorCode ORDER_GEN_FAIL = new ErrorCode(1008007009, "订单生成失败");
    ErrorCode ORDER_PAY_FINISH = new ErrorCode(1008007010, "该订单已支付");
    ErrorCode PAY_YUE_NOT = new ErrorCode(1008007011, "余额不足");
    ErrorCode ORDER_STATUS_ERROR = new ErrorCode(1008007012, "订单状态错误");
    ErrorCode ORDER_STATUS_FINISH = new ErrorCode(1008007023, "订单已经完成，不要重复操作");
    ErrorCode COMMENT_PRODUCT_NOT_EXISTS = new ErrorCode(1008007013, "评价产品不存在");
    ErrorCode COMMENT_PRODUCT_IN_EXISTS = new ErrorCode(1008007014, "该产品已评价");
    ErrorCode ORDER_NOT_DELETE = new ErrorCode(1008007018, "该订单无法删除");
    ErrorCode ORDER_STATUS_NOT_EXPRESS_ = new ErrorCode(1008007015, "当前状态不能添加物流信息");
    ErrorCode ORDER_REFUND_NOT = new ErrorCode(1008007017, "订单状态不能售后");
    ErrorCode ORDER_NOT_REVOKE = new ErrorCode(1008007016, "订单不能撤销");
    ErrorCode ORDER_NOT_CANCEL = new ErrorCode(1008007018, "订单不能取消");
    ErrorCode ORDER_ADDRESS_REQUERED = new ErrorCode(1008007019, "请输入商家收货人信息");
    ErrorCode ORDER_REFUNDING = new ErrorCode(1008007020, "正在申请退款中");
    ErrorCode ORDER_REFUNDED = new ErrorCode(1008007021, "订单已退款");
    ErrorCode ORDER_PRICE_ERROR = new ErrorCode(1008007022, "退款金额不正确");
    // ========== 订单电子面单记录 ==========
    ErrorCode STORE_ORDER_ELECTRONICS_NOT_EXISTS = new ErrorCode(1008010000, "订单电子面单记录不存在");


    ErrorCode STORE_ORDER_DESK_NOT = new ErrorCode(1008007023, "当前桌号不存在");



}

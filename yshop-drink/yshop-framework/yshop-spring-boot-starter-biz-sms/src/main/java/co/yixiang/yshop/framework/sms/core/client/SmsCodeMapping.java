package co.yixiang.yshop.framework.sms.core.client;

import co.yixiang.yshop.framework.common.exception.ErrorCode;
import co.yixiang.yshop.framework.sms.core.enums.SmsFrameworkErrorCodeConstants;

import java.util.function.Function;

/**
 * 将 API 的错误码，转换为通用的错误码
 *
 * @see SmsCommonResult
 * @see SmsFrameworkErrorCodeConstants
 *
 * @author yshop
 */
public interface SmsCodeMapping extends Function<String, ErrorCode> {
}

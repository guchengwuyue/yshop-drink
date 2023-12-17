package co.yixiang.yshop.framework.sms.core.client.impl.debug;

import co.yixiang.yshop.framework.common.exception.ErrorCode;
import co.yixiang.yshop.framework.common.exception.enums.GlobalErrorCodeConstants;
import co.yixiang.yshop.framework.sms.core.client.SmsCodeMapping;
import co.yixiang.yshop.framework.sms.core.enums.SmsFrameworkErrorCodeConstants;

import java.util.Objects;

/**
 * 钉钉的 SmsCodeMapping 实现类
 *
 * @author yshop
 */
public class DebugDingTalkCodeMapping implements SmsCodeMapping {

    @Override
    public ErrorCode apply(String apiCode) {
        return Objects.equals(apiCode, "0") ? GlobalErrorCodeConstants.SUCCESS : SmsFrameworkErrorCodeConstants.SMS_UNKNOWN;
    }

}

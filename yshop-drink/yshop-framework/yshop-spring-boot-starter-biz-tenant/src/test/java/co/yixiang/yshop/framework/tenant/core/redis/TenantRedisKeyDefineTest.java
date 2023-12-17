package co.yixiang.yshop.framework.tenant.core.redis;

import co.yixiang.yshop.framework.redis.core.RedisKeyDefine;
import co.yixiang.yshop.framework.tenant.core.context.TenantContextHolder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TenantRedisKeyDefineTest {

    @Test
    public void testFormatKey() {
        Long tenantId = 30L;
        TenantContextHolder.setTenantId(tenantId);
        // 准备参数
        TenantRedisKeyDefine define = new TenantRedisKeyDefine("", "user:%d:%d", RedisKeyDefine.KeyTypeEnum.HASH,
                Object.class, RedisKeyDefine.TimeoutTypeEnum.FIXED);
        Long userId = 10L;
        Integer userType = 1;

        // 调用
        String key = define.formatKey(userId, userType);
        // 断言
        assertEquals("user:10:1:30", key);
    }

}

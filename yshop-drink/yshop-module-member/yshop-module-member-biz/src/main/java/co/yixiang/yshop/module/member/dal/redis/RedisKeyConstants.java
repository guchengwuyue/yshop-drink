package co.yixiang.yshop.module.member.dal.redis;

import co.yixiang.yshop.framework.redis.core.RedisKeyDefine;

import static co.yixiang.yshop.framework.redis.core.RedisKeyDefine.KeyTypeEnum.STRING;

/**
 * System Redis Key 枚举类
 *
 * @author yshop
 */
public interface RedisKeyConstants {




    RedisKeyDefine YSHOP_MINI_LOGIN_CACHE_KEY = new RedisKeyDefine("小程序登录session",
            "yshop_mini_login_cache:%s", // 参数为访问uid+key
            STRING, String.class, RedisKeyDefine.TimeoutTypeEnum.FOREVER);




}

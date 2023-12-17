package co.yixiang.yshop.module.order.dal.redis;

import co.yixiang.yshop.framework.redis.core.RedisKeyDefine;
import co.yixiang.yshop.module.order.service.storeorder.dto.CacheDto;

import static co.yixiang.yshop.framework.redis.core.RedisKeyDefine.KeyTypeEnum.STRING;

/**
 * System Redis Key 枚举类
 *
 * @author yshop
 */
public interface RedisKeyConstants {


    RedisKeyDefine YSHOP_ORDER_CACHE_KEY = new RedisKeyDefine("确认订单数据缓存",
            "yshop_order_cache:%s", // 参数为访问uid+key
            STRING, CacheDto.class, RedisKeyDefine.TimeoutTypeEnum.DYNAMIC);

    RedisKeyDefine YSHOP_ORDER_SALE_STATUS_KEY = new RedisKeyDefine("售后订单数据缓存",
            "yshop_after_order_cache:%s", // 参数为访问uid+key
            STRING, String.class, RedisKeyDefine.TimeoutTypeEnum.DYNAMIC);

    RedisKeyDefine YSHOP_ORDER_COUNT_CACHE_KEY = new RedisKeyDefine("统计订单数据缓存",
            "yshop_order_count_cache:%s", // 参数为访问uid
            STRING, CacheDto.class, RedisKeyDefine.TimeoutTypeEnum.FOREVER);

    RedisKeyDefine YSHOP_ADMIN_ORDER_COUNT_CACHE_KEY = new RedisKeyDefine("后台统计订单数据缓存",
            "yshop_admin_order_count_cache:", // 参数为访问uid
            STRING, CacheDto.class, RedisKeyDefine.TimeoutTypeEnum.FOREVER);

    RedisKeyDefine YSHOP_WEB_PRINT_MECHINE_KEY = new RedisKeyDefine("打印机token",
            "yshop_web_print_machine_cache:%s", // 参数为访问shopid
            STRING, String.class, RedisKeyDefine.TimeoutTypeEnum.DYNAMIC);


}

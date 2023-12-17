package co.yixiang.yshop.module.express.dal.redis;

import co.yixiang.yshop.framework.redis.core.RedisKeyDefine;
import co.yixiang.yshop.module.express.kdniao.model.dto.KdniaoApiBaseDTO;

import static co.yixiang.yshop.framework.redis.core.RedisKeyDefine.KeyTypeEnum.STRING;

/**
 * System Redis Key 枚举类
 *
 * @author yshop
 */
public interface RedisKeyConstants {


    RedisKeyDefine YSHOP_EXPRESS_CACHE_KEY = new RedisKeyDefine("快递鸟配置",
            "yshop_express_cache:", //
            STRING, KdniaoApiBaseDTO.class, RedisKeyDefine.TimeoutTypeEnum.FOREVER);




}

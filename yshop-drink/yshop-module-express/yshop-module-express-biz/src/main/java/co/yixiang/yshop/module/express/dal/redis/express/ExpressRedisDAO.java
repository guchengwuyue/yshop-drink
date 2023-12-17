package co.yixiang.yshop.module.express.dal.redis.express;

import co.yixiang.yshop.framework.common.util.json.JsonUtils;
import co.yixiang.yshop.module.express.kdniao.model.dto.KdniaoApiBaseDTO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import static co.yixiang.yshop.module.express.dal.redis.RedisKeyConstants.YSHOP_EXPRESS_CACHE_KEY;


/**
 * {@link KdniaoApiBaseDTO} 的 RedisDAO
 *
 * @author yshop
 */
@Repository
public class ExpressRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    public KdniaoApiBaseDTO get() {
        String redisKey = YSHOP_EXPRESS_CACHE_KEY.getKeyTemplate();
        return JsonUtils.parseObject(stringRedisTemplate.opsForValue().get(redisKey), KdniaoApiBaseDTO.class);
    }

    public void set(KdniaoApiBaseDTO apiBaseDTO) {
        String redisKey = YSHOP_EXPRESS_CACHE_KEY.getKeyTemplate();
        stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(apiBaseDTO));
    }

    public void delete() {
        String redisKey =YSHOP_EXPRESS_CACHE_KEY.getKeyTemplate();
        stringRedisTemplate.delete(redisKey);
    }



    private static String formatKey() {
        return String.format(YSHOP_EXPRESS_CACHE_KEY.getKeyTemplate());
    }

}

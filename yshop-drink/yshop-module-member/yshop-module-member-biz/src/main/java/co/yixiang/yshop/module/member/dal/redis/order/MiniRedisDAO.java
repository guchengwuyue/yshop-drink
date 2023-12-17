package co.yixiang.yshop.module.member.dal.redis.order;

import co.yixiang.yshop.framework.common.util.json.JsonUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import static co.yixiang.yshop.module.member.dal.redis.RedisKeyConstants.YSHOP_MINI_LOGIN_CACHE_KEY;


/**
 *
 * @author yshop
 */
@Repository
public class MiniRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public String get(String key) {
        String redisKey = formatKey(key);
        return stringRedisTemplate.opsForValue().get(redisKey);
    }

    public String set(String str,String key) {
        String redisKey = formatKey(key);
        stringRedisTemplate.opsForValue().set(redisKey, str);
        return key;
    }

    public void delete(String key) {
        String redisKey = formatKey(key);
        stringRedisTemplate.delete(redisKey);
    }



    private static String formatKey(String key) {
        return String.format(YSHOP_MINI_LOGIN_CACHE_KEY.getKeyTemplate(), key);
    }

}

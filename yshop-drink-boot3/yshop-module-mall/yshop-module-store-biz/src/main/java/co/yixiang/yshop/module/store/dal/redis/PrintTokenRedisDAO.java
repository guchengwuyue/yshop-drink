package co.yixiang.yshop.module.store.dal.redis;


import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;

import java.util.concurrent.TimeUnit;

import static co.yixiang.yshop.module.store.dal.redis.RedisKeyConstants.YSHOP_WEB_PRINT_TOKEN_KEY;


/**

 *
 * @author yshop
 */
@Repository
public class PrintTokenRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public String get() {
        String redisKey = YSHOP_WEB_PRINT_TOKEN_KEY.getKeyTemplate();
        return stringRedisTemplate.opsForValue().get(redisKey);
    }

    public void set(String o) {
        String redisKey = YSHOP_WEB_PRINT_TOKEN_KEY.getKeyTemplate();


        stringRedisTemplate.opsForValue().set(redisKey, o,2, TimeUnit.HOURS);
    }

    public void delete() {
        String redisKey = YSHOP_WEB_PRINT_TOKEN_KEY.getKeyTemplate();
        stringRedisTemplate.delete(redisKey);
    }


//
//    private static String formatKey) {
//        return String.format(YSHOP_ORDER_SALE_STATUS_KEY.getKeyTemplate(), key);
//    }

}

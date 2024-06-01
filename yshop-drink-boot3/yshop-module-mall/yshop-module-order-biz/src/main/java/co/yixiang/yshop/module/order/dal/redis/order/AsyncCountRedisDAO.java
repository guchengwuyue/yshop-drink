package co.yixiang.yshop.module.order.dal.redis.order;

import co.yixiang.yshop.framework.common.util.json.JsonUtils;
import co.yixiang.yshop.framework.tenant.core.context.TenantContextHolder;
import co.yixiang.yshop.module.member.controller.app.user.vo.AppUserOrderCountVo;
import co.yixiang.yshop.module.order.controller.admin.storeorder.vo.ShoperOrderTimeDataVo;
import co.yixiang.yshop.module.order.service.storeorder.dto.OrderTimeDataDto;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;

import static co.yixiang.yshop.module.order.dal.redis.RedisKeyConstants.YSHOP_ADMIN_ORDER_COUNT_CACHE_KEY;

/**
 * {@link AppUserOrderCountVo} 的 RedisDAO
 *
 * @author yshop
 */
@Repository
public class AsyncCountRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public OrderTimeDataDto get() {
        String redisKey = formatKey();
        return JsonUtils.parseObject(stringRedisTemplate.opsForValue().get(redisKey), OrderTimeDataDto.class);
    }

    public void set(OrderTimeDataDto orderTimeDataDto) {
        String redisKey = formatKey();
        stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(orderTimeDataDto));
    }

    public void delete(Long uid) {
        String redisKey = YSHOP_ADMIN_ORDER_COUNT_CACHE_KEY.getKeyTemplate();
        stringRedisTemplate.delete(redisKey);
    }


    private static String formatKey() {
        return String.format(YSHOP_ADMIN_ORDER_COUNT_CACHE_KEY.getKeyTemplate());
    }

}

package co.yixiang.yshop.module.order.handle;

import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.ThreadFactoryBuilder;
import cn.hutool.core.util.StrUtil;
import co.yixiang.yshop.framework.common.constant.ShopConstants;
import co.yixiang.yshop.module.order.service.storeorder.AppStoreOrderService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

/**
 * 延时队列消费
 * @author hupeng
 * @date 2023.7.27
 */
@Component
@Slf4j
public class RedisDelayHandle {
    @Resource
    private RedissonClient redissonClient;
    @Resource
    private AppStoreOrderService appStoreOrderService;

    @PostConstruct
    public void startJobTimer() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNamePrefix("delay-job-service").build();
        log.info("========延时队列开始=========");
        ExecutorService executorService = ExecutorBuilder.create()
                .setCorePoolSize(2)
                .setMaxPoolSize(10)
                .setKeepAliveTime(0)
                .setThreadFactory(threadFactory)
                .build();

        executorService.execute(new ExecutorTaskUnPay());
        executorService.execute(new ExecutorTaskUnConfirm());
    }

    class ExecutorTaskUnPay implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            RBlockingDeque<String> blockingDeque = redissonClient
                    .getBlockingDeque(ShopConstants.REDIS_ORDER_OUTTIME_UNPAY_QUEUE);
            while (true) {
                try {
                    log.info("======延迟队列监听未支付订单======");
                    String orderId = blockingDeque.take();
                    log.info("延迟队列监听未支付订单订单编号：{}", orderId);
                    if(StrUtil.isNotEmpty(orderId)) {
                        appStoreOrderService.cancelOrder(orderId,null);
                    }
                } catch (Exception e) {
                    log.error("Redission延迟队列监测异常中断,忽略此消息：{}", e.getMessage());
                    return;
                }

            }

        }
    }


    class ExecutorTaskUnConfirm implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            RBlockingDeque<String> blockingDeque = redissonClient
                    .getBlockingDeque(ShopConstants.REDIS_ORDER_OUTTIME_UNCONFIRM);
            while (true) {
                try {
                    log.info("======延迟队列监听收货超时订单======");
                    String orderId = blockingDeque.take();
                    log.info("延迟队列监听收货超时订单订单编号：{}", orderId);
                    if(StrUtil.isNotEmpty(orderId)) {
                        appStoreOrderService.takeOrder(orderId,null);
                    }
                } catch (Exception e) {
                    log.error("Redission延迟队列监测异常中断,忽略此消息：{}", e.getMessage());
                    return;
                }

            }

        }
    }

}

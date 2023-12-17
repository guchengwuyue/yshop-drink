package co.yixiang.yshop.module.order.service.storeorder;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import co.yixiang.yshop.framework.common.enums.OrderInfoEnum;
import co.yixiang.yshop.module.member.controller.app.user.vo.AppUserOrderCountVo;
import co.yixiang.yshop.module.member.service.user.MemberUserService;
import co.yixiang.yshop.module.order.controller.admin.storeorder.vo.ShoperOrderTimeDataVo;
import co.yixiang.yshop.module.order.dal.dataobject.storeorder.StoreOrderDO;
import co.yixiang.yshop.module.order.dal.mysql.storeorder.StoreOrderMapper;
import co.yixiang.yshop.module.order.dal.mysql.storeordercartinfo.StoreOrderCartInfoMapper;
import co.yixiang.yshop.module.order.dal.redis.order.AsyncCountRedisDAO;
import co.yixiang.yshop.module.order.dal.redis.order.AsyncOrderRedisDAO;
import co.yixiang.yshop.module.order.dal.redis.order.PrintMechinRedisDAO;
import co.yixiang.yshop.module.order.service.storeorder.dto.OrderTimeDataDto;
import co.yixiang.yshop.module.product.service.storeproduct.StoreProductService;
import co.yixiang.yshop.module.store.dal.mysql.storeshop.StoreShopMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 异步订单 Service 实现类
 *
 * @author yshop
 */
@Service
@Validated
@Slf4j
public class AsynStoreOrderServiceImpl implements AsyncStoreOrderService {

    @Resource
    private StoreOrderMapper storeOrderMapper;
    @Resource
    private AsyncOrderRedisDAO asyncOrderRedisDAO;
    @Resource
    private AsyncCountRedisDAO asyncCountRedisDAO;
    @Resource
    private MemberUserService userService;
    @Resource
    private StoreProductService productService;
    @Resource
    private PrintMechinRedisDAO printMechinRedisDAO;
    @Resource
    private StoreShopMapper storeShopMapper;
    @Resource
    private StoreOrderCartInfoMapper storeOrderCartInfoMapper;

    /**
     * 获取某个用户的订单统计数据
     *
     * @param uid uid>0 取用户 否则取所有
     * @return
     */
    @Override
    @Async
    public void orderData(Long uid) {
        log.info("========获取某个用户的订单统计数据=========");
        //订单支付没有退款 数量
        LambdaQueryWrapper<StoreOrderDO> wrapperOne = new LambdaQueryWrapper<>();
        if (uid != null) {
            wrapperOne.eq(StoreOrderDO::getUid, uid);
        }
        wrapperOne.eq(StoreOrderDO::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue())
                .eq(StoreOrderDO::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue());
        Long orderCount = storeOrderMapper.selectCount(wrapperOne);

        //订单支付没有退款 支付总金额
        double sumPrice = storeOrderMapper.sumPrice(uid);

        //订单待支付 数量
        LambdaQueryWrapper<StoreOrderDO> wrapperTwo = new LambdaQueryWrapper<>();
        if (uid != null) {
            wrapperTwo.eq(StoreOrderDO::getUid, uid);
        }
        wrapperTwo.eq(StoreOrderDO::getPaid, OrderInfoEnum.PAY_STATUS_0.getValue())
                .eq(StoreOrderDO::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue())
                .eq(StoreOrderDO::getStatus, OrderInfoEnum.STATUS_0.getValue());
        Long unpaidCount = storeOrderMapper.selectCount(wrapperTwo);

        //订单待发货 数量
        LambdaQueryWrapper<StoreOrderDO> wrapperThree = new LambdaQueryWrapper<>();
        if (uid != null) {
            wrapperThree.eq(StoreOrderDO::getUid, uid);
        }
        wrapperThree.eq(StoreOrderDO::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                .eq(StoreOrderDO::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue())
                .eq(StoreOrderDO::getStatus, OrderInfoEnum.STATUS_0.getValue());
        Long unshippedCount = storeOrderMapper.selectCount(wrapperThree);

        //订单待收货 数量
        LambdaQueryWrapper<StoreOrderDO> wrapperFour = new LambdaQueryWrapper<>();
        if (uid != null) {
            wrapperFour.eq(StoreOrderDO::getUid, uid);
        }
        wrapperFour.eq(StoreOrderDO::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                .eq(StoreOrderDO::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue())
                .eq(StoreOrderDO::getStatus, OrderInfoEnum.STATUS_1.getValue());
        Long receivedCount = storeOrderMapper.selectCount(wrapperFour);

        //订单待评价 数量
        LambdaQueryWrapper<StoreOrderDO> wrapperFive = new LambdaQueryWrapper<>();
        if (uid != null) {
            wrapperFive.eq(StoreOrderDO::getUid, uid);
        }
        wrapperFive.eq(StoreOrderDO::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                .eq(StoreOrderDO::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue())
                .eq(StoreOrderDO::getStatus, OrderInfoEnum.STATUS_2.getValue());
        Long evaluatedCount = storeOrderMapper.selectCount(wrapperFive);

        //订单已完成 数量
        LambdaQueryWrapper<StoreOrderDO> wrapperSix = new LambdaQueryWrapper<>();
        if (uid != null) {
            wrapperSix.eq(StoreOrderDO::getUid, uid);
        }
        wrapperSix.eq(StoreOrderDO::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                .eq(StoreOrderDO::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue())
                .eq(StoreOrderDO::getStatus, OrderInfoEnum.STATUS_3.getValue());
        Long completeCount = storeOrderMapper.selectCount(wrapperSix);

        //售后退款
        Long salesCount = 0L;

         AppUserOrderCountVo appUserOrderCountVo = AppUserOrderCountVo.builder()
                .orderCount(orderCount)
                .sumPrice(sumPrice)
                .unpaidCount(unpaidCount)
                .unshippedCount(unshippedCount)
                .receivedCount(receivedCount)
                .evaluatedCount(evaluatedCount)
                .completeCount(completeCount)
                .refundCount(salesCount)
                .build();

         //存redis
        asyncOrderRedisDAO.set(appUserOrderCountVo,uid);

        this.getOrderTimeData();
    }


    /**
     * 首页订单/用户等统计
     *
     * @return OrderTimeDataDto
     */
    @Async
    @Override
    public void getOrderTimeData() {
        OrderTimeDataDto orderTimeDataDto = new OrderTimeDataDto();

        ShoperOrderTimeDataVo shoperOrderTimeData = this.getShoperOrderTimeData();

        BeanUtil.copyProperties(shoperOrderTimeData, orderTimeDataDto);


        orderTimeDataDto.setUserCount(userService.count());
        orderTimeDataDto.setOrderCount(storeOrderMapper.selectCount());
        orderTimeDataDto.setPriceCount(storeOrderMapper.sumTotalPrice());
        orderTimeDataDto.setGoodsCount(productService.count());

        asyncCountRedisDAO.set(orderTimeDataDto);
    }

    /**
     * 异步后台统计
     */
    public ShoperOrderTimeDataVo getShoperOrderTimeData() {

        Date today = DateUtil.beginOfDay(new Date());
        Date yesterday = DateUtil.beginOfDay(DateUtil.yesterday());
        Date nowMonth = DateUtil.beginOfMonth(new Date());
        Date lastWeek = DateUtil.beginOfDay(DateUtil.lastWeek());

        ShoperOrderTimeDataVo orderTimeDataVo = new ShoperOrderTimeDataVo();

        //今日成交额
        LambdaQueryWrapper<StoreOrderDO> wrapperOne = new LambdaQueryWrapper<>();
        wrapperOne
                .ge(StoreOrderDO::getPayTime, today)
                .eq(StoreOrderDO::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                .eq(StoreOrderDO::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue());
        orderTimeDataVo.setTodayPrice(storeOrderMapper.todayPrice(wrapperOne));
        //今日订单数
        orderTimeDataVo.setTodayCount(storeOrderMapper.selectCount(wrapperOne));

        //昨日成交额
        LambdaQueryWrapper<StoreOrderDO> wrapperTwo = new LambdaQueryWrapper<>();
        wrapperTwo
                .lt(StoreOrderDO::getPayTime, today)
                .ge(StoreOrderDO::getPayTime, yesterday)
                .eq(StoreOrderDO::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                .eq(StoreOrderDO::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue());
        orderTimeDataVo.setProPrice(storeOrderMapper.todayPrice(wrapperTwo));
        //昨日订单数
        orderTimeDataVo.setProCount(storeOrderMapper.selectCount(wrapperTwo));

        //本月成交额
        LambdaQueryWrapper<StoreOrderDO> wrapperThree = new LambdaQueryWrapper<>();
        wrapperThree
                .ge(StoreOrderDO::getPayTime, nowMonth)
                .eq(StoreOrderDO::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                .eq(StoreOrderDO::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue());
        orderTimeDataVo.setMonthPrice(storeOrderMapper.todayPrice(wrapperThree));
        //本月订单数
        orderTimeDataVo.setMonthCount(storeOrderMapper.selectCount(wrapperThree));

        //上周成交额
        LambdaQueryWrapper<StoreOrderDO> wrapperLastWeek = new LambdaQueryWrapper<>();
        wrapperLastWeek
                .lt(StoreOrderDO::getPayTime, today)
                .ge(StoreOrderDO::getPayTime, lastWeek)
                .eq(StoreOrderDO::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                .eq(StoreOrderDO::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue());
        orderTimeDataVo.setLastWeekPrice(storeOrderMapper.todayPrice(wrapperLastWeek));
        //上周订单数
        orderTimeDataVo.setLastWeekCount(storeOrderMapper.selectCount(wrapperLastWeek));


        return orderTimeDataVo;

    }






}

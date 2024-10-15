package co.yixiang.yshop.module.order.service.storeorder;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import co.yixiang.yshop.framework.common.constant.ShopConstants;
import co.yixiang.yshop.framework.common.enums.OrderInfoEnum;
import co.yixiang.yshop.framework.common.enums.PayIdEnum;
import co.yixiang.yshop.framework.common.enums.ShopCommonEnum;
import co.yixiang.yshop.framework.common.exception.ErrorCode;
import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.framework.security.core.util.SecurityFrameworkUtils;
import co.yixiang.yshop.framework.tenant.core.context.TenantContextHolder;
import co.yixiang.yshop.module.member.controller.admin.user.vo.UserRespVO;
import co.yixiang.yshop.module.member.convert.user.UserConvert;
import co.yixiang.yshop.module.member.dal.dataobject.user.MemberUserDO;
import co.yixiang.yshop.module.member.dal.mysql.user.MemberUserMapper;
import co.yixiang.yshop.module.member.enums.BillDetailEnum;
import co.yixiang.yshop.module.member.service.user.MemberUserService;
import co.yixiang.yshop.module.member.service.userbill.UserBillService;
import co.yixiang.yshop.module.message.mq.producer.WeixinNoticeProducer;
import co.yixiang.yshop.module.message.redismq.msg.OrderMsg;
import co.yixiang.yshop.module.order.controller.admin.storeorder.vo.*;
import co.yixiang.yshop.module.order.convert.storeorder.StoreOrderConvert;
import co.yixiang.yshop.module.order.dal.dataobject.storeorder.StoreOrderDO;
import co.yixiang.yshop.module.order.dal.dataobject.storeordercartinfo.StoreOrderCartInfoDO;
import co.yixiang.yshop.module.order.dal.mysql.storeorder.StoreOrderMapper;
import co.yixiang.yshop.module.order.dal.mysql.storeordercartinfo.StoreOrderCartInfoMapper;
import co.yixiang.yshop.module.order.enums.OrderLogEnum;
import co.yixiang.yshop.module.order.enums.PayTypeEnum;
import co.yixiang.yshop.module.order.enums.UpdateOrderEnum;
import co.yixiang.yshop.module.order.service.storeorderstatus.StoreOrderStatusService;
import co.yixiang.yshop.module.product.service.storeproduct.AppStoreProductService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.egzosn.pay.common.bean.RefundOrder;
import com.egzosn.pay.common.bean.RefundResult;
import com.egzosn.pay.spring.boot.core.PayServiceManager;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static co.yixiang.yshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static co.yixiang.yshop.module.member.enums.ErrorCodeConstants.USER_NOT_EXISTS;
import static co.yixiang.yshop.module.order.enums.ErrorCodeConstants.*;

/**
 * 订单 Service 实现类
 *
 * @author yshop
 */
@Slf4j
@Service
@Validated
public class StoreOrderServiceImpl implements StoreOrderService {

    @Resource
    private StoreOrderMapper storeOrderMapper;
    @Resource
    private MemberUserMapper memberUserMapper;
    @Resource
    private StoreOrderCartInfoMapper storeOrderCartInfoMapper;
    @Resource
    private StoreOrderStatusService storeOrderStatusService;
    @Resource
    private AppStoreOrderService appStoreOrderService;
    @Resource
    private MemberUserService userService;
    @Resource
    private WeixinNoticeProducer weixinNoticeProducer;
    @Resource
    private RedissonClient redissonClient;
    @Resource
    private UserBillService billService;
    @Resource
    private AppStoreProductService appStoreProductService;
    @Resource
    private PayServiceManager manager;

    @Resource
    private AsyncStoreOrderService asyncStoreOrderService;


    @Value("${yshop.demo}")
    private Boolean isDemo;


    @Override
    public Long createStoreOrder(StoreOrderCreateReqVO createReqVO) {
        // 插入
        StoreOrderDO storeOrder = StoreOrderConvert.INSTANCE.convert(createReqVO);
        storeOrderMapper.insert(storeOrder);
        // 返回
        return storeOrder.getId();
    }

    @Override
    public void updateStoreOrder(StoreOrderUpdateReqVO updateReqVO) {
        // 校验存在
        // 更新
        StoreOrderDO updateObj = StoreOrderConvert.INSTANCE.convert(updateReqVO);
        StoreOrderDO updateObj2 = storeOrderMapper.selectById(updateReqVO.getId());
        if (updateObj2 == null) {
            throw exception(STORE_ORDER_NOT_EXISTS);
        }

        //发货自取模式 直接收货
        if(UpdateOrderEnum.ORDER_SEND.getValue().equals(updateReqVO.getUpdateType())
                && updateObj.getOrderType().equals(OrderLogEnum.ORDER_TAKE_IN.getValue())){
           this.takeStoreOrder(updateObj.getId());
            //todo 异步打印小票 商业版本才有 官网购买：https://www.yixiang.co

           return;
        }
        //发货-外卖模式
        if(UpdateOrderEnum.ORDER_SEND.getValue().equals(updateReqVO.getUpdateType())
                && updateObj.getOrderType().equals(OrderLogEnum.ORDER_TAKE_OUT.getValue())){
            updateObj.setStatus(OrderInfoEnum.STATUS_1.getValue());
            //todo 异步打印小票 商业版本才有 官网购买：https://www.yixiang.co

        }
        //堂食模式
        if(UpdateOrderEnum.ORDER_SEND.getValue().equals(updateReqVO.getUpdateType())
                && updateObj.getOrderType().equals(OrderLogEnum.ORDER_TAKE_DESK.getValue())){

            //todo 异步打印小票 商业版本才有 官网购买：https://www.yixiang.co

        }
        storeOrderMapper.updateById(updateObj);

        if(UpdateOrderEnum.ORDER_SEND.getValue().equals(updateReqVO.getUpdateType())){
            //增加状态
            storeOrderStatusService.create(updateObj.getUid(),updateObj.getId(), OrderLogEnum.DELIVERY_GOODS.getValue(),
                    "已发货 快递公司：" + updateObj.getDeliveryName() + "快递单号：" + updateObj.getDeliveryId());

            MemberUserDO userInfo = userService.getUser(updateObj.getUid());
            //发送消息队列进行推送消息
//            if(userInfo.getLoginType().equals(AppFromEnum.ROUNTINE.getValue())){
//                weixinNoticeProducer.sendNoticeMessage(updateObj.getUid(), WechatTempateEnum.DELIVERY_SUCCESS.getValue(),
//                        WechatTempateEnum.SUBSCRIBE.getValue(),updateObj.getOrderId(),
//                        updateObj.getPayPrice().toString(),"",updateObj.getDeliveryName(),
//                        updateObj.getDeliveryId());
//            }else if(userInfo.getLoginType().equals(AppFromEnum.WECHAT.getValue())){
//                weixinNoticeProducer.sendNoticeMessage(updateObj.getUid(),WechatTempateEnum.PAY_SUCCESS.getValue(),
//                        WechatTempateEnum.TEMPLATES.getValue(),updateObj.getOrderId(),
//                        updateObj.getPayPrice().toString(),"",updateObj.getDeliveryName(),
//                        updateObj.getDeliveryId());
//            }

            //延时队列 1小时候自动收货
            try {
                RBlockingDeque<Object> blockingDeque = redissonClient.getBlockingDeque(ShopConstants.REDIS_ORDER_OUTTIME_UNCONFIRM);
                RDelayedQueue<Object> delayedQueue = redissonClient.getDelayedQueue(blockingDeque);
                delayedQueue.offer(OrderMsg.builder().orderId(updateObj.getOrderId()).build(), ShopConstants.ORDER_OUTTIME_UNCONFIRM, TimeUnit.MINUTES);
                String s = TimeUnit.SECONDS.toSeconds(ShopConstants.ORDER_OUTTIME_UNCONFIRM) + "分钟";
                log.info("添加延时队列成功 ，延迟时间：" + s);
            } catch (Exception e) {
                log.error(e.getMessage());
            }

        }

    }

    @Override
    public void deleteStoreOrder(Long id) {
        // 校验存在
        validateStoreOrderExists(id);
        // 删除
        StoreOrderDO storeOrderDO = StoreOrderDO.builder()
                .isSystemDel(ShopCommonEnum.DELETE_1.getValue())
                .id(id)
                .build();
        storeOrderMapper.updateById(storeOrderDO);
    }

    @Override
    public void payStoreOrder(Long id) {
        // 校验存在
        validateStoreOrderExists(id);
        StoreOrderDO storeOrderDO = storeOrderMapper.selectById(id);
        storeOrderDO.setPaid(OrderInfoEnum.PAY_STATUS_1.getValue());
        storeOrderDO.setPayTime(LocalDateTime.now());
        storeOrderMapper.updateById(storeOrderDO);

        //增加状态
        storeOrderStatusService.create(storeOrderDO.getUid(),storeOrderDO.getId(), OrderLogEnum.OFFLINE_PAY.getValue(),
                OrderLogEnum.OFFLINE_PAY.getDesc());
    }

    @Override
    public void takeStoreOrder(Long id) {
        StoreOrderDO storeOrderDO = storeOrderMapper.selectById(id);
        appStoreOrderService.takeOrder(storeOrderDO.getOrderId(),storeOrderDO.getUid());
    }


    private void validateStoreOrderExists(Long id) {
        if (storeOrderMapper.selectById(id) == null) {
            throw exception(STORE_ORDER_NOT_EXISTS);
        }
    }

    @Override
    public StoreOrderRespVO getStoreOrder(Long id) {
        StoreOrderDO storeOrderDO = storeOrderMapper.selectById(id);
        StoreOrderRespVO storeOrderRespVO = StoreOrderConvert.INSTANCE.convert(storeOrderDO);
        MemberUserDO memberUserDO = memberUserMapper.selectById(storeOrderRespVO.getUid());
        UserRespVO  userRespVO = UserConvert.INSTANCE.convert4(memberUserDO);
        storeOrderRespVO.setUserRespVO(userRespVO);

        LambdaQueryWrapper<StoreOrderCartInfoDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StoreOrderCartInfoDO::getOid,storeOrderDO.getId());
        List<StoreOrderCartInfoDO> storeOrderCartInfoDOList = storeOrderCartInfoMapper.selectList(wrapper);
        storeOrderRespVO.setStoreOrderCartInfoDOList(storeOrderCartInfoDOList);

        storeOrderRespVO.setStatusStr(this.handleOrderStatus(storeOrderRespVO.getPaid()
                ,storeOrderRespVO.getStatus(),storeOrderRespVO.getRefundStatus(),storeOrderRespVO.getIsSystemDel()));
        return storeOrderRespVO;
    }

    @Override
    public List<StoreOrderDO> getStoreOrderList(Collection<Long> ids) {
        return storeOrderMapper.selectBatchIds(ids);
    }

    /**
     * 订单查询
     * @param pageReqVO 分页查询
     * @return
     */
    @Override
    public PageResult<StoreOrderRespVO> getStoreOrderPage(StoreOrderPageReqVO pageReqVO) {
        PageResult<StoreOrderDO> pageResult =  storeOrderMapper.selectPage(pageReqVO);
        PageResult<StoreOrderRespVO> storeOrderRespVO =  StoreOrderConvert.INSTANCE.convertPage(pageResult);
        for (StoreOrderRespVO storeOrderRespVO1 : storeOrderRespVO.getList()) {
            LambdaQueryWrapper<StoreOrderCartInfoDO> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(StoreOrderCartInfoDO::getOid,storeOrderRespVO1.getId());
            List<StoreOrderCartInfoDO> storeOrderCartInfoDOList = storeOrderCartInfoMapper.selectList(wrapper);
            MemberUserDO memberUserDO = memberUserMapper.selectById(storeOrderRespVO1.getUid());
            UserRespVO  userRespVO = UserConvert.INSTANCE.convert4(memberUserDO);
            storeOrderRespVO1.setStoreOrderCartInfoDOList(storeOrderCartInfoDOList);
            storeOrderRespVO1.setUserRespVO(userRespVO);
            storeOrderRespVO1.setStatusStr(this.handleOrderStatus(storeOrderRespVO1.getPaid()
                    ,storeOrderRespVO1.getStatus(),storeOrderRespVO1.getRefundStatus(),storeOrderRespVO1.getIsSystemDel()));
        }
        return storeOrderRespVO;
    }

    @Override
    public List<StoreOrderDO> getStoreOrderList(StoreOrderExportReqVO exportReqVO) {
        return storeOrderMapper.selectList(exportReqVO);
    }

    /**
     * 确认订单退款
     *
     * @param id 单号
     * @param price   金额
     * @param type    ShopCommonEnum
     * @param salesId 售后id
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void orderRefund(Long id, BigDecimal price, Integer type, Long salesId) {

        StoreOrderDO storeOrderDO = storeOrderMapper.selectById(id);
        if (storeOrderDO == null) {
            throw exception(STORE_ORDER_NOT_EXISTS);
        }

        MemberUserDO userQueryVo = userService.getById(storeOrderDO.getUid());
        if (ObjectUtil.isNull(userQueryVo)) {
            throw exception(USER_NOT_EXISTS);
        }

        if (OrderInfoEnum.REFUND_STATUS_2.getValue().equals(storeOrderDO.getRefundStatus())) {
            throw exception(ORDER_REFUNDED);
        }

        if (storeOrderDO.getPayPrice().compareTo(price) < 0) {
            throw exception(ORDER_PRICE_ERROR);
        }

        storeOrderDO.setRefundStatus(OrderInfoEnum.REFUND_STATUS_2.getValue());
        storeOrderDO.setRefundPrice(price);
        storeOrderMapper.updateById(storeOrderDO);

        //生成分布式唯一值用于退款订单
        String orderSn = IdUtil.getSnowflake(0, 0).nextIdStr();
        BigDecimal balance = userQueryVo.getNowMoney();
        //根据支付类型不同退款不同
        if (PayTypeEnum.YUE.getValue().equals(storeOrderDO.getPayType())) {
            //退款到余额
            userService.incMoney(storeOrderDO.getUid(), price);
            balance = balance.add(price);

        } else if (PayTypeEnum.WEIXIN.getValue().equals(storeOrderDO.getPayType())) {
            if(isDemo){
                throw exception(new ErrorCode(888888,"演示模式没有配置证书无法微信退款的哦！"));
            }
            log.error("{},{},{},{}",orderSn,storeOrderDO.getOrderId(),price,storeOrderDO.getPayPrice());
            RefundOrder refundOrder = new RefundOrder(orderSn,"",storeOrderDO.getOrderId(),price,storeOrderDO.getPayPrice());
            RefundResult refundResult = manager.refund(PayIdEnum.WX_MINIAPP.getValue(), refundOrder);
            if(refundResult.getCode().equals("FAIL")){
                log.error("支付退款错误：{}",refundResult.getMsg());
                throw exception(new ErrorCode(999999,refundResult.getMsg()));
            }else {
                if(refundResult.getResultCode().equals("FAIL")){
                    log.error("支付退款错误：{}",refundResult.getResultMsg());
                    throw exception(new ErrorCode(999998,refundResult.getResultMsg()));
                }
            }
        }else if (PayTypeEnum.ALI.getValue().equals(storeOrderDO.getPayType())){
            throw exception(new ErrorCode(999997,"支付宝暂时不支持退款"));
        }

        //增加流水
        billService.income(storeOrderDO.getUid(), "商品退款", BillDetailEnum.CATEGORY_1.getValue(),
                BillDetailEnum.TYPE_5.getValue(),
                price.doubleValue(),
                balance.doubleValue(),
                "订单退款到余额" + price + "元", storeOrderDO.getId().toString(),orderSn);
        storeOrderStatusService.create(storeOrderDO.getUid(), storeOrderDO.getId(),
                OrderLogEnum.REFUND_ORDER_SUCCESS.getValue(), "退款给用户：" + price + "元");

        //退库存
        this.regressionStock(storeOrderDO,0);

    }

    /**
     * 订单30s内通知
     * @return
     */
    @Override
    public Long orderNotice() {
        LambdaQueryWrapper<StoreOrderDO> wrapper = new LambdaQueryWrapper();
        Long shopId = SecurityFrameworkUtils.getLoginUser().getShopId();
        if(shopId > 0) {
            wrapper.eq(StoreOrderDO::getShopId,shopId);
        }
        wrapper.eq(StoreOrderDO::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue());
        LocalDateTime nowTime = LocalDateTime.now();
        wrapper.ge(StoreOrderDO::getCreateTime,nowTime.minusSeconds(30));

        return  storeOrderMapper.selectCount(wrapper);

    }

    /**
     * 退回库存
     *
     * @param order 订单
     */
    private void regressionStock(StoreOrderDO order, Integer type) {

        LambdaQueryWrapper<StoreOrderCartInfoDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(StoreOrderCartInfoDO::getOid, order.getId());

        List<StoreOrderCartInfoDO> cartInfoList = storeOrderCartInfoMapper.selectList(wrapper);
        for (StoreOrderCartInfoDO cartInfo : cartInfoList) {
            appStoreProductService.incProductStock(cartInfo.getNumber(), cartInfo.getProductId(), cartInfo.getSpec(),
                    0L, null);
        }
    }


    /**
     * 处理订单状态
     * @param payStatus
     * @param status
     * @param refundStatus
     * @param del
     * @return
     */
    private String handleOrderStatus(Integer payStatus,Integer status,Integer refundStatus,Integer del) {
        String statusName = "";
        if (del == 1){
            statusName = "已删除";
        }else if (payStatus == 0 && status == 0) {
            statusName = "未支付";
        } else if (payStatus == 1 && status == 0 && refundStatus == 0) {
            statusName = "未发货";
        }  else if (payStatus == 1 && status == 1 && refundStatus == 0) {
            statusName = "待收货";
        }  else if (payStatus == 1 && status == 2 && refundStatus == 0) {
            statusName = "待评价";
        } else if (payStatus == 1 && status == 3 && refundStatus == 0) {
            statusName = "已完成";
        } else if (payStatus == 1 && refundStatus == 2) {
            statusName = "已退款";
        }
        else if (payStatus == 1 && refundStatus == 1) {
            statusName = "退款中";
        }
        return statusName;
    }




}

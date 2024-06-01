package co.yixiang.yshop.module.score.service.scoreorder;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.yshop.framework.common.constant.ShopConstants;
import co.yixiang.yshop.framework.common.enums.OrderInfoEnum;
import co.yixiang.yshop.framework.common.enums.ShopCommonEnum;
import co.yixiang.yshop.framework.common.exception.ErrorCode;
import co.yixiang.yshop.framework.common.util.servlet.ServletUtils;
import co.yixiang.yshop.framework.ip.core.utils.IPUtils;
import co.yixiang.yshop.module.member.dal.dataobject.user.MemberUserDO;
import co.yixiang.yshop.module.member.dal.dataobject.useraddress.UserAddressDO;
import co.yixiang.yshop.module.member.enums.BillDetailEnum;
import co.yixiang.yshop.module.member.service.user.MemberUserService;
import co.yixiang.yshop.module.member.service.useraddress.AppUserAddressService;
import co.yixiang.yshop.module.member.service.userbill.UserBillService;
import co.yixiang.yshop.module.score.controller.app.order.param.AppScoreOrderParam;
import co.yixiang.yshop.module.score.controller.app.order.vo.AppScoreOrderVO;
import co.yixiang.yshop.module.score.convert.scoreorder.ScoreOrderConvert;
import co.yixiang.yshop.module.score.dal.dataobject.scoreorder.ScoreOrderDO;
import co.yixiang.yshop.module.score.dal.dataobject.scoreproduct.ScoreProductDO;
import co.yixiang.yshop.module.score.dal.mysql.scoreorder.ScoreOrderMapper;
import co.yixiang.yshop.module.score.dal.mysql.scoreproduct.ScoreProductMapper;
import co.yixiang.yshop.module.score.enums.OrderStatusEnum;
import co.yixiang.yshop.module.score.service.scoreproduct.AppScoreProductService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static co.yixiang.yshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static co.yixiang.yshop.module.member.enums.ErrorCodeConstants.USER_ADDRESS_NOT_EXISTS;
import static co.yixiang.yshop.module.score.enums.ErrorCodeConstants.*;

/**
 * 积分商城订单 Service 实现类
 *
 * @author yshop
 */
@Service
@Validated
public class AppScoreOrderServiceImpl extends ServiceImpl<ScoreOrderMapper, ScoreOrderDO> implements AppScoreOrderService {

    @Resource
    private ScoreOrderMapper orderMapper;
    @Resource
    private ScoreProductMapper scoreProductMapper;
    @Resource
    private AppScoreProductService appScoreProductService;
    @Resource
    private MemberUserService userService;
    @Resource
    private RedissonClient redissonClient;
    @Resource
    private UserBillService billService;
    @Resource
    private  AppUserAddressService appUserAddressService;

    private static final String STOCK_LOCK_KEY = "score:order:stock:lock";


    /**
     * 提交
     * @param uid
     * @param appScoreOrderParam
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void submit(Long uid,AppScoreOrderParam appScoreOrderParam) {
        ScoreProductDO scoreProductDO = appScoreProductService.getById(appScoreOrderParam.getProductId());
        if(scoreProductDO == null){
            throw exception(PRODUCT_NOT_EXISTS);
        }
       UserAddressDO userAddressDO =  appUserAddressService.getById(appScoreOrderParam.getAddressId());
        if(userAddressDO == null){
            throw exception(USER_ADDRESS_NOT_EXISTS);
        }
        MemberUserDO memberUserDO = userService.getById(uid);
        if(scoreProductDO.getStock() <= 0){
            throw exception(PRODUCT_NOT_STOCK);
        }
        if(NumberUtil.compare(memberUserDO.getIntegral().intValue(),scoreProductDO.getScore()) < 0){
            throw exception(SCORE_NOT);
        }

        //生成分布式唯一值
        String orderSn = IdUtil.getSnowflake(0, 0).nextIdStr();
        ScoreOrderDO scoreOrderDO = ScoreOrderDO.builder()
                .havePaid(ShopCommonEnum.IS_STATUS_1.getValue())
                .customerAddress(userAddressDO.getAddress() + userAddressDO.getDetail())
                .customerName(userAddressDO.getRealName())
                .customerPhone(userAddressDO.getPhone())
                .ip(ServletUtils.getClientIP())
                .orderId(orderSn)
                .productId(scoreProductDO.getId())
                .number(Integer.valueOf(appScoreOrderParam.getNum()))
                .score(scoreProductDO.getScore())
                .totalScore(scoreProductDO.getScore())
                .uid(uid)
                .build();
        //保存
        this.save(scoreOrderDO);

        //减去积分
        userService.decScore(uid,scoreOrderDO.getScore());

        this.deStockIncSale(scoreProductDO.getId(),Integer.valueOf(appScoreOrderParam.getNum()));

        //增加流水
        billService.expend(uid, "积分兑换",
                BillDetailEnum.CATEGORY_2.getValue(),
                BillDetailEnum.TYPE_3.getValue(),
                scoreProductDO.getScore().doubleValue(), memberUserDO.getIntegral().doubleValue(),
                scoreProductDO.getScore() + "积分兑换商品");

    }


    /**
     * 订单列表
     *
     * @param uid   用户id
     * @param type  OrderStatusEnum
     * @param page  page
     * @param limit limit
     * @return list
     */
    @Override
    public List<AppScoreOrderVO> orderList(Long uid, int type, int page, int limit) {
        LambdaQueryWrapper<ScoreOrderDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ScoreOrderDO::getUid, uid);
        wrapper.orderByDesc(ScoreOrderDO::getId);
        switch (OrderStatusEnum.toType(type)) {
            case STATUS__1:
                break;
            //待发货
            case STATUS_0:
                wrapper.eq(ScoreOrderDO::getHavePaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                        .eq(ScoreOrderDO::getHaveDelivered, OrderInfoEnum.STATUS_0.getValue())
                        .eq(ScoreOrderDO::getHaveReceived, OrderInfoEnum.STATUS_0.getValue());
                break;
            //待收货
            case STATUS_1:
                wrapper.eq(ScoreOrderDO::getHavePaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                        .eq(ScoreOrderDO::getHaveDelivered, OrderInfoEnum.STATUS_1.getValue())
                        .eq(ScoreOrderDO::getHaveReceived, OrderInfoEnum.STATUS_0.getValue());
                break;
            //已完成
            case STATUS_2:
                wrapper.eq(ScoreOrderDO::getHavePaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                        //.eq(ScoreOrderDO::getHaveDelivered, OrderInfoEnum.STATUS_1.getValue())
                        .eq(ScoreOrderDO::getHaveReceived, OrderInfoEnum.STATUS_1.getValue());
                break;
            default:
        }

        Page<ScoreOrderDO> pageModel = new Page<>(page, limit);
        IPage<ScoreOrderDO> pageList = orderMapper.selectPage(pageModel, wrapper);
        List<AppScoreOrderVO> list = ScoreOrderConvert.INSTANCE.convertList01(pageList.getRecords());

        return list.stream().map(this::handleOrder).collect(Collectors.toList());
    }

    /**
     * 订单详情
     * @param uid 用户id
     * @param id
     */
    @Override
    public AppScoreOrderVO orderDetail(Long uid, Long id) {
        LambdaQueryWrapper<ScoreOrderDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ScoreOrderDO::getUid, uid).eq(ScoreOrderDO::getId,id);
        ScoreOrderDO scoreOrderDO = this.getOne(wrapper);

        AppScoreOrderVO appScoreOrderVO = ScoreOrderConvert.INSTANCE.convert01(scoreOrderDO);
        return this.handleOrder(appScoreOrderVO);
    }

    /**
     * 收货
     * @param uid
     * @param id
     */
    @Override
    public void take(Long uid, Long id) {
        LambdaQueryWrapper<ScoreOrderDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ScoreOrderDO::getUid, uid).eq(ScoreOrderDO::getId,id);
        ScoreOrderDO scoreOrderDO = this.getOne(wrapper);
        if(scoreOrderDO == null){
            throw exception(ORDER_NOT_EXISTS);
        }
        scoreOrderDO.setHaveReceived(ShopCommonEnum.DEFAULT_1.getValue());
        this.updateById(scoreOrderDO);
    }

    /**
     * 处理订单
     * @param order
     * @return
     */
    private AppScoreOrderVO handleOrder(AppScoreOrderVO order) {
        order.setProduct(appScoreProductService.getDetail(order.getProductId()));
        if (OrderInfoEnum.PAY_STATUS_1.getValue().equals(order.getHavePaid())
                && OrderInfoEnum.STATUS_0.getValue().equals(order.getHaveDelivered())
                && OrderInfoEnum.STATUS_0.getValue().equals(order.getHaveReceived())) {
            order.setStatusText("待发货");
        } else if (OrderInfoEnum.PAY_STATUS_1.getValue().equals(order.getHavePaid())
                && OrderInfoEnum.STATUS_1.getValue().equals(order.getHaveDelivered())
                && OrderInfoEnum.STATUS_0.getValue().equals(order.getHaveReceived())) {
            order.setStatusText("待收货");
        } else if (OrderInfoEnum.PAY_STATUS_1.getValue().equals(order.getHavePaid())
                && OrderInfoEnum.STATUS_1.getValue().equals(order.getHaveReceived())) {
            order.setStatusText("已完成");
        }
        return order;
    }



    /**
     * 减库存增加销量
     *
     * @param productId 商品id
     * @param number 商品数量
     */
    private void deStockIncSale(Long productId, Integer number) {

        //对库存加锁
        RLock lock = redissonClient.getLock(STOCK_LOCK_KEY);
        if (lock.tryLock()) {
            try {
                scoreProductMapper.decStockIncSales(number,productId);
            }catch (Exception ex) {
                log.error("[deStockIncSale][执行异常]", ex);
                throw exception(new ErrorCode(999999,ex.getMessage()));
            } finally {
                lock.unlock();
            }
        }
    }

}

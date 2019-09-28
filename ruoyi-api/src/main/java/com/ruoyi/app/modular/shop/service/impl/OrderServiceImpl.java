package com.ruoyi.app.modular.shop.service.impl;


//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmuch.lightsecurity.exception.LightSecurityException;
import com.ruoyi.app.common.persistence.dao.*;
import com.ruoyi.app.common.persistence.model.*;
import com.ruoyi.app.common.utils.OrderUtil;
import com.ruoyi.app.modular.member.service.IMemberService;
import com.ruoyi.app.modular.shop.service.IOrderService;
import com.ruoyi.app.modular.shop.service.dto.CouponDTO;
import com.ruoyi.app.modular.shop.service.dto.GoodsDTO;
import com.ruoyi.app.modular.shop.service.dto.OrderDTO;
import com.ruoyi.app.modular.shop.service.mapper.GoodsMapper;
import com.ruoyi.app.modular.shop.service.mapper.OrderGoodsMapper;
import com.ruoyi.app.modular.shop.service.mapper.OrderMapper;
import com.ruoyi.app.modular.shop.service.vo.CartAttrVO;
import com.ruoyi.app.modular.shop.service.vo.CartVO;
import com.ruoyi.app.modular.shop.service.vo.OrderVo;
import com.ruoyi.app.modular.shop.service.vo.PageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl extends ServiceImpl<StoreOrderMapper, StoreOrder>  implements IOrderService {

    private final StoreCartMapper storeCartMapper;
    private final StoreSpecGoodsPriceMapper storeSpecGoodsPriceMapper;
    private final StoreMemberAddressMapper storeMemberAddressMapper;
    private final StoreCouponMapper storeCouponMapper;
    private final StoreCouponListMapper storeCouponListMapper;
    private final OrderGoodsServiceImpl orderGoodsService;
    private final GoodsServiceImpl goodsService;
    private final StoreOrderGoodsMapper storeOrderGoodsMapper;
    private final OrderMapper orderMapper;
    private final StorePointsMoneyLogMapper storePointsMoneyLogMapper;
    private final OrderGoodsMapper orderGoodsMapper;
    private final IMemberService memberService;
    private final StoreMemberMapper storeMemberMapper;
    private final StoreGoodsMapper storeGoodsMapper;


    /**
     * 购物车提交
     * @param orderVo
     * @param userId
     * @return
     */
    @Override
    @Transactional
    public String addOrderOfOne(OrderVo orderVo,int userId) {
        if(StrUtil.isEmpty(orderVo.getGoodsIds())){
            throw new LightSecurityException("参数缺失");
        }
        String[] goddsArr = orderVo.getGoodsIds().split(",");
        QueryWrapper<StoreCart> cartQueryWrapper = new QueryWrapper<>();
        cartQueryWrapper.eq("user_id",userId).in("goods_id", Arrays.asList(goddsArr));
        List<StoreCart> storeCarts = storeCartMapper.selectList(cartQueryWrapper);
        System.out.println("storeCarts:"+storeCarts);
        if(storeCarts.isEmpty()){
            throw new LightSecurityException("购物车空空如也");
        }

        //计算商品总价
        Double totalGoodsPrice = 0d;

        for (StoreCart cart : storeCarts) {
            int storeCount = checkStore(cart.getGoodsId(),cart.getSpecKey());
            if(storeCount < cart.getGoodsNum()){
                throw new LightSecurityException("库存不足");
            }

           // BigDecimal decimal = new BigDecimal();
            totalGoodsPrice += cart.getGoodsPrice().doubleValue()*cart.getGoodsNum();
        }

        //插入主表
        StoreOrder storeOrder = new StoreOrder();
        storeOrder.setOrderSn(OrderUtil.orderSn());
        storeOrder.setUserId(userId);
        storeOrder.setReceiveType(2);
        storeOrder.setGoodsPrice(BigDecimal.valueOf(totalGoodsPrice));
        storeOrder.setTotalAmount(BigDecimal.valueOf(totalGoodsPrice));
        //storeOrder.setAddTime(OrderUtil.getSecondTimestampTwo());


        //处理收货
        StoreMemberAddress address = storeMemberAddressMapper.selectById(orderVo.getAddressId());
        if(address == null){
            throw new LightSecurityException("地址不存在");
        }
        storeOrder.setConsignee(address.getUsername());
        storeOrder.setCityInfo(address.getCityInfo());
        storeOrder.setAddress(address.getAddress());
        storeOrder.setMobile(address.getPhone());
        //处理优惠券
        if(StrUtil.isNotEmpty(orderVo.getCouponId())){
            CouponDTO couponInfo = storeCouponMapper
                    .findLog(Integer.valueOf(orderVo.getCouponId()));
            storeOrder.setCouponPrice(BigDecimal.valueOf(couponInfo.getMoney()));
            storeOrder.setOrderAmount(BigDecimal
                    .valueOf((totalGoodsPrice - couponInfo.getMoney())));
            updateCouponStatus(Integer.valueOf(orderVo.getCouponId()));
        }else{
            storeOrder.setOrderAmount(BigDecimal.valueOf(totalGoodsPrice));
        }

        //插入主表
        if(baseMapper.insert(storeOrder) == 0) throw new LightSecurityException("操作失败");;
        int orderId = storeOrder.getOrderId();

        //副表
        List<StoreOrderGoods> orderGoodsList = new ArrayList<>();
        for (StoreCart cart : storeCarts) {
            StoreOrderGoods orderGoods = new StoreOrderGoods();
            orderGoods.setOrderId(orderId);
            orderGoods.setGoodsId(cart.getGoodsId());
            orderGoods.setGoodsSn(cart.getGoodsSn());
            orderGoods.setGoodsName(cart.getGoodsName());
            orderGoods.setMarketPrice(cart.getMarketPrice());
            orderGoods.setGoodsPrice(cart.getGoodsPrice());
            orderGoods.setGoodsNum(cart.getGoodsNum());
            orderGoods.setSpecKey(cart.getSpecKey());
            orderGoods.setSpecKeyName(cart.getSpecKeyName());
            orderGoods.setGoodsLogo(cart.getGoodsLogo());

            orderGoodsList.add(orderGoods);
        }

        if(!orderGoodsService.saveBatch(orderGoodsList)){
            throw new LightSecurityException("操作失败");
        }

        //清空购物车
        QueryWrapper<StoreCart> cartWrapper = new QueryWrapper<>();
        cartWrapper.eq("user_id",userId).in("goods_id", Arrays.asList(goddsArr));
        storeCartMapper.delete(cartWrapper);



        return storeOrder.getOrderSn();
    }

    /**
     * 直接提交订单
     * @param orderVo
     * @param userId
     * @return
     */
    @Override
    public String addOrderOfTwo(OrderVo orderVo, int userId) {
        if(orderVo.getCartVO() == null){
            throw new LightSecurityException("参数缺失");
        }

        CartVO cartVO = orderVo.getCartVO();

        StoreGoods storeGoods = goodsService.getById(cartVO.getGoodsId());
        if(storeGoods == null) throw new LightSecurityException("商品不存在");

        //计算商品总价
        Double totalGoodsPrice = 0d;

        for (CartAttrVO attrVO : cartVO.getCartAttr()) {
            int storeCount = checkStore(Integer.valueOf(cartVO.getGoodsId()),attrVO.getSpecKey());
            if(storeCount < attrVO.getGoodsNum()){
                throw new LightSecurityException("库存不足");
            }

            QueryWrapper<StoreSpecGoodsPrice> wrapper = new QueryWrapper<>();
            wrapper.eq("goods_id",cartVO.getGoodsId()).eq("`key`",attrVO.getSpecKey());
            StoreSpecGoodsPrice storeSpecGoodsPrice = storeSpecGoodsPriceMapper
                    .selectOne(wrapper);
            totalGoodsPrice += storeSpecGoodsPrice.getPrice().doubleValue()*attrVO.getGoodsNum();

        }

        //插入主表
        StoreOrder storeOrder = new StoreOrder();
        storeOrder.setOrderSn(OrderUtil.orderSn());
        storeOrder.setUserId(userId);
        storeOrder.setReceiveType(2);
        storeOrder.setGoodsPrice(BigDecimal.valueOf(totalGoodsPrice));
        storeOrder.setTotalAmount(BigDecimal.valueOf(totalGoodsPrice));


        //处理收货
        StoreMemberAddress address = storeMemberAddressMapper.selectById(orderVo.getAddressId());
        if(address == null){
            throw new LightSecurityException("地址不存在");
        }
        storeOrder.setConsignee(address.getUsername());
        storeOrder.setCityInfo(address.getCityInfo());
        storeOrder.setAddress(address.getAddress());
        storeOrder.setMobile(address.getPhone());

        //处理优惠券
        if(StrUtil.isNotEmpty(orderVo.getCouponId())){
            CouponDTO couponInfo = storeCouponMapper
                    .findLog(Integer.valueOf(orderVo.getCouponId()));
            storeOrder.setCouponPrice(BigDecimal.valueOf(couponInfo.getMoney()));
            storeOrder.setOrderAmount(BigDecimal
                    .valueOf((totalGoodsPrice - couponInfo.getMoney())));
            updateCouponStatus(Integer.valueOf(orderVo.getCouponId()));
        }else{
            storeOrder.setOrderAmount(BigDecimal.valueOf(totalGoodsPrice));
        }

        //插入主表
        if(baseMapper.insert(storeOrder) == 0) throw new LightSecurityException("操作失败");;
        int orderId = storeOrder.getOrderId();

        //处理副表
        List<StoreOrderGoods> orderGoodsList = new ArrayList<>();
        for (CartAttrVO attrVO : cartVO.getCartAttr()) {
            QueryWrapper<StoreSpecGoodsPrice> wrap = new QueryWrapper<>();
            wrap.eq("goods_id",cartVO.getGoodsId()).eq("`key`",attrVO.getSpecKey());
            StoreSpecGoodsPrice specGoodsPrice = storeSpecGoodsPriceMapper
                    .selectOne(wrap);
            StoreOrderGoods orderGoods = new StoreOrderGoods();
            orderGoods.setOrderId(orderId);
            orderGoods.setGoodsId(storeGoods.getGoodsId());
            orderGoods.setGoodsSn(storeGoods.getGoodsSn());
            orderGoods.setGoodsName(storeGoods.getGoodsName());
            orderGoods.setMarketPrice(BigDecimal.valueOf(specGoodsPrice.getMarketPrice()));
            orderGoods.setGoodsPrice(specGoodsPrice.getPrice());
            orderGoods.setGoodsNum(attrVO.getGoodsNum());
            orderGoods.setSpecKey(attrVO.getSpecKey());
            orderGoods.setSpecKeyName(specGoodsPrice.getKeyName());
            orderGoods.setGoodsLogo(storeGoods.getGoodsLogo());

            orderGoodsList.add(orderGoods);

        }

        if(!orderGoodsService.saveBatch(orderGoodsList)){
            throw new LightSecurityException("操作失败");
        }

        return storeOrder.getOrderSn();
    }

    @Override
    public int checkStore(int goodsId, String spec_key) {
        QueryWrapper<StoreSpecGoodsPrice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("`key`",spec_key)
                .eq("goods_id", goodsId);
        return storeSpecGoodsPriceMapper.selectOne(queryWrapper).getStoreCount();
    }

    @Override
    public void updateCouponStatus(int id) {
        StoreCouponList storeCouponList = new StoreCouponList();
        storeCouponList.setId(id);
        storeCouponList.setUseTime(OrderUtil.getSecondTimestampTwo());
        storeCouponList.setStatus(1);
        storeCouponListMapper.updateById(storeCouponList);
        storeCouponMapper.incUserNum(id);
    }


    /**
     * 订单列表
     * @param status
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<OrderDTO> orderList(int status, int userId, int page,int limit) {
        QueryWrapper<StoreOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("deleted",0).eq("user_id",userId);
        // 0-全部 1-待付款 2-待发货 3-待收货 4-待评价
        switch (status){
            case 1:
                wrapper.eq("pay_status",0);
                break;
            case 2:
                wrapper.eq("pay_status",1).eq("shipping_status",0).eq("order_status",0);
                break;
            case 3:
                wrapper.eq("shipping_status",1);
                break;
            case 4:
                wrapper.eq("order_status",1);
                break;
        }

        Page<StoreOrder> pageModel = new Page<>(page, limit);
        IPage<StoreOrder> pageList = baseMapper.selectPage(pageModel,wrapper);
        List<StoreOrder> list = pageList.getRecords();


        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (StoreOrder storeOrder : list) {
            QueryWrapper<StoreOrderGoods> wrapperGoods = new QueryWrapper<>();
            wrapperGoods.eq("order_id",storeOrder.getOrderId()).groupBy("goods_id");
            List<StoreOrderGoods> storeGoods = storeOrderGoodsMapper.selectList(wrapperGoods);
            List<StoreOrderGoods> newStoreGoods = new ArrayList<>();
            for (StoreOrderGoods storeGood : storeGoods) {
                QueryWrapper<StoreOrderGoods> wrapper2 = new QueryWrapper<>();
                wrapper2.eq("order_id",storeOrder.getOrderId()).eq("goods_id",storeGood.getGoodsId());
                storeGood.setSpecList(orderGoodsMapper.toDto(storeOrderGoodsMapper.selectList(wrapper2)));
                newStoreGoods.add(storeGood);
            }
            OrderDTO orderDTO = orderMapper.toDto(storeOrder);
            orderDTO.setGoodsList(newStoreGoods);
            //处理订单状态
            orderDTO.setStatus(OrderUtil.orderStatus(storeOrder.getPayStatus(),
                    storeOrder.getShippingStatus(),storeOrder.getOrderStatus()));

            orderDTOList.add(orderDTO);

        }

        return orderDTOList;
    }


    /**
     * 订单详情
     * @param orderId
     * @param userId
     * @return
     */
    @Override
    public OrderDTO orderDetail(String orderId,int userId) {
        QueryWrapper<StoreOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("deleted",0).eq("user_id",userId).and(
                i->i.eq("order_id",orderId).or().eq("order_sn",orderId));
        StoreOrder storeOrder = baseMapper.selectOne(wrapper);

        QueryWrapper<StoreOrderGoods> wrapperGoods = new QueryWrapper<>();
        wrapperGoods.eq("order_id",storeOrder.getOrderId()).groupBy("goods_id");
        List<StoreOrderGoods> orderGoodsList = storeOrderGoodsMapper
                .selectList(wrapperGoods);
        OrderDTO orderDTO = orderMapper.toDto(storeOrder);
        List<StoreOrderGoods> newStoreGoods = new ArrayList<>();
        for (StoreOrderGoods storeGood : orderGoodsList) {
            QueryWrapper<StoreOrderGoods> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("order_id",storeOrder.getOrderId()).eq("goods_id",storeGood.getGoodsId());
            storeGood.setSpecList(orderGoodsMapper.toDto(storeOrderGoodsMapper.selectList(wrapper2)));
            newStoreGoods.add(storeGood);
        }
        orderDTO.setGoodsList(newStoreGoods);
        //处理订单状态
        orderDTO.setStatus(OrderUtil.orderStatus(storeOrder.getPayStatus(),
                storeOrder.getShippingStatus(),storeOrder.getOrderStatus()));

        return orderDTO;
    }

    /**
     * 操作订单
     * @param orderId
     * @param type 操作类型 1-取消订单 2-确认收货 3-退货
     */
    @Override
    @Transactional
    public void orderHandle(int orderId, int type,int userId) {
        QueryWrapper<StoreOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("deleted",0).eq("user_id",userId).eq("order_id",orderId);
        StoreOrder storeOrder = baseMapper.selectOne(wrapper);
        if(ObjectUtil.isNull(storeOrder)){
            throw new LightSecurityException("订单不存在");
        }

        StoreOrder order = new StoreOrder();
        order.setOrderId(orderId);
        switch (type){
            case 1:
                if(storeOrder.getPayStatus() != 0){
                    throw new LightSecurityException("只有未付款订单才能取消");
                }
                order.setDeleted(1);
                order.setDeletedTime(OrderUtil.getSecondTimestampTwo());
                break;
            case 2:
                if(storeOrder.getShippingStatus() != 1){
                    throw new LightSecurityException("只能操作已经发货订单");
                }
                order.setOrderStatus(1);
                order.setConfirmTime(OrderUtil.getSecondTimestampTwo());
                break;
            case 3:
                if(storeOrder.getShippingStatus() == 1 && storeOrder.getOrderStatus() == 0){
                    throw new LightSecurityException("已发货订单才能操作退货");
                }
                order.setShippingStatus(2);
                break;
        }

        baseMapper.updateById(order);


    }

    /**
     *
     * @param type 1-积分 2-余额
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<StorePointsMoneyLog> pointsMoneyLogs(int type, int userId, int page, int limit) {
        QueryWrapper<StorePointsMoneyLog> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId).orderByDesc("create_time");
        if(type == 1){
            wrapper.gt("points_number",0);
        }else{
            wrapper.gt("money_number",0);
        }
        Page<StorePointsMoneyLog> pageModel = new Page<>(page, limit);
        IPage<StorePointsMoneyLog> pageList = storePointsMoneyLogMapper.selectPage(pageModel,wrapper);
        List<StorePointsMoneyLog> list = pageList.getRecords();

        return list;
    }

    @Override
    public Map<String, Integer> countInfo(int userId) {
        QueryWrapper<StoreOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("deleted",0).eq("user_id",userId).eq("pay_status",0);
        int unpayCount  = count(wrapper);

        QueryWrapper<StoreOrder> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("deleted",0).eq("user_id",userId)
                .eq("pay_status",1).eq("shipping_status",0);
        int undeliverCount  = count(wrapper1);

        QueryWrapper<StoreOrder> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("deleted",0).eq("user_id",userId)
                .eq("shipping_status",1).eq("order_status",0);
        int deliveringCount  = count(wrapper2);

        QueryWrapper<StoreOrder> wrapper3 = new QueryWrapper<>();
        wrapper3.eq("deleted",0).eq("user_id",userId)
                .eq("shipping_status",1).eq("order_status",1);
        int unevalCount  = count(wrapper3);

        QueryWrapper<StoreOrder> wrapper4 = new QueryWrapper<>();
        wrapper4.eq("deleted",0).eq("user_id",userId).gt("shipping_status",1);
        int returnCount  = count(wrapper4);

        Map<String,Integer> map = new HashMap<>();
        map.put("unpayCount",unpayCount);
        map.put("undeliverCount",undeliverCount);
        map.put("deliveringCount",deliveringCount);
        map.put("unevalCount",unevalCount);
        map.put("returnCount",returnCount);
        return map;
    }

    @Override
    public StoreOrder orderInfo(String orderId, int userId) {
        QueryWrapper<StoreOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("deleted",0).eq("user_id",userId).eq("pay_status",0).and(
                i->i.eq("order_id",orderId).or().eq("order_sn",orderId));
        StoreOrder storeOrder = baseMapper.selectOne(wrapper);
        return storeOrder;
    }

    /**
     * 订单处理
     * @param order
     */
    @Override
    @Transactional
    public void notifyHandle(StoreOrder order) {
        QueryWrapper<StoreOrderGoods> wrapperGoods = new QueryWrapper<>();
        wrapperGoods.eq("order_id",order.getOrderId());
        List<StoreOrderGoods> orderGoodsList = storeOrderGoodsMapper
                .selectList(wrapperGoods);
        for (StoreOrderGoods storeGood : orderGoodsList) {
            //更新库存
            storeSpecGoodsPriceMapper.decCount(storeGood.getGoodsId(),
                    storeGood.getSpecKey(),storeGood.getGoodsNum());
            //增加商品销量
            storeGoodsMapper.incSaleNum(storeGood.getGoodsId()
                    ,storeGood.getGoodsNum());

        }

        //更新订单状态
        StoreOrder storeOrder =  new StoreOrder();
        storeOrder.setOrderId(order.getOrderId());
        storeOrder.setPayStatus(1);
        storeOrder.setPayMethod(1);
        storeOrder.setUserMoney(order.getOrderAmount());
        storeOrder.setPayTime(OrderUtil.getSecondTimestampTwo());
        updateById(storeOrder);

        //增加流水
        StorePointsMoneyLog storePointsMoneyLog = new StorePointsMoneyLog();
        storePointsMoneyLog.setUserId(order.getUserId());
        storePointsMoneyLog.setTitle("购买商品");
        storePointsMoneyLog.setMoneyNumber(order.getOrderAmount());
        storePointsMoneyLog.setType(2);
        storePointsMoneyLog.setCreateTime(OrderUtil.getSecondTimestampTwo());

        storePointsMoneyLogMapper.insert(storePointsMoneyLog);

    }

    /**
     * 余额支付
     */
    @Override
    @Transactional
    public void payYue(StoreOrder order) {
        //判断库存
        QueryWrapper<StoreOrderGoods> wrapperGoods = new QueryWrapper<>();
        wrapperGoods.eq("order_id",order.getOrderId());
        List<StoreOrderGoods> orderGoodsList = storeOrderGoodsMapper
                .selectList(wrapperGoods);
        for (StoreOrderGoods storeGood : orderGoodsList) {
            int storeCount = checkStore(storeGood.getGoodsId(),storeGood.getSpecKey());
            if(storeCount < storeGood.getGoodsNum()){
                throw new LightSecurityException("库存不足");
            }

            //更新库存
            storeSpecGoodsPriceMapper.decCount(storeGood.getGoodsId(),
                    storeGood.getSpecKey(),storeGood.getGoodsNum());
            //增加商品销量
            storeGoodsMapper.incSaleNum(storeGood.getGoodsId()
                    ,storeGood.getGoodsNum());

        }

        //余额判断
        StoreMember member = memberService.getById(order.getUserId());
        if(order.getOrderAmount().doubleValue() > member.getUserMoney().doubleValue()){
            throw new LightSecurityException("余额不足");
        }

        //更新订单状态
        StoreOrder storeOrder =  new StoreOrder();
        storeOrder.setOrderId(order.getOrderId());
        storeOrder.setPayStatus(1);
        storeOrder.setPayMethod(2);
        storeOrder.setUserMoney(order.getOrderAmount());
        storeOrder.setPayTime(OrderUtil.getSecondTimestampTwo());
        updateById(storeOrder);

        //减去余额
        int result = storeMemberMapper.decUserMony(order.getUserId(),
                order.getOrderAmount().doubleValue());
        if(result <= 0){
            throw new LightSecurityException("减余额失败");
        }

        //增加流水
        StorePointsMoneyLog storePointsMoneyLog = new StorePointsMoneyLog();
        storePointsMoneyLog.setUserId(order.getUserId());
        storePointsMoneyLog.setTitle("购买商品");
        storePointsMoneyLog.setMoneyNumber(order.getOrderAmount());
        storePointsMoneyLog.setType(2);
        storePointsMoneyLog.setCreateTime(OrderUtil.getSecondTimestampTwo());

        storePointsMoneyLogMapper.insert(storePointsMoneyLog);

    }




}

package com.ruoyi.app.modular.shop.service.impl;


//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmuch.lightsecurity.exception.LightSecurityException;
import com.ruoyi.app.common.persistence.dao.*;
import com.ruoyi.app.common.persistence.model.*;
import com.ruoyi.app.common.utils.OrderUtil;
import com.ruoyi.app.modular.shop.service.IOrderService;
import com.ruoyi.app.modular.shop.service.dto.CouponDTO;
import com.ruoyi.app.modular.shop.service.vo.CartAttrVO;
import com.ruoyi.app.modular.shop.service.vo.CartVO;
import com.ruoyi.app.modular.shop.service.vo.OrderVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


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
}

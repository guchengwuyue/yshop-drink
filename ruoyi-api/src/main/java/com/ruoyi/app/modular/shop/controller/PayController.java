package com.ruoyi.app.modular.shop.controller;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.itmuch.lightsecurity.jwt.UserOperator;
import com.ruoyi.app.common.R;
import com.ruoyi.app.common.persistence.dao.StoreOrderGoodsMapper;
import com.ruoyi.app.common.persistence.model.StoreMember;
import com.ruoyi.app.common.persistence.model.StoreOrder;
import com.ruoyi.app.common.persistence.model.StoreOrderGoods;
import com.ruoyi.app.modular.member.service.IMemberService;
import com.ruoyi.app.modular.shop.service.impl.OrderServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PayController
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/9/26
 **/

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "支付模块", tags = "支付模块", description = "支付模块")
public class PayController {
    private final UserOperator userOperator;
    private final OrderServiceImpl orderService;
    private final WxPayService wxPayService;
    private final IMemberService memberService;
    private final StoreOrderGoodsMapper storeOrderGoodsMapper;

    @PostMapping(value = "/shop/pay-confirm-money")
    @ApiOperation(value = "支付前检测获取的价格",notes = "支付前检测获取的价格")
    public R confirmPayMoney(@Validated @RequestBody String jsonStr){
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String orderNo = jsonObject.get("order_no").toString();
        if(StrUtil.isEmpty(orderNo)){
            return R.error(4000,"订单号缺失");
        }
        int userId = userOperator.getUser().getId();
        StoreOrder storeOrder = orderService.orderInfo(orderNo,userId);
        if(ObjectUtil.isNull(storeOrder)){
            return R.error(4000,"订单不存在");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("order_amount",storeOrder.getOrderAmount());

        return R.success(map);
    }

    /**
     * 调用统一下单接口，并组装生成支付所需参数对象.
     *
     * @param request 统一下单请求参数
     * @param <T>     请使用{@link com.github.binarywang.wxpay.bean.order}包下的类
     * @return 返回 {@link com.github.binarywang.wxpay.bean.order}包下的类对象
     */
    @PostMapping(value = "/shop/pay-do-pay")
    @ApiOperation(value = "开始支付",notes = "开始支付")
    public R doPay(@Validated @RequestBody String jsonStr){
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String orderNo = jsonObject.get("order_no").toString();
        //1-余额支付 2-微信支付
        int payType = 1;

        if(StrUtil.isEmpty(orderNo)){
            return R.error(4000,"订单号缺失");
        }
        if(ObjectUtil.isNotNull(jsonObject.get("pay_type"))){
            payType = Integer.valueOf(jsonObject.get("pay_type").toString());
        }

        int userId = userOperator.getUser().getId();
        StoreOrder storeOrder = orderService.orderInfo(orderNo,userId);
        if(ObjectUtil.isNull(storeOrder)){
            return R.error(4000,"订单不存在");
        }


        if(payType == 1){
            orderService.payYue(storeOrder);
            return R.success("余额支付成功");
        }else{
            //判断库存
            QueryWrapper<StoreOrderGoods> wrapperGoods = new QueryWrapper<>();
            wrapperGoods.eq("order_id",storeOrder.getOrderId());
            List<StoreOrderGoods> orderGoodsList = storeOrderGoodsMapper
                    .selectList(wrapperGoods);
            for (StoreOrderGoods storeGood : orderGoodsList) {
                int storeCount = orderService.checkStore(storeGood.getGoodsId(),storeGood.getSpecKey());
                if(storeCount < storeGood.getGoodsNum()){
                    return R.error(4000,"库存不足");
                }
            }

            WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
            StoreMember member = memberService.getById(userId);
            orderRequest.setBody("商品购买");
            orderRequest.setOutTradeNo(orderNo);
            BigDecimal bigDecimal = new BigDecimal(100);
            orderRequest.setTotalFee(bigDecimal.multiply(storeOrder.getOrderAmount()).intValue());//元转成分
            orderRequest.setOpenid(member.getOpenid());
            orderRequest.setSpbillCreateIp("127.0.0.1");
            orderRequest.setNotifyUrl("https://app2.dayouqiantu.cn/shop/notify");
            orderRequest.setTradeType("JSAPI");
            try {
                WxPayMpOrderResult orderResult = wxPayService.createOrder(orderRequest);
                return R.success(orderResult);
            } catch (WxPayException e) {
                return R.error(4000,e.getMessage());
            }

        }



    }

    @PostMapping(value = "/shop/notify")
    @ApiOperation(value = "异步通知",notes = "异步通知")
    public String payNotify(@RequestBody String xmlData){
        try {
            WxPayOrderNotifyResult notifyResult = wxPayService.parseOrderNotifyResult(xmlData);
            String orderNo = notifyResult.getOutTradeNo();
            QueryWrapper<StoreOrder> wrapper = new QueryWrapper<>();
            wrapper.eq("deleted",0).eq("pay_status",0).eq("order_sn",orderNo);
            StoreOrder storeOrder = orderService.getOne(wrapper);
            if(ObjectUtil.isNull(storeOrder)) {
                return WxPayNotifyResponse.success("处理成功!");
            }
            orderService.notifyHandle(storeOrder);
            return WxPayNotifyResponse.success("处理成功!");
        } catch (WxPayException e) {
            log.error(e.getMessage());
            return WxPayNotifyResponse.fail(e.getMessage());
        }


       // return WxPayNotifyResponse.success("处理成功!");


    }




}

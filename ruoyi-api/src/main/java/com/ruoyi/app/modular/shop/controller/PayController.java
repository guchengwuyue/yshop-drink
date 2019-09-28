package com.ruoyi.app.modular.shop.controller;


import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.service.WxPayService;
import com.itmuch.lightsecurity.jwt.UserOperator;
import com.ruoyi.app.common.R;
import com.ruoyi.app.common.persistence.model.StoreOrder;
import com.ruoyi.app.modular.shop.service.dto.OrderDTO;
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

import java.util.HashMap;
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

    @PostMapping(value = "/shop/pay-do-pay")
    @ApiOperation(value = "开始支付",notes = "开始支付")
    public R doPay(@Validated @RequestBody String jsonStr,
                   WxPayUnifiedOrderRequest orderRequest){
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
            return R.error(4000,"微信支付开发中");
            //todo 微信支付
//            try {
//                //WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
//                orderRequest.setBody("主题");
//                orderRequest.setOutTradeNo("订单号");
//                orderRequest.setTotalFee(1);//元转成分
//                orderRequest.setOpenid("openId");
//                orderRequest.setSpbillCreateIp("userIp");
//                //orderRequest.setTimeStart("yyyyMMddHHmmss");
//               // orderRequest.setTimeExpire("yyyyMMddHHmmss");
//
//                return R.success(wxPayService.createOrder(orderRequest));
//            } catch (Exception e) {
//                log.error("微信支付失败！订单号：{},原因:{}", orderNo, e.getMessage());
//                e.printStackTrace();
//                return R.error(4000,"支付失败，请稍后重试！");
//            }

        }


       // return R.success("");

    }


}

/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.yshop.module.message.supply;

import cn.hutool.core.util.StrUtil;
import co.yixiang.yshop.framework.common.constant.ShopConstants;
import co.yixiang.yshop.framework.common.enums.ShopCommonEnum;
import co.yixiang.yshop.framework.common.exception.ErrorCode;
import co.yixiang.yshop.module.member.api.user.dto.WechatUserDto;
import co.yixiang.yshop.module.member.dal.dataobject.user.MemberUserDO;
import co.yixiang.yshop.module.member.service.user.MemberUserService;
import co.yixiang.yshop.module.message.dal.dataobject.wechattemplate.WechatTemplateDO;
import co.yixiang.yshop.module.message.enums.WechatTempateEnum;
import co.yixiang.yshop.module.message.service.wechattemplate.WechatTemplateService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static co.yixiang.yshop.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * @ClassName 微信公众号模板通知
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/6/27
 **/
@Slf4j
@Service
public class WeixinTemplateService {


    @Resource
    private MemberUserService userService;
    @Resource
    private WechatTemplateService wechatTemplateService;
    @Resource
    private WxMpService wxMpService;




    /**
     * 充值成功通知
     * @param time 时间
     * @param price 金额
     * @param uid uid
     */
    public void rechargeSuccessNotice(String time,String price,Long uid){
        String openid = this.getUserOpenid(uid);

        if(StrUtil.isBlank(openid)) {
            return;
        }

        Map<String,String> map = new HashMap<>();
        map.put("first","您的账户金币发生变动，详情如下：");
        map.put("keyword1","充值");
        map.put("keyword2",time);
        map.put("keyword3",price);
        map.put("remark", ShopConstants.YSHOP_WECHAT_PUSH_REMARK);
        String tempId = this.getTempId(WechatTempateEnum.RECHARGE_SUCCESS.getValue());
        if(StrUtil.isNotBlank(tempId)) {
            this.sendWxMpTemplateMessage( openid, tempId, "https://www.yixiang.co",map);
        }
    }


    /**
     * 支付成功通知
     * @param orderId 订单号
     * @param price 金额
     * @param uid uid
     */
    public void paySuccessNotice(String orderId,String price,Long uid){

        String openid = this.getUserOpenid(uid);

        if(StrUtil.isBlank(openid)) {
            return;
        }

        Map<String,String> map = new HashMap<>();
        map.put("first","您的订单已支付成功，我们会尽快为您发货。");
        //订单号
        map.put("keyword1",orderId);
        map.put("keyword2",price);
        map.put("remark",ShopConstants.YSHOP_WECHAT_PUSH_REMARK);
        String tempId = this.getTempId(WechatTempateEnum.PAY_SUCCESS.getValue());
        if(StrUtil.isNotBlank(tempId)) {
            this.sendWxMpTemplateMessage( openid,tempId, "https://www.yixiang.co",map);
        }
    }




    /**
     * 退款成功通知
     * @param orderId 订单号
     * @param price 金额
     * @param uid uid
     * @param time 时间
     */
    public void refundSuccessNotice(String title,String orderId,String price,Long uid,String time){

        String openid = this.getUserOpenid(uid);

        if(StrUtil.isBlank(openid)) {
            return;
        }

        Map<String,String> map = new HashMap<>();
        map.put("first",title);
        //订单号
        map.put("keyword1",orderId);
        map.put("keyword2",price);
        map.put("keyword3", time);
        map.put("remark",ShopConstants.YSHOP_WECHAT_PUSH_REMARK);
        String tempId = this.getTempId(WechatTempateEnum.REFUND_SUCCESS.getValue());
        if(StrUtil.isNotBlank(tempId)) {
            this.sendWxMpTemplateMessage( openid,tempId, "https://www.yixiang.co",map);
        }
    }


    /**
     * 发货成功通知
     * @param orderId 单号
     * @param deliveryName 快递公司
     * @param deliveryId 快递单号
     * @param uid uid
     */
    public void deliverySuccessNotice(String orderId,String deliveryName,
                                      String deliveryId,Long uid){

        String openid = this.getUserOpenid(uid);

        if(StrUtil.isEmpty(openid)) {
            return;
        }

        Map<String,String> map = new HashMap<>();
        map.put("first","亲，宝贝已经启程了，好想快点来到你身边。");
        map.put("keyword2",deliveryName);
        map.put("keyword1",orderId);
        map.put("keyword3",deliveryId);
        map.put("remark",ShopConstants.YSHOP_WECHAT_PUSH_REMARK);
        String tempId = this.getTempId(WechatTempateEnum.DELIVERY_SUCCESS.getValue());
        if(StrUtil.isNotBlank(tempId)) {
            this.sendWxMpTemplateMessage( openid,tempId, "https://www.yixiang.co",map);
        }
    }


    /**
     * 构建微信模板通知
     * @param openId 单号
     * @param templateId 模板id
     * @param url 跳转url
     * @param map map内容
     * @return String
     */
    private String sendWxMpTemplateMessage(String openId, String templateId, String url, Map<String,String> map){
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(openId)
                .templateId(templateId)
                .url(url)
                .build();
        map.forEach( (k,v)-> { templateMessage.addData(new WxMpTemplateData(k, v, "#000000"));} );
        String msgId = null;
        try {
            msgId =   wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return msgId;
    }





    /**
     * 获取模板消息id
     * @param key 模板key
     * @return string
     */
    private String getTempId(String key){
        WechatTemplateDO yxWechatTemplate = wechatTemplateService.lambdaQuery()
                .eq(WechatTemplateDO::getType,"template")
                .eq(WechatTemplateDO::getTempkey,key)
                .one();
        if (yxWechatTemplate == null) {
            throw exception(new ErrorCode(9999999,"请后台配置key:" + key + "订阅消息id"));
        }

        if(ShopCommonEnum.IS_STATUS_0.getValue().equals(yxWechatTemplate.getStatus())){
            return "";
        }

        return yxWechatTemplate.getTempid();
    }



    /**
     * 获取openid
     * @param uid uid
     * @return String
     */
    private String getUserOpenid(Long uid){
        MemberUserDO yxUser = userService.getById(uid);
        if(yxUser == null) {
            return "";
        }

        WechatUserDto wechatUserDto = yxUser.getWxProfile();
        if(wechatUserDto == null) {
            return "";
        }
        if(StrUtil.isBlank(wechatUserDto.getOpenid())) {
            return "";
        }
        return wechatUserDto.getOpenid();

    }


}

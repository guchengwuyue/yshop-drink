package co.yixiang.yshop.module.message.mq.consumer;

import co.yixiang.yshop.framework.mq.core.stream.AbstractStreamMessageListener;
import co.yixiang.yshop.module.message.enums.WechatTempateEnum;
import co.yixiang.yshop.module.message.mq.message.WeixinNoticeMessage;
import co.yixiang.yshop.module.message.supply.WeiXinSubscribeService;
import co.yixiang.yshop.module.message.supply.WeixinTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * 消息队列处理消息推送
 */
@Component
@Slf4j
public class WeixinNoticeConsumer extends AbstractStreamMessageListener<WeixinNoticeMessage> {

    @Resource
    private WeiXinSubscribeService weiXinSubscribeService;
    @Resource
    private WeixinTemplateService weixinTemplateService;



    @Override
    public void onMessage(WeixinNoticeMessage message) {
        log.info("[onMessage][消息内容({})]", message);
        //公众号
        if(WechatTempateEnum.TEMPLATES.getValue().equals(message.getType())) {
            if(WechatTempateEnum.PAY_SUCCESS.getValue().equals(message.getTempkey())){
                weixinTemplateService.paySuccessNotice(message.getOrderId(),message.getPrice(),message.getUid());
            }else if(WechatTempateEnum.DELIVERY_SUCCESS.getValue().equals(message.getTempkey())){
                weixinTemplateService.deliverySuccessNotice(message.getOrderId(),message.getDeliveryName(),
                        message.getDeliveryId(),message.getUid());
            } else if(WechatTempateEnum.REFUND_SUCCESS.getValue().equals(message.getTempkey())){
                weixinTemplateService.refundSuccessNotice("您的订单退款申请被通过，钱款将很快还至您的支付账户。",
                        message.getOrderId(),message.getDeliveryName(),message.getUid(),message.getTime());
            }

        }else if(WechatTempateEnum.SUBSCRIBE.getValue().equals(message.getType())){
            //小程序
            if(WechatTempateEnum.PAY_SUCCESS.getValue().equals(message.getTempkey())){
                weiXinSubscribeService.paySuccessNotice(message.getNumberId().toString(),message.getProductName()
                        ,message.getShopName(),message.getUid(),message.getId(),message.getOrderId());
            }else if(WechatTempateEnum.DELIVERY_SUCCESS.getValue().equals(message.getTempkey())){
                weiXinSubscribeService.deliverySuccessNotice(message.getOrderId(),message.getDeliveryName(),
                        message.getDeliveryId(),message.getUid());
            } else if(WechatTempateEnum.REFUND_SUCCESS.getValue().equals(message.getTempkey())){
                weiXinSubscribeService.refundSuccessNotice(message.getOrderId(),message.getDeliveryName(),
                        message.getUid(),message.getTime());
            }
        }


    }
}

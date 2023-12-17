package co.yixiang.yshop.module.message.supply;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
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
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static co.yixiang.yshop.framework.common.exception.util.ServiceExceptionUtil.exception;

;

/**
 * 小程序订阅消息通知
 */
@Service
public class WeiXinSubscribeService {

    @Resource
    private MemberUserService userService;
    @Resource
    private WechatTemplateService wechatTemplateService;
    @Resource
    private WxMaService wxMaService;

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
            this.sendSubscribeMsg( openid, tempId, "/user/account",map);
        }
    }


    /**
     * 支付成功通知
     * @param numberId 取餐号
     * @param productName 商品名称
     * @param shopName 门店名称
     * @param uid uid
     * @param id 订单ID
     */
    public void paySuccessNotice(String numberId,String productName,String shopName,Long uid,Long id,String orderId){

        String openid = this.getUserOpenid(uid);
        if(StrUtil.isBlank(openid)) {
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Map<String,String> map = new HashMap<>();
        //取餐号
        map.put("character_string1",numberId);
        //商品名
        map.put("thing2", productName);
        map.put("time3",simpleDateFormat.format(new Date()));
        //下单门店
        map.put("thing4",shopName);
        map.put("thing5","意向点餐系统为您服务！");
        String tempId = this.getTempId(WechatTempateEnum.PAY_SUCCESS.getValue());
        if(StrUtil.isNotBlank(tempId)) {
            this.sendSubscribeMsg( openid,tempId, "/pages/components/pages/orders/detail?id="+orderId,map);
        }
    }

    /**
     * 退款成功通知
     * @param orderId 订单号
     * @param price 金额
     * @param uid uid
     * @param time 时间
     */
    public void refundSuccessNotice(String orderId,String price,Long uid,String time){

        String openid = this.getUserOpenid(uid);

        if(StrUtil.isBlank(openid)) {
            return;
        }

        Map<String,String> map = new HashMap<>();
        map.put("first","您的订单退款申请被通过，钱款将很快还至您的支付账户。");
        //订单号
        map.put("keyword1",orderId);
        map.put("keyword2",price);
        map.put("keyword3", time);
        map.put("remark",ShopConstants.YSHOP_WECHAT_PUSH_REMARK);
        String tempId = this.getTempId(WechatTempateEnum.REFUND_SUCCESS.getValue());
        if(StrUtil.isNotBlank(tempId)) {
            this.sendSubscribeMsg( openid,tempId, "/order/detail/"+orderId,map);
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
            this.sendSubscribeMsg( openid,tempId, "/order/detail/"+orderId,map);
        }
    }


    /**
     * 构建小程序一次性订阅消息
     * @param openId 单号
     * @param templateId 模板id
     * @param page 跳转页面
     * @param map map内容
     * @return String
     */
    private void sendSubscribeMsg(String openId, String templateId, String page, Map<String,String> map){
        WxMaSubscribeMessage wxMaSubscribeMessage = WxMaSubscribeMessage.builder()
                .toUser(openId)
                .templateId(templateId)
                .page(page)
                .build();
        map.forEach( (k,v)-> { wxMaSubscribeMessage.addData(new WxMaSubscribeMessage.MsgData(k, v));} );
       // WxMaService wxMaService = WxMaConfiguration.getWxMaService();
        try {
            wxMaService.getMsgService().sendSubscribeMsg(wxMaSubscribeMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取模板消息id
     * @param key 模板key
     * @return string
     */
    private String getTempId(String key){
        WechatTemplateDO yxWechatTemplate = wechatTemplateService.lambdaQuery()
                .eq(WechatTemplateDO::getType,"subscribe")
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
        if(StrUtil.isBlank(yxUser.getRoutineOpenid())) {
            return "";
        }
        return yxUser.getRoutineOpenid();

    }
}

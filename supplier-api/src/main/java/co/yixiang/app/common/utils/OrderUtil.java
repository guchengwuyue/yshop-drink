package co.yixiang.app.common.utils;

import cn.hutool.core.date.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName OrderUtil
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/9/13
 **/
public class OrderUtil {

    /**
     * 时间戳订单号
     * @return
     */
    public static String orderSn(){
        Date date = DateUtil.date();
        return DateUtil.format(date,"yyyyMMddHHmmssSSS");
    }


    /**
     * 获取精确到秒的时间戳
     * @param date
     * @return
     **/
    public static int getSecondTimestampTwo(){
        String timestamp = String.valueOf(new Date().getTime()/1000);
        return Integer.valueOf(timestamp);
    }


    public static int orderStatus(int payStatus,int shippingStatus,int orderStatus){
        //todo 0-待付款 1-已付款,待发货 2-已发货,待收货 3-退货中 4-退货完成 5-待评价 6-已评价
        int status = 0;

        if(payStatus == 1 && shippingStatus == 0 && orderStatus == 0){
            status = 1;
        }else if(shippingStatus == 1 && orderStatus == 0){
            status = 2;
        }else if(shippingStatus == 2) {
            status = 3;
        }else if(shippingStatus == 3) {
            status = 4;
        }else if (orderStatus == 1){
            status = 5;
        }else if (orderStatus == 2){
            status = 6;
        }

        return status;

    }

}

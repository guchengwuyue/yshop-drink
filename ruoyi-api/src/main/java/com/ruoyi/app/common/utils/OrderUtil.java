package com.ruoyi.app.common.utils;

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
}

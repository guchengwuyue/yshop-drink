package com.ruoyi.common.utils;

/**
 * @ClassName OrderUtils
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/8/30
 **/
public class OrderUtils {

    public static int orderStatus(int payStatus,int shippingStatus,int orderStatus,int deleted){
        //todo -1 已经取消 0-待付款 1-已付款,待发货 2-已发货,待收货 3-退货中 4-退货完成 5-待评价 6-已评价
        int status = 0;
        if(deleted == 1){
            return -1;
        }
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

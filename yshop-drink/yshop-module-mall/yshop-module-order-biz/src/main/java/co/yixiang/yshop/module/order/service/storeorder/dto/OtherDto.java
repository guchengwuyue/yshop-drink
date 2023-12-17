package co.yixiang.yshop.module.order.service.storeorder.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName OtherDto
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/10/27
 **/
@Data
public class OtherDto implements Serializable {
    //线下包邮
    private String offlinePostage;
    //积分抵扣
    private String integralRatio;

    //最大
    private String integralMax;

    //满多少
    private String integralFull;

}

package com.ruoyi.app.modular.shop.service.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName OrderVo
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/9/13
 **/

@Data
public class OrderVo {

    @NotNull
    private String orderFrom; //订单来源1-购物车提交 2-直接提交

    private String goodsIds; //order_from=1时候需要传购物车商品id字符串逗号隔开例如"5,6,7"

    private CartVO cartVO; //order_from=2直接提交

    @NotNull
    private String addressId;

    private String couponId;
}

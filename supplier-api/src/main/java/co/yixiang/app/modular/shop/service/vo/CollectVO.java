package co.yixiang.app.modular.shop.service.vo;

import lombok.Data;

import javax.validation.constraints.Min;


@Data
public class CollectVO {

    @Min(message = "商品id必须为数值",value = 0)
    private int goodsId;







}

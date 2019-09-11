package com.ruoyi.app.modular.shop.service.dto;



import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CartDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String goodsName;

    private BigDecimal goodsPrice;

    private Integer goodsNum;

    private String specKeyName;

}

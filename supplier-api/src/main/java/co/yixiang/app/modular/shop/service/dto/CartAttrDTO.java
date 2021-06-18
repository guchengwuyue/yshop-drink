package co.yixiang.app.modular.shop.service.dto;



import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CartAttrDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private BigDecimal goodsPrice;

    private int goodsNum;

    private String specKeyName;

}

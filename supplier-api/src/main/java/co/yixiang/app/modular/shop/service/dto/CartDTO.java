package co.yixiang.app.modular.shop.service.dto;



import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class CartDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer goodsId;

    private String goodsName;

    private BigDecimal goodsPrice;

    private String goodsSn;

    private String goodsLogo;

    private List<CartAttrDTO> specList;

}

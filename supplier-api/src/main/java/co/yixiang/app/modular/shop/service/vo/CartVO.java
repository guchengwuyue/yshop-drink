package co.yixiang.app.modular.shop.service.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class CartVO {
    @NotBlank(message = "goodsId必传")
    private String goodsId;

    private List<CartAttrVO> cartAttr;

}

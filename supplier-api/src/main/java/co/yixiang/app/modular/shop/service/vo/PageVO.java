package co.yixiang.app.modular.shop.service.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class PageVO {
    //@NotBlank(message = "code必传")
    @Min(message = "page为数值其值必须大于1",value = 1)
    private int page;
    @Min(message = "limit为数值其值必须大于1",value = 1)
    private int limit;






}

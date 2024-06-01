package co.yixiang.yshop.module.order.service.storeorder.dto;

import lombok.*;

import java.math.BigDecimal;

/**
 * @ClassName TemplateDTO
 * @Author hupeng <610796224@qq.com>
 * @Date 2024/2/17
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemplateDto {
    private Double number;
    private BigDecimal price;
    private Double first;
    private BigDecimal firstPrice;
    private Double yContinue;
    private BigDecimal continuePrice;
    private Integer tempId;
    private Integer cityId;
}

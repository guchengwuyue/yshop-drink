package co.yixiang.yshop.module.order.service.storeorder.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName OrderExtendDto
 * @Author hupeng <610796224@qq.com>
 * @Date 2023/6/19
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderExtendDto implements Serializable {

    @Schema(description = "唯一的key", required = true)
    private String key;

    @Schema(description = "订单ID", required = true)
    private String orderId;

    @Schema(description = "微信相关配置", required = true)
    private Map<String,String> jsConfig;
}

package co.yixiang.yshop.module.express.kdniao.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 快递鸟-物流-查询参数
 * </p>
 *
 * @author hupeng
 * @date 2023/7/21
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
//快递鸟-物流-查询参数
public class KdniaoApiDTO extends KdniaoApiBaseDTO {

    //快递公司编码   ZTO
    private String shipperCode;

    //快递单号
    private String logisticCode;

    //是否收费 true=免费 false 收费
    private Boolean isFree;

}

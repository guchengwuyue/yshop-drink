package com.ruoyi.app.distribution.web.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.ruoyi.app.common.web.param.QueryParam;

/**
 * <p>
 * 代发货表 查询参数对象
 * </p>
 *
 * @author hupeng
 * @date 2019-09-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="StoreSubstituteQueryParam对象", description="代发货表查询参数")
public class StoreSubstituteQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}

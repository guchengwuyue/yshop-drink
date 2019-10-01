package com.ruoyi.plus.system.web.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.ruoyi.plus.common.web.param.QueryParam;

/**
 * <p>
 * SystemUser 查询参数对象
 * </p>
 *
 * @author geekidea
 * @date 2019-08-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysUserQueryParam对象", description="SystemUser查询参数")
public class SysUserQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}



package com.ruoyi.app.common.web.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Data
@ApiModel("ID参数")
public class IdParam implements Serializable {
    private static final long serialVersionUID = -5353973980674510450L;
    
    @NotBlank(message="ID不能为空")
    private String id;
}

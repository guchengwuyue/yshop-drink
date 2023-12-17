package co.yixiang.yshop.module.shop.controller.admin.service.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 我的服务更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ServiceUpdateReqVO extends ServiceBaseVO {

    @Schema(description = "id", required = true, example = "6335")
    @NotNull(message = "id不能为空")
    private Long id;

}

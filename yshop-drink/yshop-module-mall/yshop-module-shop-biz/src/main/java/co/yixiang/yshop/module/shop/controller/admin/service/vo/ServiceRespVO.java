package co.yixiang.yshop.module.shop.controller.admin.service.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 我的服务 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ServiceRespVO extends ServiceBaseVO {

    @Schema(description = "id", required = true, example = "6335")
    private Long id;

    @Schema(description = "添加时间")
    private LocalDateTime createTime;

}

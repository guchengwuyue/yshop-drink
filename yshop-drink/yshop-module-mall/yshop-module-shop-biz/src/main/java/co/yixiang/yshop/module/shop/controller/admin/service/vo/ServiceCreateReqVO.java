package co.yixiang.yshop.module.shop.controller.admin.service.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 我的服务创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ServiceCreateReqVO extends ServiceBaseVO {

}

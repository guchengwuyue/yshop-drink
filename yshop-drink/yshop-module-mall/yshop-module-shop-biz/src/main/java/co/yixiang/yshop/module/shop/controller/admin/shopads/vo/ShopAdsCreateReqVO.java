package co.yixiang.yshop.module.shop.controller.admin.shopads.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 广告图管理创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopAdsCreateReqVO extends ShopAdsBaseVO {

}

package co.yixiang.yshop.module.shop.controller.admin.shopads.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
* 广告图管理 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ShopAdsBaseVO {

    @Schema(description = "图片")
    private String image;

    @Schema(description = "是否显现", required = true)
    @NotNull(message = "是否显现不能为空")
    private Integer isSwitch;

    @Schema(description = "权重", required = true)
    @NotNull(message = "权重不能为空")
    private Integer weigh;

    @Schema(description = "所支持的店铺id用','区分，0代表全部", required = true, example = "2901")
    @NotNull(message = "所支持的店铺id用','区分，0代表全部不能为空")
    private String shopId;

    private String shopName;

}

package co.yixiang.yshop.module.member.controller.app.address.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户地址表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2023-6-28
 */
@Data
@Schema(description = "用户地址表查询参数")
public class AppUserAddressQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户地址id", required = true, example = "24169")
    private Long id;

    @Schema(description = "用户id", required = true, example = "24169")
    private Long uid;

    @Schema(description = "收货人姓名", required = true, example = "24169")
    private String realName;

    @Schema(description = "收货人电话", required = true, example = "24169")
    private String phone;

    @Schema(description = "收货人所在省", required = true, example = "24169")
    private String address;

    @Schema(description = "收货人详细地址", required = true, example = "24169")
    private String detail;

    @Schema(description = "经度", required = true, example = "24169")
    private String longitude;

    @Schema(description = "纬度", required = true, example = "24169")
    private String latitude;

    @Schema(description = "是否默认", required = true, example = "24169")
    private Integer isDefault;


}

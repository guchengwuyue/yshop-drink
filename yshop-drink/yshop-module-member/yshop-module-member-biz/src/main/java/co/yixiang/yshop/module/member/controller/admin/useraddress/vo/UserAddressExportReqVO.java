package co.yixiang.yshop.module.member.controller.admin.useraddress.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import co.yixiang.yshop.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static co.yixiang.yshop.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 用户地址 Excel 导出 Request VO，参数和 UserAddressPageReqVO 是一致的")
@Data
public class UserAddressExportReqVO {

    @Schema(description = "用户id", example = "25124")
    private Long uid;

    @Schema(description = "收货人姓名", example = "李四")
    private String realName;

    @Schema(description = "收货人电话")
    private String phone;

    @Schema(description = "收货人所在省")
    private String province;

    @Schema(description = "收货人所在市")
    private String city;

    @Schema(description = "城市id", example = "15595")
    private Integer cityId;

    @Schema(description = "收货人所在区")
    private String district;

    @Schema(description = "收货人详细地址")
    private String detail;

    @Schema(description = "邮编")
    private String postCode;

    @Schema(description = "经度")
    private String longitude;

    @Schema(description = "纬度")
    private String latitude;

    @Schema(description = "是否默认")
    private Byte isDefault;

    @Schema(description = "添加时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}

package co.yixiang.yshop.module.member.dal.dataobject.useraddress;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import co.yixiang.yshop.framework.mybatis.core.dataobject.BaseDO;

/**
 * 用户地址 DO
 *
 * @author yshop
 */
@TableName("yshop_user_address")
@KeySequence("yshop_user_address_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAddressDO extends BaseDO {

    /**
     * 用户地址id
     */
    @TableId
    private Long id;
    /**
     * 用户id
     */
    private Long uid;
    /**
     * 收货人姓名
     */
    private String realName;
    /**
     * 收货人电话
     */
    private String phone;
    /**
     * 收货人所在省
     */
    private String province;
    /**
     * 收货人所在市
     */
    private String city;
    /**
     * 城市id
     */
    private Integer cityId;
    /**
     * 收货人所在区
     */
    private String district;
    /**
     * 收货人详细地址
     */
    private String detail;
    /**
     * 邮编
     */
    private String postCode;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 是否默认
     */
    private Integer isDefault;

    /**
     * 地址
     */
    private String address;

}

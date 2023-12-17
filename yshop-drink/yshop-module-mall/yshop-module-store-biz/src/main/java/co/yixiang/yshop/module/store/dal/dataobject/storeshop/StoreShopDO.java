package co.yixiang.yshop.module.store.dal.dataobject.storeshop;

import co.yixiang.yshop.framework.mybatis.core.type.StringListTypeHandler;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import co.yixiang.yshop.framework.mybatis.core.dataobject.BaseDO;

/**
 * 门店管理 DO
 *
 * @author yshop
 */
@TableName(value = "yshop_store_shop",autoResultMap = true)
@KeySequence("yshop_store_shop_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreShopDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 店铺名称
     */
    private String name;
    /**
     * 店铺电话
     */
    private String mobile;
    /**
     * 图片
     */
    private String image;
    /**
     * 多张图片
     */
    @TableField(typeHandler = StringListTypeHandler.class)
    private List<String> images;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 地图定位地址
     */
    private String addressMap;
    /**
     * 经度
     */
    private String lng;
    /**
     * 纬度
     */
    private String lat;
    /**
     * 外卖配送距离,单位为千米。0表示不送外卖
     */
    private Integer distance;
    /**
     * 起送价钱
     */
    private BigDecimal minPrice;
    /**
     * 配送价格
     */
    private BigDecimal deliveryPrice;
    /**
     * 公告
     */
    private String notice;
    /**
     * 是否营业:0=否,1=是
     */
    private Integer status;
    /**
     * 管理员id
     */
    @TableField(typeHandler = StringListTypeHandler.class)
    private List<String> adminId;
    /**
     * 打印机id
     */
    private String uniprintId;
    /**
     * 营业开始时间
     */
    private Date startTime;
    /**
     * 营业结束时间
     */
    private Date endTime;

}

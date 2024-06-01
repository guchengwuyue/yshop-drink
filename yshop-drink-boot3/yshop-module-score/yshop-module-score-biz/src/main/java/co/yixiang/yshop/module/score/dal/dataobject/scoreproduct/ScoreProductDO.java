package co.yixiang.yshop.module.score.dal.dataobject.scoreproduct;

import co.yixiang.yshop.framework.mybatis.core.type.StringListTypeHandler;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import co.yixiang.yshop.framework.mybatis.core.dataobject.BaseDO;

/**
 * 积分产品 DO
 *
 * @author yshop
 */
@TableName(value = "yshop_score_product",autoResultMap = true)
@KeySequence("yshop_score_product_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoreProductDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 产品标题
     */
    private String title;
    /**
     * 主图
     */
    private String image;
    /**
     * 组图
     */
    @TableField(typeHandler = StringListTypeHandler.class)
    private List<String> images;
    /**
     * 详情
     */
    @TableField(value = "`desc`")
    private String desc;
    /**
     * 消耗积分
     */
    private Integer score;
    /**
     * 权重
     */
    private Integer weigh;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 销售量
     */
    private Integer sales;
    /**
     * 是否上架:0=否,1=是
     */
    private Integer isSwitch;

}

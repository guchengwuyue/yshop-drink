package co.yixiang.yshop.module.express.dal.dataobject.express;

import co.yixiang.yshop.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 快递公司 DO
 *
 * @author yshop
 */
@TableName("yshop_express")
@KeySequence("yshop_express_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpressDO extends BaseDO {

    /**
     * 快递公司id
     */
    @TableId
    private Integer id;
    /**
     * 快递公司简称
     */
    private String code;
    /**
     * 快递公司全称
     */
    private String name;
    /**
     * 排序
     */
    private Integer sort;

}

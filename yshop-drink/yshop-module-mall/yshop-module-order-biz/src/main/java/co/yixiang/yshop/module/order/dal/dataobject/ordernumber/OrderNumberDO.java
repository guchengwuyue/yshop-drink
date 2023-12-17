package co.yixiang.yshop.module.order.dal.dataobject.ordernumber;

import co.yixiang.yshop.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单 DO
 *
 * @author yshop
 */
@TableName("yshop_order_number")
@KeySequence("yshop_order_number_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderNumberDO  {

    /**
     * ID
     */
    private Long id;
    /**
     * 订单号
     */
    private String orderId;


}

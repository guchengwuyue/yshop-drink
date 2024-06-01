package co.yixiang.yshop.module.order.dal.dataobject.storeorderstatus;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import co.yixiang.yshop.framework.mybatis.core.dataobject.BaseDO;

/**
 * 订单操作记录 DO
 *
 * @author yshop
 */
@TableName("yshop_store_order_status")
@KeySequence("yshop_store_order_status_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreOrderStatusDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 订单id
     */
    private Long oid;
    /**
     * 操作类型
     */
    private String changeType;
    /**
     * 操作备注
     */
    private String changeMessage;
    /**
     * 操作时间
     */
    private LocalDateTime changeTime;

}

package co.yixiang.yshop.module.product.dal.dataobject.storeproductreply;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import co.yixiang.yshop.framework.mybatis.core.dataobject.BaseDO;

/**
 * 评论 DO
 *
 * @author yshop
 */
@TableName("yshop_store_product_reply")
@KeySequence("yshop_store_product_reply_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreProductReplyDO extends BaseDO {

    /**
     * 评论ID
     */
    @TableId
    private Long id;
    /**
     * 用户ID
     */
    private Long uid;
    /**
     * 订单ID
     */
    private Long oid;
    /**
     * 唯一id
     */
    @TableField(value = "`unique`")
    private String unique;
    /**
     * 产品id
     */
    private Long productId;
    /**
     * 某种商品类型(普通商品、秒杀商品）
     */
    private String replyType;
    /**
     * 商品分数
     */
    private Integer productScore;
    /**
     * 服务分数
     */
    private Integer serviceScore;
    /**
     * 评论内容
     */
    private String comment;
    /**
     * 评论图片
     */
    private String pics;
    /**
     * 管理员回复内容
     */
    private String merchantReplyContent;
    /**
     * 管理员回复时间
     */
    private LocalDateTime merchantReplyTime;
    /**
     * 0未回复1已回复
     */
    private Integer isReply;

}

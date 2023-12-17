package co.yixiang.yshop.module.member.controller.app.user.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户账单 vo
 *
 * @author yshop
 */
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUserBillVO {

    /**
     * 用户账单id
     */
    private Long id;
    /**
     * 用户uid
     */
    private Long uid;
    /**
     * 关联id
     */
    private String linkId;
    /**
     * 0 = 支出 1 = 获得
     */
    private Integer pm;
    /**
     * 账单标题
     */
    private String title;
    /**
     * 明细种类
     */
    private String category;
    /**
     * 明细类型
     */
    private String type;
    /**
     * 明细数字
     */
    private BigDecimal number;
    /**
     * 剩余
     */
    private BigDecimal balance;
    /**
     * 备注
     */
    private String mark;
    /**
     * 0 = 带确定 1 = 有效 -1 = 无效
     */
    private Integer status;

    private LocalDateTime createTime;



}

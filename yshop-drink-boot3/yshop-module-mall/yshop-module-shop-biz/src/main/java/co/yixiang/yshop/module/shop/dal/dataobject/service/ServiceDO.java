package co.yixiang.yshop.module.shop.dal.dataobject.service;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import co.yixiang.yshop.framework.mybatis.core.dataobject.BaseDO;

/**
 * 我的服务 DO
 *
 * @author yshop
 */
@TableName("yshop_service")
@KeySequence("yshop_service_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 标题
     */
    private String name;
    /**
     * 图标
     */
    private String image;
    /**
     * 类型:pages=页面,miniprogram=跳转小程序,menu=菜单,content=内容,call=电话
     */
    private String type;
    /**
     * 详情
     */
    private String content;
    /**
     * 父级id
     */
    private Integer pid;
    /**
     * 小程序app_id
     */
    private String appId;
    /**
     * 页面路径
     */
    private String pages;
    /**
     * 电话
     */
    private String phone;
    /**
     * 权重
     */
    private Integer weigh;
    /**
     * 状态:0=下架,1=上架
     */
    private Integer status;

}
